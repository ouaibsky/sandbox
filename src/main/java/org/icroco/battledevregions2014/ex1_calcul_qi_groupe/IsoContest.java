package org.icroco.battledevregions2014.ex1_calcul_qi_groupe;
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
        LinkedList<String> output       = new LinkedList<>();
        int                participants = Integer.parseInt(aInput.removeFirst().trim());
        IsoContestBase.localEcho("Read amount: " + participants);

        int qiMin = Integer.MAX_VALUE;
        while (!aInput.isEmpty()) {
            int qi = Integer.valueOf(aInput.removeFirst());
            if (qi < qiMin)
                qiMin = qi;
        }

        int moyenne = qiMin / participants;

        IsoContestBase.localEcho("");

        IsoContestBase.localEcho("result: " + moyenne);
        output.add("" + moyenne);
        IsoContestBase.localEcho("----");
        IsoContestBase.localEcho("");
        return output;
    }

}
// END COPY


