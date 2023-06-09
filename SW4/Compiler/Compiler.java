/* Compiler.java */
/* Generated By:JavaCC: Do not edit this line. Compiler.java */
import AST.*;
import AST.CodeGeneration.CodeGenerator;
import AST.Nodes.*;
import AST.Types.*;
import AST.SymbolTableFilling.SymbolTableFilling;import AST.Types.ProcedureType;
import java.util.ArrayList;

public class Compiler implements CompilerConstants {
    public static void main(String[] args) {
        try {
            Compiler compiler = new Compiler(new java.io.FileReader("Compiler/code.txt"));
            Node prog = compiler.Prog();

            PrettyPrint prettyPrint = new PrettyPrint();
            ArrayList<SymbolTableFilling> symbolTableFillings = new ArrayList<>();
            SymbolTableFilling symbolTableFilling = new SymbolTableFilling(symbolTableFillings , 0);
            TypeChecking typeChecking = new TypeChecking(symbolTableFillings);
            ConstantFolding constantFolding = new ConstantFolding();
            CodeGenerator codeGenerator = new CodeGenerator(symbolTableFillings);

            prog.accept(prettyPrint);
            System.out.println(prettyPrint.getResult());
            System.out.println();
            System.out.println("Syntax is good");
            prog.accept(symbolTableFilling);
            System.out.println("Symbol table is good");
            prog.accept(typeChecking);
            System.out.println("Type check is good");
            prog.accept(constantFolding);
            System.out.println("Constant folding is good");
            prog.accept(codeGenerator);
            System.out.println("Code generator is good");

            codeGenerator.generateCode();

        } catch (Throwable e) {
            // Catching Throwable is ugly but JavaCC throws Error objects!
            System.out.println("Syntax error: " + e.getMessage());
        }
    }

