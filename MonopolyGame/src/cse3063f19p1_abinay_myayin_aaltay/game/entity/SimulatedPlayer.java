package cse3063f19p1_abinay_myayin_aaltay.game.entity;

import cse3063f19p1_abinay_myayin_aaltay.game.square.LotSquare;

import java.util.ArrayList;
import java.util.List;

public class SimulatedPlayer {

    private int balance;
    private Piece piece;
    private String playerName;
    private List<LotSquare> lotSquare = new ArrayList<LotSquare>();
    private int[] numOfProperties = new int[6];

    public List<LotSquare> getLotSquare() {
        return lotSquare;
    }

    public void setLotSquare(List<LotSquare> lotSquare) {
        this.lotSquare = lotSquare;
    }


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
