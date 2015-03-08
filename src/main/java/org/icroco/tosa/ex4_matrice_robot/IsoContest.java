package org.icroco.tosa.ex4_matrice_robot;

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

        line = sc.nextLine();
        input.add(line);

        while(sc.hasNextLine())
            input.add(sc.nextLine()); //

        List<String> output = getSolution(input);

        for (String s: output)
            System.out.println(s);
    }

    public static class Coord {
        int x;
        int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Coord coord = (Coord) o;

            if (x != coord.x) return false;
            if (y != coord.y) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

    static List<String> getSolution(LinkedList<String> aInput) {
        LinkedList<String> output = new LinkedList<>();

        Integer seqSize = Integer.parseInt(aInput.removeFirst());
        List<String> seqences = new ArrayList<>();
        char[] C = aInput.removeFirst().toCharArray();

        for (int i = 0; i < C.length; i++) {
            if (i + seqSize < C.length) {
                seqences.add(new String(Arrays.copyOfRange(C, i, i + seqSize)));
                IsoContestBase.localEcho(seqences.get(i));
            }
        }

        int maxTrajet = 0;
        String maxSeq = null;

        for (String seq: seqences) {
            int x = 0, y = 0;
            Set<Coord> trajet = new HashSet<>();
            trajet.add(new Coord(x, y));
            for (char c : seq.toCharArray()) {
                switch (c) {
                    case 'O':
                        x = x-1;
                        break;
                    case 'E':
                        x = x+1;
                        break;
                    case 'N':
                        y = y -1;
                        break;
                    case 'S':
                        y = y +1;
                        break;
                }
                trajet.add(new Coord(x, y));
            }
            if (trajet.size()  > maxTrajet) {
                maxTrajet = trajet.size();
                maxSeq = seq;
            }
        }

        output.add(""+maxTrajet);
        output.add(maxSeq);

        return output;
    }


    final static LinkedList<String> splitSpace(final String aLine) {
        return new LinkedList<>(Arrays.asList(aLine.split("\\s+")));
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

