package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Identifier;
import de.hskempten.tabulang.datatypes.exceptions.VariableNotDeclaredException;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class ReturnNode extends StatementNode{
    private Node node;

    public ReturnNode(Node node) {
        super();
        this.node = node;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object o = node.evaluateNode(interpretation);
        if(o instanceof Identifier){
           Interpretation found = interpretation.findIdentifier((Identifier) o);
           if(found == null){
               throw new VariableNotDeclaredException(((Identifier) o).getIdentifierName());
           } else {
               o = found.getEnvironment().get(((Identifier) o).getIdentifierName());
           }
        }
        //TODO Zeile 36 um eine Abbruchbedingung zu haben falls nach einem Return Statement weitere Statements kommen würden
        //siehe FunctionCallNode Zeile 63
        //EDIT 13.12: getParent in Zeile 38 wshl nichtmehr nötig
        interpretation.getEnvironment().put("return", o);
        //interpretation.getParent().getEnvironment().put("return", o);
        return o;
    }
}
