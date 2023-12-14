import java.util.*;

public class Pipe {
    final int y;
    final int x;
    final char symbol;
    static final char invalidSymbol = '.';
    static final char startSymbol = 'S';

    // Joinable pipes on east west north south
    // Starting pipe can join in any direction
    static Hashtable<Character, String[]> joinablePipes = new Hashtable<>();
    static final String eJoints = "J-7";
    static final String wJoints = "L-F";
    static final String nJoints = "7|F";
    static final String sJoints = "J|L";
    static {
        joinablePipes.put('-', new String[]{eJoints, wJoints, "", ""});
        joinablePipes.put('|', new String[]{"", "", nJoints, sJoints});
        joinablePipes.put('F', new String[]{eJoints, "", "", sJoints});
        joinablePipes.put('L', new String[]{eJoints, "", nJoints, ""});
        joinablePipes.put('J', new String[]{"", wJoints, nJoints, ""});
        joinablePipes.put('7', new String[]{"", wJoints, "", sJoints});
        joinablePipes.put(startSymbol, new String[]{eJoints, wJoints, nJoints, sJoints});
        joinablePipes.put(invalidSymbol, new String[]{"", "", "", ""});
    }

    Pipe(char symbol, int y, int x) {
        this.symbol = symbol;
        this.y = y;
        this.x = x;
    }

    boolean canJoinTo(Pipe p, int direction) {
        String joinables = this.joinablePipes.get(this.symbol)[direction];
        return joinables.indexOf(p.symbol) != -1;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + this.symbol + " " + this.y + "-" + this.x + "]";
    }
}