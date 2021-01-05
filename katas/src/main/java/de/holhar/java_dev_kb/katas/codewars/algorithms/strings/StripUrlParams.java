package de.holhar.java_dev_kb.katas.codewars.algorithms.strings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * TODO check if Kata is available in Java sometime
 */
public class StripUrlParams {

    private StripUrlParams() {}

    /*
     * Removes any duplicate query string parameters from the url
     * Removes any query string parameters specified within the 2nd argument (optional array)
     */
    public static String stripUrlParams(String url, String... params) {

        if (!url.contains("?")) {
            return url;
        }

        List<String> urlElements = new LinkedList<>(Arrays.asList(url.split("[?|&]")));
        StringBuilder result = new StringBuilder();
        String baseUrl = urlElements.remove(0);
        result.append(baseUrl).append("?");

        List<String> forbiddenParams = new LinkedList<>(Arrays.asList(params)); // May be an empty List.
        Set<String> presentParams = new HashSet<>();
        String param;

        for (String urlElement : urlElements) {
            param = urlElement.substring(0, urlElement.indexOf('='));
            if (!presentParams.contains(param) && !forbiddenParams.contains(param)) {
                result.append(urlElement).append("&");
            }
            presentParams.add(param);
        }

        String resultStr = result.toString();
        return resultStr.substring(0, resultStr.length() - 1);
    }
}
