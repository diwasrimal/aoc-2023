import java.util.ArrayList;
import java.util.List;

public class Map {
    public String src;
    public String dst;
    public List<Interval> intervals = new ArrayList<>();;

    Map(String s, String d) {
        this.src = s;
        this.dst = d;
    }

    public void addInterval(Interval i) {
        this.intervals.add(i);
    }

    public long getDstNumber(long srcNum) {
        // Check if given number has a specific dst
        for (Interval i : this.intervals) {
            if (i.srcStart <= srcNum && srcNum < i.srcStart + i.range)
                return srcNum + i.diff;
        }

        // Else just return the given number
        return srcNum;
    }
}