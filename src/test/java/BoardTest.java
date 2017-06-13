import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    @Test
    @DisplayName("Check if Board can be initialized")
    void testIfBoardCanBeInitialized() {
        Board board = new Board();
        assertEquals(Board.class, board.getClass());
    }

    @Nested
    class BoardInitialized {

        Board testBoard;

        @BeforeEach
        void setUp() {
            testBoard = new Board();
        }

        @Test
        @DisplayName("Check if Board initialized proper size board")
        void testIfBoardInitializedRightBoardSize() {
            testBoard.init();
            Integer expectedRowCount = 3;
            Integer expectedColumnCount = 3;
            Integer testRowCount = Array.getLength(testBoard.getCells());
            Integer testColCount = Array.getLength(testBoard.getCells()[0]);
            assertAll("Check 3x3 board",
                () -> assertEquals(expectedRowCount, testRowCount, "Test row count"),
                () -> assertEquals(expectedColumnCount, testColCount, "Test column count")
            );
        }

        @Test
        @DisplayName("Check if initialized Board has empty cell")
        void testIfBoardCreatesEmptyBoard() {
            testBoard.init();
            Integer testRow = 1;
            Integer testColumn = 2;
            assertEquals(Seed.EMPTY, testBoard.getCells()[testRow][testColumn].getContent());
        }

        @Test
        @DisplayName("Check that the Board correctly refers to cells")
        void testIfBoardRefersToCells() {
            testBoard.init();
            Integer expectedRowIs1 = 0;
            Integer expectedRowIs2 = 1;
            Integer expectedColumnIs3 = 2;
            assertAll("Check 3 cells",
                () -> assertEquals(expectedRowIs2, testBoard.getCells()[1][1].getRow(), "Test for second row"),
                () -> assertEquals(expectedRowIs1, testBoard.getCells()[2][0].getColumn(), "Test for first row"),
                () -> assertEquals(expectedColumnIs3, testBoard.getCells()[2][2].getColumn(), "Test for third column"));
        }
    }
}
