package de.unitrier.st.stringsimilarity.tests;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static de.unitrier.st.stringsimilarity.Similarity.DELTA_MAX;
import static de.unitrier.st.stringsimilarity.Tokenization.NGRAM_SIZE;
import static de.unitrier.st.stringsimilarity.Tokenization.nGramList;
import static de.unitrier.st.stringsimilarity.edit.Variants.*;
import static de.unitrier.st.stringsimilarity.fingerprint.Base.*;
import static de.unitrier.st.stringsimilarity.fingerprint.Default.winnowingNGramDice;
import static de.unitrier.st.stringsimilarity.profile.Default.*;
import static de.unitrier.st.stringsimilarity.set.Default.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SimilarityTest {

    // TODO: Write test cases for all variants

    private static final String str1 = "Please divide this Sentence, into Tokens or nGrams or Shingles";
    private static final String str2 = "Please do not divide this sentence, into Tokens or nGrams or shingles";

    private String s1 = "public String f(String s)";
    private String s2 = "public String func(String s)";

    private String t1 = "with Hash table entries Hash table entries has Arun name is here, Arun name is here with Hash table entries Arun how is arun";
    private String t2 = "Hash table entries has Arun name is here, Arun name is here with Hash table entries Arun how is arun Arun name is here with Hash table entries";

    // ************************ EDIT-BASED
    @Test
    void testLevenshtein() {
        assertEquals(1.0, levenshtein("", ""), DELTA_MAX);
        // TODO: update expected value (use fraction)
        //assertEquals(0.8985, levenshtein(normalizeForEdit(str1), normalizeForEdit(str2)), DELTA_MAX);
        assertEquals(0.5, levenshtein("paul", "pual"), DELTA_MAX);
    }

    @Test
    void testDamerauLevenshtein() {
        assertEquals(1.0, damerauLevenshtein("", ""), DELTA_MAX);
        // TODO: update expected value (use fraction)
        //assertEquals(0.8985, damerauLevenshtein(normalizeForEdit(str1), normalizeForEdit(str2)), DELTA_MAX);
        assertEquals(0.75, damerauLevenshtein("paul", "pual"), DELTA_MAX);
    }

    // ************************ SET-BASED
    @Test
    void testSetBased(){
        assertEquals(15./24, nGramJaccard(s1,s2), DELTA_MAX);
        assertEquals(3./15, nShingleJaccard(str1,str2), DELTA_MAX);

        assertEquals(2*15./(18+21), nGramDice(s1,s2), DELTA_MAX);
        assertEquals(2*3./(8+10), nShingleDice(str1,str2), DELTA_MAX);

        assertEquals(15./18, nGramOverlap(s1,s2), DELTA_MAX);
        assertEquals(3./8, nShingleOverlap(str1,str2), DELTA_MAX);
    }

    @Test
    void testCosine(){

        // publ ubli blic lics icst cstr stri trin ring ingf ngf( gf(S f(st (str stri trin ring ings ngs)
        // publ ubli blic lics icst cstr stri trin ring ingf ngfu gfun func unc( nc(s c(st (str stri trin ring ings ngs)

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

        assertEquals(22/(5*Math.sqrt(28)), cosineNGramNormalizedTermFrequency(s1, s2), DELTA_MAX);
        assertEquals(13/(4*Math.sqrt(19)), cosineNGramNormalizedBool(s1, s2), DELTA_MAX);

        // check for rounding errors
        assertEquals(1.0, cosineNGramNormalizedTermFrequency("Please", "Please"));
    }

    @Test
    void testQGram() {
        // ("hall", "allo", "llow", "lowe", "lowo")
        // str1: "hallowe" -> "hall", "allo", "llow", "lowe" -> (1, 1, 1, 1, 0)
        // str2: "hallowo" -> "hall", "allo", "llow", "lowo" -> (1, 1, 1, 0, 1)
        // manhattan = |1-1| + |1-1| + |1-1| + |1-0| + |0-1| = 2
        // length1 = 4
        // length2 = 4
        // sim = 1 - 2/(4+4) = 1 - 1/4 = 0.75

        assertEquals(1 - 1.0/4, manhattanNGramNormalized("hallowe", "hallowo"), DELTA_MAX);
    }

    @Test
    void testLCS(){
        // str1 is  "Please divide this Sentence, into Tokens or nGrams or Shingles"            => 62 Characters
        // str2 is  "Please do not divide this sentence, into Tokens or nGrams or shingles"     => 69 Characters
        // LCS is   "Please divide this entence, into Tokens or nGrams or hingles"              => 60 Characters

        assertEquals(60.0/69, longestCommonSubsequence(str1,str2), DELTA_MAX);
    }


    @Test
    void testWinnowing(){
        List<List<Integer>> completeFingerprintsS1 = completeFingerprintList(nGramList(s1, NGRAM_SIZE),
                getWindowSize(NGRAM_SIZE, GUARANTEE_THRESHOLD));
        List<List<Integer>> completeFingerprintsS2 = completeFingerprintList(nGramList(s2, NGRAM_SIZE),
                getWindowSize(NGRAM_SIZE, GUARANTEE_THRESHOLD));

        List<Integer> fingerprintsS1 = fingerprintList(nGramList(s1, NGRAM_SIZE),
                getWindowSize(NGRAM_SIZE, GUARANTEE_THRESHOLD));
        List<Integer> fingerprintsS2 = fingerprintList(nGramList(s2, NGRAM_SIZE),
                getWindowSize(NGRAM_SIZE, GUARANTEE_THRESHOLD));

        for (int i=0; i<completeFingerprintsS1.size(); i++) {
            assertEquals(Collections.min(completeFingerprintsS1.get(i)), fingerprintsS1.get(i));
        }

        for (int i=0; i<completeFingerprintsS2.size(); i++) {
            assertEquals(Collections.min(completeFingerprintsS2.get(i)), fingerprintsS2.get(i));
        }

        //HashSet<Integer> distinctFingerprintsS1 = new HashSet<Integer>(fingerprintsS1);
        //HashSet<Integer> distinctFingerprintsS2 = new HashSet<Integer>(fingerprintsS2);

        // distinct fingerprints for s1: [1052657, 2982750, 1275113, 1036785, 2587768]
        // distinct fingerprints for s2: [         2982750, 1275113, 1036785, 2587768, 1055071]
        // intersection: [2982750, 1275113, 1036785, 2587768]
        // dice: 2*4 / (5+5) = 0.8

        assertEquals(0.8, winnowingNGramDice(s1,s2), DELTA_MAX);
    }

    @Test
    void testNGramSimilarityKondrak05(){
        assertEquals(0.8659420013427734, nGramSimilarityKondrak05(str1, str2), DELTA_MAX);
        assertEquals(0.8571428656578064, nGramSimilarityKondrak05(s1, s2), DELTA_MAX);
        assertEquals(0.5246478915214539, nGramSimilarityKondrak05(t1, t2), DELTA_MAX);

        assertEquals(1.0, nGramSimilarityKondrak05(str1, str1), DELTA_MAX);
        assertEquals(1.0, nGramSimilarityKondrak05(str2, str2), DELTA_MAX);
        assertEquals(1.0, nGramSimilarityKondrak05(s1, s1), DELTA_MAX);
        assertEquals(1.0, nGramSimilarityKondrak05(s2, s2), DELTA_MAX);
        assertEquals(1.0, nGramSimilarityKondrak05(t1, t1), DELTA_MAX);
        assertEquals(1.0, nGramSimilarityKondrak05(t2, t2), DELTA_MAX);
    }

    @Test
    void testOptimalStringAlignment() {
        assertEquals(1.0, optimalAlignment("", ""), DELTA_MAX);
        assertEquals(0.75, optimalAlignment("paul", "pual"), DELTA_MAX);
    }

    @Test
    void testLongestCommonSubsequence() {
        assertEquals(1.0, longestCommonSubsequence("", ""), DELTA_MAX);
        assertEquals(0.75, longestCommonSubsequence("paul", "pual"), DELTA_MAX);
    }
}
