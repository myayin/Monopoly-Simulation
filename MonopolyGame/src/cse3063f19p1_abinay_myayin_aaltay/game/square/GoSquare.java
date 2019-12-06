package cse3063f19p1_abinay_myayin_aaltay.game.square;

import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;

public class GoSquare extends Square {

    private int salary;

    public GoSquare(int salary) {
        super(0,"Go Square");
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public void performLanding(SimulatedPlayer player) {
        paySalary(player);
    }

    public void paySalary(SimulatedPlayer player) {
        int currentBalance = player.getBalance();
        player.setBalance(currentBalance + salary);
    }

}
