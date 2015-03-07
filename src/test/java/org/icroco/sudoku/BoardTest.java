package org.icroco.sudoku;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


public class BoardTest {

    @Test
    public void testGetX() throws Exception {
        Board b = new Board(new Integer[10][10]);
        assertThat(b.getX()).isEqualTo(10);

        b = new Board(new Integer[10][100]);
        assertThat(b.getX()).isEqualTo(10);
    }

    @Test
    public void testGetY() throws Exception {
        Board b = new Board(new Integer[10][10]);
        assertThat(b.getY()).isEqualTo(10);
        b = new Board(new Integer[10][150]);
        assertThat(b.getY()).isEqualTo(150);

    }

    @Test
    public void testGetSize() throws Exception {
        Board b = new Board(new Integer[10][10]);
        assertThat(b.getSize()).isEqualTo(100);
        b = new Board(new Integer[10][150]);
        assertThat(b.getSize()).isEqualTo(1500);
        b = new Board(new Integer[1][1]);
        assertThat(b.getY()).isEqualTo(1);

    }

    @Test
    public void testIsCellValid() throws Exception {
        Board b = new Board(new Integer[10][10]);
        assertThat(b.isCellValid(new Cell(1, 1))).isTrue();
        assertThat(b.isCellValid(new Cell(0, 0))).isTrue();
        assertThat(b.isCellValid(new Cell(9, 9))).isTrue();
        assertThat(b.isCellValid(new Cell(9, 10))).isFalse();
        assertThat(b.isCellValid(new Cell(10, 9))).isFalse();
        assertThat(b.isCellValid(new Cell(10, 10))).isFalse();
        assertThat(b.isCellValid(new Cell(-10, 9))).isFalse();
        assertThat(b.isCellValid(new Cell(10, -9))).isFalse();
        assertThat(b.isCellValid(new Cell(-100, -9))).isFalse();


        b = new Board(new Integer[10][150]);
        assertThat(b.isCellValid(new Cell(20, 10))).isFalse();
        assertThat(b.isCellValid(new Cell(10, 100))).isFalse();
        assertThat(b.isCellValid(new Cell(9, 100))).isTrue();

    }

    @Test
    public void testGetNextCell() throws Exception {
        final Board b = new Board(new Integer[10][10]);
        Cell cur = new Cell(0, 0);
        assertThat(b.isCellValid(cur));
        Cell next = b.getNextCell(cur);
        assertThat(next.col).isEqualTo(1);
        assertThat(next.row).isEqualTo(0);

        cur = new Cell(9, 0);
        assertThat(b.isCellValid(cur));
        next = b.getNextCell(cur);
        assertThat(next.col).isEqualTo(1);
        assertThat(next.row).isEqualTo(0);

    }


    @Test
    public void testPrint() throws Exception {
        Integer[][] matrice = new Integer[10][10];
        for (int i = 0; i < matrice.length ; i++) {
            Arrays.fill(matrice[i], i);
        }

        Board b = new Board(matrice);
        b.printStdErr();
    }

    @Test
    public void testPrint2() throws Exception {
        Character[][] matrice = new Character[10][10];
        for (int i = 0; i < matrice.length ; i++) {
            Arrays.fill(matrice[i], (char)('A'+i));
        }

        Board b = new Board(matrice);
        b.printStdErr(3);
    }

}