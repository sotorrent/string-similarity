package de.unitrier.st.stringsimilarity.edit;

import static de.unitrier.st.stringsimilarity.Normalization.normalizeForEdit;
import static de.unitrier.st.stringsimilarity.edit.Base.nShingleFingerprintOptimalAlignment;

/*
 * Different variants of edit-based similarity metrics.
 *
 * All metric variants must be a BiFunction<String, String, Double> and return a value between 0.0 and 1.0.
 */
public class Variants {

    // ********** LEVENSHTEIN **********

    public static double levenshtein(String str1, String str2) {
        return Base.levenshtein(str1, str2);
    }

    // normalization
    public static double levenshteinNormalized(String str1, String str2) {
        return Base.levenshtein(
                normalizeForEdit(str1),
                normalizeForEdit(str2)
        );
    }

    // ********** DAMERAU-LEVENSHTEIN **********

    public static double damerauLevenshtein(String str1, String str2) {
        return Base.damerauLevenshtein(str1, str2);
    }

    // normalization
    public static double damerauLevenshteinNormalized(String str1, String str2) {
        return Base.damerauLevenshtein(
                normalizeForEdit(str1),
                normalizeForEdit(str2)
        );
    }

    // ********** OPTIMAL ALIGNMENT **********

    public static double optimalAlignment(String str1, String str2) {
        return Base.optimalAlignment(str1, str2);
    }

    // normalization
    public static double optimalAlignmentNormalized(String str1, String str2) {
        return Base.optimalAlignmentNormalized(str1, str2);
    }

    // ngrams + fingerprints
    public static double twoGramFingerprintOptimalAlignment(String str1, String str2) {
        return Base.nGramFingerprintOptimalAlignment(str1, str2, 2);
    }

    public static double threeGramFingerprintOptimalAlignment(String str1, String str2) {
        return Base.nGramFingerprintOptimalAlignment(str1, str2, 3);
    }

    public static double fourGramFingerprintOptimalAlignment(String str1, String str2) {
        return Base.nGramFingerprintOptimalAlignment(str1, str2, 4);
    }

    public static double fiveGramFingerprintOptimalAlignment(String str1, String str2) {
        return Base.nGramFingerprintOptimalAlignment(str1, str2, 5);
    }

    // ngrams + fingerprints + normalization
    public static double twoGramFingerprintOptimalAlignmentNormalized(String str1, String str2) {
        return Base.nGramFingerprintOptimalAlignmentNormalized(str1, str2, 2);
    }

    public static double threeGramFingerprintOptimalAlignmentNormalized(String str1, String str2) {
        return Base.nGramFingerprintOptimalAlignmentNormalized(str1, str2, 3);
    }

    public static double fourGramFingerprintOptimalAlignmentNormalized(String str1, String str2) {
        return Base.nGramFingerprintOptimalAlignmentNormalized(str1, str2, 4);
    }

    public static double fiveGramFingerprintOptimalAlignmentNormalized(String str1, String str2) {
        return Base.nGramFingerprintOptimalAlignmentNormalized(str1, str2, 5);
    }

    // shingles + fingerprints
    public static double twoShingleFingerprintOptimalAlignment(String str1, String str2) {
        return nShingleFingerprintOptimalAlignment(str1, str2, 2);
    }

    public static double threeShingleFingerprintOptimalAlignment(String str1, String str2) {
        return nShingleFingerprintOptimalAlignment(str1, str2, 3);
    }

    // shingles + fingerprints + normalization
    public static double twoShingleFingerprintOptimalAlignmentNormalized(String str1, String str2) {
        return Base.nShingleFingerprintOptimalAlignmentNormalized(str1, str2, 2);
    }

    public static double threeShingleFingerprintOptimalAlignmentNormalized(String str1, String str2) {
        return Base.nShingleFingerprintOptimalAlignmentNormalized(str1, str2, 3);
    }


    // ********** LONGEST COMMON SUBSEQUENCE **********

    // Remark: LCS makes only sense on Strings directly or on hashes of ngrams or shingles for speedup

    public static double longestCommonSubsequence(String str1, String str2) {
        return Base.longestCommonSubsequence(str1, str2);
    }

    // normalization
    public static double longestCommonSubsequenceNormalized(String str1, String str2) {
        return Base.longestCommonSubsequenceNormalized(str1, str2);
    }

    // ngrams + fingerprints
    public static double twoGramFingerprintLongestCommonSubsequence(String str1, String str2) {
        return Base.nGramFingerprintLongestCommonSubsequence(str1, str2, 2);
    }

    public static double threeGramFingerprintLongestCommonSubsequence(String str1, String str2) {
        return Base.nGramFingerprintLongestCommonSubsequence(str1, str2, 3);
    }

    public static double fourGramFingerprintLongestCommonSubsequence(String str1, String str2) {
        return Base.nGramFingerprintLongestCommonSubsequence(str1, str2, 4);
    }

    public static double fiveGramFingerprintLongestCommonSubsequence(String str1, String str2) {
        return Base.nGramFingerprintLongestCommonSubsequence(str1, str2, 5);
    }

    // ngrams + fingerprints + normalization
    public static double twoGramFingerprintLongestCommonSubsequenceNormalized(String str1, String str2) {
        return Base.nGramFingerprintLongestCommonSubsequenceNormalized(str1, str2, 2);
    }

    public static double threeGramFingerprintLongestCommonSubsequenceNormalized(String str1, String str2) {
        return Base.nGramFingerprintLongestCommonSubsequenceNormalized(str1, str2, 3);
    }

    public static double fourGramFingerprintLongestCommonSubsequenceNormalized(String str1, String str2) {
        return Base.nGramFingerprintLongestCommonSubsequenceNormalized(str1, str2, 4);
    }

    public static double fiveGramFingerprintLongestCommonSubsequenceNormalized(String str1, String str2) {
        return Base.nGramFingerprintLongestCommonSubsequenceNormalized(str1, str2, 5);
    }

    // shingles + fingerprints
    public static double twoShingleFingerprintLongestCommonSubsequence(String str1, String str2) {
        return Base.nShingleFingerprintLongestCommonSubsequence(str1, str2, 2);
    }

    public static double threeShingleFingerprintLongestCommonSubsequence(String str1, String str2) {
        return Base.nShingleFingerprintLongestCommonSubsequence(str1, str2, 3);
    }

    // shingles + fingerprints + normalization
    public static double twoShingleFingerprintLongestCommonSubsequenceNormalized(String str1, String str2) {
        return Base.nShingleFingerprintLongestCommonSubsequenceNormalized(str1, str2, 2);
    }

    public static double threeShingleFingerprintLongestCommonSubsequenceNormalized(String str1, String str2) {
        return Base.nShingleFingerprintLongestCommonSubsequenceNormalized(str1, str2, 2);
    }

}
