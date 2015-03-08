package org.icroco.tosa.ex6_vitesse;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * imp
 * Created by christophe on 07/03/15.
 */
public class IsoContest {


    public static void main(String[] argv) throws Exception {
        String line;
        LinkedList<String> input = new LinkedList<>();
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine())     // TODO check if applicable
            input.add(sc.nextLine());

        List<String> output = getSolution(input);

        for (String s: output)
            System.out.println(s);
    }

    static List<String> getSolution(LinkedList<String> aInput) throws ParseException {
        LinkedList<String> output = new LinkedList<>();


        String first = aInput.removeFirst();
        String[] data = first.split("\\s+");
        double distance = 0;
        int X1 = Integer.parseInt(data[0])/1000, Y1 = Integer.parseInt(data[1])/1000;
        long Df = parseDate("HH:mm:ss", data[2]).getTime();
        long De = Df;

        for (String line: aInput) {
            data = line.split("\\s+");
            int X2 = Integer.parseInt(data[0])/1000;
            int Y2 = Integer.parseInt(data[1])/1000;
            long D2 = parseDate("HH:mm:ss", data[2]).getTime();
            double dK = Math.sqrt((X2-X1)*(X2-X1)+(Y2-Y1)*(Y2-Y1)); //Math.sqrt(Math.pow(X2-X1, 2)+Math.pow(Y2-Y1, 2));
            IsoContestBase.localEcho("V: "+dK + " Time: "+TimeUnit.MINUTES.convert(D2, TimeUnit.SECONDS));
            distance+=dK;
            X1 = X2;
            Y1 = Y2;
            De = D2;
        }

        //IsoContestBase.localEcho("V: "+distance + " Time: "+TimeUnit.MINUTES.convert(D2 - D1, TimeUnit.MILLISECONDS));

        //double totalTime = ((De-Df)*1.0/(1000*60*60));
        double totalTime = TimeUnit.MILLISECONDS.toHours((Long)(De-Df))*1.0;
        double moyenne = distance / totalTime;

        output.add(String.format(Locale.US, "%.02f", moyenne).replaceAll("00*$", "0"));
        return output;
    }


    final static LinkedList<String> splitSpace(final String aLine) {
        return new LinkedList<>(Arrays.asList(aLine.split("\\s+")));
    }

    final static LinkedList<Integer> convertIntList(List<String> from) {
        final LinkedList<Integer> result = new LinkedList<>();
        for (String s: from) {
            result.add(Integer.parseInt(s, 16));
        }
        return result;
    }


    final static Date parseDate(String format, String data) throws ParseException {
        // "dd-MMM-yy hh:mm:ss a"
        // "HH-mm-ss
        final SimpleDateFormat df = new SimpleDateFormat(format);
        return df.parse(data);
    }

}


class IsoContestBase {
    public static void localEcho( String txt ) {
        System.err.println( txt );
    }
}

