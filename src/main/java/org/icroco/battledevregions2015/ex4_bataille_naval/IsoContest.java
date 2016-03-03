package org.icroco.battledevregions2015.ex4_bataille_naval;
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
             char[] cells = aInput.removeFirst().toCharArray(); // TODO uncomment if char array
            for (int x = 0; x < cells.length; x++) {
                result[y][x] = cells[x] == '.' ? 0 : 1; // TODO uncomment if char array
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

    public static List<String> getSolution(LinkedList<String> aInput) {
        LinkedList<String> output = new LinkedList<>();

        Integer nb[]  = toIntArray(aInput.removeFirst().trim());
        int     M     = nb[0]; // y
        int     N     = nb[1]; // x
        int     nbTir = nb[2];
        //IsoContestBase.localEcho("Read line: " + Arrays.toString(nb));

        Integer[][] matrix = extractMatrix(aInput, M, N);

        while (!aInput.isEmpty()) {
            Integer[] coord = toIntArray(aInput.removeFirst());
            if (matrix[coord[0]][coord[1]] == 1)
                matrix[coord[0]][coord[1]] = 2;
        }

        printMatrix(matrix, 3);

        List<List<Integer>> boats = new ArrayList<>();

        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if (matrix[y][x] > 0) {
                    if ((x == 0 || matrix[y][x - 1] == 0) && (y == 0 || matrix[y - 1][x] == 0)) {
                        List<Integer> values = new ArrayList<>(Arrays.asList(matrix[y][x]));
                        boats.add(values);
                        if (x + 1 < N && matrix[y][x + 1] > 0) { // horiz
                            int k = x + 1;
                            while (k < N) {
                                if (matrix[y][k] >= 1)
                                    values.add(matrix[y][k]);
                                else
                                    break;
                                k++;
                            }
                        } else if (y + 1 < M && matrix[y + 1][x] > 0) {
                            int k = y + 1;
                            while (k < M) {
                                if (matrix[k][x] >= 1)
                                    values.add(matrix[k][x]);
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
        for (List<Integer> oneBoat : boats) {
            boolean coule  = true;
            boolean touche = false;
            for (Integer i : oneBoat) {
                coule = coule & i == 2;
                touche = touche | i == 2;
            }
            if (coule)
                nbCoule++;
            if (touche && !coule)
                nbTouche++;
        }

        output.add("" + nbCoule + " " + nbTouche);
        return output;
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


