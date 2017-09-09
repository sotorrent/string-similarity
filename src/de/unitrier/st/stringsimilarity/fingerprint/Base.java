package de.unitrier.st.stringsimilarity.fingerprint;

import com.google.common.collect.Multiset;
import de.unitrier.st.stringsimilarity.util.MultisetCollector;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static de.unitrier.st.stringsimilarity.Normalization.normalizeForNGram;
import static de.unitrier.st.stringsimilarity.Normalization.normalizeForShingle;
import static de.unitrier.st.stringsimilarity.Tokenization.nGramList;
import static de.unitrier.st.stringsimilarity.Tokenization.shingleList;
import static de.unitrier.st.stringsimilarity.Tokenization.tokens;
import static de.unitrier.st.stringsimilarity.set.Base.*;

/*
 * Fingerprint-based similarity metrics.
 *
 * All base metrics must return a value between 0.0 and 1.0.
 */
public class Base {

    public static final int WINNOWING_WINDOW_SIZE = 4; // TODO: Keep this as default?

    public static List<Integer> fingerprintList(List<String> tokens) {
        return tokens
                .stream()
                .map(String::hashCode)
                .collect(Collectors.toList());
    }

    public static List<Integer> fingerprintList(List<String> tokens, int windowSize) {
        return IntStream
                .iterate(0, i -> i+windowSize)
                .limit((int)Math.ceil((double)tokens.size()/windowSize))
                .mapToObj(currentStartPos -> tokens.subList(currentStartPos, Math.min(tokens.size(), currentStartPos+windowSize-1)))
                .map(windowList -> windowList
                        .stream()
                        .map(String::hashCode)
                        .min(Integer::compare))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public static Multiset<Integer> fingerprintMultiset(Multiset<String> tokens) {
        return tokens
                .stream()
                .map(String::hashCode)
                .collect(MultisetCollector.toMultiset());
    }

    public static Multiset<Integer> fingerprintMultiset(Multiset<String> tokens, int windowSize) {
        return fingerprintList(new ArrayList<>(tokens), windowSize)
                .stream()
                .collect(MultisetCollector.toMultiset());
    }

    public static Set<Integer> fingerprintSet(Set<String> tokens) {
        return tokens
                .stream()
                .map(String::hashCode)
                .collect(Collectors.toSet());
    }

    public static Set<Integer> fingerprintSet(Set<String> tokens, int windowSize) {
        return new HashSet<>(fingerprintList(new ArrayList<>(tokens), windowSize));
    }

    /*
    * Similarity metrics based on Winnowing.
    * See Papers Duric13 and Schleimer03.
    * Duric13 also uses dice for Winnowing.
    */

    /*
     * Winnowing base variants using Dice coefficient
     */

    // ngram + dice
    static double winnowingNGramDice(String str1, String str2, int nGramSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(str1, nGramSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(str2, nGramSize), WINNOWING_WINDOW_SIZE)
        );

        return dice(set1, set2);
    }

    // normalization + ngram + dice
    static double winnowingNormalizedNGramDice(String str1, String str2, int nGramSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str1), nGramSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str2), nGramSize), WINNOWING_WINDOW_SIZE)
        );

        return dice(set1, set2);
    }

    // shingle + dice
    static double winnowingShingleDice(String str1, String str2, int shingleSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(shingleList(tokens(str1), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(shingleList(tokens(str2), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        return dice(set1, set2);
    }

    // normalization + shingle + dice
    static double winnowingNormalizedShingleDice(String str1, String str2, int shingleSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(shingleList(tokens(normalizeForShingle(str1)), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(shingleList(tokens(normalizeForShingle(str2)), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        return dice(set1, set2);
    }


    /*
     * Winnowing base variants using Dice Variant coefficient
     */

    // ngram + dice variant
    static double winnowingNGramDiceVariant(String str1, String str2, int nGramSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(str1, nGramSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(str2, nGramSize), WINNOWING_WINDOW_SIZE)
        );

        return diceVariant(set1, set2);
    }

    // normalization + ngram + dice variant
    static double winnowingNormalizedNGramDiceVariant(String str1, String str2, int nGramSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str1), nGramSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str2), nGramSize), WINNOWING_WINDOW_SIZE)
        );

        return diceVariant(set1, set2);
    }

    // shingle + dice variant
    static double winnowingShingleDiceVariant(String str1, String str2, int shingleSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(shingleList(tokens(str1), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(shingleList(tokens(str2), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        return diceVariant(set1, set2);
    }

    // normalization + shingle + dice variant
    static double winnowingNormalizedShingleDiceVariant(String str1, String str2, int shingleSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(shingleList(tokens(normalizeForShingle(str1)), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(shingleList(tokens(normalizeForShingle(str2)), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        return diceVariant(set1, set2);
    }


    /*
     * Winnowing base variants using Jaccard coefficient
     */

    // ngram + jaccard
    static double winnowingNGramJaccard(String str1, String str2, int nGramSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(str1, nGramSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(str2, nGramSize), WINNOWING_WINDOW_SIZE)
        );

        return jaccard(set1, set2);
    }

    // normalization + ngram + jaccard
    static double winnowingNormalizedNGramJaccard(String str1, String str2, int nGramSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str1), nGramSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str2), nGramSize), WINNOWING_WINDOW_SIZE)
        );

        return jaccard(set1, set2);
    }

    // shingle + jaccard
    static double winnowingShingleJaccard(String str1, String str2, int shingleSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(shingleList(tokens(str1), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(shingleList(tokens(str2), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        return jaccard(set1, set2);
    }

    // normalization + shingle + jaccard
    static double winnowingNormalizedShingleJaccard(String str1, String str2, int shingleSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(shingleList(tokens(normalizeForShingle(str1)), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(shingleList(tokens(normalizeForShingle(str2)), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        return jaccard(set1, set2);
    }


    /*
     * Winnowing base variants using Overlap coefficient
     */

    // ngram + overlap
    static double winnowingNGramOverlap(String str1, String str2, int nGramSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(str1, nGramSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(str2, nGramSize), WINNOWING_WINDOW_SIZE)
        );

        return overlap(set1, set2);
    }

    // normalization + ngram + overflap
    static double winnowingNormalizedNGramOverlap(String str1, String str2, int nGramSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str1), nGramSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str2), nGramSize), WINNOWING_WINDOW_SIZE)
        );

        return overlap(set1, set2);
    }

    // shingle + overlap
    static double winnowingShingleOverlap(String str1, String str2, int shingleSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(shingleList(tokens(str1), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(shingleList(tokens(str2), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        return overlap(set1, set2);
    }

    // normalization + shingle + overlap
    static double winnowingNormalizedShingleOverlap(String str1, String str2, int shingleSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(shingleList(tokens(normalizeForShingle(str1)), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(shingleList(tokens(normalizeForShingle(str2)), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        return overlap(set1, set2);
    }
}
