package org.icroco.mdf2015.ex1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


/**
 * imp
 * Created by christophe on 07/03/15.
 */
public class IsoContest {
    public static void main(String[] argv) throws Exception {
        String line;
        Scanner sc = new Scanner(System.in);

        LinkedList<String> input = new LinkedList<>();

        while(sc.hasNextLine())
            input.add(sc.nextLine());

        List<String> output = getSolution(input);

        for (String s: output)
            System.out.println(s);
    }

    static List<String> getSolution(LinkedList<String> aInput) {
        List<String> output = new ArrayList<>();

        int amount = Integer.parseInt(aInput.removeFirst().trim());
        int nbTour = Integer.parseInt(aInput.removeFirst().trim());


        while(aInput.size() != 0) { // TODO
            String[] SA = aInput.removeFirst().split("\\s+");
            int X = Integer.parseInt(SA[0]);
            int Y = Integer.parseInt(SA[1]);
            amount = amount - X + Y;
        }


        output.add(""+amount);
        return output;
    }

    public  static Integer[] toIntArray(String line) {
        String[] SA = line.split("\\s+");
        Integer[]IA = new Integer[SA.length];
        for (int i = 0 ; i < SA.length ; i++)
            IA[i] = Integer.parseInt(SA[i]);

        return IA;
    }

}


class IsoContestBase {
    public static void localEcho( String txt ) {
        System.err.println( txt );
    }
}

