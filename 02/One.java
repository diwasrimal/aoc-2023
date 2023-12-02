import java.util.Hashtable;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class One {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Provide input file!");
            return;
        }

        try {
            File inputFile = new File(args[0]);
            Scanner reader = new Scanner(inputFile);
            int idSum = 0;

            while (reader.hasNextLine()) {
                String game = reader.nextLine();
                int gameId = Integer.parseInt(game.split(": ", 0)[0].split(" ", 0)[1]);

                boolean validGame = true;
                String[] draws = game.split(": ", 0)[1].split("; ");
                for (int i = 0; i < draws.length; i++) {
                    if (!isValidDraw(draws[i])) {
                        validGame = false;
                        break;
                    }
                }

                if (validGame) {
                    idSum += gameId;
                }
            }

            System.out.println(idSum);
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static boolean isValidDraw(String draw) {
        Hashtable<String, Integer> colCount = new Hashtable<>();
        colCount.put("red", 0);
        colCount.put("green", 0);
        colCount.put("blue", 0);

        String[] cubes = draw.split(", ", 0);
        for (int i = 0; i < cubes.length; i++) {
            int count = Integer.parseInt(cubes[i].split(" ", 0)[0]);
            String color = cubes[i].split(" ", 0)[1];
            colCount.put(color, count);
        }

        return colCount.get("red") <= 12 && colCount.get("green") <= 13 && colCount.get("blue") <= 14;
    }
}
