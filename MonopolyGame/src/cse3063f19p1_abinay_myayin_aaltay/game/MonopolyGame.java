package cse3063f19p1_abinay_myayin_aaltay.game;

import cse3063f19p1_abinay_myayin_aaltay.game.builder.BoardBuilder;
import cse3063f19p1_abinay_myayin_aaltay.game.config.MonopolyConfig;
import cse3063f19p1_abinay_myayin_aaltay.game.entity.*;
import cse3063f19p1_abinay_myayin_aaltay.game.square.JailSquare;
import cse3063f19p1_abinay_myayin_aaltay.game.square.Square;

import java.util.*;
import java.util.stream.Stream;

public class MonopolyGame {

    private SimulatedPlayer[] players;
    private int currentPlayerIndex;
    private Board gameBoard;
    private Cup cup;

    private boolean running;

    // TODO: Extract possible speed values into a distinct class: Speed POJO
    private double sleepAfterTurn;

    // TODO: Extract those statistical values into a distinct class: GameStats || PlayerStats
    private int turnCounter;
    private int cycleCounter; // TODO: analyse

    public MonopolyGame(MonopolyConfig config) {

        PrintHelper.printSimulationOpener();

        this.cup = new Cup();
        this.sleepAfterTurn = config.getSleepAfterTurn();

        this.gameBoard = new BoardBuilder(this).withConfig(config).build();

        List<String> playerNames = Arrays.asList(config.getPlayers());
        sortPlayers(playerNames);

        System.out.println("Player order -> " + playerNames);

        PrintHelper.printSeperator();

        this.players = playerNames.stream()
                .map(playerName -> new SimulatedPlayer(playerName, config.getStartingBalance(), gameBoard))
                .toArray(SimulatedPlayer[]::new);
    }

    private void sortPlayers(List<String> playerNames) {
        Map<String, Integer> rolls = new HashMap<>();

        for (String playerName : playerNames) {
            cup.rollDices();
            int diceTotal = cup.getTotal();

            PrintHelper.printRoll(playerName, cup.getDices());

            rolls.put(playerName, diceTotal);
        }

        // Sort players comparing their roll totals
        playerNames.sort((p1, p2) -> {
            int roll1 = rolls.get(p1);
            int roll2 = rolls.get(p2);
            return Integer.compare(roll2, roll1);
        });

        PrintHelper.printSeperator();

        // Resolve roll conflicts
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

    /* ----------------------------- */

    public void start() {
        running = true;

        while (running) gameLoop();
    }

    private void gameLoop() {
        SimulatedPlayer currentPlayer = getCurrentPlayer();

        System.out.printf("Cycle:%d Turn:%d\n", cycleCounter + 1, turnCounter + 1);
        performTurn(currentPlayer);


        sleep(sleepAfterTurn);

        if (players.length - countOfBankrupts() == 1) {
            onGameOver();
            stop();
            return;
        }

        onTurnEnding();
        currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
        System.out.println();

    }

    /* ----------------------------- */

    public void performTurn(SimulatedPlayer player) {

        if (player.isBankrupt()) {
            // TODO: sell mechanism
            System.out.printf("%s is bankrupted. Turn is ended.\n", player);
            return;
        }

        int turnsPerformed = 0;

        do {
            cup.rollDices();
            int diceTotal = cup.getTotal();

            if (gameBoard.getJailSquare().isJailed(player)) {

                boolean pardonedPlayer = performJailedTurn(player);
                if (!pardonedPlayer) return;
                gameBoard.getJailSquare().pardonPlayer(player);
                System.out.println(player.getPlayerName() + " got out of jail!");

            }

            PrintHelper.printRoll(player, cup.getDices());

            Square previousSquare = gameBoard.getSquare(player.getPiece().getCurrentLocation());
            Square nextSquare = gameBoard.getSquare(player.getPiece().getCurrentLocation(), diceTotal);

            if (nextSquare.getLocation() < previousSquare.getLocation())
                gameBoard.getGoSquare().paySalary(player);

            System.out.printf("%s is moving from [%d] %s to [%d] %s.\n",
                    player.getPlayerName(),
                    previousSquare.getLocation(), previousSquare.getName(),
                    nextSquare.getLocation(), nextSquare.getName());

            player.getPiece().setCurrentLocation(nextSquare.getLocation());

            nextSquare.performLanding(player);

            turnsPerformed++;

        } while (cup.dicesEqual() && turnsPerformed < 3);

        if (turnsPerformed >= 3) {
            System.out.println(player.getPlayerName() + " rolled even dices three times. Going to jail!");
            gameBoard.getJailSquare().jailPlayer(player);
        }

    }

    public boolean performJailedTurn(SimulatedPlayer player) {
        final JailSquare jailSquare = gameBoard.getJailSquare();

        if (jailSquare.getJailedDuration(player) < 3) {
            if (jailSquare.getJailPenalty() <= player.getBalance() / 2) { //TODO: extract to player
                player.setBalance(player.getBalance() - jailSquare.getJailPenalty());
                player.pay(jailSquare.getJailPenalty());
                System.out.println(player.getPlayerName() + " paid " + jailSquare.getJailPenalty() + "$ penalty.");
                return true;

            } else if (cup.dicesEqual()) {
                System.out.println(player.getPlayerName() + " rolled double.");
                return true;

            } else {
                jailSquare.increaseJailedDuration(player);
                System.out.println(player.getPlayerName() + " failed to roll double.");
                return false;
            }
        }

        if (jailSquare.getJailPenalty() <= player.getBalance()) {
            player.setBalance(player.getBalance() - jailSquare.getJailPenalty());
            System.out.println(player.getPlayerName() + " paid " + jailSquare.getJailPenalty() + "$ penalty.");
            return true;
        }

        //TODO: extract to player.
        player.pay(jailSquare.getJailPenalty(),null);
        System.out.println(player.getPlayerName() + " sold their properties to get out of the Jail!");

        return false;
    }

    private void onTurnEnding() {
        System.out.println(getCurrentPlayer().getPlayerName() + " has " + getCurrentPlayer().getBalance() + "$");
    }

    private void onCycleEnding() {
        PrintHelper.printSeperator();
        for (SimulatedPlayer player : players) {
            System.out.println(player + " has " + player.getBalance() + "$");
        }
        PrintHelper.printSeperator();
    }

    private void onGameOver() {
        // TODO
        System.out.println("Game over. Winner is: TODO");
    }

    /* ----------------------------- */

    public void sleep(double seconds) {
        try {
            Thread.sleep((long) (seconds * 1000L));
        } catch (InterruptedException ignored) {
        }
    }

    public void stop() {
        running = false;
    }


}
