package kalah;

public class Game implements IGame {
    private int playerInput;
    private boolean printBoardOnce = true;
    private final Display display;
    private final PlayerProp playerProp;

    public Game(Display display) {
        this.display = display;
        playerProp = new PlayerProp();
    }

    public boolean runGame() {
        if (printBoardOnce) {
            display.boardPrint(playerProp.pitArray(1), playerProp.pitArray(2), playerProp.returnStoreScore(1), playerProp.returnStoreScore(2));
            printBoardOnce = false;
        }

        // player quits
        if (playerInput == -1) {
            display.printGameOver();
            display.boardPrint(playerProp.pitArray(1), playerProp.pitArray(2), playerProp.returnStoreScore(1), playerProp.returnStoreScore(2));
            return false;

            // Game over
        } else if (getData("gameEnded") > 0) {
            display.printGameOver();
            display.boardPrint(playerProp.pitArray(1), playerProp.pitArray(2), playerProp.returnStoreScore(1), playerProp.returnStoreScore(2));
            display.printScore(playerProp.returnPlayerScore(1), playerProp.returnPlayerScore(2));
            display.printWinner(getWinner());
            return false;

            // Get userInput
        } else {
            display.userInput(playerProp.getCurrentPlayer());
            playerInput = display.getUserInput();
            if (playerInput != -1) {
                playerProp.playerMoves(playerInput);
                if (playerProp.checkHouseEmpty()) {
                    display.printHouseEmpty();
                }
                display.boardPrint(playerProp.pitArray(1), playerProp.pitArray(2), playerProp.returnStoreScore(1), playerProp.returnStoreScore(2));
            }
            return true;
        }
    }

    public int getData(String data) {
        int countEmptySeedsOne = 0, countEmptySeedsTwo = 0, totalSeedsPitOne = 0, totalSeedsPitTwo = 0;

        for (int i = 0; i < 6; i++) {
            totalSeedsPitOne += playerProp.returnSeedsPit(i, 1);
            totalSeedsPitTwo += playerProp.returnSeedsPit(i, 2);

            if (playerProp.returnSeedsPit(i, 1) == 0) {
                countEmptySeedsOne++;
            }
            if (playerProp.returnSeedsPit(i, 2) == 0) {
                countEmptySeedsTwo++;
            }
        }
        // Set total players Total score
        playerProp.SetPlayerScores(1, totalSeedsPitOne);
        playerProp.SetPlayerScores(2, totalSeedsPitTwo);
        return gameProgress(data, countEmptySeedsOne, countEmptySeedsTwo, playerProp.returnPlayerScore(1), playerProp.returnPlayerScore(2));

    }


    public int gameProgress(String gameData, int emptyOne, int emptyTwo, int playerOneScore, int playerTwoScore) {
        switch (gameData) {
            case "gameEnded":
                if ((emptyOne == 6 || emptyTwo == 6) && (playerOneScore == playerTwoScore)) {
                    // Tie
                    return 3;
                }
                if (emptyOne == 6 && playerProp.getCurrentPlayer() == 1) {
                    // PlayerOne loses
                    return 1;
                }
                if (emptyTwo == 6 && playerProp.getCurrentPlayer() == 2) {
                    // PlayerTwo loses
                    return 2;
                }
                // game not ended
                return 0;
            case "getWinner":
                if (playerTwoScore > playerOneScore) {
                    // playerTwo Winner
                    return 2;

                } else if (playerOneScore > playerTwoScore) {
                    // playerOne Winner
                    return 1;

                } else {
                    // Tie
                    return 0;
                }

            default:
                return -1;
        }
    }

    public int getWinner() {
        return getData("getWinner");
    }
}
