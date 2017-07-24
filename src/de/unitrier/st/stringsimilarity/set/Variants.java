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
 * All metrics should return a value between 0.0 and 1.0.
 */
public class Variants {

    static NGramDistance ngramDistance = new NGramDistance();


    // TODO : write a function for this OR change the function tokens() that it returns a set OR keep this?
    private static Set<String> listToSet(List<String> stringList){
        return new HashSet<>(stringList);// list to set : // http://www.baeldung.com/convert-list-to-set-and-set-to-list
    }

    // ********** JACCARD **********

    // tokens
    public static double tokenJaccard(String str1, String str2) {
        Set<String> nGramSet1 = listToSet(tokens(str1));
        Set<String> nGramSet2 = listToSet(tokens(str2));

        return jaccard(nGramSet1, nGramSet2);
    }

    public static double tokenJaccardNormalized(String str1, String str2) {
        Set<String> nGramSet1 = nGramSet(normalizeForEdit(str1));
        Set<String> nGramSet2 = nGramSet(normalizeForEdit(str2));

        return jaccard(nGramSet1, nGramSet2);
    }

    // nGrams
    public static double nGramJaccard(String str1, String str2, int n) {
        Set<String> nGramSet1 = nGramSet( str1, n);
        Set<String> nGramSet2 = nGramSet(str2, n);

        return jaccard(nGramSet1, nGramSet2);
    }

    public static double nGramJaccard(String str1, String str2) {
        return nGramJaccard(str1, str2, NGRAM_SIZE);
    }

    public static double nGramJaccardNormalized(String str1, String str2, int n) {
        Set<String> nGramSet1 = nGramSet(normalizeForNGram(str1), n);
        Set<String> nGramSet2 = nGramSet(normalizeForNGram(str2), n);

        return jaccard(nGramSet1, nGramSet2);
    }

    public static double nGramJaccardNormalized(String str1, String str2) {
        return nGramJaccardNormalized(str1, str2, NGRAM_SIZE);
    }

    public static double nGramJaccardNormalizedPadding(String str1, String str2, int n) {
        Set<String> nGramSet1 = nGramSet(normalizeForNGram(str1), n, true);
        Set<String> nGramSet2 = nGramSet(normalizeForNGram(str2), n, true);

        return jaccard(nGramSet1, nGramSet2);
    }

    public static double nGramJaccardNormalizedPadding(String str1, String str2) {
        return nGramJaccardNormalizedPadding(str1, str2, NGRAM_SIZE);
    }

    // shingles
    public static double shingleJaccard(String str1, String str2, int n) {
        Set<String> shingleSet1 = shingleSet(tokens(str1), DEFAULT_SEPARATOR, n);
        Set<String> shingleSet2 = shingleSet(tokens(str2), DEFAULT_SEPARATOR, n);

        return jaccard(shingleSet1, shingleSet2);
    }

    public static double shingleJaccard(String str1, String str2) {
        return shingleJaccard(str1, str2, SHINGLE_SIZE);
    }

    public static double shingleJaccardNormalized(String str1, String str2, int n) {
        Set<String> shingleSet1 = shingleSet(tokens(normalizeForShingle(str1)), DEFAULT_SEPARATOR, n);
        Set<String> shingleSet2 = shingleSet(tokens(normalizeForShingle(str2)), DEFAULT_SEPARATOR, n);

        return jaccard(shingleSet1, shingleSet2);
    }

    public static double shingleJaccardNormalized(String str1, String str2) {
        return shingleJaccardNormalized(str1, str2, SHINGLE_SIZE);
    }


    // ********** DICE **********

    // tokens
    public static double tokenDice(String str1, String str2){
        Set<String> nGramSet1 = listToSet(tokens(str1));
        Set<String> nGramSet2 = listToSet(tokens(str2));

        return dice(nGramSet1, nGramSet2);
    }

    public static double tokenDiceVariant(String str1, String str2){
        Set<String> nGramSet1 = listToSet(tokens(str1));
        Set<String> nGramSet2 = listToSet(tokens(str2));

        return diceVariant(nGramSet1, nGramSet2);
    }

