package de.unitrier.st.stringsimilarity.tests;

import de.unitrier.st.stringsimilarity.Normalization;
import de.unitrier.st.stringsimilarity.Tokenization;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.tartarus.snowball.SnowballStemmer;
import org.tartarus.snowball.ext.englishStemmer;

import java.util.List;
import java.util.Set;

import static de.unitrier.st.stringsimilarity.Normalization.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NormalizationTest {

    private static final String str = "main(String[] args)  {\n print('Test');\n\texit(0); }";

    @Test
    void testRemoveWhitespaces(){
        String strNorm = removeWhitespaces(str);
        assertEquals("main(String[]args){print('Test');exit(0);}", strNorm);
    }

    @Test
    void testUnifyWhitespaces(){
        String strNorm = unifyWhitespaces(str);
        assertEquals("main(String[] args) { print('Test'); exit(0); }", strNorm);
    }

    @Test
    void testToLowerCase(){
        String strNorm = toLowerCase(str);
        assertEquals("main(string[] args)  {\n print('test');\n\texit(0); }", strNorm);
    }

    @Test
    void testRemoveSpecialChars(){
        String strNorm = removeSpecialChars(str);
        assertEquals("main(String[] args)  \n print('Test')\n\texit(0) ", strNorm);
    }

    @Test
    void testNormalizeForEdit(){
        String strNorm = normalizeForEdit(str);
        assertEquals("main(string[] args) { print('test'); exit(0); }", strNorm);
    }

    @Test
    void testNormalizeForNGram(){
        String strNorm = normalizeForNGram(str);
        assertEquals("main(string[]args)print('test')exit(0)", strNorm);
    }

    @Test
    void testNormalizeForShingle(){
        String strNorm = normalizeForShingle(str);
        assertEquals("main string args print test exit 0 ", strNorm);
    }

    @Test
    void testStemmer() {
        // The text of the input string is from http://9ol.es/porter_js_demo.html
        // The output generated at this site removes special characters and unifies whitespaces. Furthermore, it stemms "are" to "ar":
        // Exampl Do you realli think it is weak that yield to temptat I tell you that there ar terribl temptat which it requir strength strength and courag to yield to Oscar Wild

        // output of stemmer from http://snowballstem.org/demo.html
        // In comparison to the former, this output does not remove special characters and does not unify whitespaces. But it sets characters to lower case. In contrast to the former, it does not stem "are" to "ar".
        // Its output has been adapted as "expected output" in this test method:
        // example:
        //
        // do you realli think it is weak that yield to temptation? i tell you that there are terribl temptat which it requir strength, strength and courag to yield to ~ oscar wild

        String inputString = "Example:\n" +
                "\n" +
                "Do you really think it is weakness that yields to temptation? I tell you that there are terrible temptations which it requires strength, strength and courage to yield to ~ Oscar Wilde\n";

        String expectedOutputString = "example:\n" +
                "\n" +
                "do you realli think it is weak that yield to temptation? i tell you that there are terribl temptat which it requir strength, strength and courag to yield to ~ oscar wild\n";

        List<String> tokens =
                Tokenization.tokens(
                        Normalization.toLowerCase(inputString)
                );
        StringBuilder outputString = new StringBuilder();

        SnowballStemmer snowballStemmer = new englishStemmer();
        for (String token : tokens) {
            snowballStemmer.setCurrent(token);
            snowballStemmer.stem();
            outputString.append(snowballStemmer.getCurrent()).append(" ");
        }

        Assert.assertEquals(expectedOutputString, outputString.toString());
        // TODO: The test case fails because the stemmer does not stem "wilde" to "wild"
    }
}
