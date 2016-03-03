package org.icroco.battledevregions2015.ex3_plus_long_facteur_commun;
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

    static List<String> getSolution3(LinkedList<String> aInput) {
        LinkedList<String> output    = new LinkedList<>();
        int          nbFacteur = Integer.parseInt(aInput.removeFirst().trim());
        //IsoContestBase.localEcho("Read nb Facteur: " + nbFacteur);


        List<Line> lines = getAsList(aInput);
        for (Line l : lines) {
            String s = enumerateFactors(l.mot1, l.mot2);
            if(s.length() == 0)
                output.add("AUCUN FACTEUR");
            else
                output.add(s);
        }
        return output;
    }

    public static String enumerateFactors(String first, String second) {
        String longest = "";
        for (int i = 0; i < first.length(); i++) {
            for(int j = 0;  j< second.length(); j++){
                String s = computeCommonFactor(first.substring(i), second.substring(j));
                if(s.length() > longest.length())
                    longest = s;
                if(s.length() == longest.length() && s.compareTo(longest) < 0)
                    longest = s;
            }
        }
        return longest;
    }

    public static String computeCommonFactor(String first, String second) {
        if (first.length() == 0 || second.length() == 0)
            return "";
        if (first.charAt(0) == second.charAt(0))
            return first.charAt(0) + computeCommonFactor(first.substring(1), second.substring(1));
        return "";
    }

    static List<String> getSolution(LinkedList<String> aInput) {
        LinkedList<String> output    = new LinkedList<>();
        int          nbFacteur = Integer.parseInt(aInput.removeFirst().trim());
        //IsoContestBase.localEcho("Read nb Facteur: " + nbFacteur);


        List<Line> lines = getAsList(aInput);
        for (Line l : lines) {
            String facteur = l.findFacteur();
            if (facteur.length() == 0)
                output.add("AUCUN FACTEUR");
            else
                output.add(facteur);
            //IsoContestBase.localEcho(l+"  Found: "+output.getLast());
        }

//
//        IsoContestBase.localEcho("");
//
//        IsoContestBase.localEcho("----");
//        IsoContestBase.localEcho("");
        return output;
    }


    // TODO: refactor class name if it's help understanding.
    public static class Line {
        // TODO refactor attributes if it make sens.
        String mot1;
        String mot2;
        String res = "";

        public Line(final String oneLine) {
            String[] SA = oneLine.trim().split("\\s+"); // TODO replace speparator: "," or ":"
            mot1 = SA[0];           //                           // TODO: change type: int, string, ...
            mot2 = SA[1];           // TODO: change type: int, string, ...
            //IsoContestBase.localEcho(String.format("Read Line: %1$s %2$s", mot1, mot2));
        }

        @Override
        public String toString() {
            return "Line{" +
                    "mot1='" + mot1 + '\'' +
                    ", mot2='" + mot2 + '\'' +
                    '}';
        }

        public String findFacteur() {
//            while (res.isEmpty()) {
//                res = findFacteur(mot1, mot2);
//                if (mot1.length() == 1)
//                    return res;
//                mot1 = mot1.substring(1, mot1.length());
//            }
//            return res;
            String res1 = findFacteur(mot1, mot2);
            String res2 = findFacteur(mot2, mot1);
            if (res1.length() > res2.length()) {
                return res1;
            }
            if (res2.length() > res1.length()) {
                return res2;
            }
            return res2;
        }



        public String findFacteur(String value1, String value2) {
            String facteur = "";
            for (int i = 0; i < value1.length(); i++) {
                for (int j = value1.length(); j > i; j--) {
                    final String sub = value1.substring(i, j);
                    //IsoContestBase.localEcho("Sub: " + sub);
                    if (value2.contains(sub)) {
                        if (sub.length() > facteur.length()) {
                            facteur = sub;
                        } else if (sub.length() == facteur.length()) {
                            if (facteur.compareTo(sub) > 0)
                                facteur = sub;
                        }
                    }
                }
            }
            return facteur;
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


    // END COPY
}


