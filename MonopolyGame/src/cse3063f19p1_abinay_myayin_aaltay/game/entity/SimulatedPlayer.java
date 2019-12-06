package cse3063f19p1_abinay_myayin_aaltay.game.entity;

import cse3063f19p1_abinay_myayin_aaltay.game.square.LotSquare;

import java.util.ArrayList;
import java.util.List;

public class SimulatedPlayer {

    private int balance;
    private Piece piece;
    private String playerName;
    public List<LotSquare> lotSquare = new ArrayList<LotSquare>(); //TODO: make private after extraction.

    public int[] getNumOfProperties() {
        return numOfProperties;
    }

    private int[] numOfProperties = new int[8];

    public List<LotSquare> getLotSquare() {
        return lotSquare;
    }

    public SimulatedPlayer(String playerName, int balance, Board board) {
        this.playerName = playerName;
        this.balance = balance;
        this.piece = new Piece(board);
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

    public void addProperty(LotSquare lotSquare) {
        this.lotSquare.add(lotSquare);
    }

    @Override
    public String toString() {
        return String.format("%s ($%d%s)",
                playerName, balance,
                isBankrupt() ? "- Bankrupt" : "");
    }


}
