package org.icroco.quizz;

/**
 * Created by christophe on 02/02/15.
 */
public class ThreeFiveMultiplier {


    public static int sumTreeOrFiveDivider(int aLimit) {
        int sum = 0;

        for (int i = 3 ; i < aLimit ; i++) {
            if ((i % 3) == 0 || (i % 5) == 0)
                sum +=i;
        }

        return sum;
    }



}
