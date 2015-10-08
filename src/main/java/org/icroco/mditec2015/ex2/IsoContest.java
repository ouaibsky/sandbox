package org.icroco.mditec2015.ex2;

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

        int size = Integer.parseInt(aInput.removeFirst().trim());
        int nbValidCard = 0;

        while(aInput.size() != 0) {
            String creditCard = aInput.removeFirst().trim();
            String newwCard = "";
            if (creditCard.length() != 16)
                continue;


            char[] array = creditCard.toCharArray();
            int luneKey = Integer.valueOf(""+creditCard.charAt(15));
            int total = 0;

           for (int i = 0 ; i < 15 ; i++) {
              int v = Integer.valueOf(""+array[i]);
              int v2 = v * (i%2==0 ? 2 : 1);
              if (v2 > 9)
                  v2 = v2 - 9;
              newwCard += ""+v2;
              total+=v2;
          }
            int verif = total%10 == 0 ? 0 : 10 - (total%10);
            if (luneKey == verif)
                nbValidCard++;

        }

        output.add(""+nbValidCard);
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

