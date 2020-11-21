package it.unicam.cs.asdl2021.mp1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BalancedParenthesesCheckerTest {
    @Test
    void checkNullThrows() {
        assertThrows(NullPointerException.class, () -> new BalancedParenthesesChecker().check(null));
    }

    @Test
    void checkValidStrings() {
        BalancedParenthesesChecker testing = new BalancedParenthesesChecker();

        assertTrue(testing.check(""));
        assertTrue(testing.check(" "));
        assertTrue(testing.check("\t"));
        assertTrue(testing.check("\n"));

        assertTrue(testing.check("{}"));
        assertTrue(testing.check("[]"));
        assertTrue(testing.check("()"));

        assertTrue(testing.check("{ }"));
        assertTrue(testing.check("{\t}"));
        assertTrue(testing.check("{\n}"));

        assertTrue(testing.check("[ ]"));
        assertTrue(testing.check("[\t]"));
        assertTrue(testing.check("[\n]"));

        assertTrue(testing.check("( )"));
        assertTrue(testing.check("(\t)"));
        assertTrue(testing.check("(\n)"));

        assertTrue(testing.check("\t{}"));
        assertTrue(testing.check("\t[]"));
        assertTrue(testing.check("\t()"));
        assertTrue(testing.check("\t {}"));
        assertTrue(testing.check("\t ()"));
        assertTrue(testing.check("\t []"));
        assertTrue(testing.check("\t{ }"));
        assertTrue(testing.check("\t[ ]"));
        assertTrue(testing.check("\t( )"));
        assertTrue(testing.check(" \t{}"));
        assertTrue(testing.check(" \t[]"));
        assertTrue(testing.check(" \t()"));
        assertTrue(testing.check("\t{} "));
        assertTrue(testing.check("\t[] "));
        assertTrue(testing.check("\t() "));
        assertTrue(testing.check(" \t{} "));
        assertTrue(testing.check(" \t[] "));
        assertTrue(testing.check(" \t() "));
        assertTrue(testing.check("\t { }"));
        assertTrue(testing.check("\t [ ]"));
        assertTrue(testing.check("\t ( )"));
        assertTrue(testing.check(" \t { } "));
        assertTrue(testing.check(" \t [ ] "));
        assertTrue(testing.check(" \t ( ) "));

        assertTrue(testing.check("\n{}"));
        assertTrue(testing.check("\n[]"));
        assertTrue(testing.check("\n()"));
        assertTrue(testing.check("\n {}"));
        assertTrue(testing.check("\n ()"));
        assertTrue(testing.check("\n []"));
        assertTrue(testing.check("\n{ }"));
        assertTrue(testing.check("\n[ ]"));
        assertTrue(testing.check("\n( )"));
        assertTrue(testing.check(" \n{}"));
        assertTrue(testing.check(" \n[]"));
        assertTrue(testing.check(" \n()"));
        assertTrue(testing.check("\n{} "));
        assertTrue(testing.check("\n[] "));
        assertTrue(testing.check("\n() "));
        assertTrue(testing.check(" \n{} "));
        assertTrue(testing.check(" \n[] "));
        assertTrue(testing.check(" \n() "));
        assertTrue(testing.check("\n { }"));
        assertTrue(testing.check("\n [ ]"));
        assertTrue(testing.check("\n ( )"));
        assertTrue(testing.check(" \n { } "));
        assertTrue(testing.check(" \n [ ] "));
        assertTrue(testing.check(" \n ( ) "));

        assertTrue(testing.check("\n\t "));
        assertTrue(testing.check("\n \t"));
        assertTrue(testing.check(" \n\t"));

        assertTrue(testing.check(" \t\n"));
        assertTrue(testing.check("\t\n "));
        assertTrue(testing.check("\t \n"));

        assertTrue(testing.check("{}[]()"));
        assertTrue(testing.check("{[()]}"));
        assertTrue(testing.check("(()\t()\t())"));
        assertTrue(testing.check("(()\n()\n())"));

        assertTrue(testing.check("(()\t()\t())"));
        assertTrue(testing.check("(()\t()\t[])"));
        assertTrue(testing.check("(()\t()\t{})"));
        assertTrue(testing.check("(()\t[]\t())"));
        assertTrue(testing.check("(()\t[]\t[])"));
        assertTrue(testing.check("(()\t[]\t{})"));
        assertTrue(testing.check("(()\t{}\t())"));
        assertTrue(testing.check("(()\t{}\t[])"));
        assertTrue(testing.check("(()\t{}\t{})"));
        assertTrue(testing.check("([]\t()\t())"));
        assertTrue(testing.check("([]\t()\t[])"));
        assertTrue(testing.check("([]\t()\t{})"));
        assertTrue(testing.check("([]\t[]\t())"));
        assertTrue(testing.check("([]\t[]\t[])"));
        assertTrue(testing.check("([]\t[]\t{})"));
        assertTrue(testing.check("([]\t{}\t())"));
        assertTrue(testing.check("([]\t{}\t[])"));
        assertTrue(testing.check("([]\t{}\t{})"));
        assertTrue(testing.check("({}\t()\t())"));
        assertTrue(testing.check("({}\t()\t[])"));
        assertTrue(testing.check("({}\t()\t{})"));
        assertTrue(testing.check("({}\t[]\t())"));
        assertTrue(testing.check("({}\t[]\t[])"));
        assertTrue(testing.check("({}\t[]\t{})"));
        assertTrue(testing.check("({}\t{}\t())"));
        assertTrue(testing.check("({}\t{}\t[])"));
        assertTrue(testing.check("({}\t{}\t{})"));

        assertTrue(testing.check("(()\t()\n())"));
        assertTrue(testing.check("(()\t()\n[])"));
        assertTrue(testing.check("(()\t()\n{})"));
        assertTrue(testing.check("(()\t[]\n())"));
        assertTrue(testing.check("(()\t[]\n[])"));
        assertTrue(testing.check("(()\t[]\n{})"));
        assertTrue(testing.check("(()\t{}\n())"));
        assertTrue(testing.check("(()\t{}\n[])"));
        assertTrue(testing.check("(()\t{}\n{})"));
        assertTrue(testing.check("([]\t()\n())"));
        assertTrue(testing.check("([]\t()\n[])"));
        assertTrue(testing.check("([]\t()\n{})"));
        assertTrue(testing.check("([]\t[]\n())"));
        assertTrue(testing.check("([]\t[]\n[])"));
        assertTrue(testing.check("([]\t[]\n{})"));
        assertTrue(testing.check("([]\t{}\n())"));
        assertTrue(testing.check("([]\t{}\n[])"));
        assertTrue(testing.check("([]\t{}\n{})"));
        assertTrue(testing.check("({}\t()\n())"));
        assertTrue(testing.check("({}\t()\n[])"));
        assertTrue(testing.check("({}\t()\n{})"));
        assertTrue(testing.check("({}\t[]\n())"));
        assertTrue(testing.check("({}\t[]\n[])"));
        assertTrue(testing.check("({}\t[]\n{})"));
        assertTrue(testing.check("({}\t{}\n())"));
        assertTrue(testing.check("({}\t{}\n[])"));
        assertTrue(testing.check("({}\t{}\n{})"));

        assertTrue(testing.check("(()\n()\t())"));
        assertTrue(testing.check("(()\n()\t[])"));
        assertTrue(testing.check("(()\n()\t{})"));
        assertTrue(testing.check("(()\n[]\t())"));
        assertTrue(testing.check("(()\n[]\t[])"));
        assertTrue(testing.check("(()\n[]\t{})"));
        assertTrue(testing.check("(()\n{}\t())"));
        assertTrue(testing.check("(()\n{}\t[])"));
        assertTrue(testing.check("(()\n{}\t{})"));
        assertTrue(testing.check("([]\n()\t())"));
        assertTrue(testing.check("([]\n()\t[])"));
        assertTrue(testing.check("([]\n()\t{})"));
        assertTrue(testing.check("([]\n[]\t())"));
        assertTrue(testing.check("([]\n[]\t[])"));
        assertTrue(testing.check("([]\n[]\t{})"));
        assertTrue(testing.check("([]\n{}\t())"));
        assertTrue(testing.check("([]\n{}\t[])"));
        assertTrue(testing.check("([]\n{}\t{})"));
        assertTrue(testing.check("({}\n()\t())"));
        assertTrue(testing.check("({}\n()\t[])"));
        assertTrue(testing.check("({}\n()\t{})"));
        assertTrue(testing.check("({}\n[]\t())"));
        assertTrue(testing.check("({}\n[]\t[])"));
        assertTrue(testing.check("({}\n[]\t{})"));
        assertTrue(testing.check("({}\n{}\t())"));
        assertTrue(testing.check("({}\n{}\t[])"));
        assertTrue(testing.check("({}\n{}\t{})"));

        assertTrue(testing.check("(()\n()\n())"));
        assertTrue(testing.check("(()\n()\n[])"));
        assertTrue(testing.check("(()\n()\n{})"));
        assertTrue(testing.check("(()\n[]\n())"));
        assertTrue(testing.check("(()\n[]\n[])"));
        assertTrue(testing.check("(()\n[]\n{})"));
        assertTrue(testing.check("(()\n{}\n())"));
        assertTrue(testing.check("(()\n{}\n[])"));
        assertTrue(testing.check("(()\n{}\n{})"));
        assertTrue(testing.check("([]\n()\n())"));
        assertTrue(testing.check("([]\n()\n[])"));
        assertTrue(testing.check("([]\n()\n{})"));
        assertTrue(testing.check("([]\n[]\n())"));
        assertTrue(testing.check("([]\n[]\n[])"));
        assertTrue(testing.check("([]\n[]\n{})"));
        assertTrue(testing.check("([]\n{}\n())"));
        assertTrue(testing.check("([]\n{}\n[])"));
        assertTrue(testing.check("([]\n{}\n{})"));
        assertTrue(testing.check("({}\n()\n())"));
        assertTrue(testing.check("({}\n()\n[])"));
        assertTrue(testing.check("({}\n()\n{})"));
        assertTrue(testing.check("({}\n[]\n())"));
        assertTrue(testing.check("({}\n[]\n[])"));
        assertTrue(testing.check("({}\n[]\n{})"));
        assertTrue(testing.check("({}\n{}\n())"));
        assertTrue(testing.check("({}\n{}\n[])"));
        assertTrue(testing.check("({}\n{}\n{})"));

        assertTrue(testing.check("(( )\t()\n())"));
        assertTrue(testing.check("(( )\t()\n[])"));
        assertTrue(testing.check("(( )\t()\n{})"));
        assertTrue(testing.check("(( )\t[]\n())"));
        assertTrue(testing.check("(( )\t[]\n[])"));
        assertTrue(testing.check("(( )\t[]\n{})"));
        assertTrue(testing.check("(( )\t{}\n())"));
        assertTrue(testing.check("(( )\t{}\n[])"));
        assertTrue(testing.check("(( )\t{}\n{})"));
        assertTrue(testing.check("([ ]\t()\n())"));
        assertTrue(testing.check("([ ]\t()\n[])"));
        assertTrue(testing.check("([ ]\t()\n{})"));
        assertTrue(testing.check("([ ]\t[]\n())"));
        assertTrue(testing.check("([ ]\t[]\n[])"));
        assertTrue(testing.check("([ ]\t[]\n{})"));
        assertTrue(testing.check("([ ]\t{}\n())"));
        assertTrue(testing.check("([ ]\t{}\n[])"));
        assertTrue(testing.check("([ ]\t{}\n{})"));
        assertTrue(testing.check("({ }\t()\n())"));
        assertTrue(testing.check("({ }\t()\n[])"));
        assertTrue(testing.check("({ }\t()\n{})"));
        assertTrue(testing.check("({ }\t[]\n())"));
        assertTrue(testing.check("({ }\t[]\n[])"));
        assertTrue(testing.check("({ }\t[]\n{})"));
        assertTrue(testing.check("({ }\t{}\n())"));
        assertTrue(testing.check("({ }\t{}\n[])"));
        assertTrue(testing.check("({ }\t{}\n{})"));

        assertTrue(testing.check("(()\t( )\n())"));
        assertTrue(testing.check("(()\t( )\n[])"));
        assertTrue(testing.check("(()\t( )\n{})"));
        assertTrue(testing.check("(()\t[ ]\n())"));
        assertTrue(testing.check("(()\t[ ]\n[])"));
        assertTrue(testing.check("(()\t[ ]\n{})"));
        assertTrue(testing.check("(()\t{ }\n())"));
        assertTrue(testing.check("(()\t{ }\n[])"));
        assertTrue(testing.check("(()\t{ }\n{})"));
        assertTrue(testing.check("([]\t( )\n())"));
        assertTrue(testing.check("([]\t( )\n[])"));
        assertTrue(testing.check("([]\t( )\n{})"));
        assertTrue(testing.check("([]\t[ ]\n())"));
        assertTrue(testing.check("([]\t[ ]\n[])"));
        assertTrue(testing.check("([]\t[ ]\n{})"));
        assertTrue(testing.check("([]\t{ }\n())"));
        assertTrue(testing.check("([]\t{ }\n[])"));
        assertTrue(testing.check("([]\t{ }\n{})"));
        assertTrue(testing.check("({}\t( )\n())"));
        assertTrue(testing.check("({}\t( )\n[])"));
        assertTrue(testing.check("({}\t( )\n{})"));
        assertTrue(testing.check("({}\t[ ]\n())"));
        assertTrue(testing.check("({}\t[ ]\n[])"));
        assertTrue(testing.check("({}\t[ ]\n{})"));
        assertTrue(testing.check("({}\t{ }\n())"));
        assertTrue(testing.check("({}\t{ }\n[])"));
        assertTrue(testing.check("({}\t{ }\n{})"));

        assertTrue(testing.check("(()\n()\n( ))"));
        assertTrue(testing.check("(()\n()\n[ ])"));
        assertTrue(testing.check("(()\n()\n{ })"));
        assertTrue(testing.check("(()\n[]\n( ))"));
        assertTrue(testing.check("(()\n[]\n[ ])"));
        assertTrue(testing.check("(()\n[]\n{ })"));
        assertTrue(testing.check("(()\n{}\n( ))"));
        assertTrue(testing.check("(()\n{}\n[ ])"));
        assertTrue(testing.check("(()\n{}\n{ })"));
        assertTrue(testing.check("([]\n()\n( ))"));
        assertTrue(testing.check("([]\n()\n[ ])"));
        assertTrue(testing.check("([]\n()\n{ })"));
        assertTrue(testing.check("([]\n[]\n( ))"));
        assertTrue(testing.check("([]\n[]\n[ ])"));
        assertTrue(testing.check("([]\n[]\n{ })"));
        assertTrue(testing.check("([]\n{}\n( ))"));
        assertTrue(testing.check("([]\n{}\n[ ])"));
        assertTrue(testing.check("([]\n{}\n{ })"));
        assertTrue(testing.check("({}\n()\n( ))"));
        assertTrue(testing.check("({}\n()\n[ ])"));
        assertTrue(testing.check("({}\n()\n{ })"));
        assertTrue(testing.check("({}\n[]\n( ))"));
        assertTrue(testing.check("({}\n[]\n[ ])"));
        assertTrue(testing.check("({}\n[]\n{ })"));
        assertTrue(testing.check("({}\n{}\n( ))"));
        assertTrue(testing.check("({}\n{}\n[ ])"));
        assertTrue(testing.check("({}\n{}\n{ })"));

        assertTrue(testing.check("( ([{}\t(\n \t) [\t\t]] )\n )"));
        assertTrue(testing.check("( ([{ \t}(\n\n\n){ \t}(\n \t) {[]}[\t\t]] )\n )"));
        assertTrue(testing.check("( ([{ \t}(\n\n\n){ \t}(\n \t) {\n[( \t []\n( \t\n {}[ \n]))]}[\t\t]] )\n )"));
        assertTrue(testing.check("( ([{}\t(\n \t) [\t()\n[ ]({}) \n([\n()\t[\n]()[\n\n\n\n\t\n\t (\n[\t])]])\t]] )\n )"));
        assertTrue(testing.check("( ([{}\t(\n \t) (){ [\n]\t}[\t(  [\n])\n[ ]({()[\t]}) \n([\n()\t[\n]()[\n\n\n\n\t\n\t (\n[\t])]])\t]] )\n )"));
    }

    @Test
    void checkNonValidStrings() {
        BalancedParenthesesChecker testing = new BalancedParenthesesChecker();
        assertThrows(IllegalArgumentException.class, () -> testing.check("https://youtu.be/05ezsaFPM14"));
        assertFalse(testing.check("("));
        assertFalse(testing.check(")"));
        assertFalse(testing.check("["));
        assertFalse(testing.check(")"));
        assertFalse(testing.check("{"));
        assertFalse(testing.check("}"));
        assertFalse(testing.check("}{"));
        assertFalse(testing.check("(("));
        assertFalse(testing.check("[["));
        assertFalse(testing.check("[}"));
        assertFalse(testing.check("(((({}[\t])))))\n"));
    }
}