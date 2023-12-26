package day16;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Two {
    static char[][] tiles;
    static int rows;
    static int cols;

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 1) {
            System.out.println("Provide input file!");
            return;
        }
        Scanner reader = new Scanner(new File(args[0]));
        List<String> lines = new ArrayList<>();
        while (reader.hasNextLine()) {
            lines.add(reader.nextLine());
        }

        // Make 2d char array of tiles
        rows = lines.size();
        cols = lines.get(0).length();
        tiles = new char[rows][cols];
        for (int i = 0; i < lines.size(); i++)
            tiles[i] = lines.get(i).toCharArray();

        // Make a list of starting beams
        List<Beam> starts = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            starts.add(new Beam(r, 0, Direction.right));
            starts.add(new Beam(r, cols - 1, Direction.left));
        }
        for (int c = 0; c < cols; c++) {
            starts.add(new Beam(0, c, Direction.down));
            starts.add(new Beam(rows - 1, c, Direction.up));
        }

        int best = 0;
        for (Beam start : starts) {
            Stack<Beam> beams = new Stack<>();
            beams.push(start);
            Set<String> visited = new HashSet<>();
            boolean[][] energized = new boolean[rows][cols];
            int energizedTiles = 0;

            while (beams.size() > 0) {
                Beam b = beams.pop();
                visited.add(b.toString());
                if (!energized[b.row][b.col]) {
                    energized[b.row][b.col] = true;
                    energizedTiles++;
                }
                for (Beam propagated : propagateBeam(b)) {
                    if (!visited.contains(propagated.toString()))
                        beams.push(propagated);
                }
            }
            if (energizedTiles > best)
                best = energizedTiles;
        }

        System.out.println(best);
        reader.close();
    }

    static List<Beam> propagateBeam(Beam b) {
        List<Beam> beams = new ArrayList<>();
        char currTile = tiles[b.row][b.col];
        switch (currTile) {
            case '.' -> {
                int[] v = Direction.vectors[b.direction];
                beams.add(new Beam(b.row+v[0], b.col+v[1], b.direction));
            }
            case '\\' -> {
                int nextDir =
                    (b.direction == Direction.up) ? Direction.left :
                    (b.direction == Direction.down) ? Direction.right :
                    (b.direction == Direction.left) ? Direction.up :
                    Direction.down;

                int[] v = Direction.vectors[nextDir];
                beams.add(new Beam(b.row+v[0], b.col+v[1], nextDir));
            }
            case '/' -> {
                int nextDir =
                    (b.direction == Direction.up) ? Direction.right :
                    (b.direction == Direction.down) ? Direction.left :
                    (b.direction == Direction.left) ? Direction.down :
                    Direction.up;

                int[] v = Direction.vectors[nextDir];
                beams.add(new Beam(b.row+v[0], b.col+v[1], nextDir));
            }
            case '-' -> {
                // Along same direction
                if (b.direction == Direction.left || b.direction == Direction.right) {
                    int[] v = Direction.vectors[b.direction];
                    beams.add(new Beam(b.row+v[0], b.col+v[1], b.direction));
                }
                // up / down reflects to < >
                else {
                    int[] lv = Direction.vectors[Direction.left];
                    int[] rv = Direction.vectors[Direction.right];
                    beams.add(new Beam(b.row+lv[0], b.col+lv[1], Direction.left));
                    beams.add(new Beam(b.row+rv[0], b.col+rv[1], Direction.right));
                }
            }
            case '|' -> {
                // Along same direction
                if (b.direction == Direction.up || b.direction == Direction.down) {
                    int[] v = Direction.vectors[b.direction];
                    beams.add(new Beam(b.row+v[0], b.col+v[1], b.direction));
                }
                // left / right reflects to ^ v
                else {
                    int[] uv = Direction.vectors[Direction.up];
                    int[] dv = Direction.vectors[Direction.down];
                    beams.add(new Beam(b.row+uv[0], b.col+uv[1], Direction.up));
                    beams.add(new Beam(b.row+dv[0], b.col+dv[1], Direction.down));
                }
            }
        }

        // Filter out invalid positions
        List<Beam> propagated = new ArrayList<>();
        for (Beam bm : beams) {
            if (bm.row >= 0 && bm.row < rows && bm.col >= 0 && bm.col < cols) {
                propagated.add(bm);
            }
        }
        return propagated;
    }
}
