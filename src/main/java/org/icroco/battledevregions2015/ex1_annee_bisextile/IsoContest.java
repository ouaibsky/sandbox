package org.icroco.battledevregions2015.ex1_annee_bisextile;
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
        List<String> output = new ArrayList<>();
        IsoContestBase.localEcho("Read nb Lines: "+aInput.removeFirst());


        List<Line> lines = getAsList(aInput);
        for (Line l: lines) {
            if ((l.annee%4 == 0 && l.annee%100 != 0) || l.annee%400 == 0)
                output.add("BISSEXTILE");
            else
                output.add("NON BISSEXTILE");
        }
        IsoContestBase.localEcho("");

        return output;
    }

    // TODO: refactor class name if it's help understanding.
    public static class Line {
        // TODO refactor attributes if it make sens.
        int     annee;

        public Line(final String oneLine) {
            String[] SA = oneLine.trim().split("\\s+"); // TODO replace speparator: "," or ":"
            annee = Integer.parseInt(SA[0]);           //                           // TODO: change type: int, string, ...
            IsoContestBase.localEcho(String.format("Read Line: %1$s", annee));
        }

        @Override
        public String toString() {
            return "Line{" +
                    "annee=" + annee +
                    '}';
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

    public static SortedSet<Line> getAsSet(LinkedList<String> aInput) {
        SortedSet<Line> lines = new TreeSet<>();
        for (String str : aInput) {
            Line l = new Line(str);
            if (lines.contains(l)) {
                // TODO: already there, something different to do !!
            } else {
                lines.add(new Line(str));
            }
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

    // END COPY
}


