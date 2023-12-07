import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Two {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Provide filename as argument!");
            return;
        }
        try {
            File f = new File(args[0]);
            Scanner reader = new Scanner(f);

            String timeStr = "";
            String distanceStr = "";

            Scanner timeScanner = new Scanner(reader.nextLine().split(":")[1]);
            Scanner distanceScanner = new Scanner(reader.nextLine().split(":")[1]);
            while (timeScanner.hasNext()) {
                timeStr += timeScanner.next();
            }
            while (distanceScanner.hasNext()) {
                distanceStr += distanceScanner.next();
            }

            long allowedTime = Long.parseLong(timeStr);
            long maxDistance = Long.parseLong(distanceStr);

            long winningWays = 0;
            for (long holdTime = 0; holdTime <= allowedTime; holdTime++) {
                long remaining = allowedTime - holdTime;
                long travelled = holdTime * remaining;
                if (travelled > maxDistance) {
                    winningWays = remaining - holdTime + 1;
                    break;
                }
            }

            System.out.println(winningWays);
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
