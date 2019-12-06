package cse3063f19p1_abinay_myayin_aaltay.game.entity;

import cse3063f19p1_abinay_myayin_aaltay.game.MonopolyGame;
import cse3063f19p1_abinay_myayin_aaltay.game.square.GoSquare;
import cse3063f19p1_abinay_myayin_aaltay.game.square.JailSquare;
import cse3063f19p1_abinay_myayin_aaltay.game.square.Square;

import java.util.List;

public class Board {

    private MonopolyGame parentGame;
    private List<Square> squares;

    public void initSquares(List<Square> squares) {
        if (this.squares != null)
            throw new IllegalStateException("Squares are already initialized!");

        this.squares = squares;
    }

    public Square getSquare(int location, int diceTotal) {
        return squares.get((location + diceTotal) % squares.size());
    }

    public Square getSquare(int location) {
        if (location < 0 || location >= squares.size())
            throw new IllegalArgumentException("Invalid location argument. Location argument must between 0 and " + squares.size());
        return squares.get(location);
    }

    public GoSquare getGoSquare() {
        Square square = squares.get(0);
        if (!(square instanceof GoSquare))
            throw new InternalError("First location must be GoSquare. Found " + square.getClass().getSimpleName() + " instead.");
        return (GoSquare) square;
    }

    public JailSquare getJailSquare(){
        Square square = squares.get(JailSquare.LOCATION);
        if (!(square instanceof JailSquare))
            throw new InternalError("Location #"+JailSquare.LOCATION+" must be JailSquare. Found " + square.getClass().getSimpleName() + " instead.");
        return (JailSquare) square;
    }

}
