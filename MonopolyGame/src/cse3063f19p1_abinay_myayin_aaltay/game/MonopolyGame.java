package cse3063f19p1_abinay_myayin_aaltay.game;

import cse3063f19p1_abinay_myayin_aaltay.game.builder.BoardBuilder;
import cse3063f19p1_abinay_myayin_aaltay.game.config.MonopolyConfig;
import cse3063f19p1_abinay_myayin_aaltay.game.entity.Board;
import cse3063f19p1_abinay_myayin_aaltay.game.entity.Dice;
import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;

import java.util.*;
import java.util.stream.Stream;

public class MonopolyGame {

    private SimulatedPlayer[] players;
    private int currentPlayerIndex;
    private Board gameBoard;

    private boolean running;

    // TODO: Extract possible speed values into a distinct class: Speed POJO
    private double sleepAfterTurn;

    // TODO: Extract those statistical values into a distinct class: GameStats || PlayerStats
    private int turnCounter;
    private int cycleCounter; // TODO: analyse

    public MonopolyGame(MonopolyConfig config) {
        PrintHelper.printSimulationOpener();

        this.sleepAfterTurn = config.getSleepAfterTurn();

        this.gameBoard = new BoardBuilder(this).withConfig(config).build();

        List<String> playerNames = Arrays.asList(config.getPlayers());
        sortPlayers(playerNames);

        System.out.println("Player order -> " + playerNames);

        PrintHelper.printSeperator();

        this.players = playerNames.stream()
                .map(playerName -> new SimulatedPlayer(playerName, config.getStartingBalance()))
                .toArray(SimulatedPlayer[]::new);
    }

    private void sortPlayers(List<String> playerNames) {
        Map<String, Integer> rolls = new HashMap<>();

        for (String playerName : playerNames) {
            Dice[] dices = gameBoard.rollDices();
            int diceTotal = Stream.of(dices).mapToInt(Dice::getFacingValue).sum();

            PrintHelper.printRoll(playerName, dices);

            rolls.put(playerName, diceTotal);
        }

        // Sort players comparing their roll totals
        playerNames.sort((p1, p2) -> {
            int roll1 = rolls.get(p1);
            int roll2 = rolls.get(p2);
            return Integer.compare(roll2, roll1);
        });

        PrintHelper.printSeperator();

        // Resolve roll conflictions
        for (int i = 0; i < playerNames.size(); i++) {
            String playerName = playerNames.get(i);

            // Find last index repeating playerName's roll
            int lastIndexRepeating = i; //lastIndexRepeating(playerNames, i, rolls);
            for (int j = i; j < playerNames.size(); j++) {
                int roll1 = rolls.get(playerName);
                int roll2 = rolls.get(playerNames.get(j));

                if (roll1 != roll2)
                    break;

                lastIndexRepeating = j;
            }

            if (i != lastIndexRepeating) {
                List<String> rerollRequired = playerNames.subList(i, lastIndexRepeating + 1);
                sortPlayers(rerollRequired);
                i = lastIndexRepeating;
            }
        }
    }

    public boolean isRunning() {
        return running;
    }

    private int countOfBankrupts() {
        return (int) Stream.of(players)
                .filter(SimulatedPlayer::isBankrupt).count();
    }

    private SimulatedPlayer getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    public void start() {
        running = true;

        while (running) gameLoop();
    }

    private void gameLoop() {
        SimulatedPlayer currentPlayer = getCurrentPlayer();

        System.out.printf("Cycle:%d Turn:%d\n", cycleCounter + 1, turnCounter + 1);
        gameBoard.performTurn(currentPlayer);

        sleep(sleepAfterTurn);

        if (players.length - countOfBankrupts() == 1) {
            onGameOver();
            stop();
            return;
        }

        onTurnIncrement();
    }

    private void onTurnIncrement() {
        System.out.println();
        currentPlayerIndex = (currentPlayerIndex + 1) % players.length;

        turnCounter++;

        if (turnCounter == players.length) {
            turnCounter = 0;
            cycleCounter++;
        }
    }

    private void onGameOver() {
        // TODO
        System.out.println("Game over. Winner is: TODO");
    }

    /* ----------------------------- */

    public void sleep(double seconds) {
        try { Thread.sleep((long) (seconds * 1000L)); } catch (InterruptedException ignored) {}
    }

    public void stop() {
        running = false;
    }

}
