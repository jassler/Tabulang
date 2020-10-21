package de.hskempten.tabulang.tokenizer;

public class LineColPosition {

    private int line;
    private int col;

    public LineColPosition(int line, int col) {
        super();
        this.line = line;
        this.col = col;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }


}
