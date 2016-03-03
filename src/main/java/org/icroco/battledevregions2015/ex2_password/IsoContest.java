package org.icroco.battledevregions2015.ex2_password;
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
        IsoContestBase.localEcho("Read nb pwd: "+aInput.removeFirst());


        int nbValid = 0;
        List<Line> lines = getAsList(aInput);
        for (Line l: lines) {
            if (l.password.length() == 6) {
                if (l.password.matches("[a-zA-Z][0-9][a-zA-Z]{4}"))
                    nbValid++;
            }

        }
        IsoContestBase.localEcho("");

        IsoContestBase.localEcho("result, nbValid: "+nbValid);
        output.add(""+nbValid);
        return output;
    }

    public static class Line {
        String password;

        public Line(final String oneLine) {
            password = oneLine.trim();           //                           // TODO: change type: int, string, ...
            IsoContestBase.localEcho(String.format("Read Line: %1$s", password));
        }

        @Override
        public String toString() {
            return "Line{" +
                    "password='" + password + '\'' +
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


