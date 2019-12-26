package cse3063f19p1_abinay_myayin_aaltay.game.square;

import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;

/**
 * Represents the Go Square from Monopoly Game.
 * Players start from this square and get paid everytime they land on this square or passing by.
 */
public class GoSquare extends Square {

    private int salary;

    /**
     * Constructs GoSquare object.
     * @param salary money to paid passing and landing players.
     */
    public GoSquare(int salary) {
        super(0,"Go Square");
        this.salary = salary;
    }

    /**
     * Gets salary paid to the passing and landing players.
     * @return
     */
    public int getSalary() {
        return salary;
    }

    /**
     * Performs pay salary routine to landing players.
     * @param player player to be paid
     */
    @Override
    public void performLanding(SimulatedPlayer player) {
        paySalary(player);
    }

    /**
     * Pays salary to players.
     * @param player player to be paid
     */
    public void paySalary(SimulatedPlayer player) {
        int currentBalance = player.getBalance();
        player.setBalance(currentBalance + salary);
    }

}
