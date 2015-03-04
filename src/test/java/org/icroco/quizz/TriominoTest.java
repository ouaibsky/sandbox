package org.icroco.quizz;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TriominoTest {

    public final int width;
    public final int high;

    public TriominoTest(int width, int high) {
        this.width = width;
        this.high = high;
    }

    public int[] createMatrice() {
        int []matrice = new int[width*high];
        Arrays.fill(matrice, 0);
        return matrice;
    }

    public class Solution {
        final int width;
        final int matrice [];
        final int hash;

        public Solution(int aWidth, int value[]) {
            width = aWidth;
            matrice = value;
            hash = Arrays.hashCode(matrice);
        }

        @Override
        public boolean equals(Object o) {
            final Solution toBeCompared = (Solution)o;
            for (int i = 0 ; i < matrice.length ;i++) {
                if (matrice[i] != toBeCompared.matrice[i])
                    return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            return hash;
        }

        public boolean isFull() {
            for (int i = 0 ; i < matrice.length ;i++) {
                if (matrice[i] == 0)
                    return false;
            }
            return true;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder(matrice.length+width);
            for (int i = 0 ; i < matrice.length; i++) {
                builder.append(matrice[i]);
                if (i % width == 0)
                    builder.append(File.separator);
            }
            return builder.toString();
        }
    }

    public final static boolean isFull(int matrice[]) {
        for (int i = 0 ; i < matrice.length ;i++) {
            if (matrice[i] == 0)
                return false;
        }
        return true;
    }

    public int addBar(int pos, int matrice[]) {
        // pos should at fist free
        //if ()
        int posA = pos;
        int posB = pos+width;
        int posC = pos+width+width;
        if (posB >= matrice.length || posC >= matrice.length)
            return pos;
        if (matrice[posB] != 0 || matrice[posC] != 0)
            return pos;

        matrice[posA] = 1;
        matrice[posB] = 1;
        matrice[posC] = 1;
        return pos;
    }

    public int addLine(int pos, int matrice[]) {
        // pos should at fist free
        //if ()
        int posA = pos;
        int posB = pos+1;
        int posC = pos+2;
        if (posB >= matrice.length || posC >= matrice.length)
            return pos;
        if (matrice[posB] != 0 || matrice[posC] != 0)
            return pos;

        matrice[posA] = 1;
        matrice[posB] = 1;
        matrice[posC] = 1;
        return pos+3;
    }


    public Solution track() {
        return track(0, createMatrice());
    }

    public Solution track (int pos, int [] matrice) {
        if (pos >= matrice.length)
        {
            if (isFull(matrice))
                return new Solution(width, matrice);
        }
        int newPos = addBar(pos, matrice);
        return track(newPos, matrice);
//        if (pos == pos) {
//            newPos = addLine(pos, matrice);
//        }
    }

//    @Test
//    public void myTest() {
//        System.out.println(new TriominoTest(2, 9).track());
//    }

    public static void main(String[] argv) throws Exception {
        System.out.println(new TriominoTest(3, 3).track());
    }

}