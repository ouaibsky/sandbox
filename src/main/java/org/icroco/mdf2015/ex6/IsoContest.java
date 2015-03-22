package org.icroco.mdf2015.ex6;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


/**
 * imp
 * Created by christophe on 07/03/15.
 */
public class IsoContest {
    public static void main(String[] argv) throws Exception {
        String line;
        Scanner sc = new Scanner(System.in);

        LinkedList<String> input = new LinkedList<>();

        while(sc.hasNextLine())
            input.add(sc.nextLine());

        List<String> output = getSolution(new LinkedList<String>((input)));

        for (String s: output)
            System.out.println(s);
    }

    static List<String> getSolution(LinkedList<String> aInput) {
        List<String> output = new ArrayList<>();

        int size = Integer.parseInt(aInput.removeFirst().split("\\s")[1]);

        Integer[][] matrix = extractMatrix(aInput, size);


        //printMatrix(matrix, 3);
        int maxPrf = 0;


        getSpiralArray(matrix);

        printMatrix(matrix, 3);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > maxPrf)
                    maxPrf = matrix[i][j];
            }
        }


        output.add(""+maxPrf);
        return output;
    }

    public static Integer[][] getSpiralArray(Integer[][] spiralArray) {

        if (spiralArray.length == 0 || spiralArray[0].length == 0)
            return spiralArray;

        int numConcentricSquaresRow = (int) Math.ceil((spiralArray.length) / 2.0);
        int numConcentricSquaresCol = (int) Math.ceil((spiralArray[0].length) / 2.0);
        int numConcentricSquares = Math.min(numConcentricSquaresRow, numConcentricSquaresCol);

        int j;
        int rowLen = spiralArray.length;
        int colLen = spiralArray[0].length;
        int currNum = 0;

        for (int i = 0; i < numConcentricSquares; i++) {
            // do top side
            for (j = 0; j < colLen; j++) {
                checkProf(spiralArray, i, i + j);
            }

            // do right side
            for (j = 1; j < rowLen; j++) {
                checkProf(spiralArray,i + j, spiralArray[0].length - 1 - i);
            }

            // do bottom side
            for (j = colLen - 2; j > -1; j--) {
                checkProf(spiralArray, spiralArray.length - 1 - i, i + j);
            }

            // do left side
            for (j = rowLen - 2; j > 0; j--) {
                checkProf(spiralArray, i + j, i);
            }

            rowLen -= 2;
            colLen -= 2;
        }

        return spiralArray;
    }

    private static void checkProf(Integer[][] matrix, int y, int x) {
        if (matrix[y][x] == -1)
            return;
        if (matrix[y-1][x] == -1 || matrix[y+1][x] == -1 || matrix[y][x-1] == -1 || matrix[y][x+1] == -1)
            matrix[y][x] = 1;
        else {
            matrix[y][x] = Math.min(matrix[y - 1][x], Math.min(matrix[y + 1][x], Math.min(matrix[y][x - 1], matrix[y][x + 1]))) + 1;
        }
    }

    public static Integer[][] extractMatrix(List<String> aInput, int xSize) {
        Integer[][] result = new Integer[aInput.size()][xSize];

        for (int i = 0; i < aInput.size(); i++) {
            char[] numbers = aInput.get(i).toCharArray();
            for (int j = 0; j < numbers.length; j++) {
                if (numbers[j] == '.')
                    result[i][j] = -1;
                else
                    result[i][j] = 1000;
            }
        }
        return result;
    }

    public static void printMatrix(Integer[][] matrix, int padding) {
        for (int i = 0; i < matrix.length; i++) {
            StringBuilder b = new StringBuilder();
            for (int j = 0; j < matrix[i].length; j++) {
                b.append(String.format("%1$" + padding + "s", matrix[i][j]));
            }
            IsoContestBase.localEcho(b.toString());
        }
    }

}


class IsoContestBase {
    public static void localEcho( String txt ) {
        System.err.println( txt );
    }
}

