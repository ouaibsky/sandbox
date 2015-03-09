package org.icroco.tosa.ex8_dist_max;


import java.util.*;


/**
 * imp
 * Created by christophe on 07/03/15.
 */
public class IsoContest {
    public static void main(String[] argv) throws Exception {
        String line;
        LinkedList<String> input = new LinkedList<>();
        Scanner sc = new Scanner(System.in);


        while (sc.hasNextLine())
            input.add(sc.nextLine());

        List<String> output = getSolution(input);

        for (String s : output)
            System.out.println(s);
    }

    static class Node {
        String name;
        Node parent= null;
        List<Node> children = new ArrayList<>();

        public Node(String c) {
            this.name = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (!name.equals(node.name)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }

    static List<String> getSolution(LinkedList<String> aInput) {
        LinkedList<String> output = new LinkedList<>();
        HashMap<String, Node> cache = new HashMap<>();

        Node root = null;
        Node last = root;
        for (String line : aInput) {
            LinkedList<String>  computers = splitSpace(line);

            String firstInLine = computers.removeFirst();
            Node first =  cache.get(firstInLine);
            if (first == null) {
                first = new Node(firstInLine);
                cache.put(firstInLine, first);
            }
            for (String C: computers) {
                Node n = new Node(C);
                n.parent = first;
                first.children.add(n);
                if (cache.containsKey(C))
                    IsoContestBase.localEcho("Error: already added"+C);
                else
                    cache.put(C, n);
            }
            if (root == null)
                root = first;
            else {
                first.parent = last;
            }
            last = first;
        }

        int distance = findD(root);

        output.add(""+distance);

        return output;
    }

    private static int findD(Node node) {
        Integer nb = 0;
        if (node.children.size() == 0)
            return nb;

        nb+=1;
        int max = 0;
        for (Node n: node.children) {
            int res = findD(n);
            if (res > max)
                max = res;
        }
        nb+=max;
        return nb;
    }


    final static LinkedList<String> splitSpace(final String aLine) {
        return new LinkedList<>(Arrays.asList(aLine.split("\\s+")));
    }

    final Date parseDate(String format, String data) throws java.text.ParseException {
        final java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(format);
        return df.parse(data);
    }


    final static LinkedList<Integer> convertIntList(List<String> from) {
        final LinkedList<Integer> result = new LinkedList<>();
        for (String s: from) {
            result.add(Integer.parseInt(s, 16));
        }
        return result;
    }

}


class IsoContestBase {
    public static void localEcho( String txt ) {
        System.err.println( txt );
    }
}

