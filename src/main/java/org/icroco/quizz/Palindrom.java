package org.icroco.quizz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by christophe on 02/02/15.
 */

public class Palindrom {

    public static boolean isPalindrome(int number) {
        int palindrome = number; // copied number into variable
        int reverse = 0;

        while (palindrome != 0) {
            int remainder = palindrome % 10;
            reverse = reverse * 10 + remainder;
            palindrome = palindrome / 10;
        }

        // if original and reverse of number is equal means
        // number is palindrome in Java
        if (number == reverse) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        List<Integer> values = new ArrayList<>(100);
        for (int i = 999 ; i > 99 ; i-- )
            for (int j = 999 ; j > 99 ; j--)
                if (isPalindrome(j*i)) {
                    System.out.println("Palindrom is: "+j*i);
                    values.add(j*i);
                }

        Collections.sort(values);
        System.out.println("Max Palindrom is: " + values.get(values.size()-1));
    }


}
