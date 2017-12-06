package de.unitrier.st.stringsimilarity.set;

import de.unitrier.st.util.InputTooShortException;
import org.apache.lucene.search.spell.NGramDistance;

import static de.unitrier.st.stringsimilarity.Tokenization.NGRAM_SIZE;
import static de.unitrier.st.stringsimilarity.Tokenization.SHINGLE_SIZE;
import static de.unitrier.st.stringsimilarity.set.Base.*;

/*
 * Different variants of set-based similarity metrics using default values.
 *
 * All metric variants must be a BiFunction<String, String, Double> and return a value between 0.0 and 1.0.
 */
public class Default {
    // needed for nGramSimilarityKondrak05
    private final static NGramDistance nGramDistance = new NGramDistance(NGRAM_SIZE);

    // ********** JACCARD **********

    // tokens
    public static double tokenJaccard(String str1, String str2) {
        return tokenSimilarity(str1, str2, Base::jaccard);
    }

    // tokens + normalization
    public static double tokenJaccardNormalized(String str1, String str2) {
        return tokenSimilarityNormalized(str1, str2, Base::jaccard);
    }

    // ngrams
    public static double nGramJaccard(String str1, String str2) {
        return nGramSimilarity(str1, str2, NGRAM_SIZE, Base::jaccard);
    }

    // ngrams + normalization
    public static double nGramJaccardNormalized(String str1, String str2) {
        return nGramSimilarityNormalized(str1, str2, NGRAM_SIZE, Base::jaccard);
    }

    // ngrams + normalization + padding
    public static double nGramJaccardNormalizedPadding(String str1, String str2) {
        return nGramSimilarityNormalizedPadding(str1, str2, NGRAM_SIZE, Base::jaccard);
    }

    // shingles
    public static double nShingleJaccard(String str1, String str2) {
        return nShingleSimilarity(str1, str2, SHINGLE_SIZE, Base::jaccard);
    }

    // shingles + normalization
    public static double nShingleJaccardNormalized(String str1, String str2) {
        return nShingleSimilarityNormalized(str1, str2, SHINGLE_SIZE, Base::jaccard);
    }


    // ********** DICE **********

    // tokens
    public static double tokenDice(String str1, String str2) {
        return tokenSimilarity(str1, str2, Base::dice);
    }

    // tokens + normalization
    public static double tokenDiceNormalized(String str1, String str2) {
        return tokenSimilarityNormalized(str1, str2, Base::dice);
    }

    // ngrams
    public static double nGramDice(String str1, String str2) {
        return nGramSimilarity(str1, str2, NGRAM_SIZE, Base::dice);
    }

    // ngrams + normalization
    public static double nGramDiceNormalized(String str1, String str2) {
        return nGramSimilarityNormalized(str1, str2, NGRAM_SIZE, Base::dice);
    }

    // ngrams + normalization + padding
    public static double nGramDiceNormalizedPadding(String str1, String str2) {
        return nGramSimilarityNormalizedPadding(str1, str2, NGRAM_SIZE, Base::dice);
    }

    // shingles
    public static double nShingleDice(String str1, String str2) {
        return nShingleSimilarity(str1, str2, SHINGLE_SIZE, Base::dice);
    }

    // shingles + normalization
    public static double nShingleDiceNormalized(String str1, String str2) {
        return nShingleSimilarityNormalized(str1, str2, SHINGLE_SIZE, Base::dice);
    }

    // ********** OVERLAP **********

    // tokens
    public static double tokenOverlap(String str1, String str2) {
        return tokenSimilarity(str1, str2, Base::overlap);
    }

    // tokens + normalization
    public static double tokenOverlapNormalized(String str1, String str2) {
        return tokenSimilarityNormalized(str1, str2, Base::overlap);
    }

    // ngrams
    public static double nGramOverlap(String str1, String str2) {
        return nGramSimilarity(str1, str2, NGRAM_SIZE, Base::overlap);
    }

    // ngrams + normalization
    public static double nGramOverlapNormalized(String str1, String str2) {
        return nGramSimilarityNormalized(str1, str2, NGRAM_SIZE, Base::overlap);
    }

    // ngrams + normalization + padding
    public static double nGramOverlapNormalizedPadding(String str1, String str2) {
        return nGramSimilarityNormalizedPadding(str1, str2, NGRAM_SIZE, Base::overlap);
    }

    // shingles
    public static double nShingleOverlap(String str1, String str2) {
        return nShingleSimilarity(str1, str2, SHINGLE_SIZE, Base::overlap);
    }

    // shingles + normalization
    public static double nShingleOverlapNormalized(String str1, String str2) {
        return nShingleSimilarityNormalized(str1, str2, SHINGLE_SIZE, Base::overlap);
    }

    public static double nGramSimilarityKondrak05(String str1, String str2){
        if (str1.length() < NGRAM_SIZE || str2.length() < NGRAM_SIZE) {
            throw new InputTooShortException("String length in shorter than nGram size.");
        }

        return nGramDistance.getDistance(str1, str2);
    }

}