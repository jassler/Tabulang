package de.hskempten.tabulang.items;

public interface AnyTermAST {
    TermTypeAST getTermTypeAST();
}

enum TermTypeAST {
    AGGREGATION, AVERAGE, COUNT, DIRECTIONAL, DISTINCT,
    FILTER, FLIP, FUNC, FUNCALL, FUNDEF, IDENTIFIER, INDEXED,
    INTERSECT, LOOP, MARKED, NULL, OPERATOR, ORDINAL,
    TERM, TUPEL, UNITE
}
