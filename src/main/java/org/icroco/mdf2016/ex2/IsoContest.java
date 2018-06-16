package org.icroco.mdf2016.ex2;
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
            input.add(sc.nextLine().trim());

        List<String> output = getSolution(input);

        for (String s : output)
            System.out.println(s);
    }

    static List<String> getSolution(LinkedList<String> aInput) {
        LinkedList<String> output    = new LinkedList<>();
        int                firstLine = Integer.parseInt(aInput.removeFirst());
        int                nbMouv    = Integer.parseInt(aInput.removeFirst());
        //Integer[]            firstLine = toIntArray(aInput.removeFirst());  // TODO: remove or uncomment

        IsoContestBase.localEcho("Read len: " + firstLine+" nb:"+nbMouv);

        int y = 0;
        int x = firstLine - 1;

        LinkedList<String> lastCommand = new LinkedList<>();
int xSave;
        int ySave;
        int i = 1;
        while (!aInput.isEmpty()) {
            String value = aInput.removeFirst();
            if (value.equals("D")) {
                x++;
            } else if (value.equals("G")) {
                x--;
            } else if (value.equals("B")) {
                y++;
            } else if (value.equals("H")) {
                y--;
            }
            if (lastCommand.size() > firstLine - 1) {
                lastCommand.removeFirst();
            }
            lastCommand.addLast(x+" "+y);

        }


        IsoContestBase.localEcho("");

        IsoContestBase.localEcho("result: " + lastCommand.getFirst());
        output.add("" + lastCommand.getFirst());
        IsoContestBase.localEcho("----");
        IsoContestBase.localEcho("");
        return output;
    }

}
// END COPY


