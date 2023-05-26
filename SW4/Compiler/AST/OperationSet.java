package AST;

public enum OperationSet {
    MINUS("-"),
    PLUS("+"),
    GREATERTHAN(">"),
    LESSTHAN("<"),
    LESSEQUAL("<="),
    GREATEREQUAL(">="),
    EQUAL("=="),
    NOTEQUAL("!="),
    AND("&&"),
    NOT("!"),
    OR("||"),
    COMPASSPLUS("+="),
    COMPASSMINUS("-="),
    HASHTAG("#"),
    MULTIPLY("*"),
    DIVIDE("/"),
    ADDRESS("&");

    private final String operation;

    OperationSet(String operation) {
        this.operation = operation;
    }

    public String getOp() {
        return operation;
    }

}
