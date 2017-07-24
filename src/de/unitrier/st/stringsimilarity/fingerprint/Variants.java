package de.unitrier.st.stringsimilarity.fingerprint;

import java.util.HashSet;
import java.util.Set;

import static de.unitrier.st.stringsimilarity.Normalization.*;
import static de.unitrier.st.stringsimilarity.Tokenization.*;
import static de.unitrier.st.stringsimilarity.fingerprint.Base.fingerprintList;
import static de.unitrier.st.stringsimilarity.set.Base.*;

/*
 * Different variants of fingerprint-based similarity metrics.
 * All metrics should return a value between 0.0 and 1.0.
 */
public class Variants {

    public static final int WINNOWING_WINDOW_SIZE = 4; // TODO: Keep this as default?

    // ********** WINNOWING **********

    /*
    * SetSimilarity metric based on Winnowing with ngrams and dice coefficient.
    * See Papers Duric13 and Schleimer03.
    *
    * Duric13 also uses dice for Winnowing:
    */
    public static double winnowingNormalizedTokenDice(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(tokens(normalizeForEdit(str1)), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(tokens(normalizeForEdit(str2)), WINNOWING_WINDOW_SIZE)
        );

        return dice(set1, set2);
    }

    public static double winnowingNormalizedTokenDiceVariant(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(tokens(normalizeForEdit(str1)), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(tokens(normalizeForEdit(str2)), WINNOWING_WINDOW_SIZE)
        );

        return diceVariant(set1, set2);
    }

