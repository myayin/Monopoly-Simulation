package player;

import entity.Piece;

public class SimulatedPlayer {

    private int balance;
    private Piece piece;
    private String playerName;

    public SimulatedPlayer(String playerName, Piece.Color color, int balance) {
        this.playerName = playerName;
        this.balance = balance;
        this.piece = new Piece(color);
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

}
