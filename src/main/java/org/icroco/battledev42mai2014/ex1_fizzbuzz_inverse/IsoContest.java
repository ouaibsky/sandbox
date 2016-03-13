package org.icroco.battledev42mai2014.ex1_fizzbuzz_inverse;
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
        String[]            firstLine = aInput.removeFirst().split("\\s+");  // TODO: remove or uncomment
        IsoContestBase.localEcho("Read firstLine: " + Arrays.toString(firstLine));


        List<Integer> dico = new ArrayList<>();
        int fizz = 0;
        int cpt = 1;
        for (String s: firstLine) {
            if (s.equals("Buzz")) {
                dico.add(cpt);

            }
            cpt++;
        }

        int first = dico.get(0);
        int second = 0;
        for (int i = 1; i < dico.size(); i++) {
            if (dico.get(i) % first != 0)
            {
                second = dico.get(i);
                break;
            }

        }

        IsoContestBase.localEcho("");

        IsoContestBase.localEcho("result: " + first+" "+second);
        output.add("" + first+" "+second);
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