    public static double winnowingNormalizedNGramDice(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str1)), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str2)), WINNOWING_WINDOW_SIZE)
        );

        return dice(set1, set2);
    }

    public static double winnowingNormalizedNGramDice(String str1, String str2, int nGramSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str1), nGramSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str2), nGramSize), WINNOWING_WINDOW_SIZE)
        );

        return dice(set1, set2);
    }

    public static double winnowingNormalizedNGramDiceVariant(String str1, String str2, int nGramSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str1), nGramSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str2), nGramSize), WINNOWING_WINDOW_SIZE)
        );

        return diceVariant(set1, set2);
    }

    /*
     * SetSimilarity metric based on Winnowing with shingles and dice coefficient.
     * See Papers Duric13 and Schleimer03.
     */
    public static double winnowingNormalizedShingleDice(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(shingleList(tokens(normalizeForShingle(str1))), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(shingleList(tokens(normalizeForShingle(str2))), WINNOWING_WINDOW_SIZE)
        );

        return dice(set1, set2);
    }

    public static double winnowingNormalizedShingleDice(String str1, String str2, int shingleSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(shingleList(tokens(normalizeForShingle(str1)), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(shingleList(tokens(normalizeForShingle(str2)), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        return dice(set1, set2);
    }

    public static double winnowingNormalizedShingleDiceVariant(String str1, String str2, int shingleSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(shingleList(tokens(normalizeForShingle(str1)), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(shingleList(tokens(normalizeForShingle(str2)), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        return diceVariant(set1, set2);
    }


    public static double winnowingTokenDice(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(tokens(str1), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(tokens(str2), WINNOWING_WINDOW_SIZE)
        );

        return dice(set1, set2);
    }

    public static double winnowingTokenDiceVariant(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(tokens(str1), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(tokens(str2), WINNOWING_WINDOW_SIZE)
        );

        return diceVariant(set1, set2);
    }

    public static double winnowingNGramDice(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(str1), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(str2), WINNOWING_WINDOW_SIZE)
        );

        return dice(set1, set2);
    }

    public static double winnowingNGramDice(String str1, String str2, int nGramSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(str1, nGramSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(str2, nGramSize), WINNOWING_WINDOW_SIZE)
        );

        return dice(set1, set2);
    }

    public static double winnowingNGramDiceVariant(String str1, String str2, int nGramSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(str1, nGramSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(str2, nGramSize), WINNOWING_WINDOW_SIZE)
        );

        return diceVariant(set1, set2);
    }

    public static double winnowingShingleDice(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(shingleList(tokens(str1)), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(shingleList(tokens(str2)), WINNOWING_WINDOW_SIZE)
        );

        return dice(set1, set2);
    }

    public static double winnowingShingleDice(String str1, String str2, int shingleSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(shingleList(tokens(str1), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(shingleList(tokens(str2), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        return dice(set1, set2);
    }

    public static double winnowingShingleDiceVariant(String str1, String str2, int shingleSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(shingleList(tokens(str1), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(shingleList(tokens(str2), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        return diceVariant(set1, set2);
    }

    public static double winnowingNormalizedTokenJaccard(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(tokens(normalizeForEdit(str1)), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(tokens(normalizeForEdit(str2)), WINNOWING_WINDOW_SIZE)
        );

        return jaccard(set1, set2);
    }

    public static double winnowingNormalizedNGramJaccard(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str1)), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str2)), WINNOWING_WINDOW_SIZE)
        );

        return jaccard(set1, set2);
    }

    public static double winnowingNormalizedNGramJaccard(String str1, String str2, int nGramSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str1), nGramSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str2), nGramSize), WINNOWING_WINDOW_SIZE)
        );

        return jaccard(set1, set2);
    }

    public static double winnowingNormalizedShingleJaccard(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(shingleList(tokens(normalizeForShingle(str1))), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(shingleList(tokens(normalizeForShingle(str2))), WINNOWING_WINDOW_SIZE)
        );

        return jaccard(set1, set2);
    }

    public static double winnowingNormalizedShingleJaccard(String str1, String str2, int shingleSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(shingleList(tokens(normalizeForShingle(str1)), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(shingleList(tokens(normalizeForShingle(str2)), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        return jaccard(set1, set2);
    }

    public static double winnowingTokenJaccard(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(tokens(str1), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(tokens(str2), WINNOWING_WINDOW_SIZE)
        );

        return jaccard(set1, set2);
    }

    public static double winnowingNGramJaccard(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(str1), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(str2), WINNOWING_WINDOW_SIZE)
        );

        return jaccard(set1, set2);
    }

    public static double winnowingNGramJaccard(String str1, String str2, int nGramSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(str1, nGramSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(str2, nGramSize), WINNOWING_WINDOW_SIZE)
        );

        return jaccard(set1, set2);
    }

    public static double winnowingShingleJaccard(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(shingleList(tokens(str1)), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(shingleList(tokens(str2)), WINNOWING_WINDOW_SIZE)
        );

        return jaccard(set1, set2);
    }

    public static double winnowingShingleJaccard(String str1, String str2, int shingleSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(shingleList(tokens(str1), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(shingleList(tokens(str2), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        return jaccard(set1, set2);
    }


    public static double winnowingNormalizedTokenOverlap(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(tokens(normalizeForEdit(str1)), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(tokens(normalizeForEdit(str2)), WINNOWING_WINDOW_SIZE)
        );

        return overlap(set1, set2);
    }

    public static double winnowingNormalizedNGramOverlap(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str1)), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str2)), WINNOWING_WINDOW_SIZE)
        );

        return overlap(set1, set2);
    }

    public static double winnowingNormalizedNGramOverlap(String str1, String str2, int nGramSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str1), nGramSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str2), nGramSize), WINNOWING_WINDOW_SIZE)
        );

        return overlap(set1, set2);
    }

    public static double winnowingNormalizedShingleOverlap(String str1, String str2, int shingleSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(shingleList(tokens(normalizeForShingle(str1)), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(shingleList(tokens(normalizeForShingle(str2)), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        return overlap(set1, set2);
    }


    public static double winnowingTokenOverlap(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(tokens(str1), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(tokens(str2), WINNOWING_WINDOW_SIZE)
        );

        return overlap(set1, set2);
    }

    public static double winnowingNGramOverlap(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(str1), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(str2), WINNOWING_WINDOW_SIZE)
        );

        return overlap(set1, set2);
    }

    public static double winnowingNGramOverlap(String str1, String str2, int nGramSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(str1, nGramSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(str2, nGramSize), WINNOWING_WINDOW_SIZE)
        );

        return overlap(set1, set2);
    }

    public static double winnowingShingleOverlap(String str1, String str2, int shingleSize) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(shingleList(tokens(str1), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(shingleList(tokens(str2), shingleSize), WINNOWING_WINDOW_SIZE)
        );

        return overlap(set1, set2);
    }
}
