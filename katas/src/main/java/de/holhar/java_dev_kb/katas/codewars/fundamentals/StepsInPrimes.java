package de.holhar.java_dev_kb.katas.codewars.fundamentals;

import java.util.LinkedList;
import java.util.List;

/**
 * Steps in Primes (https://www.codewars.com/kata/5613d06cee1e7da6d5000055)
 * <p>
 * The prime numbers are not regularly spaced. For example from 2 to 3 the step is 1. From 3 to 5 the step is 2. From
 * 7 to 11 it is 4. Between 2 and 50 we have the following pairs of 2-steps primes:
 * <p>
 * 3, 5 - 5, 7, - 11, 13, - 17, 19, - 29, 31, - 41, 43
 * <p>
 * We will write a function step with parameters:
 * <p>
 * g (integer >= 2) which indicates the step we are looking for,
 * <p>
 * m (integer >= 2) which gives the start of the search (m inclusive),
 * <p>
 * n (integer >= m) which gives the end of the search (n inclusive)
 * <p>
 * In the example above step(2, 2, 50) will return [3, 5] which is the first pair between 2 and 50 with a 2-steps.
 * <p>
 * So this function should return the first pair of the two prime numbers spaced with a step of g between the limits
 * m, n if these g-steps prime numbers exist otherwise nil or null or None or Nothing
 * or [] or "0, 0" or {0, 0} or 0 0(depending on the language).
 * <p>
 * Examples:
 * <p>
 * step(2, 5, 7) --> [5, 7] or (5, 7) or {5, 7} or "5 7"
 * <p>
 * step(2, 5, 5) --> nil or ... or [] in Ocaml or {0, 0} in C++
 * <p>
 * step(4, 130, 200) --> [163, 167] or (163, 167) or {163, 167}
 * <p>
 * See more examples for your language in "TESTS"
 * Remarks:
 * <p>
 * ([193, 197] is also such a 4-steps primes between 130 and 200 but it's not the first pair).
 * <p>
 * step(6, 100, 110) --> [101, 107] though there is a prime between 101 and 107 which is 103; the pair 101-103 is a 2-step.
 * Notes:
 * <p>
 * The idea of "step" is close to that of "gap" but it is not exactly the same. For those interested they can have a
 * look at http://mathworld.wolfram.com/PrimeGaps.html.
 * <p>
 * A "gap" is more restrictive: there must be no primes in between (101-107 is a "step" but not a "gap".
 * Next kata will be about "gaps":-).
 * <p>
 * For Go: nil slice is expected when there are no step between m and n. Example: step(2,4900,4919) --> nil
 */
public class StepsInPrimes {

    private StepsInPrimes() {}

    public static long[] step(int g, long m, long n) {
        if (isInvalidInput(g, m, n))
            return new long[0];

        List<Long> primes = getPrimes(m, n);
        long[] result = getStep(g, primes);

        return isResultNotEmpty(result) ? result : null;
    }

    private static boolean isInvalidInput(int g, long m, long n) {
        return m > n || g > (n - m);
    }

    private static List<Long> getPrimes(long m, long n) {
        List<Long> primes = new LinkedList<>();
        if (m == 2) {
            primes.add(2L);
        }

        long start = m % 2 == 0 ? m + 1 : m;
        for (long i = start; i <= n; i += 2) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

    private static boolean isPrime(long number) {
        for (long i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static long[] getStep(int g, List<Long> primes) {

        long[] result = new long[2];
        long temp;

        for (Long p : primes) {
            temp = p;
            for (long i : primes) {
                if (i <= p)
                    continue;
                if (temp + g == i) {
                    result[0] = temp;
                    result[1] = i;
                    break;
                }
            }
            if (isResultNotEmpty(result))
                break;
        }
        return result;
    }

    private static boolean isResultNotEmpty(long[] result) {
        return result[0] != 0;
    }
}
