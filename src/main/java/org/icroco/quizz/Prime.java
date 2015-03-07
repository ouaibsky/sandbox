package org.icroco.quizz;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * Created by christophe on 02/02/15.
 */


public class Prime {

    //checks whether an int is prime or not.
    public static boolean isPrime(long n) {
        //check if n is a multiple of 2
        if (n%2==0)
            return false;
        // if not, then just check the odds
        for(long i=3;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }

    public static boolean isPrimeReverse(long n) {
        //check if n is a multiple of 2
        if (n%2==0)
            return false;
        // if not, then just check the odds
        for(long i=3;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }


    public static List<Long> findPrimeNumber(long aLimit) {
        final List<Long> primes = new ArrayList(10000000);
        LongStream.iterate(2, i -> i + 1).
                filter(i -> {
                    for (long prime : primes)
                        if (i % prime == 0)
                            return false;
                    return true;
                }).limit(aLimit).forEach(primes::add);
        return primes;
    }


    public static void main(String[] args) {
        List<Long> values = findPrimeNumber(10001);

        System.err.println("size: "+values.size());
        System.out.println("10001: "+values.get(values.size()-1));
    }


}
