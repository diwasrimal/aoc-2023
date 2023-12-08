import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.*;

public class One {

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

            Hashtable<String, Pair> graph = new Hashtable<>();

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                Matcher nodeMatcher = nodePattern.matcher(line);
                List<String> nodes = new ArrayList<>();

                while (nodeMatcher.find()) {
                    nodes.add(nodeMatcher.group());
                }
                if (nodes.size() != 0) {
                    String source = nodes.get(0);
                    graph.put(source, new Pair(nodes.get(1), nodes.get(2)));
                }
            }

            String src = "AAA";
            String dst = "ZZZ";
            int steps = 0;
            int i = 0;
            while (!src.equals(dst)) {
                Pair nodes = graph.get(src);
                src = (instructions.charAt(i) == 'L') ? nodes.left : nodes.right;
                steps++;
                i = (i + 1) % instructions.length();
            }
            System.out.println(steps);
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}