package org.icroco.tosa.ex5_carte;

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

        line = sc.nextLine();       // TODO check if applicable
        input.add(line);            // TODO check if applicable

        while(sc.hasNextLine())     // TODO check if applicable
            input.add(sc.nextLine());

        List<String> output = getSolution(input);

        for (String s: output)
            System.out.println(s);
    }

    static List<String> getSolution(LinkedList<String> aInput) {
        LinkedList<String> output = new LinkedList<>();

        List<String> jeuxComplet =  remplirJeux();


        String line = aInput.removeFirst();

        String[] cartes = line.split("\\s+");
        for (String carte: cartes) {
            if (jeuxComplet.remove(carte));
        }

        String result = "";
        for (String s: jeuxComplet)
            result+=" "+s;
        output.add(result.trim());

        return output;
    }

    private static List<String> remplirJeux() {
        List<String> jeux = new ArrayList<>();
        for (String color: Arrays.asList("C", "P", "Q","T")) {
            for (String carte: Arrays.asList("2","3","4","5","6","7","8","9","10","V","D","R","A")) {
                jeux.add(color+carte);
            }
        }
        return jeux;
    }

}


class IsoContestBase {
    public static void localEcho( String txt ) {
        System.err.println( txt );
    }
}

