package template;


import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by christophe on 07/02/15.
 */
public class Util {


    //for lists

    /**
     * List<String> stringList = Arrays.asList("1", "2", "3");
     * List<Integer> integerList = convertList(stringList);
     * List<Double> doubleList = convertList(stringList);
     */

    public static List<Integer> convertStringListToToIntList(List<String> from) {
        return convertStringListToToIntList(from, 10);
    }

    public static List<Integer> convertStringListToToIntList(List<String> from, int radix) {
        final List<Integer> result = new ArrayList<>();
        for (String s : from) {
            result.add(Integer.parseInt(s, radix));
        }
        return result;
    }

    public static List<Double> convertDoubleList(List<String> from) {
        final List<Double> result = new ArrayList<>();
        for (String s : from) {
            result.add(Double.parseDouble(s));
        }
        return result;
    }

    /**
     * String[] stringArr = {"1", "2", "3"};
     * Double[] doubleArr = convertArray(stringArr);
     * Integer[] intArr   = convertArray(stringArr, Integer::parseInt, Integer[]::new);
     */
    //for arrays
    public static Integer[] convertStringArrayToIntArray(String[] from) {
        return convertStringListToToIntList(Arrays.asList(from)).toArray(new Integer[0]);
    }

    public static Integer[] convertStringToIntArray(final String aLine) {
        return convertStringArrayToIntArray(aLine.split("\\s+"));
    }

    public static Double[] convertDoubleArray(String[] from) {
        return convertDoubleList(Arrays.asList(from)).toArray(new Double[0]);
    }

    /**
     * Extraire Even Number
     */
    public static List<Integer> extractEven(Collection<Integer> aValues) {
        final List<Integer> evens = new ArrayList<>();
        for (Integer i : aValues) {
            if (i % 2 == 0)
                evens.add(i);
        }
        return evens;
    }


    public static List<Integer> splitAndGetInt(String aLine) {
        final String[] numbers = aLine.split("\\s+");
        final LinkedList<Integer> result = new LinkedList<>();
        for (String s : numbers) {
            result.add(Integer.parseInt(s));
            //            result.add(Integer.parseInt(s, 16)); TODO if octal
        }
        return result;
    }

    /**
     * Read a file and return ist of line
     */
    public static LinkedList<String> readFile(final URI aInput) throws IOException {
        if (aInput == null)
            throw new IllegalArgumentException("Input File Not Found: " + aInput);

        final Path path = Paths.get(aInput);
        final List<String> result = Files.readAllLines(path, Charset.defaultCharset());
//        for (String line: result)
//            System.err.println(line);

        return new LinkedList<>(result);
    }


    public static Integer[] extractFlatMatrix(List<String> aInput, int xSize) {
        Integer[] result = new Integer[xSize * aInput.size()];
        for (int i = 0; i < aInput.size(); i++) {
            String[] numbers = aInput.get(i).split("\\s+");
            for (int j = 0; j < numbers.length; j++) {
                result[i*xSize + j] = Integer.parseInt(numbers[j]);
            }
        }
        return result;
    }

    public static Integer[][] extractMatrix(List<String> aInput, int xSize) {
        Integer[][] result = new Integer[aInput.size()][xSize];

        for (int i = 0; i < aInput.size(); i++) {
            String[] numbers = aInput.get(i).split("\\s+");
            for (int j = 0; j < numbers.length; j++) {
                result[i][j] = Integer.parseInt(numbers[j]);
            }
        }
        return result;
    }

//    public static Integer[] extractCenter(Integer[] matrix, int aRowSize, int aNewRowSize) {
//        Integer [] result = new Integer[aNewRowSize*aNewRowSize];
//
//        int j = 0;
//        for (int i = 0; i < matrix.length ; i++) {
//            if ((i/aRowSize >= aNewRowSize-1) && (i / aRowSize) <= (aRowSize - aNewRowSize) && (i % aRowSize) >= (aNewRowSize-1) && (i % aRowSize) <= (aRowSize - aNewRowSize)) {
//                result[j] = matrix[i];
//                j++;
//            }
//        }
//        return result;
//    }



    public static void printMatrix(Integer[][] matrix, int padding) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.err.printf("%1$-" + padding + "s", matrix[i][j]);
            }
            System.err.println("");
        }
    }

}
