package square;

import player.SimulatedPlayer;

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
