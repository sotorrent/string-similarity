package de.unitrier.st.stringsimilarity.profile;

import static de.unitrier.st.stringsimilarity.profile.Base.*;

/*
 * Different variants of profile-based similarity metrics.
 *
 * All metric variants must be a BiFunction<String, String, Double> and return a value between 0.0 and 1.0.
 */
public class Variants {

    // Remark: Always normalize here to reduce number of dimensions.

    // ********** Cosine **********

    // tokens
    public static double cosineTokenNormalizedBool(String str1, String str2) {
        return Default.cosineTokenNormalizedBool(str1, str2);
    }

    public static double cosineTokenNormalizedTermFrequency(String str1, String str2) {
        return Default.cosineTokenNormalizedTermFrequency(str1, str2);
    }

    public static double cosineTokenNormalizedNormalizedTermFrequency(String str1, String str2) {
        return Default.cosineTokenNormalizedNormalizedTermFrequency(str1, str2);
    }

    // ngrams
    public static double cosineTwoGramNormalizedBool(String str1, String str2) {
        return cosineNGramNormalized(str1, str2, 2, Base.WeightingScheme.BOOL);
    }

    public static double cosineTwoGramNormalizedTermFrequency(String str1, String str2) {
        return cosineNGramNormalized(str1, str2, 2, Base.WeightingScheme.TERM_FREQUENCY);
    }

    public static double cosineTwoGramNormalizedNormalizedTermFrequency(String str1, String str2) {
        return cosineNGramNormalized(str1, str2, 2, Base.WeightingScheme.NORMALIZED_TERM_FREQUENCY);
    }

    public static double cosineThreeGramNormalizedBool(String str1, String str2) {
        return cosineNGramNormalized(str1, str2, 3, Base.WeightingScheme.BOOL);
    }

    public static double cosineThreeGramNormalizedTermFrequency(String str1, String str2) {
        return cosineNGramNormalized(str1, str2, 3, Base.WeightingScheme.TERM_FREQUENCY);
    }

    public static double cosineThreeGramNormalizedNormalizedTermFrequency(String str1, String str2) {
        return cosineNGramNormalized(str1, str2, 3, Base.WeightingScheme.NORMALIZED_TERM_FREQUENCY);
    }

    public static double cosineFourGramNormalizedBool(String str1, String str2) {
        return cosineNGramNormalized(str1, str2, 4, Base.WeightingScheme.BOOL);
    }

    public static double cosineFourGramNormalizedTermFrequency(String str1, String str2) {
        return cosineNGramNormalized(str1, str2, 4, Base.WeightingScheme.TERM_FREQUENCY);
    }

    public static double cosineFourGramNormalizedNormalizedTermFrequency(String str1, String str2) {
        return cosineNGramNormalized(str1, str2, 4, Base.WeightingScheme.NORMALIZED_TERM_FREQUENCY);
    }

    public static double cosineFiveGramNormalizedBool(String str1, String str2) {
        return cosineNGramNormalized(str1, str2, 5, Base.WeightingScheme.BOOL);
    }

    public static double cosineFiveGramNormalizedTermFrequency(String str1, String str2) {
        return cosineNGramNormalized(str1, str2, 5, Base.WeightingScheme.TERM_FREQUENCY);
    }

    public static double cosineFiveGramNormalizedNormalizedTermFrequency(String str1, String str2) {
        return cosineNGramNormalized(str1, str2, 5, Base.WeightingScheme.NORMALIZED_TERM_FREQUENCY);
    }

    // shingles
    public static double cosineTwoShingleNormalizedBool(String str1, String str2) {
        return cosineNShingleNormalized(str1, str2, 2, Base.WeightingScheme.BOOL);
    }

    public static double cosineTwoShingleNormalizedTermFrequency(String str1, String str2) {
        return cosineNShingleNormalized(str1, str2, 2, Base.WeightingScheme.TERM_FREQUENCY);
    }

    public static double cosineTwoShingleNormalizedNormalizedTermFrequency(String str1, String str2) {
        return cosineNShingleNormalized(str1, str2, 2, Base.WeightingScheme.NORMALIZED_TERM_FREQUENCY);
    }

    public static double cosineThreeShingleNormalizedBool(String str1, String str2) {
        return cosineNShingleNormalized(str1, str2, 3, Base.WeightingScheme.BOOL);
    }

    public static double cosineThreeShingleNormalizedTermFrequency(String str1, String str2) {
        return cosineNShingleNormalized(str1, str2, 3, Base.WeightingScheme.TERM_FREQUENCY);
    }

    public static double cosineThreeShingleNormalizedNormalizedTermFrequency(String str1, String str2) {
        return cosineNShingleNormalized(str1, str2, 3, Base.WeightingScheme.NORMALIZED_TERM_FREQUENCY);
    }


    // ********** Manhattan / qGram **********

    // tokens
    public static double manhattanTokenNormalized(String str1, String str2) {
        return Base.manhattanTokenNormalized(str1, str2);
    }

    // ngrams
    public static double manhattanTwoGramNormalized(String str1, String str2) {
        return manhattanNGramNormalized(str1, str2, 2);
    }

    public static double manhattanThreeGramNormalized(String str1, String str2) {
        return manhattanNGramNormalized(str1, str2, 3);
    }

    public static double manhattanFourGramNormalized(String str1, String str2) {
        return manhattanNGramNormalized(str1, str2, 4);
    }

    public static double manhattanFiveGramNormalized(String str1, String str2) {
        return manhattanNGramNormalized(str1, str2, 5);
    }

    // shingles
    public static double manhattanTwoShingleNormalized(String str1, String str2) {
        return manhattanNShingleNormalized(str1, str2, 2);
    }

    public static double manhattanThreeShingleNormalized(String str1, String str2) {
        return manhattanNShingleNormalized(str1, str2, 3);
    }

}
