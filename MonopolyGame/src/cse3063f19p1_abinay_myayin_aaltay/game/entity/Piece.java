package cse3063f19p1_abinay_myayin_aaltay.game.entity;

/**
 * Represents pieces from Monopoly Game.
 * Abstraction for location information of the simulated user.
 * @author anilaltay
 */
public class Piece {

    private int currentLocation;
    private Board board;

    /**
     * Constructs a piece on the given board.
     * @param board
     */
    public Piece(Board board) {
        this.board = board;
        this.currentLocation = 0;
    }

    /**
     * Gets current location of this Piece.
     * @return current location of this Piece
     */
    public int getCurrentLocation() {
        return currentLocation;
    }

    /**
     * Sets current location of this Piece.
     * @param currentLocation location of the piece will be set
     */
    public void setCurrentLocation(int currentLocation) {
        this.currentLocation = currentLocation;
    }

    /**
     * Gets the board which this Piece is on.
     * @return board which piece is on
     */
    public Board getBoard() {
        return board;
    }
}