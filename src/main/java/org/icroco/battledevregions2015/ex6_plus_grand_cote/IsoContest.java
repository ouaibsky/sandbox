package org.icroco.battledevregions2015.ex6_plus_grand_cote;
// START COPY

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


/**
 * imp
 * Created by christophe on 07/03/15.
 */
public class IsoContest {
    public static void main(String[] argv) throws Exception {
        String  line;
        Scanner sc = new Scanner(System.in);

        LinkedList<String> input = new LinkedList<>();

        while (sc.hasNextLine())
            input.add(sc.nextLine());

        List<String> output = getSolution(input);

        for (String s : output)
            System.out.println(s);
    }

    public static Integer[][] extractMatrix(LinkedList<String> aInput, int ySize, int xSize) {
        Integer[][] result = new Integer[ySize][xSize];

        for (int y = 0; y < ySize; y++) {
            char[] cells = aInput.removeFirst().toCharArray();
            for (int x = 0; x < cells.length; x++) {
                result[y][x] = cells[x] == '.' ? 0 : 1;
            }
        }
        return result;
    }

    public static void printMatrix(Integer[][] matrix, int padding) {
        for (int y = 0; y < matrix.length; y++) {
            StringBuilder b = new StringBuilder();
            for (int x = 0; x < matrix[y].length; x++) {
                b.append(String.format("%1$-" + padding + "s", matrix[y][x]));
            }
            IsoContestBase.localEcho(b.toString());
        }
    }

    static List<String> getSolution(LinkedList<String> aInput) {
        LinkedList<String> output = new LinkedList<>();

        Integer[] firstLine = toIntArray(aInput.removeFirst().trim());
        IsoContestBase.localEcho("Read line: " + Arrays.toString(firstLine));
        int Y = firstLine[0];
        int X = firstLine[1];

        Integer[][] matrix = extractMatrix(aInput, Y, X);
        printMatrix(matrix, 3);

        int maxSize = 0;
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if (matrix[y][x] == 1) {
                    int s = 1;
                    while (true) {
                        if (y + s == Y || x + s == X)
                            break;
                        if (!checkSquare(matrix, y, x, s))
                            break;
                        s++;
                    }
                    if (s > maxSize)
                        maxSize = s;
                }
            }
        }

        IsoContestBase.localEcho("");
        IsoContestBase.localEcho("result: " + maxSize);
        output.add("" + maxSize);
        IsoContestBase.localEcho("----");
        IsoContestBase.localEcho("");
        return output;
    }

    private static boolean checkSquare(final Integer[][] aMatrix, final int aY, final int aX, final int aS) {
        boolean res = true;
        for (int y = aY; y < aMatrix.length && y <= aY+aS; y++) {
            for (int x = aX; x < aMatrix[y].length && x <= aX +aS; x++) {
                res = res & aMatrix[y][x] == 1;
            }
        }
        return res;
    }

    public static Integer[] toIntArray(String line) {
        String[]  SA = line.split("\\s+");
        Integer[] IA = new Integer[SA.length];
        for (int i = 0; i < SA.length; i++)
            IA[i] = Integer.parseInt(SA[i]);

        return IA;
    }
}
// END COPY


