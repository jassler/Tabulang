package de.hskempten.tabulang.datatypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Tuple {

    private final List<Object> objects;
    private final List<String> names;
    private boolean isHorizontal = true;

    public Tuple(List<Object> objects) {
        this.objects = objects;
        this.names = IntStream.range(0, objects.size()).boxed().map(String::valueOf).collect(Collectors.toList());
    }

    public Tuple(List<Object> objects, List<String> names) {
        this.objects = objects;
        this.names = names;
    }

    public Tuple(List<Object> objects, List<String> names, boolean isHorizontal) {
        this.objects = objects;
        this.names = names;
        this.isHorizontal = isHorizontal;
    }

    public List<Object> getObjects() {
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
     * @throws ArrayIndexOutOfBoundsException if name not present and converted number is out of range
     */
    public Object get(String name) {
        int index = names.indexOf(name);
        if (index >= 0)
            return objects.get(index);

        try {
            index = Integer.parseInt(name);
            return objects.get(index);
        } catch (NumberFormatException e) {
            // TODO should those exceptions be handled differently?
            throw e;
        } catch (ArrayIndexOutOfBoundsException e) {
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
     * @throws ArrayIndexOutOfBoundsException if name not present and converted number is out of range
     */
    public void set(String name, Object value) {
        int index = names.indexOf(name);
        if (index >= 0) {
            objects.set(index, value);
            return;
        }

        try {
            index = Integer.parseInt(name);
            objects.set(index, value);
        } catch (NumberFormatException e) {
            // TODO should those exceptions be handled differently?
            throw e;
        } catch (ArrayIndexOutOfBoundsException e) {
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
    public Tuple concatenate(Tuple t) {
        List<Object> newObjects = new ArrayList<>(objects);
        newObjects.addAll(t.getObjects());

        List<String> newNames = new ArrayList<>(names);
        newNames.addAll(t.getNames());

        return new Tuple(newObjects, newNames, isHorizontal);
    }

    /**
     * Generate new tuple with selected element indexes.
     * E.g., if given the numbers {@code {0, 2, 5, 1}},
     * it will pick the objects in those indexes and create a new tuple from it.
     *
     * @param elements Element indexes
     * @return Tuple with selected indexes
     */
    public Tuple projection(int... elements) {
        List<Object> newObjects = new ArrayList<>(elements.length);
        List<String> newNames = new ArrayList<>(elements.length);

        for (int el : elements) {
            newObjects.add(objects.get(el));
            newNames.add(names.get(el));
        }

        return new Tuple(newObjects, newNames, isHorizontal);
    }

    /**
     * @see Tuple#projection(int...)
     */
    public Tuple projection(List<Integer> elements) {
        List<Object> newObjects = new ArrayList<>(elements.size());
        List<String> newNames = new ArrayList<>(elements.size());

        for (int i = 0; i < elements.size(); i++) {
            newObjects.set(i, objects.get(elements.get(i)));
            newNames.set(i, names.get(elements.get(i)));
        }

        return new Tuple(newObjects, newNames, isHorizontal);
    }

    /**
     * Create tuple with new names. Copies objects of current tuple to the other tuple over.
     *
     * @param newNames New names list, must be same size as this tuple size
     * @return New tuple with copied object list and new names
     */
    public Tuple newTupleWithNames(List<String> newNames) {
        if (newNames.size() != objects.size()) {
            throw new RuntimeException("Size of newNames list passed does not equal tuple size: expected "
                    + objects.size() + ", got " + newNames.size());
        }
        List<Object> copy = new ArrayList<>(objects.size());
        copy.addAll(objects);
        return new Tuple(copy, newNames, isHorizontal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple tuple = (Tuple) o;
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
}
