package de.hskempten.tabulang.arithmetic;

import de.hskempten.tabulang.exampleTest.Interpretable;
import de.hskempten.tabulang.exampleTest.Interpretation;

public class AssignmentItem implements Interpretable {
    @Override
    public void interpret(Interpretation i) {
        //AssignmentItem: Identifier := Term
        i.getEnvironment().put(i.getOperandStack().pop(), i.getOperandStack().pop());
        i.getTypeStack().pop();
        i.getTypeStack().pop();
    }
}
