/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fm263game;

import java.io.Serializable;
import java.util.Objects;

public class Player implements Serializable {

    public String name;
    public int score;
    private int highScore;

    /*
     * creates a new player with score 0
     */
    public Player() {
        highScore = 0;
        score = 0;
    }

    /**
     * sets a player's name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName(){
        return name; 
    }
   /*
    * sets the player's high score and checks if the amount entered is higher
    * than the previously recorder high score
    */
    public void setHighScore(int sco) {
        if (sco > highScore) {
            highScore = sco;
        }
    }
     /**
     * 
     * returns the player's name with his high score in a string
     * 
     */
    public String getHighScoreEntry(){
        return name +" :" +""+highScore; 
    }
    
    public int getHighScore(){
        return highScore; 
    }

    /**
     * increments a players score
     *
     * @param sco
     * @return the player's score
     */
    public int setScore(int sco) {
        return score = score + sco;
    }
    
    public int getScore(){
        return score; 
    }
    /**
     * Generates the hashcode for a player object
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.name);
        return hash;
    }
    /**
     * Compares two players based on their names
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    
    
}
