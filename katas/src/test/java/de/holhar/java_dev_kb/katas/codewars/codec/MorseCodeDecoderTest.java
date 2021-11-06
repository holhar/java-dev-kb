package de.holhar.java_dev_kb.katas.codewars.codec;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Disabled
class MorseCodeDecoderTest {
    
    @Test
    void testExampleFromDescription() throws Exception {
        assertThat(MorseCodeDecoder.decodeMorse(MorseCodeDecoder.decodeBits("000110011001100110000001100000011111100110011111100111111000000000000001100111111001111110011111100000011001100111111000000111111001100110000001100")), is("HEY JUDE"));
    }

    @Test
    void testMorseCodeDecoder_basicBitDecoding1() throws Exception {
        assertThat(MorseCodeDecoder.decodeMorse(MorseCodeDecoder.decodeBits("10001")), is("EE"));
    }

    @Test
    void testMorseCodeDecoder_basicBitDecoding2() throws Exception {
        assertThat(MorseCodeDecoder.decodeMorse(MorseCodeDecoder.decodeBits("101")), is("I"));
    }

    // does not make any sense at all...
    @Test
    void testMorseCodeDecoder_testLongMessageHandling() throws Exception {
        assertThat(MorseCodeDecoder.decodeMorse(MorseCodeDecoder.decodeBits("00011100010101010001000000011101110101110001010111000101000111010111010001110101110000000111010101000101110100011101110111000101110111000111010000000101011101000111011101110001110101011100000001011101110111000101011100011101110001011101110100010101000000011101110111000101010111000100010111010000000111000101010100010000000101110101000101110001110111010100011101011101110000000111010100011101110111000111011101000101110101110101110")), is("THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG."));
    }

    @Test
    void testMorseCodeDecoder_testMultipleBitsPerDotHandling1() throws Exception {
        assertThat(MorseCodeDecoder.decodeMorse(MorseCodeDecoder.decodeBits("111000000000111")), is("EE"));
    }

    // contradics to testMorseCodeDecoder_basicBitDecoding1
    @Test
    void testMorseCodeDecoder_testMultipleBitsPerDotHandling2() throws Exception {
        assertThat(MorseCodeDecoder.decodeMorse(MorseCodeDecoder.decodeBits("111000111")), is("I"));
    }
}