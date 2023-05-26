package AST.Types;

public abstract class Type {
    public abstract boolean isAssignable(Type other);
    public abstract boolean isEqual(Type other);
    public abstract Type getResultType(String operator, Type other);
}
