package org.icroco.util;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by christophe on 07/02/15.
 */
public class StringUtil8 {



    //for lists
    /**
     *  List<String> stringList = Arrays.asList("1", "2", "3");
     *  List<Integer> integerList = convertList(stringList, s -> Integer.parseInt(s));
     *  List<Double> doubleList = convertList(stringList, s -> Double.parseDouble(s));
     */
    public static <T, U> List<U> convertList(List<T> from, Function<T, U> func) {
        return from.stream().map(func).collect(Collectors.toList());
    }

    /**
     *    String[] stringArr = {"1", "2", "3"};
     *    Double[] doubleArr = convertArray(stringArr, Double::parseDouble, Double[]::new);
     *    Integer[] intArr   = convertArray(stringArr, Integer::parseInt, Integer[]::new);
     *
     */
    //for arrays
    public static <T, U> U[] convertArray(T[] from, Function<T, U> func, IntFunction<U[]> generator) {
        return Arrays.stream(from).map(func).toArray(generator);
    }

    /**
     * Extraire Even Number
     *
     */
    public static Integer[] extractEven(Collection<Integer> aValues) {
        Stream<Integer> stream = aValues.stream();
        return stream.filter(i -> i % 2 == 0).toArray(Integer[]::new);
    }

    public static final String applyFilter(String aValue) {
        String upper = aValue.chars()
                .filter(Character::isUpperCase)
                .mapToObj(c -> Character.toString((char) c))
                .collect(Collectors.joining());
        return upper;
    }

    public void frequency_of_object_in_collection_java8_lambda(String randomParagraph) {

        List<String> words = Arrays.asList(randomParagraph.split("\\s+"));

        long numberOfOccurences = words
                .stream()
                .filter(p -> p.equalsIgnoreCase("me"))
                .count();
    }

    public void splitStringIntoDistinctChar(String randomParagraph) throws IOException {
        Files.lines(Paths.get("stuff.txt"))
                .map(line -> line.split("\\s+")) // Stream<String[]>
                .flatMap(Arrays::stream) // Stream<String>
                .distinct() // Stream<String>
                .forEach(System.out::println);
    }

    public void numberStatistic() throws IOException {
        //Get count, min, max, sum, and average for numbers
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());
    }

    /**
     * Read a file and return ist of line
     */
    public static List<String> readFile(final URI aInput) throws IOException {
        if (aInput == null)
            throw  new IllegalArgumentException("Input File Not Found: "+aInput);

        Path path = Paths.get(aInput);
        List<String> result =  Files.readAllLines(path);
        result.forEach(s -> System.err.println(s));

        return result;
    }

}
