public class Interval {
    public long srcStart;
    public long dstStart;
    public long diff;
    public long range;

    Interval(long srcStart, long dstStart, long range) {
        this.srcStart = srcStart;
        this.dstStart = dstStart;
        this.diff = dstStart - srcStart;
        this.range = range;
    }
}