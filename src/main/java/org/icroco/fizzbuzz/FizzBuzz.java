package org.icroco.fizzbuzz;

import java.util.stream.IntStream;

import static java.lang.System.out;

/**
 * Created by christophe on 20/03/15.
 */
public class FizzBuzz {
    public static void main(String[] args) {

            IntStream.rangeClosed(1, 100)
                    .mapToObj(n -> {
                        if (n % 15 == 0) return "FizzBuzz";
                        else if (n % 3 == 0) return "Fizz";
                        else if (n % 5 == 0) return "Buzz";
                        else return n;
                    }).forEach(out::println);


        IntStream.rangeClosed(1, 100)
                .mapToObj(n -> n % 15 == 0 ? "FizzBuzz" :
                               n % 3 == 0 ? "Fizz" :
                               n % 5 == 0 ? "Buzz" :
                               n)
                .forEach(out::println);
    }

}
