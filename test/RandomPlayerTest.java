/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import fm263game.PathBoardTable;
import fm263game.PathGameFrame;
import fm263game.Player;
import fm263game.RandomPlayer;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fm263
 */
public class RandomPlayerTest {

    public RandomPlayerTest() {
    }
    /**
     * Tests that when the random player goes first, a tile is placed. 
     */
    @Test
    public void ai_PlaceFirstTile() {
        PathBoardTable b = new PathBoardTable();
        RandomPlayer p = new RandomPlayer();
        b.setDimentions(8, 8);
        b.placePathTile(p.getRandomRow(b.getRowCount()), p.getRandomCol(b.getColumnCount()));
        assertFalse(b.isEmpty());

    }
    /**
     * Tests if the tile placed is adjacent to the previously tile placed on the 
     * board. This is verified by the "placePathTile" algorithm which returns 
     * null if a move isn't valid. 
     */
    @Test
    public void ai_isTileAdjacent() {
        PathBoardTable b = new PathBoardTable();
        RandomPlayer p = new RandomPlayer();

        b.setDimentions(8, 8);
        b.placePathTile(4, 3);
        p.putMoves(4, 3);
        assertTrue(b.placePathTile(p.getRow(b.getRowCount()), p.getCol(b.getColumnCount())) != null);
    }
     /**
      *Checks if the AI can win by placing the last tile
      */
    @Test
    public void ai_Win() {
        PathBoardTable b = new PathBoardTable();
        RandomPlayer p = new RandomPlayer();

        b.setDimentions(3, 3);
        b.placePathTile(0, 0);
        b.placePathTile(1, 0);
        b.placePathTile(2, 0);
        b.placePathTile(2, 1);
        b.placePathTile(2, 2);
        b.placePathTile(1, 2);
        b.placePathTile(0, 2);
        b.placePathTile(0, 1);
        p.putMoves(0, 1);
        assertTrue(b.adjacent(1, 1));
    }
   
    /**
     * tests if the random player outputs valid row and column values
     */
    @Test
    public void ai_validRowColChoicesTest() {
        PathBoardTable b = new PathBoardTable();
        RandomPlayer p = new RandomPlayer();
        boolean validRow = false;
        boolean validCol = false;
        b.setDimentions(3, 3);
        b.placePathTile(0, 1);
        p.putMoves(0, 1);
        int var = p.getRow(b.getRowCount());
        if (var == 1 || var == 0) {
            validRow = true;
        }

        int var2 = p.getCol(b.getColumnCount());
        if (var2==0 || var2==1 || var2==2) {
            validCol = true;
        }

        assertTrue(validRow);
        assertTrue(validCol);
    }
    
    
}