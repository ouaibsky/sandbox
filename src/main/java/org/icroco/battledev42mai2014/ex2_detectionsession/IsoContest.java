package org.icroco.battledev42mai2014.ex2_detectionsession;
// START COPY

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


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
        LinkedList<String> output    = new LinkedList<>();
        String[]           firstLine = aInput.removeFirst().split("\\s+");  // TODO: remove or uncomment

        //IsoContestBase.localEcho("Read firstLine: " + Arrays.toString(firstLine));

        int prev = Integer.valueOf(firstLine[0], 16);
        int    count    = prev;
        int maxLen = 0;
        String maxSeq = "";
        String res    = firstLine[0]+" ";
        for (int i = 1; i < firstLine.length; i++) {
            int v = Integer.valueOf(firstLine[i], 16);
            if (v == prev + 1) {
                res += firstLine[i] + " ";
                count += v;

            } else {
                if (count > maxLen) {
                    maxLen = count;
                    maxSeq = res;
                }
                count = v;
                res = firstLine[i]+" ";
            }
            prev = v;
        }

        IsoContestBase.localEcho("");

        IsoContestBase.localEcho("result: " + maxSeq.trim());
        output.add("" + maxSeq.trim());
        IsoContestBase.localEcho("----");
        IsoContestBase.localEcho("");
        return output;
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


