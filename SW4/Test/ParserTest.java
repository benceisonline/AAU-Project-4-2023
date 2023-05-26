import AST.CodeGeneration.CodeGenerator;
import AST.CodeGeneration.InstructionSet;
import AST.ConstantFolding;
import AST.Nodes.*;
import AST.SymbolTableFilling.SymbolTableFilling;
import AST.TypeChecking;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void IntDclTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/intDcl.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        IntDcl intDcl = new IntDcl("phone_number");
        IntDcl intDcl2 = new IntDcl("creditCardNumber");
        IntDcl intDcl3 = new IntDcl("a123456");
        expectedAST.addChild(intDcl);
        expectedAST.addChild(intDcl2);
        expectedAST.addChild(intDcl3);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void FloatDclTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/floatDcl.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        FloatDcl floatDcl = new FloatDcl("floatDcl");
        FloatDcl floatDcl2 = new FloatDcl("my_variable");
        FloatDcl floatDcl3 = new FloatDcl("order123");
        expectedAST.addChild(floatDcl);
        expectedAST.addChild(floatDcl2);
        expectedAST.addChild(floatDcl3);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void BoolDclTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/boolDcl.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        BoolDcl boolDcl = new BoolDcl("fooBar");
        BoolDcl boolDcl2 = new BoolDcl("thisIsA_boolean2023");
        BoolDcl boolDcl3 = new BoolDcl("boolean_value");
        expectedAST.addChild(boolDcl);
        expectedAST.addChild(boolDcl2);
        expectedAST.addChild(boolDcl3);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void MixDclTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/mixDcl.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        BoolDcl boolDcl = new BoolDcl("fooBar");
        FloatDcl floatDcl = new FloatDcl("thisIsA_boolean2023");
        IntDcl intDcl = new IntDcl("a123456");
        PointerDcl pointerDcl = new PointerDcl("ptr");
        expectedAST.addChild(boolDcl);
        expectedAST.addChild(floatDcl);
        expectedAST.addChild(intDcl);
        expectedAST.addChild(pointerDcl);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void WrongTypeDclTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/wrongTypeDcl.txt");

        // [WHEN] [THEN] We try to parse the code from the file, and it gives an error
        try {
            compiler.Prog();
            assert false;
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert true;
        }
    }

    @Test
    void SpecialCharDclTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/containsSpecialCharDcl.txt");

        // [WHEN] [THEN] We try to parse the code from the file, and it gives an error
        try {
            compiler.Prog();
            assert false;
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert true;
        }
    }

    @Test
    void AssignIntTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignInt.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        AssignmentOp intAssign = new AssignmentOp("a", new Id("a"), new IntNum(5));
        AssignmentOp intAssign2 = new AssignmentOp("b", new Id("b"), new IntNum(10));
        expectedAST.addChild(intAssign);
        expectedAST.addChild(intAssign2);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void AssignIntWithExprTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignIntWithExpr.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        ArithmeticOp computingA1 = new ArithmeticOp(new IntNum(3), "-", new IntNum(4));
        ArithmeticOp computingA2 = new ArithmeticOp(new IntNum(5), "+", new IntNum(10));
        ArithmeticOp computingA3 = new ArithmeticOp(new IntNum(5), "+", computingA2);
        ArithmeticOp computingA4 = new ArithmeticOp(computingA3, "-", new IntNum(4));
        ArithmeticOp computingA5 = new ArithmeticOp(computingA4, "+", new IntNum(5));
        ArithmeticOp computingA6 = new ArithmeticOp(computingA5, "-", computingA1);
        ArithmeticOp computingB1 = new ArithmeticOp(new IntNum(10), "-", new IntNum(5));
        ArithmeticOp computingC1 = new ArithmeticOp(new IntNum(0), "+", new IntNum(10005));
        AssignmentOp intAssign = new AssignmentOp("a", new Id("a"), computingA6);
        AssignmentOp intAssign2 = new AssignmentOp("b",new Id("b"), computingB1);
        AssignmentOp intAssign3 = new AssignmentOp("c",new Id("c"), computingC1);

        expectedAST.addChild(intAssign);
        expectedAST.addChild(intAssign2);
        expectedAST.addChild(intAssign3);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();
          
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void AssignFloatWithExprTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignFloatWithExpr.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        ArithmeticOp computingA1 = new ArithmeticOp(new FloatNum(3.2F), "-", new FloatNum(4.3562F));
        ArithmeticOp computingA2 = new ArithmeticOp(new FloatNum(5.00005F), "+", new FloatNum(10.4F));
        ArithmeticOp computingA3 = new ArithmeticOp(new FloatNum(5.0F), "+", computingA2);
        ArithmeticOp computingA4 = new ArithmeticOp(computingA3, "-", new FloatNum(4.92F));
        ArithmeticOp computingA5 = new ArithmeticOp(computingA4, "+", new FloatNum(5.42F));
        ArithmeticOp computingA6 = new ArithmeticOp(computingA5, "-", computingA1);
        ArithmeticOp computingB1 = new ArithmeticOp(new FloatNum(10.0F), "-", new FloatNum(5.43F));
        ArithmeticOp computingC1 = new ArithmeticOp(new FloatNum(0.3F), "+", new FloatNum(10005.78F));
        AssignmentOp floatAssign = new AssignmentOp("a", new Id("a"), computingA6);
        AssignmentOp floatAssign2 = new AssignmentOp("b", new Id("b"), computingB1);
        AssignmentOp floatAssign3 = new AssignmentOp("c", new Id("c"), computingC1);

        expectedAST.addChild(floatAssign);
        expectedAST.addChild(floatAssign2);
        expectedAST.addChild(floatAssign3);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();

            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void AssignFloatTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignFloat.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
      
        AssignmentOp floatAssign = new AssignmentOp("a", new Id("a"), new FloatNum(3.14F));
        AssignmentOp floatAssign2 = new AssignmentOp("b", new Id("b"), new FloatNum(0.025F));

        expectedAST.addChild(floatAssign);
        expectedAST.addChild(floatAssign2);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();

            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void AssignBoolWithExprTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignBoolWithExpr.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        ComparisonOp binOperatorA1 = new ComparisonOp(new Bool(true), "||", new Bool(false));
        ComparisonOp binOperatorA2 = new ComparisonOp(binOperatorA1, "&&", new Bool(true));
        ComparisonOp binOperatorA3 = new ComparisonOp(binOperatorA2, "&&", new NegationOp(new Id("x")));
        ComparisonOp binOperatorB1 = new ComparisonOp(new Bool(true), "||", new Bool(false));
        ComparisonOp binOperatorC1 = new ComparisonOp(new Bool(false), "&&", new Bool(true));
        ComparisonOp binOperatorD1 = new ComparisonOp(new FloatNum(2.5F), "<", new FloatNum(4.3F));
        ComparisonOp binOperatorD2 = new ComparisonOp(binOperatorD1, "&&", new Bool(true));
        ComparisonOp binOperatorE1 = new ComparisonOp(new IntNum(2), "<", new IntNum(4));
        ComparisonOp binOperatorE2 = new ComparisonOp(binOperatorE1, "<=", new IntNum(5));
        ComparisonOp binOperatorE3 = new ComparisonOp(binOperatorE2, ">", new IntNum(5));
        ComparisonOp binOperatorE4 = new ComparisonOp(new IntNum(5), ">=", new IntNum(40));
        ComparisonOp binOperatorE5 = new ComparisonOp(binOperatorE4, "==", new IntNum(5));
        ComparisonOp binOperatorE6 = new ComparisonOp(binOperatorE5, "!=", new IntNum(10));
        ComparisonOp binOperatorE7 = new ComparisonOp(binOperatorE3, "&&", binOperatorE6);
        AssignmentOp boolAssign = new AssignmentOp("a", new Id("a"), binOperatorA3);
        AssignmentOp boolAssign2 = new AssignmentOp("b",new Id("b"), binOperatorB1);
        AssignmentOp boolAssign3 = new AssignmentOp("c",new Id("c"), binOperatorC1);
        AssignmentOp boolAssign4 = new AssignmentOp("d",new Id("d"), binOperatorD2);
        AssignmentOp boolAssign5 = new AssignmentOp("e",new Id("e"), binOperatorE7);

        expectedAST.addChild(boolAssign);
        expectedAST.addChild(boolAssign2);
        expectedAST.addChild(boolAssign3);
        expectedAST.addChild(boolAssign4);
        expectedAST.addChild(boolAssign5);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();

            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void AssignBoolTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignBool.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();

        AssignmentOp boolAssign = new AssignmentOp("a", new Id("a"), new Bool(true));
        AssignmentOp boolAssign2 = new AssignmentOp("b", new Id("b"), new Bool(false));

        expectedAST.addChild(boolAssign);
        expectedAST.addChild(boolAssign2);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();

            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void AssignIntDclWithExprTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignIntDclWithExpr.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        ArithmeticOp computingA1 = new ArithmeticOp(new IntNum(3), "-", new IntNum(4));
        ArithmeticOp computingA2 = new ArithmeticOp(new IntNum(5), "+", new IntNum(10));
        ArithmeticOp computingA3 = new ArithmeticOp(new IntNum(5), "+", computingA2);
        ArithmeticOp computingA4 = new ArithmeticOp(computingA3, "-", new IntNum(4));
        ArithmeticOp computingA5 = new ArithmeticOp(computingA4, "+", new IntNum(5));
        ArithmeticOp computingA6 = new ArithmeticOp(computingA5, "-", computingA1);
        ArithmeticOp computingB1 = new ArithmeticOp(new IntNum(10), "-", new IntNum(5));
        ArithmeticOp computingC1 = new ArithmeticOp(new IntNum(0), "+", new IntNum(10005));
        AssignmentOp intAssign = new AssignmentOp("a", new IntDcl("a"), computingA6);
        AssignmentOp intAssign2 = new AssignmentOp("b", new IntDcl("b"), computingB1);
        AssignmentOp intAssign3 = new AssignmentOp("c", new IntDcl("c"), computingC1);

        expectedAST.addChild(intAssign);
        expectedAST.addChild(intAssign2);
        expectedAST.addChild(intAssign3);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();

            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void AssignIntDclTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignIntDcl.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();

        AssignmentOp intAssign = new AssignmentOp("a", new IntDcl("a"), new IntNum(3));
        AssignmentOp intAssign2 = new AssignmentOp("b", new IntDcl("b"), new IntNum(12));

        expectedAST.addChild(intAssign);
        expectedAST.addChild(intAssign2);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    /*
    @Test
    void AssignFloatDclWithExprTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignFloatDclWithExpr.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        ArithmeticOp computingA1 = new ArithmeticOp(new FloatNum(3.2F), "-", new FloatNum(4.3562F));
        ArithmeticOp computingA2 = new ArithmeticOp(new FloatNum(5.00005F), "+", new FloatNum(10.4F));
        ArithmeticOp computingA3 = new ArithmeticOp(new FloatNum(5.0F), "+", computingA2);
        ArithmeticOp computingA4 = new ArithmeticOp(computingA3, "-", new FloatNum(4.92F));
        ArithmeticOp computingA5 = new ArithmeticOp(computingA4, "+", new FloatNum(5.42F));
        ArithmeticOp computingA6 = new ArithmeticOp(computingA5, "-", computingA1);
        ArithmeticOp computingB1 = new ArithmeticOp(new FloatNum(10.0F), "-", new FloatNum(5.43F));
        ArithmeticOp computingC1 = new ArithmeticOp(new FloatNum(0.3F), "+", new FloatNum(10005.78F));
        AssignmentOp floatAssign = new AssignmentOp("a", new Id("a"), computingA6);
        AssignmentOp floatAssign2 = new AssignmentOp("b", new Id("b"), computingB1);
        AssignmentOp floatAssign3 = new AssignmentOp("c", new Id("c"), computingC1);

        expectedAST.addChild(floatAssign);
        expectedAST.addChild(floatAssign2);
        expectedAST.addChild(floatAssign3);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

     */

    @Test
    void AssignFloatDclTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignFloatDcl.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();

        AssignmentOp floatAssign = new AssignmentOp("a", new FloatDcl("a"), new FloatNum(8.32F));
        AssignmentOp floatAssign2 = new AssignmentOp("b", new FloatDcl("b"), new FloatNum(0.97F));

        expectedAST.addChild(floatAssign);
        expectedAST.addChild(floatAssign2);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();

            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    /*
    @Test
    void AssignBoolDclWithExprTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignBoolDclWithExpr.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();

        ComparisonOp binOperatorA1 = new ComparisonOp(new Bool(true), "||", new Bool(false));
        ComparisonOp binOperatorA2 = new ComparisonOp(binOperatorA1, "&&", new Bool(true));
        ComparisonOp binOperatorA3 = new ComparisonOp(binOperatorA2, "&&", new NegationOp(new Id("x")));
        ComparisonOp binOperatorB1 = new ComparisonOp(new Bool(true), "||", new Bool(false));
        ComparisonOp binOperatorC1 = new ComparisonOp(new Bool(false), "&&", new Bool(true));
        ComparisonOp binOperatorD1 = new ComparisonOp(new FloatNum(2.5F), "<", new FloatNum(4.3F));
        ComparisonOp binOperatorD2 = new ComparisonOp(binOperatorD1, "&&", new Bool(true));
        ComparisonOp binOperatorE1 = new ComparisonOp(new IntNum(2), "<", new IntNum(4));
        ComparisonOp binOperatorE2 = new ComparisonOp(binOperatorE1, "<=", new IntNum(5));
        ComparisonOp binOperatorE3 = new ComparisonOp(binOperatorE2, ">", new IntNum(5));
        ComparisonOp binOperatorE4 = new ComparisonOp(new IntNum(5), ">=", new IntNum(40));
        ComparisonOp binOperatorE5 = new ComparisonOp(binOperatorE4, "==", new IntNum(5));
        ComparisonOp binOperatorE6 = new ComparisonOp(binOperatorE5, "!=", new IntNum(10));
        ComparisonOp binOperatorE7 = new ComparisonOp(binOperatorE3, "&&", binOperatorE6);
        AssignmentOp boolAssign = new AssignmentOp("a", new Id("a"), binOperatorA3);
        AssignmentOp boolAssign2 = new AssignmentOp("b",new Id("b"), binOperatorB1);
        AssignmentOp boolAssign3 = new AssignmentOp("c",new Id("c"), binOperatorC1);
        AssignmentOp boolAssign4 = new AssignmentOp("d",new Id("d"), binOperatorD2);
        AssignmentOp boolAssign5 = new AssignmentOp("e",new Id("e"), binOperatorE7);

        expectedAST.addChild(boolAssign);
        expectedAST.addChild(boolAssign2);
        expectedAST.addChild(boolAssign3);
        expectedAST.addChild(boolAssign4);
        expectedAST.addChild(boolAssign5);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();

            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

     */

    @Test
    void AssignBoolDclTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignBoolDcl.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();

        AssignmentOp boolAssign = new AssignmentOp("p", new BoolDcl("p"), new Bool(true));
        AssignmentOp boolAssign2 = new AssignmentOp("q",new BoolDcl("q"), new Bool(false));

        expectedAST.addChild(boolAssign);
        expectedAST.addChild(boolAssign2);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void MixAssignDclTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/mixAssignWithDcls.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();

        AssignmentOp intAssign = new AssignmentOp("a", new IntDcl("a"), new IntNum(1));
        AssignmentOp boolAssign = new AssignmentOp("b", new BoolDcl("b"), new Bool(false));
        AssignmentOp floatAssign = new AssignmentOp("c", new FloatDcl("c"), new FloatNum(6.2F));

        expectedAST.addChild(intAssign);
        expectedAST.addChild(boolAssign);
        expectedAST.addChild(floatAssign);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void AssignWithExprFail() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignWithExprFail.txt");

        // [WHEN] [THEN] We try to parse the code from the file, and it gives an error
        try {
            compiler.Prog();
            assert false;
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert true;
        }
    }

    @Test
    void WrongAssignTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/wrongAssign.txt");

        // [WHEN] [THEN] We try to parse the code from the file, and it gives an error
        try {
            compiler.Prog();
            assert false;
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert true;
        }
    }

    @Test
    void AssignDclWithExprFail() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignDclWithExprFail.txt");

        // [WHEN] [THEN] We try to parse the code from the file, and it gives an error
        try {
            compiler.Prog();
            assert false;
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert true;
        }
    }

    @Test
    void IfTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/if.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();

        ComparisonOp binOp = new ComparisonOp(new IntNum(2), "<", new IntNum(4));
        AssignmentOp assigning = new AssignmentOp("t", new Id("t"), new Bool(true));
        Block block = new Block();
        block.addChild(assigning);
        IfStmt ifStmt = new IfStmt (binOp, block);

        expectedAST.addChild(ifStmt);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void IfElseTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/ifElse.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();

        ComparisonOp binOp = new ComparisonOp(new IntNum(1), ">", new IntNum(2));
        AssignmentOp assigning = new AssignmentOp("t", new Id("t"), new Bool(true));
        AssignmentOp assigning2 = new AssignmentOp("t", new Id("t"), new Bool (false));

        Block block = new Block();
        block.addChild(assigning);
        Block block2 = new Block();
        block2.addChild(assigning2);

        IfElseStmt ifElseStmt = new IfElseStmt(binOp, block, block2);

        expectedAST.addChild(ifElseStmt);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void WrongIfElseTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/wrongIfElse.txt");

        // [WHEN] [THEN] We try to parse the code from the file, and it gives an error
        try {
            compiler.Prog();
            assert false;
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert true;
        }
    }

    /*
    @Test
    void AssignPointerWithAddressTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignPointerWithAddress.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        Assigning pointerAssign = new Assigning("ptr", new Id("ptr", true), new Id("a", true));
        Assigning pointerAssign2 = new Assigning("ptr2", new Id("ptr2", true), new Id("example", true));
        Assigning pointerAssign3 = new Assigning("ptr3", new Id("ptr3", true), new Id("test", true));
        expectedAST.addChild(pointerAssign);
        expectedAST.addChild(pointerAssign2);
        expectedAST.addChild(pointerAssign3);
    */

    @Test
    void NestedIfElseTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/nestedIfElse.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();

        // elseBlock
        AssignmentOp assigning = new AssignmentOp("b", new Id("b"), new FloatNum(3.5F));

        Block elseBlock = new Block();
        elseBlock.addChild(assigning);

        Block ifBlockInner = new Block();

        // condition for if-else statement
        ComparisonOp binOp2 = new ComparisonOp(new Bool(true), "==", new Bool(false));

        IfElseStmt ifElseStmt = new IfElseStmt(binOp2, ifBlockInner, elseBlock);

        Block ifBlockOuter = new Block();
        ifBlockOuter.addChild(ifElseStmt);

        // condition for outer if-statement
        ComparisonOp binOp = new ComparisonOp(new IntNum(1), "<", new IntNum(2));

        IfStmt ifStmt = new IfStmt(binOp, ifBlockOuter);
        
        expectedAST.addChild(ifStmt);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void integrationTestIntDcl() throws FileNotFoundException {
        ArrayList<SymbolTableFilling> symbolTableFillings = new ArrayList<>();
        SymbolTableFilling symbolTableFilling = new SymbolTableFilling(symbolTableFillings , 0);
        TypeChecking typeChecking = new TypeChecking(symbolTableFillings);
        ConstantFolding constantFolding = new ConstantFolding();
        CodeGenerator codeGenerator = new CodeGenerator(symbolTableFillings);

        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/intDcl.txt");

        // [GIVEN] That we create the expected StringBuilder from the code from the file
        StringBuilder expectedCode = new StringBuilder();
        expectedCode.append(InstructionSet.PHA.getInstruction() + "\n");
        expectedCode.append(InstructionSet.PHA.getInstruction() + "\n");
        expectedCode.append(InstructionSet.PHA.getInstruction() + "\n");
        expectedCode.append(InstructionSet.JMP.getInstruction() + " Final\n");
        expectedCode.append("Final:\n");

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();
            AST.accept(symbolTableFilling);
            AST.accept(typeChecking);
            AST.accept(constantFolding);
            AST.accept(codeGenerator);
            // [THEN] Assert that the created StringBuilder is equal to the expected StringBuilder
            assertTrue(codeGenerator.getCodeBuilder().toString().equals(expectedCode.toString()));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    private Compiler readFileToParse(String filePath) throws FileNotFoundException {
        FileInputStream stream = new FileInputStream(filePath);
        CompilerTokenManager tm = new CompilerTokenManager(new SimpleCharStream(stream));
        return new Compiler(tm);
    }
}