    public static double tokenDiceNormalized(String str1, String str2){
        Set<String> nGramSet1 = listToSet(tokens(normalizeForEdit(str1)));
        Set<String> nGramSet2 = listToSet(tokens(normalizeForEdit(str2)));

        return dice(nGramSet1, nGramSet2);
    }

    public static double tokenDiceVariantNormalized(String str1, String str2){
        Set<String> nGramSet1 = listToSet(tokens(normalizeForEdit(str1)));
        Set<String> nGramSet2 = listToSet(tokens(normalizeForEdit(str2)));

        return diceVariant(nGramSet1, nGramSet2);
    }

    // nGrams
    public static double nGramDice(String str1, String str2, int n){
        Set<String> nGramSet1 = nGramSet(str1, n);
        Set<String> nGramSet2 = nGramSet(str2, n);

        return dice(nGramSet1, nGramSet2);
    }

    public static double nGramDiceVariant(String str1, String str2, int n){
        Set<String> nGramSet1 = nGramSet(str1, n);
        Set<String> nGramSet2 = nGramSet(str2, n);

        return diceVariant(nGramSet1, nGramSet2);
    }

    public static double nGramDice(String str1, String str2) {
        return nGramDice(str1, str2, NGRAM_SIZE);
    }

    public static double nGramDiceNormalized(String str1, String str2, int n){
        Set<String> nGramSet1 = nGramSet(normalizeForNGram(str1), n);
        Set<String> nGramSet2 = nGramSet(normalizeForNGram(str2), n);

        return dice(nGramSet1, nGramSet2);
    }

    public static double nGramDiceVariantNormalized(String str1, String str2, int n){
        Set<String> nGramSet1 = nGramSet(normalizeForNGram(str1), n);
        Set<String> nGramSet2 = nGramSet(normalizeForNGram(str2), n);

        return diceVariant(nGramSet1, nGramSet2);
    }

    public static double nGramDiceNormalized(String str1, String str2) {
        return nGramDiceNormalized(str1, str2, NGRAM_SIZE);
    }

    public static double nGramDiceNormalizedPadding(String str1, String str2, int n){
        Set<String> nGramSet1 = nGramSet(normalizeForNGram(str1), n, true);
        Set<String> nGramSet2 = nGramSet(normalizeForNGram(str2), n, true);

        return dice(nGramSet1, nGramSet2);
    }

    public static double nGramDiceVariantNormalizedPadding(String str1, String str2, int n){
        Set<String> nGramSet1 = nGramSet(normalizeForNGram(str1), n, true);
        Set<String> nGramSet2 = nGramSet(normalizeForNGram(str2), n, true);

        return diceVariant(nGramSet1, nGramSet2);
    }

    public static double nGramDiceNormalizedPadding(String str1, String str2) {
        return nGramDiceNormalizedPadding(str1, str2, NGRAM_SIZE);
    }

    public static double biGramDice(String str1, String str2) {
        return nGramDice(str1, str2, 2);
    }

    // shingles
    public static double shingleDice(String str1, String str2, int n) {
        Set<String> shingleSet1 = shingleSet(tokens(str1), DEFAULT_SEPARATOR, n);
        Set<String> shingleSet2 = shingleSet(tokens(str2), DEFAULT_SEPARATOR, n);

        return dice(shingleSet1, shingleSet2);
    }

    public static double shingleDiceVariant(String str1, String str2, int n) {
        Set<String> shingleSet1 = shingleSet(tokens(str1), DEFAULT_SEPARATOR, n);
        Set<String> shingleSet2 = shingleSet(tokens(str2), DEFAULT_SEPARATOR, n);

        return diceVariant(shingleSet1, shingleSet2);
    }

    public static double shingleDice(String str1, String str2) {
        return shingleDice(str1, str2, SHINGLE_SIZE);
    }

