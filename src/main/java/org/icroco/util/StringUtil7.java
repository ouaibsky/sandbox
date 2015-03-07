package org.icroco.util;


import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by christophe on 07/02/15.
 */
public class StringUtil7 {



    //for lists
    /**
     *  List<String> stringList = Arrays.asList("1", "2", "3");
     *  List<Integer> integerList = convertList(stringList);
     *  List<Double> doubleList = convertList(stringList);
     */
    public static List<Integer> convertIntList(List<String> from) {
        final List<Integer> result = new ArrayList<>();
        for (String s: from) {
            result.add(Integer.parseInt(s));
        }
        return result;
    }

    public static List<Double> convertDoubleList(List<String> from) {
        final List<Double> result = new ArrayList<>();
        for (String s: from) {
            result.add(Double.parseDouble(s));
        }
        return result;
    }

    /**
     *    String[] stringArr = {"1", "2", "3"};
     *    Double[] doubleArr = convertArray(stringArr);
     *    Integer[] intArr   = convertArray(stringArr, Integer::parseInt, Integer[]::new);
     *
     */
    //for arrays
    public static Integer[] convertIntArray(String[] from) {
        return convertIntList(Arrays.asList(from)).toArray(new Integer[0]);
    }

    public static Integer[] convertIntArray(final String aLine) {
        return convertIntArray(aLine.split("\\s+"));
    }

    public static Double[] convertDoubleArray(String[] from) {
        return convertDoubleList(Arrays.asList(from)).toArray(new Double[0]);
    }

    /**
     * Extraire Even Number
     *
     */
    public static List<Integer> extractEven(Collection<Integer> aValues) {
        final List<Integer> evens = new ArrayList<>();
        for (Integer i: aValues) {
            if (i % 2 == 0)
                evens.add(i);
        }
        return evens;
    }

//    public static final String applyFilter(String aValue) {
//        String upper = aValue.chars()
//                .filter(Character::isUpperCase)
//                .mapToObj(c -> Character.toString((char) c))
//                .collect(Collectors.joining());
//        return upper;
//    }

//    public void frequency_of_object_in_collection_java8_lambda(String randomParagraph) {
//
//        List<String> words = Arrays.asList(randomParagraph.split("\\s+"));
//
//        long numberOfOccurences = words
//                .stream()
//                .filter(p -> p.equalsIgnoreCase("me"))
//                .count();
//    }

//    public void splitStringIntoDistinctChar(String randomParagraph) throws IOException {
//        Files.lines(Paths.get("stuff.txt"))
//                .map(line -> line.split("\\s+")) // Stream<String[]>
//                .flatMap(Arrays::stream) // Stream<String>
//                .distinct() // Stream<String>
//                .forEach(System.out::println);
//    }

//    public void numberStatistic() throws IOException {
//        //Get count, min, max, sum, and average for numbers
//        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
//        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
//        System.out.println("Highest prime number in List : " + stats.getMax());
//        System.out.println("Lowest prime number in List : " + stats.getMin());
//        System.out.println("Sum of all prime numbers : " + stats.getSum());
//        System.out.println("Average of all prime numbers : " + stats.getAverage());
//    }

    /**
     * Read a file and return ist of line
     */
    public static List<String> readFile(final URI aInput) throws IOException {
        if (aInput == null)
            throw  new IllegalArgumentException("Input File Not Found: "+aInput);

        final Path path = Paths.get(aInput);
        final List<String> result =  Files.readAllLines(path, Charset.defaultCharset());
        for (String line: result)
            System.err.println(line);

        return result;
    }

}
