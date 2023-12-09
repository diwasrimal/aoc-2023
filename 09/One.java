import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

public class One {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Provide filename as argument!");
            return;
        }
        try {
            File f = new File(args[0]);
            Scanner reader = new Scanner(f);

            int sum = 0;
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                int[] sequence = Arrays.stream(line.split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
                sum += findLast(sequence);
            }

            System.out.println(sum);
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static int findLast(int[] seq) {
        boolean allZeroes = true;
        for (int i = 0; i < seq.length; i++) {
            if (seq[i] != 0) {
                allZeroes = false;
                break;
            }
        }

        if (allZeroes)
            return 0;

        int[] subseq = new int[seq.length - 1];
        for (int i = 1; i < seq.length; i++) {
            subseq[i - 1] = seq[i] - seq[i - 1];
        }
        return seq[seq.length - 1] + findLast(subseq);
    }
}
