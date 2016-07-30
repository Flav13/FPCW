/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import fm263game.Fm263MatchGame;
import fm263game.MatchBoardTable;
import fm263game.PathBoardTable;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Flavius
 */
public class MatchBoardTest {
    
    public MatchBoardTest() {
    }
    /**
     * Tests the rules of the match game
     * If player one can place a tile anywhere and if player two can only place it
     * adjacent to player one's last move
     */
   @Test
   public void matchGameRuleTest(){
         MatchBoardTable game = new MatchBoardTable();
         game.setDimentions(8, 8);
         game.placeMatchTile(5, 5); 
         game.placePathTile(5, 6);
         game.placeMatchTile(1, 1);
         assertEquals(3,game.getValueAt(1, 1));
         
   }
   /**
    * Checks if the "isBoardFree" method works
    */
   @Test
   public void isBoardFree(){
       MatchBoardTable game = new MatchBoardTable();
       game.setDimentions(8, 8);
         game.placeMatchTile(5, 5); 
         game.placePathTile(5, 6);
         game.placeMatchTile(1, 1);
         assertTrue(game.isBoardFree());
   }
       
}
