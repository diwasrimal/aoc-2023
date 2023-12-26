package day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Two {
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

            // Record x and y positions of followed pipes on maze
            List<Integer> xs = new ArrayList<>();
            List<Integer> ys = new ArrayList<>();
            xs.add(maze.startPipe.x);
            ys.add(maze.startPipe.y);
            Pipe prev = maze.startPipe;
            Pipe curr = maze.findMovablePipes(prev).get(0);

            while (curr.symbol != Pipe.startSymbol) {
                xs.add(curr.x);
                ys.add(curr.y);
                for (Pipe next : maze.findMovablePipes(curr)) {
                    if (next.y == prev.y && next.x == prev.x) {
                        continue;
                    } else {
                        prev = curr;
                        curr = next;
                        break;
                    }
                }
            }

            // Shoe lace formula to find area
            // https://en.wikipedia.org/wiki/Shoelace_formula
            int sum = 0;
            int n = xs.size();
            for (int i = 0; i < n; i++) {
                sum += xs.get(i) * ys.get((i+1)%n) - ys.get(i) * xs.get((i+1)%n);
            }
            int area = sum / 2;

            // Picks formula to find interior points
            // https://en.wikipedia.org/wiki/Pick's_theorem
            int boundaryPoints = xs.size();
            int interiorPoints = area + 1 - boundaryPoints / 2;
            System.out.println(interiorPoints);
            
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
