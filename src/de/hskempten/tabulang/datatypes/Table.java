package de.hskempten.tabulang.datatypes;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Table<E> {

    private ArrayList<ArrayList<E>> tuples;
    private boolean transposed = false;

    private ArrayList<String> colNames;
    private HashMap<String, Integer> colLookup;

    /**
     * Create table with rows of tuples.
     *
     * All tuples are assumed to have the same amount of elements.
     * Elements will be copied. A table only has one set of column header names,
     * so it will be taken from the first tuples parameter. All header names from the
     * other tuples will be stripped away.
     *
     * @param tuples Rows of tuples
     */
    public Table(Tuple<E>... tuples) {
        this.tuples = new ArrayList<>(tuples.length);
        this.colNames = new ArrayList<>(tuples[0].getNames());

        this.colLookup = new HashMap<>(this.colNames.size());
        for(int i = 0; i < this.colNames.size(); i++)
            this.colLookup.put(this.colNames.get(i), i);

        int l = this.colNames.size();
        for(Tuple<E> t : tuples) {
            if(t.size() != l)
                throw new ArrayLengthMismatchException(t.size(), l);

            this.tuples.add(new ArrayList<>(t.getObjects()));
        }
    }

    /**
     * See {@link Table#Table(Tuple[])}.
     *
     * @param colNames Column header names
     * @param tuples Rows of tuples, each row having the same amount of elements as {@code colNames}
     */
    public Table(ArrayList<String> colNames, ArrayList<ArrayList<E>> tuples) {
        this(colNames, tuples, true);
    }

    /**
     * See {@link Table#Table(Tuple[])}.
     *
     * If {@code deepCopy} is {@code false}, no new object will be instantiated
     * (beside the colLookup HashMap).
     *
     * @param colNames Column header names
     * @param tuples Rows of tuples, each row having the same amount of elements as {@code colNames}
     * @param deepCopy If false, simply point the lists to the parameters given. Else create new {@code ArrayList} for each tuple
     */
    protected Table(ArrayList<String> colNames, ArrayList<ArrayList<E>> tuples, boolean deepCopy) {
        if(deepCopy) {
            this.colNames = new ArrayList<>(colNames);
            this.tuples = new ArrayList<>(tuples.size());
            for(var t : tuples)
                this.tuples.add(new ArrayList<>(t));
        } else {
            this.colNames = colNames;
            this.tuples = tuples;
        }

        this.colLookup = new HashMap<>(this.colNames.size());
        for(int i = 0; i < this.colNames.size(); i++)
            this.colLookup.put(this.colNames.get(i), i);
    }

    public Tuple<E> getRow(int rowNum) {
        return new Tuple<>(tuples.get(rowNum), colNames, !transposed);
    }

    public void transpose() {
        transposed = !transposed;
    }

    public boolean isTransposed() {
        return transposed;
    }

    public int colIndex(String name) {
        return colLookup.get(name);
    }

    public Table<E> filter(Predicate<ArrayList<E>> p) {

        ArrayList<ArrayList<E>> newRows = new ArrayList<>();
        for(var row : tuples) {
            if(p.test(row))
                newRows.add(row);
        }

        return new Table(colNames, newRows);
    }

    @Override
    public String toString() {
        return "Table{" +
                "tuples=" + tuples +
                ", transposed=" + transposed +
                ", colNames=" + colNames +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table<?> table = (Table<?>) o;
        return transposed == table.transposed &&
                tuples.equals(table.tuples) &&
                colNames.equals(table.colNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tuples, transposed, colNames);
    }
}
