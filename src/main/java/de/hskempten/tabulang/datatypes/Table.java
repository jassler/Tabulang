package de.hskempten.tabulang.datatypes;

import de.hskempten.tabulang.datatypes.exceptions.ArrayLengthMismatchException;
import de.hskempten.tabulang.datatypes.exceptions.TableHeaderMismatchException;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Table<E> extends TableObject implements Iterable<Tuple<E>> {

    private final HashMap<Integer, Style> rowStyles = new HashMap<>();
    private final HashMap<Integer, Style> columnStyles = new HashMap<>();
    private final HashMap<Point, Style> cellStyles = new HashMap<>();

    private final ArrayList<ArrayList<E>> tuples;
    private boolean transposed = false;

    private final HeaderNames colNames;
    // private final HashMap<String, Integer> colLookup;

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
        this(null, tuples);
    }

    /**
     * Create table with rows of tuples.
     *
     * <p>All tuples are assumed to have the same amount of elements.
     * Elements will be copied. A table only has one set of column header names,
     * so it will be taken from the first tuples parameter. All header names from the
     * other tuples will be stripped away.
     *
     * @param parent Parent table object this table sits in
     * @param tuples Rows of tuples
     */
    @SafeVarargs
    public Table(TableObject parent, Tuple<E>... tuples) {
        super(parent);
        this.tuples = new ArrayList<>(tuples.length);

        if (tuples.length > 0)
            this.colNames = new HeaderNames(tuples[0].getNames());
        else
            this.colNames = new HeaderNames();

        int l = this.colNames.size();
        for (Tuple<E> t : tuples) {
            if (t.size() != l)
                throw new ArrayLengthMismatchException(t.size(), l);

            this.tuples.add(new ArrayList<>(t.getElements()));
        }
    }

    /**
     * See {@link Table#Table(Tuple[])}.
     *
     * @param colNames Column header names
     * @param tuples Rows of tuples, each row having the same amount of elements as {@code colNames}
     */
    public Table(ArrayList<String> colNames, ArrayList<ArrayList<E>> tuples) {
        this(colNames, tuples, true, null);
    }

    /**
     * See {@link Table#Table(Tuple[])}.
     *
     * <p>If {@code deepCopy} is {@code false}, no new object will be instantiated
     * (beside the colLookup HashMap).
     *
     * @param colNames Column header names
     * @param tuples   Rows of tuples, each row having the same amount of elements as {@code colNames}
     * @param deepCopy If false, simply point the lists to the parameters given. Else create new {@code ArrayList} for each tuple
     */
    protected Table(ArrayList<String> colNames, ArrayList<ArrayList<E>> tuples, boolean deepCopy, TableObject parent) {
        super(parent);
        this.colNames = new HeaderNames(colNames);
        if (deepCopy) {
            this.tuples = new ArrayList<>(tuples.size());
            for (var t : tuples)
                this.tuples.add(new ArrayList<>(t));
        } else {
            this.tuples = tuples;
        }
    }

    public Tuple<E> getRow(int rowNum) {
        Tuple<E> t = new Tuple<>(tuples.get(rowNum), colNames, !transposed, this);

        if (rowStyles.containsKey(rowNum)) {
            Style s = rowStyles.get(rowNum);
            for (int i = 0; i < t.size(); i++)
                t.setElementStyle(i, s);
        }

        for (var entry : columnStyles.entrySet()) {
            if (entry.getKey() >= 0 && entry.getKey() < t.size()) {
                t.setElementStyle(entry.getKey(), entry.getValue());
            }
        }

        for (var entry : cellStyles.entrySet()) {
            if (entry.getKey().y == rowNum)
                t.setElementStyle(entry.getKey().x, entry.getValue());
        }

        return t;
    }

    public void setRowStyle(int rowNum, Style style) {
        this.rowStyles.put(rowNum, style);
    }

    public void setRowHeight(int rowNum, double height) {
        Style s = this.rowStyles.getOrDefault(rowNum, new Style());
        s.setAttribute(Style.ROW_HEIGHT, Double.toString(height));
        this.rowStyles.put(rowNum, s);
    }

    public HashMap<Integer, Style> getRowStyles() {
        return rowStyles;
    }

    public void setColumnStyle(int colNum, Style style) {
        this.columnStyles.put(colNum, style);
    }

    public void setColumnWidth(int colNum, double width) {
        Style s = this.columnStyles.getOrDefault(colNum, new Style());
        s.setAttribute(Style.COLUMN_WIDTH, Double.toString(width));
        this.columnStyles.put(colNum, s);
    }

    public HashMap<Integer, Style> getColumnStyles() {
        return columnStyles;
    }

    public void setCellStyle(int x, int y, Style style) {
        this.cellStyles.put(new Point(x, y), style);
    }

    public HashMap<Point, Style> getCellStyles() {
        return cellStyles;
    }

    /**
     * Use carefully!
     *
     * @return tuple arraylist
     */
    @Deprecated
    public ArrayList<ArrayList<E>> getRows() {
        return tuples;
    }

    public void transpose() {
        transposed = !transposed;
    }

    public boolean isTransposed() {
        return transposed;
    }

    public HeaderNames getColNames() {
        return colNames;
    }

    /**
     * Get column index from name.
     *
     * @param name Column header string
     * @return Index of column
     * @throws NumberFormatException     if name not present and not convertible into a number
     * @throws IndexOutOfBoundsException if name not present and converted number is out of range
     */
    public int getColumnIndex(String name) {
        return colNames.getIndexOf(name);
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

        return new Table<>(colNames.getNames(), newRows, true, getParent());
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

        return new Table<>(newColNames, newRows, false, getParent());
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
        return new Table<>(getParent());
    }

    /**
     * Given two tables generate a new table with rows that appear in both of them only.
     *
     * @param other Table with which to intersect
     * @return Table intersection with this and the other Table
     */
    public Table<E> intersection(Table<E> other) {

        if(!colNames.equals(other.colNames))
            throw new TableHeaderMismatchException(colNames.getNames(), other.colNames.getNames());

        ArrayList<ArrayList<E>> newRows = new ArrayList<>();

        // TODO solve for O(n^2) complexity
        for(var t : tuples) {
            if(other.tuples.contains(t))
                newRows.add(new ArrayList<>(t));
        }

        return new Table<>(colNames.getNames(), newRows, false, getParent());
    }

    /**
     * Given two tables generate a new table with rows of both of them, though with no duplicates.
     *
     * @param other Table with which to create a union
     * @return Table with tuple rows of {@code other} appended to tuples of this object
     */
    public Table<E> union(Table<E> other) {

        if(!colNames.equals(other.colNames))
            throw new TableHeaderMismatchException(colNames.getNames(), other.colNames.getNames());

        ArrayList<ArrayList<E>> newRows = new ArrayList<>();
        Set<ArrayList<E>> lookup = new HashSet<>();

        for(var t : tuples) {
            if(!lookup.contains(t)) {
                newRows.add(t);
                lookup.add(t);
            }
        }

        for(var t : other.tuples) {
            if(!lookup.contains(t)) {
                newRows.add(t);
                lookup.add(t);
            }
        }

        return new Table<>(colNames.getNames(), newRows, false, getParent());
    }

    /**
     * Generate new table with all rows removed that appear in {@code other}.
     *
     * @param other Rows which are to be removed from this object's tuple rows
     * @return Table with removed rows
     */
    public Table<E> difference(Table<E> other) {

        if(!colNames.equals(other.colNames))
            throw new TableHeaderMismatchException(colNames.getNames(), other.colNames.getNames());

        ArrayList<ArrayList<E>> newRows = new ArrayList<>(tuples);

        for(var t : other.tuples) {
            newRows.remove(t);
        }

        return new Table<>(colNames.getNames(), newRows, false, getParent());
    }

    /**
     * Add {@code other} to the right of the table. If number of rows is not equal, missing cells will be filled with null.
     *
     * @param other Table which is to be appended to the right of this table
     * @return New table with other table appended to the right
     */
    public Table<E> horizontalPairing(Table<E> other) {

        int numRows = Math.max(tuples.size(), other.tuples.size());
        int numCols = colNames.size() + other.colNames.size();

        ArrayList<ArrayList<E>> newRows = new ArrayList<>(numRows);
        ArrayList<String> newColNames = new ArrayList<>(numCols);

        for(int i = 0; i < numRows; i++) {
            ArrayList<E> row = new ArrayList<>(numCols);
            if(i < tuples.size())
                row.addAll(tuples.get(i));
            else
                row.addAll(Collections.nCopies(colNames.size(), null));

            if (i < other.tuples.size())
                row.addAll(other.tuples.get(i));
            else
                row.addAll(Collections.nCopies(other.colNames.size(), null));

            newRows.add(row);
        }

        newColNames.addAll(colNames.getNames());
        newColNames.addAll(other.colNames.getNames());

        return new Table<>(newColNames, newRows, false, getParent());
    }

    /**
     * Add {@code other} to the bottom of the table. If number of columns is not equal, missing cells will be filled with null.
     *
     * Column header will be taken from this table, each missing column from {@code other}.
     *
     * @param other Table which is to be appended to the bottom of this table
     * @return New table with other table appended to the bottom
     */
    public Table<E> verticalPairing(Table<E> other) {

        int numRows = tuples.size() + other.tuples.size();
        int numCols = Math.max(colNames.size(), other.colNames.size());

        ArrayList<ArrayList<E>> newRows = new ArrayList<>(numRows);
        ArrayList<String> newColNames = new ArrayList<>(numCols);

        List<E> toAppend = Collections.nCopies(numCols - colNames.size(), null);
        for(var row : tuples) {
            ArrayList<E> newRow = new ArrayList<>(numCols);
            newRow.addAll(row);
            if(toAppend.size() > 0)
                newRow.addAll(toAppend);
            newRows.add(newRow);
        }

        toAppend = Collections.nCopies(numCols - other.colNames.size(), null);
        for(var row : other.tuples) {
            ArrayList<E> newRow = new ArrayList<>(numCols);
            newRow.addAll(row);
            if(toAppend.size() > 0)
                newRow.addAll(toAppend);
            newRows.add(newRow);
        }

        newColNames.addAll(colNames.getNames());
        if (colNames.size() < other.colNames.size()) {
            for (int i = colNames.size(); i < other.colNames.size(); i++)
                newColNames.add(other.colNames.get(i));
        }

        return new Table<>(newColNames, newRows, false, getParent());
    }

    private int[] calculateColumnWidths() {
        int[] strLengths;

        if (transposed) {
            strLengths = new int[tuples.size() + 1];

            // colHeader is index 0
            strLengths[0] = colNames.getNames().stream()
                    .mapToInt(String::length)
                    .max().getAsInt();

            // each tuple represents a column, so we can simply check the longest value in each tuple
            for (int i = 0; i < tuples.size(); i++) {
                strLengths[i + 1] = tuples.get(i).stream()
                        .mapToInt(o -> o.toString().length())
                        .max().getAsInt();
            }
        } else {
            strLengths = IntStream.range(0, colNames.size())
                    .map(i -> Math.max(
                            colNames.get(i).length(),
                            tuples.stream()
                                    .mapToInt(row -> row.get(i).toString().length())
                                    .max().getAsInt()))
                    .toArray();
        }

        return strLengths;
    }

    @Override
    public String toString() {
        if (colNames.size() == 0)
            return "";

        StringBuilder sb;

        int[] strLengths = calculateColumnWidths();
        var formattedStream = Arrays.stream(strLengths).mapToObj(i -> "%-" + i + "s");

        if (transposed) {
            String[] formatted = formattedStream.toArray(String[]::new);
            formatted[0] = formatted[0] + " |";
            formatted[formatted.length - 1] = "%s";

            // Calculating the capacity
            // Width of all columns added together (probably a little less for most rows)
            // +strLength.length for whitespace between each column (and newline at the end)
            // +2 for the " |" delimiter after the first column
            // This row length times number of rows
            sb = new StringBuilder((Arrays.stream(strLengths).sum() + strLengths.length + 2) * colNames.size());

            for (int row = 0; row < colNames.size(); row++) {
                var it = Arrays.stream(formatted).iterator();

                if(row > 0)
                    sb.append('\n');
                sb.append(String.format(it.next(), colNames.get(row)));

                // variable used in lambda expression must be final
                int finalRow = row;
                tuples.stream().forEach(
                        t -> sb.append(' ').append(String.format(it.next(), t.get(finalRow)))
                );
            }

        } else {
            String delimiter = " | ";
            String formatted = formattedStream.collect(Collectors.joining(delimiter)).replaceAll("%-\\d+s$", "%s");

            // rowLength is sum of column widths
            // + amount of whitespace between columns
            // (-1 because there's no delimiter after the last column)
            int rowLength = Arrays.stream(strLengths).sum() + (strLengths.length - 1) * delimiter.length();
            sb = new StringBuilder(rowLength * (tuples.size() + 1));

            // column header
            // -----separator-----
            // tuple rows
            sb.append(String.format(formatted, colNames.getNames().toArray())).append('\n');
            sb.append("-".repeat(rowLength));
            tuples.stream().forEach(t -> sb.append('\n').append(String.format(formatted, t.toArray())));
        }

        return sb.toString();
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

    @Override
    public Iterator<Tuple<E>> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<Tuple<E>> {
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < tuples.size();
        }

        @Override
        public Tuple<E> next() {
            if (!hasNext())
                return null;

            Tuple<E> result = getRow(cursor);
            cursor++;
            return result;
        }
    }
}
