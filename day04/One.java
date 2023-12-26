package day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class One {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Provide input file!");
            return;
        }

        try {
            File f = new File(args[0]);
            Scanner reader = new Scanner(f);
            int totalPoints = 0;

            while (reader.hasNextLine()) {
                Card card = Card.fromString(reader.nextLine());
                int matches = card.matches();
                if (matches > 0) {
                    totalPoints += (int)Math.pow(2, matches - 1);
                }
            }

            System.out.println(totalPoints);
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}