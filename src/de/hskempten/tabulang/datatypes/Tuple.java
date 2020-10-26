package de.hskempten.tabulang.datatypes;

import java.util.List;
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
            throw e;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Append tuple elements including names to this tuple.
     * Orientation stays the same.
     *
     * @param t Tuple with object elements and names to append
     */
    public void append(Tuple t) {
        objects.addAll(t.getObjects());
        names.addAll(t.getNames());
    }
}
