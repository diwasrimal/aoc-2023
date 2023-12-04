import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Two {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Provide input file!");
            return;
        }

        try {
            File f = new File(args[0]);
            Scanner reader = new Scanner(f);
            List<String> data = new ArrayList<>();
            int maxLineLen = 0;

            while (reader.hasNextLine()) {
                String str = reader.nextLine();
                if (str.length() > maxLineLen)
                    maxLineLen = str.length();

                data.add(str);
            }

            // Collect gear position of each part number
            int lines = data.size();

            

            int gearPartSize = lines * maxLineLen;
            List<List<Integer>> gearParts = new ArrayList<List<Integer>>(gearPartSize);
            for (int i = 0; i < gearPartSize; i++) {
                gearParts.add(i, new ArrayList<Integer>());
            }

            // List<Integer>[] gearParts = new List[gearPartSize];

            for (int line = 0; line < lines; line++) {
                String str = data.get(line);
                int num = 0;
                boolean gearFound = false;
                int gearLine = -1;
                int gearCol = -1;
                for (int i = 0; i < str.length(); i++) {
                    char c = str.charAt(i);

                    // If char is digit, search for a gear in adjacent cells
                    if (Character.isDigit(c)) {
                        num = num * 10 + (c - '0');
                        if (!gearFound) {
                            for (int l = line - 1; l <= line + 1; l++) {
                                for (int j = i - 1; j <= i + 1; j++) {
                                    if (l < 0 || l >= lines || j < 0 || j >= maxLineLen || gearFound)
                                        continue;
                                    if (data.get(l).charAt(j) == '*') {
                                        gearFound = true;
                                        gearLine = l;
                                        gearCol = j;
                                    }
                                }
                            }
                        }

                    // Digits parsed and gear was found
                    } else {
                        if (num != 0 && gearFound) {
                                int idx = gearLine * maxLineLen + gearCol;
                                gearParts.get(idx).add(num);
                        }
                        gearFound = false;
                        gearLine = -1;
                        gearCol = -1;
                        num = 0;
                    }
                }
                if (num != 0 && gearFound) {
                    int idx = gearLine * maxLineLen + gearCol;
                    gearParts.get(idx).add(num);
                }
            }

            long sum = 0;
            for (List<Integer> parts : gearParts) {
                if (parts.size() == 2) {
                    sum += parts.get(0) * parts.get(1);
                }
            }

            System.out.println("Sum: " + sum);
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}