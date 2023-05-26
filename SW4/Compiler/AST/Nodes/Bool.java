package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.BooleanType;
import AST.Types.Type;
import AST.Visitor;

public class Bool extends Node {
    private boolean value;
    private Type type;

    public Bool(Boolean value){
        this.value = value;
        this.type = new BooleanType();
    }

    public void accept(Visitor v){
        v.visit(this);
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        return this.type;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bool bool = (Bool) o;
        return value == bool.value && type.equals(bool.type);
    }
}
