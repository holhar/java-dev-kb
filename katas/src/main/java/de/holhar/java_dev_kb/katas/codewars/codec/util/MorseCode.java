package de.holhar.java_dev_kb.katas.codewars.codec.util;

public class MorseCode {

    private MorseCode() {}

    public static String get(String morseCode) {
        String letter;
        switch (morseCode) {
            case ".-":
                letter = "A";
                break;
            case "-...":
                letter = "B";
                break;
            case "-.-.":
                letter = "C";
                break;
            case "-..":
                letter = "D";
                break;
            case ".":
                letter = "E";
                break;
            case "..-.":
                letter = "F";
                break;
            case "--.":
                letter = "G";
                break;
            case "....":
                letter = "H";
                break;
            case "..":
                letter = "I";
                break;
            case ".---":
                letter = "J";
                break;
            case "-.-":
                letter = "K";
                break;
            case ".-..":
                letter = "L";
                break;
            case "--":
                letter = "M";
                break;
            case "-.":
                letter = "N";
                break;
            case "---":
                letter = "O";
                break;
            case ".--.":
                letter = "P";
                break;
            case "--.-":
                letter = "Q";
                break;
            case ".-.":
                letter = "R";
                break;
            case "...":
                letter = "S";
                break;
            case "-":
                letter = "T";
                break;
            case "..-":
                letter = "U";
                break;
            case "...-":
                letter = "V";
                break;
            case ".--":
                letter = "W";
                break;
            case "-..-":
                letter = "X";
                break;
            case "-.--":
                letter = "Y";
                break;
            case "--..":
                letter = "Z";
                break;
            case "...---...":
                letter = "SOS";
                break;
            case "-.-.--":
                letter = "!";
                break;
            default:
                letter = ".";
                break;
        }
        return letter;
    }
}
