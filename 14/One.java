import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class One {
    static private char[][] map;
    static int mapRows;
    static int mapCols;

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
        reader.close();

        // Initialize map
        mapRows = lines.size();
        mapCols = lines.get(0).length();
        map = new char[mapRows][mapCols];
        for (int i = 0; i < lines.size(); i++)
            map[i] = lines.get(i).toCharArray();

        // Tilt once and calculate loads
        tiltNorth();
        int load = 0;
        for (int r = 0; r < mapRows; r++) {
            for (int c = 0; c < mapCols; c++) {
                if (map[r][c] == 'O') {
                    load += mapRows - r;
                }
            }
        }
        System.out.println(load);
    }

    // Performs insertion sort upwards
    static void tiltNorth() {
        for (int c = 0; c < mapCols; c++) {
            for (int r = 0; r < mapRows; r++) {
                if (map[r][c] == 'O') {
                    int up = r - 1;
                    while (up >= 0 && map[up][c] == '.')
                        up--;
                    map[r][c] = '.';
                    map[up + 1][c] = 'O';
                }
            }
        }
    }
}
