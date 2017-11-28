package de.unitrier.st.stringsimilarity.equal;

import static de.unitrier.st.stringsimilarity.set.Base.tokenSimilarity;
import static de.unitrier.st.stringsimilarity.set.Base.tokenSimilarityNormalized;

/*
 * Different variants of equality-based similarity metrics using default values.
 *
 * All metric variants must be a BiFunction<String, String, Double> and return a value between 0.0 and 1.0.
 */
public class Default {

    // ********** SET ***********

    // tokens
    public static double tokenEqual(String str1, String str2) {
        return tokenSimilarity(str1, str2, Base::equal);
    }

    // tokens + normalization
    public static double tokenEqualNormalized(String str1, String str2) {
        return tokenSimilarityNormalized(str1, str2, Base::equal);
    }
}