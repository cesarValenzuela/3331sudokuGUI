package sudoku.model;

import com.cesarvalenzuela.Square;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** An abstraction of Sudoku puzzle. */
public class Board {
//    public static void main(String[] args) {
//        Board b = new Board(9);
//    }

    /** Size of this board (number of columns/rows). */
    public final int size;

    /** Squares of this board. */
    private final List<Square> squares;

    /** Create a new board of the given size. */
    public Board(int size) {
        this.size = size;
        this.squares = new ArrayList<>(size * size);
        // WRITE YOUR CODE HERE ...
        for (int x = 0; x < size; x++) { // store in column-major
            for (int y = 0; y < size; y++) {
                this.squares.add(new Square(x, y));
            }
        }
    }
    /** Return the size of this board. */
    public int size() {
    	return size;
    }

    // or a slow but more robust version:
    public Square getSquare(int x, int y) {
        for (Square s: squares) {
            if (s.getX() == x && s.getY() == y) {
                return s;
            }
        }
        return null;
    }

    /** Return an unmodifiable list of all the squares of this board. */
    public List<Square> squares() {
        return Collections.unmodifiableList(squares);
    }

    // here's a benefit of using a collection, not a 2-d array; no indexes!
    public void clear() {
        squares.forEach(s -> s.clear()); // for (Square s: squares) { s.clear(); }
    }
}
