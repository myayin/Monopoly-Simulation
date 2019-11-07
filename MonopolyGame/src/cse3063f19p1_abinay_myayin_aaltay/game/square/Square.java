package cse3063f19p1_abinay_myayin_aaltay.game.square;


import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;

public abstract class Square {

    private String name;
//    private String type;

    public Square(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void performLanding(SimulatedPlayer player);

    public void performPassing(SimulatedPlayer player) {}

}
