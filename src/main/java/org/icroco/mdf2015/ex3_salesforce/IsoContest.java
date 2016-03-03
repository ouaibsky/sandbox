package org.icroco.mdf2015.ex3_salesforce;
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
        int                nb     = Integer.parseInt(aInput.removeFirst().trim());
        IsoContestBase.localEcho("Read nb: " + nb);

        List<String> pays = Arrays.asList(aInput.removeFirst().trim().split(";"));
        IsoContestBase.localEcho("pays: " + pays);


        List<Line> lines   = getAsList(aInput);
        List<Line> valid   = new ArrayList<>();
        List<Line> doublon = new ArrayList<>();
        //int             doublon = 0;
        int telKo  = 0;
        int paysKo = 0;
        for (Line l : lines) {
            if (valid.contains(l) || doublon.contains(l)) {
                doublon.add(l);
                valid.remove(l);
                //doublon++;
            } else {
                valid.add(l);
                if (l.telephone.matches("^[+]([0-9]){1,3}[-]([0-9]){9,11}")) {
                    if (pays.contains(l.pays)) {
                        valid.add(l);
                    } else {
                        paysKo++;
                    }
                } else {
                    telKo++;
                    if (!pays.contains(l.pays)) {
                        paysKo++;
                    }
                }
            }
        }
        IsoContestBase.localEcho("");

        IsoContestBase.localEcho("result: d:" + doublon.size() + " p: " + telKo + " t: " + paysKo);
        output.add("" + doublon.size() + " " + telKo + " " + paysKo);
        IsoContestBase.localEcho("----");
        IsoContestBase.localEcho("");
        return output;
    }

    // TODO: refactor class name if it's help understanding.
    public static class Line {
        // TODO refactor attributes if it make sens.
        String nom;
        String prenom;
        String societe;
        String telephone;
        String pays;

        public Line(final String oneLine) {
            String[] SA = oneLine.trim().split(";"); // TODO replace speparator: "," or ":"
            nom = SA[0];           //                           // TODO: change type: int, string, ...
            prenom = SA[1];           // TODO: change type: int, string, ...
            societe = SA[2];           // TODO: change type: int, string, ...
            telephone = SA[3];           // TODO: change type: int, string, ...
            pays = SA[4];           // TODO: change type: int, string, ...
            //IsoContestBase.localEcho(String.format("Read Line: %1$s %2$s", x, y));
        }

        @Override
        public boolean equals(final Object aO) {
            if (this == aO) return true;
            if (aO == null || getClass() != aO.getClass()) return false;

            final Line line = (Line) aO;

            if (nom != null ? !nom.equals(line.nom) : line.nom != null) return false;
            if (prenom != null ? !prenom.equals(line.prenom) : line.prenom != null) return false;
            return societe != null ? societe.equals(line.societe) : line.societe == null;

        }

        @Override
        public int hashCode() {
            int result = nom != null ? nom.hashCode() : 0;
            result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
            result = 31 * result + (societe != null ? societe.hashCode() : 0);
            return result;
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

    public static Integer[] toIntArray(String line) {
        String[]  SA = line.split("\\s+");
        Integer[] IA = new Integer[SA.length];
        for (int i = 0; i < SA.length; i++)
            IA[i] = Integer.parseInt(SA[i]);

        return IA;
    }
}
// END COPY


