package de.hskempten.tabulang.arithmetic;

import de.hskempten.tabulang.exampleTest.Interpretation;

import java.math.BigDecimal;

import static java.lang.Float.parseFloat;

public class SubtractItem implements ArithmeticIF {
    @Override
    public void evaluate(Interpretation i) {
        String secondOp = i.getTypeStack().pop();
        String firstOp = i.getTypeStack().pop();
        Number secondNumber;
        Number firstNumber;
        //TODO Eval wenn beides Tabellen sind
        if(false){//Beides Tabellen
        ;
        } else {
            if(secondOp == "Identifier"){
                secondNumber = parseFloat(i.getEnvironment().get(i.getOperandStack().pop()).toString());
            } else {
                secondNumber = parseFloat(i.getOperandStack().pop());
            }
            if(firstOp == "Identifier"){
                firstNumber = parseFloat(i.getEnvironment().get(i.getOperandStack().pop()).toString());
            } else {
                firstNumber = parseFloat(i.getOperandStack().pop());
            }
            BigDecimal sum = new BigDecimal(firstNumber.floatValue()).subtract(new BigDecimal(secondNumber.floatValue()));
            System.out.println("Subtraktion beider Zahlen: " + sum);
            i.getOperandStack().push(sum.toString());
            i.getTypeStack().push("Number");
        }
    }
}
