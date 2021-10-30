package kalah;

public class Store implements IGetID, IGetScore {
    private final int StoreID;
    private int storeNum;

    public Store(int Store) {
        StoreID = Store;
        storeNum = 0;
    }

    public int getScore() {
        return storeNum;
    }

    public int getID() {
        return StoreID;
    }

    public void increaseStore(int storeIncrease) {
        storeNum += storeIncrease;
    }
}
