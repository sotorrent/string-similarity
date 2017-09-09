package de.unitrier.st.stringsimilarity.set;

import com.google.common.collect.Sets;

import java.util.Set;

import static de.unitrier.st.stringsimilarity.Normalization.normalizeForNGram;
import static de.unitrier.st.stringsimilarity.Normalization.normalizeForShingle;
import static de.unitrier.st.stringsimilarity.Tokenization.*;
import static de.unitrier.st.stringsimilarity.Tokenization.DEFAULT_SEPARATOR;

/*
 * Set-based similarity metrics.
 *
 * All base metrics must return a value between 0.0 and 1.0.
 */
public class Base {

    public static final double THRESHOLD = 0.6;

    // ********** JACCARD **********

    // https://en.wikipedia.org/wiki/Jaccard_index
    public static <T> double jaccard(Set<T> s1, Set<T> s2) {
        if (s1.size() == 0 && s2.size() == 0) {
            return 1.0;
        } else {
            return (double) Sets.intersection(s1, s2).size() / Sets.union(s1, s2).size();
        }
    }

    // ngrams
    static double nGramJaccard(String str1, String str2, int n) {
        Set<String> nGramSet1 = nGramSet( str1, n);
        Set<String> nGramSet2 = nGramSet(str2, n);

        return jaccard(nGramSet1, nGramSet2);
    }

    // ngrams + normalization
    static double nGramJaccardNormalized(String str1, String str2, int n) {
        Set<String> nGramSet1 = nGramSet(normalizeForNGram(str1), n);
        Set<String> nGramSet2 = nGramSet(normalizeForNGram(str2), n);

        return jaccard(nGramSet1, nGramSet2);
    }

    // ngrams + normalization + padding
    static double nGramJaccardNormalizedPadding(String str1, String str2, int n) {
        Set<String> nGramSet1 = nGramSet(normalizeForNGram(str1), n, true);
        Set<String> nGramSet2 = nGramSet(normalizeForNGram(str2), n, true);

        return jaccard(nGramSet1, nGramSet2);
    }

    // shingles
    static double shingleJaccard(String str1, String str2, int n) {
        Set<String> shingleSet1 = shingleSet(tokens(str1), DEFAULT_SEPARATOR, n);
        Set<String> shingleSet2 = shingleSet(tokens(str2), DEFAULT_SEPARATOR, n);

        return jaccard(shingleSet1, shingleSet2);
    }

    // shingles + normalization
    static double shingleJaccardNormalized(String str1, String str2, int n) {
        Set<String> shingleSet1 = shingleSet(tokens(normalizeForShingle(str1)), DEFAULT_SEPARATOR, n);
        Set<String> shingleSet2 = shingleSet(tokens(normalizeForShingle(str2)), DEFAULT_SEPARATOR, n);

        return jaccard(shingleSet1, shingleSet2);
    }


    // ********** DICE **********

    // https://en.wikipedia.org/wiki/S%C3%B8rensen%E2%80%93Dice_coefficient
    public static <T> double dice(Set<T> s1, Set<T> s2) {
        if (s1.size() == 0 && s2.size() == 0) {
            return 1.0;
        } else {
            return (double) 2 * Sets.intersection(s1, s2).size() / (s1.size() + s2.size());
        }
    }

    // ngrams
    static double nGramDice(String str1, String str2, int n){
        Set<String> nGramSet1 = nGramSet(str1, n);
        Set<String> nGramSet2 = nGramSet(str2, n);

        return dice(nGramSet1, nGramSet2);
    }

    // ngrams + normalization
    static double nGramDiceNormalized(String str1, String str2, int n){
        Set<String> nGramSet1 = nGramSet(normalizeForNGram(str1), n);
        Set<String> nGramSet2 = nGramSet(normalizeForNGram(str2), n);

        return dice(nGramSet1, nGramSet2);
    }

    // ngram + normalization + padding
    static double nGramDiceNormalizedPadding(String str1, String str2, int n){
        Set<String> nGramSet1 = nGramSet(normalizeForNGram(str1), n, true);
        Set<String> nGramSet2 = nGramSet(normalizeForNGram(str2), n, true);

        return dice(nGramSet1, nGramSet2);
    }

    // shingles
    static double shingleDice(String str1, String str2, int n) {
        Set<String> shingleSet1 = shingleSet(tokens(str1), DEFAULT_SEPARATOR, n);
        Set<String> shingleSet2 = shingleSet(tokens(str2), DEFAULT_SEPARATOR, n);

        return dice(shingleSet1, shingleSet2);
    }

