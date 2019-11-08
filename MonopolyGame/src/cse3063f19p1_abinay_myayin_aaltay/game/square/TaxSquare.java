package cse3063f19p1_abinay_myayin_aaltay.game.square;

import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;

public class TaxSquare extends Square {

    private int payment;

    public TaxSquare(int payment) {
        super("Tax Square");
        this.payment = payment;
    }

    public int getPayment() {
        return payment;
    }

    @Override
    public void performLanding(SimulatedPlayer player) {
        int currentBalance = player.getBalance();
        player.setBalance(currentBalance - payment);
    }

}
