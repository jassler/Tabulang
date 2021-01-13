package de.hskempten.tabulang.datatypes;

import de.hskempten.tabulang.datatypes.exceptions.ArrayLengthMismatchException;
import de.hskempten.tabulang.datatypes.exceptions.DuplicateNamesException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Tuple<E extends Styleable> extends InternalObject implements TupleOperation<Tuple<E>, E> {

    private final ArrayList<E> elements;
    private HeaderNames names;
    private boolean isHorizontal;

    /**
     * Create a new Tuple with the elements of the specified array in the order they are in.
     *
     * <p>With no names given as second parameter, elements can be indexed by their position
     * either as int or String, starting with 0.</p>
     *
     * @param elements Elements inside the Tuple
     */
    public Tuple(E[] elements) {
        this(
                elements,
                IntStream.range(0, elements.length).boxed().map(String::valueOf).map(InternalString::new).toArray(InternalString[]::new)
        );
    }

    /**
     * See {@link Tuple#Tuple(E[], InternalString[], boolean)}
     *
     * @param elements Elements inside the Tuple
     * @param names    Name for each element, where {@code objects.length == names.length}
     * @throws ArrayLengthMismatchException if objects and names array do not have the same length
     * @throws DuplicateNamesException      if names has at least one String appearing twice
     */
    public Tuple(E[] elements, InternalString[] names) {
        this(elements, names, true);
    }

    /**
     * Create a new Tuple with the elements of the specified array in the order they are in.
     * Each element can be indexed either by its position as int, or by the corresponding tuple name.
     *
     * <p>It is assumed that {@code objects.length == names.length}, thus establishing a 1:1 relationship
     * between element name and the element itself.</p>
     *
     * @param elements     Elements inside the Tuple
     * @param names        Name for each element, where {@code objects.length == names.length}
     * @param isHorizontal Are elements aligned horizontally or vertically?
     * @throws ArrayLengthMismatchException if objects and names array do not have the same length
     * @throws DuplicateNamesException      if names has at least one String appearing twice
     */
    public Tuple(E[] elements, InternalString[] names, boolean isHorizontal) {
        this(Arrays.asList(elements), Arrays.asList(names), isHorizontal);
    }

    /**
     * Create a new Tuple with the elements of the specified List in the order they are in.
     *
     * <p>With no names given as second parameter, elements can be indexed by their position
     * either as int or String, starting with 0.</p>
     *
     * @param elements Elements inside the Tuple
     */
    public Tuple(List<E> elements) {
        this(
                elements,
                IntStream.range(0, elements.size()).mapToObj(String::valueOf).map(InternalString::new).collect(Collectors.toList())
        );
    }

    /**
     * See {@link Tuple#Tuple(List, List, boolean)}
     *
     * @param elements Elements inside the Tuple
     * @param names    Name for each element, where {@code objects.size() == names.size()}
     * @throws ArrayLengthMismatchException if objects and names List do not have the same length
     * @throws DuplicateNamesException      if names has at least one String appearing twice
     */
    public Tuple(List<E> elements, List<InternalString> names) {
        this(elements, names, true);
    }

    /**
     * Create a new Tuple with the elements of the specified List in the order they are in.
     * Each element can be indexed either by its position as int, or by the corresponding tuple name.
     *
     * <p>It is assumed that {@code objects.size() == names.size()}, thus establishing a 1:1 relationship
     * between element name and the element itself. This also implies that each name must be unique.</p>
     *
     * @param elements     Elements inside the Tuple
     * @param names        Name for each element, where {@code objects.length == names.length}
     * @param isHorizontal Are elements aligned horizontally or vertically?
     * @throws ArrayLengthMismatchException if objects and names array do not have the same length
     * @throws DuplicateNamesException      if names has at least one String appearing twice
     */
    public Tuple(List<E> elements, List<InternalString> names, boolean isHorizontal) {
        this(elements, names, isHorizontal, null);
    }

    /**
     * Create a new Tuple with the elements of the specified List in the order they are in.
     * Each element can be indexed either by its position as int, or by the corresponding tuple name.
     *
     * <p>It is assumed that {@code objects.size() == names.size()}, thus establishing a 1:1 relationship
     * between element name and the element itself. This also implies that each name must be unique.</p>
     *
     * @param elements     Elements inside the Tuple
     * @param names        Name for each element, where {@code objects.length == names.length}
     * @param isHorizontal Are elements aligned horizontally or vertically?
     * @param parent       Parent table object this tuple belongs to
     * @throws ArrayLengthMismatchException if objects and names array do not have the same length
     * @throws DuplicateNamesException      if names has at least one String appearing twice
     */
    public Tuple(List<E> elements, List<InternalString> names, boolean isHorizontal, InternalObject parent) {
        super(parent);
        if (elements.size() != names.size())
            throw new ArrayLengthMismatchException(elements.size(), names.size());

        this.elements = new ArrayList<>(elements);
        for (var el : this.elements) {
            if (el instanceof InternalObject o)
                o.setParent(this);
        }

        this.names = new HeaderNames(names);
        this.isHorizontal = isHorizontal;
    }

    /**
     * Get elements of Tuple.
     *
     * <p><b>Do not tamper with the length of the list!</b> This method exists in good faith that one
     * is only looking for through the elements and changing one or the other value. If the
     * length of the list changes (eg. by adding an element or emptying the list), it's not synchronous
     * to the tuple names anymore.</p>
     *
     * @return List of elements
     */
    public List<E> getElements() {
        return elements;
    }

    /**
     * Get header names of tuple.
     *
     * <p><b>Do not tamper with the length of the list!</b> This method exists in good faith that one
     * is only looking for through the header elements and changing one or the other value. If the
     * length of the list changes (eg. by adding an element or emptying the list), it's not synchronous
     * to the tuple elements anymore.</p>
     *
     * @return List of header names
     */
    public HeaderNames getNames() {
        return names;
    }

    public void setNames(HeaderNames names) {
        if (elements.size() != names.size())
            throw new ArrayLengthMismatchException(elements.size(), names.size());

        this.names = names;
    }

    @Override
    public E get(int index) {
        return elements.get(index);
    }

    /**
     * Get tuple orientation.
     *
     * @return {@code true} for horizontal, {@code false} for vertical.
     */
    @Override
    public boolean isHorizontal() {
        return isHorizontal;
    }

    @Override
    public void transpose() {
        this.isHorizontal = !this.isHorizontal;
    }

    @Override
    public boolean isTransposed() {
        return !this.isHorizontal;
    }

    /**
     * Set tuple orientation.
     *
     * @param horizontal Tuple orientation, {@code true} for horizontal, {@code false} for vertical.
     */
    public void setHorizontal(boolean horizontal) {
        isHorizontal = horizontal;
    }

    /**
     * Get tuple element by name or index.
     * Note that if names are also indexes, the named index will be prioritized over the number index.
     *
     * @param name Name or index number of element
     * @return Element object
     * @throws NumberFormatException     if name not present and not convertible into a number
     * @throws IndexOutOfBoundsException if name not present and converted number is out of range
     */
    public E get(InternalString name) {
        return elements.get(names.getIndexOf(name.getString()));
    }

    /**
     * Get element from tuple by index. Same operation as {@link List#get(int)}.
     *
     * @param i Non-negative index
     * @return Tuple element
     * @throws IndexOutOfBoundsException if i &lt; 0 or i &ge; {@link Tuple#size()}
     */
    public E getFromIndex(int i) {
        return elements.get(i);
    }

    /**
     * Replaces the element at the specified position in this list with
     * the specified element. Same operation as {@link List#set(int, Object)}.
     *
     * @param index Non-negative index
     * @param value Value to replace element at position index with
     * @throws IndexOutOfBoundsException if i &lt; 0 or i &ge; {@link Tuple#size()}
     */
    public void setToIndex(int index, E value) {
        elements.set(index, value);
    }

    /**
     * Set tuple element with name or index to value.
     * Note that if names are also indexes, the named index will be prioritized over the number index.
     *
     * @param name Name or index number of element
     * @throws NumberFormatException     if name not present and not convertible into a number
     * @throws IndexOutOfBoundsException if name not present and converted number is out of range
     */
    public void set(String name, E value) {
        elements.set(names.getIndexOf(name), value);
    }


    /**
     * Concatenate elements of two tuples and return new generated tuple.
     * Or, get a new Tuple with elements of this tuple with elements of parameter t appended.
     *
     * @param t Tuple with object elements and names to append
     * @return new concatenated tuple
     */
    public Tuple<E> concatenate(Tuple<E> t) {
        List<E> newObjects = new ArrayList<>(elements.size() + t.getElements().size());
        newObjects.addAll(elements);
        newObjects.addAll(t.getElements());

        List<InternalString> newNames = new ArrayList<>(names.size() + t.getNames().size());
        newNames.addAll(names.getNames());
        newNames.addAll(t.names.getNames());

        return new Tuple<>(newObjects, newNames, isHorizontal);
    }

    /**
     * Generate new tuple with selected element indexes.
     * E.g., if given the numbers {@code {0, 2, 5, 1}},
     * it will pick the objects in those indexes and create a new tuple from it.
     *
     * @param elements Element indexes
     * @return Tuple with selected indexes
     */
    @Override
    public Tuple<E> projection(int... elements) {
        List<E> newObjects = new ArrayList<>(elements.length);
        List<InternalString> newNames = new ArrayList<>(elements.length);

        for (int e : elements) {
            newObjects.add(this.elements.get(e));
            newNames.add(names.get(e));
        }

        return new Tuple<>(newObjects, newNames, isHorizontal);
    }

    /**
     * See {@link Tuple#projection(int...)}
     *
     * @param names Choose elements with (column) names
     * @return Tuple with selected indexes
     */
    @Override
    public Tuple<E> projection(InternalString... names) {
        int[] indices = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            indices[i] = this.names.getIndexOf(names[i]);
        }

        return projection(indices);
    }

    /**
     * Tuple projection with no index, meaning this returns an empty tuple.
     * This is kept here since function overloading with variadic parameters gets confused
     * if no parameter is given.
     *
     * @return Tuple with no elements
     */
    @Override
    public Tuple<E> projection() {
        return new Tuple<>(new ArrayList<>(0));
    }

    /**
     * Get width of tuple. If tuple is vertical (or transposed), it returns 1.
     *
     * <p>To get the amount of elements in this tuple, use {@link Tuple#size()}</p>
     *
     * @return 1 if not horizontal or transposed, else tuple size
     */
    @Override
    public int getWidth() {
        return isHorizontal ? size() : 1;
    }

    /**
     * Get height of tuple. If tuple is horizontal (or not transposed), it returns 1.
     *
     * <p>To get the amount of elements in this tuple, use {@link Tuple#size()}</p>
     *
     * @return 1 if horizontal or not transposed, else tuple size
     */
    @Override
    public int getHeight() {
        return isHorizontal ? 1 : size();
    }

    /**
     * Create tuple with new names. Copies objects of current tuple to the other tuple over.
     *
     * @param newNames New names list, must be same size as this tuple size
     * @return New tuple with copied object list and new names
     */
    public Tuple<E> newTupleWithNames(List<InternalString> newNames) {
        if (newNames.size() != elements.size()) {
            throw new ArrayLengthMismatchException(elements.size(), newNames.size());
        }

        return new Tuple<>(elements, newNames, isHorizontal);
    }

    /**
     * NumberAST of elements in this tuple.
     *
     * @return NumberAST of elements in this tuple.
     */
    public int size() {
        return this.elements.size();
    }

    @SuppressWarnings({"MethodDoesntCallSuperMethod"})
    @Override
    public Tuple<E> clone() {
        var clone = new Tuple<>(elements, names.getNames(), isHorizontal, getParent());
        clone.setStyle(getStyle().clone());
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple<?> tuple = (Tuple<?>) o;
        return isHorizontal == tuple.isHorizontal && elements.equals(tuple.elements) && names.equals(tuple.names);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elements, names, isHorizontal);
    }

    @Override
    public String toString() {
        if (size() == 0)
            return "";

        StringBuilder sb = new StringBuilder();

        if (isHorizontal) {
            // ignore length of last element because there'll be a new line anyway
            String formatted = IntStream.range(0, names.size() - 1)
                    .map(i -> Math.max(
                            names.get(i).getString().length(),
                            elements.get(i).toString().length()
                    ))
                    .mapToObj(colSize -> "%-" + colSize + "s | ")
                    .collect(Collectors.joining());
            formatted += "%s";

            sb
                    .append(String.format(formatted, names.getNames().toArray()))
                    .append('\n')
                    .append(String.format(formatted, elements.toArray()));

        } else {
            int namesColSize = names.getNames().stream().max(Comparator.comparing(v -> v.getString().length())).orElse(new InternalString("")).getString().length();
            String formatted = "%-" + namesColSize + "s | %s";

            var nameIt = names.iterator();
            var elementIt = elements.iterator();

            while (nameIt.hasNext()) {
                sb.append(String.format(formatted, nameIt.next(), elementIt.next())).append('\n');
            }

            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    /**
     * Iterate through Tuple elements. Header names are dropped.
     *
     * @return iterator for elements List
     */
    @Override
    public Iterator<E> iterator() {
        return elements.iterator();
    }

    public Object transformIntoTableIfPossible() {
        Object firstObject = elements.get(0);
        if (firstObject instanceof Tuple) {
            int sizeRequirement = ((Tuple<?>) firstObject).size();
            for (Object o : elements) {
                if (!(o instanceof Tuple) || ((Tuple<?>) o).size() != sizeRequirement) {
                    return this;
                }
            }
            return new Table<>(elements.toArray(Tuple[]::new));
        } else {
            return this;
        }
    }
}
