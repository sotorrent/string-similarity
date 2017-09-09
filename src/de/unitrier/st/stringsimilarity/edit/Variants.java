package de.unitrier.st.stringsimilarity.edit;

import static de.unitrier.st.stringsimilarity.Normalization.*;
import static de.unitrier.st.stringsimilarity.Tokenization.*;
import static de.unitrier.st.stringsimilarity.edit.Base.*;
import static de.unitrier.st.stringsimilarity.fingerprint.Base.*;

/*
 * Different variants of edit-based similarity metrics.
 *
 * All metric variants must be a BiFunction<String, String, Double> and return a value between 0.0 and 1.0.
 */
public class Variants {

    // ********** LEVENSHTEIN **********

    public static double levenshteinNormalized(String str1, String str2) {
        return levenshtein(
                normalizeForEdit(str1),
                normalizeForEdit(str2)
        );
    }

    // ********** DAMERAU-LEVENSHTEIN **********

    public static double damerauLevenshteinNormalized(String str1, String str2) {
        return damerauLevenshtein(
                normalizeForEdit(str1),
                normalizeForEdit(str2)
        );
    }


    // ********** OPTIMAL ALIGNMENT **********

    public static double optimalAlignmentNormalized(String str1, String str2) {
        return optimalAlignment(
                normalizeForEdit(str1),
                normalizeForEdit(str2)
        );
    }

    public static double nGramFingerprintOptimalAlignment(String str1, String str2) {
        return optimalAlignment(
                fingerprintList(nGramList(str1)),
                fingerprintList(nGramList(str2))
        );
    }

    public static double nGramFingerprintOptimalAlignmentNormalized(String str1, String str2) {
        return optimalAlignment(
                fingerprintList(nGramList(normalizeForNGram(str1))),
                fingerprintList(nGramList(normalizeForNGram(str2)))
        );
    }

    public static double shingleFingerprintOptimalAlignment(String str1, String str2) {
        return optimalAlignment(
                fingerprintList(shingleList(tokens(str1))),
                fingerprintList(shingleList(tokens(str2)))
        );
    }

    public static double shingleFingerprintOptimalAlignmentNormalized(String str1, String str2) {
        return optimalAlignment(
                fingerprintList(shingleList(tokens(normalizeForShingle(str1)))),
                fingerprintList(shingleList(tokens(normalizeForShingle(str2))))
        );
    }


    // ********** LONGEST COMMON SUBSEQUENCE **********

    // Remark: LCS makes only sense on Strings directly or on hashes of ngrams or shingles for speedup

    public static double longestCommonSubsequenceNormalized(String str1, String str2) {
        return longestCommonSubsequence(
                normalizeForEdit(str1),
                normalizeForEdit(str2)
        );
    }

    public static double nGramFingerprintLongestCommonSubsequence(String str1, String str2) {
        return longestCommonSubsequence(
                fingerprintList(nGramList(str1)),
                fingerprintList(nGramList(str2))
        );
    }

    public static double nGramFingerprintLongestCommonSubsequenceNormalized(String str1, String str2) {
        return longestCommonSubsequence(
                fingerprintList(nGramList(normalizeForNGram(str1))),
                fingerprintList(nGramList(normalizeForNGram(str2)))
        );
    }

    public static double shingleFingerprintLongestCommonSubsequence(String str1, String str2) {
        return longestCommonSubsequence(
                fingerprintList(shingleList(tokens(str1))),
                fingerprintList(shingleList(tokens(str2)))
        );
    }

    public static double twoShingleFingerprintLongestCommonSubsequence(String str1, String str2) {
        return longestCommonSubsequence(
                fingerprintList(shingleList(tokens(str1), 2)),
                fingerprintList(shingleList(tokens(str2), 2))
        );
    }

    public static double shingleFingerprintLongestCommonSubsequenceNormalized(String str1, String str2) {
        return longestCommonSubsequence(
                fingerprintList(shingleList(tokens(normalizeForShingle(str1)))),
                fingerprintList(shingleList(tokens(normalizeForShingle(str2))))
        );
    }
}
