package org.icroco.tosa.template;

import org.icroco.util.StringUtil7;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by christophe on 07/03/15.
 */
class CheckOutput {

    public static void executeAndCompareFromFile(final String aInput, final String aExpectedOutput) throws URISyntaxException, IOException {
        List<String> input = StringUtil7.readFile(org.icroco.mdf2014.ssequences.IsoContest.class.getResource(aInput).toURI());
        List<String> expectedOutput = StringUtil7.readFile(org.icroco.mdf2014.ssequences.IsoContest.class.getResource(aExpectedOutput).toURI());

        executeAndCompare(input, expectedOutput);
    }

    public static void executeAndCompare(final List<String> aInput, final List<String> aExpectedOutput) throws URISyntaxException, IOException {
        List<String> output = IsoContest.getSolution(aInput);

        System.err.println("** Input *");
        for (String s: aInput)
            IsoContestBase.localEcho(s);
        System.err.println("");
        System.err.println("** Expected *");
        for (String s: aExpectedOutput)
            IsoContestBase.localEcho(s);
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
        executeAndCompare(Arrays.asList("1 2 9 A B 11 3 4 5"), Arrays.asList("9 A B"));
    }


}
