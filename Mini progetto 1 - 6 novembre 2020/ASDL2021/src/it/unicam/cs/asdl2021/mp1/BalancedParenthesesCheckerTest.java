package it.unicam.cs.asdl2021.mp1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BalancedParenthesesCheckerTest {

    private BalancedParenthesesChecker test = new BalancedParenthesesChecker();

    @Test
    void check() {
        String tmp;

        tmp = " (( [( {\t (\t) [ ] } ) \n ] ) ) ";
        assertTrue(test.check(tmp));

        tmp = " ";
        assertTrue(test.check(tmp));

        tmp = "(([ )] )";
        assertFalse(test.check(tmp));

        tmp = "( { } ";
        assertFalse(test.check(tmp));

        tmp = "}(([]))";
        assertFalse(test.check(tmp));

        tmp = "( ( \n [(P)] \t ))";
        assertFalse(test.check(tmp));
    }
}