  final public Node Prog() throws ParseException {Prog prog = new Prog();
 Node stmt = null;
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INTDCL:
      case FLOATDCL:
      case POINTERDCL:
      case IF:
      case BOOLDCL:
      case HASHTAG:
      case WHILE:
      case PROCEDURE:
      case ID:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      stmt = Stmt();
prog.addChild(stmt);
    }
    jj_consume_token(END_OF_FILE);
    jj_consume_token(END_OF_LINE);
{if ("" != null) return prog;}
    throw new Error("Missing return statement in function");
}

  final public Node Stmt() throws ParseException {Node node;
    Node expr = null;
    Node proc = null;
    Token t;
    Token prefix = null;
    Token prefix2 = null;
    Token tAddress = null;
    Token compAssOp = null;
    Id id;
    AssignmentOp assignmentOp;
    if (jj_2_1(2)) {
      node = Assignment();
{if ("" != null) return node;}
    } else {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INTDCL:
      case FLOATDCL:
      case POINTERDCL:
      case BOOLDCL:
      case PROCEDURE:{
        node = Dcl();
{if ("" != null) return node;}
        break;
        }
      case IF:{
        node = IfStmt();
{if ("" != null) return node;}
        break;
        }
      case WHILE:{
        node = WhileLoop();
{if ("" != null) return node;}
        break;
        }
      default:
        jj_la1[1] = jj_gen;
        if (jj_2_2(2)) {
          node = Procedure();
{if ("" != null) return node;}
        } else {
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
    throw new Error("Missing return statement in function");
}

  final public Node Assignment() throws ParseException {Token t;
    Token compAssOp;
    Token tAddress = null;
    Token prefix = null;
    Node expr = null;
    if (jj_2_3(2)) {
      t = jj_consume_token(ID);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLUS:{
        compAssOp = jj_consume_token(PLUS);
        break;
        }
      case MINUS:{
        compAssOp = jj_consume_token(MINUS);
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      jj_consume_token(ASSIGN);
      expr = Expr();
      jj_consume_token(END_OF_LINE);
{if ("" != null) return new AssignmentOp(t.image, new Id(t.image), expr, compAssOp.image);}
    } else if (jj_2_4(2)) {
      t = jj_consume_token(ID);
      jj_consume_token(ASSIGN);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ADDRESS:{
        prefix = jj_consume_token(ADDRESS);
        tAddress = jj_consume_token(ID);
        break;
        }
      case INT:
      case FLOAT:
      case MULTIPLY:
      case NOT:
      case TRUE:
      case FALSE:
      case LPAREN:
      case ID:{
        expr = Expr();
        break;
        }
      default:
        jj_la1[3] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      jj_consume_token(END_OF_LINE);
if (prefix != null) {
        {if ("" != null) return new AssignmentOp(t.image, new Id(t.image), new Id(prefix.image, tAddress.image));}
    }
    {if ("" != null) return new AssignmentOp(t.image, new Id(t.image), expr);}
    } else {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case HASHTAG:{
        prefix = jj_consume_token(HASHTAG);
        t = jj_consume_token(ID);
        jj_consume_token(ASSIGN);
        expr = Expr();
        jj_consume_token(END_OF_LINE);
{if ("" != null) return new AssignmentOp(t.image, new Id(prefix.image, t.image), expr);}
        break;
        }
      default:
        jj_la1[4] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    throw new Error("Missing return statement in function");
}

  final public Node Procedure() throws ParseException {Token t;
    Node val = null;
    t = jj_consume_token(ID);
    jj_consume_token(LPAREN);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case INT:
    case FLOAT:
    case MULTIPLY:
    case TRUE:
    case FALSE:
    case ID:{
      val = Val();
      break;
      }
    default:
      jj_la1[5] = jj_gen;
      ;
    }
    jj_consume_token(RPAREN);
    jj_consume_token(END_OF_LINE);
if (val != null) {
        {if ("" != null) return new Procedure(t.image, new Id(t.image), val);}
    }
    {if ("" != null) return new Procedure(t.image, new Id(t.image));}
    throw new Error("Missing return statement in function");
}

  final public Node Dcl() throws ParseException {Node expr = null;
 Token tAddress = null;
 Token prefix = null;
 Node proc;
 Token t;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case FLOATDCL:{
      jj_consume_token(FLOATDCL);
      t = jj_consume_token(ID);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ASSIGN:{
        jj_consume_token(ASSIGN);
        expr = Expr();
        break;
        }
      default:
        jj_la1[6] = jj_gen;
        ;
      }
      jj_consume_token(END_OF_LINE);
if (expr != null) {
       FloatDcl floatDcl = new FloatDcl(t.image);
       {if ("" != null) return new AssignmentOp(t.image, floatDcl, expr);}
   }
   {if ("" != null) return new FloatDcl(t.image);}
      break;
      }
    case INTDCL:{
      jj_consume_token(INTDCL);
      t = jj_consume_token(ID);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ASSIGN:{
        jj_consume_token(ASSIGN);
        expr = Expr();
        break;
        }
      default:
        jj_la1[7] = jj_gen;
        ;
      }
      jj_consume_token(END_OF_LINE);
if (expr != null) {
        IntDcl intDcl = new IntDcl(t.image);
        {if ("" != null) return new AssignmentOp(t.image, intDcl, expr);}
    }
    {if ("" != null) return new IntDcl(t.image);}
      break;
      }
    case BOOLDCL:{
      jj_consume_token(BOOLDCL);
      t = jj_consume_token(ID);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ASSIGN:{
        jj_consume_token(ASSIGN);
        expr = Expr();
        break;
        }
      default:
        jj_la1[8] = jj_gen;
        ;
      }
      jj_consume_token(END_OF_LINE);
if (expr != null) {
        BoolDcl boolDcl = new BoolDcl(t.image);
        {if ("" != null) return new AssignmentOp(t.image, boolDcl, expr);}
    }
    {if ("" != null) return new BoolDcl(t.image);}
      break;
      }
    case POINTERDCL:{
      jj_consume_token(POINTERDCL);
      t = jj_consume_token(ID);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ASSIGN:{
        jj_consume_token(ASSIGN);
        prefix = jj_consume_token(ADDRESS);
        tAddress = jj_consume_token(ID);
        break;
        }
      default:
        jj_la1[9] = jj_gen;
        ;
      }
      jj_consume_token(END_OF_LINE);
if (tAddress != null) {
        PointerDcl pointerDcl = new PointerDcl(t.image);
        Id id = new Id(prefix.image, tAddress.image);
        {if ("" != null) return new AssignmentOp(t.image, pointerDcl, id);}
    }
    {if ("" != null) return new PointerDcl(t.image);}
      break;
      }
    case PROCEDURE:{
      proc = ProcedureDcl();
{if ("" != null) return proc;}
      break;
      }
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
}

  final public Node ProcedureDcl() throws ParseException {Token t;
    Token type = null;
    Token tParam = null;
    Node param = null;
    Node block;
    jj_consume_token(PROCEDURE);
    t = jj_consume_token(ID);
    jj_consume_token(LPAREN);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case INTDCL:
    case FLOATDCL:
    case POINTERDCL:
    case BOOLDCL:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INTDCL:{
        type = jj_consume_token(INTDCL);
        tParam = jj_consume_token(ID);
param = new IntDcl(tParam.image);
        break;
        }
      case FLOATDCL:{
        type = jj_consume_token(FLOATDCL);
        tParam = jj_consume_token(ID);
param = new FloatDcl(tParam.image);
        break;
        }
      case BOOLDCL:{
        type = jj_consume_token(BOOLDCL);
        tParam = jj_consume_token(ID);
param = new BoolDcl(tParam.image);
        break;
        }
      case POINTERDCL:{
        type = jj_consume_token(POINTERDCL);
        tParam = jj_consume_token(ID);
param = new PointerDcl(tParam.image);
        break;
        }
      default:
        jj_la1[11] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
      }
    default:
      jj_la1[12] = jj_gen;
      ;
    }
    jj_consume_token(RPAREN);
    jj_consume_token(LBRACE);
    block = Block();
    jj_consume_token(RBRACE);
