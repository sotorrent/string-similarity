package de.unitrier.st.stringsimilarity.profile;

import static de.unitrier.st.stringsimilarity.Tokenization.*;
import static de.unitrier.st.stringsimilarity.profile.Base.*;

/*
 * Different variants of profile-based similarity metrics using default values.
 *
 * All metric variants must be a BiFunction<String, String, Double> and return a value between 0.0 and 1.0.
 */
public class Default {

    // Remark: Always normalize here to reduce number of dimensions.

    // ********** Cosine **********

    // tokens
    public static double cosineTokenNormalizedBool(String str1, String str2) {
        return cosineTokenNormalized(str1, str2, WeightingScheme.BOOL);
    }

    public static double cosineTokenNormalizedTermFrequency(String str1, String str2) {
        return cosineTokenNormalized(str1, str2, WeightingScheme.TERM_FREQUENCY);
    }

    public static double cosineTokenNormalizedNormalizedTermFrequency(String str1, String str2) {
        return cosineTokenNormalized(str1, str2, WeightingScheme.NORMALIZED_TERM_FREQUENCY);
    }

    // ngrams
    public static double cosineNGramNormalizedBool(String str1, String str2) {
        return cosineNGramNormalized(str1, str2, WeightingScheme.BOOL);
    }

    public static double cosineNGramNormalizedTermFrequency(String str1, String str2) {
        return cosineNGramNormalized(str1, str2, WeightingScheme.TERM_FREQUENCY);
    }

    public static double cosineNGramNormalizedNormalizedTermFrequency(String str1, String str2) {
        return cosineNGramNormalized(str1, str2, WeightingScheme.NORMALIZED_TERM_FREQUENCY);
    }

    // shingles
    public static double cosineNShingleNormalizedBool(String str1, String str2) {
        return cosineNShingleNormalized(str1, str2, WeightingScheme.BOOL);
    }

    public static double cosineNShingleNormalizedTermFrequency(String str1, String str2) {
        return cosineNShingleNormalized(str1, str2, WeightingScheme.TERM_FREQUENCY);
    }

    public static double cosineNShingleNormalizedNormalizedTermFrequency(String str1, String str2) {
        return cosineNShingleNormalized(str1, str2, WeightingScheme.NORMALIZED_TERM_FREQUENCY);
    }


    // ********** Manhattan / qGram **********

    // ngrams
    public static double manhattanNGramNormalized(String str1, String str2) {
        return Base.manhattanNGramNormalized(str1, str2, NGRAM_SIZE);
    }

    // shingles
    public static double manhattanNShingleNormalized(String str1, String str2) {
        return Base.manhattanNShingleNormalized(str1, str2, SHINGLE_SIZE);
    }

}