package AST;

import AST.Nodes.*;

public class PrettyPrint implements Visitor {
    private StringBuilder sb = new StringBuilder();
    private int indentLevel = 0;

    private void indent() {
        this.indentLevel++;
    }

    private void unindent() {
        this.indentLevel--;
    }

    private void printIndent() {
        for (int i = 0; i < this.indentLevel; i++) {
            sb.append("    ");
        }
    }

    public String getResult() {
        return sb.toString();
    }

    @Override
    public void visit(AssignmentOp node) {
        if (node.getCompAssOp() == null) {
            switch (node.getLeft().getClass().getSimpleName()) {
                case "FloatDcl", "IntDcl", "BoolDcl, PointerDcl" -> {
                    node.getLeft().accept(this);
                    sb.append(" = ");
                    node.getRight().accept(this);
                }
                default -> {
                    sb.append("\n");
                    printIndent();
                    node.getLeft().accept(this);
                    sb.append(" = ");
                    node.getRight().accept(this);
                }
            }
        } else {
            if (node.getCompAssOp().equals(OperationSet.COMPASSPLUS.getOp())) {
                sb.append("\n");
                printIndent();
                node.getLeft().accept(this); // variable not declaration, but works because it is still child 1
                sb.append(" += ");
                node.getRight().accept(this);
            } else if (node.getCompAssOp().equals(OperationSet.COMPASSMINUS.getOp())) {
                sb.append("\n");
                printIndent();
                node.getLeft().accept(this); // variable not declaration, but works because it is still child 1
                sb.append(" -= ");
                node.getRight().accept(this);
            }
        }
    }

    @Override
    public void visit(ComparisonOp node) {
        node.getLeft().accept(this);
        sb.append(" " + node.getOperator() + " ");
        node.getRight().accept(this);
    }

    @Override
    public void visit(Block node) {
        for(Node n : node.getChildren()){
            indent();
            n.accept(this);
            unindent();
        }
    }

    @Override
    public void visit(Bool node) {
        sb.append(node.getValue());
    }

    @Override
    public void visit(BoolDcl node) {
        printIndent();
        sb.append("boolean " + node.getId());
    }

    @Override
    public void visit(ArithmeticOp node) {
        node.getLeft().accept(this);
        sb.append(" " + node.getOperator() + " ");
        node.getRight().accept(this);
    }

    @Override
    public void visit(FloatDcl node) {
        sb.append("\n");
        printIndent();
        sb.append("float " + node.getId());
    }

    @Override
    public void visit(FloatNum node) {
        sb.append(node.getValue());
    }

    @Override
    public void visit(Id node) {
        if (node.getPrefix() != null) {
            if (node.getPrefix().equals(OperationSet.MULTIPLY.getOp())) {
                sb.append("*");
            } else if (node.getPrefix().equals(OperationSet.HASHTAG.getOp())) {
                sb.append("#");
            } else if (node.getPrefix().equals(OperationSet.ADDRESS.getOp())) {
                sb.append("&");
            }
        }
        sb.append(node.getName());
    }

    @Override
    public void visit(IfStmt node) {
        sb.append("\n");
        printIndent();
        sb.append("if(");
        node.getLeft().accept(this);
        sb.append(")");
        node.getRight().accept(this);
    }

    @Override
    public void visit(IfElseStmt node) {
        sb.append("\n");
        printIndent();
        sb.append("if(");
        node.getCondition().accept(this);
        sb.append(")");
        node.getLeft().accept(this);
        sb.append("\n");
        printIndent();
        sb.append("else");
        node.getRight().accept(this);
    }

    @Override
    public void visit(IntDcl node) {
        sb.append("\n");
        printIndent();
        sb.append("int " + node.getId());
    }

    @Override
    public void visit(IntNum node) {
        sb.append(node.getValue());
    }

    @Override
    public void visit(NegationOp node) {
        sb.append("!(");
        node.getLeft().accept(this);
        sb.append(")");
    }

    @Override
    public void visit(Prog node) {
        for(Node n : node.getChildren()){
            n.accept(this);
        }
    }

    @Override
    public void visit(PointerDcl node) {
        printIndent();
        sb.append("pointer " + node.getId());
    }

    public void visit(WhileLoop node) {
        sb.append("\n");
        printIndent();
        sb.append("while (");
        node.getLeft().accept(this);
        sb.append(")");
        node.getRight().accept(this);
    }

    @Override
    public void visit(Procedure node) {
        sb.append("\n");
        node.getLeft().accept(this);
        sb.append("(");
        if (node.getRight() != null) {
            node.getRight().accept(this);
        }
        sb.append(")");
    }

    @Override
    public void visit(ProcedureDcl node) {
        printIndent();
        sb.append("\n");
        sb.append("proc " + node.getId());
        sb.append("(");
        if (node.getLeft() != null) {
            node.getLeft().accept(this);
        }
        sb.append(")");
        node.getRight().accept(this);
    }
}
