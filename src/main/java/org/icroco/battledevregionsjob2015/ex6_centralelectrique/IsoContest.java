package org.icroco.battledevregionsjob2015.ex6_centralelectrique;
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
        LinkedList<String> output   = new LinkedList<>();
        int                nbLines  = Integer.parseInt(aInput.removeFirst());
        int                resultat = 0;
        IsoContestBase.localEcho("Read nbLine: " + nbLines);

        List<Cell> prises = new ArrayList<>();
        List<Cell> prisesOK = new ArrayList<>();
        while (!aInput.isEmpty()) {
            Integer[] tuple = toIntArray(aInput.removeFirst());
            prises.add(new Cell(tuple[0], tuple[1]));
        }

        Collections.sort(prises, new Comparator<Cell>() {
            @Override
            public int compare(final Cell o1, final Cell o2) {
                return Integer.compare(o1.a, o2.a);
            }
        });

        for (Cell c: prises) {
            if (!croisent(prisesOK, c))   {
                prisesOK.add(c);
            }
        }

        IsoContestBase.localEcho("sorted: " + prisesOK.size());
        IsoContestBase.localEcho("");

        IsoContestBase.localEcho("result: " + prisesOK.size());
        output.add("" + resultat);
        IsoContestBase.localEcho("----");
        IsoContestBase.localEcho("");
        return output;
    }

    private static boolean croisent(final List<Cell> aPrises, final Cell aC) {
        if (aPrises.isEmpty())
            return false;

        for (Cell c: aPrises) {
            if (aC.a >= c.a)
                continue;
        }

        return false;
    }

    public static class Cell {
        int a;
        int b;

        public Cell(final int aA, final int aB) {
            a = aA;
            b = aB;
        }

        @Override
        public String toString() {
            return a + " " + b;
        }
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


