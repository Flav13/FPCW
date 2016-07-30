/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fm263game;

/**
 *Sets up the board abstract table for the method game and inherits the 
 * identical methods from the path board table.
 * @author Flavius
 */
public class MatchBoardTable extends PathBoardTable {

    
    /**
     * Allows the user to place a tile anywhere on the board provided the cell selected is empty
     * @param row the x parameter of the cell
     * @param col the y parameter of the cell
     * @return if the cell is empty, the value of the tile(move number) is returned, null if cell isn't empty
     */
    public Object placeMatchTile(int row, int col) {
        if (boardArray[row][col] == null) {
            boardArray[row][col] = increment;
            theI=row;
            theJ=col;
            increment++;
        }
        return boardArray[row][col];
    }
    /**
     * checks whether the board is full
     * used to determine whether player 2 won the game
     * @return true if table has free cells, false if it's full
     */
    public boolean isBoardFree() {
        boolean notFull = false;
        int i = 0;
        while (!notFull && i < getRowCount()) {
            for (int j = 0; j < getColumnCount(); j++) {
                if (getValueAt(i, j) == null) {
                    notFull = true;
                }
            }
            i++;
        }
        return notFull;
    }

}
