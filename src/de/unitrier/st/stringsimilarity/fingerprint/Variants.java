package de.unitrier.st.stringsimilarity.fingerprint;

import java.util.HashSet;
import java.util.Set;

import static de.unitrier.st.stringsimilarity.Normalization.*;
import static de.unitrier.st.stringsimilarity.Tokenization.*;
import static de.unitrier.st.stringsimilarity.fingerprint.Base.WINNOWING_WINDOW_SIZE;
import static de.unitrier.st.stringsimilarity.fingerprint.Base.fingerprintList;
import static de.unitrier.st.stringsimilarity.set.Base.*;

/*
 * Different variants of fingerprint-based similarity metrics.
 *
 * All metric variants must be a BiFunction<String, String, Double> and return a value between 0.0 and 1.0.
 */
public class Variants {

    // ********** WINNOWING **********

    /*
    * Similarity metrics based on Winnowing with tokens.
    */

    // token + dice
    public static double winnowingTokenDice(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(tokens(str1), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(tokens(str2), WINNOWING_WINDOW_SIZE)
        );

        return dice(set1, set2);
    }

    // normalization + token + dice
    public static double winnowingNormalizedTokenDice(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(tokens(normalizeForEdit(str1)), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(tokens(normalizeForEdit(str2)), WINNOWING_WINDOW_SIZE)
        );

        return dice(set1, set2);
    }

    // token + dice variant
    public static double winnowingTokenDiceVariant(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(tokens(str1), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(tokens(str2), WINNOWING_WINDOW_SIZE)
        );

        return diceVariant(set1, set2);
    }

    // normalization + token + dice variant
    public static double winnowingNormalizedTokenDiceVariant(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(tokens(normalizeForEdit(str1)), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(tokens(normalizeForEdit(str2)), WINNOWING_WINDOW_SIZE)
        );

        return diceVariant(set1, set2);
    }

    // token + jaccard
    public static double winnowingTokenJaccard(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(tokens(str1), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(tokens(str2), WINNOWING_WINDOW_SIZE)
        );

        return jaccard(set1, set2);
    }

    // normalization + token + jaccard
    public static double winnowingNormalizedTokenJaccard(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(tokens(normalizeForEdit(str1)), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(tokens(normalizeForEdit(str2)), WINNOWING_WINDOW_SIZE)
        );

        return jaccard(set1, set2);
    }

    // token + overlap
    public static double winnowingTokenOverlap(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(tokens(str1), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(tokens(str2), WINNOWING_WINDOW_SIZE)
        );

        return overlap(set1, set2);
    }

    // normalization + token + overlap
    public static double winnowingNormalizedTokenOverlap(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(tokens(normalizeForEdit(str1)), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(tokens(normalizeForEdit(str2)), WINNOWING_WINDOW_SIZE)
        );

        return overlap(set1, set2);
    }


    /*
    * Similarity metrics based on Winnowing with nGrams.
    */

    // ngram + dice
    public static double winnowingNGramDice(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(str1), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(str2), WINNOWING_WINDOW_SIZE)
        );

        return dice(set1, set2);
    }

    // normalization + ngram + dice
    public static double winnowingNormalizedNGramDice(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str1)), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str2)), WINNOWING_WINDOW_SIZE)
        );

        return dice(set1, set2);
    }

    // ngram + dice variant
    public static double winnowingNGramDiceVariant(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(str1), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(str2), WINNOWING_WINDOW_SIZE)
        );

        return diceVariant(set1, set2);
    }

    // normalization + ngram + dice variant
    public static double winnowingNormalizedNGramDiceVariant(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str1)), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str2)), WINNOWING_WINDOW_SIZE)
        );

        return diceVariant(set1, set2);
    }

    // ngram + jaccard
    public static double winnowingNGramJaccard(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(str1), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(str2), WINNOWING_WINDOW_SIZE)
        );

        return jaccard(set1, set2);
    }

    // normalization + ngram + jaccard
    public static double winnowingNormalizedNGramJaccard(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str1)), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str2)), WINNOWING_WINDOW_SIZE)
        );

        return jaccard(set1, set2);
    }

    // ngram + overlap
    public static double winnowingNGramOverlap(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(str1), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(str2), WINNOWING_WINDOW_SIZE)
        );

        return overlap(set1, set2);
    }

    // normalization + ngram + overlap
    public static double winnowingNormalizedNGramOverlap(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str1)), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(normalizeForNGram(str2)), WINNOWING_WINDOW_SIZE)
        );

        return overlap(set1, set2);
    }


    /*
    * Similarity metrics based on Winnowing with shingles.
    */

    // shingle + dice
    public static double winnowingShingleDice(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(shingleList(tokens(str1)), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(shingleList(tokens(str2)), WINNOWING_WINDOW_SIZE)
        );

        return dice(set1, set2);
    }

    // normalization + shingle + dice
    public static double winnowingNormalizedShingleDice(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(shingleList(tokens(normalizeForShingle(str1))), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(shingleList(tokens(normalizeForShingle(str2))), WINNOWING_WINDOW_SIZE)
        );

        return dice(set1, set2);
    }

    // shingle + dice variant
    public static double winnowingShingleDiceVariant(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(shingleList(tokens(str1)), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(shingleList(tokens(str2)), WINNOWING_WINDOW_SIZE)
        );

        return diceVariant(set1, set2);
    }

    // normalization + shingle + dice variant
    public static double winnowingNormalizedShingleDiceVariant(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(shingleList(tokens(normalizeForShingle(str1))), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(shingleList(tokens(normalizeForShingle(str2))), WINNOWING_WINDOW_SIZE)
        );

        return diceVariant(set1, set2);
    }

    // shingle + jaccard
    public static double winnowingShingleJaccard(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(shingleList(tokens(str1)), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(shingleList(tokens(str2)), WINNOWING_WINDOW_SIZE)
        );

        return jaccard(set1, set2);
    }

    // normalization + shingle + jaccard
    public static double winnowingNormalizedShingleJaccard(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(shingleList(tokens(normalizeForShingle(str1))), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(shingleList(tokens(normalizeForShingle(str2))), WINNOWING_WINDOW_SIZE)
        );

        return jaccard(set1, set2);
    }

    // shingle + overlap
    public static double winnowingShingleOverlap(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(shingleList(tokens(str1)), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(shingleList(tokens(str2)), WINNOWING_WINDOW_SIZE)
        );

        return overlap(set1, set2);
    }

    // normalization + shingle + overlap
    public static double winnowingNormalizedShingleOverlap(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(shingleList(tokens(normalizeForShingle(str1))), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(shingleList(tokens(normalizeForShingle(str2))), WINNOWING_WINDOW_SIZE)
        );

        return overlap(set1, set2);
    }

}
