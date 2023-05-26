package AST.Nodes;

import AST.OperationSet;
import AST.SymbolTableFilling.Symbol;
import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.Type;
import AST.Visitor;

import java.util.Objects;

public class Id extends Node {
    private String prefix;
    private String id;
    private Type type;
    private String address;

    public Id(String id){
        this.prefix = " ";
        this.id = id;
    }

    public Id(String prefix, String id){
        this.prefix = prefix;
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public void accept(Visitor v){
        v.visit(this);
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        if (this.type == null) {
            Symbol symbol = symbolTable.lookup(id);
            if (prefix != null) {
                if (prefix.equals(OperationSet.HASHTAG.getOp()) || prefix.equals(OperationSet.MULTIPLY.getOp())) {
                    setType(symbol.getPointAtType());
                }
            }
            setType(symbol.getType());
        }
        return this.type;
    }

    public String getName() {
        return id;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Id idNode = (Id) o;
        if (prefix != null) {
            return prefix.equals(idNode.prefix) && id.equals(idNode.id);
        }
        return id.equals(idNode.id);
    }
}