package org.icroco.mditec2015.ex6;

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

        String[] values = aInput.removeFirst().trim().split(" ");
        int W = Integer.valueOf(values[0]);
        int L = Integer.valueOf(values[1]);
        int H = Integer.valueOf(values[2]);

        Integer matrix[][] = new Integer[W][L];
        int i = 0;
        while(aInput.size() != 0) {
            matrix[i] = toIntArray(aInput.removeFirst().trim());   // TODO
            i++;
        }

        String res = "NO";
        int j = 0;
        int k = 0;
        for (; j < matrix.length; j++) {
            for (; k < matrix[j].length; k++) {
                if (matrix[j][k] < H) {
                    j++;
                    break;
                }
                //System.out.print(matrix[j][k]+" ");
            }
            //System.out.println("");

        }

        if ((j == matrix.length) && (k == matrix[0].length)) {
            res = "YES";
        }

        output.add(res);
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

