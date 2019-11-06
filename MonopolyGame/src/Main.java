public class Main {

    /**
     * Parses given arguments and starts Monopoly Simulation.
     * Expected argument format:"-key:value"
     */
    public static void main(String[] args) {
        Arguments arguments = new Arguments(args);
        new MonopolyGame(arguments).start();
    }

}
