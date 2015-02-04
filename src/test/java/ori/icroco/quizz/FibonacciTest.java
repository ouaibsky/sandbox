package ori.icroco.quizz;

import org.junit.Test;

import static org.junit.Assert.*;
import static ori.icroco.quizz.Fibonacci.fibo;

public class FibonacciTest {

    @Test
    public void testFibo() throws Exception {
//        for (int i = 1; i < 10; i++) {
 //           System.out.println(Fibonacci.fibo(i));
//        }
        long term = 0;
        long sum = 0;
        int i = 1;
        while(true) {
            term = fibo(i);
            if (term >= 4000000)
                break;
            System.out.println(term);
            if (term % 2 == 0)
                sum += term;
            i++;
        }
        System.out.println("sum: "+sum);



    }
}