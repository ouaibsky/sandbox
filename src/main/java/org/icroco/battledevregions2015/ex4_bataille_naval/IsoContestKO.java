package org.icroco.battledevregions2015.ex4_bataille_naval;
// START COPY

import java.util.*;


/**
 * imp
 * Created by christophe on 07/03/15.
 */
public class IsoContestKO {
    public static class Matrix {
        public final int      xMax;
        public final int      yMax;
        public       Cell[][] array;

        public Matrix(LinkedList<String> aInput, int ySize, int xSize) {
            yMax = ySize;
            xMax = xSize;
            array = new Cell[yMax][xMax];

            for (int i = 0; i < yMax; i++) {
                //Integer[] cells = toIntArray(aInput.removeFirst());
                //String[] cells = aInput.removeFirst().split("\\s+"); // TODO: uncomment if substrings
                char[] cells = aInput.removeFirst().toCharArray(); // TODO: uncomment if char array
                for (int j = 0; j < cells.length; j++) {
                    array[i][j] = new Cell(j, i, Integer.valueOf(cells[j] == '.' ? 0 : 1));
                }
            }
        }

        public void echo(int padding) {
            for (int i = 0; i < array.length; i++) {
                StringBuilder b = new StringBuilder();
                for (int j = 0; j < array[i].length; j++) {
                    b.append(String.format("%1$-" + padding + "s", array[i][j].value));
                }
                IsoContestBase.localEcho(b.toString());
            }
        }

        public Cell get(int aY, int aX) {
            return array[aY][aX];
        }

        public Cell[] getY(int aY) {
            if (aY >= yMax)
                return null;
            return array[aY];

        }

        public Cell next(Cell aCell) {
            if (aCell.x + 1 == xMax) {
                if (aCell.y + 1 == yMax) {
                    return array[0][0];
                } else {
                    return array[aCell.y + 1][0];
                }
            } else {
                return array[aCell.y][aCell.x + 1];
            }
        }

        public Cell up(Cell aCell) {
            if (aCell.y == 0)
                return null;
            return array[aCell.y - 1][aCell.x];
        }

        public Cell down(Cell aCell) {
            if (aCell.y + 1 == yMax)
                return null;
            return array[aCell.y + 1][aCell.x];
        }

        public Cell left(Cell aCell) {
            if (aCell.x == 0)
                return null;
            return array[aCell.y][aCell.x - 1];
        }

        public Cell right(Cell aCell) {
            if (aCell.x + 1 == xMax)
                return null;
            return array[aCell.y][aCell.x + 1];
        }

        public final class Cell {
            public int     x;
            public int     y;
            public Integer value; // TODO: Replace by good type
            public boolean marked;

            public Cell(final int aX, final int aY, final Integer aValue) {
                x = aX;
                y = aY;
                value = aValue;
                marked = false;
            }

            public Integer getValue() {
                return value;
            }

            @Override
            public String toString() {
                return "Cell{" +
                        "x=" + x +
                        ", y=" + y +
                        ", value=" + value +
                        '}';
            }

            @Override
            public boolean equals(final Object aO) {
                if (this == aO) return true;
                if (aO == null || getClass() != aO.getClass()) return false;

                final Cell cell = (Cell) aO;

                return x == cell.x && y == cell.y;

            }

            @Override
            public int hashCode() {
                int result = x;
                result = 31 * result + y;
                return result;
            }
        }

    }


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
        LinkedList<String> output = new LinkedList<>();

        Integer nb[]  = toIntArray(aInput.removeFirst().trim());
        int     m     = nb[0];
        int     n     = nb[1];
        int     nbTir = nb[2];
        //IsoContestBase.localEcho("Read line: " + Arrays.toString(nb));

        Matrix matrix = new Matrix(aInput, m, n);
        //    matrix.echo(3);

        while (!aInput.isEmpty()) {
            Integer[]   coord = toIntArray(aInput.removeFirst());
            Matrix.Cell v     = matrix.get(coord[0], coord[1]);
            if (v.value == 1)
                v.value = 2;
        }

        //matrix.echo(3);

        Map<Matrix.Cell, List<Matrix.Cell>> boats = new HashMap<>();
        for (int i = 0; i < matrix.yMax; i++) {
            for (int j = 0; j < matrix.xMax; j++) {
                Matrix.Cell v = matrix.get(i, j);
                if (v.value > 0) {
                    Matrix.Cell left   = matrix.left(matrix.array[i][j]);
                    Matrix.Cell bottom = matrix.down(matrix.array[i][j]);
                    Matrix.Cell right  = matrix.right(matrix.array[i][j]);
                    Matrix.Cell up     = matrix.up(matrix.array[i][j]);

                    if ((left == null || left.value == 0) && (up == null || up.value == 0)) {
                        List<Matrix.Cell> values = new ArrayList<>(Arrays.asList(v));
                        Matrix.Cell       vN     = null;
                        boats.put(matrix.get(i, j), values);
                        if (right != null && right.value > 0) { // horiz
                            int k = j + 1;
                            while (k < matrix.xMax) {
                                vN = matrix.get(i, k);
                                if (vN.value >= 1)
                                    values.add(matrix.get(i, k));
                                else
                                    break;
                                k++;
                            }
                        } else if (bottom != null && bottom.value > 0) {
                            int k = i + 1;
                            while (k < matrix.yMax) {
                                vN = matrix.get(k, j);
                                if (vN.value >= 1)
                                    values.add(matrix.get(k, j));
                                else
                                    break;
                                k++;
                            }
                        } else {
                            // nothing, 1 cell
                        }
                    } // else: part of a boat
                }
            }
        }

        int nbCoule  = 0;
        int nbTouche = 0;
        for (List<Matrix.Cell> cells : boats.values()) {
            boolean coule  = true;
            boolean touche = false;
            for (Matrix.Cell c : cells) {
                coule = coule & c.value == 2;
                touche = touche | c.value == 2;
            }
            if (coule)
                nbCoule++;
            if (touche && !coule)
                nbTouche++;
        }

        //IsoContestBase.localEcho("");
        output.add("" + nbCoule + " " + nbTouche);

        return output;
    }

    // Matrix shortcut:
    // matrix (List<String> to Integer[][]), pmatrix (print matrix)
    // fmatrix, pfmatrix


    public static Integer[] toIntArray(String line) {
        String[]  SA = line.split("\\s+");
        Integer[] IA = new Integer[SA.length];
        for (int i = 0; i < SA.length; i++)
            IA[i] = Integer.parseInt(SA[i]);

        return IA;
    }
}
// END COPY


