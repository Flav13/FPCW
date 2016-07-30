/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import fm263game.HighScore;
import fm263game.HighScoreFile;
import fm263game.Player;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fm263
 */
public class HighScoreTest {

    /**
     * checks if the file is saved correctly
     */
    @Test
    public void test_highScoreFileSave() {
        HighScoreFile f = new HighScoreFile();
        String[] scores = {"Test"};
        assertTrue(f.save(scores, "testFile"));
    }

    /**
     * checks if the file saved is loaded correctly
     */
    @Test
    public void test_highScoreFileLoad() {
        HighScoreFile f = new HighScoreFile();
        String[] sco = {"Test"};
        f.save(sco, "testFile2");

        String[] scores = f.load("testFile2");
        assertEquals("Test", scores[0]);

    }

    /**
     * This checks if the program can load in previously saved scores and if
     * they are read correctly by comparing the high score. This tests if the
     * regular expressions work correctly.
     */
    @Test
    public void test_loadPreviousHighScores() {
        HighScoreFile f = new HighScoreFile();
        HighScore h = new HighScore();

        String[] scores = {"1.Test:51"};
        f.save(scores, "testFile");

        h.loadInPreviousScores("testFile");
        Player p = (Player) h.getAllHighScores().get(0);

        assertEquals(51, p.getHighScore());
        assertEquals("Test", p.getName());
    }

    @Test
    public void test_addingFirstPlayerScore() {
        Player p = new Player();
        p.setHighScore(56);
        p.setName("Test");
        HighScore h = new HighScore();
        h.addPlayerScore(p);
        assertEquals(p, h.getAllHighScores().get(0));
    }
    /**
     * This test verifies that the player with the highest score is added at the
     * top and that the player with the previous high score is shifted down
     */
    @Test
    public void test_addingPlayerWithHigherScore() {
        Player p = new Player();
        p.setHighScore(56);
        p.setName("Test");

        Player p2 = new Player();
        p2.setHighScore(100);
        p2.setName("Test2");

        HighScore h = new HighScore();
        h.addPlayerScore(p);
        h.addPlayerScore(p2);

        assertEquals(p2, h.getAllHighScores().get(0));
        assertEquals(p, h.getAllHighScores().get(1));
    }

    /**
     * Tests when a the same player with a higher score is added. It checks if
     * the score is replaced.
     */
    @Test
    public void test_addingSamePlayerWithHigherScore() {
        HighScore h = new HighScore();
        Player p = new Player();
      
        p.setHighScore(56);
        p.setName("Test");
        h.addPlayerScore(p);
       
        p.setHighScore(100);
        h.addPlayerScore(p);

        Player pTest = (Player) h.getAllHighScores().get(0);

        assertEquals(100, pTest.getHighScore());
    }

    /**
     * This tests if the private hasDuplicates method works. It works by
     * checking if the player added using the "addPlayerScore" method has any
     * duplicates. If it does, and the highScore is lower or equal, it isn't
     * added to the high scores list
     */
    @Test
    public void test_duplicateEntry() {
        Player p = new Player();
        HighScore h = new HighScore();

        p.setName("Test");
        p.setHighScore(56);
        h.addPlayerScore(p);

        p.setHighScore(55);
        h.addPlayerScore(p);
        assertEquals(1, h.getAllHighScores().size());
    }
}
