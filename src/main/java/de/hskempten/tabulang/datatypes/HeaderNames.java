package de.hskempten.tabulang.datatypes;

import de.hskempten.tabulang.datatypes.exceptions.DuplicateNamesException;
import de.hskempten.tabulang.datatypes.exceptions.TupleNameNotFoundException;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Headers for named tuples and table columns. Creates a convenient and efficient lookup table
 * to find the name indices.
 */
public class HeaderNames implements Iterable<InternalString>, Cloneable {

    private final ArrayList<InternalString> names;
    private final HashMap<String, Integer> nameLookup;

    /**
     * Create HeaderNames object with deep-copied list of names.
     *
     * @param names Header names
     * @throws DuplicateNamesException if there is at least one name twice in the names list (case-insensitive)
     */
    public HeaderNames(List<InternalString> names) {
        this.names = new ArrayList<>(names);
        this.nameLookup = new HashMap<>(names.size());

        for (int i = 0; i < names.size(); i++)
            this.nameLookup.put(names.get(i).getString(), i);

        // keys in map must not appear twice
        // thus if map-keys-size is not the same as array size, there must be a duplicate value
        if (this.nameLookup.keySet().size() != names.size()) {
            Collections.sort(this.names);
            InternalString duplicate = findDuplicate(this.names);

            assert duplicate != null;
            throw new DuplicateNamesException(duplicate.getString());
        }
    }

    /**
     * Create HeaderNames object by appending the names of several HeaderNames objects.
     *
     * @param h Array of HeaderNames objects with each object's names array concatenated
     * @throws DuplicateNamesException if one name appears twice in different HeaderNames objects
     */
    public HeaderNames(HeaderNames... h) {
        this(HeaderNames.collectNames(h));
    }

    private static ArrayList<InternalString> collectNames(HeaderNames... headerNamesArray) {
        ArrayList<InternalString> l = new ArrayList<>(Arrays.stream(headerNamesArray).mapToInt(HeaderNames::size).sum());
        for(var headerNames : headerNamesArray)
            l.addAll(headerNames.names);

        return l;
    }

    /**
     * Get String element at corresponding index.
     *
     * @param index List index
     * @return InternalString element
     *
     * @throws IndexOutOfBoundsException if {@code index < 0} or {@code index >= size()}
     */
    public InternalString get(int index) {
        return names.get(index);
    }

