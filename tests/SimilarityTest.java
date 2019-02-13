import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sotorrent.stringsimilarity.Similarity;
import org.sotorrent.stringsimilarity.profile.Variants;
import org.sotorrent.util.MathUtils;
import org.sotorrent.util.exceptions.InputTooShortException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.sotorrent.stringsimilarity.Normalization.normalizeForEdit;
import static org.sotorrent.stringsimilarity.edit.Variants.*;
import static org.sotorrent.stringsimilarity.set.Variants.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SimilarityTest {
    private static final String str1 = "Please divide this Sentence, into Tokens or nGrams or Shingles";
    private static final String str2 = "Please do not divide this sentence, into Tokens or nGrams or shingles";

    private final String s1 = "public String f(String s)";
    private final String s2 = "public String func(String s)";

    private final String t1 = "with Hash table entries Hash table entries has Arun name is here, Arun name is here with Hash table entries Arun how is arun";
    private final String t2 = "Hash table entries has Arun name is here, Arun name is here with Hash table entries Arun how is arun Arun name is here with Hash table entries";

    @Test
    void testInputTooShortException() {
        assertThrows(InputTooShortException.class, () -> twoShingleJaccard("a", "a"));

        assertThrows(InputTooShortException.class, () -> twoGramJaccard("a", "a"));
    }

    // ************************ EDIT-BASED ************************

    @Test
    void testLevenshtein() {
        assertEquals(1.0, levenshtein("", ""), Similarity.DELTA_MAX);
        assertEquals(1.0, levenshtein("Hello","Hello"), Similarity.DELTA_MAX);

        // str1 normalized: "please divide this sentence into tokens or ngrams or shingles"
        // str2 normalized: "please do not divide this sentence into tokens or ngrams or shingles"
        // max length: 68
        // levenshtein distance: 7
        assertEquals((68.0-7.0)/68.0, levenshtein(normalizeForEdit(str1), normalizeForEdit(str2)), Similarity.DELTA_MAX);
        assertEquals(0.5, levenshtein("paul", "pual"), Similarity.DELTA_MAX);
        assertEquals(0.2, levenshtein("Hello","Ola"), Similarity.DELTA_MAX);

        assertThat(levenshtein("Here is my go at it:", "Here is my go at it:"), allOf(greaterThanOrEqualTo(0.0),lessThanOrEqualTo(1.0)));

        // str1: "ab"
        // str2: ""
        // max length: 2
        // levenshtein distance: 2
        assertEquals((2.0-2.0)/2.0, levenshtein("ab", ""), Similarity.DELTA_MAX);
        assertEquals((2.0-2.0)/2.0, levenshtein("", "ab"), Similarity.DELTA_MAX);

        // str1: "2007"
        // str2: "2008"
        // max length: 4
        // levenshtein distance: 1
        assertEquals((4.0-1.0)/4.0, levenshtein("2007", "2008"), Similarity.DELTA_MAX);
    }

    @Test
    void testDamerauLevenshtein() {
        assertEquals(1.0, damerauLevenshtein("", ""), Similarity.DELTA_MAX);
        assertEquals(1.0, damerauLevenshtein("Hello","Hello"), Similarity.DELTA_MAX);
        // str1 normalized: "please divide this sentence into tokens or ngrams or shingles"
        // str2 normalized: "please do not divide this sentence into tokens or ngrams or shingles"
        // max length: 68
        // damerau-levenshtein distance: 7
        assertEquals((68.0-7.0)/68.0, damerauLevenshtein(normalizeForEdit(str1), normalizeForEdit(str2)), Similarity.DELTA_MAX);
        assertEquals(0.75, damerauLevenshtein("paul", "pual"), Similarity.DELTA_MAX);
        assertEquals(0.8, damerauLevenshtein("Hello","Hlelo"), Similarity.DELTA_MAX);

        // str1: "ab"
        // str2: ""
        // max length: 2
        // damerau-levenshtein distance: 2
        assertEquals((2.0-2.0)/2.0, damerauLevenshtein("ab", ""));
        assertEquals((2.0-2.0)/2.0, damerauLevenshtein("", "ab"));

        // str1: "2007"
        // str2: "2008"
        // max length: 4
        // damerau-levenshtein distance: 1
        assertEquals((4.0-1.0)/4.0, damerauLevenshtein("2007", "2008"), Similarity.DELTA_MAX);
    }

    @Test
    void testOptimalStringAlignment() {
        assertEquals(1.0, optimalAlignment("", ""), Similarity.DELTA_MAX);
        assertEquals(0.75, optimalAlignment("paul", "pual"), Similarity.DELTA_MAX);

        // str1: "ab"
        // str2: ""
        // max length: 2
        // optimal string alignment distance: 2
        assertEquals((2.0-2.0)/2.0, optimalAlignment("ab", ""), Similarity.DELTA_MAX);
        assertEquals((2.0-2.0)/2.0, optimalAlignment("", "ab"), Similarity.DELTA_MAX);

        // str1: "2007"
        // str2: "2008"
        // max length: 4
        // optimal string alignment distance: 1
        assertEquals((4.0-1.0)/4.0, optimalAlignment("2007", "2008"), Similarity.DELTA_MAX);
    }

    @Test
    void testLongestCommonSubsequence() {
        // str1 is  "Please divide this Sentence, into Tokens or nGrams or Shingles"            => 62 Characters
        // str2 is  "Please do not divide this sentence, into Tokens or nGrams or shingles"     => 69 Characters
        // LCS is   "Please divide this entence, into Tokens or nGrams or hingles"              => 60 Characters

        assertEquals(60.0/69, longestCommonSubsequence(str1,str2), Similarity.DELTA_MAX);

        assertEquals(0.6, longestCommonSubsequence("ABCDEFG","ABCDEFHJKL"), Similarity.DELTA_MAX);

        assertEquals(1.0, longestCommonSubsequence("", ""), Similarity.DELTA_MAX);
        assertEquals(0.75, longestCommonSubsequence("paul", "pual"), Similarity.DELTA_MAX);

        // str1: "ab"
        // str2: ""
        // max length: 2
        // longestCommonSubsequence similarity: 0
        assertEquals(0.0, longestCommonSubsequence("ab", ""), Similarity.DELTA_MAX);
        assertEquals(0.0, longestCommonSubsequence("", "ab"), Similarity.DELTA_MAX);

        // str1: "2007"
        // str2: "2008"
        // max length: 4
        // longest common subsequence: 3
        assertEquals(3.0/4.0, longestCommonSubsequence("2007", "2008"), Similarity.DELTA_MAX);
    }

    // ************************ SET-BASED ************************

    @Test
    void testSetBased(){
        assertEquals(15./24, fourGramJaccard(s1,s2), Similarity.DELTA_MAX);
        assertEquals(3./15, threeShingleJaccard(str1,str2), Similarity.DELTA_MAX);

        // str1: "Hallo Du";
        // str2: "Hallo Sie";
        //
        //       "Hall" - "allo" - "llo " - "lo D"  - "o Du" - "lo S" - "o Si" - " Sie"
        // str1:   1         1       1        1          1        0        0        0
        // str2:   1         1       1        0          0        1        1        1
        assertEquals(3.0/8, fourGramJaccard("Hallo Du", "Hallo Sie"));

        assertEquals(2*15./(18+21), fourGramDice(s1,s2), Similarity.DELTA_MAX);
        assertEquals(2*3./(8+10), threeShingleDice(str1,str2), Similarity.DELTA_MAX);

        assertEquals(15./18, fourGramOverlap(s1,s2), Similarity.DELTA_MAX);
        assertEquals(3./8, threeShingleOverlap(str1,str2), Similarity.DELTA_MAX);

        assertThat(fourGramOverlap("Here is my go at it:", "Here is my go at it:"), allOf(greaterThanOrEqualTo(0.0),lessThanOrEqualTo(1.0)));

        // "Hallo Welt" -> "Hall", "allo", "llo ", "lo W", "o We", " Wel", "Welt"
        // "Hallo Wlt" -> "Hall", "allo", "llo ", "lo W", "o Wl", "o Wlt"
        // -> Union: 9, Intersection: 4 -> 4/9=0.444...
        assertEquals(4.0/9, fourGramJaccard("Hallo Welt", "Hallo Wlt"), Similarity.DELTA_MAX);

        assertThrows(InputTooShortException.class, () -> fourGramSimilarityKondrak05("ab", ""));
        assertThrows(InputTooShortException.class, () -> fourGramSimilarityKondrak05("", "ab"));

        // taken from Stack Overflow answer 34779037, versions 2 and 3
        String tmp1 = "Note that you need to *comma-separate* them.";
        String tmp2 = "I'm pretty sure that the `application/x-unknown` is the one that would work for you since this is what Firefox itself determines as your file's mime-type.";
        assertTrue(MathUtils.greaterThan(fiveGramDice(tmp1, tmp2), 0.04));
    }

    @Test
    void testNGramSimilarityKondrak05(){
        // ("hall", "allo", "llow", "lowe", "lowo")
        // str1: "hallowe" -> "hall", "allo", "llow", "lowe" -> (1, 1, 1, 1, 0)
        // str2: "hallowo" -> "hall", "allo", "llow", "lowo" -> (1, 1, 1, 0, 1)
        // manhattan = |1-1| + |1-1| + |1-1| + |1-0| + |0-1| = 2
        // length1 = 4
        // length2 = 4
        // sim = 1 - 2/(4+4) = 1 - 1/4 = 0.75

        Assertions.assertEquals(1 - 1.0/4, Variants.manhattanFourGramNormalized("hallowe", "hallowo"), Similarity.DELTA_MAX);

        assertThrows(InputTooShortException.class, () -> fourGramSimilarityKondrak05("ab", ""));
        assertThrows(InputTooShortException.class, () -> fourGramSimilarityKondrak05("", "ab"));

        assertEquals(0.8659420013427734, fourGramSimilarityKondrak05(str1, str2), Similarity.DELTA_MAX);
        assertEquals(0.8571428656578064, fourGramSimilarityKondrak05(s1, s2), Similarity.DELTA_MAX);
        assertEquals(0.5246478915214539, fourGramSimilarityKondrak05(t1, t2), Similarity.DELTA_MAX);

        assertEquals(1.0, fourGramSimilarityKondrak05(str1, str1), Similarity.DELTA_MAX);
        assertEquals(1.0, fourGramSimilarityKondrak05(str2, str2), Similarity.DELTA_MAX);
        assertEquals(1.0, fourGramSimilarityKondrak05(s1, s1), Similarity.DELTA_MAX);
        assertEquals(1.0, fourGramSimilarityKondrak05(s2, s2), Similarity.DELTA_MAX);
        assertEquals(1.0, fourGramSimilarityKondrak05(t1, t1), Similarity.DELTA_MAX);
        assertEquals(1.0, fourGramSimilarityKondrak05(t2, t2), Similarity.DELTA_MAX);

        assertThrows(InputTooShortException.class, () -> fourGramSimilarityKondrak05("ab", ""));
        assertThrows(InputTooShortException.class, () -> fourGramSimilarityKondrak05("", "ab"));
    }

    // ************************ PROFILE-BASED ************************

    @Test
    void testCosine(){
        // publ ubli blic lics icst cstr stri trin ring ingf ngf( gf(S f(st                               (str stri trin ring ings ngs)
        // publ ubli blic lics icst cstr stri trin ring ingf                ngfu gfun func unc( nc(s c(st (str stri trin ring ings ngs)

        // scalar product:
        // TF: 22
        // BOOL: 13

        // Length string 1:
        // TF: sqrt(25) = 5
        // BOOL: sqrt(16) = 4

        // Length string 2
        // TF: sqrt(28) = 5.292
        // BOOL: sqrt(19) = 4.359

        // cosine similarity
        // TF: 22/(5*sqrt(28)) = 0.831
        // BOOL: 13/(4*sqrt(19)) = 0.746

        Assertions.assertEquals(22/(5*Math.sqrt(28)), Variants.cosineFourGramNormalizedTermFrequency(s1, s2), Similarity.DELTA_MAX);
        Assertions.assertEquals(13/(4*Math.sqrt(19)), Variants.cosineFourGramNormalizedBool(s1, s2), Similarity.DELTA_MAX);

        // check for rounding errors
        Assertions.assertEquals(1.0, Variants.cosineFourGramNormalizedTermFrequency("Please", "Please"), Similarity.DELTA_MAX);

        assertThrows(InputTooShortException.class, () -> Variants.cosineFourGramNormalizedTermFrequency("ab", ""));
        assertThrows(InputTooShortException.class, () -> Variants.cosineFourGramNormalizedTermFrequency("", "ab"));

        // Stack Overflow question 38463455, version 3 compared to 4
        assertTrue(org.sotorrent.stringsimilarity.profile.Variants.cosineTokenNormalizedTermFrequency("to", "to:") > 0.0);
    }

    @Test
    void testManhattan() {
        // Stack Overflow question 38463455, version 3 compared to 4
        assertThrows(InputTooShortException.class, () -> org.sotorrent.stringsimilarity.profile.Variants.manhattanFourGramNormalized("to", "to:"));
    }


    // ************************ FINGERPRINT-BASED ************************

    @Test
    void testWinnowing() {
        // distinct fingerprints for s1: [1052657, 1275113, 1036785]
        // distinct fingerprints for s2: [         1275113, 1036785, 1055071]
        // intersection: [1275113, 1036785]
        // dice: 2*2 / (3+3) = 4/6 = 0.66...

        Assertions.assertEquals(4.0 / 6, org.sotorrent.stringsimilarity.fingerprint.Variants.winnowingFourGramDice(s1, s2), Similarity.DELTA_MAX);

        double sim = org.sotorrent.stringsimilarity.fingerprint.Variants.winnowingFourGramDice("public Node(int n)", "public Node(int v)");
        Assertions.assertEquals(1.0, sim, Similarity.DELTA_MAX);

        assertThrows(InputTooShortException.class, () -> org.sotorrent.stringsimilarity.fingerprint.Variants.winnowingFourGramDice("ab", ""));
        assertThrows(InputTooShortException.class, () -> org.sotorrent.stringsimilarity.fingerprint.Variants.winnowingFourGramDice("", "ab"));

        // Stack Overflow question 38463455, version 3 compared to 4
        // input too short for default window size
        assertThrows(InputTooShortException.class, () -> org.sotorrent.stringsimilarity.fingerprint.Variants.winnowingFourGramDiceNormalized("a = [1,2,3]", "a = [1,2,20,5,99,70,35]"));
    }
}
