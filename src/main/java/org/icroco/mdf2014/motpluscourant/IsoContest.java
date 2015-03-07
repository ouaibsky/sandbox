/*******
 * Read input from System.in
 * Use System.out.println to ouput your result.
 * Use:
 *  IsoContestBase.localEcho( variable)
 * to display variable in a dedicated area.
 * ***/
package org.icroco.mdf2014.motpluscourant;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class IsoContest {
    public static void main(String[] argv) throws Exception {
        String line;
        Scanner sc = new Scanner(System.in);
        List<String> texts = new ArrayList<>();

        Map<Integer, Map<String, Integer>> values = new HashMap<>();

        while (sc.hasNextLine()) {
            line = sc.nextLine().toLowerCase();
            final String test = line.chars().map(c -> (Character.isLetter(c) || Character.isSpaceChar(c)) ? c : ' ').collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
            texts.add(test);
            System.err.println("add new line: " + test);
            /* Lisez les données et effectuez votre traitement */
        }

        int i = 0;
        for (String item : texts) {
            final String[] words = item.split("\\s+");
            System.err.println("words: " + Arrays.toString(words));
            Map<String, Integer> wordCount = Arrays.asList(words).stream().filter(w -> w.length() > 1).collect(Collectors.groupingBy(e -> e, Collectors.summingInt(e -> 1)));
            values.put(i, wordCount);
            wordCount.forEach((key, value) -> {
                System.err.println("Key: " + key + "\t" + " Value: " + value);
            });
            i++;
        }

        //remove word presents in all lines
        Map<String, Integer> first = values.get(0);
        List<String> toBeTested = new ArrayList<>();
        toBeTested.addAll(first.keySet());
        toBeTested.stream().filter(word -> isPresent(word, values)).forEach(word -> remove(word, values));

        // consolidate
        Map<String, Integer> all = new HashMap<>();
        for (Map<String, Integer> lines : values.values()) {
            for (Map.Entry<String, Integer> entry: lines.entrySet()) {
                if (all.containsKey(entry.getKey()))
                    all.put(entry.getKey(), all.get(entry.getKey()) + entry.getValue());
                else
                    all.put(entry.getKey(), entry.getValue());
            }
        }


        Comparator<Map.Entry<String,Integer>> comp = new Comparator<Map.Entry<String,Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                final int diff =  o2.getValue() - o1.getValue();
                if (diff == 0)
                    return o1.getKey().compareTo(o2.getKey());
                return diff;
            }
        };

        all.entrySet().stream().sorted(comp).forEach((entry) -> {
            System.err.println("All: " + entry.getValue() + " " + entry.getKey());
        });

        all.entrySet().stream().sorted(comp).limit(3).forEach((entry) -> {
            System.out.println(entry.getValue() + " " + entry.getKey());
        });

	/* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }

    private static void remove(String word, Map<Integer, Map<String, Integer>> values) {
        System.err.println("Remove: " + word);
        for (Map<String, Integer> line : values.values()) {
            line.remove(word);
        }
    }

    public static final boolean isPresent(String word, Map<Integer, Map<String, Integer>> values) {
        for (Map<String, Integer> line : values.values()) {
            if (!line.keySet().contains(word))
                return false;
        }

        System.err.println("Present everywhere: " + word);
        return true;
    }

}
/* 
 * DO NOT PASTE THIS UTILITY CODE BACK INTO THE BROWSER WINDOW
 */
