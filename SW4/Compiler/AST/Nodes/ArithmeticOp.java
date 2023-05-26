package AST.Nodes;

import AST.OperationSet;
import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.Type;
import AST.Visitor;

public class ArithmeticOp extends Node {
    private String operation;
    private Type type;

    public ArithmeticOp(Node left, String operation, Node right){
        this.left = left;
        this.operation = operation;
        this.right = right;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        if ((this.type == null)) {
            Type leftType = left.getType(symbolTable);
            Type rightType = right.getType(symbolTable);
            setType(leftType.getResultType(operation, rightType));
        }
        return this.type;
    }

    public String getOperator() {
        return operation;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArithmeticOp computing = (ArithmeticOp) o;
        return operation.equals(computing.operation) && left.equals(computing.left) && right.equals(computing.right);
    }
}
