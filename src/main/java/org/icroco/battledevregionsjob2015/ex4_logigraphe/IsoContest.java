package org.icroco.battledevregionsjob2015.ex4_logigraphe;
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
            input.add(sc.nextLine().trim());

        List<String> output = getSolution(input);

        for (String s : output)
            System.out.println(s);
    }

    static List<String> getSolution(LinkedList<String> aInput) {
        LinkedList<String> output  = new LinkedList<>();
        Integer[]          nbLines = toIntArray(aInput.removeFirst());
        IsoContestBase.localEcho("Read nbLine: " + Arrays.toString(nbLines));

        Integer[][] matrix = extractMatrix(aInput, nbLines[0], nbLines[1]);
        printMatrix(matrix, 2);

        String resultat = "";

        int nb = 0;
        for (int y = 0; y < matrix.length; y++) {
            String row = "";
            nb = 0;
            for (int x = 0; x < matrix[0].length; x++) {
                if (matrix[y][x] == 1)
                    nb++;
                else {
                    if (nb > 0)
                        row += "" + nb + "-";
                    nb = 0;
                }
            }
            if (nb > 0)
                row += "" + nb;
            row = row.replaceAll("-$", "");
            if (row.length() == 0)
                row = ".";
            resultat += row + " ";
        }

        nb = 0;
        for (int x = 0; x < matrix[0].length; x++) {
            String col = "";
            nb = 0;
            for (int y = 0; y < matrix.length; y++) {
                if (matrix[y][x] == 1)
                    nb++;
                else {
                    if (nb > 0)
                        col += "" + nb + "-";
                    nb = 0;
                }

            }
            if (nb > 0)
                col += "" + nb;
            col = col.replaceAll("-$", "");
            if (col.length() == 0)
                col = ".";
            resultat += col + " ";
        }


        IsoContestBase.localEcho("");

        IsoContestBase.localEcho("result: " + resultat.trim());
        output.add("" + resultat.trim());
        IsoContestBase.localEcho("----");
        IsoContestBase.localEcho("");
        return output;
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

    public static Integer[][] extractMatrix(LinkedList<String> aInput, int ySize, int xSize) {
        Integer[][] result = new Integer[ySize][xSize];

        for (int y = 0; y < ySize; y++) {
            char[] cells = aInput.removeFirst().toCharArray(); // TODO uncomment if char array
            for (int x = 0; x < cells.length; x++) {
                result[y][x] = cells[x] == '.' ? 0 : 1; // TODO uncomment if char array
            }
        }
        return result;
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


