package org.icroco.mditec2015.ex1;

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

        for (String s: output) {
            System.out.println(s);
        }
    }

    static List<String> getSolution(LinkedList<String> aInput) {
        List<String> output = new ArrayList<>();

        // TOO: Assume first line is size, if not remove
        int initialBalance = Integer.parseInt(aInput.removeFirst().trim());
        int nbInst = Integer.parseInt(aInput.removeFirst().trim());

        while(aInput.size() != 0) {
            int inst = Integer.parseInt(aInput.removeFirst().trim());
            initialBalance = initialBalance + inst;

        }

        output.add(""+initialBalance);
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

