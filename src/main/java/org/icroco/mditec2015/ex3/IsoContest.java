package org.icroco.mditec2015.ex3;

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

    static List<String> getSolution(LinkedList<String> aInput) {
        List<String> output = new ArrayList<>();

        int nbOfPeople = Integer.parseInt(aInput.removeFirst().trim());


        Map<Integer, Integer> hours = new HashMap<Integer, Integer>();
        for (int i = 0 ; i < 24 ; i++){
            hours.put(i, 0);
        }

        while(aInput.size() != 0) {
            String[] startEnd = aInput.removeFirst().trim().split(" ");
            if (startEnd.length != 2) {
                continue;
            }
            int start = Integer.valueOf(startEnd[0]);
            int end = Integer.valueOf(startEnd[1]);
            for (int i = start; i < end; i++) {
                hours.put(i, hours.get(i)+1);
            }

        }

//        TreeMap<Integer, Integer> sortedMap = new TreeMap<Integer, Integer>(new ValueComparator(hours));
//        for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
//            System.out.println("hours: "+entry.getKey()+" value: "+entry.getValue());
//        }

        int hourMax = 0;
        int peopleMax = 0;
        List<Integer> topH = new ArrayList<Integer>();
        for (int i = 0 ; i < 24 ; i++){
            if (hours.get(i) > peopleMax)
            {
                hourMax = i;
                peopleMax = hours.get(i);
                topH.clear();
                topH.add(hourMax);
            }
            else if (hours.get(i) == peopleMax)
            {
                topH.add(i);
            }
        }

        Collections.sort(topH);
        String res = "";
        for (Integer i : topH) {
            res = res + " " +i;
        }
        // TODO Fill input
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

    static class ValueComparator implements Comparator<Integer> {
        Map<Integer, Integer> base;

        public ValueComparator(Map<Integer, Integer> base) {
            this.base = base;
        }

        // Note: this comparator imposes orderings that are inconsistent with
        // equals.
        public int compare(Integer a, Integer b) {
            if (base.get(a) >= base.get(b)) {
                return -1;
            } else {
                return 1;
            } // returning 0 would merge keys
        }
    }
}


class IsoContestBase {
    public static void localEcho( String txt ) {
        System.err.println( txt );
    }
}

