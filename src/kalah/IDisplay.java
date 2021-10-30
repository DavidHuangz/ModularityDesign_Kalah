package kalah;

public interface IDisplay {

    void userInput(int playerNow);

    int getUserInput();

    void printGameOver();

    void printWinner(int winner);

    void printScore(int playerOne, int playerTwo);

    void printHouseEmpty();

    void boardPrint(int[] pitOne, int[] pitTwo, int storeOneScore, int storeTwoScore);
}
