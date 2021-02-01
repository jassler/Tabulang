package de.hskempten.tabulang.datatypes;

import java.util.Objects;

public class InternalDataObject extends InternalObject {

    private Object data;

    public InternalDataObject(Object data) {
        this.data = data;
    }

    public Object getObject() {
        return data;
    }

    public void setObject(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InternalDataObject that = (InternalDataObject) o;
        return data.equals(that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    /**
     * Turn a variable amount of values into an array of InternalDataObject objects.
     *
     * @param values Objects to be converted into InternalString objects
     * @return InternalString array with converted values
     */
    public static InternalDataObject[] objToArray(Object... values) {
        InternalDataObject[] converted = new InternalDataObject[values.length];

        for (int i = 0; i < values.length; i++) {
            converted[i] = new InternalDataObject(values[i]);
        }

        return converted;
    }
}
