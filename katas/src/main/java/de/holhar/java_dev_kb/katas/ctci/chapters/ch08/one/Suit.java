package de.holhar.java_dev_kb.katas.ctci.chapters.ch08.one;

public enum Suit {

    CLUB(0), DIAMOND(1), HEART(2), SPADE(3);

    private final int value;

    private Suit(int v) {
        value = v;
    }

    public int getValue() {
        return value;
    }

    public Suit getSuitFromValue(int v) {
        switch (v) {
            case 0:
                return CLUB;
            case 1:
                return DIAMOND;
            case 2:
                return HEART;
            case 3:
                return SPADE;
            default:
                return null;
        }
    }
}
