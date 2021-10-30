package kalah;

public interface IPlayerProp {
    void playerMoves(int playerInput);

    void assignObjects();

    boolean checkCapture(int finalPos, Pits pits, Store store);

    void addExcessSeeds(Pits pits, Store store, int excessSeeds);

    Pits switchPits(Pits pits);

    void swapPlayersTurn();

    int getOppositePit(int pit);

    boolean checkHouseEmpty();

    int getCurrentPlayer();


    int returnPlayerScore(int playerNum);

    int returnStoreScore(int storeNum);

    int[] pitArray(int pitArrNum);

    void SetPlayerScores(int storeNum, int totalSeedsPitNum);

    int returnSeedsPit(int i, int pitNum);
}
