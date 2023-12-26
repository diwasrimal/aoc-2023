package day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
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

            // Store seed line, find seeds at last
            String seedLine = reader.nextLine();

            Pattern mapNamePattern = Pattern.compile("\\w+-to-\\w+ map:");
            Pattern IntervalPattern = Pattern.compile("\\d+ \\d+ \\d+");
            List<Map> maps = new ArrayList<>();

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                Matcher mapNameMatcher = mapNamePattern.matcher(line);
                Matcher IntervalMatcher = IntervalPattern.matcher(line);

                if (mapNameMatcher.matches()) {
                    String[] parts = line.split(" ")[0].split("-to-");
                    maps.add(new Map(parts[0], parts[1]));
                }
                else if (IntervalMatcher.matches()) {
                    String[] parts = line.split(" ");
                    long dstStart = Long.parseLong(parts[0]);
                    long srcStart = Long.parseLong(parts[1]);
                    long range = Long.parseLong(parts[2]);
                    Interval i = new Interval(srcStart, dstStart, range);
                    maps.get(maps.size() - 1).addInterval(i);
                }
            }


            long lowestLocation = Long.MAX_VALUE;
            Scanner seedScanner = new Scanner(seedLine.split("seeds: ")[1]);
            while (seedScanner.hasNextLong()) {
                long seed = seedScanner.nextLong();
                long range = seedScanner.nextLong();
                System.out.printf("initialseed: %d, range: %d%n", seed, range);
                long maxSeed = seed + range - 1;

                for (; seed <= maxSeed; seed++) {
                    long dst = seed;
                    for (Map map : maps) {
                        dst = map.getDstNumber(dst);
                    }
                    if (dst < lowestLocation)
                        lowestLocation = dst;
                }
            }

            System.out.println(lowestLocation);
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}