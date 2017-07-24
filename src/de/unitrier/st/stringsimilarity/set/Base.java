package de.unitrier.st.stringsimilarity.set;

import com.google.common.collect.Sets;

import java.util.Set;

/*
 * Set-based similarity metrics.
 * All metrics should return a value between 0.0 and 1.0.
 */
public class Base {

    public static final double THRESHOLD = 0.6;

    // https://en.wikipedia.org/wiki/Jaccard_index
    public static <T> double jaccard(Set<T> s1, Set<T> s2) {
        if (s1.size() == 0 && s2.size() == 0) {
            return 1.0;
        } else {
            return (double) Sets.intersection(s1, s2).size() / Sets.union(s1, s2).size();
        }
    }

    // https://en.wikipedia.org/wiki/S%C3%B8rensen%E2%80%93Dice_coefficient
    public static <T> double dice(Set<T> s1, Set<T> s2) {
        if (s1.size() == 0 && s2.size() == 0) {
            return 1.0;
        } else {
            return (double) 2 * Sets.intersection(s1, s2).size() / (s1.size() + s2.size());
        }
    }

    // See in Duric13
    public static <T> double diceVariant(Set<T> s1, Set<T> s2) {
        if (s1.size() == 0 && s2.size() == 0) {
            return 1.0;
        } else {
            return (double) 2 * Sets.intersection(s1, s2).size() / (s1.size() < s2.size() ? s1.size() : s2.size());
        }
    }

    // https://en.wikipedia.org/wiki/Overlap_coefficient
    public static <T> double overlap(Set<T> s1, Set<T> s2) {
        if (s1.size() == 0 && s2.size() == 0) {
            return 1.0;
        } else {
            return (double) Sets.intersection(s1, s2).size() / Math.min(s1.size(), s2.size());
        }
    }
}
