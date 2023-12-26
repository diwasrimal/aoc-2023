package day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
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
            List<String> data = new ArrayList<>();
            int maxLineLen = 0;

            while (reader.hasNextLine()) {
                String str = reader.nextLine();
                if (str.length() > maxLineLen)
                    maxLineLen = str.length();

                data.add(str);
            }

            for (int line = 0; line < data.size(); line++) {
                for (int j = 0; j < maxLineLen; j++) {
                    System.out.printf("%c ", data.get(line).charAt(j));
                }
                System.out.println();
            }
            System.out.println("________________");

            // Fill cells adjacent to symbols
            int lines = data.size();
            boolean[][] isAdjacent = new boolean[lines][maxLineLen];
            for (int line = 0; line < lines; line++) {
                String str = data.get(line);
                for (int i = 0; i < str.length(); i++) {
                    char c = str.charAt(i);
                    if (!Character.isDigit(c) && c != '.') {
                        for (int l = line - 1; l <= line + 1; l++) {
                            if (l < 0 || l >= lines)
                                continue;
                            for (int j = i - 1; j <= i + 1; j++) {
                                if (j < 0 || j >= maxLineLen)
                                    continue;
                                isAdjacent[l][j] = true;
                            }
                        }
                    }
                }
            }

            for (int line = 0; line < lines; line++) {
                for (int j = 0; j < maxLineLen; j++) {
                    System.out.printf("%d ", isAdjacent[line][j] ? 1 : 0);
                }
                System.out.println();
            }
            System.out.println("________________");

            // Go through data again, parsing numbers,
            // If one of the digits of number is adjacent
            // It is valid
            int sum = 0;
            for (int line = 0; line < lines; line++) {
                String str = data.get(line);
                int num = 0;
                boolean numIsAdj = false;
                for (int i = 0; i < str.length(); i++) {
                    char c = str.charAt(i);
                    if (Character.isDigit(c)) {
                        num = num * 10 + (c - '0');
                        if (isAdjacent[line][i])
                            numIsAdj = true;
                    } else {
                        if (num != 0) {
                            if (numIsAdj) {
                                sum += num;
                                System.out.printf("sum += %d, sum = %d%n", num, sum);
                            }
                            num = 0;
                            numIsAdj = false;
                        }
                    }
                }
                // Number might be at end of line
                if (num != 0 && numIsAdj)
                    sum += num;
            }

            System.out.println("Sum: " + sum);
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}