package de.unitrier.st.stringsimilarity.hybrid;

import de.unitrier.st.stringsimilarity.util.NotImplementedException;

/*
 * Hybrid similarity metrics.
 *
 * All metric variants must be a BiFunction<String, String, Double> and return a value between 0.0 and 1.0.
 */
public class Variants {

    public static double nGramFingerprintOverlapThenLevenshtein(String str1, String str2) {

        // TODO: modify structure such that it is possible to get the raw strings for a matching fingerprint window
        // TODO: compute qGram similarity or Levenshtein similarity for matching raw string
        // TODO: combine dice of sets (winnowing) with edit-based similarity of matching blocks

        throw new NotImplementedException();
    }
}
