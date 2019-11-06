package square;

import player.SimulatedPlayer;

public class GoSquare extends Square {

    private int salary;

    public GoSquare(int salary) {
        super("Go Square");
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public void performLanding(SimulatedPlayer player) {
        payPlayer(player);
    }

    @Override
    public void performPassing(SimulatedPlayer player) {
        payPlayer(player);
    }

    private void payPlayer(SimulatedPlayer player) {
        int currentBalance = player.getBalance();
        player.setBalance(currentBalance + salary);
    }

}
