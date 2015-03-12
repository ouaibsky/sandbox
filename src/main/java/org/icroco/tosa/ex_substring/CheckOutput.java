package org.icroco.tosa.ex_substring;


import org.icroco.util.StringUtil7;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by christophe on 07/03/15.
 */
class CheckOutput {

    public static void executeAndCompareFromFile(final String aInput, final String aExpectedOutput) throws Exception {
        LinkedList<String> input = StringUtil7.readFile(CheckOutput.class.getResource(aInput).toURI());
        List<String> expectedOutput = StringUtil7.readFile(CheckOutput.class.getResource(aExpectedOutput).toURI());

        executeAndCompare(input, expectedOutput);
    }

    public static void executeAndCompare(final LinkedList<String> aInput, final List<String> aExpectedOutput) throws Exception{

        System.err.println("** Input *");
        for (String s: aInput)
            org.icroco.tosa.ex_substring.IsoContestBase.localEcho(s);
        System.err.println("");
        System.err.println("** Expected *");
        for (String s: aExpectedOutput)
            IsoContestBase.localEcho(s);
        System.err.println("");
        System.err.println("** Given *");
        System.err.flush();

        long start = System.currentTimeMillis();
        List<String> output = IsoContest.getSolution(aInput);


        for (String s: output)
            System.err.println(s);
        long end = System.currentTimeMillis();
        System.err.flush();

        System.err.println("");
        System.err.println("** End, took: "+(end -start)+"ms");

        if (!Arrays.equals(aExpectedOutput.toArray(new String[0]), output.toArray(new String[0])))
            throw new IllegalStateException("output is not as expected");
    }

    public static void main(String[] args) throws Exception {
//        executeAndCompare(Arrays.asList("1 2 9 A B 11 3 4 5"), Arrays.asList("9 A B"));
        executeAndCompareFromFile("input1.txt", "output1.txt");
        executeAndCompareFromFile("input2.txt", "output2.txt");
        executeAndCompareFromFile("input3.txt", "output3.txt");
    }


}
