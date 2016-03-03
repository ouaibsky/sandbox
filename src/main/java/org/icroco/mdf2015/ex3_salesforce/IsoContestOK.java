package org.icroco.mdf2015.ex3_salesforce;

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

        String[] SA = aInput.removeFirst().split(";");
        Set<String> country = new HashSet<>();
        Set<String> idDoublon = new HashSet<>();
        int nbDoublon = 0;
        int nbBadPhone = 0;
        int nbHorsZone = 0;

        country.addAll(Arrays.asList(SA));



        while(aInput.size() != 0) {
            String[] item = aInput.removeFirst().split(";");   // TODO
            String zone = item[4];
            String id = item[0]+";"+item[1]+";"+item[2];
            String phone = item[3];
            if (idDoublon.contains(id)) {
                nbDoublon++;
                continue;
            }
            else
                idDoublon.add(id);

            if (!country.contains(zone)) {
                nbHorsZone++;
            }

            if (!phone.startsWith("+"))
                nbBadPhone++;
            else {

                String[] phoneItems = phone.substring(1).split("-");
                if (phoneItems.length > 2)
                    nbBadPhone++;
                else {
                    if (phoneItems[0].length() ==0 || phoneItems[0].length() > 3)
                        nbBadPhone++;
                    else if (phoneItems[1].length() < 9 || phoneItems[1].length() > 11)
                    nbBadPhone++;
                }
            }
        }

        output.add(nbDoublon+" "+nbBadPhone+" "+nbHorsZone);
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



