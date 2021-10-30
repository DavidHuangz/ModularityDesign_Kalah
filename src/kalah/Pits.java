package kalah;

import java.util.Arrays;

public class Pits implements IGetID {
    private final int PitsID;
    private final int[] pits;

    public Pits(int newPits) {
        PitsID = newPits;
        pits = new int[6];
        Arrays.fill(pits, 4);
    }

    public void emptyPit(int pos) {
        pits[pos] = 0;
    }

    public int getSeedsInPos(int pos) {
        return pits[pos];
    }

    public int getID() {
        return PitsID;
    }

    public void incrementPit(int pos) {
        pits[pos] += 1;
    }

    public int getPitsLength() {
        return pits.length;
    }

}