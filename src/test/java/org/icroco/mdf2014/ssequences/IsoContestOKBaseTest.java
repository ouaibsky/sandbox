package org.icroco.mdf2014.ssequences;

import org.icroco.util.StringUtil7;
//import org.icroco.util.StringUtil8;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class IsoContestOKBaseTest {



    @Test
    public void intput1() throws Exception {
        executeAndCompare("input1.txt", "output1.txt");
    }
    @Test
    public void intput2() throws Exception {
        executeAndCompare("input2.txt", "output2.txt");
    }
    @Test
    public void intput3() throws Exception {
        executeAndCompare("input3.txt", "output3.txt");
    }
    @Test
    public void intput4() throws Exception {
        executeAndCompare("input4.txt", "output4.txt");
    }


    public static void executeAndCompare(final String aInput, final String aExpectedOutput) throws URISyntaxException, IOException {
            System.err.println("** Input *");
            List<String> input = StringUtil7.readFile(IsoContest.class.getResource(aInput).toURI());
            System.err.println("");
            System.err.println("** Expected *");
            List<String> expectedOutput = StringUtil7.readFile(IsoContest.class.getResource(aExpectedOutput).toURI());
            System.err.println("");
            System.err.println("** Given *");
            List<String> output = IsoContest.getSolution2(input);

        for (String s: output)
            System.out.println(s);

            System.err.println("");
            System.err.println("** End *");

            if (!Arrays.equals(expectedOutput.toArray(new String[0]), output.toArray(new String[0])))
                throw new IllegalStateException("output is not as expected");

    }




}