package org.icroco.mditec2015.ex5;

import java.util.*;


/**
 * imp
 * Created by christophe on 07/03/15.
 */
public class IsoContest {
    public static void main(String[] argv) throws Exception {
        String line;
        Scanner sc = new Scanner(System.in);

        LinkedList<String> input = new LinkedList<>();

        while(sc.hasNextLine())
            input.add(sc.nextLine());

        List<String> output = getSolution(input);

        for (String s: output)
            System.out.println(s);
    }

    static class Dispenser implements Comparable<Dispenser>
    {
        String id;
        int amount;

        public Dispenser(String id, int amount) {
            this.id = id;
            this.amount = amount;
        }


        @Override
        public int compareTo(Dispenser o) {
            if (o.amount == amount) {
                return id.compareTo(o.id);
            }
            if (o.amount > amount)
                return 1;
            else return -1;
        }
    }

    static List<String> getSolution(LinkedList<String> aInput) {
        List<String> output = new ArrayList<>();

        int size = Integer.parseInt(aInput.removeFirst().trim()); // TRIM

        List<Dispenser> dispensers = new ArrayList<Dispenser>();
        while(aInput.size() != 0) {
            String[] values = aInput.removeFirst().trim().split(" ");
            String id = values[0];
            int amount = Integer.valueOf(values[1]);
            Dispenser disp = null;
            for (Dispenser d : dispensers) {
                if (d.id.equals(id)) {
                    disp = d;
                    break;
                }
            }
            if (disp == null) {
                dispensers.add(new Dispenser(id, amount));
            }
            else {
                disp.amount+=amount;
            }

        }

        Collections.sort(dispensers);

        String res = "";
        int i = 0;
        for (Dispenser d: dispensers) {
            res+=d.id+" ";
            i++;
            if (i == 10)
                break;
        }

        output.add(res.trim());
        return output;
    }


    public  static Integer[] toIntArray(String line) {
        String[] SA = line.split("\\s+");
        Integer[]IA = new Integer[SA.length];
        for (int i = 0 ; i < SA.length ; i++)
            IA[i] = Integer.parseInt(SA[i]);

        return IA;
    }
}


class IsoContestBase {
    public static void localEcho( String txt ) {
        System.err.println( txt );
    }
}