if (type != null) {
        ProcedureDcl proc = new ProcedureDcl(t.image, param, block);
        switch (type.image) {
            case "int":
                proc.setType(new IntType());
                break;
            case "float":
                proc.setType(new FloatType());
                break;
            case "boolean":
                proc.setType(new BooleanType());
                break;
            case "pointer":
                proc.setType(new PointerType());
                break;
            default:
                proc.setType(new ProcedureType());
        }
        {if ("" != null) return proc;}
    }

    {if ("" != null) return new ProcedureDcl(t.image, block);}
    throw new Error("Missing return statement in function");
}

  final public Node IfStmt() throws ParseException {Node expr;
    Node ifBlock;
    Node elseBlock = null;
    jj_consume_token(IF);
    jj_consume_token(LPAREN);
    expr = Expr();
    jj_consume_token(RPAREN);
    jj_consume_token(LBRACE);
    ifBlock = Block();
    jj_consume_token(RBRACE);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case ELSE:{
      jj_consume_token(ELSE);
      jj_consume_token(LBRACE);
      elseBlock = Block();
      jj_consume_token(RBRACE);
      break;
      }
    default:
      jj_la1[13] = jj_gen;
      ;
    }
if (elseBlock != null) {
        {if ("" != null) return new IfElseStmt(expr, ifBlock, elseBlock);}
    }
    {if ("" != null) return new IfStmt(expr, ifBlock);}
    throw new Error("Missing return statement in function");
}

  final public Node WhileLoop() throws ParseException {Node expr;
    Node block;
    jj_consume_token(WHILE);
    jj_consume_token(LPAREN);
    expr = Expr();
    jj_consume_token(RPAREN);
    jj_consume_token(LBRACE);
    block = Block();
    jj_consume_token(RBRACE);
{if ("" != null) return new WhileLoop(expr, block);}
    throw new Error("Missing return statement in function");
}

  final public Node Block() throws ParseException {Block block = new Block();
   Node stmt = null;
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INTDCL:
      case FLOATDCL:
      case POINTERDCL:
      case IF:
      case BOOLDCL:
      case HASHTAG:
      case WHILE:
      case PROCEDURE:
      case ID:{
        ;
        break;
        }
      default:
        jj_la1[14] = jj_gen;
        break label_2;
      }
      stmt = Stmt();
block.addChild(stmt);
    }
{if ("" != null) return block;}
    throw new Error("Missing return statement in function");
}

  final public Node Expr() throws ParseException {Node expr;
    expr = OrOp();
{if ("" != null) return expr;}
    throw new Error("Missing return statement in function");
}

  final public Node OrOp() throws ParseException {Node left = null;
    Token op = null;
    Node right = null;
    left = AndOp();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case OR:{
        ;
        break;
        }
      default:
        jj_la1[15] = jj_gen;
        break label_3;
      }
      op = jj_consume_token(OR);
      right = AndOp();
left = new ComparisonOp(left, op.image, right);
    }
{if ("" != null) return left;}
    throw new Error("Missing return statement in function");
}

  final public Node AndOp() throws ParseException {Node left = null;
    Token op = null;
    Node right = null;
    left = EqualityOp();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case AND:{
        ;
        break;
        }
      default:
        jj_la1[16] = jj_gen;
        break label_4;
      }
      op = jj_consume_token(AND);
      right = EqualityOp();
