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

            List<Hand> hands = new ArrayList<>();
            final boolean considerJoker = true;

            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(" ");
                Hand hand = new Hand(parts[0], Integer.parseInt(parts[1]), considerJoker);
                hands.add(hand);
            }
            Collections.sort(hands);

            int total = 0;
            int rank = 1;
            for (Hand h : hands) {
                total += h.bid * rank;
                rank++;
            }

            System.out.println(total);
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}


