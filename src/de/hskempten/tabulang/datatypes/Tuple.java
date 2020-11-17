package de.hskempten.tabulang.datatypes;

import de.hskempten.tabulang.datatypes.exceptions.ArrayLengthMismatchException;
import de.hskempten.tabulang.datatypes.exceptions.DuplicateNamesException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Tuple<E> implements Cloneable, Iterable<E> {

    private final ArrayList<E> elements;
    private final ArrayList<String> names;
    private boolean isHorizontal;

    // name - index lookup table
    // best case, HashMap finds the index of a given name in O(1) complexity, worst O(n)
    private final HashMap<String, Integer> nameLookup;

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
                IntStream.range(0, elements.length).boxed().map(String::valueOf).toArray(String[]::new)
        );
    }

    /**
     * See {@link Tuple#Tuple(Object[], String[], boolean)}
     * 
     * @param elements Elements inside the Tuple
     * @param names Name for each element, where {@code objects.length == names.length}
     *              
     * @throws ArrayLengthMismatchException if objects and names array do not have the same length
     * @throws DuplicateNamesException if names has at least one String appearing twice
     */
    public Tuple(E[] elements, String[] names) {
        this(elements, names, true);
    }

    /**
     * Create a new Tuple with the elements of the specified array in the order they are in.
     * Each element can be indexed either by its position as int, or by the corresponding tuple name.
     *
     * <p>It is assumed that {@code objects.length == names.length}, thus establishing a 1:1 relationship
     * between element name and the element itself.</p>
     *
     * @param elements Elements inside the Tuple
     * @param names Name for each element, where {@code objects.length == names.length}
     * @param isHorizontal Are elements aligned horizontally or vertically?
     *
     * @throws ArrayLengthMismatchException if objects and names array do not have the same length
     * @throws DuplicateNamesException if names has at least one String appearing twice
     */
    public Tuple(E[] elements, String[] names, boolean isHorizontal) {
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
                IntStream.range(0, elements.size()).mapToObj(String::valueOf).collect(Collectors.toList())
        );
    }

    /**
     * See {@link Tuple#Tuple(List, List, boolean)}
     *
     * @param elements Elements inside the Tuple
     * @param names Name for each element, where {@code objects.size() == names.size()}
     *
     * @throws ArrayLengthMismatchException if objects and names List do not have the same length
     * @throws DuplicateNamesException if names has at least one String appearing twice
     */
    public Tuple(List<E> elements, List<String> names) {
        this(elements, names, true);
    }

    /**
     * Create a new Tuple with the elements of the specified List in the order they are in.
     * Each element can be indexed either by its position as int, or by the corresponding tuple name.
     *
     * <p>It is assumed that {@code objects.size() == names.size()}, thus establishing a 1:1 relationship
     * between element name and the element itself. This also implies that each name must be unique.</p>
     *
     * @param elements Elements inside the Tuple
     * @param names Name for each element, where {@code objects.length == names.length}
     * @param isHorizontal Are elements aligned horizontally or vertically?
     *
     * @throws ArrayLengthMismatchException if objects and names array do not have the same length
     * @throws DuplicateNamesException if names has at least one String appearing twice
     */
    public Tuple(List<E> elements, List<String> names, boolean isHorizontal) {
        this.elements = new ArrayList<>(elements);
        this.names = new ArrayList<>(names);
        this.isHorizontal = isHorizontal;

        this.nameLookup = new HashMap<>(this.names.size());
        for (int i = 0; i < this.names.size(); i++)
            this.nameLookup.put(this.names.get(i), i);

        if (this.elements.size() != this.names.size())
            throw new ArrayLengthMismatchException(this.elements.size(), this.names.size());

        // keys in map must not appear twice
        // thus if map-keys-size is not the same as array size, there must be a duplicate value
        if (this.nameLookup.keySet().size() != this.names.size()) {
            Collections.sort(this.names);
            String duplicate = findDuplicate(this.names);

            throw new DuplicateNamesException(duplicate);
        }
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
    public List<String> getNames() {
        return names;
    }

    /**
     * Get tuple orientation.
     *
     * @return {@code true} for horizontal, {@code false} for vertical.
     */
    public boolean isHorizontal() {
        return isHorizontal;
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
     * @throws NumberFormatException          if name not present and not convertible into a number
     * @throws IndexOutOfBoundsException if name not present and converted number is out of range
     */
    public E get(String name) {
        int index = nameLookup.getOrDefault(name, -1);
        if (index >= 0)
            return elements.get(index);

        try {
            index = Integer.parseInt(name);
            return elements.get(index);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            // TODO should those exceptions be handled differently?
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Set tuple element with name or index to value.
     * Note that if names are also indexes, the named index will be prioritized over the number index.
     *
     * @param name Name or index number of element
     * @throws NumberFormatException          if name not present and not convertible into a number
     * @throws IndexOutOfBoundsException if name not present and converted number is out of range
     */
    public void set(String name, E value) {
        int index = nameLookup.getOrDefault(name, -1);
        if (index >= 0) {
            elements.set(index, value);
            return;
        }

        try {
            index = Integer.parseInt(name);
            elements.set(index, value);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            // TODO should those exceptions be handled differently?
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Concatenate elements of two tuples and return new generated tuple.
     *
     * @param t Tuple with object elements and names to append
     * @return new concatenated tuple
     */
    public Tuple<E> concatenate(Tuple<E> t) {
        List<E> newObjects = new ArrayList<>(elements.size() + t.getElements().size());
        newObjects.addAll(elements);
        newObjects.addAll(t.getElements());

        List<String> newNames = new ArrayList<>(names.size() + t.getNames().size());
        newNames.addAll(names);
        newNames.addAll(t.getNames());

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
    public Tuple<E> projection(int... elements) {
        List<E> newObjects = new ArrayList<>(elements.length);
        List<String> newNames = new ArrayList<>(elements.length);

        for(int e : elements) {
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
    public Tuple<E> projection(String... names) {
        List<E> newObjects = new ArrayList<>(names.length);
        List<String> newNames = new ArrayList<>(names.length);

        for(String name : names) {
            newObjects.add(this.get(name));
            newNames.add(name);
        }

        return new Tuple<>(newObjects, newNames, isHorizontal);
    }

    /**
     * Tuple projection with no index, meaning this returns an empty tuple.
     * This is kept here since function overloading with variadic parameters gets confused
     * if no parameter is given.
     *
     * @return Tuple with no elements
     */
    public Tuple<E> projection() {
        return new Tuple<>(new ArrayList<>(0));
    }

    /**
     * Create tuple with new names. Copies objects of current tuple to the other tuple over.
     *
     * @param newNames New names list, must be same size as this tuple size
     * @return New tuple with copied object list and new names
     */
    public Tuple<E> newTupleWithNames(List<String> newNames) {
        if (newNames.size() != elements.size()) {
            throw new ArrayLengthMismatchException(elements.size(), newNames.size());
        }

        return new Tuple<>(elements, newNames, isHorizontal);
    }

    /**
     * Check if <b>sorted</b> array has duplicate elements, returns first element string it finds.
     *
     * @param arr Sorted String array with variable names
     * @return null, if no same string appears next to each other
     */
    private static String findDuplicate(List<String> arr) {
        for(int i = 1; i < arr.size(); i++) {
            if(arr.get(i).equals(arr.get(i-1)))
                return arr.get(i);
        }
        return null;
    }

    /**
     * Number of elements in this tuple.
     *
     * @return Number of elements in this tuple.
     */
    public int size() {
        return this.elements.size();
    }

    @SuppressWarnings({"MethodDoesntCallSuperMethod", "CloneDoesntDeclareCloneNotSupportedException"})
    @Override
    protected Tuple<E> clone() {
        return new Tuple<>(elements, names, isHorizontal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple<?> tuple = (Tuple<?>) o;
        return isHorizontal == tuple.isHorizontal &&
                elements.equals(tuple.elements) &&
                names.equals(tuple.names);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elements, names, isHorizontal);
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "objects=" + elements +
                ", names=" + names +
                ", isHorizontal=" + isHorizontal +
                '}';
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
}
