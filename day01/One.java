package day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class One {
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

                int firstDigit = 0;
                for (int i = 0; i < line.length(); i++) {
                    char ch = line.charAt(i);
                    if (Character.isDigit(ch)) {
                        firstDigit = ch - '0';
                        break;
                    }
                }
                int lastDigit = 0;
                for (int i = line.length() - 1; i >= 0; i--) {
                    char ch = line.charAt(i);
                    if (Character.isDigit(ch)) {
                        lastDigit = ch - '0';
                        break;
                    }
                }
                sum += firstDigit * 10 + lastDigit;
            }
            System.out.println(sum);
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
