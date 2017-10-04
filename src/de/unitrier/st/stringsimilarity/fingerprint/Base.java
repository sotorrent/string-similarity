package de.unitrier.st.stringsimilarity.fingerprint;

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

        Integer[] nGramHashes = getNGramHashes(nGrams);
        List<Integer> fingerprintList = new LinkedList<>();
        int minHashPos = -1; // index of minimum hash

        for (int windowBegin = 0; windowBegin <= nGramHashes.length-windowSize; windowBegin++) {
            int windowEnd = windowBegin+windowSize-1; // index of last hash in window

            if (minHashPos < windowBegin) {
                // first hash or previous minimum is no longer in window -> search for new minimum from right to left
                minHashPos = windowEnd;
                for (int currentPos = windowEnd-1; currentPos >= windowBegin; currentPos--) {
                    if (nGramHashes[currentPos] < nGramHashes[minHashPos]) {
                        minHashPos = currentPos;
                    }
                }
                fingerprintList.add(nGramHashes[minHashPos]);
            } else {
                // previous minimum is still in window -> compare new (rightmost) hash value in window with minHash
                if (nGramHashes[windowEnd] < nGramHashes[minHashPos]) {
                    minHashPos = windowEnd;
                    fingerprintList.add(nGramHashes[minHashPos]);
                }
            }
        }

        return fingerprintList;
    }

    private static Integer[] getNGramHashes(List<String> nGrams) {
        // save nGram hash values together with their position
        Integer[] nGramHashValues = new Integer[nGrams.size()];
        for (int i=0; i<nGrams.size(); i++) {
            nGramHashValues[i] = nGrams.get(i).hashCode();
        }
        return nGramHashValues;
    }

    /**
     * Retrieve hash values for all windows (without filtering)
     * Used for debugging.
     *
     * @param nGrams List of nGrams
     * @param windowSize Winnowing window size
     * @return List of lists with hash values for each window
     */
    public static List<List<Integer>> completeFingerprintList(List<String> nGrams, int windowSize) {
        List<Integer> nGramHashes = Arrays.asList(getNGramHashes(nGrams));
        return IntStream
                .iterate(0, i -> i+1)
                .limit(nGramHashes.size()-windowSize)
                .mapToObj(currentStartPos -> nGramHashes.subList(currentStartPos, currentStartPos+windowSize-1))
                .collect(Collectors.toList());
    }


    public static int getGuaranteeThreshold(int nGramSize, int windowSize) {
        // see Schleimer03 "Robust Winnowing": "for any two matching substrings of length t = w+k−1 we guarantee to select
        // the same hash value and so the match is still found;"
        return windowSize+nGramSize-1;
    }

    public static int getWindowSize(int nGramSize) {
        // see Schleimer03 section "Experiments with Web Data"
        return 2*nGramSize;
    }

    // ngrams
    static double winnowingNGramSimilarity(String str1, String str2, int nGramSize,
                                           BiFunction<Set<Integer>, Set<Integer>, Double> coefficient) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(str1, nGramSize), getWindowSize(nGramSize))
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(str2, nGramSize), getWindowSize(nGramSize))
        );

        return coefficient.apply(set1, set2);
    }

    // ngrams + normalization
    static double winnowingNGramSimilarityNormalized(String str1, String str2, int nGramSize,
                                                     BiFunction<Set<Integer>, Set<Integer>, Double> coefficient) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str1), nGramSize), getWindowSize(nGramSize))
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str2), nGramSize), getWindowSize(nGramSize))
        );

        return coefficient.apply(set1, set2);
    }

}
