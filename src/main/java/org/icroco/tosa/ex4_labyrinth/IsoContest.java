package org.icroco.tosa.ex4_labyrinth;


import java.util.*;


/**
 * imp
 * Created by christophe on 07/03/15.
 */
public class IsoContest {
    public static void main(String[] argv) throws Exception {
        String line;
        LinkedList<String> input = new LinkedList<>();
        Scanner sc = new Scanner(System.in);

        line = sc.nextLine();       // TODO check if applicable
        input.add(line);            // TODO check if applicable

        while (sc.hasNextLine())     // TODO check if applicable
            input.add(sc.nextLine());

        List<String> output = getSolution(input);

        for (String s : output)
            System.out.println(s);
    }

    static List<String> getSolution(LinkedList<String> aInput) {
        LinkedList<String> output = new LinkedList<>();

        String[] first = aInput.removeFirst().split("\\s+"); // TODO if integer ...
        int N = Integer.parseInt(first[0]);
        int M = Integer.parseInt(first[1]);

        char[][] result = extractMatrix(aInput, M);
        //printMatrix(result, 2);
        int nbDelete = 0;

        for (int i = 1; i < N ; i++) {
            for (int j = 1; j < M; j++) {
                if (i < N-1) {
                    if (result[i + 1][j] == '.') {
                        i = i + 1;
                        j = 1;
                    } else // char
                    {

                    }

                }
            }

        }



        return output;
    }


    public static char[][] extractMatrix(LinkedList<String> aInput, int xSize) {
        char[][] result = new char[aInput.size()][xSize];

        for (int i = 0; i < aInput.size(); i++) {
            char[] numbers = aInput.get(i).toCharArray();
            for (int j = 0; j < numbers.length; j++) {
                result[i][j] = numbers[j];
            }
        }
        return result;
    }

    public static void printMatrix(char[][] matrix, int padding) {
        for (int i = 0; i < matrix.length; i++) {
            StringBuilder b = new StringBuilder();
            for (int j = 0; j < matrix[i].length; j++) {
                b.append(String.format("%1$-" + padding + "s", matrix[i][j]));
            }
            IsoContestBase.localEcho(b.toString());
        }
    }

    final static LinkedList<String> splitSpace(final String aLine) {
        return new LinkedList<>(Arrays.asList(aLine.split("\\s+")));
    }

    final Date parseDate(String format, String data) throws java.text.ParseException {
        final java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(format);
        return df.parse(data);
    }


    final static LinkedList<Integer> convertIntList(List<String> from) {
        final LinkedList<Integer> result = new LinkedList<>();
        for (String s: from) {
            result.add(Integer.parseInt(s, 16));
        }
        return result;
    }

}


class IsoContestBase {
    public static void localEcho( String txt ) {
        System.err.println( txt );
    }
}

