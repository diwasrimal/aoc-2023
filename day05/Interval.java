package day05;

class Interval {
    long srcStart;
    long dstStart;
    long diff;
    long range;

    Interval(long srcStart, long dstStart, long range) {
        this.srcStart = srcStart;
        this.dstStart = dstStart;
        this.diff = dstStart - srcStart;
        this.range = range;
    }
}