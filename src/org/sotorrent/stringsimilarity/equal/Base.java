package org.sotorrent.stringsimilarity.equal;

import java.util.Set;

import static org.sotorrent.stringsimilarity.Normalization.normalizeForEdit;

/*
 * Equality-based similarity metrics.
 *
 * All base metrics must return a value between 0.0 and 1.0.
 */
public class Base {
    // ********** STRING **********

    /*
     * Similarity metric based on String equality.
     */
    static double equal(String str1, String str2) {
        return str1.equals(str2) ? 1.0 : 0.0;
    }

    /*
     * Similarity metric based on String equality and normalization.
     */
    static double equalNormalized(String str1, String str2) {
        return normalizeForEdit(str1).equals(normalizeForEdit(str2)) ? 1.0 : 0.0;
    }

    // ********** SET **********

    public static <T> double equal(Set<T> s1, Set<T> s2) {
        if (s1.size() == 0 && s2.size() == 0) {
            return 1.0;
        } else {
            return s1.equals(s2) ? 1.0 : 0.0;
        }
    }

}