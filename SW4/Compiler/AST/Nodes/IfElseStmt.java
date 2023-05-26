package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.Type;
import AST.Visitor;

public class IfElseStmt extends Node {

    private Node condition;

    public IfElseStmt(Node condition, Node left, Node right){
        this.condition = condition;
        this.left = left;
        this.right = right;
    }

    public void accept(Visitor v){
        v.visit(this);
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        return null;
    }

    public Node getCondition() {
        return condition;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IfElseStmt ifElse = (IfElseStmt) o;
        return condition.equals(ifElse.condition) && left.equals(ifElse.left) && right.equals(ifElse.right);
    }
   
}
