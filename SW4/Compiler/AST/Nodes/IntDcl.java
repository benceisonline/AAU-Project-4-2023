package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.IntType;
import AST.Types.Type;
import AST.Visitor;

import java.util.Objects;

public class IntDcl extends Node {
    private String id;
    private Type type;

    public IntDcl(String id) {
        this.id = id;
        this.type = new IntType();
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
        IntDcl intDcl = (IntDcl) o;
        return id.equals(intDcl.id) && type.equals(intDcl.type);
    }
}
