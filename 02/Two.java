import java.util.Hashtable;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class Two {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Provide input file!");
            return;
        }

        try {
            File inputFile = new File(args[0]);
            Scanner reader = new Scanner(inputFile);
            Hashtable<String, Integer> maxCount = new Hashtable<>();
            int sum = 0;

            while (reader.hasNextLine()) {
                String game = reader.nextLine();
                maxCount.put("red", 0);
                maxCount.put("green", 0);
                maxCount.put("blue", 0);

                String[] draws = game.split(": ", 0)[1].split("; ");
                for (String draw : draws) {
                    String[] cubes = draw.split(", ", 0);
                    for (String cube : cubes) {
                        int count = Integer.parseInt(cube.split(" ", 0)[0]);
                        String color = cube.split(" ", 0)[1];
                        if (count > maxCount.get(color))
                            maxCount.put(color, count);
                    }
                }

                int power = maxCount.get("red") * maxCount.get("green") * maxCount.get("blue");
                sum += power;
            }

            System.out.println(sum);
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
