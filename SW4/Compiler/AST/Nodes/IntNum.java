package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.IntType;
import AST.Types.Type;
import AST.Visitor;

public class IntNum extends Node {
    private int value;
    private Type type;

    public IntNum(int value){
        this.value = value;
        this.type = new IntType();
    }

    public int getValue() {
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
        IntNum intNum = (IntNum) o;
        return value == intNum.value && type.equals(intNum.type);
    }
}


