package de.unitrier.st.stringsimilarity.fingerprint;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static de.unitrier.st.stringsimilarity.Normalization.normalizeForNGram;
import static de.unitrier.st.stringsimilarity.Tokenization.nGramList;

/*
 * Fingerprint-based similarity metrics.
 *
 * All base metrics must return a value between 0.0 and 1.0.
 */
public class Base {
    public static final int GUARANTEE_THRESHOLD = 10; // TODO: what to choose here?

    /*
    * Similarity metrics based on Winnowing.
    * See Papers Duric13 and Schleimer03.
    * Duric13 also uses dice for Winnowing.
    */

    /**
     * Retrieve the fingerprint of a list of nGrams as defined in the Winnowing algorithm presented in Schleimer03.
     *
     * @param nGrams List of nGrams
     * @param windowSize Winnowing window size
     * @return Fingerprint of nGrams (list of selected hash values)
     */
    public static List<Integer> fingerprintList(List<String> nGrams, int windowSize) {
        // TODO: Also return positions of selected hashes? (see Schleimer03)
        List<NGramHash> nGramHashes = getNGramHashes(nGrams);
        return IntStream
                .iterate(0, i -> i+1)
                .limit(nGramHashes.size()-windowSize)
                .mapToObj(currentStartPos -> nGramHashes.subList(currentStartPos, currentStartPos+windowSize-1))
                .map(windowList -> windowList
                        .stream()
                        .min(NGramHash::compareTo))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .distinct() // uses equals()
                .map(n -> n.hashValue)
                .collect(Collectors.toList());
    }

    private static List<NGramHash> getNGramHashes(List<String> nGrams) {
        // save nGram hash values together with their position
        List<NGramHash> nGramHashValues = new ArrayList<>(nGrams.size());
        for (int i=0; i<nGrams.size(); i++) {
            nGramHashValues.add(new NGramHash(nGrams.get(i).hashCode(), i));
        }
        return nGramHashValues;
    }

    public static class NGramHash implements Comparable<NGramHash> {
        public int hashValue;
        public int pos;

        public NGramHash(int hash, int pos) {
            this.hashValue = hash;
            this.pos = pos;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof NGramHash) {
                NGramHash other = (NGramHash) obj;
                return (this.hashValue == other.hashValue && this.pos == other.pos);
            } else {
                return false;
            }
        }

        @Override
        public int compareTo(@Nonnull NGramHash o) {
            if (this.hashValue > o.hashValue) {
                return 1;
            } else if (this.hashValue == o.hashValue) {
                return Integer.compare(this.pos, o.pos);
            } else {
                return -1;
            }
        }
    }

    /**
     * Retrieve hash values for all windows (without filtering)
     * Used for debugging.
     *
     * @param nGrams List of nGrams
     * @param windowSize Winnowing window size
     * @return List of lists with hash values for each window
     */
    public static List<List<NGramHash>> completeFingerprintList(List<String> nGrams, int windowSize) {
        List<NGramHash> nGramHashes = getNGramHashes(nGrams);
        return IntStream
                .iterate(0, i -> i+1)
                .limit(nGramHashes.size()-windowSize)
                .mapToObj(currentStartPos -> nGramHashes.subList(currentStartPos, currentStartPos+windowSize-1))
                .collect(Collectors.toList());
    }


    public static int getWindowSize(int nGramSize, int guaranteeThreshold) {
        // see Schleimer03: "Let the window size be w = t − k + 1. Consider the sequence of hashes h1 h2 . . . hn that
        // represents a document. Each position 1 ≤ i ≤ n − w + 1 in this sequence defines a window of hashes
        // hi . . . hi+w−1 ."
        if (nGramSize > guaranteeThreshold) {
            throw new IllegalArgumentException("nGramSize must not be larger than guaranteeThreshold!");
        }
        return guaranteeThreshold-nGramSize+1;
    }

    // ngrams
    static double winnowingNGramSimilarity(String str1, String str2, int nGramSize, int guaranteeThreshold,
                                           BiFunction<Set<Integer>, Set<Integer>, Double> coefficient) {

        int windowSize = getWindowSize(nGramSize, guaranteeThreshold);

        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(str1, nGramSize), windowSize)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(str2, nGramSize), windowSize)
        );

        return coefficient.apply(set1, set2);
    }

    // ngrams + normalization
    static double winnowingNGramSimilarityNormalized(String str1, String str2, int nGramSize, int guaranteeThreshold,
                                                     BiFunction<Set<Integer>, Set<Integer>, Double> coefficient) {

        int windowSize = getWindowSize(nGramSize, guaranteeThreshold);

        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str1), nGramSize), windowSize)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str2), nGramSize), windowSize)
        );

        return coefficient.apply(set1, set2);
    }

}
