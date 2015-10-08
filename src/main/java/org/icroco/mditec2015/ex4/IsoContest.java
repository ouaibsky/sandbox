package org.icroco.mditec2015.ex4;

import java.util.*;


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

        int amountToDeliver = Integer.parseInt(aInput.removeFirst().trim());

        aInput.removeFirst(); // bypass

//        String[] types = aInput.removeFirst().trim().split(" ");
//        Integer[] iType = new Integer[types.length];
//        for (int i = 0; i < types.length; i++) {
//            iType[i] = Integer.valueOf(types[i]);
//        }

        List<Integer> nTypes = new ArrayList<Integer>();



        while(aInput.size() != 0) {
            nTypes.add(Integer.parseInt(aInput.removeFirst().trim()));


        }
        Collections.sort(nTypes, Collections.reverseOrder());

        int remaining = amountToDeliver;
        String res = "";
        for (Integer note: nTypes)
        {
            int nb = remaining / note;
            if (nb > 0) {
                res+= ""+nb+" "+note+" ";
                remaining = remaining%note;
            }


        }

        output.add(res.trim());
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

