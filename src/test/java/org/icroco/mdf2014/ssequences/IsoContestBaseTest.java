package org.icroco.mdf2014.ssequences;

import org.icroco.util.Util;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class IsoContestBaseTest {



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
            List<String> input = Util.readFile(IsoContest.class.getResource(aInput).toURI());
            System.err.println("");
            System.err.println("** Expected *");
            List<String> expectedOutput = Util.readFile(IsoContest.class.getResource(aExpectedOutput).toURI());
            System.err.println("");
            System.err.println("** Given *");
            List<String> output = IsoContest.getSolution2(input);

            output.forEach(s -> System.out.println(s));

            System.err.println("");
            System.err.println("** End *");

            if (!Arrays.equals(expectedOutput.toArray(new String[0]), output.toArray(new String[0])))
                throw new IllegalStateException("output is not as expected");

    }




}