/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fm263game;

import java.util.Random;
import javax.swing.JOptionPane;

/**
 * Just like a normal player but it makes random but valid moves
 * @author fm263
 */
public class RandomPlayer extends Player {

    private Random r;
    public int i;
    public int j;
    private int[] possibleRows;
    private int[] possibleCols;
    private int row;
    private int col;
   
    /**
     * Initiates the random number picker and the arrays where the possible
     * moves would be
     */
    public RandomPlayer() {
        super();
        r = new Random();
        possibleRows = new int[3];
        possibleCols = new int[2];

    }
    /**
     * Takes in the previously placed position so it knows the adjacent cells
     * where to make a move
     * @param row the row coordinate of the previously placed cell
     * @param col the cell coordinate of the previously placed cell
     */
    public void putMoves(int row, int col) {
        possibleRows[0] = row;
        possibleRows[1] = row + 1;
        possibleRows[2] = row - 1;

        possibleCols[0] = col + 1;
        possibleCols[1] = col - 1;
        this.col = col;
        this.row = row;


    }

    /**
     * Gets a random row where the move will be made. The row is recorded so the 
     * column selected is in the right position so that the cell placed is adjacent
     * to the previous cell
     * @param max This takes in the maximum value the row can take. It is used 
     * to detect if the row placed is at the edge or in a corner 
     * @return a valid random row 
     */
    public int getRow(int max) {
        i = possibleRows[r.nextInt(3)];
        if (i == -1) {
            return i = possibleRows[r.nextInt(2)];
        } else {
            if (i == max) {
                int pArray[] = {row, row - 1};
                return i = pArray[r.nextInt(2)];
            }
        }
        return i;
    }
   /**
    * Gets a random column where the move will be made. It takes in consideration
    * the row from the previous method so the column and row point to the right
    * adjacent cell
    * @param This takes in the maximum value the column can take. It is used 
     * to detect if the column placed is at the edge or in a corner 
     * @return a valid random column 
    */
    public int getCol(int max) {
        if (i == row) {
            j = possibleCols[r.nextInt(2)];
            if (j == -1) {
                return j = possibleCols[0];
            } else {
                if (j == max) {
                    return j = possibleCols[1];
                }
                return j;
            }

        } else {
            return col;
        }
    }
    /**
     * returns a random value for the row placed
     * not greater than the max table row
     * @param max the max table row
     * @return a random row
     */
    public int getRandomRow(int max){
        return r.nextInt(max);
    }
    /**
     * returns a random value for the column placed
     * not greater than the max table column
     * @param max the max table column
     * @return a random column
     */
    public int getRandomCol(int max){
        return r.nextInt(max);
    }
    

}