/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fm263game;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

/**Sets up the user interface for the match game 
 * and inherits certain identical features from 
 * the path game user interface.
 *
 * @author Flavius
 */
public class MatchGameFrame extends PathGameFrame {

    private MatchBoardTable board;
    /**
     * Initialises the match game frame
     * @param board takes in the abstract board table
     * @param game takes in a specific title of the game 
     */
    public MatchGameFrame(MatchBoardTable board,String game) {
        super(board,game);  
        this.board = board;
    }
    /**
     * Initialises the game board. Creates a 
     * new JTable that takes in the board AbsractTableModel.
     * It sets up functionality to the board table (specifically for the
     * match game) and the clear button by adding listeners
     * @return the board 
     */
    @Override
    public Component theBoard() {
        final JTable table;
        table = new JTable(board);
        JPanel panel = new JPanel();
        table.setTableHeader(null);
        JButton b = new JButton("Reset Game");
        b.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                board.clear();
                board.fireTableDataChanged();
                thePlayer = true;
            }
        });
        table.setRowSelectionAllowed(false);
        final CellRenderer myRenderer = new CellRenderer();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JTable target = (JTable) e.getSource();
                table.setCellSelectionEnabled(false);
                sRow = target.getSelectedRow();
                sCol = target.getSelectedColumn();
                try {
                    if (thePlayer) {
                        if (board.placeMatchTile(sRow, sCol) != null) {
                            if (!board.adjacent(sRow, sCol)) {
                                thePlayer = false;
                                table.setDefaultRenderer(Object.class, myRenderer);

                            } else {
                                scoreOne.setText("Score " + Integer.toString(playerOne.setScore(1)));
                                JOptionPane.showMessageDialog(MatchGameFrame.this, playerOneLabel.getText() + " won!");
                                thePlayer = true;
                            }
                        } else {
                            JOptionPane.showMessageDialog(MatchGameFrame.this, "Not a legal move !");
                        }
                    } else {
                        if (board.placePathTile(sRow, sCol) != null) {
                            table.setDefaultRenderer(Object.class, myRenderer);
                            if (!board.isBoardFree()) {
                                scoreTwo.setText("Score " + Integer.toString(playerTwo.setScore(1)));
                                JOptionPane.showMessageDialog(MatchGameFrame.this, playerTwoLabel.getText() + " won!");
                                thePlayer = true;
                            }
                            thePlayer = true;

                        } else {
                            JOptionPane.showMessageDialog(MatchGameFrame.this, "Not a legal move !");
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(PathGameFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        panel.add(table);
        panel.add(b);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;

    }

    

}
