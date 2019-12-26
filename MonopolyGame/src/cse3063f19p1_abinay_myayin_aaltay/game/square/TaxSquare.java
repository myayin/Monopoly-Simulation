package cse3063f19p1_abinay_myayin_aaltay.game.square;

import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;

/**
 * Represents the TaxSquare from Monopoly Game.
 * Players pay tax when they land on this square.
 */
public class TaxSquare extends Square {

    private int payment;

    /**
     * Constructs TaxSquare
     * @param location location TaxSquare will be set
     * @param payment tax to be paid by landing players
     */
    public TaxSquare(int location, int payment) {
        super(location,"Tax Square");
        this.payment = payment;
    }

    /**
     * Gets amount of tax
     * @return tax payment amount
     */
    public int getPayment() {
        return payment;
    }

    /**
     * Performs tax payment routine for landing player.
     * @param player
     */
    @Override
    public void performLanding(SimulatedPlayer player) {
        //int currentBalance = player.getBalance();
        //player.setBalance(currentBalance - payment);
        player.pay(payment,player);
        System.out.println(player.getPlayerName()+" paid "+payment+"$ Tax.");
    }

}
