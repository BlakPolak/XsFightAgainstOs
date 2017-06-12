import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    @DisplayName("Check if Cell constructor initialized proper row")
    void testIfCellConstructorInitializeProperlyRow() {
        Integer testRow = 1;
        Integer testColumn = 3;
        Cell cell = new Cell(testRow, testColumn);
        assertEquals(testRow, cell.getRow());
    }

    @Test
    @DisplayName("Check if Cell constructor initialized proper column")
    void testIfCellConstructorInitializeProperlyColumn() {
        Integer testRow = 1;
        Integer testColumn = 3;
        Cell cell = new Cell(testRow, testColumn);
        assertEquals(testColumn, cell.getColumn());
    }

    @DisplayName("When initialized")
    @Nested
    class CellTestInitialized {

        Cell testCell;

        @BeforeEach
        void setUp() {
            Integer testRow = 3;
            Integer testColumn = 2;
            testCell = new Cell(testRow, testColumn);
        }

        @Test
        @DisplayName("Check if cell return Seed as content")
        void testIfCellGetSeedAsContent() {
            assertEquals(Seed.class, testCell.getContent().getClass());
        }

        @Test
        @DisplayName("Check if clear() method clear values")
        void testIfClearClearsObjectFromValues() {
            testCell.setContent(Seed.CROSS);
            testCell.clear();
            assertEquals(Seed.EMPTY, testCell.getContent());

        }

        @Disabled
        @Test
        @DisplayName("Test if Cell can be bigger than 3x3 table - row")
        void testIfCellCanTakeWrongRowSize() {
            Integer testSetRow = 5;
            assertThrows(IllegalArgumentException.class, () -> { testCell.setRow(testSetRow);});

        }

        @Disabled
        @Test
        @DisplayName("Test if Cell can be bigger than 3x3 table - column")
        void testIfCellCanTakeWrongColumnSize() {
            Integer testSetColumn = -1;
            assertThrows(IllegalArgumentException.class, () -> { testCell.setColumn(testSetColumn);});
        }
    }
}
