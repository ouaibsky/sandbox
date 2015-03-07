package org.icroco.tosa.ex2;

import java.util.*;


/**
 * imp
 * Created by christophe on 07/03/15.
 */
public class IsoContest {
    public static void main(String[] argv) throws Exception {
        String line;
        Scanner sc = new Scanner(System.in);
        line = sc.nextLine();

        int S = Integer.parseInt(line);
        IsoContestBase.localEcho("S: "+S);
        List<String> input = new ArrayList<>(10);
        input.add(""+S);
        for (int i = 0; i < S; i++) {
            input.add(sc.nextLine());
        }
        List<String> output = getSolution(input);

        for (String s: output)
            System.out.println(s);
    }

    static List<String> getSolution(List<String> aInput) {
        int S = Integer.parseInt(aInput.remove(0));
        Integer[][] M = new Integer[S][S];

        for (int i = 0; i < S; i++) {
            M[i] = convertIntList(splitSpace(aInput.get(i))).toArray(new Integer[0]);
        }

        int max = -1;
        int mX = 0;
        int mY = 0;
        int centre = M.length/2;
        int E = M[centre][centre];
        IsoContestBase.localEcho("Center Value is: "+E);

        for (int row = 0; row < M.length ; row++) {
            for (int col = 0; col < M[row].length; col++) {
                if (M[row][col] > max) {
                    max = M[row][col];
                    mX = col;
                    mY = row;
                    IsoContestBase.localEcho("Max: "+max+" ["+mX+","+mY+"]");
                } else
                if (M[row][col] == max) {
                    // calcule equidistance
                    int mD = Math.abs(mY-centre)+Math.abs(centre - mX);
                    int cD = Math.abs(col-centre)+Math.abs(centre - row);
                    if (cD < mD) {
                        max = M[row][col];
                        mX = col;
                        mY = row;
                        IsoContestBase.localEcho("Max: "+max+" ["+mX+","+mY+"]");
                    }
                }

            }
        }

//        System.err.println("Solution is: "+mX+" "+ mY);

        return Arrays.asList((mX-centre)+" "+(centre - mY));
    }


    final static List<String> splitSpace(final String aLine) {
        return Arrays.asList(aLine.split("\\s+"));
    }

    final static LinkedList<Integer> convertIntList(List<String> from) {
        final LinkedList<Integer> result = new LinkedList<>();
        for (String s: from) {
            result.add(Integer.parseInt(s));
        }
        return result;
    }

}


class IsoContestBase {
    public static void localEcho( String txt ) {
        System.err.println( txt );
    }
}

