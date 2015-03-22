package org.icroco.mdf2015.ex5;

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

        while (sc.hasNextLine())
            input.add(sc.nextLine());

        List<String> output = getSolution(input);

        for (String s : output)
            System.out.println(s);
    }

    static class Pair implements Comparable<Pair> {

        String name;
        int len;

        public Pair(String name, int len) {
            this.name = name;
            this.len = len;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (name != pair.name) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }

        @Override
        public int compareTo(Pair o) {
            return o.len - len;
        }
    }

    static List<String> getSolution(LinkedList<String> aInput) {
        List<String> output = new ArrayList<>();

        int size = Integer.parseInt(aInput.removeFirst().trim());
        LinkedList<Pair> MM = new LinkedList<>();
        LinkedList<Pair> FF = new LinkedList<>();
        LinkedList<Pair> MF = new LinkedList<>();
        LinkedList<Pair> FM = new LinkedList<>();


        while (aInput.size() != 0) {
            String[] SA = aInput.removeFirst().split("\\s+");
            String type = SA[0];
            int len = Integer.parseInt(SA[1]);
            Pair P = new Pair(type, len);
            switch (type) {
                case "M-M":
                    MM.add(P);
                    break;
                case "F-F":
                    FF.add(P);
                    break;
                case "M-F":
                    MF.add(P);
                    break;
                case "F-M":
                    FM.add(P);
                    break;
            }
        }

        Collections.sort(MM);
        Collections.sort(FF);
        Collections.sort(MF);
        Collections.sort(FM);

        LinkedList<Pair> TEMP = new LinkedList<>();

        while (true) {
            if (!MM.isEmpty() && !FF.isEmpty()) {
                TEMP.add(MM.removeFirst());
                TEMP.add(FF.removeFirst());
            } else
                break;
        }



        LinkedList<Pair> TEMP2 = new LinkedList<>();
        for (int i = 0; i < TEMP.size(); i += 2) {
            Pair p = TEMP.get(i);
            TEMP2.add(p);
            if (p.name.endsWith("M")) {
                while (!FM.isEmpty()) {
                    TEMP2.add(FM.removeFirst());
                }
                TEMP2.add(TEMP.get(i + 1));
            }

        }

        //if (!TEMP2.isEmpty() && TEMP2.peekLast().name.endsWith("F"))
            TEMP2.addAll(MF);

        // TODO Fill input
        output.add("" + compute(TEMP2));
        return output;
    }


    public static int compute(List<Pair> all) {
        int res = 0;

        for (Pair p : all)
            res += p.len;
        return res;
    }

    public static Integer[] toIntArray(String line) {
        String[] SA = line.split("\\s+");
        Integer[] IA = new Integer[SA.length];
        for (int i = 0; i < SA.length; i++)
            IA[i] = Integer.parseInt(SA[i]);

        return IA;
    }

}

class IsoContestBase {
    public static void localEcho(String txt) {
        System.err.println(txt);
    }
}