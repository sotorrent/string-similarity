package de.unitrier.st.stringsimilarity.fingerprint;

import static de.unitrier.st.stringsimilarity.Tokenization.NGRAM_SIZE;

/*
 * Different variants of fingerprint-based similarity metrics using default values.
 *
 * All metric variants must be a BiFunction<String, String, Double> and return a value between 0.0 and 1.0.
 */
public class Default {

    // ********** WINNOWING **********

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

}
