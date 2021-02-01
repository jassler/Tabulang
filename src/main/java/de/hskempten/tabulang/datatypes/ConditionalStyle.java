package de.hskempten.tabulang.datatypes;

import java.util.function.Predicate;

/**
 * Apply style to object that satisfies a certain condition.
 * TODO: Not yet supported
 *
 * @param <E> Data object type that is passed to the predicate
 */
public class ConditionalStyle<E> extends Style {

    private final Predicate<E> p;
    public ConditionalStyle(Predicate<E> p) {
        this.p = p;
    }

    public boolean isStyleApplied(E data) {
        return p.test(data);
    }

}
