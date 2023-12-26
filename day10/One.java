package day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class One {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Provide filename as argument!");
            return;
        }
        try {
            File f = new File(args[0]);
            Scanner reader = new Scanner(f);

            // Initialize pipe maze with input lines
            List<String> lines = new ArrayList<>();
            while (reader.hasNextLine()) {
                lines.add(reader.nextLine());
            }
            PipeMaze maze = new PipeMaze(lines);

            // Follow one path until starting pipe comes again
            int travelled = 0;
            Pipe prev = maze.startPipe;
            Pipe curr = maze.findMovablePipes(prev).get(0);
            travelled++;
            
            while (curr.symbol != Pipe.startSymbol) {
                System.out.printf("%s [%d]%n", curr, travelled);
                for (Pipe next : maze.findMovablePipes(curr)) {
                    if (next.y == prev.y && next.x == prev.x) {
                        continue;
                    } else {
                        prev = curr;
                        curr = next;
                        break;
                    }
                }
                travelled++;
            }

            // Last move to starting pipe
            travelled++;
            System.out.println(travelled / 2);

            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
