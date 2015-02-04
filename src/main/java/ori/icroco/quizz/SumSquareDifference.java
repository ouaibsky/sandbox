package ori.icroco.quizz;

/**
 * Created by christophe on 02/02/15.
 */

public class SumSquareDifference {


    public static long sumOfSquare(int value) {
        long res = 0;
        for (int i = 1 ; i <= value ; i++)
            res += (i*i);
        return res;
    }

    public static long squareOfSum(int value) {
        long res = 0;
        for (int i = 1 ; i <= value ; i++)
            res += i;
        return res*res;
    }


    public static void main(String[] args) {
        System.out.println("Diff: "+(squareOfSum(100)-sumOfSquare(100)));
    }


}
