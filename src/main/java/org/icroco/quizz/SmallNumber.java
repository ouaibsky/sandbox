package org.icroco.quizz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by christophe on 02/02/15.
 */

public class SmallNumber {


    public static boolean canBeDivid(int value) {
        for (int i = 2 ; i <= 20 ; i++)
            if (value%i != 0)
                return false;

        return true;
    }

    public static void main(String[] args) {
        int i = 20;
        while(true){
            i+=1;
            if (canBeDivid(i))
            {
                System.out.println("small number "+i);
                break;
            }
        }
    }


}
