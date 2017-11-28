package de.unitrier.st.stringsimilarity.equal;

/*
 * Different variants of equality-based similarity metrics.
 *
 * All metric variants must be a BiFunction<String, String, Double> and return a value between 0.0 and 1.0.
 */
public class Variants {

    // ********** STRING **********

    public static double equal(String str1, String str2) {
        return Base.equal(str1, str2);
    }

    public static double equalNormalized(String str1, String str2) {
        return Base.equalNormalized(str1, str2);
    }

    // ********** SET **********

    // tokens
    public static double tokenEqual(String str1, String str2) {
        return Default.tokenEqual(str1, str2);
    }

    // tokens + normalization
    public static double tokenEqualNormalized(String str1, String str2) {
        return Default.tokenEqualNormalized(str1, str2);
    }
}