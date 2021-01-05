package de.holhar.java_dev_kb.katas.codewars.algorithms.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Sort the columns of a csv-file (https://www.codewars.com/kata/57f7f71a7b992e699400013f)
 * <p>
 * #Sort the columns of a csv-file
 * <p>
 * You get a string with the content of a csv-file. The columns are separated by semicolons.
 * The first line contains the names of the columns.
 * Write a method that sorts the columns by the names of the columns alphabetically and incasesensitive.
 * <p>
 * An example:
 * <p>
 * Before sorting:
 * As table (only visualization):
 * |myjinxin2015|raulbc777|smile67|Dentzil|SteffenVogel_79|
 * |17945       |10091    |10088  |3907   |10132          |
 * |2           |12       |13     |48     |11             |
 * <p>
 * The csv-file:
 * myjinxin2015;raulbc777;smile67;Dentzil;SteffenVogel_79\n
 * 17945;10091;10088;3907;10132\n
 * 2;12;13;48;11
 * <p>
 * ----------------------------------
 * <p>
 * After sorting:
 * As table (only visualization):
 * |Dentzil|myjinxin2015|raulbc777|smile67|SteffenVogel_79|
 * |3907   |17945       |10091    |10088  |10132          |
 * |48     |2           |12       |13     |11             |
 * <p>
 * The csv-file:
 * Dentzil;myjinxin2015;raulbc777;smile67;SteffenVogel_79\n
 * 3907;17945;10091;10088;10132\n
 * 48;2;12;13;11
 * <p>
 * There is no need for prechecks. You will always get a correct string with more than 1 line und more than 1 row.
 * All columns will have different names.
 * <p>
 * Have fun coding it and please don't forget to vote and rank this kata! :-)
 * <p>
 * I have created other katas. Have a look if you like coding and challenges.
 */
public class SortColumnsCsvFile {

    private SortColumnsCsvFile() {}

    private static List<String> allEntries;

    public static String sortCsvColumns(String csvFileContent) {
        allEntries = new ArrayList<>();
        String[] lines = csvFileContent.split("\n");
        int rowCount = lines.length;
        StringBuilder result = new StringBuilder(2000);

        getEntriesAsDataStructure(lines);
        allEntries.sort((o1, o2) -> o1.toLowerCase().compareTo(o2.toLowerCase()));
        transformSortedDataToString(rowCount, result);

        return result.toString().substring(0, result.toString().length() - 1);
    }

    private static void transformSortedDataToString(int rowCount, StringBuilder result) {
        for (int i = 0; i < rowCount; i++) {
            for (String entry : allEntries) {
                String[] entryVals = entry.split(",");
                result.append(entryVals[i]);

                if (entry.equals(allEntries.get(allEntries.size() - 1))) {
                    result.append("\n");
                } else {
                    result.append(";");
                }
            }
        }
    }

    private static void getEntriesAsDataStructure(String[] lines) {
        boolean firstIt = true;
        for (String line : lines) {
            String[] lineEntries = line.split(";");
            for (int i = 0; i < lineEntries.length; i++) {
                if (firstIt) {
                    allEntries.add(lineEntries[i]);
                } else {
                    allEntries.set(i, allEntries.get(i) + "," + lineEntries[i]);
                }

                if (i == lineEntries.length - 1) {
                    firstIt = false;
                }
            }
        }
    }
}
