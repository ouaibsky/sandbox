/*******
 * Read input from System.in
 * Use System.out.println to ouput your result.
 * Use:
 *  IsoContestBase.localEcho( variable)
 * to display variable in a dedicated area.
 * ***/
package org.icroco.mdf2014.ssequences;

import java.util.*;
//import java.util.stream.Collectors;


public class IsoContest {
    public static void main(String[] argv) throws Exception {
        String line;
        Scanner sc = new Scanner(System.in);

        line = sc.nextLine();
        List<String> input = new ArrayList<>(1);
        input.add(line);
        List<String> output = getSolution2(input);

        for (String s: output)
            System.out.println(s);
    }

    public static List<String> getSolution(List<String> aInput) {
        List<String> list = Arrays.asList(aInput.get(0).split("(<|>)"));
        List<String> result = new ArrayList<>();

        Deque<String> queue = new LinkedList<>();
        Deque<String> queueOK = new LinkedList<>();
        final StringBuilder builder = new StringBuilder(1024);

        int pos = 0;
        for (String str : list) {
            //System.err.println("put: "+str);
            final boolean start = !str.startsWith("/");
            if (start) {
                queue.offer(str);
                queueOK.offer(str);
                pos += str.length() + 2;
            } else {
                if (queue.isEmpty()) {
                    return getError(pos, str, "");
                } else {
                    final String last = queue.removeLast();
                    //System.err.println("Remove: "+last);
                    if (last.equals(str.substring(1))) {
                        System.err.println("Ok, we poll: " + str);
                        pos += str.length() + 2;
                    } else {
                        //System.err.println("Error: " + str);
                        return getError(pos, str, last);
                    }
                }
            }
        }

        String current = "";
        while (!queueOK.isEmpty()) {
            String last = queueOK.pollLast();
            current = "(" + last + current + ")";
        }
        result.add(current);

        return result;
    }

    public static List<String> getSolution2(List<String> aInput) {
        List<String> list = Arrays.asList(aInput.get(0).split("(<|>)"));
        //List<String> list = Arrays.stream(aInput.getValue(0).split("(<|>)")).filter(s -> !s.trim().isEmpty()).collect(Collectors.toList());
        String str = "";
        int pos = 0;
        try {
            while (!list.isEmpty())
                str += findOpenBrace(list, pos, new LinkedList<String>());
        } catch (IllegalArgumentException aExcpt) {
            str = aExcpt.getMessage();
        }

        return Arrays.asList(str);
    }

    private static String findOpenBrace(List<String> aValue, int pos, Deque<String> aQueue) {
        if (aValue.size() == 0)
            return "";

        String open = aValue.get(0);
        aValue.remove(0);
        pos += open.length() + 2;
        if (open.startsWith("/")) {
            if (open.substring(1).equals(aQueue.getLast())) {
                //findOpenBrace(aValue, pos, open);
                aQueue.removeLast();
                return ")" + findOpenBrace(aValue, pos, aQueue);
            } else {
                throw new IllegalArgumentException("E " + (pos - (open.length() + 2)) + " " + open.substring(1) + " " + aQueue.getLast());
            }
        } else {
            aQueue.offer(open);
            return "(" + open + findOpenBrace(aValue, pos, aQueue);
        }
    }


//    private static String findCloseBrace(List<String> aValue, String open, int pos) {
//        if (aValue.size() == 0)
//            throw new IllegalArgumentException("E " + pos);
//
//        String close = aValue.getValue(0);
//        aValue.remove(0);
//        if (close.startsWith("/")) {
//            if (close.substring(1).equals(open)) {
//                pos += close.length() + 2;
//                return "";
//            }
//            else
//                throw new IllegalArgumentException("E "+pos+close.substring(1)+" "+open);
//        } else
//            return "(" + close + findOpenBrace(aValue, pos + close.length() + 2) + ")";
//    }

    private static List<String> getError(int aPos, String baliseError, String baliseExpected) {
        StringBuilder builder = new StringBuilder();
        builder.append("E ");
        builder.append(aPos).append(" ");
        builder.append(baliseError.substring(1) + " ");
        builder.append(baliseExpected);
        return Arrays.asList(builder.toString());
    }
}
/* 
 * DO NOT PASTE THIS UTILITY CODE BACK INTO THE BROWSER WINDOW
 */
