package de.unitrier.st.stringsimilarity.edit;

import static de.unitrier.st.stringsimilarity.Tokenization.*;

/*
 * Different variants of edit-based similarity metrics using default values.
 *
 * All metric variants must be a BiFunction<String, String, Double> and return a value between 0.0 and 1.0.
 */
public class Default {

    // ********** OPTIMAL ALIGNMENT **********

    // ngrams + fingerprints
    public static double nGramFingerprintOptimalAlignment(String str1, String str2) {
        return Base.nGramFingerprintOptimalAlignment(str1, str2, NGRAM_SIZE);
    }

    // ngrams + fingerprints + normalization
    public static double nGramFingerprintOptimalAlignmentNormalized(String str1, String str2) {
        return Base.nGramFingerprintOptimalAlignmentNormalized(str1, str2, NGRAM_SIZE);
    }

    // shingles + fingerprints
    public static double nShingleFingerprintOptimalAlignment(String str1, String str2) {
        return Base.shingleFingerprintOptimalAlignment(str1, str2, SHINGLE_SIZE);
    }

    // shingles + fingerprints + normalization
    public static double nShingleFingerprintOptimalAlignmentNormalized(String str1, String str2) {
        return Base.shingleFingerprintOptimalAlignmentNormalized(str1, str2, SHINGLE_SIZE);
    }


    // ********** LONGEST COMMON SUBSEQUENCE **********

    // Remark: LCS makes only sense on Strings directly or on hashes of ngrams or shingles for speedup

    // ngrams + fingerprints
    public static double nGramFingerprintLongestCommonSubsequence(String str1, String str2) {
        return Base.nGramFingerprintLongestCommonSubsequence(str1, str2, NGRAM_SIZE);
    }

    // ngrams + fingerprints + normalization
    public static double nGramFingerprintLongestCommonSubsequenceNormalized(String str1, String str2) {
        return Base.nGramFingerprintLongestCommonSubsequenceNormalized(str1, str2, NGRAM_SIZE);
    }

    // shingles + fingerprints
    public static double shingleFingerprintLongestCommonSubsequence(String str1, String str2) {
        return Base.shingleFingerprintLongestCommonSubsequence(str1, str2, SHINGLE_SIZE);
    }

    // shingles + fingerprints + normalization
    public static double shingleFingerprintLongestCommonSubsequenceNormalized(String str1, String str2) {
        return Base.shingleFingerprintLongestCommonSubsequenceNormalized(str1, str2, SHINGLE_SIZE);
    }

}
