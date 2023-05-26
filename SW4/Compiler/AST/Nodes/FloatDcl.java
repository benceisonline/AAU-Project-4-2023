package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.FloatType;
import AST.Types.Type;
import AST.Visitor;

import java.util.Objects;

public class FloatDcl extends Node {
    private String id;
    private Type type;

    public FloatDcl(String id) {
        this.id = id;
        this.type = new FloatType();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FloatDcl floatDcl = (FloatDcl) o;
        return id.equals(floatDcl.id) && type.equals(floatDcl.type);
    }
}
