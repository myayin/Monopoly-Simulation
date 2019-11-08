package cse3063f19p1_abinay_myayin_aaltay.game.entity;

public class SimulatedPlayer {

    private int balance;
    private Piece piece;
    private String playerName;

    public SimulatedPlayer(String playerName, int balance) {
        this.playerName = playerName;
        this.balance = balance;
        this.piece = new Piece();
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean isBankrupt() {
        return balance <= 0;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Piece getPiece() {
        return piece;
    }

    @Override
    public String toString() {
        return String.format("%s ($%d%s)",
                playerName, balance,
                isBankrupt() ? "- Bankrupt" : "");
    }

}
