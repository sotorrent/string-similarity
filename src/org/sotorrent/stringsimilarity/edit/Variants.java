package org.sotorrent.stringsimilarity.edit;

import static org.sotorrent.stringsimilarity.Normalization.normalizeForEdit;

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


    // ********** LONGEST COMMON SUBSEQUENCE **********

    // Remark: LCS makes only sense on Strings directly or on hashes of ngrams or shingles for speedup

    public static double longestCommonSubsequence(String str1, String str2) {
        return Base.longestCommonSubsequence(str1, str2);
    }

    // normalization
    public static double longestCommonSubsequenceNormalized(String str1, String str2) {
        return Base.longestCommonSubsequenceNormalized(str1, str2);
    }

}
