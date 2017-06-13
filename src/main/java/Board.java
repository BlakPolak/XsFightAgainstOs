
public class Board {
    private Cell[][] cells;

    public Board(){
        init();
    }

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

    public void setCell(Seed player, Integer row, Integer column) {
        cells[row][column].setContent(player);
    }
}