left = new ComparisonOp(left, op.image, right);
    }
{if ("" != null) return left;}
    throw new Error("Missing return statement in function");
}

  final public Node EqualityOp() throws ParseException {Node left = null;
    Token op = null;
    Node right = null;
    left = ComparisonOp();
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case EQ:
      case NE:{
        ;
        break;
        }
      default:
        jj_la1[17] = jj_gen;
        break label_5;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case EQ:{
        op = jj_consume_token(EQ);
        break;
        }
      case NE:{
        op = jj_consume_token(NE);
        break;
        }
      default:
        jj_la1[18] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      right = ComparisonOp();
left = new ComparisonOp(left, op.image, right);
    }
{if ("" != null) return left;}
    throw new Error("Missing return statement in function");
}

  final public Node ComparisonOp() throws ParseException {Node left = null;
    Token op = null;
    Node right = null;
    left = ArithmeticPlusMinusOp();
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LT:
      case LE:
      case GT:
      case GE:{
        ;
        break;
        }
      default:
        jj_la1[19] = jj_gen;
        break label_6;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LT:{
        op = jj_consume_token(LT);
        break;
        }
      case GT:{
        op = jj_consume_token(GT);
        break;
        }
      case GE:{
        op = jj_consume_token(GE);
        break;
        }
      case LE:{
        op = jj_consume_token(LE);
        break;
        }
      default:
        jj_la1[20] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      right = ArithmeticPlusMinusOp();
left = new ComparisonOp(left, op.image, right);
    }
{if ("" != null) return left;}
    throw new Error("Missing return statement in function");
}

  final public Node ArithmeticPlusMinusOp() throws ParseException {Node left = null;
    Token op = null;
    Node right = null;
    left = ArithmeticMulDivOp();
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLUS:
      case MINUS:{
        ;
        break;
        }
      default:
        jj_la1[21] = jj_gen;
        break label_7;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLUS:{
        op = jj_consume_token(PLUS);
        break;
        }
      case MINUS:{
        op = jj_consume_token(MINUS);
        break;
        }
      default:
        jj_la1[22] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      right = ArithmeticMulDivOp();
left = new ArithmeticOp(left, op.image, right);
    }
{if ("" != null) return left;}
    throw new Error("Missing return statement in function");
}

  final public Node ArithmeticMulDivOp() throws ParseException {Node left = null;
    Token op = null;
    Node right = null;
    left = NegationOp();
    label_8:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case MULTIPLY:
      case DIVIDE:{
        ;
        break;
        }
      default:
        jj_la1[23] = jj_gen;
        break label_8;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case MULTIPLY:{
        op = jj_consume_token(MULTIPLY);
        break;
        }
      case DIVIDE:{
        op = jj_consume_token(DIVIDE);
        break;
        }
      default:
        jj_la1[24] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      right = NegationOp();
left = new ArithmeticOp(left, op.image, right);
    }
{if ("" != null) return left;}
    throw new Error("Missing return statement in function");
}

  final public Node NegationOp() throws ParseException {Node factor;
    boolean hasNegationOp = false;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case NOT:{
      jj_consume_token(NOT);
hasNegationOp = true;
      break;
      }
    default:
      jj_la1[25] = jj_gen;
      ;
    }
    factor = Factor();
if (hasNegationOp) {
        {if ("" != null) return new NegationOp(factor);}
    }
    {if ("" != null) return factor;}
    throw new Error("Missing return statement in function");
}

  final public Node Factor() throws ParseException {Node val;
    Node expr;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LPAREN:{
      jj_consume_token(LPAREN);
      expr = Expr();
      jj_consume_token(RPAREN);
{if ("" != null) return expr;}
      break;
      }
    case INT:
    case FLOAT:
    case MULTIPLY:
    case TRUE:
    case FALSE:
    case ID:{
      val = Val();
{if ("" != null) return val;}
      break;
      }
    default:
      jj_la1[26] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
}

  final public Node Val() throws ParseException {Token prefix = null;
    Token t;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case INT:{
      t = jj_consume_token(INT);
{if ("" != null) return new IntNum(Integer.parseInt(t.image));}
      break;
      }
    case FLOAT:{
      t = jj_consume_token(FLOAT);
{if ("" != null) return new FloatNum(Float.parseFloat(t.image));}
      break;
      }
    case MULTIPLY:
    case ID:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case MULTIPLY:{
        prefix = jj_consume_token(MULTIPLY);
        break;
        }
      default:
        jj_la1[27] = jj_gen;
        ;
      }
      t = jj_consume_token(ID);
