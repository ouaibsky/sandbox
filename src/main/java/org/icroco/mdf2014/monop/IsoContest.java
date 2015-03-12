/*******
 * Read input from System.in
 * Use System.out.println to ouput your result.
 * Use:
 *  IsoContestBase.localEcho( variable)
 * to display variable in a dedicated area.
 * ***/
package org.icroco.mdf2014.monop;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class IsoContest {
    public static void main(String[] argv) throws Exception {
        String line;
        Scanner sc = new Scanner(System.in);
        int pos = 1;
        String [] colors = {"violet", "orange", "jaune", "vert", "rose", "bleu" };
        int amount = sc.nextInt();
        sc.nextLine();
        System.err.println("amount: " + amount);


        Integer [] cashOut = toIntArray(sc.nextLine());
        System.err.println("cashOut: " + Arrays.toString(cashOut));

        Integer [] game =  toIntArray(sc.nextLine());

        System.err.println("game: " + Arrays.toString(game));


        String[] SA = "fdfdd".split("\\s+");


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

    public  static Integer[] toIntArray(String line) {
        String[] SA = line.split("\\s+");
        Integer[]IA = new Integer[SA.length];
        for (int i = 0 ; i < SA.length ; i++)
            IA[i] = Integer.parseInt(SA[i]);

        return IA;
    }

    public static Integer[][] extractMatrix(List<String> aInput, int xSize) {
        Integer[][] result = new Integer[aInput.size()][xSize];

        for (int i = 0; i < aInput.size(); i++) {
            String[] numbers = aInput.get(i).split("\\s+");
            for (int j = 0; j < numbers.length; j++) {
                result[i][j] = Integer.parseInt(numbers[j]);
            }
        }
        return result;
    }

}
/* 
 * DO NOT PASTE THIS UTILITY CODE BACK INTO THE BROWSER WINDOW
 */

