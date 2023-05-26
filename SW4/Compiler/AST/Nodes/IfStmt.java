package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.Type;
import AST.Visitor;

public class IfStmt extends Node {

    public IfStmt(Node left, Node right){
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IfStmt anIf = (IfStmt) o;
        return left.equals(anIf.left) && right.equals(anIf.right);
    }
}
