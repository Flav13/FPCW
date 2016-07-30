/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fm263game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author fm263
 */
public class PathGameFrame extends JFrame {

    private final PathBoardTable board;
    final JTextField playerOneLabel;
    final JTextField playerTwoLabel;
    final JLabel scoreOne;
    final JLabel scoreTwo;
    final Player playerOne;
    final Player playerTwo;
    final RandomPlayer playerR;
    protected int sRow;
    protected int sCol;
    protected boolean thePlayer;
    private JTextField current;
    private JRadioButton b3;
    private boolean compPlaying;
    private int score;
    private HighScore highScore;
    private HighScoreFile f;
    private JTextField cpu;

    /**
     * Initialises the global variables used in multiple methods. and calls the
     * main menu method where player and board size details are taken
     *
     * @param board takes in the abstract board table
     * @param title takes in a specific title of the game
     */
    public PathGameFrame(PathBoardTable board, String title) {
        super(title);
        this.board = board;
        mainMenu();
        playerOneLabel = new JTextField();
        playerTwoLabel = new JTextField();
        thePlayer = true;
        scoreOne = new JLabel("Score 0");
        scoreTwo = new JLabel("Score 0");
        playerOne = new Player();
        playerTwo = new Player();
        playerR = new RandomPlayer();
        score = 0;
        highScore = new HighScore();
        f = new HighScoreFile();

    }

    /**
     * Initialises the game menu where the board, player details and reset
     * button are placed
     */
    private void gameMenu() {
        JPanel panel = new JPanel();
        panel.add(playerDetails());
        panel.add(theBoard());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

    }

    /**
     * This method randomly places a tile adjacent to the previously placed
     * tile, if the tile chosen is already taken, it uses recursion to randomly
     * pick another tile until a free one is found.
     *
     * @param row - Random row taken
     * @param col - Random column taken
     *
     */
    public void compPlaceTile(int row, int col) {
        if (board.placePathTile(row, col) == null) {
            int i = playerR.getRow(board.getRowCount());
            int j = playerR.getCol(board.getColumnCount());
            compPlaceTile(i, j);
            board.fireTableDataChanged();

        } else {
            if (board.adjacent(row, col)) {
                if (playerOneLabel.getText().equals("CPU")) {
                    scoreOne.setText("Score " + Integer.toString(playerR.setScore(score)));
                } else {
                    scoreTwo.setText("Score " + Integer.toString(playerR.setScore(score)));
                }
                JOptionPane.showMessageDialog(PathGameFrame.this, cpu.getText() + " won!");
                board.fireTableDataChanged();
            }
        }

    }

    /**
     * creates the panel that displays the high scores when the "High Scores"
     * button is pressed from the game frame. It takes all entries from the
     * String array loaded from the file, and breaks it down so it can be
     * displayed vertically
     *
     * @return A pop-up window with the high scores in order.
     */
    private Component scoresPanel() {
        String[] scores = f.load("highScores");
        JPanel scoresPanel = new JPanel();

        for (int i = 0; i < scores.length; i++) {
            JLabel t = new JLabel(scores[i]);
            scoresPanel.add(t);
        }
        scoresPanel.setLayout(new BoxLayout(scoresPanel, BoxLayout.Y_AXIS));
        return scoresPanel;
    }

