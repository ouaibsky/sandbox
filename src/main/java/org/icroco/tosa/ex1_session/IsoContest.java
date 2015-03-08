package org.icroco.tosa.ex1_session;

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
        List<String> input = new ArrayList<>(1);
        input.add(line);
        List<?> output = getSolution(input);

        for (Object s: output)
            System.out.println(s);
    }

    static List<String> getSolution(List<String> aInput) {
        LinkedList<Integer> iInput = convertIntList(splitSpace(aInput.get(0)));
        List<Integer> lastSeq = new ArrayList<>();
        LinkedList<Integer> curSeq = new LinkedList<>();

        for (Integer i: iInput) {
            //IsoContestBase.localEcho("parse: " + i + " (" + Integer.toHexString(i) + ")");
            if (curSeq.isEmpty()) {
                curSeq.add(i);
            }
            else{
                if (i == (curSeq.peekLast()+1)) {
                    curSeq.add(i);
                } else {
                    if (curSeq.size() > lastSeq.size()) {
                        lastSeq.clear();
                        lastSeq.addAll(curSeq);
                    }
                    curSeq.clear();
                    curSeq.add(i);
                    //IsoContestBase.localEcho("Start new seq");
                }
            }
        }
        if (curSeq.size() > lastSeq.size()) {
            lastSeq.clear();
            lastSeq.addAll(curSeq);
        }

        final StringBuilder b = new StringBuilder();
        for (Integer i: lastSeq)
            b.append(Integer.toHexString(i).toUpperCase()).append(" ");

        return Arrays.asList(b.toString().trim());
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

