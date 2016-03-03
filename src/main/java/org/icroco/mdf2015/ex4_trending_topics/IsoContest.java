package org.icroco.mdf2015.ex4_trending_topics;
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
        LinkedList<String> output = new LinkedList<>();
        int                nb     = Integer.parseInt(aInput.removeFirst().trim());
        IsoContestBase.localEcho("Read nb: " + nb);

        int                i      = 0;
        LinkedList<String> values = new LinkedList<>();
        while (!aInput.isEmpty()) {
            values.add(aInput.removeFirst());
            if (values.size() < 60) {
                continue;
            }
            String res = computeTrend(values);
            if (res != null) {
                output.add(res);
                break;
            }
            values.removeFirst();
        }

        if (output.isEmpty()) {
            output.add("Pas de trending topic");
        }
        IsoContestBase.localEcho("");
        IsoContestBase.localEcho("----");
        IsoContestBase.localEcho("");
        return output;
    }

    private static String computeTrend(final LinkedList<String> aValues) {
        Map<String, Integer> cache = new HashMap<>();
        for (String s : aValues)
            if (cache.containsKey(s)) {
                int nb = cache.get(s) + 1;
                cache.put(s, nb);
                if (nb == 40)
                    return s;
            } else {
                cache.put(s, 1);
            }
        return null;
    }

}


// END COPY


