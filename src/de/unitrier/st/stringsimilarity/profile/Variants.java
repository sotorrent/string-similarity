package de.unitrier.st.stringsimilarity.profile;

import com.google.common.collect.Multiset;

import static de.unitrier.st.stringsimilarity.Normalization.*;
import static de.unitrier.st.stringsimilarity.Tokenization.*;
import static de.unitrier.st.stringsimilarity.profile.Base.*;
import static de.unitrier.st.stringsimilarity.util.MultisetCollector.toMultiset;

/*
 * Different variants of profile-based similarity metrics.
 *
 * All metric variants must be a BiFunction<String, String, Double> and return a value between 0.0 and 1.0.
 */
public class Variants {

    // Remark: Always normalize here to reduce number of dimensions.

    // ********** Cosine **********

    // tokens
    public static double tokensProfileCosineNormalizedTermFrequency(String str1, String str2) {
        return tokensProfileCosineNormalized(str1, str2, WeightingScheme.TERM_FREQUENCY);
    }

    public static double tokensProfileCosineNormalizedNormalizedTermFrequency(String str1, String str2) {
        return tokensProfileCosineNormalized(str1, str2, WeightingScheme.NORMALIZED_TERM_FREQUENCY);
    }

    public static double tokensProfileCosineNormalizedBool(String str1, String str2) {
        return tokensProfileCosineNormalized(str1, str2, WeightingScheme.BOOL);
    }

    // nGrams
    public static double nGramProfileCosineNormalizedTermFrequency(String str1, String str2) {
        return nGramProfileCosineNormalized(str1, str2, WeightingScheme.TERM_FREQUENCY);
    }

    public static double nGramProfileCosineNormalizedNormalizedTermFrequency(String str1, String str2) {
        return nGramProfileCosineNormalized(str1, str2, WeightingScheme.NORMALIZED_TERM_FREQUENCY);
    }

    public static double nGramProfileCosineNormalizedBool(String str1, String str2) {
        return nGramProfileCosineNormalized(str1, str2, WeightingScheme.BOOL);
    }

    // shingles
    public static double shingleProfileCosineNormalizedTermFrequency(String str1, String str2) {
        return shingleProfileCosineNormalized(str1, str2, WeightingScheme.TERM_FREQUENCY);
    }

    public static double shingleProfileCosineNormalizedNormalizedTermFrequency(String str1, String str2) {
        return shingleProfileCosineNormalized(str1, str2, WeightingScheme.NORMALIZED_TERM_FREQUENCY);
    }

    public static double shingleProfileCosineNormalizedBool(String str1, String str2) {
        return shingleProfileCosineNormalized(str1, str2, WeightingScheme.BOOL);
    }


    // ********** Manhattan / qGram **********

    // tokens
    public static double tokenManhattanNormalized(String str1, String str2) {
        Multiset<String> multiset1 = tokens(normalizeForEdit(str1))
                .stream()
                .collect(toMultiset());
        Multiset<String> multiset2 = tokens(normalizeForEdit(str2))
                .stream()
                .collect(toMultiset());

        return manhattan(multiset1, multiset2);
    }

    // nGrams
    public static double nGramManhattanNormalized(String str1, String str2) {
        return Base.nGramManhattanNormalized(str1, str2, NGRAM_SIZE);
    }

    // shingles
    public static double shingleManhattanNormalized(String str1, String str2) {
        return Base.shingleManhattanNormalized(str1, str2, SHINGLE_SIZE);
    }

}