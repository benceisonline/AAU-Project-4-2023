package AST.Types;

public class ProcedureType extends Type {
    public static final ProcedureType INSTANCE = new ProcedureType();

    @Override
    public boolean isAssignable(Type other) {
        return false;
    }

    @Override
    public boolean isEqual(Type other) {
        return other instanceof ProcedureType;
    }

    @Override
    public Type getResultType(String operator, Type other) {
        return null;
    }
}
