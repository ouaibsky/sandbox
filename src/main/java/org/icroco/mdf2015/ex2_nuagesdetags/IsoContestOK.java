package org.icroco.mdf2015.ex2_nuagesdetags;

import java.util.*;


/**
 * imp
 * Created by christophe on 07/03/15.
 */
public class IsoContestOK {
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

        int size = Integer.parseInt(aInput.removeFirst().trim()); // TODO
        Map<String, Integer> count = new HashMap<>();
        while(aInput.size() != 0) {
            String word = aInput.removeFirst();   // TODO
            if (count.containsKey(word))
                count.put(word, count.get(word)+1);
            else
                count.put(word, 1);
        }

        Set<Map.Entry<String, Integer>> entries = count.entrySet();
        List<Map.Entry<String, Integer>> list = new ArrayList<>();
        list.addAll(entries);
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });


        for (int i = 0; i < 5; i++) {
            Map.Entry<String, Integer> item = list.get(i);
            output.add(item.getKey()+" "+item.getValue());
        }

        // TODO Fill input
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


