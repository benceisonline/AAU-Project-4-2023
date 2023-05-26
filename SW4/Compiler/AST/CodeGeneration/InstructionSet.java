package AST.CodeGeneration;

public enum InstructionSet {
    ADC("Add with Carry", "ADC"),
    AND("Logical AND", "AND"),
    ASL("Arithmetic Shift Left", "ASL"),
    BCC("Branch if Carry Clear", "BCC"),
    BCS("Branch if Carry Set", "BCS"),
    BEQ("Branch if Equal", "BEQ"),
    BIT("Bit Test", "BIT"),
    BMI("Branch if Minus", "BMI"),
    BNE("Branch if Not Equal", "BNE"),
    BPL("Branch if Positive", "BPL"),
    BRK("Break", "BRK"),
    BVC("Branch if Overflow Clear", "BVC"),
    BVS("Branch if Overflow Set", "BVS"),
    CLC("Clear Carry Flag", "CLC"),
    CLD("Clear Decimal Mode", "CLD"),
    CLI("Clear Interrupt Disable", "CLI"),
    CLV("Clear Overflow Flag", "CLV"),
    CMP("Compare", "CMP"),
    CPX("Compare X Register", "CPX"),
    CPY("Compare Y Register", "CPY"),
    DEC("Decrement Memory", "DEC"),
    DEX("Decrement X Register", "DEX"),
    DEY("Decrement Y Register", "DEY"),
    EOR("Exclusive OR", "EOR"),
    INC("Increment Memory", "INC"),
    INX("Increment X Register", "INX"),
    INY("Increment Y Register", "INY"),
    JMP("Jump", "JMP"),
    JSR("Jump to Subroutine", "JSR"),
    LDA("Load Accumulator", "LDA"),
    LDX("Load X Register", "LDX"),
    LDY("Load Y Register", "LDY"),
    LSR("Logical Shift Right", "LSR"),
    NOP("No Operation", "NOP"),
    ORA("Logical Inclusive OR", "ORA"),
    PHA("Push Accumulator", "PHA"),
    PHP("Push Processor Status", "PHP"),
    PLA("Pull Accumulator", "PLA"),
    PLP("Pull Processor Status", "PLP"),
    ROL("Rotate Left", "ROL"),
    ROR("Rotate Right", "ROR"),
    RTI("Return from Interrupt", "RTI"),
    RTS("Return from Subroutine", "RTS"),
    SBC("Subtract with Carry", "SBC"),
    SEC("Set Carry Flag", "SEC"),
    SED("Set Decimal Flag", "SED"),
    SEI("Set Interrupt Disable", "SEI"),
    STA("Store Accumulator", "STA"),
    STX("Store X Register", "STX"),
    STY("Store Y Register", "STY"),
    TAX("Transfer Accumulator to X", "TAX"),
    TAY("Transfer Accumulator to Y", "TAY"),
    TSX("Transfer Stack Pointer to X", "TSX"),
    TXA("Transfer X to Accumulator", "TXA"),
    TXS("Transfer X to Stack Pointer", "TXS"),
    TYA("Transfer Y to Accumulator", "TYA");

    private final String description;
    private final String instruction;

    InstructionSet(String description, String instruction) {
        this.description = description;
        this.instruction = instruction;
    }

    public String getDescription() {
        return description;
    }

    public String getInstruction() {
        return instruction;
    }

}
