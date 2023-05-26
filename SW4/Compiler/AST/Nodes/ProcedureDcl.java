package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.ProcedureType;
import AST.Types.Type;
import AST.Visitor;

public class ProcedureDcl extends Node {
    private String id;
    private Type type;

    public ProcedureDcl(String id, Node block) {
        this.id = id;
        this.right = block;
        this.type = new ProcedureType();
    }

    public ProcedureDcl(String id, Node param, Node block) {
        this.id = id;
        this.left = param;
        this.right = block;
    }

    public void accept(Visitor v){
        v.visit(this);
    }

    public String getId() {
        return id;
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcedureDcl procedureDcl = (ProcedureDcl) o;
        return id.equals(procedureDcl.id);
    }
}
