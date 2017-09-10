package de.unitrier.st.stringsimilarity.profile;

import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static de.unitrier.st.stringsimilarity.Normalization.normalizeForEdit;
import static de.unitrier.st.stringsimilarity.Normalization.normalizeForNGram;
import static de.unitrier.st.stringsimilarity.Normalization.normalizeForShingle;
import static de.unitrier.st.stringsimilarity.Tokenization.*;
import static de.unitrier.st.stringsimilarity.Tokenization.SHINGLE_SIZE;
import static de.unitrier.st.stringsimilarity.Tokenization.shingleMultiset;
import static de.unitrier.st.stringsimilarity.util.MultisetCollector.toMultiset;

/*
 * Profile-based similarity metrics.
 *
 * All base metrics must return a value between 0.0 and 1.0.
 */
public class Base {

    public static final double THRESHOLD = 0.6; // TODO: set after evaluation

    public enum WeightingScheme{TERM_FREQUENCY, NORMALIZED_TERM_FREQUENCY, BOOL}
    private final static double k = 1.5; // for term frequency with normalization // TODO: according to this source, k should be between 1.2 and 2 (source: https://en.wikipedia.org/wiki/Okapi_BM25)

    /*
     * Cosine similarity.
     * See: https://en.wikipedia.org/wiki/Cosine_similarity
     */
    static <T> double cosine(Multiset<T> s1, Multiset<T> s2, WeightingScheme weightingScheme) {
        Map<T, Integer> profile1 = getProfile(s1);
        Map<T, Integer> profile2 = getProfile(s2);

        List<T> terms = getTerms(profile1, profile2);

        // vectors for the two profiles
        double[] v1 = new double[terms.size()];
        double[] v2 = new double[terms.size()];
        // euclidean norm of the two vectors
        double norm1 = 0;
        double norm2 = 0;
        // scalar product of the two vectors
        double scalarProduct = 0;
        // test if v1==v2 to deal with rounding errors (sim should be 1.0)
        boolean equal = true;

        for(int i=0; i<terms.size(); i++){
            int tf_t_0 = profile1.get(terms.get(i)) != null ? profile1.get(terms.get(i)) : 0;
            int tf_t_1 = profile2.get(terms.get(i)) != null ? profile2.get(terms.get(i)) : 0;

            switch(weightingScheme){
                case TERM_FREQUENCY:
                    v1[i] = tf_t_0;
                    v2[i] = tf_t_1;
                    break;

                // https://en.wikipedia.org/wiki/Okapi_BM25
                case NORMALIZED_TERM_FREQUENCY:
                    v1[i] = (1+k)*tf_t_0 / (tf_t_0 + k);
                    v2[i] = (1+k)*tf_t_1 / (tf_t_1 + k);
                    break;

                case BOOL:
                    v1[i] = (tf_t_0 != 0) ? 1 : 0;
                    v2[i] = (tf_t_1 != 0) ? 1 : 0;
                    break;
            }

            equal = equal && (tf_t_0 == tf_t_1);
            norm1 += Math.pow(v1[i], 2);
            norm2 += Math.pow(v2[i], 2);
            scalarProduct += v1[i] * v2[i]; // 0.0 if v1 and v2 are orthogonal
        }

        norm1 = Math.sqrt(norm1);
        norm2 = Math.sqrt(norm2);

        return equal ? 1.0 : scalarProduct / (norm1 * norm2);
    }

    private static <T> Map<T, Integer> getProfile(Multiset<T> s) {
        return s.entrySet()
                .stream()
                .collect(Collectors.toMap(Multiset.Entry::getElement, Multiset.Entry::getCount));
    }

    private static <T> List<T> getTerms(Map<T, Integer> profile1, Map<T, Integer> profile2) {
        return new ArrayList<>(Sets.union(profile1.keySet(), profile2.keySet()));
    }

    /*
     * Base variants of Cosine similarity.
     */

