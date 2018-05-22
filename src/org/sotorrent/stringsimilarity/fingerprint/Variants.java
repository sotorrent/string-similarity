package org.sotorrent.stringsimilarity.fingerprint;

/*
 * Different variants of fingerprint-based similarity metrics.
 *
 * All metric variants must be a BiFunction<String, String, Double> and return a value between 0.0 and 1.0.
 */
public class Variants {

    // ngrams + dice
    public static double winnowingTwoGramDice(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 2,
                org.sotorrent.stringsimilarity.set.Base::dice
        );
    }

    public static double winnowingThreeGramDice(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 3,
                org.sotorrent.stringsimilarity.set.Base::dice
        );
    }


    public static double winnowingFourGramDice(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 4,
                org.sotorrent.stringsimilarity.set.Base::dice
        );
    }


    public static double winnowingFiveGramDice(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 5,
                org.sotorrent.stringsimilarity.set.Base::dice
        );
    }

    // ngrams + dice + normalization
    public static double winnowingTwoGramDiceNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 2,
                org.sotorrent.stringsimilarity.set.Base::dice
        );
    }

    public static double winnowingThreeGramDiceNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 3,
                org.sotorrent.stringsimilarity.set.Base::dice
        );
    }

    public static double winnowingFourGramDiceNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 4,
                org.sotorrent.stringsimilarity.set.Base::dice
        );
    }

    public static double winnowingFiveGramDiceNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 5,
                org.sotorrent.stringsimilarity.set.Base::dice
        );
    }

    // ngrams + jaccard
    public static double winnowingTwoGramJaccard(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 2,
                org.sotorrent.stringsimilarity.set.Base::jaccard
        );
    }

    public static double winnowingThreeGramJaccard(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 3,
                org.sotorrent.stringsimilarity.set.Base::jaccard
        );
    }

    public static double winnowingFourGramJaccard(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 4,
                org.sotorrent.stringsimilarity.set.Base::jaccard
        );
    }

    public static double winnowingFiveGramJaccard(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 5,
                org.sotorrent.stringsimilarity.set.Base::jaccard
        );
    }

    // ngrams + jaccard + normalization
    public static double winnowingTwoGramJaccardNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 2,
                org.sotorrent.stringsimilarity.set.Base::jaccard
        );
    }

    public static double winnowingThreeGramJaccardNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 3,
                org.sotorrent.stringsimilarity.set.Base::jaccard
        );
    }

    public static double winnowingFourGramJaccardNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 4,
                org.sotorrent.stringsimilarity.set.Base::jaccard
        );
    }

    public static double winnowingFiveGramJaccardNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 5,
                org.sotorrent.stringsimilarity.set.Base::jaccard
        );
    }

    // ngrams + overlap
    public static double winnowingTwoGramOverlap(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 2,
                org.sotorrent.stringsimilarity.set.Base::overlap
        );
    }

    public static double winnowingThreeGramOverlap(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 3,
                org.sotorrent.stringsimilarity.set.Base::overlap
        );
    }

    public static double winnowingFourGramOverlap(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 4,
                org.sotorrent.stringsimilarity.set.Base::overlap
        );
    }

    public static double winnowingFiveGramOverlap(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 5,
                org.sotorrent.stringsimilarity.set.Base::overlap
        );
    }

    // ngrams + overlap + normalization
    public static double winnowingTwoGramOverlapNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 2,
                org.sotorrent.stringsimilarity.set.Base::overlap
        );
    }

    public static double winnowingThreeGramOverlapNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 3,
                org.sotorrent.stringsimilarity.set.Base::overlap
        );
    }

    public static double winnowingFourGramOverlapNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 4,
                org.sotorrent.stringsimilarity.set.Base::overlap
        );
    }

    public static double winnowingFiveGramOverlapNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 5,
                org.sotorrent.stringsimilarity.set.Base::overlap
        );
    }

    // ngrams + longest-common-subsequence
    public static double winnowingTwoGramLongestCommonSubsequence(String str1, String str2) {
        return Base.winnowingNGramLongestCommonSubsequenceSimilarity(str1, str2, 2,
                org.sotorrent.stringsimilarity.edit.Base::longestCommonSubsequence);
    }

    public static double winnowingThreeGramLongestCommonSubsequence(String str1, String str2) {
        return Base.winnowingNGramLongestCommonSubsequenceSimilarity(str1, str2, 3,
                org.sotorrent.stringsimilarity.edit.Base::longestCommonSubsequence);
    }

    public static double winnowingFourGramLongestCommonSubsequence(String str1, String str2) {
        return Base.winnowingNGramLongestCommonSubsequenceSimilarity(str1, str2, 4,
                org.sotorrent.stringsimilarity.edit.Base::longestCommonSubsequence);
    }

    public static double winnowingFiveGramLongestCommonSubsequence(String str1, String str2) {
        return Base.winnowingNGramLongestCommonSubsequenceSimilarity(str1, str2, 5,
                org.sotorrent.stringsimilarity.edit.Base::longestCommonSubsequence);
    }

    // ngrams + longest-common-subsequence + normalization
    public static double winnowingTwoGramLongestCommonSubsequenceNormalized(String str1, String str2) {
        return Base.winnowingNGramLongestCommonSubsequenceSimilarityNormalized(str1, str2, 2,
                org.sotorrent.stringsimilarity.edit.Base::longestCommonSubsequence);
    }

    public static double winnowingThreeGramLongestCommonSubsequenceNormalized(String str1, String str2) {
        return Base.winnowingNGramLongestCommonSubsequenceSimilarityNormalized(str1, str2, 3,
                org.sotorrent.stringsimilarity.edit.Base::longestCommonSubsequence);
    }

    public static double winnowingFourGramLongestCommonSubsequenceNormalized(String str1, String str2) {
        return Base.winnowingNGramLongestCommonSubsequenceSimilarityNormalized(str1, str2, 4,
                org.sotorrent.stringsimilarity.edit.Base::longestCommonSubsequence);
    }

    public static double winnowingFiveGramLongestCommonSubsequenceNormalized(String str1, String str2) {
        return Base.winnowingNGramLongestCommonSubsequenceSimilarityNormalized(str1, str2, 5,
                org.sotorrent.stringsimilarity.edit.Base::longestCommonSubsequence);
    }

    // ngrams + optimal alignment
    public static double winnowingTwoGramOptimalAlignment(String str1, String str2) {
        return Base.winnowingNGramLongestCommonSubsequenceSimilarity(str1, str2, 2,
                org.sotorrent.stringsimilarity.edit.Base::optimalAlignment);
    }

    public static double winnowingThreeGramOptimalAlignment(String str1, String str2) {
        return Base.winnowingNGramLongestCommonSubsequenceSimilarity(str1, str2, 3,
                org.sotorrent.stringsimilarity.edit.Base::optimalAlignment);
    }

    public static double winnowingFourGramOptimalAlignment(String str1, String str2) {
        return Base.winnowingNGramLongestCommonSubsequenceSimilarity(str1, str2, 4,
                org.sotorrent.stringsimilarity.edit.Base::optimalAlignment);
    }

    public static double winnowingFiveGramOptimalAlignment(String str1, String str2) {
        return Base.winnowingNGramLongestCommonSubsequenceSimilarity(str1, str2, 5,
                org.sotorrent.stringsimilarity.edit.Base::optimalAlignment);
    }

    // ngrams + optimal alignment + normalization
    public static double winnowingTwoGramOptimalAlignmentNormalized(String str1, String str2) {
        return Base.winnowingNGramLongestCommonSubsequenceSimilarityNormalized(str1, str2, 2,
                org.sotorrent.stringsimilarity.edit.Base::optimalAlignment);
    }

    public static double winnowingThreeGramOptimalAlignmentNormalized(String str1, String str2) {
        return Base.winnowingNGramLongestCommonSubsequenceSimilarityNormalized(str1, str2, 3,
                org.sotorrent.stringsimilarity.edit.Base::optimalAlignment);
    }

    public static double winnowingFourGramOptimalAlignmentNormalized(String str1, String str2) {
        return Base.winnowingNGramLongestCommonSubsequenceSimilarityNormalized(str1, str2, 4,
                org.sotorrent.stringsimilarity.edit.Base::optimalAlignment);
    }

    public static double winnowingFiveGramOptimalAlignmentNormalized(String str1, String str2) {
        return Base.winnowingNGramLongestCommonSubsequenceSimilarityNormalized(str1, str2, 5,
                org.sotorrent.stringsimilarity.edit.Base::optimalAlignment);
    }

}
