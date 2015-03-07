package org.icroco.sudoku;

/**
 * Created by christophe on 07/03/15.
 */
public class Cell {

    int row, col;

    public Cell(int col, int row) {
        super();
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "Cell [X: "+ col + ", Y=" + row + "]";
    }
};