    /**
     * Get index of InternalString. Note that InternalString could also represent a number, in which case this number is returned
     * if the String itself doesn't appear in the header list.
     *
     * @param name Name or index number of element
     * @return Element object
     * @throws NumberFormatException     if name not present and not convertible into a number
     * @throws IndexOutOfBoundsException if name not present and converted number is out of range
     */
    public int getIndexOf(InternalString name) {
        return getIndexOf(name.getString());
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
            throw new TupleNameNotFoundException(name, names);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * <p>Get name list.</p>
     *
     * <p><b>Do not alter list</b>, otherwise the lookup table is not synced with the ArrayList! Use the
     * {@link HeaderNames#add(InternalString)} et al to change names.</p>
     *
     * @return names ArrayList
     */
    public ArrayList<InternalString> getNames() {
        return names;
    }

    /**
     * <p>Add new name to the end of the HeaderNames object (see {@link ArrayList#add(Object)}.</p>
     *
     * <p><b>Warning:</b> This method mutates the size of the names list and breaks the functional programming paradigm.
     * Make sure containers that have objects associated with header names are updated accordingly.</p>
     *
     * @param s InternalString name to add
     * @return {@code true} (as specified by {@link Collection#add})
     */
    public boolean add(InternalString s) {
        if(nameLookup.containsKey(s.getString()))
            throw new DuplicateNamesException(s.getString());

        nameLookup.put(s.getString(), names.size());
        return names.add(s);
    }

    /**
     * <p>Add new name at specified index (see {@link ArrayList#add(int, Object)}.</p>
     *
     * <p><b>Warning:</b> This method mutates the size of the names list and breaks the functional programming paradigm.
     * Make sure containers that have objects associated with header names are updated accordingly.</p>
     *
     * @param index Index at which to insert the element
     * @param element InternalString name to add
     */
    public void add(int index, InternalString element) {
        if(nameLookup.containsKey(element.getString()))
            throw new DuplicateNamesException(element.getString());

        names.add(index, element);
        updateLookupValues(x -> (x >= index) ? (x + 1) : x);
        nameLookup.put(element.getString(), index);
    }

    /**
     * <p>Add collection of names at the end of HeaderNames object (see {@link ArrayList#addAll(Collection)}.</p>
     *
     * <p><b>Warning:</b> This method mutates the size of the names list and breaks the functional programming paradigm.
     * Make sure containers that have objects associated with header names are updated accordingly.</p>
     *
     * @param c Container with {@link InternalString} objects
     */
    public void addAll(Collection<? extends InternalString> c) {
        c.forEach(this::add);
    }

    /**
     * <p>Remove name object from list (see {@link ArrayList#remove(Object)}.</p>
     *
     * <p><b>Warning:</b> This method mutates the size of the names list and breaks the functional programming paradigm.
     * Make sure containers that have objects associated with header names are updated accordingly.</p>
     *
     * @param o String name to remove
     * @return true if removal was successful
     */
    public boolean remove(String o) {
        Integer i = nameLookup.get(o);
        if(i != null) {
            updateLookupValues(x -> (x > i) ? (x - 1) : x);
            nameLookup.remove(o);
        }

        return names.remove(new InternalString(o));
    }

    /**
     * <p>Remove element at specified index (see {@link ArrayList#remove(int)}.</p>
     *
     * <p><b>Warning:</b> This method mutates the size of the names list and breaks the functional programming paradigm.
     * Make sure containers that have objects associated with header names are updated accordingly.</p>
     *
     * @param index Index at which to remove the element
     */
    public InternalString remove(int index) {
        InternalString element = names.remove(index);
        Integer i = nameLookup.get(element.getString());
        if(i != null) {
            updateLookupValues(x -> (x > i) ? (x - 1) : x);
            nameLookup.remove(element.getString());
        }

        return element;
    }

    private void updateLookupValues(Function<Integer, Integer> updatedValue) {
        for(var entry : nameLookup.entrySet()) {
            entry.setValue(updatedValue.apply(entry.getValue()));
        }
    }

    /**
     * <p>Get number of elements in header.</p>
     *
     * @return number of elements
     */
    public int size() {
        return names.size();
    }

    /**
     * Returns {@code true} if HeaderNames contains specified name.
     *
     * @param   name   Name to check if it's in HeaderNames
     * @return {@code true} if this list or header names contains specified name.
     */
    public boolean contains(InternalString name) {
        return nameLookup.containsKey(name.getString());
    }

    /**
     * Check if <b>sorted</b> array has duplicate elements, returns first element string it finds.
     *
     * @param arr Sorted String array with variable names
     * @return null, if no same string appears next to each other
     */
    private static InternalString findDuplicate(List<InternalString> arr) {
        for(int i = 1; i < arr.size(); i++) {
            if(arr.get(i).equals(arr.get(i-1)))
                return arr.get(i);
        }
        return null;
    }

    /**
     * <p>Check if names only consists of ascending numbers from 0 to {@link HeaderNames#size()}-1</p>
     *
     * @return true, if elements from index 0 to {@link HeaderNames#size()}-1 are equal to 0, 1, ..., {@link HeaderNames#size()}-1
     */
    protected boolean hasNumberOnly() {
        for(int i = 0; i < names.size(); i++) {
            if(!String.valueOf(i).equals(names.get(i).getString()))
                return false;
        }
        return true;
    }

    @SuppressWarnings({"MethodDoesntCallSuperMethod"})
    @Override
    public HeaderNames clone() throws CloneNotSupportedException {
        return new HeaderNames(names);
    }

    @Override
    public Iterator<InternalString> iterator() {
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

    @Override
    public String toString() {
        return "HeaderNames{" +
                "names=" + names +
                ", nameLookup=" + nameLookup +
                '}';
    }
}
