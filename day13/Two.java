package day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Two {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 1) {
            System.out.println("Provide input file!");
            return;
        }

        Scanner reader = new Scanner(new File(args[0]));
        List<List<String>> maps = new ArrayList<List<String>>();
        reader.useDelimiter("\\n\\n");
        while (reader.hasNext()) {
            maps.add(Arrays.asList(reader.next().split("\n")));
        }
        reader.close();

        int total = 0;
        for (List<String> map : maps) {
            int upperHalf = horizontalCheck(map);
            List<String> transposed = transpose(map);
            int leftHalf = horizontalCheck(transposed);
            total += upperHalf * 100 + leftHalf;
        }

        System.out.println(total);
    }

    // Find mirror that has just one character mismatched
    static int horizontalCheck(List<String> map) {
        for (int row = 1; row < map.size(); row++) {
            int up = row - 1;
            int down = row;
            int totalMismatches = 0;
            while (up >= 0 && down < map.size()) {
                totalMismatches += mismatches(map.get(up), map.get(down));
                up--;
                down++;
            }
            if (totalMismatches == 1) return row;
        }
        return 0;
    }

    static List<String> transpose(List<String> list) {
        List<String> transposed = new ArrayList<>();
        for (int col = 0; col < list.get(0).length(); col++) {
            String s = "";
            for (int row = 0; row < list.size(); row++) s += list.get(row).charAt(col);
            transposed.add(s);
        }
        return transposed;
    }

    static int mismatches(String a, String b) {
        int mismatch = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                mismatch++;
            }
        }
        return mismatch;
    }
}
