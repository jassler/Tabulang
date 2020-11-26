package de.hskempten.tabulang.tokenizer;

import de.hskempten.tabulang.utils.Pair;

/**
 * Line/column-coordinate.
 */
public class TextCoordinate extends Pair<Integer, Integer> {

    public TextCoordinate(Integer line, Integer col) {
        super(line, col);
        if (line == null)
            throw new IllegalArgumentException("Line may not be null.");
        if (col == null)
            throw new IllegalArgumentException("Column may not be null.");
    }

    public int getLine() {
        return getFirst();
    }

    public int getColumn() {
        return getLast();
    }

}
