/*******
 * Read input from System.in
 * Use System.out.println to ouput your result.
 * Use:
 *  IsoContestBase.localEcho( variable)
 * to display variable in a dedicated area.
 * ***/
package org.icroco.mdf2014.geoplane;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.icroco.mdf2014.geoplane.IsoContestBase.*;

public class IsoContest {

    public static void main(String[] argv) throws Exception {
        String line;
        Scanner sc = new Scanner(System.in);

        int xMin = Integer.MAX_VALUE;
        int yMin = Integer.MAX_VALUE;
        int xMax = Integer.MIN_VALUE;
        int yMax = Integer.MIN_VALUE;

        int nbRect = sc.nextInt();

        for (int i = 0; i < nbRect; i++) {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();

            // mormalize
            int xLower = Math.min(x1, x2);
            int yLower = Math.min(y1, y2);
            int xUpper = Math.max(x1, x2);
            int yUpper = Math.max(y1, y2);

            // bigger rect.
            xMin = Math.min(xMin, xLower);
            yMin = Math.min(yMin, yLower);
            xMax = Math.max(xMax, xUpper);
            yMax = Math.max(yMax, yUpper);

            System.err.printf("x: %d, y: %d, x: %d, y: %d%n", xLower, yLower, xUpper, yUpper);
            System.err.printf("%d %d %d %d %d %d %d %d%n",xMin, yMin, xMin, yMax, xMax, yMin, xMax, yMax);
        }

        System.out.printf("%d %d %d %d %d %d %d %d%n", xMin, yMin, xMin, yMax, xMax, yMin, xMax, yMax);

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