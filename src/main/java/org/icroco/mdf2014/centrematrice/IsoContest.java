/*******
 * Read input from System.in
 * Use System.out.println to ouput your result.
 * Use:
 *  IsoContestBase.localEcho( variable)
 * to display variable in a dedicated area.
 * ***/
package org.icroco.mdf2014.centrematrice;

import java.math.BigDecimal;
import java.util.*;

public class IsoContest {
    public static void main(String[] argv) throws Exception {
        String line;
        Scanner sc = new Scanner(System.in);
        int p = Integer.parseInt(sc.nextLine().trim());
        int s = p / 2;
        int items = s*s;
        int border = s / 2;
        Map<Integer, Integer> mode = new TreeMap<>();


        int i = 0;
        List<Integer> matrice = new ArrayList<>(s*s);
        IsoContestBase.localEcho("P: " + p);
        IsoContestBase.localEcho("s: " + s);
        IsoContestBase.localEcho("border: " + border);
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            if ((i < border) || (i >= (border+s))) {
                IsoContestBase.localEcho("Skip: " + line);
                i++;
                continue;
            }
            i++;

            Integer[] resFull = splitAndGetInt(line).toArray(new Integer[0]);
            Integer res[] = Arrays.copyOfRange(resFull, border, s+border);
            matrice.addAll(Arrays.asList(res));
            IsoContestBase.localEcho("OK  : " + line + " (" + Arrays.toString(res) + ")");
        }

        int mid1 = matrice.get((items/2)-1);
        int mid2 = matrice.get((items/2));
        double average = (mid1 + mid2)/2.0;

        Collections.sort(matrice);

        int maxV = Integer.MIN_VALUE;
        int minK = 0;
        for(Map.Entry<Integer, Integer> entry: mode.entrySet()) {
            if (entry.getValue() > maxV) {
                maxV = entry.getValue();
                minK = entry.getKey();
            }
            else if (entry.getValue() == maxV) {
                if (entry.getKey() < minK)
                    minK = entry.getKey();
            }
        }

        IsoContestBase.localEcho("Sort: " + matrice);
        IsoContestBase.localEcho("mid1: " + mid1 + " mid2: " + mid2 + " average: " + average);
        BigDecimal mediane = BigDecimal.valueOf(average).setScale(1, BigDecimal.ROUND_DOWN);
        //BigDecimal.valueOf(outputMediane / (s * s)).setScale(1, BigDecimal.ROUND_DOWN);

        System.out.printf(Locale.US, "%1$d %2$d %3$#.1f %4$d %n ", matrice.get(0), matrice.get(matrice.size()-1), mediane, minK);
	/* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }

    public static void mainOld(String[] argv) throws Exception {
        String line;
        Scanner sc = new Scanner(System.in);
        int p = Integer.parseInt(sc.nextLine().trim());
        int s = p / 2;
        int items = s*s;
        int border = s / 2;
        Map<Integer, Integer> mode = new TreeMap<>();


        int i = 0;
        List<Integer> matrice = new ArrayList<>(s*s);
        List<Integer> values = new ArrayList<>(20);
        IsoContestBase.localEcho("P: " + p);
        IsoContestBase.localEcho("s: " + s);
        IsoContestBase.localEcho("border: " + border);
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            if ((i < border) || (i >= (border+s))) {
                IsoContestBase.localEcho("Skip: " + line);
                i++;
                continue;
            }
            i++;

            Integer[] resFull = splitAndGetInt(line).toArray(new Integer[0]);
            Integer res[] = Arrays.copyOfRange(resFull, border, s+border);
            matrice.addAll(Arrays.asList(res));
            IsoContestBase.localEcho("OK  : " + line + " (" + Arrays.toString(res) + ")");

            for (int j = 0; j < res.length; j++) {
                if (mode.containsKey(res[j]))
                    mode.put(res[j], mode.get(res[j])+1);
                else
                    mode.put(res[j], 1);
            }
     /* Lisez les données et effectuez votre traitement */
        }

        Collections.sort(matrice);
        int maxV = Integer.MIN_VALUE;
        int minK = 0;
        for(Map.Entry<Integer, Integer> entry: mode.entrySet()) {
            if (entry.getValue() > maxV) {
                maxV = entry.getValue();
                minK = entry.getKey();
            }
            else if (entry.getValue() == maxV) {
                if (entry.getKey() < minK)
                    minK = entry.getKey();
            }
        }

        IsoContestBase.localEcho("Sort: " + matrice);
        int mid1 = matrice.get((items/2)-1);
        int mid2 = matrice.get((items/2));
        double average = (mid1 + mid2)/2.0;
        IsoContestBase.localEcho("mid1: " + mid1 + " mid2: " + mid2 + " average: " + average);
        BigDecimal mediane = BigDecimal.valueOf(average).setScale(1, BigDecimal.ROUND_DOWN);
        //BigDecimal.valueOf(outputMediane / (s * s)).setScale(1, BigDecimal.ROUND_DOWN);

        System.out.printf(Locale.US, "%1$d %2$d %3$#.1f %4$d %n ", matrice.get(0), matrice.get(matrice.size()-1), mediane, minK);
	/* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }

    public static List<Integer> splitAndGetInt(String aLine) {
        final String[] numbers = aLine.split("\\s+");
        final LinkedList<Integer> result = new LinkedList<>();
        for (String s : numbers) {
            result.add(Integer.parseInt(s));
            //            result.add(Integer.parseInt(s, 16)); TODO if octal
        }
        return result;
    }
}
/* 
 * DO NOT PASTE THIS UTILITY CODE BACK INTO THE BROWSER WINDOW
 */

class IsoContestBase {
    public static void localEcho( String txt ) {
        System.err.println( txt );
    }
}

