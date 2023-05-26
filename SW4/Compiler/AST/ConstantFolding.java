package AST;

import AST.Nodes.*;

public class ConstantFolding implements Visitor {

    @Override
    public void visit(AssignmentOp node) {
        node.getLeft().setParent(node);
        node.getLeft().accept(this);
        node.getRight().setParent(node);
        node.getRight().accept(this);
    }

    @Override
    public void visit(ComparisonOp node) {
        node.getLeft().setParent(node);
        node.getLeft().accept(this);
        node.getRight().setParent(node);
        node.getRight().accept(this);

        boolean foldedValue = false;
        String operator = node.getOperator();

        if (node.getLeft() instanceof IntNum && node.getRight() instanceof IntNum) {
            int leftValueInt = ((IntNum) node.getLeft()).getValue();
            int rightValueInt = ((IntNum) node.getRight()).getValue();

            if (operator.equals(OperationSet.LESSTHAN.getOp())) {
                foldedValue = leftValueInt < rightValueInt;
            } else if (operator.equals(OperationSet.GREATERTHAN.getOp())) {
                foldedValue = leftValueInt > rightValueInt;
            } else if (operator.equals(OperationSet.GREATEREQUAL.getOp())) {
                foldedValue = leftValueInt >= rightValueInt;
            } else if (operator.equals(OperationSet.LESSEQUAL.getOp())) {
                foldedValue = leftValueInt <= rightValueInt;
            } else if (operator.equals(OperationSet.EQUAL.getOp())) {
                foldedValue = leftValueInt == rightValueInt;
            } else if (operator.equals(OperationSet.NOTEQUAL.getOp())) {
                foldedValue = leftValueInt != rightValueInt;
            } else {
                return;
            }

            replaceParentChild(node.getParent(), node, new Bool(foldedValue));

        } else if (node.getLeft() instanceof FloatNum && node.getRight() instanceof FloatNum) {
            float leftValueFloat = ((FloatNum) node.getLeft()).getValue();
            float rightValueFloat = ((FloatNum) node.getRight()).getValue();

            if (operator.equals(OperationSet.LESSTHAN.getOp())) {
                foldedValue = leftValueFloat < rightValueFloat;
            } else if (operator.equals(OperationSet.GREATERTHAN.getOp())) {
                foldedValue = leftValueFloat > rightValueFloat;
            } else if (operator.equals(OperationSet.GREATEREQUAL.getOp())) {
                foldedValue = leftValueFloat >= rightValueFloat;
            } else if (operator.equals(OperationSet.LESSEQUAL.getOp())) {
                foldedValue = leftValueFloat <= rightValueFloat;
            } else if (operator.equals(OperationSet.EQUAL.getOp())) {
                foldedValue = leftValueFloat == rightValueFloat;
            } else if (operator.equals(OperationSet.NOTEQUAL.getOp())) {
                foldedValue = leftValueFloat != rightValueFloat;
            } else {
                return;
            }

            replaceParentChild(node.getParent(), node, new Bool(foldedValue));

        } else if (node.getLeft() instanceof Bool && node.getRight() instanceof Bool) {
            boolean leftValueBool = ((Bool) node.getLeft()).getValue();
            boolean rightValueBool = ((Bool) node.getRight()).getValue();

            if (operator.equals(OperationSet.EQUAL.getOp())) {
                foldedValue = leftValueBool == rightValueBool;
            } else if (operator.equals(OperationSet.NOTEQUAL.getOp())) {
                foldedValue = leftValueBool != rightValueBool;
            } else if (operator.equals(OperationSet.OR.getOp())) {
                foldedValue = leftValueBool || rightValueBool;
            } else if (operator.equals(OperationSet.AND.getOp())) {
                foldedValue = leftValueBool && rightValueBool;
            } else {
                return;
            }

            replaceParentChild(node.getParent(), node, new Bool(foldedValue));
        }
    }

