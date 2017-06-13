import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ObjectArrayArguments;

import java.lang.reflect.Array;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        @Nested
        class BoardHasWonTest {
            final Integer FIRST_ROW = 0;
            final Integer SECOND_ROW = 1;
            final Integer THIRD_ROW = 2;
            final Integer FIRST_COLUMN = 0;
            final Integer SECOND_COLUMN = 1;
            final Integer THIRD_COLUMN = 2;

            Board testBoard;

            @BeforeEach
            void setup() {
                testBoard = new Board();
                testBoard.init();
            }

            @Test
            @DisplayName("Chosen cell is occupied")
            void testIfChosenCellIsOccupied() {
                Integer chosenRow = 2;
                Integer chosenCol = 2;
                Integer rowToSetCross = 2;
                Integer colToSetCross = 2;
                setCellInBoardToCross(rowToSetCross, colToSetCross);
                Boolean isCellOccupied = testBoard.isCellOccupied(chosenRow, chosenCol);
                assertTrue(isCellOccupied);
            }

            @Test
            @DisplayName("Chosen cell is not occupied")
            void testIfChosenCellIsNotOccupied() {
                Integer chosenRow = 1;
                Integer chosenCol = 1;
                Integer rowToSetCross = 2;
                Integer colToSetCross = 2;
                setCellInBoardToCross(rowToSetCross, colToSetCross);
                Boolean isCellOccupied = testBoard.isCellOccupied(chosenRow, chosenCol);
                assertFalse(isCellOccupied);
            }

            @Test
            @DisplayName("Chosen cell is not on board")
            void testIfChosenCellIsNotOnBoard() {
                Integer chosenRow = 5;
                Integer chosenCol = -1;
                Boolean isCellOnBoard = testBoard.isOnBoard(chosenRow, chosenCol);
                assertFalse(isCellOnBoard);
            }

            @Test
            @DisplayName("Chosen cell is on board")
            void testIfChosenCellIsOnBoard() {
                Integer chosenRow = 1;
                Integer chosenCol = 1;
                Boolean isCellOnBoard = testBoard.isOnBoard(chosenRow, chosenCol);
                assertTrue(isCellOnBoard);
            }

            private Stream<Arguments> setRowToCheck() {
                Integer firstRow = 0;
                Integer secondRow = 1;
                Integer thirdRow = 2;
                return Stream.of(
                ObjectArrayArguments.create(firstRow),
                ObjectArrayArguments.create(secondRow),
                ObjectArrayArguments.create(thirdRow)
                );
            }

            @ParameterizedTest
            @MethodSource(names = "setRowToCheck")
            @DisplayName("Check hasWon() when user should win with seeds horizontal configuration")
            void testUserWinIfSeedsInRow(Integer row) {
                Integer firstCol = 0;
                Integer secondCol = 1;
                setCellInBoardToCross(row, firstCol);
                setCellInBoardToCross(row, secondCol);
                Integer userChoice = 2;
                Seed seed = Seed.CROSS;
                Boolean hasWon = testBoard.hasWon(seed, row, userChoice);
                assertTrue(hasWon);
            }

            @ParameterizedTest
            @MethodSource(names = "setRowToCheck")
            @DisplayName("Check hasWon() when user should not win with 2 seeds in one row")
            void testUserDoesNotWinIfOnlyTwoSeedsInRow(Integer row) {
                setCellInBoardToCross(row, FIRST_COLUMN);
                Integer userChoice = 2;
                Seed seed = Seed.CROSS;
                Boolean hasWon = testBoard.hasWon(seed, row, userChoice);
                assertFalse(hasWon);
            }

            private Stream<Arguments> setColumnToCheck() {
                return Stream.of(
                ObjectArrayArguments.create(FIRST_COLUMN),
                ObjectArrayArguments.create(SECOND_COLUMN),
                ObjectArrayArguments.create(THIRD_COLUMN)
                );
            }

            @ParameterizedTest
            @MethodSource(names = "setColumnToCheck")
            @DisplayName("Check hasWon() when user should win with seeds Vertical configuration")
            void testUserWinIfSeedsInColumn(Integer col) {
                setCellInBoardToCross(FIRST_ROW, col);
                setCellInBoardToCross(THIRD_ROW, col);
                Integer userChoice = 1;
                Seed seed = Seed.CROSS;
                Boolean hasWon = testBoard.hasWon(seed, userChoice, col);
                assertTrue(hasWon);
            }

            @ParameterizedTest
            @MethodSource(names = "setColumnToCheck")
            @DisplayName("Check hasWon() when user should not win with only 2 seeds in column")
            void testUserDoesNotWinIfOnlyTwoSeedsInColumn(Integer col) {
                Integer firstRow = 0;
                setCellInBoardToCross(firstRow, col);
                Integer userChoice = 1;
                Seed seed = Seed.CROSS;
                Boolean hasWon = testBoard.hasWon(seed, userChoice, col);
                assertFalse(hasWon);
            }

            private Stream<Arguments> setColumnInFirstAndLastRow() {
                Integer firstCol = 0;
                Integer thirdCol = 2;
                return Stream.of(
                ObjectArrayArguments.create(firstCol, thirdCol),
                ObjectArrayArguments.create(thirdCol, firstCol)
                );
            }

            @ParameterizedTest
            @MethodSource(names = "setColumnInFirstAndLastRow")
            @DisplayName("Win if user have seeds by the slant")
            void testWinIfUserHaveSeedsByTheSlant(Integer colInFirstRow, Integer colInThirdRow) {
                setCellInBoardToCross(FIRST_ROW, colInFirstRow);
                setCellInBoardToCross(THIRD_ROW, colInThirdRow);
                Integer crossInCenter = 1;
                Seed seed = Seed.CROSS;
                Boolean hasWon = testBoard.hasWon(seed, crossInCenter, crossInCenter);
                assertTrue(hasWon);
            }

            @Test
            @DisplayName("Check if isDraw() returns false if moves are possible")
            void testIsDrawReturnsFalseIfMovesArePossible() {
                Boolean isDraw = testBoard.isDraw();
                assertFalse(isDraw);
            }

            private void setCellInBoardToCross(Integer row, Integer col) {
                Cell[][] cells = testBoard.getCells();
                Cell oneCell = cells[row][col];
                oneCell.setContent(Seed.CROSS);
            }
        }
    }
}
