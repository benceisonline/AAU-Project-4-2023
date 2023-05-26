package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.BooleanType;
import AST.Types.Type;
import AST.Visitor;

import java.util.Objects;

public class BoolDcl extends Node {
    private String id;
    private Type type;

    public BoolDcl(String id) {
        this.id = id;
        this.type = new BooleanType();
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
        BoolDcl boolDcl = (BoolDcl) o;
        return (id.equals(boolDcl.id)) && (type.equals(boolDcl.type));
    }
}