    /**
     * Initialises the game board. Creates a new JTable that takes in the board
     * AbsractTableModel. It sets up functionality to the board table and the
     * clear button by adding listeners. The listeners react to mouse clicks.
     *
     * When "b", the reset button is pressed, the board is cleared of cells, and
     * if there's an AI playing as player 1, a random cell is placed.
     *
     * When a cell is clicked, the program checks if there's an AI playing. If
     * there is, a cell is automatically placed next to the player's placed
     * cell. When a human player wins, their high scores are recorded.
     *
     * If both players are humans, a boolean alternates between them. Same rules
     * apply as for the human playing against the AI.
     * 
     * When the b4 "New Game" button is pressed, the program rolls back to the 
     * main menu 
     * @return the board
     */
    public Component theBoard() {
        final JTable table;
        table = new JTable(board);
        final JPanel panel = new JPanel(), bPanel = new JPanel();
        table.setTableHeader(null);

        JButton b = new JButton("Reset Game");
        JButton b2 = new JButton("High Scores");
        JButton b4 = new JButton("New Game");

        b4.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                board.clear();
                score = board.getColumnCount() * board.getRowCount();
                dispose();
                PathGameFrame pathGameFrame = new PathGameFrame(board,"Path Game");

            }
        });

        b.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                board.clear();
                score = board.getColumnCount() * board.getRowCount();
                board.fireTableDataChanged();
                thePlayer = true;
                current.setText("Current Player: " + playerOneLabel.getText());
                if (compPlaying) {
                    if (playerOneLabel.getText().equals("CPU")) {
                        if (board.isEmpty()) {

                            board.placePathTile(playerR.getRandomRow(board.getRowCount()), playerR.getRandomCol(board.getColumnCount()));
                            board.fireTableDataChanged();
                            score--;

                        }
                    }
                }
            }
        });

        b2.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mousePressed(MouseEvent e) {
                        System.out.println(Arrays.toString(f.load("highScores")));
                        JOptionPane.showMessageDialog(panel, scoresPanel());
                    }
                });

        table.setRowSelectionAllowed(
                false);
        final CellRenderer myRenderer = new CellRenderer();

        table.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mousePressed(MouseEvent e) {
                        JTable target = (JTable) e.getSource();
                        table.setCellSelectionEnabled(false);
                        sRow = target.getSelectedRow();
                        sCol = target.getSelectedColumn();
                        try {
                            if (compPlaying) {
                                if (playerTwoLabel.getText().equals("CPU")) {

                                    if (board.placePathTile(sRow, sCol) != null) {
                                        if (!board.adjacent(sRow, sCol)) {
                                            current.setText("Current Player: " + playerOneLabel.getText());
                                            score--;
                                            playerR.putMoves(sRow, sCol);
                                            compPlaceTile(playerR.getRow(board.getRowCount()), playerR.getCol(board.getColumnCount()));
                                            board.fireTableDataChanged();
                                            score--;
                                            table.setDefaultRenderer(Object.class, myRenderer);

                                        } else {
                                            scoreOne.setText("Score " + Integer.toString(playerOne.setScore(score)));
                                            JOptionPane.showMessageDialog(PathGameFrame.this, playerOneLabel.getText() + " won!");
                                            playerOne.setHighScore(playerOne.getScore());
                                            highScore.addPlayerScore(playerOne);
                                            f.save(makeHighScoreEntries(), "highScores");


                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(PathGameFrame.this, "Not a legal move !");
                                    }
                                } else {
                                    if (board.placePathTile(sRow, sCol) != null) {
                                        if (!board.adjacent(sRow, sCol)) {
                                            current.setText("Current Player: " + playerTwoLabel.getText());
                                            score--;
                                            playerR.putMoves(sRow, sCol);
                                            compPlaceTile(playerR.getRow(board.getRowCount()), playerR.getCol(board.getColumnCount()));
                                            board.fireTableDataChanged();
                                            score--;
                                            table.setDefaultRenderer(Object.class, myRenderer);

                                        } else {
                                            scoreTwo.setText("Score " + Integer.toString(playerTwo.setScore(score)));
                                            JOptionPane.showMessageDialog(PathGameFrame.this, playerTwoLabel.getText() + " won!");
                                            playerTwo.setHighScore(playerTwo.getScore());
                                            highScore.addPlayerScore(playerTwo);
                                            f.save(makeHighScoreEntries(), "highScores");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(PathGameFrame.this, "Not a legal move !");
                                    }
                                }
                            } else {
                                if (thePlayer) {
                                    if (board.placePathTile(sRow, sCol) != null) {
                                        if (!board.adjacent(sRow, sCol)) {
                                            score--;
                                            current.setText("Current Player: " + playerTwoLabel.getText());
                                            thePlayer = false;
                                            table.setDefaultRenderer(Object.class, myRenderer);

                                        } else {
                                            score--;
                                            scoreOne.setText("Score " + Integer.toString(playerOne.setScore(score)));
                                            JOptionPane.showMessageDialog(PathGameFrame.this, playerOneLabel.getText() + " won!");
                                            thePlayer = true;
                                            playerOne.setHighScore(playerOne.getScore());
                                            highScore.addPlayerScore(playerOne);
                                            f.save(makeHighScoreEntries(), "highScores");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(PathGameFrame.this, "Not a legal move !");
                                    }
                                } else {
                                    if (board.placePathTile(sRow, sCol) != null) {
                                        if (!board.adjacent(sRow, sCol)) {
                                            score--;
                                            current.setText("Current Player: " + playerOneLabel.getText());
                                            thePlayer = true;
                                            table.setDefaultRenderer(Object.class, myRenderer);
                                        } else {
                                            score--;
                                            scoreTwo.setText("Score " + Integer.toString(playerTwo.setScore(score)));
                                            JOptionPane.showMessageDialog(PathGameFrame.this, playerTwoLabel.getText() + " won!");
                                            thePlayer = true;
                                            playerTwo.setHighScore(playerTwo.getScore());
                                            highScore.addPlayerScore(playerTwo);
                                            f.save(makeHighScoreEntries(), "highScores");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(PathGameFrame.this, "Not a legal move !");
                                    }
                                }
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(PathGameFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
        panel.add(table);

        bPanel.add(b);

        bPanel.add(b2);

        bPanel.add(b4);

        panel.add(bPanel);

        panel.setLayout(
                new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

    /**
     * Creates the player fields and score fields whose details are taken from
     * the mainMenu method. They are added to a single panel and given a grid
     * layout
     *
     * @return the panel with the player and score fields.
     */
    public Component playerDetails() {
        JPanel names = new JPanel(), scores = new JPanel(),
                nameScores = new JPanel();
        JPanel all = new JPanel();
        current = new JTextField("Current Player: " + playerOneLabel.getText());

        playerOneLabel.setEditable(false);
        playerTwoLabel.setEditable(false);
        playerOneLabel.setBackground(Color.white);
        playerTwoLabel.setBackground(Color.white);
        playerOneLabel.setForeground(Color.red);
        playerTwoLabel.setForeground(Color.cyan);

        scoreOne.setForeground(Color.red);
        scoreTwo.setForeground(Color.cyan);

        names.add(playerOneLabel);
        names.add(playerTwoLabel);
        names.setLayout(new BoxLayout(names, BoxLayout.Y_AXIS));

        scores.add(scoreOne);
        scores.add(scoreTwo);
        scores.setLayout(new BoxLayout(scores, BoxLayout.Y_AXIS));

        nameScores.add(names);
        nameScores.add(scores);
        nameScores.setLayout(new BoxLayout(nameScores, BoxLayout.X_AXIS));

        all.add(nameScores);
        all.add(current);
        all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));


        return all;
    }

    /**
     * Creates the main menu where the size of the board and player names are
     * initiated. These values are stored in global variables so they can be
     * used in other methods.
     *
     * When the "start game" button is pressed, the previous high scores are
     * loaded in, text fields validated and if the player chose a game against
     * the AI, a cell is randomly placed.
     */
    public void mainMenu() {
        final JPanel panel = new JPanel(), textPanel = new JPanel(),
                labelPanel = new JPanel(),
                dataPanel = new JPanel(), buttonPanel = new JPanel();

        final JTextField row = new JTextField();
        final JTextField col = new JTextField();
        b3 = new JRadioButton();

        final JTextField player1 = new JTextField();
        final JTextField player2 = new JTextField();

        final JLabel rowL = new JLabel("Enter number of rows");
        final JLabel colL = new JLabel("Enter number of columns");
        final JLabel player1L = new JLabel("Enter player one");
        final JLabel player2L = new JLabel("Enter player two");

        JLabel l = new JLabel("Play against computer?");

        final Component[] p = new Component[2];
        final Random r = new Random();
        p[0] = player1;
        p[1] = player2;


        JButton b = new JButton("Start Game");
        JButton b2 = new JButton("Cancel");
        b2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        b3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (b3.isSelected()) {
                    cpu = (JTextField) p[r.nextInt(2)];
                    cpu.setVisible(false);
                    cpu.setText("CPU");
                    cpu.setVisible(false);
                    compPlaying = true;
                } else {

                    cpu.setVisible(true);
                    compPlaying = false;
                    cpu.setText(" ");
                }
            }
        });

        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (player1.getText().isEmpty() || player2.getText().isEmpty() || row.getText().isEmpty() || col.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(PathGameFrame.this, "Error - fields cannot be empty !");
                } else {
                    try {
                        try {
                            if (Integer.valueOf(row.getText()) == 0 || Integer.valueOf(col.getText()) == 0) {
                                JOptionPane.showMessageDialog(PathGameFrame.this, "Error - invalid row/column sizes!");
                            } else {
                                board.setDimentions(Integer.valueOf(row.getText()), Integer.valueOf(col.getText()));
                                score = board.getRowCount() * board.getColumnCount();
                                playerOneLabel.setText(player1.getText());
                                playerOne.setName(player1.getText());
                                playerTwoLabel.setText(player2.getText());
                                playerTwo.setName(player2.getText());
                                highScore.loadInPreviousScores("highScores");
                                if (compPlaying) {
                                    if (playerOneLabel.getText().equals("CPU")) {
                                        if (board.isEmpty()) {

                                            board.placePathTile(playerR.getRandomRow(board.getRowCount()), playerR.getRandomCol(board.getColumnCount()));
                                            board.fireTableDataChanged();
                                            score--;

                                        }
                                    }
                                }
                                gameMenu();
                                panel.setVisible(false);
                            }
                        } catch (NegativeArraySizeException exe) {
                            JOptionPane.showMessageDialog(PathGameFrame.this, "Error - invalid row/column sizes!");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(PathGameFrame.this, "Error - invalid row/column sizes!");
                    }
                }
            }
        });
        labelPanel.add(rowL);
        labelPanel.add(colL);
        labelPanel.add(player1L);
        labelPanel.add(player2L);
        labelPanel.add(l);
        labelPanel.setLayout(new GridLayout(5, 1));

        textPanel.add(row);
        textPanel.add(col);
        textPanel.add(player1);
        textPanel.add(player2);
        textPanel.add(b3);
        textPanel.setLayout(new GridLayout(5, 1));

        dataPanel.add(labelPanel);
        dataPanel.add(textPanel);
        dataPanel.setLayout(new GridLayout(1, 2));

        buttonPanel.add(b);
        buttonPanel.add(b2);

        panel.add(dataPanel);
        panel.add(buttonPanel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        //  return panel; 
        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    /**
     * Converts all high scores into strings so they can be displayed on screen
     *
     * If there are more than 10 scores recorded, trim the list down to 10
     *
     * @return a string of arrays that contain the place, player name and high
     * score
     */
    private String[] makeHighScoreEntries() {
        String[] s = new String[10];
        LinkedList l = highScore.getAllHighScores();
        int i = 1;
        if (l.size() <= 10) {
            for (int j = 0; j < l.size(); j++) {
                Player p = (Player) l.get(j);
                s[j] = i + "." + " " + p.getHighScoreEntry();
                i++;
            }
        } else {
            for (int z = 0; z < 10; z++) {
                Player p = (Player) l.get(z);
                s[z] = i + "." + " " + p.getHighScoreEntry();
                i++;
            }
        }
        return s;


    }

    /**
     * Allows for cell coloring. If the value in the cell is odd, then a cell is
     * colored red. If the value in cell is even, it will color it in cyan. This
     * works because player one can only have tiles with odd values as he goes
     * first and player two always get even numbered cells
     */
    public class CellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (board.getValueAt(row, column) != null) {
                if ((int) board.getValueAt(row, column) % 2 != 0) {
                    c.setBackground(Color.red);
                } else {
                    c.setBackground(Color.cyan);
                }
            } else {
                c.setBackground(Color.white);
            }
            return c;
        }
    }
}
