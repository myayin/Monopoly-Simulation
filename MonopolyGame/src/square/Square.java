package square;

import player.SimulatedPlayer;

public abstract class Square {

    private String name;
//    private String type;

    public Square(String name) {
        this.name = name;
    }

    public abstract void performLanding(SimulatedPlayer player);

    public void performPassing(SimulatedPlayer player) {}

}
