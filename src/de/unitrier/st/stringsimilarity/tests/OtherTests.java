package de.unitrier.st.stringsimilarity.tests;

import org.junit.jupiter.api.Test;

import static de.unitrier.st.stringsimilarity.edit.Variants.*;
import static de.unitrier.st.stringsimilarity.fingerprint.Default.winnowingNGramDice;
import static de.unitrier.st.stringsimilarity.set.Variants.fourGramJaccard;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OtherTests {
    // TODO: merge these tests into other test suites?

    @Test
    void testLevenshteinFunction1(){
        double sim = levenshtein("Hello","Hello");
        assertEquals(1, sim);
    }

    @Test
    void testLevenshteinFunction2(){
        double sim = levenshtein("Hello","Ola");
        assertEquals(0.2, sim);
    }

    @Test
    void testDamerauFunction1(){
        double sim = damerauLevenshtein("Hello","Hello");
        assertEquals(1, sim);
    }

    @Test
    void testDamerauFunction2(){
        double sim = damerauLevenshtein("Hello","Hlelo");
        assertEquals(0.8, sim);
    }

    @Test
    void testLongestCommonSubsequenceFunction(){
        double sim = longestCommonSubsequence("ABCDEFG","ABCDEFHJKL");
        assertEquals(0.6, sim);
    }


    @Test
    void testNGramFunction(){
        double sim = fourGramJaccard(
                "Hallo Welt", // -> "Hall", "allo", "llo ", "lo W", "o We", " Wel", "Welt"
                "Hallo Wlt"); // -> "Hall", "allo", "llo ", "lo W", "o Wl", "o Wlt"
                // -> Union: 9, Intersection: 4 -> 4/9=0.444...
        assertEquals(4.0/9, sim);
    }

    @Test
    void testQGramShingleFunction(){
        double sim = twoShingleFingerprintLongestCommonSubsequence("Hello my world","Hello my beloved world");
        assertEquals(1.0/3, sim);
    }

    @Test
    void testJaccardFunction(){
        String s1 = "Hallo Du";
        String s2 = "Hallo Sie";
        double sim = fourGramJaccard(s1, s2);
        //     "Hall" - "allo" - "llo " - "lo D"  - "o Du" - "lo S" - "o Si" - " Sie"
        // s1:   1         1       1        1          1        0        0        0
        // s2:   1         1       1        0          0        1        1        1

        assertEquals(3.0/8, sim);
    }

    @Test
    void testWinnowingFunction(){
        String s1 = "public Node(int n)";
        String s2 = "public Node(int v)";
        double sim = winnowingNGramDice(s1, s2);
        assertEquals(1, sim);
    }
}
