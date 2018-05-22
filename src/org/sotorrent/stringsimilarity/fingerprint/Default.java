package org.sotorrent.stringsimilarity.fingerprint;

import static org.sotorrent.stringsimilarity.Tokenization.NGRAM_SIZE;

/*
 * Different variants of fingerprint-based similarity metrics using default values.
 *
 * All metric variants must be a BiFunction<String, String, Double> and return a value between 0.0 and 1.0.
 */
public class Default {

    // ********** WINNOWING **********

    // ngrams + dice
    public static double winnowingNGramDice(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, NGRAM_SIZE,
                org.sotorrent.stringsimilarity.set.Base::dice
        );
    }

    // ngrams + dice + normalization
    public static double winnowingNGramDiceNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, NGRAM_SIZE,
                org.sotorrent.stringsimilarity.set.Base::dice
        );
    }

    // ngrams + jaccard
    public static double winnowingNGramJaccard(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, NGRAM_SIZE,
                org.sotorrent.stringsimilarity.set.Base::jaccard
        );
    }

    // ngrams + jaccard + normalization
    public static double winnowingNGramJaccardNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, NGRAM_SIZE,
                org.sotorrent.stringsimilarity.set.Base::jaccard
        );
    }

    // ngrams + overlap
    public static double winnowingNGramOverlap(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, NGRAM_SIZE,
                org.sotorrent.stringsimilarity.set.Base::overlap
        );
    }

    // ngrams + overlap + normalization
    public static double winnowingNGramOverlapNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, NGRAM_SIZE,
                org.sotorrent.stringsimilarity.set.Base::overlap
        );
    }

    // ngrams + longest-common-subsequence
    public static double winnowingNGramLongestCommonSubsequence(String str1, String str2) {
        return Base.winnowingNGramLongestCommonSubsequenceSimilarity(str1, str2, NGRAM_SIZE,
                org.sotorrent.stringsimilarity.edit.Base::longestCommonSubsequence);
    }

    // ngrams + longest-common-subsequence + normalization
    public static double winnowingNGramLongestCommonSubsequenceNormalized(String str1, String str2) {
        return Base.winnowingNGramLongestCommonSubsequenceSimilarityNormalized(str1, str2, NGRAM_SIZE,
                org.sotorrent.stringsimilarity.edit.Base::longestCommonSubsequence);
    }

    // ngrams + optimal alignment
    public static double winnowingNGramOptimalAlignment(String str1, String str2) {
        return Base.winnowingNGramLongestCommonSubsequenceSimilarity(str1, str2, NGRAM_SIZE,
                org.sotorrent.stringsimilarity.edit.Base::optimalAlignment);
    }

    // ngrams + optimal alignment + normalization
    public static double winnowingNGramOptimalAlignmentNormalized(String str1, String str2) {
        return Base.winnowingNGramLongestCommonSubsequenceSimilarityNormalized(str1, str2, NGRAM_SIZE,
                org.sotorrent.stringsimilarity.edit.Base::optimalAlignment);
    }

}
