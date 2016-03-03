package org.icroco.battledevregions2014.ex3_bingo;
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

    public static Integer[][] extractMatrix(LinkedList<String> aInput, int ySize, int xSize) {
        Integer[][] result = new Integer[ySize][xSize];

        for (int y = 0; y < ySize; y++) {
            String[] cells = aInput.removeFirst().split("\\s+");
            //Integer[] cells = toIntArray(aInput.removeFirst());  // TODO uncomment if char array
            // char[] cells = aInput.removeFirst().toCharArray(); // TODO uncomment if char array
            for (int x = 0; x < cells.length; x++) {
                result[y][x] = Integer.valueOf(cells[x]);
                // result[y][x] = cells[x] == '.' ? 0 : 1; // TODO uncomment if char array
            }
        }
        return result;
    }

    public static void fillMatrix(Integer[][] matrix) {
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                matrix[y][x] = 0;
            }
        }
    }

    public static class Coord {
        int y;
        int x;

        public Coord(final int aY, final int aX) {
            y = aY;
            x = aX;
        }
    }

    public static Coord getCoord(Integer[][] matrix, int value) {
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if (matrix[y][x] == value)
                    return new Coord(y, x);
            }
        }
        return null;
    }

    public static boolean check(Integer[][] values, int aY, int aX) {
        boolean ok = true;
        for (int y = 0; y < 5 && ok; y++) {
            ok = ok & values[y][aX] == 1;
        }
        if (ok)
            return true;

        ok = true;
        for (int x = 0; x < 5 && ok; x++) {
            ok = ok & values[aY][x] == 1;
        }
        if (ok)
            return true;

        ok = true;
        for (int y = 0; y < 5 && ok; y++) {
            ok = ok & values[y][y] == 1;
        }
        if (ok)
            return true;

        ok = true;
        for (int y = 0; y < 5 && ok; y++) {
            ok = ok & values[y][4-y] == 1;
        }
        if (ok)
            return true;

        return false;
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

        Integer[][] matrix = extractMatrix(aInput, 5, 5);
        Integer[][] values = new Integer[5][5];
        fillMatrix(values);

        printMatrix(matrix, 3);


        int nb = Integer.parseInt(aInput.removeFirst().trim());
        IsoContestBase.localEcho("Read nb: " + nb);

        int tirage = 0;
        int cpt    = 1;
        while (!aInput.isEmpty()) {
            tirage = Integer.parseInt(aInput.removeFirst().trim());
            Coord c = getCoord(matrix, tirage);
            if (c != null) {
                values[c.y][c.x] = 1;
                if (check(values, c.y, c.x))
                    break;
            }
            cpt++;
        }
        IsoContestBase.localEcho("");
        printMatrix(values, 2);

        IsoContestBase.localEcho("");

        IsoContestBase.localEcho("result, amount: " + cpt);
        if (cpt >= nb)
            output.add("NOK");
        else
            output.add("OK " + cpt);
        IsoContestBase.localEcho("----");
        IsoContestBase.localEcho("");
        return output;
    }






}
// END COPY


