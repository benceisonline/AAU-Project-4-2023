package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.Type;
import AST.Visitor;

import java.util.ArrayList;
import java.util.Objects;

public class Block extends Node {
    private ArrayList<Node> children;

    public Block() {
        this.children = new ArrayList<>();
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    public void accept(Visitor v){
        v.visit(this);
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return Objects.equals(children, block.children);
    }
}
