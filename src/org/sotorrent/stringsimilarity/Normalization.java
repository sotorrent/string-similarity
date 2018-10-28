package org.sotorrent.stringsimilarity;

// TODO: add method to normalize text blocks by a stemmer, e.g. the Porter Stemmer http://snowball.tartarus.org/algorithms/english/stemmer.html

public class Normalization {
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
        return str.replaceAll("[{},;.:]", "");
    }

    public static String replaceNonWordChars(String str) {
        return str.replaceAll("\\W+", " "); // replaces all non-word characters with a space
    }

    // ********** DEFAULT NORMALIZATION **********

    // -> for edit metrics
    public static String normalizeForEdit(String str) {
        return unifyWhitespaces(removeSpecialChars(toLowerCase(str)));
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
