package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

public class Kalah {
    private boolean gameRun = true;

    public static void main(String[] args) {
        new Kalah().play(new MockIO());
    }

    public void play(IO io) {
        // Setting up game
        Display display = new Display(io);
        Game game = new Game(display);

        // Run the game
        while (gameRun) {
            gameRun = game.runGame();
        }
    }
}