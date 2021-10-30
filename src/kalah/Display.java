package kalah;

import com.qualitascorpus.testsupport.IO;

public class Display implements IDisplay {
    private final IO io;
    private int UserPrompt;

    public Display(IO BoardSetup) {
        io = BoardSetup;
    }

    public void userInput(int playerNow) {
        String prompt = String.format("Player P%d's turn - Specify house number or 'q' to quit: ", playerNow);
        UserPrompt = io.readInteger(prompt, 1, 6, -1, "q");
    }

    public int getUserInput() {
        return UserPrompt;
    }

    public void printGameOver() {
        io.println("Game over");
    }

    public void printWinner(int winner) {
        if (winner == 1) {
            io.println("Player 1 wins!");
        } else if (winner == 2) {
            io.println("Player 2 wins!");
        } else if (winner == 0) {
            io.println("A tie!");
        }
    }

    public void printScore(int playerOne, int playerTwo) {
        io.println(String.format("\tplayer 1:%d", playerOne));
        io.println(String.format("\tplayer 2:%d", playerTwo));
    }

    public void printHouseEmpty() {
        io.println("House is empty. Move again.");
    }

    public void boardPrint(int[] pitOne, int[] pitTwo, int storeOneScore, int storeTwoScore) {
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
        io.println(String.format("| P2 | 6[%2d] | 5[%2d] | 4[%2d] | 3[%2d] | 2[%2d] | 1[%2d] | %2d |", pitTwo[5], pitTwo[4], pitTwo[3], pitTwo[2], pitTwo[1], pitTwo[0], storeOneScore));
        io.println("|    |-------+-------+-------+-------+-------+-------|    |");
        io.println(String.format("| %2d | 1[%2d] | 2[%2d] | 3[%2d] | 4[%2d] | 5[%2d] | 6[%2d] | P1 |", storeTwoScore, pitOne[0], pitOne[1], pitOne[2], pitOne[3], pitOne[4], pitOne[5]));
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
    }
}
