package de.holhar.java_dev_kb.katas.codewars.codec.util;

public enum BitCode {

    DOT("11"),
    DASH("111111"),
    SIGNAL_PAUSE("00"),
    CHARACTER_PAUSE("000000"),
    WORD_PAUSE("00000000000000");

    private String code;

    BitCode(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
