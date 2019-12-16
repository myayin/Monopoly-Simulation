package cse3063f19p1_abinay_myayin_aaltay.game.square;

import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;

public class TaxSquare extends Square {

    private int payment;

    public TaxSquare(int location, int payment) {
        super(location,"Tax Square");
        this.payment = payment;
    }

    public int getPayment() {
        return payment;
    }

    @Override
    public void performLanding(SimulatedPlayer player) {
        //int currentBalance = player.getBalance();
        //player.setBalance(currentBalance - payment);
        player.pay(payment,player);
        System.out.println(player.getPlayerName()+" paid "+payment+"$ Tax.");
    }

}
