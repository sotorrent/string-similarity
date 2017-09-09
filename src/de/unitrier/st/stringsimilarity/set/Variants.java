package de.unitrier.st.stringsimilarity.set;

import org.apache.lucene.search.spell.NGramDistance;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static de.unitrier.st.stringsimilarity.Normalization.*;
import static de.unitrier.st.stringsimilarity.Tokenization.*;
import static de.unitrier.st.stringsimilarity.set.Base.*;

/*
 * Different variants of set-based similarity metrics.
 *
 * All metric variants must be a BiFunction<String, String, Double> and return a value between 0.0 and 1.0.
 */
public class Variants {

    private static NGramDistance ngramDistance = new NGramDistance();

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

    // tokens
    public static double tokenJaccard(String str1, String str2) {
        Set<String> nGramSet1 = listToSet(tokens(str1));
        Set<String> nGramSet2 = listToSet(tokens(str2));

        return jaccard(nGramSet1, nGramSet2);
    }

    // tokens + normalization
    public static double tokenJaccardNormalized(String str1, String str2) {
        Set<String> nGramSet1 = nGramSet(normalizeForEdit(str1));
        Set<String> nGramSet2 = nGramSet(normalizeForEdit(str2));

        return jaccard(nGramSet1, nGramSet2);
    }

    // ngrams
    public static double nGramJaccard(String str1, String str2) {
        return Base.nGramJaccard(str1, str2, NGRAM_SIZE);
    }

    // quatgrams
    public static double quatGramJaccard(String str1, String str2) {
        return Base.nGramJaccard(str1, str2, 4);
    }

    // ngrams + normalization
    public static double nGramJaccardNormalized(String str1, String str2) {
        return Base.nGramJaccardNormalized(str1, str2, NGRAM_SIZE);
    }

    // ngrams + normalization + padding
    public static double nGramJaccardNormalizedPadding(String str1, String str2) {
        return Base.nGramJaccardNormalizedPadding(str1, str2, NGRAM_SIZE);
    }

    // shingles
    public static double shingleJaccard(String str1, String str2) {
        return Base.shingleJaccard(str1, str2, SHINGLE_SIZE);
    }

    // shingles + normalization
    public static double shingleJaccardNormalized(String str1, String str2) {
        return Base.shingleJaccardNormalized(str1, str2, SHINGLE_SIZE);
    }


    // ********** DICE **********

    // tokens
    public static double tokenDice(String str1, String str2){
        Set<String> nGramSet1 = listToSet(tokens(str1));
        Set<String> nGramSet2 = listToSet(tokens(str2));

        return dice(nGramSet1, nGramSet2);
    }

    // tokens + normalization
    public static double tokenDiceNormalized(String str1, String str2){
        Set<String> nGramSet1 = listToSet(tokens(normalizeForEdit(str1)));
        Set<String> nGramSet2 = listToSet(tokens(normalizeForEdit(str2)));

        return dice(nGramSet1, nGramSet2);
    }

    // ngrams
    public static double nGramDice(String str1, String str2) {
        return Base.nGramDice(str1, str2, NGRAM_SIZE);
    }

    // bigrams
    public static double biGramDice(String str1, String str2) {
        return Base.nGramDice(str1, str2, 2);
    }

    // ngrams + normalization
    public static double nGramDiceNormalized(String str1, String str2) {
        return Base.nGramDiceNormalized(str1, str2, NGRAM_SIZE);
    }

    // ngrams + normalization + padding
    public static double nGramDiceNormalizedPadding(String str1, String str2) {
        return Base.nGramDiceNormalizedPadding(str1, str2, NGRAM_SIZE);
    }

    // shingles
    public static double shingleDice(String str1, String str2) {
        return Base.shingleDice(str1, str2, SHINGLE_SIZE);
    }

    // shingles + normalization
    public static double shingleDiceNormalized(String str1, String str2) {
        return Base.shingleDiceNormalized(str1, str2, SHINGLE_SIZE);
    }


    // ********** DICE VARIANT *********

    // tokens
    public static double tokenDiceVariant(String str1, String str2){
        Set<String> nGramSet1 = listToSet(tokens(str1));
        Set<String> nGramSet2 = listToSet(tokens(str2));

        return diceVariant(nGramSet1, nGramSet2);
    }

    // tokens + normalization
    public static double tokenDiceVariantNormalized(String str1, String str2){
        Set<String> nGramSet1 = listToSet(tokens(normalizeForEdit(str1)));
        Set<String> nGramSet2 = listToSet(tokens(normalizeForEdit(str2)));

        return diceVariant(nGramSet1, nGramSet2);
    }

    // ngrams
    static double nGramDiceVariant(String str1, String str2){
        Set<String> nGramSet1 = nGramSet(str1, NGRAM_SIZE);
        Set<String> nGramSet2 = nGramSet(str2, NGRAM_SIZE);

        return diceVariant(nGramSet1, nGramSet2);
    }

    // ngrams + normalization
    public static double nGramDiceVariantNormalized(String str1, String str2){
        Set<String> nGramSet1 = nGramSet(normalizeForNGram(str1), NGRAM_SIZE);
        Set<String> nGramSet2 = nGramSet(normalizeForNGram(str2), NGRAM_SIZE);

        return diceVariant(nGramSet1, nGramSet2);
    }

    // ngrams + normalization + padding
    public static double nGramDiceVariantNormalizedPadding(String str1, String str2, int n){
        Set<String> nGramSet1 = nGramSet(normalizeForNGram(str1), n, true);
        Set<String> nGramSet2 = nGramSet(normalizeForNGram(str2), n, true);

        return diceVariant(nGramSet1, nGramSet2);
    }


    // ********** OVERLAP **********

    // tokens
    public static double tokenOverlap(String str1, String str2){
        Set<String> nGramSet1 = listToSet(tokens(str1));
        Set<String> nGramSet2 = listToSet(tokens(str2));

        return overlap(nGramSet1, nGramSet2);
    }

    // tokens + normalization
    public static double tokenOverlapNormalized(String str1, String str2){
        Set<String> nGramSet1 = nGramSet(normalizeForEdit(str1));
        Set<String> nGramSet2 = nGramSet(normalizeForEdit(str2));

        return overlap(nGramSet1, nGramSet2);
    }

    // ngrams
    public static double nGramOverlap(String str1, String str2) {
        return Base.nGramOverlap(str1, str2, NGRAM_SIZE);
    }

    // quatgrams
    public static double quatGramOverlap(String str1, String str2) {
        return Base.nGramOverlap(str1, str2, 4);
    }

    // ngrams + normalization
    public static double nGramOverlapNormalized(String str1, String str2) {
        return Base.nGramOverlapNormalized(str1, str2, NGRAM_SIZE);
    }

    // ngrams + normalization + padding
    public static double nGramOverlapNormalizedPadding(String str1, String str2) {
        return Base.nGramOverlapNormalizedPadding(str1, str2, NGRAM_SIZE);
    }

    // shingles
    public static double shingleOverlap(String str1, String str2) {
        return Base.shingleOverlap(str1, str2, SHINGLE_SIZE);
    }

    // shingles + normalization
    public static double shingleOverlapNormalized(String str1, String str2) {
        return Base.shingleOverlapNormalized(str1, str2, SHINGLE_SIZE);
    }

    // ********** NGRAM SIMILARITY BASED ON KONDRAK05 **********

    // ngrams
    public static double nGramSimilarityKondrak05(String str1, String str2){
        return ngramDistance.getDistance(str1, str2);
    }
}