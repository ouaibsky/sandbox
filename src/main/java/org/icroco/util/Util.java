package org.icroco.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by christophe on 07/02/15.
 */
public class Util {


    //for lists
    public static <T, U> List<U> convertList(List<T> from, Function<T, U> func){
        return from.stream().map(func).collect(Collectors.toList());
    }

    //for arrays
    public static <T, U> U[] convertArray(T[] from, Function<T, U> func, IntFunction<U[]> generator){
        return Arrays.stream(from).map(func).toArray(generator);
    }

    public static Integer[] extractEven(Collection<Integer> aValues) {
        Stream<Integer> stream = aValues.stream();
        return stream.filter(i -> i % 2 == 0).toArray(Integer[]::new);
    }

    static {
        //for lists
        List<String> stringList = Arrays.asList("1", "2", "3");
        List<Integer> integerList = convertList(stringList, s -> Integer.parseInt(s));
        // Note that s -> Integer.parseInt(s) could be replace with Integer::parseInt

        //for arrays
        String[] stringArr = {"1", "2", "3"};
        Double[] doubleArr = convertArray(stringArr, Double::parseDouble, Double[]::new);
        Integer[] intArr = convertArray(stringArr, Integer::parseInt, Integer[]::new);
    }
}
