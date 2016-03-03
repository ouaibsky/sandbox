package org.icroco.mdf2015.ex2_nuagesdetags;
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
        int          nbTag   = Integer.parseInt(aInput.removeFirst().trim());
        IsoContestBase.localEcho("Read amount: "+nbTag);

        List<Line> lines = getAsList(aInput);
        Map<String, Line> occurence = new HashMap<>();
        for (Line l: lines) {
           Line found = occurence.get(l.tag);
            if (found == null) {
                found = l;
                occurence.put(found.tag, found);
            }
            found.nb++;
        }

        List<Line> values = new ArrayList<>(occurence.values());
        Collections.sort(values, new Comparator<Line>() {
            @Override
            public int compare(final Line o1, final Line o2) {
                return o1.nb >= o2.nb ? - 1 : (o1.nb > o2.nb ? 1 : 0);
            }
        });


        for (int i = 0; i < 5; i++) {
            output.add(values.get(i).toString());
            IsoContestBase.localEcho(values.get(i).toString());

        }
        IsoContestBase.localEcho("");

        IsoContestBase.localEcho("----");
        IsoContestBase.localEcho("");
        return output;
    }

    // TODO: refactor class name if it's help understanding.
    public static class Line {
        // TODO refactor attributes if it make sens.
        String     tag;
        int     nb;

        public Line(final String oneLine) {
            tag = oneLine.trim();
            nb = 0;
            //IsoContestBase.localEcho(String.format("Read Line: %1$s %2$s", x, y));
        }

        @Override
        public String toString() {
            return tag+ " " + nb;
        }

        @Override
        public boolean equals(final Object aO) {
            if (this == aO) return true;
            if (aO == null || getClass() != aO.getClass()) return false;

            final Line line = (Line) aO;

            return tag != null ? tag.equals(line.tag) : line.tag == null;

        }

        @Override
        public int hashCode() {
            return tag != null ? tag.hashCode() : 0;
        }
    }


    /**
     *
     * @param aInput
     * @return
     */
    public static List<Line> getAsList(LinkedList<String> aInput) {
        ArrayList<Line> lines = new ArrayList<>();
        for (String str : aInput) {
            lines.add(new Line(str));
        }
        return lines;
    }




    public static Integer[] toIntArray(String line) {
        String[]  SA = line.split("\\s+");
        Integer[] IA = new Integer[SA.length];
        for (int i = 0; i < SA.length; i++)
            IA[i] = Integer.parseInt(SA[i]);

        return IA;
    }
}
    // END COPY


