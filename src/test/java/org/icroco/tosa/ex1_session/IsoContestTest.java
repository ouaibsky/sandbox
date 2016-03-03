package org.icroco.tosa.ex1_session;

public class IsoContestTest {

    //    Integer matrix[][] = new Integer[][]{
//            new Integer[]{1, 2, 3},
//            new Integer[]{4, 5, 6},
//            new Integer[]{7, 8, 9},
//            new Integer[]{10, 11, 12}
//    };
//    IsoContestOK.Matrix matrix = new IsoContestOK.Matrix(new LinkedList<>(Arrays.asList("1 2 3",
//                                                                                    "4 5 6",
//                                                                                    "7 8 9",
//                                                                                    "10 11 12")), 4, 3);
//
//
//    @Before
//    public void before() {
//        matrix.echo(3);
//    }
//
//    @Test
//    public void should_return_next_y() {
//        IsoContestOK.Matrix.Cell cell = matrix.array[0][2];
//        Assert.assertEquals(cell.y, 0);
//        Assert.assertEquals(cell.x, 2);
//        IsoContestOK.Matrix.Cell next = matrix.next(cell);
//        System.out.println("Cell: " + cell);
//        System.out.println("Cell: " + next);
//        Assert.assertEquals(next.y, 1);
//        Assert.assertEquals(next.x, 0);
//
//        cell = matrix.array[0][0];
//        Assert.assertEquals(cell.y, 0);
//        Assert.assertEquals(cell.x, 0);
//        next = matrix.next(cell);
//        System.out.println("Cell: " + cell);
//        System.out.println("Cell: " + next);
//        Assert.assertEquals(next.x, 1);
//        Assert.assertEquals(next.y, 0);
//
//        cell = matrix.array[3][2];
//        Assert.assertEquals(cell.x, 2);
//        Assert.assertEquals(cell.y, 3);
//        next = matrix.next(cell);
//        System.out.println("Cell: " + cell);
//        System.out.println("Cell: " + next);
//        Assert.assertEquals(next.x, 0);
//        Assert.assertEquals(next.y, 0);
//
//        cell = matrix.array[2][1];
//        Assert.assertEquals(cell.x, 1);
//        Assert.assertEquals(cell.y, 2);
//        next = matrix.next(cell);
//        System.out.println("Cell: " + cell);
//        System.out.println("Cell: " + next);
//        Assert.assertEquals(next.x, 2);
//        Assert.assertEquals(next.y, 2);
//
//    }
//
//    @Test
//    public void should_return_row_y() {
//        IsoContestOK.Matrix.Cell y[] = matrix.getY(1);
//        Assert.assertEquals(4, y[0].value.intValue());
//        y = matrix.getY(3);
//        Assert.assertEquals(12, y[2].value.intValue());
//
//        Assert.assertNull(matrix.getY(5));
//    }
//
//    @Test
//    public void should_return_up() {
//        IsoContestOK.Matrix.Cell cell = matrix.array[0][2];
//        Assert.assertEquals(cell.value, Integer.valueOf(3));
//
//        IsoContestOK.Matrix.Cell up = matrix.up(cell);
//        Assert.assertNull(up);
//
//        cell = matrix.array[1][2];
//        up = matrix.up(cell);
//        Assert.assertEquals(up.value, Integer.valueOf(3));
//    }
//
//    @Test
//    public void should_return_down() {
//        IsoContestOK.Matrix.Cell cell = matrix.array[3][2];
//        Assert.assertEquals(cell.value, Integer.valueOf(12));
//
//        IsoContestOK.Matrix.Cell down = matrix.down(cell);
//        Assert.assertNull(down);
//
//        cell = matrix.array[1][2];
//        down = matrix.down(cell);
//        Assert.assertEquals(down.value, Integer.valueOf(9));
//    }
//
//    @Test
//    public void should_return_left() {
//        IsoContestOK.Matrix.Cell cell = matrix.array[3][0];
//        Assert.assertEquals(cell.value, Integer.valueOf(10));
//
//        IsoContestOK.Matrix.Cell down = matrix.left(cell);
//        Assert.assertNull(down);
//
//        cell = matrix.array[1][2];
//        down = matrix.left(cell);
//        Assert.assertEquals(down.value, Integer.valueOf(5));
//    }
//
//    @Test
//    public void should_return_right() {
//        IsoContestOK.Matrix.Cell cell = matrix.array[2][2];
//        Assert.assertEquals(cell.value, Integer.valueOf(9));
//
//        IsoContestOK.Matrix.Cell down = matrix.right(cell);
//        Assert.assertNull(down);
//
//        cell = matrix.array[2][1];
//        down = matrix.right(cell);
//        Assert.assertEquals(down.value, Integer.valueOf(9));
//    }
}