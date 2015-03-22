package org.icroco.util;

/**
 * Created by christophe on 15/03/15.
 */
public class MatrixUtil {

    public static int[][] getSpiralArray(int dimension) {
        int[][] spiralArray = new int[dimension][dimension];

        int numConcentricSquares = (int) Math.ceil((dimension) / 2.0);

        int j;
        int sideLen = dimension;
        int currNum = 0;

        for (int i = 0; i < numConcentricSquares; i++) {
            // do top side
            for (j = 0; j < sideLen; j++) {
                spiralArray[i][i + j] = currNum++;
            }

            // do right side
            for (j = 1; j < sideLen; j++) {
                spiralArray[i + j][dimension - 1 - i] = currNum++;
            }

            // do bottom side
            for (j = sideLen - 2; j > -1; j--) {
                spiralArray[dimension - 1 - i][i + j] = currNum++;
            }

            // do left side
            for (j = sideLen - 2; j > 0; j--) {
                spiralArray[i + j][i] = currNum++;
            }

            sideLen -= 2;
        }

        return spiralArray;
    }

    public static Integer[][] getSpiralArray(Integer aRowDimension, int aColDimension) {
        return getSpiralArray(new Integer[aRowDimension][aColDimension]);
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
                spiralArray[i][i + j] = currNum++;
            }

            // do right side
            for (j = 1; j < rowLen; j++) {
                spiralArray[i + j][spiralArray[0].length - 1 - i] = currNum++;
            }

            // do bottom side
            for (j = colLen - 2; j > -1; j--) {
                spiralArray[spiralArray.length - 1 - i][i + j] = currNum++;
            }

            // do left side
            for (j = rowLen - 2; j > 0; j--) {
                spiralArray[i + j][i] = currNum++;
            }

            rowLen -= 2;
            colLen -= 2;
        }

        return spiralArray;
    }




    public static void print2dArray(Integer[][] array) {
        for (Integer[] row : array) {
            for (Integer elem : row) {
                System.out.printf("%3d", elem);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        print2dArray(getSpiralArray(6, 2));
        System.out.println();
        print2dArray(getSpiralArray(5, 7));
        System.out.println();
        print2dArray(getSpiralArray(10, 4));
        System.out.println();
        print2dArray(getSpiralArray(5, 5));
        System.out.println();
        print2dArray(getSpiralArray(1, 1));
        System.out.println();
        print2dArray(getSpiralArray(2, 2));
        System.out.println();
        print2dArray(getSpiralArray(7, 8));
        System.out.println();
        print2dArray(getSpiralArray(2, 12));
        System.out.println();
        print2dArray(getSpiralArray(new Integer[10][5]));
        System.out.println();
        print2dArray(getSpiralArray(new Integer[10][4]));

        System.out.println();
        print2dArray(getSpiralArray(new Integer[100][3]));
    }
}
