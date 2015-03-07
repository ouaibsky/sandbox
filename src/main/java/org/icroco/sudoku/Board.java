package org.icroco.sudoku;

/**
 * Created by christophe on 07/03/15.
 */
public class Board<T> {
    final int x, y;
    final T[][] grid;

    public Board(final T[][] aModel ) {
        this.x = aModel.length;
        this.y = aModel[0].length;
        grid = aModel;
    }

    int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return x*y;
    }

    public boolean isCellValid(final Cell aCell) {
        return aCell.col >= 0 &&  aCell.row >= 0 && aCell.col < x && aCell.row < y;
    }

    public Cell getNextCell(Cell cur) {

        int row = cur.row;
        int col = cur.col;

        // next cell => col++
        col++;
        if (col >= x) {
            // goto next line
            col = 0;
            row++;
        }
        if (row >= y)
            return null; // reached end

        final Cell next = new Cell(col, row);
        return next;
    }

    public void printStdErr(int padding) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.err.printf("%1$"+padding+"s", grid[i][j]);
            }
            System.err.println("");
        }
    }

    public void printStdErr() {
        printStdErr(2);
    }
}
