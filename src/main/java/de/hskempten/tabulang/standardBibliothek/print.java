package de.hskempten.tabulang.standardBibliothek;

import de.hskempten.tabulang.astNodes.IdentifierNode;
import de.hskempten.tabulang.astNodes.StatementNode;
import de.hskempten.tabulang.datatypes.InternalFunction;

import java.util.ArrayList;

public class print extends InternalFunction {
    public print(ArrayList<IdentifierNode> parameters, ArrayList<StatementNode> statements) {
        super(parameters, statements);
    }
}
