package cse3063f19p1_abinay_myayin_aaltay.game.entity;

public class Piece {

    private int currentLocation;
    private Board board;

    public Piece(Board board) {
        this.board = board;
        this.currentLocation = 0;
    }

    public int getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(int currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Board getBoard() {
        return board;
    }
}