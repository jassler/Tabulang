package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.astNodes.GroupNode;
import de.hskempten.tabulang.astNodes.IdentifierNode;
import de.hskempten.tabulang.astNodes.StatementNode;
import de.hskempten.tabulang.astNodes.TermNode;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class GroupWithoutFunctionCallNode extends GroupNode {

    public GroupWithoutFunctionCallNode(TermNode term) {
        super(term);
    }


    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object groupTerm = getTerm().evaluateNode(interpretation);
        //TODO groupmap not necessary, but for test purposed: shows the loop indices per group
        //buildGroupMap(interpretation, groupTerm);
        buildMapValueMap(interpretation, groupTerm);

        if(isLastIteration()){
            System.out.println();
            for(Map.Entry<Object, LinkedList<Object>> group : getMapValueInLoopX().entrySet()) {
                getResultList().addAll(group.getValue());
            }
        }
        setLoopCounter(getLoopCounter() + 1);
        return getResultList();
    }
}
