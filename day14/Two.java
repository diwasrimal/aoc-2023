package day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Two {
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

        // To store position after each cycle and find repitition
        HashMap<String, Integer> cycleRecords = new HashMap<>();
        int totalCycles = 1_000_000_000;
        int remainingCycles = 0;

        for (int cycle = 1; cycle < totalCycles; cycle++) {
            tiltNorth();
            tiltWest();
            tiltSouth();
            tiltEast();
            String pos = getMapPosition();
            if (cycleRecords.containsKey(pos)) {
                int last = cycleRecords.get(pos);
                int repeatPeriod = cycle - last;
                remainingCycles = (totalCycles - cycle) % repeatPeriod;
                break;
            }
            cycleRecords.put(pos, cycle);
        }

        for (int i = 0; i < remainingCycles; i++) {
            tiltNorth();
            tiltWest();
            tiltSouth();
            tiltEast();
        }

        // Now calculate loads
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

    static void tiltWest() {
        for (int r = 0; r < mapRows; r++) {
            for (int c = 0; c < mapCols; c++) {
                if (map[r][c] == 'O') {
                    int left = c - 1;
                    while (left >= 0 && map[r][left] == '.')
                        left--;
                    map[r][c] = '.';
                    map[r][left + 1] = 'O';
                }
            }
        }
    }

    static void tiltSouth() {
        for (int c = 0; c < mapCols; c++) {
            for (int r = mapRows - 1; r >= 0; r--) {
                if (map[r][c] == 'O') {
                    int down = r + 1;
                    while (down < mapRows && map[down][c] == '.')
                        down++;
                    map[r][c] = '.';
                    map[down - 1][c] = 'O';
                }
            }
        }
    }

    static void tiltEast() {
        for (int r = 0; r < mapRows; r++) {
            for (int c = mapCols - 1; c >= 0; c--) {
                if (map[r][c] == 'O') {
                    int right = c + 1;
                    while (right < mapCols && map[r][right] == '.')
                        right++;
                    map[r][c] = '.';
                    map[r][right - 1] = 'O';
                }
            }
        }
    }

    static String getMapPosition() {
        String pos = "";
        for (char[] arr : Arrays.asList(map))
            pos += new String(arr);
        return pos;
    }
}