    @Override
    public void visit(Block node) {
        for (Node n : node.getChildren()) {
            n.accept(this);
        }
    }

    @Override
    public void visit(Bool node) {

    }

    @Override
    public void visit(BoolDcl node) {

    }

    @Override
    public void visit(ArithmeticOp node) {
        node.getLeft().setParent(node);
        node.getLeft().accept(this);
        node.getRight().setParent(node);
        node.getRight().accept(this);

        String operator = node.getOperator();

        if (node.getLeft() instanceof IntNum && node.getRight() instanceof IntNum) {
            int leftValue = ((IntNum) node.getLeft()).getValue();
            int rightValue = ((IntNum) node.getRight()).getValue();

            int foldedValue;

            if (operator.equals(OperationSet.PLUS.getOp())) {
                foldedValue = leftValue + rightValue;
            } else if (operator.equals(OperationSet.MINUS.getOp())) {
                foldedValue = leftValue - rightValue;
            } else if (operator.equals(OperationSet.MULTIPLY.getOp())) {
                foldedValue = leftValue * rightValue;
            } else if (operator.equals(OperationSet.DIVIDE.getOp())) {
                foldedValue = leftValue / rightValue;
            } else {
                return;
            }

            replaceParentChild(node.getParent(), node, new IntNum(foldedValue));
        }
        if (node.getLeft() instanceof FloatNum && node.getRight() instanceof FloatNum) {
            float leftValue = ((FloatNum) node.getLeft()).getValue();
            float rightValue = ((FloatNum) node.getRight()).getValue();

            float foldedValue;

            if (operator.equals(OperationSet.PLUS.getOp())) {
                foldedValue = leftValue + rightValue;
            } else if (operator.equals(OperationSet.MINUS.getOp())) {
                foldedValue = leftValue - rightValue;
            } else if (operator.equals(OperationSet.MULTIPLY.getOp())) {
                foldedValue = leftValue * rightValue;
            } else if (operator.equals(OperationSet.DIVIDE.getOp())) {
                foldedValue = leftValue / rightValue;
            } else {
                return;
            }

            replaceParentChild(node.getParent(), node, new FloatNum(foldedValue));
        }
    }

    @Override
    public void visit(FloatDcl node) {

    }

    @Override
    public void visit(FloatNum node) {

    }

    @Override
    public void visit(Id node) {

    }

    @Override
    public void visit(IfStmt node) {
        node.getLeft().setParent(node);
        node.getLeft().accept(this);
        node.getRight().setParent(node);
        node.getRight().accept(this);
    }

    @Override
    public void visit(IfElseStmt node) {
        node.getCondition().setParent(node);
        node.getCondition().accept(this);
        node.getLeft().setParent(node);
        node.getLeft().accept(this);
        node.getRight().setParent(node);
        node.getRight().accept(this);
    }

    @Override
    public void visit(IntDcl node) {

    }

    @Override
    public void visit(IntNum node) {

    }

    @Override
    public void visit(NegationOp node) {
        if (node.getLeft() instanceof Bool) {
            boolean leftValue = ((Bool) node.getLeft()).getValue();
            replaceParentChild(node.getParent(), node, new Bool(!leftValue));
        }
    }

    @Override
    public void visit(Prog node) {
        for (Node n : node.getChildren()) {
            n.accept(this);
        }
    }

    @Override
    public void visit(WhileLoop node) {
        node.getLeft().setParent(node);
        node.getLeft().accept(this);
        node.getRight().setParent(node);
        node.getRight().accept(this);
    }

    @Override
    public void visit(PointerDcl node) {

    }

    @Override
    public void visit(Procedure node) {

    }

    @Override
    public void visit(ProcedureDcl node) {
        node.getRight().accept(this);
    }

    private void replaceParentChild(Node parent, Node node, Node newChild) {
        if (parent.getLeft().equals(node)) {
            parent.setLeft(newChild);
        } else {
            parent.setRight(newChild);
        }
    }
}
