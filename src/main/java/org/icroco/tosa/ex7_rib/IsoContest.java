package org.icroco.tosa.ex7_rib;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        while (sc.hasNextLine())     // TODO check if applicable
            input.add(sc.nextLine());

        List<String> output = getSolution(input);

        for (String s : output)
            System.out.println(s);
    }

    static List<String> getSolution(LinkedList<String> aInput) {
        LinkedList<String> output = new LinkedList<>();

        Map<Character, Character> dico = initDico();

        for (String line : aInput) {
            String newLine = line.toLowerCase();
            for (Character c : newLine.toCharArray()) {
                if (Character.isLetter(c))
                    newLine = newLine.replace(c, dico.get(c));
            }
            String[] rib = newLine.split("\\s+");
            int codebanque = Integer.parseInt(rib[0]);
            int codequichet = Integer.parseInt(rib[1]);
            long codecompte = Long.parseLong(rib[2]);
            int codeclef = Integer.parseInt(rib[3]);
            long clefcalcule = 97L-((89L*codebanque+15L*codequichet+3*codecompte)%97L);

            if (clefcalcule == codeclef)
                output.add("OK");
            else
                output.add("KO");

        }

        return output;
    }

    private static Map<Character, Character> initDico() {
        Map<Character, Character> dico = new HashMap<>();
        dico.put('a', '1'); dico.put('j' , '1');
        dico.put('b', '2'); dico.put('k' , '2'); dico.put('s' , '2'); // b, k, s => 2
        dico.put('c', '3'); dico.put('l' , '3'); dico.put('t' , '3'); // c, l, t => 3
        dico.put('d', '4'); dico.put('m' , '4'); dico.put('u' , '4'); // d, m, u => 4
        dico.put('e', '5'); dico.put('n' , '5'); dico.put('v' , '5'); // e, n, v => 5
        dico.put('f', '6'); dico.put('o' , '6'); dico.put('w' , '6'); // f, o, w => 6
        dico.put('g', '7'); dico.put('p' , '7'); dico.put('x' , '7'); // g, p, x => 7
        dico.put('h', '8'); dico.put('q' , '8'); dico.put('y' , '8'); // h, q, y => 8
        dico.put('i', '9'); dico.put('r' , '9'); dico.put('z' , '9'); // i, r, z => 9

        return dico;
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

