package de.hskempten.tabulang.datatypes;

import java.util.function.Predicate;

public class ConditionalStyle<E> extends Style {

    private final Predicate<E> p;

    public ConditionalStyle(Predicate<E> p) {
        this.p = p;
    }

    public boolean isStyleApplied(E data) {
        return p.test(data);
    }

}
