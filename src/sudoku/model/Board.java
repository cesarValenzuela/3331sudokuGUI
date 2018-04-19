package sudoku.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

        for (int x = 0; x < size; x++) { // store in column-major
            for (int y = 0; y < size; y++) {
                this.squares.add(new Square(x, y));
            }
        }
        randomize();
    }
    /** Return the size of this board. */
    public int size() {
    	return size;
    }

    public Square getSquare(int x, int y){
        return squares.get(x*size+y);
    }

    // or a slow but more robust version:
//    public Square getSquare(int x, int y) {
//        for (Square s: squares) {
//            if (s.getX() == x && s.getY() == y) {
//                return s;
//            }
//        }
//        return null;
//    }

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

    public void clear() {
        squares.forEach(s -> s.clear()); // for (Square s: squares) { s.clear(); }
    }

    public void randomize(){
       double startNum;
        int[][] sBoard = new int[this.size][this.size];
        Random rand = new Random();

        for (int r = 0; r < this.size ; r++) {
            startNum = (Math.sqrt(this.size)) * (r % (Math.sqrt(this.size))) + (r / (Math.sqrt(this.size)));
            for (int c = 0; c < this.size ; c++) {
                sBoard[r][c] = (int) (((startNum + c) % this.size) + 1);
            }
        }

        for (int row = 0; row < this.size ; row++) {
            for (int column = 0; column < this.size ; column++) {
                getSquare(row, column).value = sBoard[row][column];
            }
        }

        for (int i = 0; i < squares.size()/3 ; i++) {
            int randNum = rand.nextInt(80);
            squares.get(randNum).value = null;
        }
    }



    public void solve(){

    }

    public class Square{
        public Integer value;

        public int x;

        public int y;

        public Square(int x, int y){
            this.x = x;
            this.y = y;
            this.value = null;
        }

        public int getX(){
            return x;
        }
        public int getY(){
            return y;
        }

        public void clear(){
            this.value = null;
        }

    }
}
