package org.icroco.tosa.ex9_social;


import sun.plugin.viewer.MNetscapePluginObject;

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

        while (sc.hasNextLine()) {
            line = sc.nextLine(); // TODO check if applicable
            input.add(line);
            //System.err.println(line);
        }

        List<String> output = getSolution(input);

        for (String s : output)
            System.out.println(s);
    }

    static final class People {
        String name;

        public People(String name) {
            this.name = name;
        }

        LinkedList<String> refused = new LinkedList<>();
        LinkedList<People> children = new LinkedList<>();

        public Set<String> findFriend(int distance) {
            Set<String> s =  new TreeSet<>();
            s.add(name);
            if (distance == 0) {
                return s;
            }

            for (People p: children) {
                Set<String> amis = p.findFriend(distance - 1);
                for (String a: amis) {
                    if (!refused.contains(a)) {
                        s.add(a);
                    }
                }
            }
            return s;
        }
    }

    static List<String> getSolution(LinkedList<String> aInput) {
        LinkedList<String> output = new LinkedList<>();

        Map<String, People> maps = new HashMap<>();
        while(aInput.size() > 1) {
            IsoContestBase.localEcho("***********");
            IsoContestBase.localEcho("input: "+aInput.getFirst());
            String[] A = aInput.removeFirst().split("\\s+");
            People P = maps.get(A[0]);
            if (P == null) {
                P = new People(A[0]);
                maps.put(A[0], P);
            }
            if (A[1].equals("-")) {
                P.refused.add(A[2]);
            } else {
                People p2 = maps.get(A[2]);
                if (p2 == null) {
                    p2 = new People(A[2]);
                    maps.put(A[2], p2);
                }
                P.children.add(p2);
            }
        }

        String[] A = aInput.removeFirst().split("\\s+");
        IsoContestBase.localEcho("question: "+Arrays.toString(A));
        People start = maps.get(A[0]);
        int distance = Integer.parseInt(A[2]);

        Set<String> res = start.findFriend(distance);

        // concat
        String str = "";
        for (String s : res)
            str += s + " ";
        str = str.trim();
        output.add(str);


//        output.addAll(res);
        return output;
    }



    final static LinkedList<String> splitSpace(final String aLine) {
        return new LinkedList<>(Arrays.asList(aLine.split("\\s+")));
    }

    final Date parseDate(String format, String data) throws java.text.ParseException {
        final java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(format);
        return df.parse(data);
    }


    final static LinkedList<Integer> convertIntList(List<String> from) {
        final LinkedList<Integer> result = new LinkedList<>();
        for (String s: from) {
            result.add(Integer.parseInt(s, 16));
        }
        return result;
    }

}


class IsoContestBase {
    public static void localEcho( String txt ) {
        System.err.println( txt );
    }
}

