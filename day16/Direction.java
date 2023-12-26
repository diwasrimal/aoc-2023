package day16;

class Direction {
    static final int up = 0;
    static final int down = 1;
    static final int left = 2;
    static final int right = 3;
    static final char[] arrows = new char[]{'^', 'v', '<', '>'};
    static final int[][] vectors = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
}
