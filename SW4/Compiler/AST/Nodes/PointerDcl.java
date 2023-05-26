package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.PointerType;
import AST.Types.Type;
import AST.Visitor;

public class PointerDcl extends Node {
    private String id;
    private Type type;

    public PointerDcl(String id) {
        this.id = id;
        this.type = new PointerType();
    }

    public void accept(Visitor v){
        v.visit(this);
    }

    public String getId() {
        return id;
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointerDcl pointer = (PointerDcl) o;
        return id.equals(pointer.id) && type.equals(pointer.type);
    }
}
