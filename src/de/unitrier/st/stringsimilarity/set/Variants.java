package de.unitrier.st.stringsimilarity.set;

import static de.unitrier.st.stringsimilarity.set.Base.*;

/*
 * Different variants of set-based similarity metrics.
 *
 * All metric variants must be a BiFunction<String, String, Double> and return a value between 0.0 and 1.0.
 */
public class Variants {

    // ********** JACCARD **********

    // tokens
    public static double tokenJaccard(String str1, String str2) {
        return Default.tokenJaccard(str1, str2);
    }

    // tokens + normalization
    public static double tokenJaccardNormalized(String str1, String str2) {
        return Default.tokenJaccardNormalized(str1, str2);
    }

    // ngrams
    public static double twoGramJaccard(String str1, String str2) {
        return Base.nGramSimilarity(str1, str2, 2, Base::jaccard);
    }

    public static double threeGramJaccard(String str1, String str2) {
        return Base.nGramSimilarity(str1, str2, 3, Base::jaccard);
    }

    public static double fourGramJaccard(String str1, String str2) {
        return Base.nGramSimilarity(str1, str2, 4, Base::jaccard);
    }

    public static double fiveGramJaccard(String str1, String str2) {
        return Base.nGramSimilarity(str1, str2, 5, Base::jaccard);
    }

    // ngrams + normalization
    public static double twoGramJaccardNormalized(String str1, String str2) {
        return Base.nGramSimilarityNormalized(str1, str2, 2, Base::jaccard);
    }

    public static double threeGramJaccardNormalized(String str1, String str2) {
        return Base.nGramSimilarityNormalized(str1, str2, 3, Base::jaccard);
    }

    public static double fourGramJaccardNormalized(String str1, String str2) {
        return Base.nGramSimilarityNormalized(str1, str2, 4, Base::jaccard);
    }

    public static double fiveGramJaccardNormalized(String str1, String str2) {
        return Base.nGramSimilarityNormalized(str1, str2, 5, Base::jaccard);
    }

    // ngrams + normalization + padding
    public static double twoGramJaccardNormalizedPadding(String str1, String str2) {
        return Base.nGramSimilarityNormalizedPadding(str1, str2, 2, Base::jaccard);
    }

    public static double threeGramJaccardNormalizedPadding(String str1, String str2) {
        return Base.nGramSimilarityNormalizedPadding(str1, str2, 3, Base::jaccard);
    }

    public static double fourGramJaccardNormalizedPadding(String str1, String str2) {
        return Base.nGramSimilarityNormalizedPadding(str1, str2, 4, Base::jaccard);
    }

    public static double fiveGramJaccardNormalizedPadding(String str1, String str2) {
        return Base.nGramSimilarityNormalizedPadding(str1, str2, 5, Base::jaccard);
    }

    // shingles
    public static double twoShingleJaccard(String str1, String str2) {
        return shingleSimilarity(str1, str2, 2, Base::jaccard);
    }

    public static double threeShingleJaccard(String str1, String str2) {
        return shingleSimilarity(str1, str2, 3, Base::jaccard);
    }

    // shingles + normalization
    public static double twoShingleJaccardNormalized(String str1, String str2) {
        return shingleSimilarityNormalized(str1, str2, 2, Base::jaccard);
    }

    public static double threeShingleJaccardNormalized(String str1, String str2) {
        return shingleSimilarityNormalized(str1, str2, 3, Base::jaccard);
    }


    // ********** DICE **********

    // tokens
    public static double tokenDice(String str1, String str2) {
        return Default.tokenDice(str1, str2);
    }

    // tokens + normalization
    public static double tokenDiceNormalized(String str1, String str2) {
        return Default.tokenDiceNormalized(str1, str2);
    }

    // ngrams
    public static double twoGramDice(String str1, String str2) {
        return nGramSimilarity(str1, str2, 2, Base::dice);
    }

    public static double threeGramDice(String str1, String str2) {
        return nGramSimilarity(str1, str2, 3, Base::dice);
    }

    public static double fourGramDice(String str1, String str2) {
        return nGramSimilarity(str1, str2, 4, Base::dice);
    }

