package de.hskempten.tabulang.standardBibliothek;

import de.hskempten.tabulang.astNodes.IdentifierNode;
import de.hskempten.tabulang.astNodes.StatementNode;

import java.util.ArrayList;

public class Print implements InternalFunction {

    @Override
    public Object compute(Object... args) {
        for(Object o : args)
            System.out.println(o);

        return null;
    }
}
