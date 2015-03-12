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

        while(sc.hasNextLine())
            input.add(sc.nextLine());

        List<String> output = getSolution(input);

        for (String s: output)
            System.out.println(s);
    }

    static class Pair {
        String name;
        int len;
        static int cpt = 1;
        int hash = cpt++;
        static List<String> MMNext = Arrays.asList("M-M", "M-F");
        static List<String> MFNext = Arrays.asList("F-F", "F-M");
        static List<String> FMNext = Arrays.asList("M-M", "M-F");
        static List<String> FFNext = Arrays.asList("F-F", "F-M");

        public Pair(String name, int len) {
            this.name = name;
            this.len = len;
        }

        List<String> getNextPossible() {
            if (name.equals("M-M") || name.equals("F-M"))
                return MMNext;
            else
                return MFNext;
        }

        public List<Pair> findMax(List<Pair> remaining, int max) {
            List<Pair> result = new LinkedList<>();
            result.add(this);

            max+=len;

            if (remaining.isEmpty())
                return result;

            List<String> next = getNextPossible();
            List<Pair> good = Collections.EMPTY_LIST;
            int x = 0;
            for (int i = 0; i < remaining.size(); i++) {
                if (next.contains(remaining.get(i)))
                {
                    List<Pair> newR = new ArrayList<>();
                    newR.addAll(remaining);
                    newR.remove(remaining.get(i));
                    int curMax = max;
                    List<Pair> newRes = remaining.get(i).findMax(newR, curMax);
                    if (curMax > x) {
                        x = curMax;
                        good = newRes;
                    }
                }
            }
            max+=x;
            result.addAll(good);
            return result;

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (hash != pair.hash) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return hash;
        }
    }

    static List<String> getSolution(LinkedList<String> aInput) {
        List<String> output = new ArrayList<>();

        int size = Integer.parseInt(aInput.removeFirst().trim()); // TODO
        List<Pair> all = new ArrayList<>();
        Set<String> MMNext = new HashSet<>(Arrays.asList("M-M", "M-F"));
        Set<String> MFNext = new HashSet<>(Arrays.asList("F-F", "F-M"));
        Set<String> FMNext = new HashSet<>(Arrays.asList("M-M", "M-F"));
        Set<String> FFNext = new HashSet<>(Arrays.asList("F-F", "F-M"));

        while(aInput.size() != 0) {
            String[] SA = aInput.removeFirst().split("\\s+");
            String type = SA[0];
            int len = Integer.parseInt(SA[1]);
            all.add(new Pair(type, len));
        }

        List<Pair> chaine = new ArrayList<>();

        int curMax = 0;

        for (Pair p: all) {
            if (p.name.startsWith("M")) {
                List<Pair> start =new ArrayList<>();
                start.addAll(all);
                start.remove(p);
                List<Pair> solutions = p.findMax(start, 0);
                int len = comput(solutions);
                if (len > curMax)
                {
                    curMax = len;
                }
            }
        }

        // TODO Fill input
        output.add(""+curMax);
        return output;
    }

    public static int comput(List<Pair> all) {
        int res = 0;

        for (Pair p: all)
            res+=p.len;
        return res;
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

