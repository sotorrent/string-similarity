package de.unitrier.st.stringsimilarity.set;

import de.unitrier.st.stringsimilarity.util.InputTooShortException;
import org.apache.lucene.search.spell.NGramDistance;

import static de.unitrier.st.stringsimilarity.set.Base.*;

/*
 * Different variants of set-based similarity metrics.
 *
 * All metric variants must be a BiFunction<String, String, Double> and return a value between 0.0 and 1.0.
 */
public class Variants {

    private static NGramDistance twoGramDistance = new NGramDistance(2);
    private static NGramDistance threeGramDistance = new NGramDistance(3);
    private static NGramDistance fourGramDistance = new NGramDistance(4);
    private static NGramDistance fiveGramDistance = new NGramDistance(5);

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
        return nShingleSimilarity(str1, str2, 2, Base::jaccard);
    }

    public static double threeShingleJaccard(String str1, String str2) {
        return nShingleSimilarity(str1, str2, 3, Base::jaccard);
    }

    // shingles + normalization
    public static double twoShingleJaccardNormalized(String str1, String str2) {
        return nShingleSimilarityNormalized(str1, str2, 2, Base::jaccard);
    }

    public static double threeShingleJaccardNormalized(String str1, String str2) {
        return nShingleSimilarityNormalized(str1, str2, 3, Base::jaccard);
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
        return nShingleSimilarity(str1, str2, 2, Base::dice);
    }

    public static double threeShingleDice(String str1, String str2) {
        return nShingleSimilarity(str1, str2, 3, Base::dice);
    }

    // shingles + normalization
    public static double twoShingleDiceNormalized(String str1, String str2) {
        return nShingleSimilarityNormalized(str1, str2, 2, Base::dice);
    }

    public static double threeShingleDiceNormalized(String str1, String str2) {
        return nShingleSimilarityNormalized(str1, str2, 3, Base::dice);
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
        return nShingleSimilarity(str1, str2, 2, Base::overlap);
    }

    public static double threeShingleOverlap(String str1, String str2) {
        return nShingleSimilarity(str1, str2, 3, Base::overlap);
    }

    // shingles + normalization
    public static double twoShingleOverlapNormalized(String str1, String str2) {
        return nShingleSimilarityNormalized(str1, str2, 2, Base::overlap);
    }

    public static double threeShingleOverlapNormalized(String str1, String str2) {
        return nShingleSimilarityNormalized(str1, str2, 3, Base::overlap);
    }


    // ********** NGRAM SIMILARITY BASED ON KONDRAK05 **********

    public static double twoGramSimilarityKondrak05(String str1, String str2){
        if (str1.length() < 2 || str2.length() < 2) {
            throw new InputTooShortException("String length in shorter than nGram size.");
        }

        return twoGramDistance.getDistance(str1, str2);
    }

    public static double threeGramSimilarityKondrak05(String str1, String str2){
        if (str1.length() < 3 || str2.length() < 3) {
            throw new InputTooShortException("String length in shorter than nGram size.");
        }

        return threeGramDistance.getDistance(str1, str2);
    }

    public static double fourGramSimilarityKondrak05(String str1, String str2){
        if (str1.length() < 4 || str2.length() < 4) {
            throw new InputTooShortException("String length in shorter than nGram size.");
        }

        return fourGramDistance.getDistance(str1, str2);
    }

    public static double fiveGramSimilarityKondrak05(String str1, String str2){
        if (str1.length() < 5 || str2.length() < 5) {
            throw new InputTooShortException("String length in shorter than nGram size.");
        }

        return fiveGramDistance.getDistance(str1, str2);
    }
}