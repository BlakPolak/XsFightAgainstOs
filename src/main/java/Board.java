
public class Board {
    private Cell[][] cells;
    private boolean draw;

    public void init() {
        cells = new Cell[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                cells[row][col] = new Cell(row, col);
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public Boolean isCellOccupied(Integer chosenRow, Integer chosenCol) {
        Cell cell = this.cells[chosenRow][chosenCol];
        Seed cellContent = cell.getContent();
        return cellContent != Seed.EMPTY;
    }

    public Boolean isOnBoard(Integer chosenRow, Integer chosenCol) {
        return chosenCol >= 0 & chosenRow <= 3 & chosenCol >= 0 & chosenCol <= 3;
    }

    public Boolean hasWon(Seed seed, Integer row, Integer userChoice) {
        return null;
    }

    public Boolean isDraw() {
        return draw;
    }
}
