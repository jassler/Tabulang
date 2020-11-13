package de.hskempten.tabulang.exampleTest.nonTerminal;

import de.hskempten.tabulang.exampleTest.Interpretation;
import de.hskempten.tabulang.exampleTest.LangItem;

public interface NonTerminalItem extends LangItem {

    public void traverse(Interpretation i);
}
