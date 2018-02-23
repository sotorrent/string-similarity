package de.unitrier.st.stringsimilarity;

// TODO: add method to normalize text blocks by a stemmer, e.g. the Porter Stemmer http://snowball.tartarus.org/algorithms/english/stemmer.html

import org.tartarus.snowball.SnowballStemmer;
import org.tartarus.snowball.ext.englishStemmer;

import java.util.List;

public class Normalization {

    private static SnowballStemmer snowballStemmer = new englishStemmer();


    public static String removeWhitespaces(String str) {
        return str.replaceAll("\\s+","");  // http://stackoverflow.com/questions/5455794/removing-whitespace-from-strings-in-java
    }

    public static String unifyWhitespaces(String str) {
        return str.replaceAll("\\s+"," ");  // http://stackoverflow.com/questions/5455794/removing-whitespace-from-strings-in-java
    }

    public static String toLowerCase(String str) {
        return str.toLowerCase();
    }

    public static String removeSpecialChars(String str) {
        // TODO: Other characters?
        return str.replaceAll("[{};]", "");
    }

    public static String replaceNonWordChars(String str) {
        return str.replaceAll("\\W+", " "); // replaces all non-word characters with a space
    }

    public static String normalizeWithStemmer(String str) {
        // choosed snowball stemmer (Porter 2) because of recommendation in https://stackoverflow.com/a/11210358
        List<String> tokens =
                Tokenization.tokens(
                        Normalization.toLowerCase(str)
                );
        StringBuilder output = new StringBuilder();

        for (String token : tokens) {
            snowballStemmer.setCurrent(token);
            snowballStemmer.stem();
            output.append(snowballStemmer.getCurrent()).append(" ");
        }

        return output.toString();
    }

    // ********** DEFAULT NORMALIZATION **********

    // -> for edit metrics
    public static String normalizeForEdit(String str) {
        return unifyWhitespaces(toLowerCase(str));
    }


    // -> for ngrams
    public static String normalizeForNGram(String str) {
        return removeWhitespaces(removeSpecialChars(toLowerCase(str)));
    }

    // -> for shingles
    public static String normalizeForShingle(String str) {
        return unifyWhitespaces(replaceNonWordChars(toLowerCase(str)));
    }
}
