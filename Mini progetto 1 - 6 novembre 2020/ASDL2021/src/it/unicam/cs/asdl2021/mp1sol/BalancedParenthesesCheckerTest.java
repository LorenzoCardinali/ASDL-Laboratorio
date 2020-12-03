package it.unicam.cs.asdl2021.mp1sol;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BalancedParenthesesCheckerTest {

    @Test
    final void testCheck() {
        BalancedParenthesesChecker checker = new BalancedParenthesesChecker();
        assertTrue(checker.check(""));
        assertTrue(checker.check("  \t"));
        assertTrue(checker.check(" \n\n\n\n\n\n    \n\t \t  "));
        assertTrue(checker.check(" (( [ \n  ( {\t ( \t) [   ] } ) \n ] ) ) "));
        assertFalse(checker.check("(]"));
        assertFalse(checker.check(")("));
        assertFalse(checker.check("( ([ ) ]) "));
        assertFalse(checker.check("( { } "));
        assertFalse(checker.check("{ (  ) "));
        assertFalse(checker.check("( }(([])))"));
        assertFalse(checker.check(" } (([]))"));
        assertFalse(checker.check(" (\t ) } (([]))"));
        assertThrows(IllegalArgumentException.class, () -> checker.check("( ( \n [ ( P )] \t ))"));
        //assertFalse(checker.check("( ( \n [ ( P )] \t ))"));
        assertThrows(IllegalArgumentException.class, () -> checker.check(" \tPippo\n"));
        //assertFalse(checker.check(" \tPippo\n"));
    }

}