    public static double fiveGramDice(String str1, String str2) {
        return nGramSimilarity(str1, str2, 5, Base::dice);
    }

    // ngrams + normalization
    public static double twoGramDiceNormalized(String str1, String str2) {
        return nGramSimilarityNormalized(str1, str2, 2, Base::dice);
    }

    public static double threeGramDiceNormalized(String str1, String str2) {
        return nGramSimilarityNormalized(str1, str2, 3, Base::dice);
    }

    public static double fourGramDiceNormalized(String str1, String str2) {
        return nGramSimilarityNormalized(str1, str2, 4, Base::dice);
    }

    public static double fiveGramDiceNormalized(String str1, String str2) {
        return nGramSimilarityNormalized(str1, str2, 5, Base::dice);
    }

    // ngrams + normalization + padding
    public static double twoGramDiceNormalizedPadding(String str1, String str2) {
        return nGramSimilarityNormalizedPadding(str1, str2, 2, Base::dice);
    }

    public static double threeGramDiceNormalizedPadding(String str1, String str2) {
        return nGramSimilarityNormalizedPadding(str1, str2, 3, Base::dice);
    }

    public static double fourGramDiceNormalizedPadding(String str1, String str2) {
        return nGramSimilarityNormalizedPadding(str1, str2, 4, Base::dice);
    }

    public static double fiveGramDiceNormalizedPadding(String str1, String str2) {
        return nGramSimilarityNormalizedPadding(str1, str2, 5, Base::dice);
    }

    // shingles
    public static double twoShingleDice(String str1, String str2) {
        return shingleSimilarity(str1, str2, 2, Base::dice);
    }

    public static double threeShingleDice(String str1, String str2) {
        return shingleSimilarity(str1, str2, 3, Base::dice);
    }

    // shingles + normalization
    public static double twoShingleDiceNormalized(String str1, String str2) {
        return shingleSimilarityNormalized(str1, str2, 2, Base::dice);
    }

    public static double threeShingleDiceNormalized(String str1, String str2) {
        return shingleSimilarityNormalized(str1, str2, 3, Base::dice);
    }


    // ********** DICE VARIANT *********

    // tokens
    public static double tokenDiceVariant(String str1, String str2) {
        return Default.tokenDiceVariant(str1, str2);
    }

    // tokens + normalization
    public static double tokenDiceVariantNormalized(String str1, String str2) {
        return Default.tokenDiceVariantNormalized(str1, str2);
    }

    // ngrams
    public static double twoGramDiceVariant(String str1, String str2) {
        return nGramSimilarity(str1, str2, 2, Base::diceVariant);
    }

    public static double threeGramDiceVariant(String str1, String str2) {
        return nGramSimilarity(str1, str2, 3, Base::diceVariant);
    }

    public static double fourGramDiceVariant(String str1, String str2) {
        return nGramSimilarity(str1, str2, 4, Base::diceVariant);
    }

    public static double fiveGramDiceVariant(String str1, String str2) {
        return nGramSimilarity(str1, str2, 5, Base::diceVariant);
    }

    // ngrams + normalization
    public static double twoGramDiceVariantNormalized(String str1, String str2) {
        return nGramSimilarityNormalized(str1, str2, 2, Base::diceVariant);
    }

    public static double threeGramDiceVariantNormalized(String str1, String str2) {
        return nGramSimilarityNormalized(str1, str2, 3, Base::diceVariant);
    }

    public static double fourGramDiceVariantNormalized(String str1, String str2) {
        return nGramSimilarityNormalized(str1, str2, 4, Base::diceVariant);
    }

    public static double fiveGramDiceVariantNormalized(String str1, String str2) {
        return nGramSimilarityNormalized(str1, str2, 5, Base::diceVariant);
    }

    // ngrams + normalization + padding
    public static double twoGramDiceVariantNormalizedPadding(String str1, String str2) {
        return nGramSimilarityNormalizedPadding(str1, str2, 2, Base::diceVariant);
    }

    public static double threeGramDiceVariantNormalizedPadding(String str1, String str2) {
        return nGramSimilarityNormalizedPadding(str1, str2, 3, Base::diceVariant);
    }

