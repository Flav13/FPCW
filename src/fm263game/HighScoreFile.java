/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fm263game;

import java.io.*;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *This class deals with saving and loading the high Scores file 
 * @author fm263
 */
public class HighScoreFile {

 

    /**
     * Takes in an array of strings and saves to a file of a given name
     *
     * Returns a boolean 
     * @param scores - The scores that will be saved
     * @filename -the name of the file saved
     * @return true if the file was successfully saved, false otherwise
     */
    public boolean save(String[] scores,String filename) {
        boolean saved = false;
        File f = new File(filename);
        ObjectOutputStream os;
        try {
            os = new ObjectOutputStream(
                    new FileOutputStream(f));
            os.writeObject(scores);
            saved = true;
            os.close();
        } catch (IOException ex) {
            Logger.getLogger(HighScoreFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return saved;
    }

    /**
     * Loads in the array of strings from the file
     * @param filename - the name of the file to load
     * @return the array of strings
     */
    public String[] load(String filename) {
        String[] s = new String[10];
        File f = new File(filename);
        ObjectInputStream is;
        try {
            is = new ObjectInputStream(new FileInputStream(f));
            s = (String[]) is.readObject();
            is.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HighScoreFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            String[] st = new String[10];
            save(st,filename);
        } catch (ClassCastException ex) {
            Logger.getLogger(HighScoreFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
}
