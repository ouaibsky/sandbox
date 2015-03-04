package org.icroco.quizz;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//@RunWith(BlockJUnit4ClassRunner.class)
public class TheLastQuestionTest {



    @Test
    public void myTest() {
        String letters = "thereisasyetinsufficientdataforameaningfulanswer";
        System.out.println("Len: "+letters.length());
        System.out.println(letters.chars().sorted().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString());
    }
}