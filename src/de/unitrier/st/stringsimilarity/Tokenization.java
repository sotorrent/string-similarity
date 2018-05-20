package de.unitrier.st.stringsimilarity;

import com.google.common.collect.Multiset;
import de.unitrier.st.util.collections.MultisetCollector;
import de.unitrier.st.util.exceptions.InputTooShortException;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Tokenization {
    public static final String DEFAULT_SEPARATOR = " ";
    public static final String PADDING_CHAR = String.valueOf(((char)31)); // see https://en.wikipedia.org/wiki/Delimiter#ASCII_delimited_text
    /*
     * From paper Burrows07: "Chawla [5] identified the most appropriate size of n-grams for his PlagiIndex system to
     * be four, and we use this size for our approach.
     */
    public static final int NGRAM_SIZE = 4;
    /*
     * "Shingles are effectively word-nGrams." (https://www.elastic.co/blog/searching-with-shingles)
     */
    public static final int SHINGLE_SIZE = 2;


    // ********** TOKENS **********

    public static List<String> tokens(String str, String separator) {
        return Stream.of(str.split(Pattern.quote(separator)))
                .filter(s -> s.length()>0)
                .collect(Collectors.toList());
    }

    public static List<String> tokens(String str) {
        return tokens(str, DEFAULT_SEPARATOR);
    }


    // ********** NGRAMS **********

    public static List<String> nGramList(String str, int nGramSize, boolean padding) throws InputTooShortException {
        if (str.length() >= nGramSize) {
            final String finalStr; // for lambda expression
            final int length;

            if (padding) {
                length = str.length() + 2 * (nGramSize - 1);
                StringBuilder strPadding = new StringBuilder(length);
                for (int i = 0; i < nGramSize - 1; i++) {
                    strPadding.append(PADDING_CHAR);
                }
                strPadding.append(str);
                for (int i = 0; i < nGramSize - 1; i++) {
                    strPadding.append(PADDING_CHAR);
                }
                finalStr = strPadding.toString();
            } else {
                finalStr = str;
                length = str.length();
            }

            return IntStream
                    .range(0, length - nGramSize + 1)
                    .mapToObj(currentStartPos -> finalStr.substring(currentStartPos, currentStartPos + nGramSize))
                    .collect(Collectors.toList());
        } else {
            throw new InputTooShortException("String length is smaller than nGram size.");
        }
    }

    public static List<String> nGramList(String str, int nGramSize) {
        return nGramList(str, nGramSize, false);
    }

    public static List<String> nGramList(String str, boolean padding) {
        return nGramList(str, NGRAM_SIZE, padding);
    }

    public static List<String> nGramList(String str) {
        return nGramList(str, NGRAM_SIZE);
    }

    public static Multiset<String> nGramMultiset(String str, int nGramSize, boolean padding) {
        return nGramList(str, nGramSize, padding)
                .stream()
                .collect(MultisetCollector.toMultiset());
    }

    // see: https://en.wikipedia.org/wiki/Bag-of-words_model
    public static Multiset<String> nGramMultiset(String str, int nGramSize) {
        return nGramMultiset(str, nGramSize, false);
    }

    public static Multiset<String> nGramMultiset(String str) {
        return nGramMultiset(str, false);
    }

    public static Multiset<String> nGramMultiset(String str, boolean padding) {
        return nGramMultiset(str, NGRAM_SIZE, padding);
    }

    public static Set<String> nGramSet(String str, int nGramSize, boolean padding) {
        return nGramMultiset(str, nGramSize, padding).elementSet();
    }

    public static Set<String> nGramSet(String str, int nGramSize) {
        return nGramMultiset(str, nGramSize).elementSet();
    }

    public static Set<String> nGramSet(String str, boolean padding) {
        return nGramMultiset(str, padding).elementSet();
    }

    public static Set<String> nGramSet(String str) {
        return nGramSet(str, NGRAM_SIZE);
    }


    // ********** SHINGLES **********

    public static List<String> shingleList(List<String> tokens, String separator, int shingleSize) throws InputTooShortException {
        if (tokens.size() >= shingleSize) {
            return IntStream
                    .range(0, tokens.size()-shingleSize+1)
                    .mapToObj(currentStartPos -> tokens.subList(currentStartPos, currentStartPos+shingleSize))
                    .map(shingleList -> shingleList
                            .stream()
                            .collect(Collectors.joining(separator)))
                    .collect(Collectors.toList());
        } else {
            throw new InputTooShortException("Number of tokens is smaller than shingle size.");
        }
    }

    public static List<String> shingleList(List<String> tokens, String separator) {
        return shingleList(tokens, separator, SHINGLE_SIZE);
    }

    public static List<String> shingleList(List<String> tokens, int shingleSize) {
        return shingleList(tokens, DEFAULT_SEPARATOR, shingleSize);
    }

    public static List<String> shingleList(List<String> tokens) {
        return shingleList(tokens, DEFAULT_SEPARATOR, SHINGLE_SIZE);
    }

    // see: https://en.wikipedia.org/wiki/Bag-of-words_model
    public static Multiset<String> shingleMultiset(List<String> tokens, String separator, int shingleSize) {
        return shingleList(tokens, separator, shingleSize)
                .stream()
                .collect(MultisetCollector.toMultiset());
    }

    public static Multiset<String> shingleMultiset(List<String> tokens, String separator) {
        return shingleMultiset(tokens, separator, SHINGLE_SIZE);
    }

    public static Multiset<String> shingleMultiset(List<String> tokens, int shingleSize) {
        return shingleMultiset(tokens, DEFAULT_SEPARATOR, shingleSize);
    }

    public static Multiset<String> shingleMultiset(List<String> tokens) {
        return shingleMultiset(tokens, DEFAULT_SEPARATOR, SHINGLE_SIZE);
    }

    public static Set<String> shingleSet(List<String> tokens, String separator, int shingleSize) {
        return shingleMultiset(tokens, separator, shingleSize).elementSet();
    }

    public static Set<String> shingleSet(List<String> tokens, String separator) {
        return shingleSet(tokens, separator, SHINGLE_SIZE);
    }

    public static Set<String> shingleSet(List<String> tokens, int shingleSize) {
        return shingleSet(tokens, DEFAULT_SEPARATOR, shingleSize);
    }

    public static Set<String> shingleSet(List<String> tokens) {
        return shingleSet(tokens, DEFAULT_SEPARATOR, SHINGLE_SIZE);
    }
}
