package day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class One {
    private static HashMap<String, Long> cache = new HashMap<>();

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

        long totalArrangements = 0;

        for (String line : lines) {
            String[] parts = line.split(" ");
            String pattern = parts[0];
            List<Integer> nums = new ArrayList<>();
            for (String s : parts[1].split(",")) {
                nums.add(Integer.parseInt(s));
            }
            totalArrangements += findArrangements(pattern, nums);
        }

        System.out.println(totalArrangements);
        reader.close();
    }

    static long findArrangements(String pattern, List<Integer> nums) {
        // Retrieve from cache if possible
        String key = pattern + nums.toString();
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        if (nums.isEmpty()) {
            return pattern.contains("#") ? 0 : 1;
        }
        if (pattern.isEmpty()) {
            return nums.isEmpty() ? 1 : 0;
        }

        long arrangements = 0;
        char fchar = pattern.charAt(0);
        int fnum = nums.get(0);

        if (fchar == '?' || fchar == '.') {
            arrangements += findArrangements(pattern.substring(1), nums);
        }
        if (fchar == '?' || fchar == '#') {
            if (fnum <= pattern.length()
                    && !pattern.substring(0, fnum).contains(".")
                    && (fnum == pattern.length() || pattern.charAt(fnum) != '#')) {
                String nextPattern = fnum + 1 < pattern.length() ? pattern.substring(fnum + 1) : "";
                arrangements += findArrangements(nextPattern, nums.subList(1, nums.size()));
            }
        }

        cache.put(key, arrangements);
        return arrangements;
    }
}
