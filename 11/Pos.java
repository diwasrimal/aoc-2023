public class Pos {
    public int col;
    public int row;

    Pos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "(" + this.row + ", " + this.col + ")";
    }
}