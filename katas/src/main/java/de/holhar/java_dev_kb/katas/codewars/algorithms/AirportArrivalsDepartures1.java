package de.holhar.java_dev_kb.katas.codewars.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Airport Arrivals/Departures - #1 (https://www.codewars.com/kata/57feb00f08d102352400026e)
 * <p>
 * You are at the airport staring blankly at the arrivals/departures flap display...
 * How it works
 * <p>
 * You notice that each flap character is on some kind of a rotor and the order of characters on each rotor is:
 * <p>
 * ABCDEFGHIJKLMNOPQRSTUVWXYZ ?!@#&()|<>.:=-+/*0123456789
 * <p>
 * And after a long while you deduce that the display works like this:
 * <p>
 * Starting from the left, all rotors (from the current one to the end of the line) flap together until the left-most rotor character is correct.
 * <p>
 * Then the mechanism advances by 1 rotor to the right...
 * <p>
 * ...REPEAT this rotor procedure until the whole line is updated
 * <p>
 * ...REPEAT this line procedure from top to bottom until the whole display is updated
 * <p>
 * Example
 * <p>
 * Consider a flap display with 3 rotors and one 1 line which currently spells CAT
 */
public class AirportArrivalsDepartures1 {

    private AirportArrivalsDepartures1() {}

    private static final String ALPHABET = Preloaded.ALPHABET.value;
    private static final char[] alphabetCharArray = ALPHABET.toCharArray();

    public static String[] flapDisplay(final String[] lines, final int[][] rotors) {

        List<String> resultWordList = new ArrayList<>();
        StringBuilder wordSb = new StringBuilder();
        char[] charArray;
        int lineCounter = 0;
        int letterCounter = 0;

        for (String line : lines) {

            int flipCount = 0;
            charArray = line.toCharArray();
            for (char c : charArray) {

                int startIndex = ALPHABET.indexOf(c);
                flipCount += rotors[lineCounter][letterCounter];
                int targetIndex = startIndex + flipCount;

                while (targetIndex > alphabetCharArray.length - 1) {
                    targetIndex = targetIndex - alphabetCharArray.length;
                }
                wordSb.append(alphabetCharArray[targetIndex]);

                if (letterCounter == charArray.length - 1) {
                    letterCounter = 0;
                    flipCount = 0;
                } else {
                    letterCounter++;
                }
            }
            resultWordList.add(wordSb.toString());
            wordSb.setLength(0);
            lineCounter++;
        }

        String[] resultWordArray = new String[resultWordList.size()];
        resultWordArray = resultWordList.toArray(resultWordArray);

        return resultWordArray;
    }
}
