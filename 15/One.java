import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class One {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 1) {
            System.out.println("Provide input file!");
            return;
        }
        Scanner reader = new Scanner(new File(args[0]));
        List<String> patterns = new ArrayList<>();
        reader.useDelimiter(",");
        while (reader.hasNext()) {
            patterns.add(reader.next());
        }
        reader.close();

        int total = 0;
        for (String pattern : patterns)
            total += findHash(pattern);

        System.out.println(total);
    }

    static int findHash(String s) {
        int hash = 0;
        for (int i = 0; i < s.length(); i++)
            hash = ((hash + (int)s.charAt(i)) * 17) % 256;
        return hash;
    }
}
