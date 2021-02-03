package de.hskempten.tabulang.datatypes;

/**
 * Basic tuple operation functionality. See {@link Tuple} and {@link Table} for specific implementation.
 *
 * @param <E> Internal object that can be annotated
 */
public interface TupleOperation<E extends Styleable> extends Cloneable, Iterable<E> {

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
    int getSize();

    TupleOperation<E> clone();
}
