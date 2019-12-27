import cse3063f19p1_abinay_myayin_aaltay.game.builder.BoardBuilder;
import cse3063f19p1_abinay_myayin_aaltay.game.config.LotDefinition;
import cse3063f19p1_abinay_myayin_aaltay.game.config.LotGroupDefinition;
import cse3063f19p1_abinay_myayin_aaltay.game.entity.Board;
import cse3063f19p1_abinay_myayin_aaltay.game.square.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

public class GameSuite {

    @Test
    @DisplayName("should build a Board correctly.")
    public void boardBuilderTest() {
        BoardBuilder boardBuilder = new BoardBuilder();

        // Configure board builder
        boardBuilder.setGoSalary(11111);
        boardBuilder.setTaxPayment(22222);
        boardBuilder.setTaxSquareSize(2);
        boardBuilder.setVehicleSquareSize(3);
        boardBuilder.setUtilitySquareSize(4);
        boardBuilder.setVehicleNames(new String[]{"V1", "V2", "V3"});
        boardBuilder.setUtilityNames(new String[]{"U1", "U2", "U3", "U4"});
        boardBuilder.setLotNames(new String[]{"L1", "L2", "L3", "L4", "L5"});
        boardBuilder.setLotGroups(new LotGroupDefinition[]{
                new LotGroupDefinition("COLOR1",
                        new LotDefinition(1, 6000, 5000, 6000, 6000),
                        new LotDefinition(3, 6000, 5000, 6000, 6000)
                ),
                new LotGroupDefinition("COLOR2",
                        new LotDefinition(6, 10_000, 5000, 10_000, 1000),
                        new LotDefinition(8, 10_000, 5000, 10_000, 1000),
                        new LotDefinition(9, 12_000, 5000, 12_000, 1200)
                )
        });

        // Build board
        Board board = boardBuilder.build();

        // Assert values
        Assertions.assertEquals(board.getGoSquare().getSalary(), 11111);
        Assertions.assertEquals(getFirstTaxSquare(board).getPayment(), 22222);
        Assertions.assertEquals(getSquareCount(board, TaxSquare.class), 2);
        Assertions.assertEquals(getSquareCount(board, VehicleSquare.class), 3);
        Assertions.assertEquals(getSquareCount(board, UtilitySquare.class), 4);
        Assertions.assertTrue(containsAllPropertyNames(board, new String[]{"V1", "V2", "V3"}));
        Assertions.assertTrue(containsAllPropertyNames(board, new String[]{"U1", "U2", "U3", "U4"}));
        Assertions.assertTrue(containsAllPropertyNames(board, new String[]{"L1", "L2", "L3", "L4", "L5"}));
    }

    private TaxSquare getFirstTaxSquare(Board board) {
        for (int i = 0; i < board.getSquareCount(); i++) {
            Square square = board.getSquare(i);
            if (square instanceof TaxSquare)
                return (TaxSquare) square;
        }
        return null;
    }

    private int getSquareCount(Board board, Class<? extends Square> squareType) {
        int occurrence = 0;
        for (int i = 0; i < board.getSquareCount(); i++) {
            Square square = board.getSquare(i);
            if (squareType.isInstance(square))
                occurrence++;
        }
        return occurrence;
    }

    private boolean containsAllPropertyNames(Board board, String[] names) {
        List<String> leftNames = new LinkedList<>(List.of(names));
        for (int i = 0; i < board.getSquareCount(); i++) {
            Square square = board.getSquare(i);
            if (square instanceof PropertySquare)
                leftNames.remove(square.getName());
        }
        return leftNames.size() == 0;
    }

}