package AST;

import AST.Nodes.*;

public interface Visitor {
    void visit(AssignmentOp node);
    void visit(ComparisonOp node);
    void visit(Block node);
    void visit(Bool node);
    void visit(BoolDcl node);
    void visit(ArithmeticOp node);
    void visit(FloatDcl node);
    void visit(FloatNum node);
    void visit(Id node);
    void visit(IfStmt node);
    void visit(IfElseStmt node);
    void visit(IntDcl node);
    void visit(IntNum node);
    void visit(NegationOp node);
    void visit(Prog node);
    void visit(PointerDcl node);
    void visit(WhileLoop node);
    void visit(Procedure node);
    void visit(ProcedureDcl node);
}
