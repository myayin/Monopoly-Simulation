package entity;

import player.SimulatedPlayer;
import square.Square;

import java.util.List;

public class Board {

    private List<Square> squares;
    private Dice[] dices;

    public void initSquares(List<Square> squares) {
        if (this.squares != null)
            throw new IllegalStateException("Squares are already initialized!");

        this.squares = squares;
    }

    public void initDices(Dice[] dices) {
        if (this.dices != null)
            throw new IllegalStateException("Dices are already initialized!");

        this.dices = dices;
    }

    /* ------------------------------ */

    public int performTurn(SimulatedPlayer player) {
        return -1; // TODO: return moved square count
    }

}