    public static double fourGramDiceVariantNormalizedPadding(String str1, String str2) {
        return nGramSimilarityNormalizedPadding(str1, str2, 4, Base::diceVariant);
    }

    public static double fiveGramDiceVariantNormalizedPadding(String str1, String str2) {
        return nGramSimilarityNormalizedPadding(str1, str2, 5, Base::diceVariant);
    }

    // shingles
    public static double twoShingleDiceVariant(String str1, String str2) {
        return shingleSimilarity(str1, str2, 2, Base::diceVariant);
    }

    public static double threeShingleDiceVariant(String str1, String str2) {
        return shingleSimilarity(str1, str2, 3, Base::diceVariant);
    }

    // shingles + normalization
    public static double twoShingleDiceVariantNormalized(String str1, String str2) {
        return shingleSimilarityNormalized(str1, str2, 2, Base::diceVariant);
    }

    public static double threeShingleDiceVariantNormalized(String str1, String str2) {
        return shingleSimilarityNormalized(str1, str2, 3, Base::diceVariant);
    }


    // ********** OVERLAP **********

    // tokens
    public static double tokenOverlap(String str1, String str2) {
        return Default.tokenOverlap(str1, str2);
    }

    // tokens + normalization
    public static double tokenOverlapNormalized(String str1, String str2) {
        return Default.tokenOverlapNormalized(str1, str2);
    }

    // ngrams
    public static double twoGramOverlap(String str1, String str2) {
        return nGramSimilarity(str1, str2, 2, Base::overlap);
    }

    public static double threeGramOverlap(String str1, String str2) {
        return nGramSimilarity(str1, str2, 3, Base::overlap);
    }

    public static double fourGramOverlap(String str1, String str2) {
        return nGramSimilarity(str1, str2, 4, Base::overlap);
    }

    public static double fiveGramOverlap(String str1, String str2) {
        return nGramSimilarity(str1, str2, 5, Base::overlap);
    }

    // ngrams + normalization
    public static double twoGramOverlapNormalized(String str1, String str2) {
        return nGramSimilarityNormalized(str1, str2, 2, Base::overlap);
    }

    public static double threeGramOverlapNormalized(String str1, String str2) {
        return nGramSimilarityNormalized(str1, str2, 3, Base::overlap);
    }

    public static double fourGramOverlapNormalized(String str1, String str2) {
        return nGramSimilarityNormalized(str1, str2, 4, Base::overlap);
    }

    public static double fiveGramOverlapNormalized(String str1, String str2) {
        return nGramSimilarityNormalized(str1, str2, 5, Base::overlap);
    }

    // ngrams + normalization + padding
    public static double twoGramOverlapNormalizedPadding(String str1, String str2) {
        return nGramSimilarityNormalizedPadding(str1, str2, 2, Base::overlap);
    }

    public static double threeGramOverlapNormalizedPadding(String str1, String str2) {
        return nGramSimilarityNormalizedPadding(str1, str2, 3, Base::overlap);
    }

    public static double fourGramOverlapNormalizedPadding(String str1, String str2) {
        return nGramSimilarityNormalizedPadding(str1, str2, 4, Base::overlap);
    }

    public static double fiveGramOverlapNormalizedPadding(String str1, String str2) {
        return nGramSimilarityNormalizedPadding(str1, str2, 5, Base::overlap);
    }

    // shingles
    public static double twoShingleOverlap(String str1, String str2) {
        return shingleSimilarity(str1, str2, 2, Base::overlap);
    }

    public static double threeShingleOverlap(String str1, String str2) {
        return shingleSimilarity(str1, str2, 3, Base::overlap);
    }

    // shingles + normalization
    public static double twoShingleOverlapNormalized(String str1, String str2) {
        return shingleSimilarityNormalized(str1, str2, 2, Base::overlap);
    }

    public static double threeShingleOverlapNormalized(String str1, String str2) {
        return shingleSimilarityNormalized(str1, str2, 3, Base::overlap);
    }


    // ********** NGRAM SIMILARITY BASED ON KONDRAK05 **********

    public static double nGramSimilarityKondrak05(String str1, String str2){
        return Base.nGramSimilarityKondrak05(str1, str2);
    }

}