package de.unitrier.st.stringsimilarity.fingerprint;

/*
 * Different variants of fingerprint-based similarity metrics.
 *
 * All metric variants must be a BiFunction<String, String, Double> and return a value between 0.0 and 1.0.
 */
public class Variants {


    /*
    * Similarity metrics based on Winnowing with tokens.
    */

    // tokens + dice
    public static double winnowingTokenDice(String str1, String str2) {
        return Default.winnowingTokenDice(str1, str2);
    }

    // tokens + dice + normalization
    public static double winnowingTokenDiceNormalized(String str1, String str2) {
        return Default.winnowingTokenDiceNormalized(str1, str2);
    }

    // tokens + dice variant
    public static double winnowingTokenDiceVariant(String str1, String str2) {
        return Default.winnowingTokenDiceVariant(str1, str2);
    }

    // tokens + dice variant + normalization
    public static double winnowingTokenDiceVariantNormalized(String str1, String str2) {
        return Default.winnowingTokenDiceVariantNormalized(str1, str2);
    }

    // tokens + jaccard
    public static double winnowingTokenJaccard(String str1, String str2) {
        return Default.winnowingTokenJaccard(str1, str2);
    }

    // tokens + jaccard + normalization
    public static double winnowingTokenJaccardNormalized(String str1, String str2) {
        return Default.winnowingTokenJaccardNormalized(str1, str2);
    }

    // tokens + overlap
    public static double winnowingTokenOverlap(String str1, String str2) {
        return Default.winnowingTokenOverlap(str1, str2);
    }

    // tokens + overlap + normalization
    public static double winnowingTokenOverlapNormalized(String str1, String str2) {
        return Default.winnowingTokenOverlapNormalized(str1, str2);
    }


    /*
    * Similarity metrics based on Winnowing with nGrams.
    */


