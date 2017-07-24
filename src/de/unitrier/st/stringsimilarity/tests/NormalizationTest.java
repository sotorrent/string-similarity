package de.unitrier.st.stringsimilarity.tests;

import org.junit.jupiter.api.Test;

import static de.unitrier.st.stringsimilarity.Normalization.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NormalizationTest {

    private static final String str = "main(String[] args)  {\n print('Test');\n\texit(0); }";

    @Test
    public void testRemoveWhitespaces(){
        String strNorm = removeWhitespaces(str);
        assertEquals("main(String[]args){print('Test');exit(0);}", strNorm);
    }

    @Test
    public void testUnifyWhitespaces(){
        String strNorm = unifyWhitespaces(str);
        assertEquals("main(String[] args) { print('Test'); exit(0); }", strNorm);
    }

    @Test
    public void testToLowerCase(){
        String strNorm = toLowerCase(str);
        assertEquals("main(string[] args)  {\n print('test');\n\texit(0); }", strNorm);
    }

    @Test
    public void testRemoveSpecialChars(){
        String strNorm = removeSpecialChars(str);
        assertEquals("main(String[] args)  \n print('Test')\n\texit(0) ", strNorm);
    }

    @Test
    public void testNormalizeForEdit(){
        String strNorm = normalizeForEdit(str);
        assertEquals("main(string[] args) { print('test'); exit(0); }", strNorm);
    }

    @Test
    public void testNormalizeForNGram(){
        String strNorm = normalizeForNGram(str);
        assertEquals("main(string[]args)print('test')exit(0)", strNorm);
    }

    @Test
    public void testNormalizeForShingle(){
        String strNorm = normalizeForShingle(str);
        assertEquals("main string args print test exit 0 ", strNorm);
    }
}
