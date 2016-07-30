/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fm263game;

import javax.swing.table.AbstractTableModel;

/**
 * Creates the table model of the path game board
 *
 * @author TripleM13
 */
public class PathBoardTable extends AbstractTableModel {

    Object[][] boardArray;
    int increment;
    public int theI;
    public int theJ;
    private int row;
    private int col;

    /**
     *
     */
    public PathBoardTable() {
        increment = 1;
    }

    /**
     * Sets up the size of the board and stores the number of rows and columns
     * so they can be used in the other methods
     *
     * @param row The number of rows the table will have
     * @param col The number of columns the table will have
     */
    public void setDimentions(int row, int col) {
        this.row = row;
        this.col = col;
        boardArray = new Object[row][col];
    }

    /**
     * Returns the number of rows of a table
     *
     * @return The number of rows
     */
    @Override
    public int getRowCount() {
        return row;
    }

    /**
     * Returns the number of columns of a table
     *
     * @return The number of columns
     */
    @Override
    public int getColumnCount() {
        return col;
    }

    /**
     * Returns the value of a cell
     *
     * @param rowIndex the row of the value returned
     * @param columnIndex the column of the value returned
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return boardArray[rowIndex][columnIndex];
    }

    /**
     * Places a value in a cell of the table while making sure it is adjacent to
     * the previously placed tile. It returns null if a move is not valid
     *
     * @param row the row of the tile placed
     * @param column the column of the tile placed
     * @return
     */
    public Object placePathTile(int row, int column) {
        if (boardArray[row][column] == null) {
            if (increment == 1) {

                boardArray[row][column] = increment;
                increment++;
                theI = row;
                theJ = column;

            } else {

                if ((row == theI + 1 && column == theJ) || ((row == theI - 1) && column == theJ) || (column == theJ + 1 && row == theI) || (column == theJ - 1 && row == theI)) {
                    if (boardArray[row][column] == null) {

                        boardArray[row][column] = increment;
                        increment++;
                        theI = row;
                        theJ = column;

                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            }

        } else {
            return null;
        }

        return boardArray[row][column];
    }

    /**
     * Checks if a specific tile has tiles adjacent to it. This is used to
     * determine if the tile placed wins the game
     *
     * @param row the row of the tile placed
     * @param col the column of the tile placed
     * @return true if tile has adjacent tiles, false otherwise
     */
    public boolean adjacent(int row, int col) {
        Object a = boardArray[row][col];
        boolean endGame = false;

        if (col + 1 == getColumnCount()) {
            if (row - 1 == -1) {
                if (getValueAt(row + 1, col) != null && getValueAt(row, col - 1) != null) {
                    endGame = true;
                }
            } else {
                if (row + 1 == getRowCount()) {
                    if (boardArray[row - 1][col] != null && boardArray[row][col - 1] != null) {
                        endGame = true;
                    }
                } else {
                    if (boardArray[row + 1][col] != null && boardArray[row - 1][col] != null && boardArray[row][col - 1] != null) {
                        endGame = true;
                    }
                }
            }
        } else {
            if (col - 1 == -1) {
                if (row - 1 == -1) {
                    if (boardArray[row + 1][col] != null && boardArray[row][col + 1] != null) {
                        endGame = true;
                    }
                } else {
                    if (row + 1 == getRowCount()) {
                        if (boardArray[row][col + 1] != null && boardArray[row - 1][col] != null) {
                            endGame = true;
                        }
                    } else {
                        if (boardArray[row + 1][col] != null && boardArray[row - 1][col] != null && boardArray[row][col + 1] != null) {
                            endGame = true;
                        }
                    }
                }
            } else {
                if (row - 1 == -1 && col > 0 && col < getColumnCount() - 1) {
                    if (boardArray[row][col - 1] != null && boardArray[row][col + 1] != null && boardArray[row + 1][col] != null) {
                        endGame = true;
                    }
                } else {
                    if (row + 1 == getRowCount() && col > 0 && col < getColumnCount() - 1) {
                        if (boardArray[row][col - 1] != null && boardArray[row][col + 1] != null && boardArray[row - 1][col] != null) {
                            endGame = true;
                        }
                    } else {
                        if (boardArray[row][col - 1] != null && boardArray[row + 1][col] != null && boardArray[row][col + 1] != null && boardArray[row - 1][col] != null) {
                            endGame = true;
                        }
                    }
                }
            }
        }

        return endGame;
    }

    /**
     * Returns the current value of the last tile placed
     *
     * @return The current value of the last tile placed
     */
    public int incre() {
        return increment;
    }

    /**
     * Returns the x coordinate of the last tile placed
     *
     * @return the row of the last tile placed
     */
    public int returnI() {
        return theI;
    }

    /**
     * Returns the y coordinate of the last tile placed
     *
     * @return the column of the last tile placed
     */
    public int returnJ() {
        return theJ;
    }

    /**
     * Clears the table of values by looking at each cell in turn and setting
     * them to null
     */
    public void clear() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                boardArray[i][j] = null;
            }
        }
        increment = 1;
    }
    /**
     * Checks if the board is empty by checking every cell. 
     * @return true if board is empty, false otherwise
     */
    public boolean isEmpty() {
        int i = 0;
        boolean empty = true;
        while (i < row && empty) {
            for (int j = 0; j < col; j++) {
                if (boardArray[i][j] != null) {
                    empty = false;
                }
            }
            i++;
        }
        return empty;
    }
}