package it.unicam.cs.asdl2021.mp1;

/**
 * An object of this class is an actor that uses an ASDL2021Deque<Character> as
 * a Stack in order to check that a sequence containing the following
 * characters: '(', ')', '[', ']', '{', '}' in any order is a string of balanced
 * parentheses or not. The input is given as a String in which white spaces,
 * tabs and newlines are ignored.
 * <p>
 * Some examples:
 * <p>
 * - " (( [( {\t (\t) [ ] } ) \n ] ) ) " is a string o balanced parentheses - " " is a
 * string of balanced parentheses - "(([)])" is NOT a string of balanced
 * parentheses - "( { } " is NOT a string of balanced parentheses - "}(([]))" is
 * NOT a string of balanced parentheses - "( ( \n [(P)] \t ))" is NOT a string
 * of balanced parentheses
 *
 * @author Template: Luca Tesei, Implementation:
 * <p>
 * Lorenzo Cardinali - lorenz.cardinali@studenti.unicam.it
 */
public class BalancedParenthesesChecker {

    // The stack is to be used to check the balanced parentheses
    private final ASDL2021Deque<Character> stack;

    /**
     * Create a new checker.
     */
    public BalancedParenthesesChecker() {
        this.stack = new ASDL2021Deque<Character>();
    }

    /**
     * Check if a given string contains a balanced parentheses sequence of
     * characters '(', ')', '[', ']', '{', '}' by ignoring white spaces ' ',
     * tabs '\t' and newlines '\n'.
     *
     * @param s the string to check
     * @return true if s contains a balanced parentheses sequence, false
     * otherwise
     * @throws IllegalArgumentException if s contains at least a character
     *                                  different form:'(', ')', '[', ']',
     *                                  '{', '}', white space ' ', tab '\t'
     *                                  and newline '\n'
     */
    public boolean check(String s) {
        //rimuovo caratteri ignorati (spazi, \n e \t)
        s = s.replaceAll("\\s+|\\t+|\\n+", "");

        //loop stringa
        for (int i = 0; i < s.length(); i++) {
            char tmp = s.charAt(i);

            //inserisco parentesi aperte nella stack
            if (tmp == '(' || tmp == '[' || tmp == '{') {
                stack.offerLast(tmp);
                continue;
            }

            //controllo che non venga inserito un carattere non ammesso
            if (tmp != ')' && tmp != ']' && tmp != '}') {
                //return false;
                throw new IllegalArgumentException("Caratteri non validi");
            }

            //se la stack è vuota vuol dire che la stringa ha chiuso una parentesi all'inizio
            //se la stringa è formata da un solo carattere vuol dire che c'è solo una parentesi nella stringa
            if (stack.isEmpty() || s.length() == 1) {
                return false;
            }

            //rimuovo e verifico parentesi dalla stack
            char test;
            switch (tmp) {
                case ')':
                    test = stack.pollLast();
                    if (test == '{' || test == '[') {
                        return false;
                    }
                    break;
                case ']':
                    test = stack.pollLast();
                    if (test == '(' || test == '{') {
                        return false;
                    }
                    break;
                case '}':
                    test = stack.pollLast();
                    if (test == '[' || test == '(') {
                        return false;
                    }
                    break;
            }
        }

        //se la stack è vuota allora la stringa è bilanciata
        return (stack.isEmpty());
    }

}
