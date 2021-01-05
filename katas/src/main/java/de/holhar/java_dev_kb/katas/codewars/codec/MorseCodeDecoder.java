package de.holhar.java_dev_kb.katas.codewars.codec;

import de.holhar.java_dev_kb.katas.codewars.codec.util.MorseCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Decode the Morse code, advanced (https://www.codewars.com/kata/54b72c16cd7f5154e9000457)
 * <p>
 * In this kata you have to write a Morse code decoder for wired electrical telegraph.
 * Electric telegraph is operated on a 2-wire line with a key that, when pressed, connects the wires
 * together, which can be detected on a remote station. The Morse code encodes every character being
 * transmitted as a sequence of "dots" (short presses on the key) and "dashes"
 * (long presses on the key).
 * <p>
 * When transmitting the Morse code, the international standard specifies that:
 * "Dot" – is 1 time unit long.
 * "Dash" – is 3 time units long.
 * Pause between dots and dashes in a character – is 1 time unit long.
 * Pause between characters inside a word – is 3 time units long.
 * Pause between words – is 7 time units long.
 * <p>
 * However, the standard does not specify how long that "time unit" is. And in fact different
 * operators would transmit at different speed. An amature person may need a few seconds to transmit
 * a single character, a skilled professional can transmit 60 words per minute, and robotic
 * transmitters may go way faster.
 * <p>
 * For this kata we assume the message receiving is performed automatically by the hardware that
 * checks the line periodically, and if the line is connected (the key at the remote station is
 * down), 1 is recorded, and if the line is not connected (remote key is up), 0 is recorded. After
 * the message is fully received, it gets to you for decoding as a string containing only symbols
 * 0 and 1.
 * <p>
 * For example, the message HEY JUDE, that is ···· · −·−−   ·−−− ··− −·· ·
 * may be received as follows:
 * <p>
 * 1100110011001100000011000000111111001100111111001111110000000000000011001111110011111100111111
 * 000000110011001111110000001111110011001100000011
 * <p>
 * As you may see, this transmission is perfectly accurate according to the standard, and the
 * hardware sampled the line exactly two times per "dot".
 * <p>
 * That said, your task is to implement two functions:
 * <p>
 * Function decodeBits(bits), that should find out the transmission rate of the message, correctly
 * decode the message to dots ., dashes - and spaces (one between characters, three between words)
 * and return those as a string. Note that some extra 0's may naturally occur at the beginning and
 * the end of a message, make sure to ignore them. Also if you have trouble discerning if the
 * particular sequence of 1's is a dot or a dash, assume it's a dot.
 * <p>
 * Function decodeMorse(morseCode), that would take the output of the previous function and return
 * a human-readable string.
 * <p>
 * The Morse code table is preloaded for you as MORSE_CODE dictionary (MorseCode class for Java),
 * feel free to use it.
 * <p>
 * All the test strings would be valid to the point that they could be reliably decoded as
 * described above, so you may skip checking for errors and exceptions, just do your best in
 * figuring out what the message is!
 * <p>
 * TODO: Finish me!
 */
public class MorseCodeDecoder {

    private MorseCodeDecoder() {}

    private static String bitString;
    private static final StringBuilder codeSign = new StringBuilder(1000);
    private static final List<String> codeSigns = new ArrayList<>();
    private static final StringBuilder morseCode = new StringBuilder(1000);

    public static String decodeBits(String bits) {

        bitString = bits;
        trimBitString();
        if (bitString.length() == 0)
            return "";

        char[] bitCharArray = bitString.toCharArray();
        char previousChar = bitCharArray[0];
        codeSign.append(bitCharArray[0]);

        for (int i = 1; i < bitCharArray.length; i++) {
            if (previousChar != bitCharArray[i]) {
                addCodeSign();
            }
            codeSign.append(bitCharArray[i]);
            previousChar = bitCharArray[i];
        }
        if (codeSign.length() != 0) {
            addCodeSign();
        }

        retrieveMorseCode();

        String result = morseCode.toString();
        clearStaticVars();
        return result;
    }

    public static String decodeMorse(String morseCode) {
        StringBuilder result = new StringBuilder(100);
        String[] words = morseCode.split("\\s{3}");
        String[] signals;
        for (String word : words) {
            signals = word.split("\\s{1}");
            for (String s : signals) {
                result.append(MorseCode.get(s.replaceAll("\\s+", "")));
            }
            if (!word.equals(words[words.length - 1])) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    private static void trimBitString() {
        int codeStart = bitString.indexOf('1');
        int codeEnd = bitString.lastIndexOf('1') + 1;
        bitString = bitString.substring(codeStart, codeEnd);
    }

    private static void addCodeSign() {
        codeSigns.add(codeSign.toString());
        codeSign.setLength(0);
    }

    // using an enum would be nicer
    private static void retrieveMorseCode() {
        for (String sign : codeSigns) {
            if (sign.equals("111111")) {
                morseCode.append("-");
            } else if (sign.contains("1")) {
                morseCode.append(".");
            } else if (sign.contains("0") && sign.length() <= 2) {
                morseCode.append("");
            } else if (sign.contains("0") && sign.length() > 2 && sign.length() <= 13) {
                morseCode.append(" ");
            } else if (sign.contains("0") && sign.length() > 13) {
                morseCode.append("   ");
            }
        }
    }

    private static void clearStaticVars() {
        bitString = "";
        codeSign.setLength(0);
        codeSigns.clear();
        morseCode.setLength(0);
    }
}
