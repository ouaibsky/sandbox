package org.icroco.battledevregions2015.ex6_plus_grand_cote;

import org.icroco.util.StringUtil7;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by christophe on 07/03/15.
 */
class CheckOutput {

    public static void executeAndCompareFromFile(final String aFileInput, final String aExpectedOutput) throws URISyntaxException, IOException {
        URL inputU = CheckOutput.class.getResource(aFileInput);
        URL outputU = CheckOutput.class.getResource(aExpectedOutput);

        if (inputU == null) {
            System.err.println("Input not found: "+aFileInput);
            return;
        }
        if (outputU == null) {
            System.err.println("Output not found: "+aExpectedOutput);
            return;
        }

        System.err.println("--> Start Input: "+aFileInput+", output: "+aExpectedOutput);
        LinkedList<String> input = StringUtil7.readFile(inputU.toURI());
        List<String> expectedOutput = StringUtil7.readFile(outputU.toURI());

        executeAndCompare(aFileInput, input, expectedOutput);
        System.err.println("--< End: OK: "+aFileInput+" --");
        System.err.println("");
    }

    public static void executeAndCompare(final String aFileInput, final LinkedList<String> aInput, final List<String> aExpectedOutput) throws URISyntaxException, IOException {
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
            System.err.println(s);
        System.err.flush();

        System.err.println("");
        System.err.println("** End *");

        if (!Arrays.equals(aExpectedOutput.toArray(new String[0]), output.toArray(new String[0])))
            throw new IllegalStateException(aFileInput+": output is not as expected");
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        //executeAndCompare(new LinkedList(Arrays.asList("1 2 9 A B 11 3 4 5")), Arrays.asList("9 A B"));
        for (int i = 1; i < 10; i++) {
            executeAndCompareFromFile("input"+i+".txt", "output"+i+".txt");
        }
    }


}
