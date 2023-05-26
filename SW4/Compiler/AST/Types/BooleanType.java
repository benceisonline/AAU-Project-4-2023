package AST.Types;

import AST.OperationSet;

public class BooleanType extends Type{
    public static final BooleanType INSTANCE = new BooleanType();

    public BooleanType() {
        // Make constructor private to prevent creation of multiple instances
    }
    @Override
    public boolean isAssignable(Type other) {
        // A boolean is assignable only to another boolean
        return other instanceof BooleanType;
    }

    @Override
    public boolean isEqual(Type other) {
        return other instanceof BooleanType;
    }

    @Override
    public Type getResultType(String operator, Type other) {
        if (operator.equals(OperationSet.EQUAL.getOp()) || operator.equals(OperationSet.NOTEQUAL.getOp()) ||
                operator.equals(OperationSet.LESSTHAN.getOp()) || operator.equals(OperationSet.GREATERTHAN.getOp()) ||
                operator.equals(OperationSet.LESSEQUAL.getOp()) || operator.equals(OperationSet.GREATEREQUAL.getOp())) {
            return BooleanType.INSTANCE;
        } else if (operator.equals(OperationSet.AND.getOp()) || operator.equals(OperationSet.OR.getOp())) {
            if (other instanceof BooleanType) {
                return BooleanType.INSTANCE;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooleanType BooleanType = (BooleanType) o;
        return this.isEqual(BooleanType);
    }
}