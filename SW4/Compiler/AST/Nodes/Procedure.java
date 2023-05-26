package AST.Nodes;

import AST.OperationSet;
import AST.SymbolTableFilling.Symbol;
import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.PointerType;
import AST.Types.ProcedureType;
import AST.Types.Type;
import AST.Visitor;

public class Procedure extends Node {
    private String id;
    private Type type;

    public Procedure(String id, Node procName, Node param) {
        this.id = id;
        this.left = procName;
        this.right = param;
    }

    public Procedure(String id, Node procName) {
        this.id = id;
        this.left = procName;
    }

    public String getId() {
        return id;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        return null;
    }
}
