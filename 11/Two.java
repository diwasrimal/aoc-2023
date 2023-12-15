import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Two {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 1) {
            System.out.println("Provide filename as argument!");
            return;
        }
        Scanner reader = new Scanner(new File(args[0]));
        List<String> input = new ArrayList<>();
        while (reader.hasNextLine()) {
            input.add(reader.nextLine());
        }

        int height = input.size();
        int width = input.get(0).length();
        char[][] map = new char[height][width];
        List<Pos> galaxyPositions = new ArrayList<>();

        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                char ch = input.get(r).charAt(c);
                if (ch == '#') {
                    galaxyPositions.add(new Pos(r, c));
                }
                map[r][c] = ch;
            }
        }

        // Figure out empty rows and cols
        boolean[] isEmptyRow = new boolean[height];
        boolean[] isEmptyCol = new boolean[width];
        for (int r = 0; r < height; r++) {
            if (!input.get(r).contains("#")) {
                isEmptyRow[r] = true;
            }
        }
        for (int c = 0; c < width; c++) {
            String vertical = "";
            for (int r = 0; r < height; r++) {
                vertical += map[r][c];
            }
            if (!vertical.contains("#")) {
                isEmptyCol[c] = true;
            }
        }

        // Amount of skips for empty row/col
        long skips = 1_000_000;
        int galaxies = galaxyPositions.size();

        // Find manhattan distance between each galaxy pairs
        long sum = 0;
        for (int i = 0; i < galaxies - 1; i++) {
            for (int j = i + 1; j < galaxies; j++) {
                Pos s = galaxyPositions.get(i);
                Pos d = galaxyPositions.get(j);
                long dist = 0;
                for (int r = Math.min(s.row, d.row); r < Math.max(s.row, d.row); r++) {
                    dist += isEmptyRow[r] ? skips : 1l;
                }
                for (int c = Math.min(s.col, d.col); c < Math.max(s.col, d.col); c++) {
                    dist += isEmptyCol[c] ? skips : 1l;
                }
                sum += dist;
            }
        }
        System.out.println(sum);
        reader.close();
    }
}
