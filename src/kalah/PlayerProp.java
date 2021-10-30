package kalah;

public class PlayerProp implements IPlayerProp {
    private int currentPlayer;
    private final Pits pitsOne;
    private final Pits pitsTwo;
    private final Player playerOne;
    private final Player playerTwo;
    private final Store storeOne;
    private final Store storeTwo;
    private Store store;
    private Pits pits;
    public boolean noSeeds = false;

    public PlayerProp() {
        pitsOne = new Pits(1);
        pitsTwo = new Pits(2);
        playerOne = new Player(1);
        playerTwo = new Player(2);
        storeOne = new Store(1);
        storeTwo = new Store(2);
        currentPlayer = playerOne.getID();
    }

    public void playerMoves(int playerInput) {
        // Array starts at zero
        int pitPosition = playerInput - 1;
        int excess_seeds, seedsInPit;
        int lastSeedInScore = 0;

        if (playerInput != -1) {
            assignObjects();
            seedsInPit = pits.getSeedsInPos(pitPosition);

            for (int i = pitPosition; i <= seedsInPit + pitPosition; i++) {
                // Passes 6 position so excess seeds go to next player pits
                if (i > pits.getPitsLength() - 1) {
                    excess_seeds = seedsInPit + pitPosition - i + 1;
                    lastSeedInScore = seedsInPit + pitPosition - i + 1;
                    pits.emptyPit(pitPosition);
                    addExcessSeeds(pits, store, excess_seeds);
                    break;
                } else {
                    if (!(seedsInPit > 0 && i == seedsInPit + pitPosition && checkCapture(seedsInPit + pitPosition, pits, store))) {
                        pits.incrementPit(i);
                        pits.emptyPit(pitPosition);
                    }
                }
            }
            // If there are no seeds, noSeeds = true else false
            noSeeds = seedsInPit == 0;
            // Same player turn if last seed in player's score or in pit with zero seeds
            if (lastSeedInScore != 1 && seedsInPit != 0) {
                swapPlayersTurn();
            }

        }
    }

    public void assignObjects() {
        if (currentPlayer == playerOne.getID()) {
            store = storeOne;
            pits = pitsOne;
        } else {
            store = storeTwo;
            pits = pitsTwo;

        }
    }

    public boolean checkCapture(int finalPos, Pits pits, Store store) {
        int OppositePitPos = getOppositePit(finalPos);
        // Only work if pit belongs to player
        if (finalPos < pits.getPitsLength()) {
            // Must be under condition or else error
            int seedsInFinalPos = pits.getSeedsInPos(finalPos);
            // Switch pit to empty and get opposite pit seeds
            pits = switchPits(pits);
            int seedsInOpposite = pits.getSeedsInPos(OppositePitPos);

            if (seedsInFinalPos == 0 && pits.getSeedsInPos(OppositePitPos) > 0) {
                store.increaseStore(seedsInOpposite + 1);
                pits.emptyPit(OppositePitPos);
                return true;
            }
        }
        return false;
    }

    public void addExcessSeeds(Pits pits, Store store, int excessSeeds) {
        int passesPlayerScore = 1;

        while (excessSeeds > 0) {
            if (pits.getID() == currentPlayer) {
                store.increaseStore(1);
                excessSeeds -= 1;
            }

            pits = switchPits(pits);

            for (int i = 0; i < pits.getPitsLength(); i++) {
                // Check for capture
                if (pits.getID() == currentPlayer && excessSeeds == 1 && pits.getSeedsInPos(i) == 0) {
                    pits = switchPits(pits);
                    store.increaseStore(pits.getSeedsInPos(getOppositePit(i)) + passesPlayerScore);
                    pits.emptyPit(getOppositePit(i));
                    excessSeeds -= passesPlayerScore;

                } else if (excessSeeds > 0) {
                    excessSeeds -= passesPlayerScore;
                    pits.incrementPit(i);
                }
            }
        }
    }

    public Pits switchPits(Pits pits) {
        if (pits.getID() == playerOne.getID()) {
            pits = pitsTwo;
        } else {
            pits = pitsOne;
        }
        return pits;
    }

    public void swapPlayersTurn() {
        if (currentPlayer == playerOne.getID()) {
            currentPlayer = playerTwo.getID();
        } else {
            currentPlayer = playerOne.getID();
        }
    }

    public int getOppositePit(int pit) {
        switch (pit) {
            case 0:
                return 5;
            case 1:
                return 4;
            case 2:
                return 3;
            case 3:
                return 2;
            case 4:
                return 1;
            case 5:
                return 0;
            default:
                return -1;
        }
    }

    public boolean checkHouseEmpty() {
        return noSeeds;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public int returnPlayerScore(int playerNum) {
        if (playerNum == playerOne.getID()) {
            return playerOne.getScore();

        } else {
            return playerTwo.getScore();
        }
    }

    public int returnStoreScore(int storeNum) {
        if (storeNum == storeOne.getID()) {
            return storeOne.getScore();

        } else {
            return storeTwo.getScore();
        }
    }

    public int returnSeedsPit(int i, int pitNum) {
        if (pitNum == pitsOne.getID()) {
            return pitsOne.getSeedsInPos(i);

        } else {
            return pitsTwo.getSeedsInPos(i);
        }
    }

    public int[] pitArray(int pitArrNum) {
        Pits tempPits;
        int[] pitArr = new int[pitsOne.getPitsLength()];

        if (pitArrNum == pitsOne.getID()) {
            tempPits = pitsOne;
        } else {
            tempPits = pitsTwo;
        }

        for (int i = 0; i < tempPits.getPitsLength(); i++) {
            pitArr[i] = tempPits.getSeedsInPos(i);
        }
        return pitArr;
    }

    public void SetPlayerScores(int storeNum, int totalSeedsPitNum) {
        if (storeNum == storeOne.getID()) {
            playerOne.setPlayerScore(storeOne.getScore() + totalSeedsPitNum);
        } else {
            playerTwo.setPlayerScore(storeTwo.getScore() + totalSeedsPitNum);
        }
    }
}
