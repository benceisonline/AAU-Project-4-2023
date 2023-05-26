import AST.Nodes.*;
import AST.Visitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class VisitorTest implements Visitor {
    private boolean isProgVisited = false;
    private boolean isAssignmentOpVisited = false;
    private boolean isComparisonOpVisited = false;
    private boolean isBlockVisited = false;
    private boolean isBoolVisited = false;
    private boolean isBoolDclVisited = false;
    private boolean isArithmeticOpVisited = false;
    private boolean isFloatDclVisited = false;
    private boolean isFloatNumVisited = false;
    private boolean isIdVisited = false;
    private boolean isIfStmtVisited = false;
    private boolean isIfElseStmtVisited = false;
    private boolean isIntDclVisited = false;
    private boolean isIntNumVisited = false;
    private boolean isNegationOpVisited = false;
    private boolean isWhileLoopVisited = false;
    private boolean isPointerDclVisited = false;
    private boolean isProcedureVisited = false;
    private boolean isProcedureDclVisited = false;

    private void resetBoolValues() {
        isProgVisited = false;
        isAssignmentOpVisited = false;
        isComparisonOpVisited = false;
        isBlockVisited = false;
        isBoolVisited = false;
        isBoolDclVisited = false;
        isArithmeticOpVisited = false;
        isFloatDclVisited = false;
        isFloatNumVisited = false;
        isIdVisited = false;
        isIfStmtVisited = false;
        isIfElseStmtVisited = false;
        isIntDclVisited = false;
        isIntNumVisited = false;
        isNegationOpVisited = false;
        isWhileLoopVisited = false;
        isPointerDclVisited = false;
        isProcedureVisited = false;
        isProcedureDclVisited = false;
    }

    @BeforeEach
    void resetTests() {
        resetBoolValues();
    }

    @Test
    void VisitorProgTest() {
        Prog prog = new Prog();
        prog.accept(this);

        assertTrue(isProgVisited);
    }

    @Test
    void VisitorArithmeticOpTest() {
        ArithmeticOp arithmeticOp = new ArithmeticOp(null, null, null);
        arithmeticOp.accept(this);

        assertTrue(isArithmeticOpVisited);
    }

    @Test
    void VisitorAssignmentOpTest() {
        AssignmentOp assignmentOp = new AssignmentOp(null, null, null);
        assignmentOp.accept(this);

        assertTrue(isAssignmentOpVisited);
    }

    @Test
    void VisitorComparisonOpTest() {
        ComparisonOp comparisonOp = new ComparisonOp(null, null, null);
        comparisonOp.accept(this);

        assertTrue(isComparisonOpVisited);
    }

    @Test
    void VisitorBlockTest() {
        Block block = new Block();
        block.accept(this);

        assertTrue(isBlockVisited);
    }

    @Test
    void VisitorBoolTest() {
        Bool bool = new Bool(true);
        bool.accept(this);

        assertTrue(isBoolVisited);
    }

    @Test
    void VisitorBoolDclTest() {
        BoolDcl boolDcl = new BoolDcl(null);
        boolDcl.accept(this);

        assertTrue(isBoolDclVisited);
    }

    @Test
    void VisitorFloatDclTest() {
        FloatDcl floatDcl = new FloatDcl(null);
        floatDcl.accept(this);

        assertTrue(isFloatDclVisited);
    }

    @Test
    void VisitorFloatNumTest() {
        FloatNum floatNum = new FloatNum(2.0F);
        floatNum.accept(this);

        assertTrue(isFloatNumVisited);
    }

    @Test
    void VisitorIdTest() {
        Id id = new Id(null);
        id.accept(this);

        assertTrue(isIdVisited);
    }

    @Test
    void VisitorIfStmtTest() {
        IfStmt ifStmt = new IfStmt(null, null);
        ifStmt.accept(this);

        assertTrue(isIfStmtVisited);
    }

    @Test
    void VisitorIfElseStmtTest() {
        IfElseStmt ifElseStmt = new IfElseStmt(null, null, null);
        ifElseStmt.accept(this);

        assertTrue(isIfElseStmtVisited);
    }

    @Test
    void VisitorIntNumTest() {
        IntNum intNum = new IntNum(2);
        intNum.accept(this);

        assertTrue(isIntNumVisited);
    }

    @Test
    void VisitorIntDclTest() {
        IntDcl intDcl = new IntDcl(null);
        intDcl.accept(this);

        assertTrue(isIntDclVisited);
    }

    @Test
    void VisitorNegationOpTest() {
        NegationOp negationOp = new NegationOp(null);
        negationOp.accept(this);

        assertTrue(isNegationOpVisited);
    }

    @Test
    void VisitorWhileLoopTest() {
        WhileLoop whileLoop = new WhileLoop(null, null);
        whileLoop.accept(this);

        assertTrue(isWhileLoopVisited);
    }

    @Test
    void VisitorPointerDclTest() {
        PointerDcl pointerDcl = new PointerDcl("test");
        pointerDcl.accept(this);

        assertTrue(isPointerDclVisited);
    }

    @Test
    void VisitorProcedureTest() {
        Procedure procedure = new Procedure("test", new Id("test"));
        procedure.accept(this);

        assertTrue(isProcedureVisited);
    }

    @Test
    void VisitorProcedureDclTest() {
        ProcedureDcl procedureDcl = new ProcedureDcl("test", new Block());
        procedureDcl.accept(this);

        assertTrue(isProcedureDclVisited);
    }

    @Override
    public void visit(AssignmentOp node) {
        isAssignmentOpVisited = true;
    }

    @Override
    public void visit(ComparisonOp node) {
        isComparisonOpVisited = true;
    }

    @Override
    public void visit(Block node) {
        isBlockVisited = true;
    }

    @Override
    public void visit(Bool node) {
        isBoolVisited = true;
    }

    @Override
    public void visit(BoolDcl node) {
        isBoolDclVisited = true;
    }

    @Override
    public void visit(ArithmeticOp node) {
        isArithmeticOpVisited = true;
    }

    @Override
    public void visit(FloatDcl node) {
        isFloatDclVisited = true;
    }

    @Override
    public void visit(FloatNum node) {
        isFloatNumVisited = true;
    }

    @Override
    public void visit(Id node) {
        isIdVisited = true;
    }

    @Override
    public void visit(IfStmt node) {
        isIfStmtVisited = true;
    }

    @Override
    public void visit(IfElseStmt node) {
        isIfElseStmtVisited = true;
    }

    @Override
    public void visit(IntDcl node) {
        isIntDclVisited = true;
    }

    @Override
    public void visit(IntNum node) {
        isIntNumVisited = true;
    }

    @Override
    public void visit(NegationOp node) {
        isNegationOpVisited = true;
    }

    @Override
    public void visit(Prog node) {
        isProgVisited = true;
    }

    @Override
    public void visit(WhileLoop node) {
        isWhileLoopVisited = true;
    }

    @Override
    public void visit(PointerDcl node) {
        isPointerDclVisited = true;
    }

    @Override
    public void visit(Procedure node) {
        isProcedureVisited = true;
    }

    @Override
    public void visit(ProcedureDcl node) {
        isProcedureDclVisited = true;
    }
}
