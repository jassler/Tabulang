package de.hskempten.tabulang.datatypes;

public interface TupleOperation<E> {

    boolean isHorizontal();

    void setHorizontal(boolean horizontal);

    void transpose();

    boolean isTransposed();

    E projection(int... elements);

    E projection(InternalString... elements);

    E projection();

    int getWidth();

    int getHeight();

}
