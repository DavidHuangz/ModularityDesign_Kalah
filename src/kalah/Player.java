package kalah;

public class Player implements IGetID, IGetScore {
    private final int PlayerID;
    private int score;

    public Player(int player) {
        PlayerID = player;
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public int getID() {
        return PlayerID;
    }

    public void setPlayerScore(int totalScore) {
        score = totalScore;
    }
}
