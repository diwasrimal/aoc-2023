import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Two {

    static String[] digitNames = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Provide filename as argument!");
            return;
        }

        try {
            File f = new File(args[0]);
            int sum = 0;
            Scanner reader = new Scanner(f);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                int firstDigit = -1;
                int lastDigit = -1;

                for (int i = 0; i < line.length(); i++) {
                    // Check if character is digit
                    // If not check for digit name
                    char ch = line.charAt(i);
                    if (Character.isDigit(ch)) {
                        if (firstDigit == -1)
                            firstDigit = ch - '0';
                        lastDigit = ch - '0';
                    } else {
                        int digit = findStartingDigit(line.substring(i, line.length()));
                        if (digit != -1) {
                            if (firstDigit == -1)
                                firstDigit = digit;
                            lastDigit = digit;
                        }
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

    static int findStartingDigit(String str) {
        for (int i = 0; i < digitNames.length; i++) {
            String name = digitNames[i];
            int n = (str.length() < name.length()) ? str.length() : name.length();
            if (str.substring(0, n).equals(name)) {
                return i;
            }
        }
        return -1;
    }
}