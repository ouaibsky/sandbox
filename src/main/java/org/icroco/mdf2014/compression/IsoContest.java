/*******
 * Read input from System.in
 * Use System.out.println to ouput your result.
 * Use:
 *  IsoContestBase.localEcho( variable)
 * to display variable in a dedicated area.
 * ***/
package org.icroco.mdf2014.compression;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IsoContest {
    public static void main(String[] argv) throws Exception {
        String line;
        Scanner sc = new Scanner(System.in);
        int pos = 0;
        String [] colors = {"violet", "orange", "jaune", "vert", "rose", "bleu", };

        List<Integer> values = new ArrayList<>(20);
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            values.add(Integer.valueOf(line.trim()));
            /* Lisez les données et effectuez votre traitement */
        }
        for (Integer v: values) {
            pos += v;
       }
        IsoContestBase.localEcho("pos: " + pos);
        IsoContestBase.localEcho("pos r: " + (pos % 48 % 6));
        System.out.println(colors[pos % 48 % 6]);
	/* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }
}
/* 
 * DO NOT PASTE THIS UTILITY CODE BACK INTO THE BROWSER WINDOW
 */

class IsoContestBase {
    public static void localEcho(String txt) {
        System.err.println(txt);
    }
}