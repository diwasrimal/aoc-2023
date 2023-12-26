package day08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.*;

class Two {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Provide input file!");
            return;
        }

        try {
            File f = new File(args[0]);
            Scanner reader = new Scanner(f);

            String instructions = reader.nextLine();
            Pattern nodePattern = Pattern.compile("(\\w{3})");

            // Fill in given information
            Hashtable<String, Pair> graph = new Hashtable<>();
            List<String> startNodes = new ArrayList<>();
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                Matcher nodeMatcher = nodePattern.matcher(line);
                List<String> nodes = new ArrayList<>();

                while (nodeMatcher.find()) {
                    nodes.add(nodeMatcher.group());
                }
                if (nodes.size() != 0) {
                    String source = nodes.get(0);
                    if (source.endsWith("A")) {
                        startNodes.add(source);
                    }
                    graph.put(source, new Pair(nodes.get(1), nodes.get(2)));
                }
            }

            String[] srcs = startNodes.toArray(new String[startNodes.size()]);

            // No of steps required to again reach a node ending with Z
            // src[0] ... ... Z  <steps> Z
            // One starting node only has one ending node
            // which repeats again in a certian amount of steps
            int[] cycleSteps = new int[srcs.length];
            for (int s = 0; s < srcs.length; s++) {
                String src = srcs[s];
                int steps = 0;
                int i = 0;
                while (!src.endsWith("Z")) {
                    Pair nodes = graph.get(src);
                    src = (instructions.charAt(i) == 'L') ? nodes.left : nodes.right;
                    steps++;
                    i = (i + 1) % instructions.length();
                }
                cycleSteps[s] = steps;
            }

            // The number of steps at which all nodes will end in Z
            // is the lcm of each cycling steps
            long lcm = cycleSteps[0];
            for (int i = 1; i < cycleSteps.length; i++) {
                lcm = lowestCommonMultiple(lcm, cycleSteps[i]);
            }

            System.out.println(lcm);
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static long lowestCommonMultiple(long a, long b) {
        return (a * b) / gcd(a, b);
    }

    static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}

