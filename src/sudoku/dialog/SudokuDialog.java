package sudoku.dialog;

import sudoku.model.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;

/**
 * A dialog template for playing simple Sudoku games.
 * You need to write code for three callback methods:
 * newClicked(int), numberClicked(int) and boardClicked(int,int).
 *
 * @author Yoonsik Cheon
 */
@SuppressWarnings("serial")
public class SudokuDialog extends JFrame {

    /** Default dimension of the dialog. */
    private final static Dimension DEFAULT_SIZE = new Dimension(310, 430);

    private final static String IMAGE_DIR = "/image/";

    /** Sudoku board. */
    private Board board;

    /** Special panel to display a Sudoku board. */
    private BoardPanel boardPanel;

    /** Message bar to display various messages. */
    private JLabel msgBar = new JLabel("");

    /** Create a new dialog. */
    public SudokuDialog() {
    	this(DEFAULT_SIZE);
    }
    
    /** Create a new dialog of the given screen dimension. */
    public SudokuDialog(Dimension dim) {
        super("Sudoku");
        setSize(dim);
        board = new Board(9);
        boardPanel = new BoardPanel(board, this::boardClicked);
        configureUI();
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        //setResizable(false);
    }

    /** Modified Constructor for creating a new game from buttons */
    public SudokuDialog(Dimension dim, int size){
        super("Sudoku");
        setSize(dim);
        board = new Board(size);
        boardPanel = new BoardPanel(board, this::boardClicked);
        configureUI();
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        //setResizable(false);
    }

    /**
     * Callback to be invoked when a square of the board is clicked.
     * @param x 0-based row index of the clicked square.
     * @param y 0-based column index of the clicked square.
     */
    private void boardClicked(int x, int y) {
        // WRITE YOUR CODE HERE ...
        //
        board.combine(x,y);

        repaint();
    	showMessage(String.format("Board clicked: x = %d, y = %d",  x, y));
    }
    
    /**
     * Callback to be invoked when a number button is clicked.
     * @param number Clicked number (1-9), or 0 for "X".
     */
    private void numberClicked(int number) {
        // WRITE YOUR CODE HERE ...
        //
        board.storeNum(number);
        showMessage("Number clicked: " + number);
    }
    
    /**
     * Callback to be invoked when a new button is clicked.
     * If the current game is over, start a new game of the given size;
     * otherwise, prompt the user for a confirmation and then proceed
     * accordingly.
     * @param size Requested puzzle size, either 4 or 9.
     */
    private void newClicked(int size) {
        // WRITE YOUR CODE HERE ...
        // IF size does not match board size > dialog and make new board
        // IF size equals board size -> just clear.
        if (size == board.size){
            JOptionPane.showConfirmDialog(this,"Start a new game?");
            this.dispose();
            new SudokuDialog(DEFAULT_SIZE, size);

        } else {
            board.clear();
            repaint();
        }


        showMessage("New clicked: " + size);
    }

    /**
     * Display the given string in the message bar.
     * @param msg Message to be displayed.
     */
    private void showMessage(String msg) {
        msgBar.setText(msg);
    }

    /** Configure the UI. */
    private void configureUI() {
        setIconImage(createImageIcon("sudoku.png").getImage());
        setLayout(new BorderLayout());
        setJMenuBar(getJMenuBar());

        // Panel combining numbers and toolbar
        JPanel northPanel = new JPanel(new GridLayout(2, 1));

        JPanel buttons = new JPanel();
        // boarder: top, left, bottom, right
        buttons.setBorder(BorderFactory.createEmptyBorder(10,16,0,16));
        northPanel.add(createToolBar());
        northPanel.add(makeControlPanel());

        add(northPanel, BorderLayout.NORTH);

        
        JPanel board = new JPanel();
        board.setBorder(BorderFactory.createEmptyBorder(10,16,0,16));
        board.setLayout(new GridLayout(1,1));
        board.add(boardPanel);
        add(board, BorderLayout.CENTER);
        
        msgBar.setBorder(BorderFactory.createEmptyBorder(10,16,10,0));
        add(msgBar, BorderLayout.SOUTH);
    }
      
    /** Create a control panel consisting of new and number buttons. */
    private JPanel makeControlPanel() {
        //HW2 4x4 and 9x9 buttons
//    	JPanel newButtons = new JPanel(new FlowLayout());
//        JButton new4Button = new JButton("New (4x4)");
//        for (JButton button: new JButton[] { new4Button, new JButton("New (9x9)") }) {
//        	button.setFocusPainted(false);
//            button.addActionListener(e -> {
//                newClicked(e.getSource() == new4Button ? 4 : 9);
//            });
//            newButtons.add(button);
//    	}
//    	newButtons.setAlignmentX(LEFT_ALIGNMENT);

    	// buttons labeled 1, 2, ..., 9, and X.
    	JPanel numberButtons = new JPanel(new FlowLayout());
    	int maxNumber = board.size() + 1;
    	for (int i = 1; i <= maxNumber; i++) {
            int number = i % maxNumber;
            JButton button = new JButton(number == 0 ? "X" : String.valueOf(number));
            button.setFocusPainted(false);
            button.setMargin(new Insets(0,2,0,2));
            button.addActionListener((ActionEvent e) -> numberClicked(number));
    		numberButtons.add(button);
    	}
    	numberButtons.setAlignmentX(LEFT_ALIGNMENT);

    	JPanel content = new JPanel();
    	content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        //content.add(newButtons);
        content.add(numberButtons);
        return content;
    }


    protected JToolBar createToolBar(){

        JToolBar toolBar = new JToolBar("Sudoku");

        JButton button = new JButton(createImageIcon("play.png"));
        JButton button2 = new JButton(createImageIcon("solve.png"));

        button.addActionListener(e -> {
            newClicked(9);
        });
        button.setToolTipText("Play a new game");
        button.setFocusPainted(false);

//        button2.addActionListener(e -> {
//            newClicked(9);
//        });
        button2.setToolTipText("Solve the puzzle");
        button2.setFocusPainted(false);

        toolBar.add(button);
        toolBar.add(button2);
        return toolBar;
    }

    @Override
    public JMenuBar getJMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Game");
        menu.setMnemonic(KeyEvent.VK_G);
        menu.getAccessibleContext().setAccessibleDescription("Game menu");
        menuBar.add(menu);

        JMenuItem menuItem = new JMenuItem("New Game", KeyEvent.VK_N);
        JMenuItem menuItem2 = new JMenuItem("Solve", KeyEvent.VK_N);

        menuItem.setIcon(createImageIcon("play.png"));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Play a new game");
        menuItem2.setIcon(createImageIcon("solve.png"));
        menuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
        menuItem2.getAccessibleContext().setAccessibleDescription("Solve the puzzle.");
        menuItem.addActionListener(e -> {
            newClicked(9);
        });
//        menuItem2.addActionListener();
        menu.add(menuItem);
        menu.add(menuItem2);

        return menuBar;
    }

    /** Create an image icon from the given image file. */
    public ImageIcon createImageIcon(String filename) {
        URL imageUrl = getClass().getResource(IMAGE_DIR + filename);
        if (imageUrl != null) {
            return new ImageIcon(imageUrl);
        }
        return null;
    }

    public static void main(String[] args) {
        new SudokuDialog();

    }
}
