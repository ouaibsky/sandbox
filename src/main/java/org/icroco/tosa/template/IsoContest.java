package org.icroco.tosa.template;

import java.util.*;



/**
 * imp
 * Created by christophe on 07/03/15.
 */
public class IsoContest {
    public static void main(String[] argv) throws Exception {
        String line;
        Scanner sc = new Scanner(System.in);
        line = sc.nextLine();
        List<String> input = new ArrayList<>(10);
        input.add(line);
        List<String> output = getSolution(input);

        for (String s: output)
            System.out.println(s);
    }

    static List<String> getSolution(List<String> aInput) {
        return Arrays.asList("??");
    }


    final static List<String> splitSpace(final String aLine) {
        return Arrays.asList(aLine.split("\\s+"));
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

