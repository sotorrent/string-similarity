package de.unitrier.st.stringsimilarity.set;

import com.google.common.collect.Sets;
import org.apache.lucene.search.spell.NGramDistance;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

import static de.unitrier.st.stringsimilarity.Normalization.*;
import static de.unitrier.st.stringsimilarity.Tokenization.*;

/*
 * Set-based similarity metrics.
 *
 * All base metrics must return a value between 0.0 and 1.0.
 */
public class Base {
    /**
     * Convert a list to a set.
     * This is needed, because tokens() return a list of tokens, but the set-based metrics need a set of tokens.
     * See also http://www.baeldung.com/convert-list-to-set-and-set-to-list
     *
     * @param stringList The list of strings that should be converted to a set of strings.
     * @return A set containing the strings from stringList.
     */
    private static Set<String> listToSet(List<String> stringList){
        return new HashSet<>(stringList);
    }


    // ********** JACCARD **********

    // https://en.wikipedia.org/wiki/Jaccard_index
    public static <T> double jaccard(Set<T> s1, Set<T> s2) {
        if (s1.size() == 0 && s2.size() == 0) {
            return 1.0;
        } else {
            return (double) Sets.intersection(s1, s2).size() / Sets.union(s1, s2).size();
        }
    }


    // ********** DICE **********

    // https://en.wikipedia.org/wiki/S%C3%B8rensen%E2%80%93Dice_coefficient
    public static <T> double dice(Set<T> s1, Set<T> s2) {
        if (s1.size() == 0 && s2.size() == 0) {
            return 1.0;
        } else {
            return (double) 2 * Sets.intersection(s1, s2).size() / (s1.size() + s2.size());
        }
    }

    // ********** OVERLAP **********

    // https://en.wikipedia.org/wiki/Overlap_coefficient
    public static <T> double overlap(Set<T> s1, Set<T> s2) {
        if (s1.size() == 0 && s2.size() == 0) {
            return 1.0;
        } else {
            return (double) Sets.intersection(s1, s2).size() / Math.min(s1.size(), s2.size());
        }
    }


    // ********** BASE VARIANTS **********

    // tokens
    static double tokenSimilarity(String str1, String str2,
                                         BiFunction<Set<String>, Set<String>, Double> coefficient) {
        Set<String> nGramSet1 = listToSet(tokens(str1));
        Set<String> nGramSet2 = listToSet(tokens(str2));

        return coefficient.apply(nGramSet1, nGramSet2);
    }

    // tokens + normalization
    static double tokenSimilarityNormalized(String str1, String str2,
                                                   BiFunction<Set<String>, Set<String>, Double> coefficient) {
        Set<String> nGramSet1 = nGramSet(normalizeForEdit(str1));
        Set<String> nGramSet2 = nGramSet(normalizeForEdit(str2));

        return coefficient.apply(nGramSet1, nGramSet2);
    }

    // ngrams
    static double nGramSimilarity(String str1, String str2, int nGramSize,
                                  BiFunction<Set<String>, Set<String>, Double> coefficient) {
        Set<String> nGramSet1 = nGramSet(str1, nGramSize);
        Set<String> nGramSet2 = nGramSet(str2, nGramSize);

        return coefficient.apply(nGramSet1, nGramSet2);
    }

    // ngrams + normalization
    static double nGramSimilarityNormalized(String str1, String str2, int nGramSize,
                                            BiFunction<Set<String>, Set<String>, Double> coefficient) {
        Set<String> nGramSet1 = nGramSet(normalizeForNGram(str1), nGramSize);
        Set<String> nGramSet2 = nGramSet(normalizeForNGram(str2), nGramSize);

        return coefficient.apply(nGramSet1, nGramSet2);
    }

    // ngrams + normalization + padding
    static double nGramSimilarityNormalizedPadding(String str1, String str2, int nGramSize,
                                                   BiFunction<Set<String>, Set<String>, Double> coefficient) {
        Set<String> nGramSet1 = nGramSet(normalizeForNGram(str1), nGramSize, true);
        Set<String> nGramSet2 = nGramSet(normalizeForNGram(str2), nGramSize, true);

        return coefficient.apply(nGramSet1, nGramSet2);
    }

    // shingles
    static double nShingleSimilarity(String str1, String str2, int shingleSize,
                                     BiFunction<Set<String>, Set<String>, Double> coefficient) {
        Set<String> shingleSet1 = shingleSet(tokens(str1), DEFAULT_SEPARATOR, shingleSize);
        Set<String> shingleSet2 = shingleSet(tokens(str2), DEFAULT_SEPARATOR, shingleSize);

        return coefficient.apply(shingleSet1, shingleSet2);
    }

    // shingles + normalization
    static double nShingleSimilarityNormalized(String str1, String str2, int shingleSize,
                                               BiFunction<Set<String>, Set<String>, Double> coefficient) {
        Set<String> shingleSet1 = shingleSet(tokens(normalizeForShingle(str1)), DEFAULT_SEPARATOR, shingleSize);
        Set<String> shingleSet2 = shingleSet(tokens(normalizeForShingle(str2)), DEFAULT_SEPARATOR, shingleSize);

        return coefficient.apply(shingleSet1, shingleSet2);
    }


    // ********** NGRAM SIMILARITY BASED ON KONDRAK05 **********

    // ngrams
    static double nGramSimilarityKondrak05(String str1, String str2, int nGramSize) {
        return new NGramDistance(nGramSize).getDistance(str1, str2);
    }
}