    // shingles + normalization
    static double shingleDiceNormalized(String str1, String str2, int n) {
        Set<String> shingleSet1 = shingleSet(tokens(normalizeForShingle(str1)), DEFAULT_SEPARATOR, n);
        Set<String> shingleSet2 = shingleSet(tokens(normalizeForShingle(str2)), DEFAULT_SEPARATOR, n);

        return dice(shingleSet1, shingleSet2);
    }


    // ********** DICE VARIANT **********

    // See paper Duric13
    public static <T> double diceVariant(Set<T> s1, Set<T> s2) {
        if (s1.size() == 0 && s2.size() == 0) {
            return 1.0;
        } else {
            return (double) 2 * Sets.intersection(s1, s2).size() / (s1.size() < s2.size() ? s1.size() : s2.size());
        }
    }

    // ngrams
    static double nGramDiceVariant(String str1, String str2, int n){
        Set<String> nGramSet1 = nGramSet(str1, n);
        Set<String> nGramSet2 = nGramSet(str2, n);

        return diceVariant(nGramSet1, nGramSet2);
    }

    // ngrams + normalization
    public static double nGramDiceVariantNormalized(String str1, String str2, int n){
        Set<String> nGramSet1 = nGramSet(normalizeForNGram(str1), n);
        Set<String> nGramSet2 = nGramSet(normalizeForNGram(str2), n);

        return diceVariant(nGramSet1, nGramSet2);
    }

    // shingles
    static double shingleDiceVariant(String str1, String str2, int n) {
        Set<String> shingleSet1 = shingleSet(tokens(str1), DEFAULT_SEPARATOR, n);
        Set<String> shingleSet2 = shingleSet(tokens(str2), DEFAULT_SEPARATOR, n);

        return diceVariant(shingleSet1, shingleSet2);
    }

    // shingles + normalization
    static double shingleDiceVariantNormalized(String str1, String str2, int n) {
        Set<String> shingleSet1 = shingleSet(tokens(normalizeForShingle(str1)), DEFAULT_SEPARATOR, n);
        Set<String> shingleSet2 = shingleSet(tokens(normalizeForShingle(str2)), DEFAULT_SEPARATOR, n);

        return diceVariant(shingleSet1, shingleSet2);
    }


    // ********** OVERLAP **********

    // https://en.wikipedia.org/wiki/Overlap_coefficient
    public static <T> double overlap(Set<T> s1, Set<T> s2) {
        if (s1.size() == 0 && s2.size() == 0) {
            return 1.0;
        } else {
            return (double) Sets.intersection(s1, s2).size() / Math.min(s1.size(), s2.size());
        }
    }

    // ngrams
    static double nGramOverlap(String str1, String str2, int n){
        Set<String> nGramSet1 = nGramSet(str1, n);
        Set<String> nGramSet2 = nGramSet(str2, n);

        return overlap(nGramSet1, nGramSet2);
    }

    // ngrams + normalization
    static double nGramOverlapNormalized(String str1, String str2, int n){
        Set<String> nGramSet1 = nGramSet(normalizeForNGram(str1), n);
        Set<String> nGramSet2 = nGramSet(normalizeForNGram(str2), n);

        return overlap(nGramSet1, nGramSet2);
    }

    // ngrams + normalization + padding
    static double nGramOverlapNormalizedPadding(String str1, String str2, int n){
        Set<String> nGramSet1 = nGramSet(normalizeForNGram(str1), n, true);
        Set<String> nGramSet2 = nGramSet(normalizeForNGram(str2), n, true);

        return overlap(nGramSet1, nGramSet2);
    }

    // shingles
    static double shingleOverlap(String str1, String str2, int n) {
        Set<String> shingleSet1 = shingleSet(tokens(str1), DEFAULT_SEPARATOR, n);
        Set<String> shingleSet2 = shingleSet(tokens(str2), DEFAULT_SEPARATOR, n);

        return overlap(shingleSet1, shingleSet2);
    }

    // shingles + normalization
    static double shingleOverlapNormalized(String str1, String str2, int n) {
        Set<String> shingleSet1 = shingleSet(tokens(normalizeForShingle(str1)), DEFAULT_SEPARATOR, n);
        Set<String> shingleSet2 = shingleSet(tokens(normalizeForShingle(str2)), DEFAULT_SEPARATOR, n);

        return overlap(shingleSet1, shingleSet2);
    }
}
