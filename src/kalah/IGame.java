package kalah;

public interface IGame {
    boolean runGame();

    int getData(String data);

    int gameProgress(String gameData, int emptyOne, int emptyTwo, int playerOneScore, int playerTwoScore);

    int getWinner();
}
