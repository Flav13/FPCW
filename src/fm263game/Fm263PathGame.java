package fm263game;

/**
 *
 * @author fm263
 */
public class Fm263PathGame {
     /*
      * It initiates the path game and sets its name
      * on the top bar 
      */
    public static void main(String[] args) {
       PathBoardTable table = new PathBoardTable();
       String game = "Path Game";
        PathGameFrame gui = new PathGameFrame(table,game);
    }

}
