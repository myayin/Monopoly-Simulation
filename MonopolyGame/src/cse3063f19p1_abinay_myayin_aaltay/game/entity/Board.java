package cse3063f19p1_abinay_myayin_aaltay.game.entity;

import cse3063f19p1_abinay_myayin_aaltay.game.MonopolyGame;
import cse3063f19p1_abinay_myayin_aaltay.game.square.GoSquare;
import cse3063f19p1_abinay_myayin_aaltay.game.square.JailSquare;
import cse3063f19p1_abinay_myayin_aaltay.game.square.Square;

import java.util.List;

/**
 * Represents the board from Monopoly Game.
 * Holds squares themselves and functionality of squares.
 * @author Anıl Altay, Ayten Binay, Merve Yayın
 */
public class Board {


    private List<Square> squares;

    /**
     * Sets squares list.
     * @param squares square list to be set as squares of Board
     */
    public void initSquares(List<Square> squares) {
        if (this.squares != null)
            throw new IllegalStateException("Squares are already initialized!");

        this.squares = squares;
    }

    /**
     * Gets the square according to the current location and total value of thrown dices.
     * @param location current location of the simulated player
     * @param diceTotal total of facing values of thrown dices
     * @return next square player will land on
     */
    public Square getSquare(int location, int diceTotal) {
        return squares.get((location + diceTotal) % squares.size());
    }

    /**
     * Gets the square at the given location.
     * @param location location index of the square
     * @return square at the location.
     */
    public Square getSquare(int location) {
        if (location < 0 || location >= squares.size())
            throw new IllegalArgumentException("Invalid location argument. Location argument must between 0 and " + squares.size());
        return squares.get(location);
    }

    /**
     * Gets the Go Square from squares.
     * @return Go Square of board
     */
    public GoSquare getGoSquare() {
        Square square = squares.get(0);
        if (!(square instanceof GoSquare))
            throw new InternalError("First location must be GoSquare. Found " + square.getClass().getSimpleName() + " instead.");
        return (GoSquare) square;
    }

    /**
     * Gets the Jail Square from squares.
     * @return Jail Square of board
     */
    public JailSquare getJailSquare(){
        Square square = squares.get(JailSquare.LOCATION);
        if (!(square instanceof JailSquare))
            throw new InternalError("Location #"+JailSquare.LOCATION+" must be JailSquare. Found " + square.getClass().getSimpleName() + " instead.");
        return (JailSquare) square;
    }

}
