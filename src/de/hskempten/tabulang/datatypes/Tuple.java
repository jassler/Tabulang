package de.hskempten.tabulang.datatypes;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Tuple<E> implements Cloneable, Iterable<E> {

    private final ArrayList<E> objects;
    private final ArrayList<String> names;
    private boolean isHorizontal;

    // name - index lookup table
    // best case, HashMap finds the index of a given name in O(1) complexity, worst O(n)
    private final HashMap<String, Integer> nameLookup;

    public Tuple(E[] objects) {
        this(
                objects,
                IntStream.range(0, objects.length).boxed().map(String::valueOf).toArray(String[]::new)
        );
    }

    public Tuple(E[] objects, String[] names) {
        this(objects, names, true);
    }

    public Tuple(E[] objects, String[] names, boolean isHorizontal) {
        this(Arrays.asList(objects), Arrays.asList(names), isHorizontal);
    }

    public Tuple(List<E> objects) {
        this(
                objects,
                IntStream.range(0, objects.size()).mapToObj(String::valueOf).collect(Collectors.toList())
        );
    }

    public Tuple(List<E> objects, List<String> names) {
        this(objects, names, true);
    }

    public Tuple(List<E> objects, List<String> names, boolean isHorizontal) {
        this.objects = new ArrayList<>(objects);
        this.names = new ArrayList<>(names);
        this.isHorizontal = isHorizontal;

        this.nameLookup = new HashMap<>(this.names.size());
        for (int i = 0; i < this.names.size(); i++)
            this.nameLookup.put(this.names.get(i), i);

        if (this.objects.size() != this.names.size())
            throw new ArrayLengthMismatchException(this.objects.size(), this.names.size());

        // keys in map must not appear twice
        // thus if map-keys-size is not the same as array size, there must be a duplicate value
        if (this.nameLookup.keySet().size() != this.names.size()) {
            Collections.sort(this.names);
            String duplicate = findDuplicate(this.names);

            throw new DuplicateNamesException(duplicate);
        }
    }

    public List<E> getObjects() {
        return objects;
    }

    public List<String> getNames() {
        return names;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

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
            return objects.get(index);

        try {
            index = Integer.parseInt(name);
            return objects.get(index);
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
            objects.set(index, value);
            return;
        }

        try {
            index = Integer.parseInt(name);
            objects.set(index, value);
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
        List<E> newObjects = new ArrayList<>(objects.size() + t.getObjects().size());
        newObjects.addAll(objects);
        newObjects.addAll(t.getObjects());

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
            newObjects.add(objects.get(e));
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
        if (newNames.size() != objects.size()) {
            throw new ArrayLengthMismatchException(objects.size(), newNames.size());
        }

        return new Tuple<>(objects, newNames, isHorizontal);
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
        return this.objects.size();
    }

    @SuppressWarnings({"MethodDoesntCallSuperMethod", "CloneDoesntDeclareCloneNotSupportedException"})
    @Override
    protected Tuple<E> clone() {
        return new Tuple<>(objects, names, isHorizontal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple<?> tuple = (Tuple<?>) o;
        return isHorizontal == tuple.isHorizontal &&
                objects.equals(tuple.objects) &&
                names.equals(tuple.names);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objects, names, isHorizontal);
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "objects=" + objects +
                ", names=" + names +
                ", isHorizontal=" + isHorizontal +
                '}';
    }

    @Override
    public Iterator<E> iterator() {
        return objects.iterator();
    }
}
