package org.icroco.quizz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

//@RunWith(BlockJUnit4ClassRunner.class)
public class PythagorTest {

    static class triplet {
        int a;
        int b;
        int c;

        public triplet(int A, int B, int C) {
            a = A;
            b = B;
            c = C;
        }

        @Override
        public String toString() {
            return "a: "+a+" b: "+b+" c:"+c;
        }
    }

    public static List<triplet> computeABC(long n) {
        List<triplet> values = new ArrayList<>();

        int a = 1;
        int b = 1;

        for (a = 1 ; a < 1000 ; a++) {
            //System.out.println(a);
            for (b = a; b < 1000 ; b++) {
                //System.out.println(b);
                int c = (a * a) + (b * b);
                double racine = Math.sqrt(c);
                int racineI = (int) racine;
                double diff = racine - racineI;
                //System.out.println("A: "+a+" B: "+b+" resCarre: "+c+" racine: "+racine);
                if (diff == 0) {
                    if (racineI > b && (a+b+racineI == 1000)) {
                        System.err.printf("%1d %1d %1d -- %d%n", a, b, racineI, (a*b*racineI));
                        values.add(new triplet(a, b, racineI));
                        return values;
                    }
                }
            }
        }

        return values;
    }

    @Test
    public void myTest() {
        System.out.println(computeABC(1000));
    }
}