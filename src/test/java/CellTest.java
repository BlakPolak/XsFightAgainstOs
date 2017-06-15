import model.Cell;
import model.Seed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;


class CellTest {

    @Test
    @DisplayName("Check if model.Cell constructor initialized proper row")
    void testIfCellConstructorInitializedProperRow() {
        Integer testRow = 1;
        Integer testColumn = 3;
        Cell cell = new Cell(testRow, testColumn);

        assertEquals(testRow, cell.getRow());
    }

    @Test
    @DisplayName("Check if model.Cell constructor initialized proper column")
    void testIfCellConstructorInitializedProperColumn() {
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
        @DisplayName("Check if cell return seed as content")
        void testIfCellGetSeedAsContent() {
            assertEquals(Seed.class, testCell.getContent().getClass());
        }

        @Test
        @DisplayName("Check if clear() method clear values")
        void testIfClearMethodClearsValues() {
            testCell.setContent(Seed.CROSS);
            testCell.clear();

            assertEquals(Seed.EMPTY, testCell.getContent());
        }
    }
}
