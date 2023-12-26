package day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class One {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Provide filename as argument!");
            return;
        }
        try {
            File f = new File(args[0]);
            Scanner reader = new Scanner(f);

            List<Integer> times = new ArrayList<>();
            List<Integer> distances = new ArrayList<>();

            Scanner timeScanner = new Scanner(reader.nextLine().split(":")[1]);
            Scanner distanceScanner = new Scanner(reader.nextLine().split(":")[1]);
            while (timeScanner.hasNextInt()) {
                times.add(timeScanner.nextInt());
            }
            while (distanceScanner.hasNextInt()) {
                distances.add(distanceScanner.nextInt());
            }

            int score = 1;
            for (int i = 0; i < times.size(); i++) {
                int allowedTime = times.get(i);
                int maxDistance = distances.get(i);

                for (int holdTime = 0; holdTime <= allowedTime; holdTime++) {
                    int remaining = allowedTime - holdTime;
                    int travelled = holdTime * remaining;
                    if (travelled > maxDistance) {
                        int winningWays = remaining - holdTime + 1;
                        score *= winningWays;
                        break;
                    }
                }
            }

            System.out.println(score);
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
