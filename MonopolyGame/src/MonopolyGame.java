import entity.Board;
import player.SimulatedPlayer;

import java.util.stream.Stream;

public class MonopolyGame {

    private SimulatedPlayer[] players;
    private int currentPlayerIndex;
    private Board gameBoard;

    // TODO: Extract those statistical values to a distinct class: GameStats || PlayerStats
    private int turnCounter;
    private int cycleCounter;

    public MonopolyGame(Arguments arguments) {
        // TODO
    }

    protected void increaseTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
        turnCounter++;
    }

    protected int countOfBankrupts() {
        return (int) Stream.of(players)
                .filter(SimulatedPlayer::isBankrupt).count();
    }

    protected SimulatedPlayer getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    public void start() {
        while (true) {
            SimulatedPlayer currentPlayer = getCurrentPlayer();

            gameBoard.performTurn(currentPlayer);

            if (countOfBankrupts() == 1) {
                onGameOver();
                break;
            }

            increaseTurn();
        }
    }

    public void onGameOver() {
        // TODO
    }

}
