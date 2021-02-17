package de.holhar.java_dev_kb.katas.codewars.algorithms.strings;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StripCommentsTest {

    @Test
    public void stripComments1() {
        String text = "apples, pears # and bananas\ngrapes\nbananas !apples";
        String[] commentSymbols = new String[] {"#", "!"};

        String actual = StripComments.stripComments(text, commentSymbols);

        assertEquals("apples, pears\ngrapes\nbananas", actual);
    }
    
    @Test
    public void stripComments2() {
        String text = "a #b\nc\nd $e f g";
        String[] commentSymbols = new String[]{"#", "$"};

        String actual = StripComments.stripComments(text, commentSymbols);

        assertEquals("a\nc\nd", actual);
    }

    @Test
    public void stripComments3() throws Exception {
        String text = "d\n\nf\n\nd\n\nb\n\n-ab\n\n-\n\naf\n\n-\n\ncc\n\nf\n\nafbf\n\nd\n\n\n-d\n\nd\n\nd\n\nc\n\n-\nc\n\nbf\n\nccc\n\ndd\n\nfec\n\ne-\n\na\n\n-e\n\nc--\n\n\n\nc\n\nbca-\n\ne\n\n\n\n\ne-\n\nbc\n\n\nd\n\ndccbafde\n\n-\n\n\nd\n\n\n\n\nf\n\nf\n\na\n\nb\n\nbb-df\n\n\nd\nfa-d\n\nb\n\nd\n\naf-\n\n\n\n\nd\n\nf\n\nbe\n\nba\n\nccccd\n\n\n\n\nc\n\nf\n\nefc\n\nb\n\n\nf\n\na\n\naef";
        String[] commentSymbols = {"#", "$", "!", "-"};

        String actual = StripComments.stripComments(text, commentSymbols);

        assertEquals("d\n\nf\n\nd\n\nb\n\n\n\n\n\naf\n\n\n\ncc\n\nf\n\nafbf\n\nd\n\n\n\n\nd\n\nd\n\nc\n\n\nc\n\nbf\n\nccc\n\ndd\n\nfec\n\ne\n\na\n\n\n\nc\n\n\n\nc\n\nbca\n\ne\n\n\n\n\ne\n\nbc\n\n\nd\n\ndccbafde\n\n\n\n\nd\n\n\n\n\nf\n\nf\n\na\n\nb\n\nbb\n\n\nd\nfa\n\nb\n\nd\n\naf\n\n\n\n\nd\n\nf\n\nbe\n\nba\n\nccccd\n\n\n\n\nc\n\nf\n\nefc\n\nb\n\n\nf\n\na\n\naef", actual);
    }

    @Test
    public void stripComments4() {
        String text = "a \nb \nc ";
        String[] commentSymbols = {"#", "$"};

        String actual = StripComments.stripComments(text, commentSymbols);

        assertEquals("a\nb\nc", actual);
    }
}
