
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
        return chosenCol >= 0 & chosenRow <= 2 & chosenCol >= 0 & chosenCol <= 2;
    }

    public Boolean hasWon(Seed seed, Integer row, Integer column) {
        if (this.isCellOccupied(row, column) || !this.isOnBoard(row, column)) {
            throw new IllegalArgumentException();
        }
        setCell(seed, row, column);
        return isWonHorizontally() & isWonVertically() & isWonByTheSlant();
    }
}
