package sudoku.model;

import com.cesarvalenzuela.Square;
import sudoku.dialog.SudokuDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** An abstraction of Sudoku puzzle. */
public class Board {

    /** Size of this board (number of columns/rows). */
    public final int size;

    /** Squares of this board. */
    private final List<Square> squares;

    /** Current number to be inserted */
    public Integer numChosen;

    /** Create a new board of the given size. */
    public Board(int size) {
        this.size = size;
        this.numChosen = null;
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

    public void storeNum(int number){
        this.numChosen = number;
    }

    public void combine(int x, int y){
        Integer number = null;
        number = this.numChosen;
        if (number != null){
            getSquare(x,y).value = this.numChosen;
        }
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
