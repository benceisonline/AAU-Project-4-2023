package AST.SymbolTableFilling;

import AST.Types.Type;

public class Symbol {
    private String name;
    private Type type;
    private Type pointAtType;
    private int scopeLevel;
    private int memoryAddress;

    public Symbol(String name, Type type, int scopeLevel) {
        this.name = name;
        this.type = type;
        this.scopeLevel = scopeLevel;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public int getScopeLevel() {
        return scopeLevel;
    }

    public int getMemoryAddress() {
        return memoryAddress;
    }

    public void setMemoryAddress(int memoryAddress) {
        this.memoryAddress = memoryAddress;
    }

    public Type getPointAtType() {
        return pointAtType;
    }

    public void setPointAtType(Type pointAtType) {
        this.pointAtType = pointAtType;
    }
}
