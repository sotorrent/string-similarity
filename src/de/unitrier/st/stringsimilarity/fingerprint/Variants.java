package de.unitrier.st.stringsimilarity.fingerprint;

import static de.unitrier.st.stringsimilarity.fingerprint.Base.GUARANTEE_THRESHOLD;

/*
 * Different variants of fingerprint-based similarity metrics.
 *
 * All metric variants must be a BiFunction<String, String, Double> and return a value between 0.0 and 1.0.
 */
public class Variants {

    // ngrams + dice
    public static double winnowingTwoGramDice(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 2, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::dice
        );
    }

    public static double winnowingThreeGramDice(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 3, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::dice
        );
    }


    public static double winnowingFourGramDice(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 4, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::dice
        );
    }


    public static double winnowingFiveGramDice(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 5, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::dice
        );
    }

    // ngrams + dice + normalization
    public static double winnowingTwoGramDiceNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 2, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::dice
        );
    }

    public static double winnowingThreeGramDiceNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 3, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::dice
        );
    }

    public static double winnowingFourGramDiceNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 4, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::dice
        );
    }

    public static double winnowingFiveGramDiceNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 5, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::dice
        );
    }

    // ngrams + dice variant
    public static double winnowingTwoGramDiceVariant(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 2, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::diceVariant
        );
    }

    public static double winnowingThreeGramDiceVariant(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 3, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::diceVariant
        );
    }

    public static double winnowingFourGramDiceVariant(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 4, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::diceVariant
        );
    }

    public static double winnowingFiveGramDiceVariant(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 5, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::diceVariant
        );
    }

    // ngrams + dice variant + normalization
    public static double winnowingTwoGramDiceVariantNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 2, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::diceVariant
        );
    }

    public static double winnowingThreeGramDiceVariantNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 3, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::diceVariant
        );
    }

    public static double winnowingFourGramDiceVariantNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 4, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::diceVariant
        );
    }

    public static double winnowingFiveGramDiceVariantNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 5, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::diceVariant
        );
    }

    // ngrams + jaccard
    public static double winnowingTwoGramJaccard(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 2, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::jaccard
        );
    }

    public static double winnowingThreeGramJaccard(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 3, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::jaccard
        );
    }

    public static double winnowingFourGramJaccard(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 4, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::jaccard
        );
    }

    public static double winnowingFiveGramJaccard(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 5, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::jaccard
        );
    }

    // ngrams + jaccard + normalization
    public static double winnowingTwoGramJaccardNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 2, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::jaccard
        );
    }

    public static double winnowingThreeGramJaccardNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 3, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::jaccard
        );
    }

    public static double winnowingFourGramJaccardNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 4, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::jaccard
        );
    }

    public static double winnowingFiveGramJaccardNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 5, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::jaccard
        );
    }

    // ngrams + overlap
    public static double winnowingTwoGramOverlap(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 2, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::overlap
        );
    }

    public static double winnowingThreeGramOverlap(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 3, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::overlap
        );
    }

    public static double winnowingFourGramOverlap(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 4, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::overlap
        );
    }

    public static double winnowingFiveGramOverlap(String str1, String str2) {
        return Base.winnowingNGramSimilarity(str1, str2, 5, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::overlap
        );
    }

    // ngrams + overlap + normalization
    public static double winnowingTwoGramOverlapNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 2, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::overlap
        );
    }

    public static double winnowingThreeGramOverlapNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 3, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::overlap
        );
    }

    public static double winnowingFourGramOverlapNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 4, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::overlap
        );
    }

    public static double winnowingFiveGramOverlapNormalized(String str1, String str2) {
        return Base.winnowingNGramSimilarityNormalized(str1, str2, 5, GUARANTEE_THRESHOLD,
                de.unitrier.st.stringsimilarity.set.Base::overlap
        );
    }

}
