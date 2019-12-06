package cse3063f19p1_abinay_myayin_aaltay.game.entity;

import cse3063f19p1_abinay_myayin_aaltay.Main;
import cse3063f19p1_abinay_myayin_aaltay.game.config.MonopolyConfig;
import cse3063f19p1_abinay_myayin_aaltay.game.square.LotSquare;
import cse3063f19p1_abinay_myayin_aaltay.game.square.PropertySquare;
import cse3063f19p1_abinay_myayin_aaltay.game.square.UtilitySquare;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SimulatedPlayer {

    private int balance;
    private Piece piece;
    private String playerName;
    private boolean isBankrupt;
    private List<PropertySquare> ownedProperties = new ArrayList<PropertySquare>();

    public SimulatedPlayer(String playerName, int balance, Board board) {
        this.playerName = playerName;
        this.balance = balance;
        this.piece = new Piece(board);
        this.isBankrupt = false;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getTotalPropertyValue() {
        return ownedProperties.stream().mapToInt(PropertySquare::getSellingPrice).sum();
    }

    public boolean isBankrupt() {
        return isBankrupt;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Piece getPiece() {
        return piece;
    }

    public List<PropertySquare> getOwnedProperties() {
        return ownedProperties;
    }

    public void buyProperty(PropertySquare propertySquare) {
        if (this.getBalance() > propertySquare.getBuyingPrice()) {
            this.setBalance(this.getBalance() - propertySquare.getBuyingPrice());
            ownedProperties.add(propertySquare);
        } else throw new IllegalArgumentException(""); //TODO: decide exception string.
    }

    public void sellProperty(PropertySquare propertySquare) {
        this.setBalance(this.getBalance() + propertySquare.getSellingPrice());
        ownedProperties.remove(propertySquare);
    }

    private int extractFromProperties(int amountToPay) {
        int total = 0;
        List<PropertySquare> propertiesToSell = new ArrayList<>();

        this.getOwnedProperties().sort((property1, property2) -> -Integer.compare(property1.getSellingPrice(), property2.getSellingPrice()));

        for (PropertySquare propertySquare : this.getOwnedProperties()) {
            total += propertySquare.getSellingPrice();
            propertiesToSell.add(propertySquare);
            if (this.getBalance() + total >= amountToPay) break;
        }

        propertiesToSell.forEach(this::sellProperty);
        this.setBalance(this.getBalance() - amountToPay);
        return amountToPay;
    }

    private int extractFromBalance(int amountToPay) {
        this.setBalance(this.getBalance() - amountToPay);
        return amountToPay;
    }

    public boolean pay(int amountToPay, SimulatedPlayer player) {
        int paidAmount = 0;

        if (this.getBalance() >= amountToPay) {
            paidAmount = extractFromBalance(amountToPay);
        } else if (this.getBalance() + getTotalPropertyValue() >= amountToPay) {
            paidAmount = extractFromProperties(amountToPay);
        }

        if (paidAmount > 0) {
            if (player != null) player.setBalance(player.getBalance() + paidAmount);
            return true;
        }

        this.isBankrupt = true;
        System.out.println(this.getPlayerName() + " is bankrupted!");
        return false;
    }

    public boolean pay(int amountToPay) {
        return pay(amountToPay, null);
    }

    public boolean hasAllUtilities() {
        return ownedProperties.stream().filter(property -> property instanceof UtilitySquare).count()
                == Main.monopolyConfig.getUtilitySquareCount();
    }


    @Override
    public String toString() {
        return String.format("%s ($%d%s)",
                playerName, balance,
                isBankrupt() ? "- Bankrupt" : "");
    }


}
