package de.holhar.java_dev_kb.katas.codewars.algorithms.strings;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StripCommentsTest {

    @Test
    public void stripComments1() throws Exception {
        String text = "apples, pears # and bananas\ngrapes\nbananas !apples";
        String[] symbols = new String[]{"#", "!"};
        String actual = StripComments.stripComments(text, symbols);

        String expected = "apples, pears\ngrapes\nbananas";
        assertEquals("\nexpected:\n'" + expected + "'\nactual:\n'" + actual + "'", expected, actual);
    }

    @Test
    public void stripComments2() throws Exception {
        String text = "a #b\nc\nd $e f g";
        String[] symbols = new String[]{"#", "$"};
        String actual = StripComments.stripComments(text, symbols);

        String expected = "a\nc\nd";
        assertEquals("\nexpected:\n'" + expected + "'\nactual:\n'" + actual + "'", expected, actual);
    }

    @Test
    public void stripComments3() throws Exception {
        String text = "";
        String[] symbols = new String[]{"#", "$", "!", "-"};
        String actual = StripComments.stripComments(text, symbols);

        String expected = "";
        assertEquals("\nexpected:\n'" + expected + "'\nactual:\n'" + actual + "'", expected, actual);
    }

    @Test
    public void stripComments4() throws Exception {
        String text = "a \n b \nc ";
        String[] symbols = new String[]{"#", "$"};
        String actual = StripComments.stripComments(text, symbols);

        String expected = "a\n b\nc";
        assertEquals("\nexpected:\n'" + expected + "'\nactual:\n'" + actual + "'", expected, actual);
    }

    @Test
    public void stripComments5() throws Exception {
        String text = "            ";
        String[] symbols = new String[]{"#"};
        String actual = StripComments.stripComments(text, symbols);

        String expected = "";
        assertEquals("\nexpected:\n'" + expected + "'\nactual:\n'" + actual + "'", expected, actual);
    }

    @Test
    public void stripComments6() throws Exception {
        String text = "\n" +
                "\n" +
                "e\n" +
                "\n" +
                "f\n" +
                "\n" +
                "\n" +
                "fd$e$\n" +
                "\n" +
                "ff\n" +
                "\n" +
                "aac\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "e\n" +
                "\n" +
                "ece\n" +
                "\n" +
                "d\n" +
                "\n" +
                "abfaa$\n" +
                "\n" +
                "e\n" +
                "\n" +
                "edd\n" +
                "\n" +
                "b\n" +
                "\n" +
                "d\n" +
                "\n" +
                "f\n" +
                "\n" +
                "d\n" +
                "\n" +
                "dcfc$\n" +
                "\n" +
                "af\n" +
                "\n" +
                "b\n" +
                "\n" +
                "f\n" +
                "\n" +
                "bafca$\n" +
                "\n" +
                "c\n" +
                "\n" +
                "\n" +
                "b\n" +
                "\n" +
                "b\n" +
                "\n" +
                "a$b\n" +
                "\n" +
                "f\n" +
                "\n" +
                "a\n" +
                "\n" +
                "dcb\n" +
                "\n" +
                "b\n" +
                "\n" +
                "b\n" +
                "\n" +
                "fa\n" +
                "\n" +
                "ceb\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "e\n" +
                "\n" +
                "\n" +
                "f\n" +
                "\n" +
                "bd\n" +
                "\n" +
                "d\n" +
                "\n" +
                "f\n" +
                "\n" +
                "a\n" +
                "\n" +
                "ae\n" +
                "\n" +
                "ef\n" +
                "\n" +
                "fa\n" +
                "\n" +
                "$\n" +
                "\n" +
                "d\n" +
                "ab\n" +
                "\n" +
                "ae\n" +
                "\n" +
                "b\n" +
                "\n" +
                "c\n" +
                "\n" +
                "c\n" +
                "\n" +
                "c\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "d\n" +
                "\n" +
                "a\n" +
                "\n" +
                "$\n" +
                "\n" +
                "de\n" +
                "\n" +
                "a\n" +
                "\n" +
                "e\n" +
                "\n" +
                "d\n" +
                "\n" +
                "$\n" +
                "\n" +
                "\n" +
                "fa\n" +
                "\n" +
                "a\n" +
                "\n" +
                "c\n" +
                "\n" +
                "$\n" +
                "\n" +
                "$$f$$\n" +
                "\n" +
                "da\n" +
                "\n" +
                "e\n" +
                "\n" +
                "\n" +
                "ad\n" +
                "\n" +
                "a";
        String[] symbols = new String[]{"#", "$", "!", "-"};
        String actual = StripComments.stripComments(text, symbols);

        String expected = "\n" +
                "\n" +
                "e\n" +
                "\n" +
                "f\n" +
                "\n" +
                "\n" +
                "fd\n" +
                "\n" +
                "ff\n" +
                "\n" +
                "aac\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "e\n" +
                "\n" +
                "ece\n" +
                "\n" +
                "d\n" +
                "\n" +
                "abfaa\n" +
                "\n" +
                "e\n" +
                "\n" +
                "edd\n" +
                "\n" +
                "b\n" +
                "\n" +
                "d\n" +
                "\n" +
                "f\n" +
                "\n" +
                "d\n" +
                "\n" +
                "dcfc\n" +
                "\n" +
                "af\n" +
                "\n" +
                "b\n" +
                "\n" +
                "f\n" +
                "\n" +
                "bafca\n" +
                "\n" +
                "c\n" +
                "\n" +
                "\n" +
                "b\n" +
                "\n" +
                "b\n" +
                "\n" +
                "a\n" +
                "\n" +
                "f\n" +
                "\n" +
                "a\n" +
                "\n" +
                "dcb\n" +
                "\n" +
                "b\n" +
                "\n" +
                "b\n" +
                "\n" +
                "fa\n" +
                "\n" +
                "ceb\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "e\n" +
                "\n" +
                "\n" +
                "f\n" +
                "\n" +
                "bd\n" +
                "\n" +
                "d\n" +
                "\n" +
                "f\n" +
                "\n" +
                "a\n" +
                "\n" +
                "ae\n" +
                "\n" +
                "ef\n" +
                "\n" +
                "fa\n" +
                "\n" +
                "\n" +
                "\n" +
                "d\n" +
                "ab\n" +
                "\n" +
                "ae\n" +
                "\n" +
                "b\n" +
                "\n" +
                "c\n" +
                "\n" +
                "c\n" +
                "\n" +
                "c\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "d\n" +
                "\n" +
                "a\n" +
                "\n" +
                "\n" +
                "\n" +
                "de\n" +
                "\n" +
                "a\n" +
                "\n" +
                "e\n" +
                "\n" +
                "d\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "fa\n" +
                "\n" +
                "a\n" +
                "\n" +
                "c\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "da\n" +
                "\n" +
                "e\n" +
                "\n" +
                "\n" +
                "ad\n" +
                "\n" +
                "a";
        assertEquals("\nexpected:\n'" + expected + "'\nactual:\n'" + actual + "'", expected, actual);
    }

    @Test
    public void stripComments7() throws Exception {
        String text = "d\n" +
                "\n" +
                "d\n" +
                "\n" +
                "\n" +
                "adc\n" +
                "\n" +
                "b\n" +
                "\n" +
                "!\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "d\n" +
                "\n" +
                "a\n" +
                "\n" +
                "c\n" +
                "b\n" +
                "\n" +
                "aa\n" +
                "\n" +
                "a\n" +
                "b\n" +
                "\n" +
                "e\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "d\n" +
                "\n" +
                "[\n" +
                "\n" +
                "b\n" +
                "\n" +
                "\n" +
                "fa\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "fe\n" +
                "\n" +
                "f\n" +
                "\n" +
                "\n" +
                "eac\n" +
                "\n" +
                "a\n" +
                "\n" +
                "e\n" +
                "\n" +
                "b\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "d\n" +
                "\n" +
                "\n" +
                "\n" +
                "adc\n" +
                "\n" +
                "f\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "eaf\n" +
                "\n" +
                "ab\n" +
                "\n" +
                "a\n" +
                "\n" +
                "\n" +
                "a\n" +
                "\n" +
                "\n" +
                "c\n" +
                "\n" +
                "fe\n" +
                "\n" +
                "bbe\n" +
                "\n" +
                "\n" +
                "c\n" +
                "\n" +
                "c\n" +
                "\n" +
                "\n" +
                "\n" +
                "db\n" +
                "\n" +
                "ca\n" +
                "\n" +
                "eaeda\n" +
                "\n" +
                "cffdf\n" +
                "\n" +
                "\n" +
                "a\n" +
                "\n" +
                "b\n" +
                "\n" +
                "e\n" +
                "\n" +
                "c\n" +
                "\n" +
                "ccb\n" +
                "\n" +
                "cfdf\n" +
                "\n" +
                "e\n" +
                "\n" +
                "cb\n" +
                "\n" +
                "a]\n" +
                "\n" +
                "\n" +
                "f\n" +
                "b\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "c";
        String[] symbols = new String[]{"#", "$", "!", "-"};
        String actual = StripComments.stripComments(text, symbols);

        String expected = "d\n" +
                "\n" +
                "d\n" +
                "\n" +
                "\n" +
                "adc\n" +
                "\n" +
                "b\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "d\n" +
                "\n" +
                "a\n" +
                "\n" +
                "c\n" +
                "b\n" +
                "\n" +
                "aa\n" +
                "\n" +
                "a\n" +
                "b\n" +
                "\n" +
                "e\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "d\n" +
                "\n" +
                "[\n" +
                "\n" +
                "b\n" +
                "\n" +
                "\n" +
                "fa\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "fe\n" +
                "\n" +
                "f\n" +
                "\n" +
                "\n" +
                "eac\n" +
                "\n" +
                "a\n" +
                "\n" +
                "e\n" +
                "\n" +
                "b\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "d\n" +
                "\n" +
                "\n" +
                "\n" +
                "adc\n" +
                "\n" +
                "f\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "eaf\n" +
                "\n" +
                "ab\n" +
                "\n" +
                "a\n" +
                "\n" +
                "\n" +
                "a\n" +
                "\n" +
                "\n" +
                "c\n" +
                "\n" +
                "fe\n" +
                "\n" +
                "bbe\n" +
                "\n" +
                "\n" +
                "c\n" +
                "\n" +
                "c\n" +
                "\n" +
                "\n" +
                "\n" +
                "db\n" +
                "\n" +
                "ca\n" +
                "\n" +
                "eaeda\n" +
                "\n" +
                "cffdf\n" +
                "\n" +
                "\n" +
                "a\n" +
                "\n" +
                "b\n" +
                "\n" +
                "e\n" +
                "\n" +
                "c\n" +
                "\n" +
                "ccb\n" +
                "\n" +
                "cfdf\n" +
                "\n" +
                "e\n" +
                "\n" +
                "cb\n" +
                "\n" +
                "a]\n" +
                "\n" +
                "\n" +
                "f\n" +
                "b\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "c";
        assertEquals("\nexpected:\n'" + expected + "'\nactual:\n'" + actual + "'", expected, actual);
    }
}
