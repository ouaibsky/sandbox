package org.icroco.mdf2015.ex3;


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

    public static void executeAndCompareFromFile(final String aInput, final String aExpectedOutput) throws URISyntaxException, IOException {
        LinkedList<String> input = StringUtil7.readFile(CheckOutput.class.getResource(aInput).toURI());
        List<String> expectedOutput = StringUtil7.readFile(CheckOutput.class.getResource(aExpectedOutput).toURI());

        executeAndCompare(input, expectedOutput);
    }

    public static void executeAndCompare(final LinkedList<String> aInput, final List<String> aExpectedOutput) throws URISyntaxException, IOException {
        List<String> output = IsoContest.getSolution(aInput);

        System.err.println("** Input *");
        for (String s: aInput)
            System.err.println(s);
        System.err.println("");
        System.err.println("** Expected *");
        for (String s: aExpectedOutput)
            System.err.println(s);
        System.err.println("");
        System.err.println("** Given *");
        System.err.flush();

        for (String s: output)
            System.out.println(s);
        System.out.flush();

        System.err.println("");
        System.err.println("** End *");

        if (!Arrays.equals(aExpectedOutput.toArray(new String[0]), output.toArray(new String[0])))
            throw new IllegalStateException("output is not as expected");
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        //executeAndCompare(new LinkedList(Arrays.asList("1 2 9 A B 11 3 4 5")), Arrays.asList("9 A B"));
        executeAndCompareFromFile("input1.txt", "output1.txt");
        executeAndCompareFromFile("input2.txt", "output2.txt");
        executeAndCompareFromFile("input3.txt", "output3.txt");
    }


}
