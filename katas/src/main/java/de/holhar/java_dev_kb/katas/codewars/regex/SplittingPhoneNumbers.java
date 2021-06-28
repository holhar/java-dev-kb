package de.holhar.java_dev_kb.katas.codewars.regex;

import java.util.regex.Pattern;

/**
 * Regular Expressions (groups): Splitting phone numbers into their separate parts. (https://www.codewars.com/kata/57a492607cb1f315ec0000bb)
 * <p>
 * With regular expressions you can find information and even split the found information into parts. This will be a
 * recognition test, although it is important to know how regular expression groups work.
 * <p>
 * The assignment will be to create a regular expression that will match with (dutch) phone numbers.
 * <p>
 * These phone numbers can be written multiple ways:
 * <p>
 * 0012 34 567890: is how you would dial a number in another nation using the ITU-recommendation (which is the 00
 * or + prefix. Note that countries do not follow these recommendations, as for Japan: 010. This will not be tested)
 * +12 34 567890: a more readable shorter version
 * 034 567890: is how you would dial a number in the same nation
 * 567890: is how you would dial a number in the same area
 * <p>
 * These phone numbers can be split up into a nation code (without prefix), an area code with 2 digits (with the
 * leading 0 if it's at the beginning of the whole number) and the local number.
 * <p>
 * The regular expression should only contain these three caught groups and anything in a format other than shown in
 * the examples above should not result in a match. Especially, consider these additionnal rules:
 * <p>
 * The nation code, area code and local number may never contain a 0 at the start unless it is the prefix for dialing
 * out of the country or it is at the beginning of the area code if there is no country code. Some examples of invalid codes because of this rule:
 * <p>
 * 0001 34 567890
 * +01 34 567890
 * +12 04 567890
 * +12 034 567890
 * +12 34 067895
 * 034 067895
 * 001 567890
 * 098765
 * <p>
 * Good luck.
 * <p>
 * The validation tests will display the number with which it fails, but not the details. Do not hesitate to add your
 * own example tests.
 * <p>
 * TODO finish me!
 */
public class SplittingPhoneNumbers {


    public static Pattern createPattern() {
        return Pattern.compile("^(?!\\s)(?:\\+|00)?(12)?(?:^0|\\s)?(34)?(?:\\s)?(\\d{6})?");
    }
}
