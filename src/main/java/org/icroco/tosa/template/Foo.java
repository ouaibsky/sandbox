package org.icroco.tosa.template;

import java.util.*;


/**
 * Created by christophe on 12/03/15.
 */
public class Foo {

    public static void main(String[] args) {
        {
            Arrays.asList("orange", "jaune", "vert", "rose", "bleu", "violet");

            //2,3,4,5,6,7,8,9,10,V,D,R,A.
            //orange puis jaune puis vert puis rose puis bleu puis violet
        }

        {
            String[] SA = "1 2 -3 4".split("\\s+");
            Integer[] IA = new Integer[SA.length];
            for (int i = 0; i < SA.length; i++)
                IA[i] = Integer.parseInt(SA[i]);

            System.out.println(Arrays.toString(IA));
        }
        {

            String[] SA = "a 1 5 b  c".split("\\s+");
            System.out.println(Arrays.toString(SA));
        }

        {
            // concat
            String str = "";
            for (String s : Arrays.asList("a", "b", "c"))
                str += s + " ";
            str = str.trim();
            System.out.println(str);
        }
        {
            LinkedList<String> SL = new LinkedList<>();
        }
        {

            printMatrix(extractMatrix(Arrays.asList("1 2 3", "4 5 6", "7 8 9"), 3), 2);
            System.err.println("****");
            printMatrix(extractFlatMatrix(Arrays.asList("1 2 3", "4 5 6", "7 8 9"), 3), 3, 2);
            System.err.println("****");
        }

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

    public static void printMatrix(Integer[][] matrix, int padding) {
        for (int i = 0; i < matrix.length; i++) {
            StringBuilder b = new StringBuilder();
            for (int j = 0; j < matrix[i].length; j++) {
                b.append(String.format("%1$-" + padding + "s", matrix[i][j]));
            }
            IsoContestBase.localEcho(b.toString());
        }
    }

    public static Integer[] extractFlatMatrix(List<String> aInput, int xSize) {
        Integer[] result = new Integer[xSize * aInput.size()];
        for (int i = 0; i < aInput.size(); i++) {
            String[] numbers = aInput.get(i).split("\\s+");
            for (int j = 0; j < numbers.length; j++) {
                result[i * xSize + j] = Integer.parseInt(numbers[j]);
            }
        }
        return result;
    }

    public static void printMatrix(Integer[] flatMatrix, int aColumnSize, int padding) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < flatMatrix.length; i++) {
            if (i != 0 && i % aColumnSize == 0) {
                IsoContestBase.localEcho(b.toString());
                b = new StringBuilder();
            }
            b.append(String.format("%1$-" + padding + "s", flatMatrix[i]));
        }
        IsoContestBase.localEcho(b.toString());
    }

}
