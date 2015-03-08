package org.icroco.tosa.ex3;

import java.nio.charset.CharsetEncoder;
import java.util.*;


/**
 * imp
 * Created by christophe on 07/03/15.
 */
public class IsoContest {
    public static void main(String[] argv) throws Exception {
        String line;
        LinkedList<String> input = new LinkedList<>();
        Scanner sc = new Scanner(System.in);

        line = sc.nextLine();
        input.add(line);

        List<String> output = getSolution(input);

        for (String s: output)
            System.out.println(s);
    }

    static class Pair implements Comparable<Pair> {
        Integer i;
        String w;
        Integer orderOrigine;

        public Pair(Integer i, String w, Integer aOrderOrigine) {
            this.i = i;
            this.w = w;
            this.orderOrigine = aOrderOrigine;
        }

        @Override
        public int compareTo(Pair o) {
            if (i == o.i)
                return o.orderOrigine - orderOrigine;
            return i-o.i;
        }
    }

    static List<String> getSolution(LinkedList<String> aInput) {
        LinkedList<String> output = new LinkedList<>();
        Map<Character, Integer> score = new HashMap<>();

        String first = aInput.removeFirst();
        LinkedList<String> values = new LinkedList<>(Arrays.asList(first.split("\\s+")));

        while (!values.isEmpty()) {
            Character c = values.removeFirst().charAt(0);
            Integer s = Integer.parseInt(values.removeFirst());
            score.put(c, s);
        }
        List<Pair> sorted = new LinkedList<>();

        int index = 0;
        for (String word: aInput) {
            sorted.add(new Pair(compute(score, word), word, ++index));
        }
        Collections.sort(sorted);
        Collections.reverse(sorted);

        for (Pair p: sorted)
            output.add(p.w);

        return output;
    }

    private static Integer compute(Map<Character, Integer> score, String word) {
        int res = 0;
        for (Character c: word.toCharArray()) {
            Integer p = score.get(c);
            res+= p == null ? 0 : p;
        }

        return res;
    }

}


class IsoContestBase {
    public static void localEcho( String txt ) {
        System.err.println( txt );
    }
}

