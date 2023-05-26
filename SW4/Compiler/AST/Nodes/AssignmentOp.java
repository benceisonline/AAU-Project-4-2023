package AST.Nodes;

import AST.OperationSet;
import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.Type;
import AST.Visitor;

import java.util.Objects;

public class AssignmentOp extends Node {
    private String name;
    private String compAssOp;

    public AssignmentOp(String name, Node left, Node right){
        this.name = name;
        this.left = left;
        this.right = right;
        this.compAssOp = null;
    }

    public AssignmentOp(String name, Node left, Node right, String compAssOp){
        this.name = name;
        this.left = left;
        this.right = right;

        if (compAssOp.equals(OperationSet.PLUS.getOp())){
            this.compAssOp = OperationSet.COMPASSPLUS.getOp();
        }
        else if (compAssOp.equals("-")){
            this.compAssOp = OperationSet.COMPASSMINUS.getOp();
        }
    }

    public void accept(Visitor v){
        v.visit(this);
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        return null;
    }

    public String getVariable() {
        return name;
    }

    public String getCompAssOp() {
        return compAssOp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssignmentOp assignmentOp = (AssignmentOp) o;
        if (compAssOp != null) {
            return name.equals(assignmentOp.name) && left.equals(assignmentOp.left) && right.equals(assignmentOp.right) && compAssOp.equals(assignmentOp.compAssOp);
        }
        return name.equals(assignmentOp.name) && left.equals(assignmentOp.left) && right.equals(assignmentOp.right);
    }
}
