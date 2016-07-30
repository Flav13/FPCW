/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fm263game;

/**
 *
 * @author Flavius
 */
public class Fm263MatchGame {
    /**
     * It initiates the match game and sets its name
      * on the top bar 
     * @param args 
     */
    public static void main(String[] args) {
       MatchBoardTable table = new MatchBoardTable();
       String game = "Match Game";
        MatchGameFrame gui = new MatchGameFrame(table,game);
    }
}
