package de.hskempten.tabulang.datatypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Table {

    private ArrayList<Tuple> tuples;
    private boolean transposed = false;

    private String[] colNames;
    private String[] rowNames;

    public Table(String[] colNames, String[] rowNames, Tuple... tuples) {
        this.tuples = new ArrayList<>(tuples.length);

        Object[][] matrix = new Object[rowNames.length][];
        for(int i = 0; i < matrix.length; i++)
            matrix[i] = new Object[colNames.length];

        int curWidth = 0;
        int curHeight = 0;

        for(Tuple t : tuples) {
            if(t.isHorizontal()) {
                if(t.getNames().length != curWidth)
                    throw new ArrayLengthMismatchException(t.getNames().length, curWidth);

                System.arraycopy(t.getObjects(), 0, matrix[curHeight], 0, curWidth);
                curHeight++;
            } else {
                if(t.getNames().length != curHeight)
                    throw new ArrayLengthMismatchException(t.getNames().length, curHeight);

                for(int y = 0; y < t.getObjects().length; y++)
                    matrix[y][curWidth] = t.getObjects()[y];

                curWidth++;
            }
        }

        if(curWidth != colNames.length)
            throw new ArrayLengthMismatchException("Number of column header names (" + colNames.length + " does not match width of largest tuple passed to the constructor (" + curWidth + ")");
        if(curHeight != rowNames.length)
            throw new ArrayLengthMismatchException("Number of row names (" + rowNames.length + " does not match width of largest tuple passed to the constructor (" + curHeight + ")");

        for(Object[] row : matrix) {
            this.tuples.add(new Tuple(row, colNames));
        }
    }

    public void transpose() {
        transposed = !transposed;
    }

    public boolean isTransposed() {
        return transposed;
    }

    public Table filter(Predicate<Tuple> p) {

        var stream = IntStream.range(0, tuples.size())
                .filter(i -> p.test(tuples.get(i)));

        int amount = (int) stream.count();
        ArrayList<Tuple> newRows = new ArrayList<>(amount);
        ArrayList<String> newRowNames = new ArrayList<>(amount);

        stream.forEach(row -> {
            newRowNames.add(rowNames[row]);
            newRows.add(tuples.get(row));
        });

        return new Table(colNames, newRowNames.toArray(String[]::new), newRowNames.toArray(Tuple[]::new));
    }

    @Override
    public String toString() {
        return "Table{" +
                "tuples=" + tuples +
                ", transposed=" + transposed +
                ", colNames=" + Arrays.toString(colNames) +
                ", rowNames=" + Arrays.toString(rowNames) +
                '}';
    }
}
