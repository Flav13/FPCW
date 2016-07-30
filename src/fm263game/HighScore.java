/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fm263game;

import java.util.LinkedList;

/**
 *Deals with the player high scores and adds them to a list in the right order. 
 * @author fm263
 */
public class HighScore {

    private LinkedList<Player> hs;
    HighScoreFile f = new HighScoreFile();

    public HighScore() {
        hs = new LinkedList<>();
    }
    /**
     * Loads the String array from the high scores file. Uses regular expressions
     * to extract the player's name and high score and place it in the high scores
     * linked list
     */
    public void loadInPreviousScores(String filename) {
        String[] s = f.load(filename);
        for (int i = 0; i < s.length; i++) {
            if (s[i] != null) {
                String name = s[i];
                String score = s[i];
                Player p = new Player();
                p.setName(name.replaceAll("[^A-Za-z]", ""));
                p.setHighScore(Integer.parseInt(score.replaceAll("[\\D]", "").substring(1)));
                addPlayerScore(p);
            }
        }


    }
    /**
     * Inserts a player in the linked list. Checks if the list is empty, if the 
     * player's score is higher than other players' high score and also checks if 
     * the player has any duplicates in the list. 
     * @param player - The player added to the list
     */
    public void addPlayerScore(Player player) {
        boolean found = false;
        int c = 0;
        int place = 0;
        if (hs.isEmpty()) {
            hs.add(player);
        } else {
            while (!found && c < hs.size()) {
                if (player.getHighScore() > hs.get(c).getHighScore()) {
                    found = true;
                    place = c;
                }
                c++;
            }
            if (found) {
                if (!hasDuplicates(player)) {
                    hs.add(place, player);
                } else {
                    hs.add(place, player);
                    hs.removeLastOccurrence(player);
                }
            } else {
                if (!hasDuplicates(player)) {
                    hs.add(player);
                }
            }
        }

    }
    /**
     * returns the highScores linked list
     * @return the high scores
     */
    public LinkedList getAllHighScores() {
        return hs;
    }

    /**
     * Checks if one player has any duplicates
     * @param p the player that is searched
     * @return true if the player has duplicates, false otherwise
     */
    private boolean hasDuplicates(Player p) {
        int i = 0;
        boolean duplicate = false;
        while (i < hs.size() && !duplicate) {
            if (p.getName().equals(hs.get(i).getName())) {
                duplicate = true;
            }
            i++;
        }
        return duplicate;
    }
}
