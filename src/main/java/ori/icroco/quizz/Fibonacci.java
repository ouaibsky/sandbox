package ori.icroco.quizz;

/**
 * Created by christophe on 02/02/15.
 */

public class Fibonacci {



    public static int fibo(int aLimit) {
        if (aLimit <= 2)
            return aLimit;

        return fibo(aLimit -1) + fibo(aLimit -2);

    }



}