if (prefix != null) {
        {if ("" != null) return new Id(prefix.image, t.image);}
    }
    {if ("" != null) return new Id(t.image);}
      break;
      }
    case TRUE:
    case FALSE:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case TRUE:{
        t = jj_consume_token(TRUE);
        break;
        }
      case FALSE:{
        t = jj_consume_token(FALSE);
        break;
        }
      default:
        jj_la1[28] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
{if ("" != null) return new Bool(Boolean.parseBoolean(t.image));}
      break;
      }
    default:
      jj_la1[29] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
}

  private boolean jj_2_1(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_1()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_2_2(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_2()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  private boolean jj_2_3(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_3()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  private boolean jj_2_4(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_4()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  private boolean jj_3_2()
 {
    if (jj_3R_Procedure_184_3_10()) return true;
    return false;
  }

  private boolean jj_3_4()
 {
    if (jj_scan_token(ID)) return true;
    if (jj_scan_token(ASSIGN)) return true;
    return false;
  }

  private boolean jj_3_1()
 {
    if (jj_3R_Assignment_162_3_9()) return true;
    return false;
  }

  private boolean jj_3R_Assignment_173_3_11()
 {
    if (jj_scan_token(HASHTAG)) return true;
    if (jj_scan_token(ID)) return true;
    return false;
  }

  private boolean jj_3R_Procedure_184_3_10()
 {
    if (jj_scan_token(ID)) return true;
    if (jj_scan_token(LPAREN)) return true;
    return false;
  }

  private boolean jj_3R_Assignment_162_3_9()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_3()) {
    jj_scanpos = xsp;
    if (jj_3_4()) {
    jj_scanpos = xsp;
    if (jj_3R_Assignment_173_3_11()) return true;
    }
    }
    return false;
  }

  private boolean jj_3_3()
 {
    if (jj_scan_token(ID)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(11)) {
    jj_scanpos = xsp;
    if (jj_scan_token(12)) return true;
    }
    return false;
  }

  /** Generated Token Manager. */
  public CompilerTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[30];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
	   jj_la1_init_0();
	   jj_la1_init_1();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {0x8018140,0x8018140,0x1800,0xb0102280,0x0,0x30002280,0x40000000,0x40000000,0x40000000,0x40000000,0x8008140,0x8008140,0x8008140,0x20000,0x8018140,0x80000,0x40000,0x600000,0x600000,0x7800000,0x7800000,0x1800,0x1800,0x6000,0x6000,0x100000,0xb0002280,0x2000,0x30000000,0x30002280,};
	}
	private static void jj_la1_init_1() {
	   jj_la1_1 = new int[] {0x5a0,0x180,0x0,0x410,0x20,0x400,0x0,0x0,0x0,0x0,0x100,0x0,0x0,0x0,0x5a0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x400,0x0,0x0,0x400,};
	}
  final private JJCalls[] jj_2_rtns = new JJCalls[4];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public Compiler(java.io.InputStream stream) {
	  this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Compiler(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source = new CompilerTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 30; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
	  ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 30; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public Compiler(java.io.Reader stream) {
	 jj_input_stream = new SimpleCharStream(stream, 1, 1);
	 token_source = new CompilerTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 30; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
	   jj_input_stream = new SimpleCharStream(stream, 1, 1);
	} else {
	   jj_input_stream.ReInit(stream, 1, 1);
	}
	if (token_source == null) {
 token_source = new CompilerTokenManager(jj_input_stream);
	}

	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 30; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public Compiler(CompilerTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 30; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(CompilerTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 30; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
	 Token oldToken;
	 if ((oldToken = token).next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 if (token.kind == kind) {
	   jj_gen++;
	   if (++jj_gc > 100) {
		 jj_gc = 0;
		 for (int i = 0; i < jj_2_rtns.length; i++) {
		   JJCalls c = jj_2_rtns[i];
		   while (c != null) {
			 if (c.gen < jj_gen) c.first = null;
			 c = c.next;
		   }
		 }
	   }
	   return token;
	 }
	 token = oldToken;
	 jj_kind = kind;
	 throw generateParseException();
  }

  @SuppressWarnings("serial")
  static private final class LookaheadSuccess extends java.lang.Error {
    @Override
    public Throwable fillInStackTrace() {
      return this;
    }
  }
  static private final LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
	 if (jj_scanpos == jj_lastpos) {
	   jj_la--;
	   if (jj_scanpos.next == null) {
		 jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
	   } else {
		 jj_lastpos = jj_scanpos = jj_scanpos.next;
	   }
	 } else {
	   jj_scanpos = jj_scanpos.next;
	 }
	 if (jj_rescan) {
	   int i = 0; Token tok = token;
	   while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
	   if (tok != null) jj_add_error_token(kind, i);
	 }
	 if (jj_scanpos.kind != kind) return true;
	 if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
	 return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
	 if (token.next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 jj_gen++;
	 return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
	 Token t = token;
	 for (int i = 0; i < index; i++) {
	   if (t.next != null) t = t.next;
	   else t = t.next = token_source.getNextToken();
	 }
	 return t;
  }

  private int jj_ntk_f() {
	 if ((jj_nt=token.next) == null)
	   return (jj_ntk = (token.next=token_source.getNextToken()).kind);
	 else
	   return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
	 if (pos >= 100) {
		return;
	 }

	 if (pos == jj_endpos + 1) {
	   jj_lasttokens[jj_endpos++] = kind;
	 } else if (jj_endpos != 0) {
	   jj_expentry = new int[jj_endpos];

	   for (int i = 0; i < jj_endpos; i++) {
		 jj_expentry[i] = jj_lasttokens[i];
	   }

	   for (int[] oldentry : jj_expentries) {
		 if (oldentry.length == jj_expentry.length) {
		   boolean isMatched = true;

		   for (int i = 0; i < jj_expentry.length; i++) {
			 if (oldentry[i] != jj_expentry[i]) {
			   isMatched = false;
			   break;
			 }

		   }
		   if (isMatched) {
			 jj_expentries.add(jj_expentry);
			 break;
		   }
		 }
	   }

	   if (pos != 0) {
		 jj_lasttokens[(jj_endpos = pos) - 1] = kind;
	   }
	 }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
	 jj_expentries.clear();
	 boolean[] la1tokens = new boolean[43];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 30; i++) {
	   if (jj_la1[i] == jj_gen) {
		 for (int j = 0; j < 32; j++) {
		   if ((jj_la1_0[i] & (1<<j)) != 0) {
			 la1tokens[j] = true;
		   }
		   if ((jj_la1_1[i] & (1<<j)) != 0) {
			 la1tokens[32+j] = true;
		   }
		 }
	   }
	 }
	 for (int i = 0; i < 43; i++) {
	   if (la1tokens[i]) {
		 jj_expentry = new int[1];
		 jj_expentry[0] = i;
		 jj_expentries.add(jj_expentry);
	   }
	 }
	 jj_endpos = 0;
	 jj_rescan_token();
	 jj_add_error_token(0, 0);
	 int[][] exptokseq = new int[jj_expentries.size()][];
	 for (int i = 0; i < jj_expentries.size(); i++) {
	   exptokseq[i] = jj_expentries.get(i);
	 }
	 return new ParseException(token, exptokseq, tokenImage);
  }

  private boolean trace_enabled;

/** Trace enabled. */
  final public boolean trace_enabled() {
	 return trace_enabled;
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
	 jj_rescan = true;
	 for (int i = 0; i < 4; i++) {
	   try {
		 JJCalls p = jj_2_rtns[i];

		 do {
		   if (p.gen > jj_gen) {
			 jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
			 switch (i) {
			   case 0: jj_3_1(); break;
			   case 1: jj_3_2(); break;
			   case 2: jj_3_3(); break;
			   case 3: jj_3_4(); break;
			 }
		   }
		   p = p.next;
		 } while (p != null);

		 } catch(LookaheadSuccess ls) { }
	 }
	 jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
	 JJCalls p = jj_2_rtns[index];
	 while (p.gen > jj_gen) {
	   if (p.next == null) { p = p.next = new JJCalls(); break; }
	   p = p.next;
	 }

	 p.gen = jj_gen + xla - jj_la; 
	 p.first = token;
	 p.arg = xla;
  }

  static final class JJCalls {
	 int gen;
	 Token first;
	 int arg;
	 JJCalls next;
  }

}
