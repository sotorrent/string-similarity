package de.unitrier.st.stringsimilarity.fingerprint;

import static de.unitrier.st.stringsimilarity.Tokenization.NGRAM_SIZE;
import static de.unitrier.st.stringsimilarity.Tokenization.SHINGLE_SIZE;

/*
 * Different variants of fingerprint-based similarity metrics using default values.
 *
 * All metric variants must be a BiFunction<String, String, Double> and return a value between 0.0 and 1.0.
 */
public class Default {

    // ********** WINNOWING **********

    /*
    * Similarity metrics based on Winnowing with tokens.
    */

    // tokens + dice
    public static double winnowingTokenDice(String str1, String str2) {
        return Base.winnowingTokenSimilarity(str1, str2, de.unitrier.st.stringsimilarity.set.Base::dice);
    }

    // tokens + dice + normalization
    public static double winnowingTokenDiceNormalized(String str1, String str2) {
        return Base.winnowingTokenSimilarityNormalized(str1, str2, de.unitrier.st.stringsimilarity.set.Base::dice);
    }

    // tokens + dice variant
    public static double winnowingTokenDiceVariant(String str1, String str2) {
        return Base.winnowingTokenSimilarity(str1, str2, de.unitrier.st.stringsimilarity.set.Base::diceVariant);
    }

    // tokens + dice variant + normalization
    public static double winnowingTokenDiceVariantNormalized(String str1, String str2) {
        return Base.winnowingTokenSimilarityNormalized(str1, str2, de.unitrier.st.stringsimilarity.set.Base::diceVariant);
    }

    // tokens + jaccard
    public static double winnowingTokenJaccard(String str1, String str2) {
        return Base.winnowingTokenSimilarity(str1, str2, de.unitrier.st.stringsimilarity.set.Base::jaccard);
    }

    // tokens + jaccard + normalization
    public static double winnowingTokenJaccardNormalized(String str1, String str2) {
        return Base.winnowingTokenSimilarityNormalized(str1, str2, de.unitrier.st.stringsimilarity.set.Base::jaccard);
    }

    // tokens + overlap
    public static double winnowingTokenOverlap(String str1, String str2) {
        return Base.winnowingTokenSimilarity(str1, str2, de.unitrier.st.stringsimilarity.set.Base::overlap);
    }

    // tokens + overlap + normalization
    public static double winnowingTokenOverlapNormalized(String str1, String str2) {
        return Base.winnowingTokenSimilarityNormalized(str1, str2, de.unitrier.st.stringsimilarity.set.Base::overlap);
    }


    /*
    * Similarity metrics based on Winnowing with nGrams.
    */

    // ngrams + dice
    public static double winnowingNGramDice(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, NGRAM_SIZE,
                de.unitrier.st.stringsimilarity.set.Base::dice
        );
    }

    // ngrams + dice + normalization
    public static double winnowingNGramDiceNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, NGRAM_SIZE,
                de.unitrier.st.stringsimilarity.set.Base::dice
        );
    }

    // ngrams + dice variant
    public static double winnowingNGramDiceVariant(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, NGRAM_SIZE,
                de.unitrier.st.stringsimilarity.set.Base::diceVariant
        );
    }

    // ngrams + dice variant + normalization
    public static double winnowingNGramDiceVariantNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, NGRAM_SIZE,
                de.unitrier.st.stringsimilarity.set.Base::diceVariant
        );
    }

    // ngrams + jaccard
    public static double winnowingNGramJaccard(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, NGRAM_SIZE,
                de.unitrier.st.stringsimilarity.set.Base::jaccard
        );
    }

    // ngrams + jaccard + normalization
    public static double winnowingNGramJaccardNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, NGRAM_SIZE,
                de.unitrier.st.stringsimilarity.set.Base::jaccard
        );
    }

    // ngrams + overlap
    public static double winnowingNGramOverlap(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, NGRAM_SIZE,
                de.unitrier.st.stringsimilarity.set.Base::overlap
        );
    }

    // ngrams + overlap + normalization
    public static double winnowingNGramOverlapNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, NGRAM_SIZE,
                de.unitrier.st.stringsimilarity.set.Base::overlap
        );
    }


    /*
    * Similarity metrics based on Winnowing with shingles.
    */

    // shingles + dice
    public static double winnowingNShingleDice(String str1, String str2) {
        return Base.winnowingNShingleSimilarity(str1, str2, SHINGLE_SIZE,
                de.unitrier.st.stringsimilarity.set.Base::dice
        );
    }

    // shingles + dice + normalization
    public static double winnowingNShingleDiceNormalized(String str1, String str2) {
        return Base.winnowingNShingleSimilarityNormalized(str1, str2, SHINGLE_SIZE,
                de.unitrier.st.stringsimilarity.set.Base::dice
        );
    }

    // shingles + dice variant
    public static double winnowingNShingleDiceVariant(String str1, String str2) {
        return Base.winnowingNShingleSimilarity(str1, str2, SHINGLE_SIZE,
                de.unitrier.st.stringsimilarity.set.Base::diceVariant
        );
    }

    // shingles + dice variant + normalization
    public static double winnowingNShingleDiceVariantNormalized(String str1, String str2) {
        return Base.winnowingNShingleSimilarityNormalized(str1, str2, SHINGLE_SIZE,
                de.unitrier.st.stringsimilarity.set.Base::diceVariant
        );
    }

    // shingles + jaccard
    public static double winnowingNShingleJaccard(String str1, String str2) {
        return Base.winnowingNShingleSimilarity(str1, str2, SHINGLE_SIZE,
                de.unitrier.st.stringsimilarity.set.Base::jaccard
        );
    }

    // shingles + jaccard + normalization
    public static double winnowingNShingleJaccardNormalized(String str1, String str2) {
        return Base.winnowingNShingleSimilarityNormalized(str1, str2, SHINGLE_SIZE,
                de.unitrier.st.stringsimilarity.set.Base::jaccard
        );
    }

    // shingles + overlap
    public static double winnowingNShingleOverlap(String str1, String str2) {
        return Base.winnowingNShingleSimilarity(str1, str2, SHINGLE_SIZE,
                de.unitrier.st.stringsimilarity.set.Base::overlap
        );
    }

    // shingles + overlap + normalization
    public static double winnowingNShingleOverlapNormalized(String str1, String str2) {
        return Base.winnowingNShingleSimilarityNormalized(str1, str2, SHINGLE_SIZE,
                de.unitrier.st.stringsimilarity.set.Base::overlap
        );
    }

}
