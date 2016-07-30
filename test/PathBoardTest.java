/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fm263game.PathBoardTable;
import fm263game.Fm263PathGame;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author TripleM13
 */
public class PathBoardTest {

    public PathBoardTest() {
    }
    /**
     * Checks if the program records the previous cell placed when the player
     * sets a new tile 
     */
    @Test
    public void previusCellPlaced() {
        PathBoardTable game = new PathBoardTable();
        game.setDimentions(8, 8);
        game.placePathTile(5, 4);
        game.placePathTile(6, 4);
        game.placePathTile(7, 4);
        assertEquals(7, game.returnI());
        assertEquals(4, game.returnJ());
    }
    /**
     * Checks if the number displayed in the cells is updated 
     * every time a tile is placed 
     */
    @Test
    public void increTest() {
        PathBoardTable game = new PathBoardTable();
        game.setDimentions(8, 8);
        game.placePathTile(5, 4);
        game.placePathTile(6, 4);
        assertEquals(3, game.incre());
    }
   /**
    * checks if the game can end( last tile placed has adjacent tiles) in the top right corner
    */
    @Test
    public void topRightCornerEndGameTest() {
        PathBoardTable game = new PathBoardTable();
        game.setDimentions(8, 8);

        game.placePathTile(0, 7);
        game.placePathTile(0, 6);
        game.placePathTile(1, 6);
        game.placePathTile(1, 7);
        assertTrue(game.adjacent(0, 7));
    }
    /**
    * checks if the game can end( last tile placed has adjacent tiles) in the bottom right corner
    */
    @Test
    public void bottomRightCornerEndGameTest() {
        PathBoardTable game = new PathBoardTable();
        game.setDimentions(8, 8);
        game.placePathTile(7, 7);
        game.placePathTile(7, 6);
        game.placePathTile(6, 6);
        game.placePathTile(6, 7);
        assertTrue(game.adjacent(7, 7));
    }
     /**
    * checks if the game can end( last tile placed has adjacent tiles) in the bottom left corner
    */
    @Test
    public void bottomLeftCornerEndGameTest() {
        PathBoardTable game = new PathBoardTable();
        game.setDimentions(8, 8);
        game.placePathTile(7, 0);
        game.placePathTile(7, 1);
        game.placePathTile(6, 1);
        game.placePathTile(6, 0);
        assertTrue(game.adjacent(7, 0));

    }
    /**
    * checks if the game can end( last tile placed has adjacent tiles) in the top left corner
    */
    @Test
    public void topLeftCornerEndGameTest() {
        PathBoardTable game = new PathBoardTable();
        game.setDimentions(8, 8);
        game.placePathTile(0, 0);
        game.placePathTile(0, 1);
        game.placePathTile(1, 1);
        game.placePathTile(1, 0);
        assertTrue(game.adjacent(0, 0));
    }
    /**
    * checks if the game can end( last tile placed has adjacent tiles) on the left 
    * side of the board
    */
    @Test
    public void leftSideEndGameTest() {
        PathBoardTable game = new PathBoardTable();
        game.setDimentions(8, 8);
        game.placePathTile(2, 0);
        game.placePathTile(2, 1);
        game.placePathTile(3, 1);
        game.placePathTile(3, 0);
        game.placePathTile(4, 0);
        assertTrue(game.adjacent(3, 0));
    }
    /**
    * checks if the game can end( last tile placed has adjacent tiles) on the right 
    * side of the board
    */
    @Test
    public void rightSideEndGameTest() {
        PathBoardTable game = new PathBoardTable();
        game.setDimentions(8, 8);
        game.placePathTile(2, 7);
        game.placePathTile(2, 6);
        game.placePathTile(3, 6);
        game.placePathTile(3, 7);
        game.placePathTile(4, 7);
        assertTrue(game.adjacent(3, 7));
    }
    /**
    * checks if the game can end( last tile placed has adjacent tiles) on the bottom 
    * of the board
    */
    @Test
    public void bottomEndGameTest() {
        PathBoardTable game = new PathBoardTable();
        game.setDimentions(8, 8);
        game.placePathTile(7, 3);
        game.placePathTile(6, 3);
        game.placePathTile(6, 4);
        game.placePathTile(7, 4);
        game.placePathTile(7, 5);
        assertTrue(game.adjacent(7, 4));
    }
    /**
    * checks if the game can end( last tile placed has adjacent tiles) on the top side
    * of the board
    */
    @Test
    public void topEndGameTest() {
        PathBoardTable game = new PathBoardTable();
        game.setDimentions(8, 8);
        game.placePathTile(7, 3);
        game.placePathTile(6, 3);
        game.placePathTile(6, 4);
        game.placePathTile(7, 4);
        game.placePathTile(7, 5);
        assertTrue(game.adjacent(7, 4));
    }
    /**
    * checks if the game can end( last tile placed has adjacent tiles) in the middle
    * of the board
    */
    @Test
    public void middleEndGameTest() {
        PathBoardTable game = new PathBoardTable();
        game.setDimentions(8, 8);
        game.placePathTile(4, 4);
        game.placePathTile(4, 3);
        game.placePathTile(3, 3);
        game.placePathTile(3, 4);
        game.placePathTile(3, 5);
        game.placePathTile(4, 5);
        game.placePathTile(4, 5);
        game.placePathTile(5, 5);
        game.placePathTile(5, 4);
        assertTrue(game.adjacent(4, 4));
    }
    /**
    * checks if the program respects the rules of the path game
    * This means that tiles can only be placed adjacent to the 
    * previously placed tile
    */
    @Test
    public void pathGameRuleTest() {
        PathBoardTable game = new PathBoardTable();
        game.setDimentions(8, 8);
        game.placePathTile(7, 0);
        game.placePathTile(7, 1);
        game.placePathTile(6, 1);
        game.placePathTile(6, 0);
        assertEquals(4, game.getValueAt(6, 0));
    }
    /**
    * checks if a custom size can be set for the board
    */
    @Test
    public void dimensionTest(){
        PathBoardTable game = new PathBoardTable();
        game.setDimentions(8, 8);
        assertEquals(8,game.getRowCount());
        assertEquals(8,game.getColumnCount());
    }
    /**
    * checks if the board clears all cells in the table
    */
    @Test
    public void clearTest(){
        PathBoardTable game = new PathBoardTable();
        game.setDimentions(8,8);
        game.placePathTile(4,4);
        game.placePathTile(4,5);
        game.clear();
        assertEquals(null,game.getValueAt(4,4));
        assertEquals(null,game.getValueAt(4,5));
        
        
    }
     
}
