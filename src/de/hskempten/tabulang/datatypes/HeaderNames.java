package de.hskempten.tabulang.datatypes;

import de.hskempten.tabulang.datatypes.exceptions.DuplicateNamesException;

import java.util.*;
import java.util.function.Function;

public class HeaderNames implements Iterable<String> {

    private ArrayList<String> names;
    private HashMap<String, Integer> nameLookup;

    public HeaderNames(List<String> names) {
        this.names = new ArrayList<>(names);
        this.nameLookup = new HashMap<>(names.size());

        for (int i = 0; i < names.size(); i++)
            this.nameLookup.put(names.get(i), i);

        // keys in map must not appear twice
        // thus if map-keys-size is not the same as array size, there must be a duplicate value
        if (this.nameLookup.keySet().size() != names.size()) {
            Collections.sort(this.names);
            String duplicate = findDuplicate(this.names);

            throw new DuplicateNamesException(duplicate);
        }
    }

    public HeaderNames(HeaderNames... h) {
        this(HeaderNames.collectNames(h));
    }

    private static ArrayList<String> collectNames(HeaderNames... headerNamesArray) {
        ArrayList<String> l = new ArrayList<>(Arrays.stream(headerNamesArray).mapToInt(HeaderNames::size).sum());
        for(var headerNames : headerNamesArray)
            l.addAll(headerNames.names);

        return l;
    }

    /**
     * Get String element at corresponding index.
     *
     * @param index List index
     * @return String element
     *
     * @throws IndexOutOfBoundsException if {@code index < 0} or {@code index >= size()}
     */
    public String get(int index) {
        return names.get(index);
    }

    /**
     * Get index of String. Note that String could also represent a number, in which case this number is returned
     * if the String itself doesn't appear in the header list.
     *
     * @param name Name or index number of element
     * @return Element object
     * @throws NumberFormatException     if name not present and not convertible into a number
     * @throws IndexOutOfBoundsException if name not present and converted number is out of range
     */
    public int getIndexOf(String name) {
        Integer index = nameLookup.get(name);
        if (index != null)
            return index;

        try {
            int i = Integer.parseInt(name);
            Objects.checkIndex(i, names.size());
            return i;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            // TODO should those exceptions be handled differently?
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public boolean add(String s) {
        if(nameLookup.containsKey(s))
            throw new DuplicateNamesException(s);

        nameLookup.put(s, names.size());
        return names.add(s);
    }

    public void add(int index, String element) {
        if(nameLookup.containsKey(element))
            throw new DuplicateNamesException(element);

        names.add(index, element);
        updateLookupValues(x -> (x >= index) ? (x + 1) : x);
        nameLookup.put(element, index);
    }

    public void addAll(Collection<? extends String> c) {
        int index = names.size();
        for(var s : c) {
            nameLookup.put(s, index);
            index++;
        }
        names.addAll(c);
    }

    public boolean remove(String o) {
        Integer i = nameLookup.get(o);
        if(i != null) {
            updateLookupValues(x -> (x > i) ? (x - 1) : x);
            nameLookup.remove(o);
        }

        return names.remove(o);
    }

    public String remove(int index) {
        String element = names.remove(index);
        Integer i = nameLookup.get(element);
        if(i != null) {
            updateLookupValues(x -> (x > i) ? (x - 1) : x);
            nameLookup.remove(element);
        }

        return element;
    }

    public int size() {
        return names.size();
    }

    private void updateLookupValues(Function<Integer, Integer> updatedValue) {
        for(var entry : nameLookup.entrySet()) {
            entry.setValue(updatedValue.apply(entry.getValue()));
        }
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

    @Override
    public Iterator<String> iterator() {
        return names.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeaderNames strings = (HeaderNames) o;
        return names.equals(strings.names);
    }

    @Override
    public int hashCode() {
        return Objects.hash(names);
    }
}
