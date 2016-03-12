package org.icroco.mdf2015.ex6_sables_mouvant;
// START COPY

import java.util.*;


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

    static List<String> getSolution(LinkedList<String> aInput) {
        LinkedList<String> output      = new LinkedList<>();
        Integer            firstLine[] = toIntArray(aInput.removeFirst());
        int                Y           = firstLine[0];
        int                X           = firstLine[1];
        IsoContestBase.localEcho("Read firstLine: " + Arrays.toString(firstLine));


        Integer[][] matrix = extractMatrix(aInput, Y, X);
//        printMatrix(matrix, 3);
//        IsoContestBase.localEcho("");
//        IsoContestBase.localEcho("After perimeter:");
//
//        //Feed around
//        for (Cell c : getPerimeter(matrix)){
//            increaseSand(matrix, c.y, c.x, 1);
//        }
        printMatrix(matrix, 3);
        IsoContestBase.localEcho("");

        int tour = 0;
        while (true) {
            boolean somethingChange = false;

            for (int y = 0; y < matrix.length; y++) {
                for (int x = 0; x < matrix[y].length; x++) {
                    if (matrix[y][x] == tour) {
                        for (Cell c : getAroundHV(matrix, y, x))
                            somethingChange |= increaseSand(matrix, c.y, c.x, tour + 1);
                    }
                }
            }
            IsoContestBase.localEcho("");
            printMatrix(matrix, 3);
            IsoContestBase.localEcho("");

            if (!somethingChange)
                break;
            tour++;
        }

        int max = 0;
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if (matrix[y][x] > max)
                    max = matrix[y][x];
            }
        }


        IsoContestBase.localEcho("result, max: " + max);
        output.add("" + max);
        IsoContestBase.localEcho("----");
        IsoContestBase.localEcho("");
        return output;
    }

    public static class Cell {
        int y;
        int x;

        public Cell(final int aY, final int aX) {
            y = aY;
            x = aX;
        }
    }


    public static List<Cell> getAroundHV(Integer[][] aMatrix, int aY, int aX) {
        ArrayList<Cell> values = new ArrayList<>();
        int             Y      = aMatrix.length;
        int             X      = aMatrix[0].length;

        if (aX - 1 >= 0) {
            values.add(new Cell(aY, aX - 1));
        }
        if (aX + 1 < X) {
            values.add(new Cell(aY, aX + 1));
        }
        if (aY - 1 >= 0) {
            values.add(new Cell(aY - 1, aX));
        }
        if (aY + 1 < Y) {
            values.add(new Cell(aY + 1, aX));
        }

        return values;
    }


    public static boolean increaseSand(Integer[][] matrix, int y, int x, final int aI) {
        if (matrix[y][x] == -1) {
            matrix[y][x] = aI;
            return true;
        }
        return false;
    }

    public static Integer[][] extractMatrix(LinkedList<String> aInput, int ySize, int xSize) {
        Integer[][] result = new Integer[ySize][xSize];

        for (int y = 0; y < ySize; y++) {
            char[] cells = aInput.removeFirst().toCharArray();
            for (int x = 0; x < cells.length; x++) {
                result[y][x] = cells[x] == '.' ? 0 : -1; // TODO uncomment if char array
            }
        }
        return result;
    }


    public static void printMatrix(Integer[][] matrix, int padding) {
        for (int y = 0; y < matrix.length; y++) {
            StringBuilder b = new StringBuilder();
            for (int x = 0; x < matrix[y].length; x++) {
                b.append(String.format("%1$" + padding + "s", matrix[y][x]));
                //b.append(String.format("%1$-" + padding + "s", matrix[y][x]));
            }
            IsoContestBase.localEcho(b.toString());
        }
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


