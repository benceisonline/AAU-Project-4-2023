package AST.Types;

import AST.OperationSet;

public class IntType extends Type{
    public static final IntType INSTANCE = new IntType();

    public IntType() {}

    public static IntType getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isAssignable(Type other) {
        // An int is assignable to another int
        return other instanceof IntType;
    }

    @Override
    public boolean isEqual(Type other) {
        // An int is equal to another int
        return other instanceof IntType;
    }

    @Override
    public Type getResultType(String operator, Type other) {
        if (other instanceof IntType) {
            // Arithmetic operators return an int result when both operands are ints
            if (operator.equals(OperationSet.PLUS.getOp()) || operator.equals(OperationSet.MINUS.getOp())
                || operator.equals(OperationSet.MULTIPLY.getOp()) || operator.equals(OperationSet.DIVIDE.getOp())) {
                return IntType.INSTANCE;
            } else if (operator.equals(OperationSet.LESSTHAN.getOp()) || operator.equals(OperationSet.LESSEQUAL.getOp())
                || operator.equals(OperationSet.GREATERTHAN.getOp()) || operator.equals(OperationSet.GREATEREQUAL.getOp())
                || operator.equals(OperationSet.EQUAL.getOp()) || operator.equals(OperationSet.NOTEQUAL.getOp())) {
                return BooleanType.INSTANCE;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntType intType = (IntType) o;
        return this.isEqual(intType);
    }
}
