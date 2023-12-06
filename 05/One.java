import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
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

            // Find seeds
            List<Long> seeds = new ArrayList<>();
            Scanner seedScanner = new Scanner(reader.nextLine().split("seeds: ")[1]);
            while (seedScanner.hasNextLong()) {
                seeds.add(seedScanner.nextLong());
            }

            Pattern mapNamePattern = Pattern.compile("\\w+-to-\\w+ map:");
            Pattern intervalPattern = Pattern.compile("\\d+ \\d+ \\d+");
            List<Map> maps = new ArrayList<>();

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                Matcher mapNameMatcher = mapNamePattern.matcher(line);
                Matcher intervalMatcher = intervalPattern.matcher(line);

                if (mapNameMatcher.matches()) {
                    String[] parts = line.split(" ")[0].split("-to-");
                    maps.add(new Map(parts[0], parts[1]));
                }
                else if (intervalMatcher.matches()) {
                    String[] parts = line.split(" ");
                    long dstStart = Long.parseLong(parts[0]);
                    long srcStart = Long.parseLong(parts[1]);
                    long range = Long.parseLong(parts[2]);
                    Interval i = new Interval(srcStart, dstStart, range);
                    maps.get(maps.size() - 1).addInterval(i);
                }
            }

            long lowestLocation = Long.MAX_VALUE;
            for (long seed : seeds) {
                System.out.println("Seed: " + seed);
                long dst = seed;
                // Walk through maps
                for (Map map : maps) {
                    dst = map.getDstNumber(dst);
                    System.out.printf("(%s->%s), dst: %d, lowest: %d%n", map.src, map.dst, dst, lowestLocation);
                }
                if (dst < lowestLocation)
                    lowestLocation = dst;
                System.out.println("=============");
            }

            System.out.println(lowestLocation);
            reader.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}