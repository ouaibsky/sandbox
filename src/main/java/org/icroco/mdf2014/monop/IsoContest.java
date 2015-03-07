/*******
 * Read input from System.in
 * Use System.out.println to ouput your result.
 * Use:
 *  IsoContestBase.localEcho( variable)
 * to display variable in a dedicated area.
 * ***/
package org.icroco.mdf2014.monop;

import java.util.Arrays;
import java.util.Scanner;

import static org.icroco.util.StringUtil8.convertArray;

public class IsoContest {
    public static void main(String[] argv) throws Exception {
        String line;
        Scanner sc = new Scanner(System.in);
        int pos = 1;
        String [] colors = {"violet", "orange", "jaune", "vert", "rose", "bleu" };
        int amount = sc.nextInt();
        sc.nextLine();
        System.err.println("amount: " + amount);
        String sLine[] = sc.nextLine().split("\\s+");
        Integer [] cashOut = convertArray(sLine, Integer::parseInt, Integer[]::new);
        System.err.println("cashOut: " + Arrays.toString(cashOut));
        sLine = sc.nextLine().split("\\s+");
        Integer [] game = convertArray(sLine, Integer::parseInt, Integer[]::new);
        System.err.println("game: " + Arrays.toString(game));

        if (game.length % 2 != 0)
            throw new IllegalArgumentException("Nombre impairs: "+game);

        for (int i = 0; i < game.length; i+=2) {
            int count = game[i] + game[i+1];
            pos += count;
            pos = pos%40;
            if (pos == 20)
                pos = 10;
            int old = amount;
            amount -= cashOut[pos-1];
            System.err.println("old: " +old+" game: "+count+" pos: "+pos+" loss: "+cashOut[pos-1]+" new: "+amount);
            if (amount <= 0) {
                System.out.println(pos);
                break;
            }
        }

        //System.out.println(colors[pos % 48 % 6]);
	/* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }


}
/* 
 * DO NOT PASTE THIS UTILITY CODE BACK INTO THE BROWSER WINDOW
 */

