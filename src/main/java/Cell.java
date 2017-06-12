
public class Cell {
    private Integer row;
    private Integer column;
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

    public Seed getContent() {
        return this.content;
    }

    public void setContent(Seed seed) {
        this.content = seed;
    }

    public void clear() {
        this.content = Seed.EMPTY;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }
}
