package org.icroco.battledevregions2015.ex5_polonaise_inverse;
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

        String          line   = aInput.removeFirst().trim();
        IsoContestBase.localEcho("Exp: "+line);

        String[] SA = line.trim().split("\\s+");
        LinkedList<String> queue = new LinkedList<>(Arrays.asList(SA));

        int amount = compute(queue);

        IsoContestBase.localEcho("");

        IsoContestBase.localEcho("result, amount: "+amount);
        output.add(""+amount);
        IsoContestBase.localEcho("----");
        IsoContestBase.localEcho("");
        return output;
    }

    private static int compute(final LinkedList<String> aQueue) {
        String value = aQueue.removeFirst();
        if (value.equals("*")) {
            return compute(aQueue) * compute(aQueue);
        }
        else if (value.equals("/")) {
            return compute(aQueue) / compute(aQueue);
        }
        else if (value.equals("+")) {
            return compute(aQueue) + compute(aQueue);
        }
        else if (value.equals("-")) {
            return compute(aQueue) - compute(aQueue);
        }
        else
            return Integer.parseInt(value);
    }

    // TODO: refactor class name if it's help understanding.
    public static class Line {
        // TODO refactor attributes if it make sens.
        int     x;
        int     y;

        public Line(final String oneLine) {
            String[] SA = oneLine.trim().split("\\s+"); // TODO replace speparator: "," or ":"
            x = Integer.parseInt(SA[0]);           //                           // TODO: change type: int, string, ...
            y = Integer.parseInt(SA[1]);           // TODO: change type: int, string, ...
            IsoContestBase.localEcho(String.format("Read Line: %1$s %2$s", x, y));
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
}
    // END COPY


