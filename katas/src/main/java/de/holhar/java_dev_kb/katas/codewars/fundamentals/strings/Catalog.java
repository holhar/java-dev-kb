package de.holhar.java_dev_kb.katas.codewars.fundamentals.strings;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Catalog (https://www.codewars.com/kata/59d9d8cb27ee005972000045)
 * <p>
 * You are given a small extract of a catalog:
 * <p>
 * s = "<prod><name>drill</name><prx>99</prx><qty>5</qty></prod>
 * <prod><name>hammer</name><prx>10</prx><qty>50</qty></prod>
 * <prod><name>screwdriver</name><prx>5</prx><qty>51</qty></prod>
 * <prod><name>table saw</name><prx>1099.99</prx><qty>5</qty></prod>
 * <prod><name>saw</name><prx>9</prx><qty>10</qty></prod>
 * <p>
 * ...
 * <p>
 * (prx stands for price, qty for quantity) and an article i.e "saw".
 * The function catalog(s, "saw") returns the line(s) corresponding to the article with $ before the prices:
 * "table saw > prx: $1099.99 qty: 5\nsaw > prx: $9 qty: 10\n..."
 * <p>
 * If the article is not in the catalog return "Nothing".
 * <p>
 * Notes
 * <p>
 * There is a blank line between two lines of the catalog.
 * The same article may appear more than once. If that happens return all the lines concerned by the article (in the same order as in the catalog).
 * The line separator of results may depend on the language \nor \r\n.
 * In Pascal \n is replaced by LineEnding.
 * You can see examples in the "Sample tests".
 */
public class Catalog {

    private Catalog() {}

    private static final String PREFIX_PATTERN = "<prod><name>";
    private static final String SUFFIX_PATTERN = "</qty></prod>";
    private static final String NAME_PRICE_DELIMITER_PATTERN = "</name><prx>";
    private static final String PRICE_QTY_DELIMITER_PATTERN = "</prx><qty>";

    public static String catalog(String rows, String article) {
        List<String> lines = Arrays.asList(rows.split("\n"));
        List<String> searchResults = lines.stream()
                .filter(line -> line.contains(article))
                .collect(Collectors.toList());

        if (searchResults.isEmpty()) {
            return "Nothing";
        }

        StringBuilder result = new StringBuilder();

        for (int index = 0; index < searchResults.size(); index++) {

            String product = searchResults.get(index);
            product = product.replace(PREFIX_PATTERN, "");
            product = product.replace(SUFFIX_PATTERN, "");
            product = product.replace(NAME_PRICE_DELIMITER_PATTERN, ",");
            product = product.replace(PRICE_QTY_DELIMITER_PATTERN, ",");

            if (index != 0) {
                result.append("\n");
            }

            result.append(product.substring(0, product.indexOf(",")));
            result.append(" > prx: $");
            result.append(product.substring(product.indexOf(",") + 1, product.lastIndexOf(",")));
            result.append(" qty: ");
            result.append(product.substring(product.lastIndexOf(",") + 1));
        }

        return result.toString();
    }
}
