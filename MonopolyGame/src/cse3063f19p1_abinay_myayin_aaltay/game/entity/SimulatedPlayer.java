package cse3063f19p1_abinay_myayin_aaltay.game.entity;

import cse3063f19p1_abinay_myayin_aaltay.Main;
import cse3063f19p1_abinay_myayin_aaltay.game.square.LotGroup;
import cse3063f19p1_abinay_myayin_aaltay.game.square.LotSquare;
import cse3063f19p1_abinay_myayin_aaltay.game.square.PropertySquare;
import cse3063f19p1_abinay_myayin_aaltay.game.square.UtilitySquare;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class simulates player of Monopoly Game.
 */
public class SimulatedPlayer {

    private int balance;
    private Piece piece;
    private String playerName;
    private boolean isBankrupt;
    private List<PropertySquare> ownedProperties = new ArrayList<PropertySquare>();

    /**
     * Creates instance of simulated player.
     *
     * @param playerName name of this player
     * @param balance    starting balance of this player
     * @param board      board that player will set their piece on
     */
    public SimulatedPlayer(String playerName, int balance, Board board) {
        this.playerName = playerName;
        this.balance = balance;
        this.piece = new Piece(board);
        this.isBankrupt = false;
    }

    /**
     * Gets the balance.
     *
     * @return balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Sets the balance.
     *
     * @param balance
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Gets total value of properties player owns.
     *
     * @return
     */
    public int getTotalPropertyValue() {
        return ownedProperties.stream().mapToInt(PropertySquare::getSellingPrice).sum();
    }

    /**
     * Checks if the player is bankrupt.
     *
     * @return <code>true</code> if player is bankrupt;
     * <code>false</code> otherwise.
     */
    public boolean isBankrupt() {
        return isBankrupt;
    }

    /**
     * Gets the name of this player.
     *
     * @return name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Gets the piece of this player.
     *
     * @return piece
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Gets the list of owned properties.
     *
     * @return list of owned properties.
     */
    public List<PropertySquare> getOwnedProperties() {
        return ownedProperties;
    }

    /**
     * Buys the passed property.
     *
     * @param propertySquare
     */
    public void buyProperty(PropertySquare propertySquare) {
        this.setBalance(this.getBalance() - propertySquare.getBuyingPrice());
        ownedProperties.add(propertySquare);
    }

    /**
     * Sells the passed property.
     *
     * @param propertySquare
     */
    public void sellProperty(PropertySquare propertySquare) {
        this.setBalance(this.getBalance() + propertySquare.getSellingPrice());
        if (propertySquare instanceof LotSquare) {
            LotGroup lotGroup = ((LotSquare) propertySquare).getLotGroup();
            if (lotGroup.getLevel() != 0 && lotGroup.ownedBy(this))
                lotGroup.sellAll();
        } else {
            ownedProperties.remove(propertySquare);
            propertySquare.onSold();
        }
    }

    /**
     * If player is out of money to pay taxes or rent,
     * they extract the amount they have to pay by selling their properties.
     *
     * @param amountToPay
     * @return paid amount
     */
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

    /**
     * Extracts the amount of money to be paid from player's balance.
     *
     * @param amountToPay
     * @return paid amount
     */
    private int extractFromBalance(int amountToPay) {
        this.setBalance(this.getBalance() - amountToPay);
        return amountToPay;
    }

    /**
     * Pays the passed amount of money to another player from balance if can not
     * extracts the money to be paid from selling properties.
     * @param amountToPay amount of money to be paid
     * @param player player to be paid
     * @return <code>true</code> player pays the amount specified
     *         <code>false</code> otherwise.
     */
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

    /**
     * Player pays the passed amount of money from balance if can not,
     * extracts the money to be paid from selling properties.
     * @param amountToPay
     * @return <code>true</code> player pays the amount specified
     *         <code>false</code> otherwise.
     */
    public boolean pay(int amountToPay) {
        return pay(amountToPay, null);
    }

    /**
     * Checks if player has all utility squares on the board.
     * @return <code>true</code> if player has all of the utility squares on the board.
     *         <code>false</code> otherwise.
     */
    public boolean hasAllUtilities() {
        return ownedProperties.stream().filter(property -> property instanceof UtilitySquare).count()
                == Main.monopolyConfig.getUtilitySquareCount();
    }

    /**
     * Upgrading process of a LotSquare that player owns.
     */
    public void processUpgrading() {
        List<LotSquare> upgradableLots;
        int initialBalance = this.balance;
        int moneySpent = 0;
        do {
            if (moneySpent >= initialBalance * 0.4) break;

            upgradableLots = ownedProperties.stream()
                    .filter(propertySquare -> propertySquare instanceof LotSquare)
                    .map(LotSquare.class::cast)
                    .filter(lotSquare -> lotSquare.canBeUpgraded() && lotSquare.getUpgradePrice() <= balance)
                    .collect(Collectors.toList());

            if (upgradableLots.size() == 0) break;

            LotSquare pickedLot = upgradableLots.get((int) (Math.random() * upgradableLots.size()));

            pickedLot.upgrade();
            moneySpent += pickedLot.getUpgradePrice();

        } while (upgradableLots.size() > 0);
    }

    @Override
    public String toString() {
        return String.format("%s ($%d%s)",
                playerName, balance,
                isBankrupt() ? "- Bankrupt" : "");
    }
}
