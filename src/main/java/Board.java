
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
        return isWonHorizontally(seed) || isWonVertically(seed) || isWonByTheSlant(seed);
    }

    public Boolean isDraw() {
        for (Cell[] row : this.cells) {
            for (Cell cellInRow : row) {
                if (cellInRow.getContent() == Seed.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private void setCell(Seed seed, Integer row, Integer column) {
        this.cells[row][column].setContent(seed);
    }

    private Boolean isWonHorizontally(Seed seed) {
        for (Cell[] row: this.cells) {
            if ((row[0].getContent() == seed)
                    && (row[1].getContent() == seed)
                    && (row[2].getContent() == seed)) {
                return true;
            }
        }
        return false;
    }

    private Boolean isWonVertically(Seed seed) {
        for (int column = 0; column < 3; column++) {
            if ((this.cells[0][column].getContent() == seed)
                    && (this.cells[1][column].getContent() == seed)
                    && (this.cells[2][column].getContent() == seed)) {
                return true;
            }
        }
        return false;
    }

    private Boolean isWonByTheSlant(Seed seed) {
        return isTheSameInRightSlant(seed) || isTheSameInLeftSlant(seed);
    }

    private Boolean isTheSameInRightSlant(Seed seed) {
        Integer column = 2;
        for (Cell[] row : this.cells) {
            if (row[column].getContent() != seed) {return false;}
            column--;
        }
        return true;
    }

    private Boolean isTheSameInLeftSlant(Seed seed) {
        Integer column = 0;
        for (Cell[] row : this.cells) {
            if (row[column].getContent() != seed) {return false;}
            column++;
        }
        return true;
    }
}
