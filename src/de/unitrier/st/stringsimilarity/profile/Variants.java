package de.unitrier.st.stringsimilarity.profile;

import com.google.common.collect.Multiset;

import static de.unitrier.st.stringsimilarity.Normalization.*;
import static de.unitrier.st.stringsimilarity.Tokenization.*;
import static de.unitrier.st.stringsimilarity.profile.Base.*;
import static de.unitrier.st.stringsimilarity.util.MultisetCollector.toMultiset;

/*
 * Different variants of profile-based similarity metrics.
 * All metrics should return a value between 0.0 and 1.0.
 */
public class Variants {

    // Remark: Always normalize here to reduce number of dimensions.

    // ********** Cosine **********

    // tokens
    public static double tokensProfileCosineNormalized(String str1, String str2, WeightingScheme weightingScheme) {
        Multiset<String> multiset1 = tokens(normalizeForEdit(str1))
                .stream()
                .collect(toMultiset());
        Multiset<String> multiset2 = tokens(normalizeForEdit(str2))
                .stream()
                .collect(toMultiset());

        return cosine(multiset1, multiset2, weightingScheme);
    }

    // nGrams
    public static double nGramProfileCosineNormalized(String str1, String str2, int nGramSize, WeightingScheme weightingScheme) {
        Multiset<String> nGramMultiset1 = nGramMultiset(normalizeForNGram(str1), nGramSize);
        Multiset<String> nGramMultiset2 = nGramMultiset(normalizeForNGram(str2), nGramSize);

        return cosine(nGramMultiset1, nGramMultiset2, weightingScheme);
    }

    public static double nGramProfileCosineNormalized(String str1, String str2, WeightingScheme weightingScheme) {
        return nGramProfileCosineNormalized(str1, str2, NGRAM_SIZE, weightingScheme);
    }

    // shingles
    public static double shingleProfileCosineNormalized(String str1, String str2, int shingleSize, WeightingScheme weightingScheme) {
        Multiset<String> shingleMultiset1 = shingleMultiset(tokens(normalizeForShingle(str1)), shingleSize);
        Multiset<String> shingleMultiset2 = shingleMultiset(tokens(normalizeForShingle(str2)), shingleSize);

        return cosine(shingleMultiset1, shingleMultiset2, weightingScheme);
    }

    public static double shingleProfileCosineNormalized(String str1, String str2, WeightingScheme weightingScheme) {
        return shingleProfileCosineNormalized(str1, str2, SHINGLE_SIZE, weightingScheme);
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
    public static double nGramManhattanNormalized(String str1, String str2, int nGramSize) {
        Multiset<String> nGramMultiset1 = nGramMultiset(normalizeForNGram(str1), nGramSize);
        Multiset<String> nGramMultiset2 = nGramMultiset(normalizeForNGram(str2), nGramSize);

        return manhattan(nGramMultiset1, nGramMultiset2);
    }

    public static double nGramManhattanNormalized(String str1, String str2) {
        return nGramManhattanNormalized(str1, str2, NGRAM_SIZE);
    }

    // shingles
    public static double shingleManhattanNormalized(String str1, String str2, int shingleSize) {
        Multiset<String> shingleMultiset1 = shingleMultiset(tokens(normalizeForShingle(str1)), shingleSize);
        Multiset<String> shingleMultiset2 = shingleMultiset(tokens(normalizeForShingle(str2)), shingleSize);

        return manhattan(shingleMultiset1, shingleMultiset2);
    }

    public static double shingleManhattanNormalized(String str1, String str2) {
        return shingleManhattanNormalized(str1, str2, SHINGLE_SIZE);
    }

}