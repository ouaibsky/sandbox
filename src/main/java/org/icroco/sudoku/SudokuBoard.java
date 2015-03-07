package org.icroco.sudoku;

/**
 * Created by christophe on 07/03/15.
 */
public class SudokuBoard extends Board<Integer>{


    public SudokuBoard() {
        super(new Integer[9][9]);
    }

    public SudokuBoard(Integer[][] aValue) {
        super(aValue);
    }

    boolean isValid(Cell cell, int value) {

        if (grid[cell.col][cell.row] != 0) {
            throw new RuntimeException(
                    "Cannot call for cell which already has a value");
        }

        // if v present row, return false
        for (int c = 0; c < 9; c++) {
            if (grid[c][cell.row] == value)
                return false;
        }

        // if v present in col, return false
        for (int r = 0; r < 9; r++) {
            if (grid[cell.col][r] == value)
                return false;
        }

        // if v present in grid, return false

        // to get the grid we should calculate (x1,y1) (x2,y2)
        int x1 = 3 * (cell.col / 3);
        int y1 = 3 * (cell.row / 3);
        int x2 = y1 + 2;
        int y2 = x1 + 2;

        for (int x = x1; x <= x2; x++)
            for (int y = y1; y <= y2; y++)
                if (grid[x][y] == value)
                    return false;

        // if value not present in row, col and bounding box, return true
        return true;
    }

    boolean solve(Cell cur) {

        // if the cell is null, we have reached the end
        if (cur == null)
            return true;

        // if grid[cur] already has a value, there is nothing to solve here,
        // continue on to next cell
        if (grid[cur.col][cur.row] != 0) {
            // return whatever is being returned by solve(next)
            // i.e the state of soduku's solution is not being determined by
            // this cell, but by other cells
            return solve(getNextCell(cur));
        }

        // this is where each possible value is being assigned to the cell, and
        // checked if a solutions could be arrived at.

        // if grid[cur] doesn't have a value
        // try each possible value
        for (int i = 1; i <= 9; i++) {
            // check if valid, if valid, then update
            boolean valid = isValid(cur, i);

            if (!valid) // i not valid for this cell, try other values
                continue;

            // assign here
            grid[cur.col][cur.row] = i;

            // continue with next cell
            boolean solved = solve(getNextCell(cur));
            // if solved, return, else try other values
            if (solved)
                return true;
            else
                grid[cur.col][cur.row] = 0; // reset
            // continue with other possible values
        }

        // if you reach here, then no value from 1 - 9 for this cell can solve
        // return false
        return false;
    }

    public static void main(String[] args) {
        Integer grid[][] =
             {
                 { 3, 0, 6, 5, 0, 8, 4, 0, 0 }, //
                 { 5, 2, 0, 0, 0, 0, 0, 0, 0 }, //
                 { 0, 8, 7, 0, 0, 0, 0, 3, 1 }, //
                 { 0, 0, 3, 0, 1, 0, 0, 8, 0 }, //
                 { 9, 0, 0, 8, 6, 3, 0, 0, 5 }, //
                 { 0, 5, 0, 0, 9, 0, 6, 0, 0 }, //
                 { 1, 3, 0, 0, 0, 0, 2, 5, 0 }, //
                 { 0, 0, 0, 0, 0, 0, 0, 7, 4 }, //
                 { 0, 0, 5, 2, 0, 6, 3, 0, 0 }
             };
        SudokuBoard board = new SudokuBoard(grid);
        board.printStdErr();
        System.err.println("");
        board.solve(new Cell(0, 0));
        board.printStdErr();
    }
}
