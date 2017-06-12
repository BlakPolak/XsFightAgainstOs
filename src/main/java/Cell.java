
public class Cell {
    private final Integer row;
    private final Integer column;
    private Seed content;

    public Cell(Integer row, Integer column) {
        this.row = row;
        this.column = column;
        this.content = Seed.EMPTY;
    }

    public Integer getRow() {
        return this.row;
    }

    public Integer getColumn() {
        return this.column;
    }
}
