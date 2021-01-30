package de.hskempten.tabulang.datatypes;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InternalString extends InternalObject implements Comparable<InternalString> {
    private String string;

    public InternalString(String string) {
        super(null);
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    /**
     * Turn a variable amount of values into an array of InternalString objects.
     *
     * @param values Objects to be converted into InternalString objects
     * @return InternalString array with converted values
     */
    public static InternalString[] objToArray(Object... values) {
        InternalString[] result = new InternalString[values.length];

        for (int i = 0; i < values.length; i++) {
            if (values[i] == null)
                result[i] = null;
            else
                result[i] = new InternalString(values[i].toString());
        }

        return result;
    }

    /**
     * Turn a variable amount of values into an ArrayList of InternalString objects.
     *
     * @param values Objects to be converted into InternalString objects
     * @return InternalString ArrayList with converted values
     */
    public static ArrayList<InternalString> objToList(Object... values) {
        return Stream.of(values).map(
                v -> new InternalString(v.toString())
        ).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public String toString() {
        return string;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InternalString that = (InternalString) o;
        return string.equals(that.string);
    }

    @Override
    public int hashCode() {
        return Objects.hash(string);
    }

    @Override
    public int compareTo(InternalString o) {
        return string.compareTo(o.getString());
    }
}