    // tokens
    static double cosineTokenNormalized(String str1, String str2, WeightingScheme weightingScheme) {
        Multiset<String> multiset1 = tokens(normalizeForEdit(str1))
                .stream()
                .collect(toMultiset());
        Multiset<String> multiset2 = tokens(normalizeForEdit(str2))
                .stream()
                .collect(toMultiset());

        return cosine(multiset1, multiset2, weightingScheme);
    }

    // ngrams
    static double cosineNGramNormalized(String str1, String str2, int nGramSize, WeightingScheme weightingScheme) {
        Multiset<String> nGramMultiset1 = nGramMultiset(normalizeForNGram(str1), nGramSize);
        Multiset<String> nGramMultiset2 = nGramMultiset(normalizeForNGram(str2), nGramSize);

        return cosine(nGramMultiset1, nGramMultiset2, weightingScheme);
    }

    static double cosineNGramNormalized(String str1, String str2, WeightingScheme weightingScheme) {
        return cosineNGramNormalized(str1, str2, NGRAM_SIZE, weightingScheme);
    }

    // shingles
    static double cosineNShingleNormalized(String str1, String str2, int shingleSize, WeightingScheme weightingScheme) {
        Multiset<String> shingleMultiset1 = shingleMultiset(tokens(normalizeForShingle(str1)), shingleSize);
        Multiset<String> shingleMultiset2 = shingleMultiset(tokens(normalizeForShingle(str2)), shingleSize);

        return cosine(shingleMultiset1, shingleMultiset2, weightingScheme);
    }

    static double cosineNShingleNormalized(String str1, String str2, WeightingScheme weightingScheme) {
        return cosineNShingleNormalized(str1, str2, SHINGLE_SIZE, weightingScheme);
    }

    /*
     * Similarity based on the Manhattan distance between two vectors.
     * Faster than edit-based distances like Levenshtein (time in O(n+m) instead of O(n*m)).
     * See "qGram distance" in paper Ukkonen92.
     * See: https://github.com/tdebatty/java-string-similarity#q-gram
     * See: https://en.wikipedia.org/wiki/Taxicab_geometry
     */
    static <T> double manhattan(Multiset<T> s1, Multiset<T> s2) {
        Map<T, Integer> profile1 = getProfile(s1);
        Map<T, Integer> profile2 = getProfile(s2);

        List<T> terms = getTerms(profile1, profile2);

        // manhattan norm of vector for profile 1
        int norm1 = 0;
        // manhattan norm of vector for profile 2
        int norm2 = 0;
        // manhattan distance between the two vectors
        int dist = 0;
        for (T term : terms) {
            int tf_1 = profile1.get(term) != null ? profile1.get(term) : 0;
            int tf_2 = profile2.get(term) != null ? profile2.get(term) : 0;
            norm1 += Math.abs(tf_1);
            norm2 += Math.abs(tf_2);
            dist += Math.abs(tf_1 - tf_2);
        }

        return 1.0-(double)dist/(norm1+norm2);
    }

    /*
     * Base variants of Manhattan similarity.
     */

    // tokens
    static double manhattanTokenNormalized(String str1, String str2) {
        Multiset<String> multiset1 = tokens(normalizeForEdit(str1))
                .stream()
                .collect(toMultiset());
        Multiset<String> multiset2 = tokens(normalizeForEdit(str2))
                .stream()
                .collect(toMultiset());

        return manhattan(multiset1, multiset2);
    }

    // ngrams
    static double manhattanNGramNormalized(String str1, String str2, int nGramSize) {
        Multiset<String> nGramMultiset1 = nGramMultiset(normalizeForNGram(str1), nGramSize);
        Multiset<String> nGramMultiset2 = nGramMultiset(normalizeForNGram(str2), nGramSize);

        return manhattan(nGramMultiset1, nGramMultiset2);
    }

    // shingles
    static double manhattanNShingleNormalized(String str1, String str2, int shingleSize) {
        Multiset<String> shingleMultiset1 = shingleMultiset(tokens(normalizeForShingle(str1)), shingleSize);
        Multiset<String> shingleMultiset2 = shingleMultiset(tokens(normalizeForShingle(str2)), shingleSize);

        return manhattan(shingleMultiset1, shingleMultiset2);
    }
}
