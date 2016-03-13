package org.icroco.battledev42mai2014.ex4_scorescrable;
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
            input.add(sc.nextLine().trim());

        List<String> output = getSolution(input);

        for (String s : output)
            System.out.println(s);
    }

    static List<String> getSolution(LinkedList<String> aInput) {
        LinkedList<String> output    = new LinkedList<>();
        int                firstLine = Integer.parseInt(aInput.removeFirst());
        //Integer            firstLine = toIntArray(aInput.removeFirst());  // TODO: remove or uncomment
        int                resultat  = 0;
        IsoContestBase.localEcho("Read firstLine: " + firstLine);

        while (!aInput.isEmpty()) {
            int value = Integer.valueOf(aInput.removeFirst());  // TODO: remove or uncomment
            // Integer[] line = toIntArray(aInput.removeFirst());  // TODO: remove or uncomment
            // List<Line> lines = getAsList(aInput);               // TODO: remove or uncomment
        }

        IsoContestBase.localEcho("");

        IsoContestBase.localEcho("result: " + resultat);
        output.add("" + resultat);
        IsoContestBase.localEcho("----");
        IsoContestBase.localEcho("");
        return output;
    }

    // TODO: refactor class name if it's help understanding.
    public static class Line {
        // TODO refactor attributes if it make sens.
        int x;
        int y;

        public Line(final String oneLine) {
            String[] SA = oneLine.trim().split("\\s+"); // TODO replace speparator: "," or ":"
            x = Integer.parseInt(SA[0]);           //                           // TODO: change type: int, string, ...
            y = Integer.parseInt(SA[1]);           // TODO: change type: int, string, ...
            IsoContestBase.localEcho(String.format("Read Line: %1$s %2$s", x, y));
        }

    }


    /**
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
}
// END COPY


