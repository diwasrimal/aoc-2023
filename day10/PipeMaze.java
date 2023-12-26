package day10;

import java.util.*;

class PipeMaze {
    Pipe[][] pipes;
    Pipe startPipe;
    final int width;
    final int height;

    PipeMaze(List<String> input) {
        this.width = input.get(0).length();
        this.height = input.size();
        this.pipes = new Pipe[this.height][this.width];

        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                Pipe pipe = new Pipe(input.get(y).charAt(x), y, x);
                this.pipes[y][x] = pipe;
                if (pipe.symbol == Pipe.startSymbol) {
                    startPipe = pipe;
                }
            }
        }
    }

    List<Pipe> findMovablePipes(Pipe src) {
        List<Pipe> movables = new ArrayList<>();
        if (src.symbol == Pipe.invalidSymbol)
            return movables;

        int[] xs = new int[]{1, -1, 0, 0};  // east west north south
        int[] ys = new int[]{0, 0, -1, 1};
        for (int d = 0; d < 4; d++) {
            int y = src.y + ys[d];
            int x = src.x + xs[d];
            if (!validIdx(y, x)) {
                continue;
            }

            // Can always move to starting pipe
            if (this.pipes[y][x].symbol == Pipe.startSymbol) {
                movables.add(this.pipes[y][x]);
            } else if (src.canJoinTo(this.pipes[y][x], d)) {
                movables.add(this.pipes[y][x]);
            }
        }
        return movables;
    }

    boolean validIdx(int y, int x) {
        return (y >= 0 && y < this.height && x >= 0 && x < this.width);
    }
}
