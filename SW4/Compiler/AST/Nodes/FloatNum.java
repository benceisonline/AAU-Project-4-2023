package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.FloatType;
import AST.Types.Type;
import AST.Visitor;

public class FloatNum extends Node {
    private float value;
    private Type type;

    public FloatNum(float value){
        this.value = value;
        this.type = new FloatType();
    }

    public float getValue() {
        return value;
    }

    public void accept(Visitor v){
        v.visit(this);
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        return this.type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FloatNum floatNum = (FloatNum) o;
        return value == floatNum.value && type.equals(floatNum.type);
    }
}