    // ngrams + dice
    public static double winnowingTwoGramDice(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 2,
                de.unitrier.st.stringsimilarity.set.Base::dice
        );
    }

    public static double winnowingThreeGramDice(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 3,
                de.unitrier.st.stringsimilarity.set.Base::dice
        );
    }


    public static double winnowingFourGramDice(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 4,
                de.unitrier.st.stringsimilarity.set.Base::dice
        );
    }


    public static double winnowingFiveGramDice(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 5,
                de.unitrier.st.stringsimilarity.set.Base::dice
        );
    }

    // ngrams + dice + normalization
    public static double winnowingTwoGramDiceNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 2,
                de.unitrier.st.stringsimilarity.set.Base::dice
        );
    }

    public static double winnowingThreeGramDiceNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 3,
                de.unitrier.st.stringsimilarity.set.Base::dice
        );
    }

    public static double winnowingFourGramDiceNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 4,
                de.unitrier.st.stringsimilarity.set.Base::dice
        );
    }

    public static double winnowingFiveGramDiceNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 5,
                de.unitrier.st.stringsimilarity.set.Base::dice
        );
    }

    // ngrams + dice variant
    public static double winnowingTwoGramDiceVariant(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 2,
                de.unitrier.st.stringsimilarity.set.Base::diceVariant
        );
    }

    public static double winnowingThreeGramDiceVariant(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 3,
                de.unitrier.st.stringsimilarity.set.Base::diceVariant
        );
    }

    public static double winnowingFourGramDiceVariant(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 4,
                de.unitrier.st.stringsimilarity.set.Base::diceVariant
        );
    }

    public static double winnowingFiveGramDiceVariant(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 5,
                de.unitrier.st.stringsimilarity.set.Base::diceVariant
        );
    }

    // ngrams + dice variant + normalization
    public static double winnowingTwoGramDiceVariantNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 2,
                de.unitrier.st.stringsimilarity.set.Base::diceVariant
        );
    }

    public static double winnowingThreeGramDiceVariantNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 3,
                de.unitrier.st.stringsimilarity.set.Base::diceVariant
        );
    }

    public static double winnowingFourGramDiceVariantNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 4,
                de.unitrier.st.stringsimilarity.set.Base::diceVariant
        );
    }

    public static double winnowingFiveGramDiceVariantNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 5,
                de.unitrier.st.stringsimilarity.set.Base::diceVariant
        );
    }

    // ngrams + jaccard
    public static double winnowingTwoGramJaccard(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 2,
                de.unitrier.st.stringsimilarity.set.Base::jaccard
        );
    }

    public static double winnowingThreeGramJaccard(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 3,
                de.unitrier.st.stringsimilarity.set.Base::jaccard
        );
    }

    public static double winnowingFourGramJaccard(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 4,
                de.unitrier.st.stringsimilarity.set.Base::jaccard
        );
    }

    public static double winnowingFiveGramJaccard(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 5,
                de.unitrier.st.stringsimilarity.set.Base::jaccard
        );
    }

    // ngrams + jaccard + normalization
    public static double winnowingTwoGramJaccardNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 2,
                de.unitrier.st.stringsimilarity.set.Base::jaccard
        );
    }

    public static double winnowingThreeGramJaccardNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 3,
                de.unitrier.st.stringsimilarity.set.Base::jaccard
        );
    }

    public static double winnowingFourGramJaccardNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 4,
                de.unitrier.st.stringsimilarity.set.Base::jaccard
        );
    }

    public static double winnowingFiveGramJaccardNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 5,
                de.unitrier.st.stringsimilarity.set.Base::jaccard
        );
    }

    // ngrams + overlap
    public static double winnowingTwoGramOverlap(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 2,
                de.unitrier.st.stringsimilarity.set.Base::overlap
        );
    }

    public static double winnowingThreeGramOverlap(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 3,
                de.unitrier.st.stringsimilarity.set.Base::overlap
        );
    }

    public static double winnowingFourGramOverlap(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 4,
                de.unitrier.st.stringsimilarity.set.Base::overlap
        );
    }

    public static double winnowingFiveGramOverlap(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 5,
                de.unitrier.st.stringsimilarity.set.Base::overlap
        );
    }

    // ngrams + overlap + normalization
    public static double winnowingTwoGramOverlapNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 2,
                de.unitrier.st.stringsimilarity.set.Base::overlap
        );
    }

    public static double winnowingThreeGramOverlapNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 3,
                de.unitrier.st.stringsimilarity.set.Base::overlap
        );
    }

    public static double winnowingFourGramOverlapNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 4,
                de.unitrier.st.stringsimilarity.set.Base::overlap
        );
    }

    public static double winnowingFiveGramOverlapNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 5,
                de.unitrier.st.stringsimilarity.set.Base::overlap
        );
    }


    /*
    * Similarity metrics based on Winnowing with shingles.
    */

    // shingles + dice
    public static double winnowingTwoShingleDice(String str1, String str2) {
        return Base.winnowingNShingleSimilarity(str1, str2, 2,
                de.unitrier.st.stringsimilarity.set.Base::dice
        );
    }

    public static double winnowingThreeShingleDice(String str1, String str2) {
        return Base.winnowingNShingleSimilarity(str1, str2, 3,
                de.unitrier.st.stringsimilarity.set.Base::dice
        );
    }

    // shingles + dice + normalization
    public static double winnowingTwoShingleDiceNormalized(String str1, String str2) {
        return Base.winnowingNShingleSimilarityNormalized(str1, str2, 2,
                de.unitrier.st.stringsimilarity.set.Base::dice
        );
    }

    public static double winnowingThreeShingleDiceNormalized(String str1, String str2) {
        return Base.winnowingNShingleSimilarityNormalized(str1, str2, 3,
                de.unitrier.st.stringsimilarity.set.Base::dice
        );
    }

    // shingles + dice variant
    public static double winnowingTwoShingleDiceVariant(String str1, String str2) {
        return Base.winnowingNShingleSimilarity(str1, str2, 2,
                de.unitrier.st.stringsimilarity.set.Base::diceVariant
        );
    }

    public static double winnowingThreeShingleDiceVariant(String str1, String str2) {
        return Base.winnowingNShingleSimilarity(str1, str2, 3,
                de.unitrier.st.stringsimilarity.set.Base::diceVariant
        );
    }

    // shingles + dice variant + normalization
    public static double winnowingTwoShingleDiceVariantNormalized(String str1, String str2) {
        return Base.winnowingNShingleSimilarityNormalized(str1, str2, 2,
                de.unitrier.st.stringsimilarity.set.Base::diceVariant
        );
    }

    public static double winnowingThreeShingleDiceVariantNormalized(String str1, String str2) {
        return Base.winnowingNShingleSimilarityNormalized(str1, str2, 3,
                de.unitrier.st.stringsimilarity.set.Base::diceVariant
        );
    }

    // shingles + jaccard
    public static double winnowingTwoShingleJaccard(String str1, String str2) {
        return Base.winnowingNShingleSimilarity(str1, str2, 2,
                de.unitrier.st.stringsimilarity.set.Base::jaccard
        );
    }

    public static double winnowingThreeShingleJaccard(String str1, String str2) {
        return Base.winnowingNShingleSimilarity(str1, str2, 3,
                de.unitrier.st.stringsimilarity.set.Base::jaccard
        );
    }

    // shingles + jaccard + normalization
    public static double winnowingTwoShingleJaccardNormalized(String str1, String str2) {
        return Base.winnowingNShingleSimilarityNormalized(str1, str2, 2,
                de.unitrier.st.stringsimilarity.set.Base::jaccard
        );
    }

    public static double winnowingThreeShingleJaccardNormalized(String str1, String str2) {
        return Base.winnowingNShingleSimilarityNormalized(str1, str2, 3,
                de.unitrier.st.stringsimilarity.set.Base::jaccard
        );
    }

    // shingles + overlap
    public static double winnowingTwoShingleOverlap(String str1, String str2) {
        return Base.winnowingNShingleSimilarity(str1, str2, 2,
                de.unitrier.st.stringsimilarity.set.Base::overlap
        );
    }

    public static double winnowingThreeShingleOverlap(String str1, String str2) {
        return Base.winnowingNShingleSimilarity(str1, str2, 3,
                de.unitrier.st.stringsimilarity.set.Base::overlap
        );
    }

    // shingles + overlap + normalization
    public static double winnowingTwoShingleOverlapNormalized(String str1, String str2) {
        return Base.winnowingNShingleSimilarityNormalized(str1, str2, 2,
                de.unitrier.st.stringsimilarity.set.Base::overlap
        );
    }

    public static double winnowingThreeShingleOverlapNormalized(String str1, String str2) {
        return Base.winnowingNShingleSimilarityNormalized(str1, str2, 3,
                de.unitrier.st.stringsimilarity.set.Base::overlap
        );
    }

}
