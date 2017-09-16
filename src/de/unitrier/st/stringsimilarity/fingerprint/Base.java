package de.unitrier.st.stringsimilarity.fingerprint;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
        // TODO: Also save positions of selected hashes? (see Schleimer03)
        return IntStream
                .iterate(0, i -> i+1)
                .limit(nGrams.size()-windowSize)
                .mapToObj(currentStartPos -> nGrams.subList(currentStartPos, currentStartPos+windowSize-1))
                .map(windowList -> windowList
                        .stream()
                        .map(String::hashCode)
                        .min(Integer::compare))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
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
        return IntStream
                .iterate(0, i -> i+1)
                .limit(nGrams.size()-windowSize)
                .mapToObj(currentStartPos -> nGrams.subList(currentStartPos, currentStartPos+windowSize-1))
                .map(windowList -> windowList
                        .stream()
                        .map(String::hashCode)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }


    public static int getWindowSize(int nGramSize, int guaranteeThreshold) {
        // see Schleimer03
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
