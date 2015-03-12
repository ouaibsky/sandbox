package org.icroco.mdf2015.ex4;

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

        int size = Integer.parseInt(aInput.removeFirst().trim()); // TODO
        LinkedList<String> SL = new LinkedList<>();

        String res = null;
        boolean first = true;
        while(aInput.size() != 0) {

           String hash = aInput.removeFirst();
            //IsoContestBase.localEcho(hash);
            if (SL.size() >= 60)
            {
                if (first)
                    first = false;
                else
                    SL.removeFirst();
                SL.add(hash);
                res = getfirsTopic(SL);
                if (res != null)
                    break;
            }
            else
                SL.add(hash);
        }
        if (!SL.isEmpty())
            res = getfirsTopic(SL);

        String KO = "Pas de trending topic";
        output.add(res == null ? KO : res);
        IsoContestBase.localEcho("*****");
        return output;
    }

    private static String getfirsTopic(LinkedList<String> aQueue)  {
        Map<String, Integer> maps = new HashMap<>();
        for(String s: aQueue) {
            if (maps.containsKey(s))
                maps.put(s, maps.get(s)+1);
            else
                maps.put(s, 1);
        }

        for (String s: aQueue) {
            if (maps.get(s) >= 40)
                return s;
        }

        return null;
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

