package day16;

class Beam {
    int row;
    int col;
    int direction;

    Beam(int row, int col, int direction) {
        this.row = row;
        this.col = col;
        this.direction = direction;
    }
    
    public String toString() {
        String dirs[] = new String[]{"^", "v", "<", ">"};
        return "[" + this.row + " " + this.col + " " + Direction.arrows[this.direction] + "]";
    }
}