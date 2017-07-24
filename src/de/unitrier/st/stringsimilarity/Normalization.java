package de.unitrier.st.stringsimilarity;

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
        return str.replaceAll("[{};]", ""); // TODO: Other characters?
    }

    public static String replaceNonWordChars(String str) {
        return str.replaceAll("\\W+", " "); // replaces all non-word characters with a space
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
