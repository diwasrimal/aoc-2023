package day11;

class Pos {
    int col;
    int row;

    Pos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "(" + this.row + ", " + this.col + ")";
    }
}