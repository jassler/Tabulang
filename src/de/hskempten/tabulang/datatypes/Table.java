package de.hskempten.tabulang.datatypes;

import java.util.*;
import java.util.function.Predicate;

public class Table<E> {

    private final ArrayList<ArrayList<E>> tuples;
    private boolean transposed = false;

    private final ArrayList<String> colNames;
    private final HashMap<String, Integer> colLookup;

    /**
     * Create table with rows of tuples.
     *
     * <p>All tuples are assumed to have the same amount of elements.
     * Elements will be copied. A table only has one set of column header names,
     * so it will be taken from the first tuples parameter. All header names from the
     * other tuples will be stripped away.
     *
     * @param tuples Rows of tuples
     */
    @SafeVarargs
    public Table(Tuple<E>... tuples) {
        this.tuples = new ArrayList<>(tuples.length);

        if (tuples.length > 0)
            this.colNames = new ArrayList<>(tuples[0].getNames());
        else
            this.colNames = new ArrayList<>(0);

        this.colLookup = new HashMap<>(this.colNames.size());
        for (int i = 0; i < this.colNames.size(); i++)
            this.colLookup.put(this.colNames.get(i), i);

        int l = this.colNames.size();
        for (Tuple<E> t : tuples) {
            if (t.size() != l)
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
     * <p>If {@code deepCopy} is {@code false}, no new object will be instantiated
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

    /**
     * Get column index from name.
     *
     * @param name Column header string
     * @return Index of column
     * @throws NullPointerException when {@code name} is not in column header
     */
    public int getColumnIndex(String name) {
        return colLookup.get(name);
    }

    /**
     * Filter tuple rows based on predicate. For storage and timing reason, the parameter passed
     * to the predicate is an {@code ArrayList<E>} and not a {@code Tuple<E>}.
     *
     * <p>To get a column index by name, call {@link Table#getColumnIndex(String)}.
     *
     * @param p Predicate by which to determine if a row should be included or not. For example: {@code filter(row -> row.get(0) != null)}
     * @return {@code Table<E>} with filtered rows
     */
    public Table<E> filter(Predicate<ArrayList<E>> p) {

        ArrayList<ArrayList<E>> newRows = new ArrayList<>();
        for (var row : tuples) {
            if (p.test(row))
                newRows.add(row);
        }

        return new Table<>(colNames, newRows);
    }

    /**
     * Go through each tuple row in table and do a projection of that (see {@link Tuple#projection(int...)}).
     *
     * <p>A row may not appear twice in the resulting table. If for example by dropping a certain column duplicates start
     * to appear, those will be dropped.
     *
     * @param indices Column indices on which to project
     * @return {@code Table<E>} with projected table columns
     */
    public Table<E> projection(int... indices) {
        // newRows keeps it in order
        // existingRows makes sure each row only appears once
        ArrayList<ArrayList<E>> newRows = new ArrayList<>(tuples.size());
        Set<ArrayList<E>> existingRows = new HashSet<>(tuples.size());

        ArrayList<String> newColNames = new ArrayList<>(indices.length);
        for(int i : indices)
            newColNames.add(colNames.get(i));

        for(var t : tuples) {
            ArrayList<E> newRow = new ArrayList<>(indices.length);
            for(int i : indices)
                newRow.add(t.get(i));

            if(existingRows.contains(newRow))
                continue;

            existingRows.add(newRow);
            newRows.add(newRow);
        }

        return new Table<>(newColNames, newRows, false);
    }

    /**
     * See {@link Table#projection(int...)}
     *
     * @param colNames Column indices on which to project
     * @return {@code Table<E>} with projected table columns
     */
    public Table<E> projection(String... colNames) {
        int[] indices = new int[colNames.length];
        for(int i = 0; i < indices.length; i++)
            indices[i] = getColumnIndex(colNames[i]);

        return projection(indices);
    }

    /**
     * Projection to no columns. This is kept here since function overloading doesn't know what to pick
     * when no parameter is given to a variadic function.
     *
     * <p>This method basically returns an empty table.
     *
     * @return an empty Table
     */
    public Table<E> projection() {
        return new Table<>();
    }

    /**
     * Given two tables generate a new table with rows that appear in both of them only.
     *
     * @param other Table with which to intersect
     * @return Table intersection with this and the other Table
     */
    public Table<E> intersection(Table<E> other) {

        if(!colNames.equals(other.colNames))
            throw new TableHeaderMismatchException(colNames, other.colNames);

        ArrayList<ArrayList<E>> newRows = new ArrayList<>();

        // TODO solve for O(n^2) complexity
        for(var t : tuples) {
            if(other.tuples.contains(t))
                newRows.add(new ArrayList<>(t));
        }

        return new Table<>(colNames, newRows, false);
    }

    /**
     * Given two tables generate a new table with rows of both of them, though with no duplicates.
     *
     * @param other Table with which to create a union
     * @return Table with tuple rows of {@code other} appended to tuples of this object
     */
    public Table<E> union(Table<E> other) {

        if(!colNames.equals(other.colNames))
            throw new TableHeaderMismatchException(colNames, other.colNames);

        ArrayList<ArrayList<E>> newRows = new ArrayList<>(tuples);
        Set<ArrayList<E>> lookup = new HashSet<>(tuples);

        for(var t : other.tuples) {
            if(!lookup.contains(t)) {
                newRows.add(t);
                lookup.add(t);
            }
        }

        return new Table<>(colNames, newRows, false);
    }

    /**
     * Generate new table with all rows removed that appear in {@code other}.
     *
     * @param other Rows which are to be removed from this object's tuple rows
     * @return Table with removed rows
     */
    public Table<E> difference(Table<E> other) {

        if(!colNames.equals(other.colNames))
            throw new TableHeaderMismatchException(colNames, other.colNames);

        ArrayList<ArrayList<E>> newRows = new ArrayList<>(tuples);

        for(var t : other.tuples) {
            newRows.remove(t);
        }

        return new Table<>(colNames, newRows, false);
    }

    public Table<E> horizontalPairing(Table<E> t1, Table<E> t2) {
        // TODO p12 (g)
        return null;
    }

    public Table<E> verticalPairing(Table<E> t1, Table<E> t2) {
        // TODO p12 (h)
        return null;
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
