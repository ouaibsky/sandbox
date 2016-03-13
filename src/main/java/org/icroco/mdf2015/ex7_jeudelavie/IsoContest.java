package org.icroco.mdf2015.ex7_jeudelavie;
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
        LinkedList<String> output    = new LinkedList<>();
        int                firstLine = Integer.parseInt(aInput.removeFirst().trim());
        int                resultat  = 0;
        IsoContestBase.localEcho("Read firstLine: " + firstLine);
        Integer[][] matrix = new Integer[1][1];

        TreeMap<Cell, Cell> cells = new TreeMap<>();
        while (!aInput.isEmpty()) {
            Integer[] line = toIntArray(aInput.removeFirst());
            int       yMin = line[1];
            int       xMin = line[0];
            int       yMax = line[3];
            int       xMax = line[2];
            for (int y = yMin - 1; y <= yMax - 1; y++) {
                for (int x = xMin - 1; x <= xMax - 1; x++) {
                    Cell c = new Cell(y, x);
                    cells.put(c, c);
                }
            }
            int YMAX = Math.max(yMax, matrix.length);
            int XMAX = Math.max(xMax, matrix[0].length);
            matrix = new Integer[YMAX][XMAX];
        }
        //matrix = new Integer[matrix.length + 100][matrix[0].length + 100];
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                matrix[y][x] = 0;
            }
        }

        for (Cell c: cells.keySet()) {
            matrix[c.y][c.x] = 1;
        }
        printMatrix(matrix, 3);
        int nb = 1;
        int nbSurvivant = cells.size();
        int nbSurvivantAfter = 0;
        while (true) {
            boolean changed = false;
            for (int y = 0; y < matrix.length; y++) {
                for (int x = 0; x < matrix[0].length; x++) {
                    boolean up   =  true;
                    boolean left = true;
                    if (y-1 < 0 || matrix[y-1][x] == 0)
                        up = false;
                    if (x-1 < 0 || matrix[y][x-1] == 0)
                        left = false;
                    if (up && left)
                        matrix[y][x] = 1;
                    if (up == false && left == false)
                        matrix[y][x] = 0;
                    if (matrix[y][x] == 1)
                        nbSurvivantAfter++;
                }
            }

//            Set<Cell> cellSet = cells.keySet();
//            for (Cell c : cellSet) {
//                List<Cell> ul = upLeft(c.y, c.x);
//                if (ul.size() == 2) {
//                    if (isAlive(cellSet, ul.get(0), ul.get(1)))
//                        cellSet.remove(c);
//                }
//            }
//            if (cells.size() == size) {
//                break;
//            }
            IsoContestBase.localEcho("tour: "+nb);
            printMatrix(matrix, 3);
            IsoContestBase.localEcho("");

            if (nbSurvivantAfter == 0) {
                break;
            } else {
                nbSurvivantAfter = 0;
            }
            nb++;
        }

        IsoContestBase.localEcho("");

        IsoContestBase.localEcho("result: " + nb);
        output.add("" + nb);
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

    public static class Cell implements Comparable<Cell> {
        int y;
        int x;

        public Cell(final int aY, final int aX) {
            y = aY;
            x = aX;
        }

        @Override
        public boolean equals(final Object aO) {
            if (this == aO) return true;
            if (aO == null || getClass() != aO.getClass()) return false;

            final Cell cell = (Cell) aO;

            if (y != cell.y) return false;
            return x == cell.x;

        }

        @Override
        public int hashCode() {
            int result = y;
            result = 31 * result + x;
            return result;
        }

        @Override
        public String toString() {
            return "Cell{" +
                    "y=" + y +
                    ", x=" + x +
                    '}';
        }

        @Override
        public int compareTo(final Cell o) {
            int res = Integer.compare(y, o.y);
            if (res == 0)
                return Integer.compare(x, o.x);
            return res;
        }
    }

    public static List<Cell> upLeft(int aY, int aX) {
        ArrayList<Cell> values = new ArrayList<>();
        values.add(new Cell(aY, aX - 1));           // Left
        values.add(new Cell(aY - 1, aX));           // Up
        return values;
    }

    public static boolean isAlive(Set<Cell> aCells, Cell aUp, Cell aLeft) {
        return aCells.contains(aUp) && aCells.contains(aLeft);
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


