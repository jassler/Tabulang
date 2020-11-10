package de.hskempten.tabulang.datatypes;

import java.util.*;
import java.util.stream.IntStream;

public class Tuple<E> implements Cloneable {

    private final E[] objects;
    private final String[] names;
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
        this.objects = Arrays.copyOf(objects, objects.length);
        this.names = Arrays.copyOf(names, names.length);

        this.nameLookup = new HashMap<>(this.names.length);
        for (int i = 0; i < this.names.length; i++)
            this.nameLookup.put(this.names[i], i);

        this.isHorizontal = isHorizontal;

        if (this.objects.length != this.names.length)
            throw new ArrayLengthMismatchException(this.objects.length, this.names.length);

        // keys in map must not appear twice
        // thus if map-keys-size is not the same as array size, there must be a duplicate value
        if (this.nameLookup.keySet().size() != this.names.length) {
            Arrays.sort(this.names);
            String duplicate = findDuplicate(this.names);

            throw new DuplicateNamesException(duplicate);
        }
    }

    public Tuple(List<E> objects) {
        this(objects.toArray((E[]) new Object[0]));
    }

    public Tuple(List<E> objects, List<String> names) {
        this(objects.toArray((E[]) new Object[0]), names.toArray(new String[0]));
    }

    public Tuple(List<E> objects, List<String> names, boolean isHorizontal) {
        this(objects.toArray((E[]) new Object[0]), names.toArray(new String[0]), isHorizontal);
    }

    public E[] getObjects() {
        return objects;
    }

    public String[] getNames() {
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
    public E get(String name) {
        int index = nameLookup.getOrDefault(name, -1);
        if (index >= 0)
            return objects[index];

        try {
            index = Integer.parseInt(name);
            return objects[index];
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
    public void set(String name, E value) {
        int index = nameLookup.getOrDefault(name, -1);
        if (index >= 0) {
            objects[index] = value;
            return;
        }

        try {
            index = Integer.parseInt(name);
            objects[index] = value;
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
    public Tuple<E> concatenate(Tuple<E> t) {
        E[] newObjects = (E[]) new Object[objects.length + t.getObjects().length];
        System.arraycopy(objects, 0, newObjects, 0, objects.length);
        System.arraycopy(t.getObjects(), 0, newObjects, objects.length, t.getObjects().length);

        String[] newNames = new String[names.length + t.getNames().length];
        System.arraycopy(names, 0, newNames, 0, names.length);
        System.arraycopy(t.getNames(), 0, newNames, names.length, t.getNames().length);

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
        E[] newObjects = (E[]) new Object[elements.length];
        String[] newNames = new String[elements.length];

        for (int i = 0; i < elements.length; i++) {
            newObjects[i] = objects[elements[i]];
            newNames[i] = names[elements[i]];
        }

        return new Tuple<>(newObjects, newNames, isHorizontal);
    }

    // TODO projectgion with names

    /**
     * Create tuple with new names. Copies objects of current tuple to the other tuple over.
     *
     * @param newNames New names list, must be same size as this tuple size
     * @return New tuple with copied object list and new names
     */
    public Tuple<E> newTupleWithNames(String[] newNames) {
        if (newNames.length != objects.length) {
            throw new ArrayLengthMismatchException(objects.length, newNames.length);
        }

        return new Tuple<>(objects, newNames, isHorizontal);
    }

    /**
     * Check if <b>sorted</b> array has duplicate elements, returns first element string it finds.
     *
     * @param arr Sorted String array with variable names
     * @return null, if no same string appears next to each other
     */
    private static String findDuplicate(String[] arr) {
        for(int i = 1; i < arr.length; i++) {
            if(arr[i].equals(arr[i-1]))
                return arr[i];
        }
        return null;
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
                Arrays.equals(objects, tuple.objects) &&
                Arrays.equals(names, tuple.names);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(isHorizontal);
        result = 31 * result + Arrays.hashCode(objects);
        result = 31 * result + Arrays.hashCode(names);
        return result;
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "objects=" + Arrays.toString(objects) +
                ", names=" + Arrays.toString(names) +
                ", isHorizontal=" + isHorizontal +
                '}';
    }
}
