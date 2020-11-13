package de.hskempten.tabulang.helper;

import de.hskempten.tabulang.arithmetic.*;
import de.hskempten.tabulang.exampleTest.Interpretation;

public class CreateArithmeticItem {
    private static CreateArithmeticItem instance;

    public CreateArithmeticItem() {
    }

    static {
        instance = new CreateArithmeticItem();
    }

    public static CreateArithmeticItem getInstance() {
        return instance;
    }

    public void createCorrespondingItem(Interpretation i){
        switch (i.getOperatorStack().pop()) {
            case "+":
                //AddItem p = new AddItem(i.getSTACK().pop(), i.getSTACK().pop());
                AddItem addItem = new AddItem();
                addItem.interpret(i);
                break;
            case "-":
                SubtractItem subtractItem = new SubtractItem();
                subtractItem.evaluate(i);
                break;
            case "*":
                MultiplyItem multiplyItem = new MultiplyItem();
                multiplyItem.evaluate(i);
                break;
            case "/":
                DivideItem divideItem = new DivideItem();
                divideItem.evaluate(i);
                break;
            case "div":
                DivItem divItem = new DivItem();
                divItem.evaluate(i);
                break;
            case "mod":
                ModItem modItem = new ModItem();
                modItem.evaluate(i);
                break;
            case "^":
                PowItem powItem = new PowItem();
                powItem.evaluate(i);
                break;
            case ":=":
                AssignmentItem assignmentItem = new AssignmentItem();
                assignmentItem.interpret(i);
            default:
                System.out.println("Fehler");
                break;
        }
    }
}
