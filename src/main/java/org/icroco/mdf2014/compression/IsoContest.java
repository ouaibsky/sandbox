/*******
 * Read input from System.in
 * Use System.out.println to ouput your result.
 * Use:
 *  IsoContestBase.localEcho( variable)
 * to display variable in a dedicated area.
 * ***/
package org.icroco.mdf2014.compression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IsoContest {
    public static void main(String[] argv) throws Exception {
        String line;
        Scanner sc = new Scanner(System.in);

        line = sc.nextLine();
        char[] values = line.trim().toCharArray();
        int repeat = 0;
        char value = 0;
        System.err.println("Array: "+ Arrays.toString(values));

        for (int i = 0; i < values.length; i++) {
            if (repeat == 0) {
                value = values[i];
                repeat = 1;
            } else if (values[i] == value) {
                repeat++;
            } else {
                System.out.print((repeat == 1 ? value : (repeat > 2 ? repeat + String.valueOf(value) : String.valueOf(value) + String.valueOf(value))));
                value = values[i];
                repeat = 1;
            }
        }
        System.out.println((repeat == 1 ? value : (repeat > 2 ? repeat + String.valueOf(value) : String.valueOf(value) + String.valueOf(value))));

	/* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }
}
/* 
 * DO NOT PASTE THIS UTILITY CODE BACK INTO THE BROWSER WINDOW
 */

