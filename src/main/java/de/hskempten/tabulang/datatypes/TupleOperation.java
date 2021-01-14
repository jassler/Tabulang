package de.hskempten.tabulang.datatypes;

public interface TupleOperation<E> extends Cloneable, Iterable<E> {

    E get(int index);
    InternalString getHeader(int index);

    boolean isHorizontal();
    void setHorizontal(boolean horizontal);

    void transpose();
    boolean isTransposed();

    TupleOperation<E> projection(int... elements);
    TupleOperation<E> projection(InternalString... elements);
    TupleOperation<E> projection();

    int getWidth();
    int getHeight();

    TupleOperation<E> clone();
}
