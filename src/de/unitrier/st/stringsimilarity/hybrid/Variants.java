package de.unitrier.st.stringsimilarity.hybrid;

import com.google.common.collect.Sets;
import de.unitrier.st.stringsimilarity.Normalization;

import java.util.HashSet;
import java.util.Set;

import static de.unitrier.st.stringsimilarity.Tokenization.nGramList;
import static de.unitrier.st.stringsimilarity.fingerprint.Base.fingerprintList;
import static de.unitrier.st.stringsimilarity.fingerprint.Variants.WINNOWING_WINDOW_SIZE;

/*
 * Hybrid similarity metrics.
 * All metrics should return a value between 0.0 and 1.0.
 */
public class Variants {

    public static double shingleFingerprintOverlapThenLevenshtein(String str1, String str2) {
        Set<Integer> set1 = new HashSet<>(
                fingerprintList(nGramList(Normalization.normalizeForNGram(str1)), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> set2 = new HashSet<>(
                fingerprintList(nGramList(Normalization.normalizeForNGram(str2)), WINNOWING_WINDOW_SIZE)
        );

        Set<Integer> intersection = Sets.intersection(set1, set2);

        // TODO: modify structure such that it is possible to get the raw strings for a matching fingerprint window
        // TODO: compute qGram similarity or Levenshtein similarity for matching raw string
        // TODO: combine dice of sets (winnowing) with edit-based similarity of matching blocks

        return 0.0;
    }
}
