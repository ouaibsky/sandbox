package org.icroco.tosa.ex9_social;


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

        while (sc.hasNextLine())     // TODO check if applicable
            input.add(sc.nextLine());

        List<String> output = getSolution(input);

        for (String s : output)
            System.out.println(s);
    }

    static List<String> getSolution(LinkedList<String> aInput) {
        LinkedList<String> output = new LinkedList<>();

        String first = aInput.removeFirst(); // TODO if integer ...

        for (String word : aInput) {  // TODO

        }

        return output;
    }


    final static LinkedList<String> splitSpace(final String aLine) {
        return new LinkedList<>(Arrays.asList(aLine.split("\\s+")));
    }

    final Date parseDate(String format, String data) throws java.text.ParseException {
        final java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(format);
        return df.parse(data);
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

