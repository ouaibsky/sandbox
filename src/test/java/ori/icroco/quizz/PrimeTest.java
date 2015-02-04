package ori.icroco.quizz;

import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PrimeTest {

    public int usingModuloOperator(final int operand1, final int operand2) {
        return operand1 % operand2;
    }

    public int usingBitwiseAnd(final int operand1, final int operand2) {
        return operand1 & operand2;
    }

    public static List<Long> primeFactors(long n) {
        List<Long> factors = new ArrayList<Long>();
        while (n % 2 == 0 && n > 0) {
            factors.add(2L);
            n /= 2;
        }

        for (long i = 3; i * i <= n; i+=2) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        if (n > 1)
            factors.add(n);

        return factors;
    }

    @Test
    public void myTest() {
        System.out.println(primeFactors(600_851_475_143L));
    }
}