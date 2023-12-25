import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Lens {
    String label;
    int focalLength;
    Lens(String label, int focalLength) {
        this.label = label;
        this.focalLength = focalLength;
    }
    public String toString() {
        return "[" + this.label + " " + this.focalLength + "]";
    }
}

public class Two {
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

        List<List<Lens>> boxes = new ArrayList<List<Lens>>(256);
        for (int i = 0; i < 256; i++)
            boxes.add(i, new ArrayList<Lens>());

        for (String pattern : patterns) {
            String[] parts = pattern.split("=|-");
            String label = parts[0];
            List<Lens> box = boxes.get(findHash(label));
            
            if (pattern.contains("-")) {
                for (int i = 0; i < box.size(); i++) {
                    if (box.get(i).label.equals(label)) {
                        box.remove(i);
                        break;
                    }
                }
            }
            else if (pattern.contains("=")) {
                int focalLength = Integer.parseInt(parts[1]);
                Lens lens = new Lens(label, focalLength);
                boolean found = false;
                for (int i = 0; i < box.size(); i++) {
                    if (box.get(i).label.equals(label)) {
                        box.set(i, lens);
                        found = true;
                        break;
                    }
                }
                if (!found)
                    box.add(lens);
            }
        }

        for (int i = 0; i < boxes.size(); i++) {
            if (!boxes.get(i).isEmpty())
                System.out.println("Box " + i + ": " + boxes.get(i));
        }

        // Find total focusing power
        int totalPower = 0;
        for (int b = 0; b < boxes.size(); b++) {
            for (int l = 0; l < boxes.get(b).size(); l++) {
                totalPower += (b + 1) * (l + 1) * boxes.get(b).get(l).focalLength;
            }
        }
        System.out.println(totalPower);
    }

    static int findHash(String s) {
        int hash = 0;
        for (int i = 0; i < s.length(); i++)
            hash = ((hash + (int)s.charAt(i)) * 17) % 256;
        return hash;
    }
}
