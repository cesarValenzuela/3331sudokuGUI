package sudoku.dialog;

import sudoku.model.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A special panel class to display a Sudoku board modeled by the
 * {@link sudoku.model.Board} class. You need to write code for
 * the paint() method.
 *
 * @see sudoku.model.Board
 * @author Yoonsik Cheon
 */
@SuppressWarnings("serial")
public class BoardPanel extends JPanel {
    
	public interface ClickListener {
		
		/** Callback to notify clicking of a square. 
		 * 
		 * @param x 0-based column index of the clicked square
		 * @param y 0-based row index of the clicked square
		 */
		void clicked(int x, int y);
	}
	
    /** Background color of the board. */
	private static final Color boardColor = new Color(247, 223, 150);

    /** Board to be displayed. */
    private Board board;

    /** Width and height of a square in pixels. */
    private int squareSize;

    /** Create a new board panel to display the given board. */
    public BoardPanel(Board board, ClickListener listener) {
        this.board = board;
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	int xy = locateSquaree(e.getX(), e.getY());
            	if (xy >= 0) {
            		listener.clicked(xy / 100, xy % 100);
            	}
            }
        });
    }

    /** Set the board to be displayed. */
    public void setBoard(Board board) {
    	this.board = board;
    }
    
    /**
     * Given a screen coordinate, return the indexes of the corresponding square
     * or -1 if there is no square.
     * The indexes are encoded and returned as x*100 + y, 
     * where x and y are 0-based column/row indexes.
     */
    private int locateSquaree(int x, int y) {
    	if (x < 0 || x > board.size * squareSize
    			|| y < 0 || y > board.size * squareSize) {
    		return -1;
    	}
    	int xx = x / squareSize;
    	int yy = y / squareSize;
    	return xx * 100 + yy;
    }

    /** Draw the associated board. */
    @Override
    public void paint(Graphics g) {
        super.paint(g); 

        // determine the square size
        Dimension dim = getSize();
        squareSize = Math.min(dim.width, dim.height) / board.size;

        // draw background
        final Color oldColor = g.getColor();
        g.setColor(boardColor);
        g.fillRect(0, 0, squareSize * board.size, squareSize * board.size);

        // WRITE YOUR CODE HERE ...
        // i.e., draw grid and squares.
        // adapted from // adapted from https://www.leepoint.net/GUI/examples/sudoku/ex-sudoku-v3.html
        g.setColor(Color.BLACK);
        for (int i = 0; i <= board.size ; i++) {
            int line = i * squareSize;
            g.drawLine(line,0,line,(board.size * squareSize));
            g.drawLine(0,line,(board.size * squareSize),line);

            if (board.size == 4){
                if(i % 2 == 0){
                    int darkerLine = line + 1;
                    g.drawLine(darkerLine,0,darkerLine,(board.size * squareSize));
                    g.drawLine(0,darkerLine,(board.size * squareSize),darkerLine);
                }
            }
            if (board.size == 9){
                if(i % 3 == 0){
                    int darkerLine = line + 1;
                    g.drawLine(darkerLine,0,darkerLine,(board.size * squareSize));
                    g.drawLine(0,darkerLine,(board.size * squareSize),darkerLine);
                }
            }
        }

        // TEXT
        // adapted from // adapted from https://www.leepoint.net/GUI/examples/sudoku/ex-sudoku-v3.html
        g.getFont();
        for (int i = 0; i < board.size ; i++) {
            int ydis = (i + 1) * squareSize - 10;
            for (int j = 0; j < board.size ; j++) {
                if(board.getSquare(j,i).value != null){
                    int xdisp = j * squareSize + 7;
                    g.drawString(" " + board.getSquare(j,i).value, xdisp, ydis);
                }
            }
        }
    }

}
