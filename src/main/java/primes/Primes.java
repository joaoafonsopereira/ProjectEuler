package primes;

import java.util.Iterator;
import java.util.List;

public class Primes {

    public static boolean isMultiple(int x, int y) {
        return (x % y) == 0;
    }

    // TODO: make isMultiple a generic method
    public static boolean isMultiple(long x, long y) {
        return (x % y) == 0;
    }

    public static boolean isPrime(long n) {
        return isPrime(n, 5);
    }

    public static boolean isPrime(long n, List<Long> prevPrimes) {
        for (Long p : prevPrimes) {
            if (isMultiple(n, p))
                return false;
        }
        long last = prevPrimes.size() > 0 ? prevPrimes.get(prevPrimes.size() - 1) : 1;
        return isPrime(n, closest6kMinus1(last));
    }

    public static Iterator<Long> newPrimesIterator() {
        return new PrimesIterator();
    }

    public static long getNthPrime(long n) {
        Iterator<Long> pIt = newPrimesIterator();
        for(int i = 0; i < (n-1); i++) // stop at (n-1)th
            pIt.next();
        return pIt.next();
    }



    // https://en.wikipedia.org/wiki/Primality_test#Pseudocode
    private static boolean isPrime(long n, long init6kMinus1) {
        if (init6kMinus1 <= 5) { // if init6kMinus1 > 5, these tests were already done
            if (n <= 3)
                return n > 1;
            if (isMultiple(n, 2) || isMultiple(n, 3))
                return false;
        }
        long i = init6kMinus1;
        while (i * i <= n) {
            if (isMultiple(n, i) || isMultiple(n, i + 2)) // 6k-1 || 6k+1
                return false;
            i += 6;
        }
        return true;
    }

    // returns m such that m = 6k-1 (for some k) && m <= n
    private static long closest6kMinus1(long n) {
        if (n <= 5)
            return 5;
        while (!isMultiple(n + 1, 6)) {
            n--;
        }
        return n;
    }

}
