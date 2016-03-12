package org.icroco.mdf2015.ex5_rallonges_courant;
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
        LinkedList<String> output      = new LinkedList<>();
        int                nbRallonges = Integer.parseInt(aInput.removeFirst().trim());
        IsoContestBase.localEcho("Read nbRallonges: " + output);


        List<Line>                    lines = getAsList(aInput);
        Map<String, LinkedList<Line>> stock = new HashMap<>();
        for (Line l : lines) {
            if (!stock.containsKey(l.type)) {
                stock.put(l.type, new LinkedList<Line>());
            }
            stock.get(l.type).add(l);
            Collections.sort(stock.get(l.type));
            Collections.reverse(stock.get(l.type));
        }
        IsoContestBase.localEcho("");
        LinkedList<Line> MM     = stock.get("M-M");
        LinkedList<Line> FF     = stock.get("F-F");
        LinkedList<Line> MF     = stock.get("M-F");
        LinkedList<Line> chaine = new LinkedList<>();

        while (!MM.isEmpty() && !FF.isEmpty()) {
            chaine.add(MM.removeFirst());
            chaine.add(FF.removeFirst());
        }
        if (!MM.isEmpty() && chaine.getLast().type.equalsIgnoreCase("FF")) {
            chaine.add(MM.removeFirst());
        } else if (!FF.isEmpty() && chaine.getLast().type.equalsIgnoreCase("MM")) {
            chaine.add(FF.removeFirst());
        }

        chaine.addAll(MF);
        int length = 0;
        for (Line l : chaine) {
            length += l.length;
        }


        IsoContestBase.localEcho("result, amount: " + length);
        output.add("" + length);
        IsoContestBase.localEcho("----");
        IsoContestBase.localEcho("");
        return output;
    }

    // TODO: refactor class name if it's help understanding.
    public static class Line implements Comparable<Line> {
        // TODO refactor attributes if it make sens.
        String type;
        int    length;

        public Line(final String oneLine) {
            String[] SA = oneLine.trim().split("\\s+"); // TODO replace speparator: "," or ":"
            type = SA[0].toUpperCase();
            if (type.equalsIgnoreCase("F-M") || type.equalsIgnoreCase("M-F"))
                type = "M-F";
            length = Integer.parseInt(SA[1]);           // TODO: change type: int, string, ...
            IsoContestBase.localEcho(String.format("Read Line: %1$s %2$s", type, length));
        }

        @Override
        public int compareTo(final Line o) {
            return Integer.compare(length, o.length);
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

}
// END COPY


