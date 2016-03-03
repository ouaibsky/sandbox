package org.icroco.battledevregions2014.ex2_sous_sequence;
// START COPY

import java.util.*;


/**
 * imp
 * Created by christophe on 07/03/15.
 */
public class IsoContest {
    public static void main(String[] argv) throws Exception {
        String  line;
        Scanner sc = new Scanner(System.in);

        LinkedList<String> input = new LinkedList<>();

        while (sc.hasNextLine())
            input.add(sc.nextLine());

        List<String> output = getSolution(input);

        for (String s : output)
            System.out.println(s);
    }

    static List<String> getSolution(LinkedList<String> aInput) {
        LinkedList<String> output  = new LinkedList<>();
        String             chaine1 = aInput.removeFirst().trim();
        String             chaine2 = aInput.removeFirst().trim();
        IsoContestBase.localEcho("Read chaine1: " + chaine1);
        IsoContestBase.localEcho("Read chaine2: " + chaine2);


        char[] c1 = chaine1.toCharArray();
        char[] c2 = chaine2.toCharArray();

        int     i  = 0;
        int     j  = 0;
        for (i = 0; i < c1.length && j < c2.length; i++, j++) {
            for (; j < c2.length; j++) {
                if (c1[i] == c2[j]) {
                    break;
                }
            }
            if (j == c2.length)
                break;
        }

        if (i == c1.length)
            output.add("OK");
        else
            output.add("NOK " + (i));

        IsoContestBase.localEcho("res: " + output.get(0));
        IsoContestBase.localEcho("----");
        IsoContestBase.localEcho("");
        return output;
    }

}
// END COPY