    public static double shingleDiceNormalized(String str1, String str2, int n) {
        Set<String> shingleSet1 = shingleSet(tokens(normalizeForShingle(str1)), DEFAULT_SEPARATOR, n);
        Set<String> shingleSet2 = shingleSet(tokens(normalizeForShingle(str2)), DEFAULT_SEPARATOR, n);

        return dice(shingleSet1, shingleSet2);
    }

    public static double shingleDiceVariantNormalized(String str1, String str2, int n) {
        Set<String> shingleSet1 = shingleSet(tokens(normalizeForShingle(str1)), DEFAULT_SEPARATOR, n);
        Set<String> shingleSet2 = shingleSet(tokens(normalizeForShingle(str2)), DEFAULT_SEPARATOR, n);

        return diceVariant(shingleSet1, shingleSet2);
    }

    public static double shingleDiceNormalized(String str1, String str2) {
        return shingleDiceNormalized(str1, str2, SHINGLE_SIZE);
    }


    // ********** OVERLAP **********

    // tokens
    public static double tokenOverlap(String str1, String str2){
        Set<String> nGramSet1 = listToSet(tokens(str1));
        Set<String> nGramSet2 = listToSet(tokens(str2));

        return overlap(nGramSet1, nGramSet2);
    }

    public static double tokenOverlapNormalized(String str1, String str2){
        Set<String> nGramSet1 = nGramSet(normalizeForEdit(str1));
        Set<String> nGramSet2 = nGramSet(normalizeForEdit(str2));

        return overlap(nGramSet1, nGramSet2);
    }


    // nGrams
    public static double nGramOverlap(String str1, String str2, int n){
        Set<String> nGramSet1 = nGramSet(str1, n);
        Set<String> nGramSet2 = nGramSet(str2, n);

        return overlap(nGramSet1, nGramSet2);
    }

    public static double nGramOverlap(String str1, String str2) {
        return nGramOverlap(str1, str2, NGRAM_SIZE);
    }

    public static double nGramOverlapNormalized(String str1, String str2, int n){
        Set<String> nGramSet1 = nGramSet(normalizeForNGram(str1), n);
        Set<String> nGramSet2 = nGramSet(normalizeForNGram(str2), n);

        return overlap(nGramSet1, nGramSet2);
    }

    public static double nGramOverlapNormalized(String str1, String str2) {
        return nGramOverlapNormalized(str1, str2, NGRAM_SIZE);
    }

    public static double nGramOverlapNormalizedPadding(String str1, String str2, int n){
        Set<String> nGramSet1 = nGramSet(normalizeForNGram(str1), n, true);
        Set<String> nGramSet2 = nGramSet(normalizeForNGram(str2), n, true);

        return overlap(nGramSet1, nGramSet2);
    }

    public static double nGramOverlapNormalizedPadding(String str1, String str2) {
        return nGramOverlapNormalizedPadding(str1, str2, NGRAM_SIZE);
    }

    public static double quatGramOverlap(String str1, String str2) {
        return nGramOverlap(str1, str2, 4);
    }

    // shingles
    public static double shingleOverlap(String str1, String str2, int n) {
        Set<String> shingleSet1 = shingleSet(tokens(str1), DEFAULT_SEPARATOR, n);
        Set<String> shingleSet2 = shingleSet(tokens(str2), DEFAULT_SEPARATOR, n);

        return overlap(shingleSet1, shingleSet2);
    }

    public static double shingleOverlap(String str1, String str2) {
        return shingleOverlap(str1, str2, SHINGLE_SIZE);
    }

    public static double shingleOverlapNormalized(String str1, String str2, int n) {
        Set<String> shingleSet1 = shingleSet(tokens(normalizeForShingle(str1)), DEFAULT_SEPARATOR, n);
        Set<String> shingleSet2 = shingleSet(tokens(normalizeForShingle(str2)), DEFAULT_SEPARATOR, n);

        return overlap(shingleSet1, shingleSet2);
    }

    public static double shingleOverlapNormalized(String str1, String str2) {
        return shingleOverlapNormalized(str1, str2, SHINGLE_SIZE);
    }

    public static double nGramSimilarityKondrak05(String str1, String str2){
        return ngramDistance.getDistance(str1, str2);
    }
}