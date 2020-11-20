package it.unicam.cs.asdl2021.mp1;

import java.util.*;

public class Prove {

    // function to check if brackets are balanced
    static boolean areBracketsBalanced(String s) {
        // Using ArrayDeque is faster than using Stack class
        Deque<Character> stack = new ArrayDeque<Character>();

        System.out.println(s);
        //rimuovo caratteri ignorati (spazi, \n e \t)
        s = s.replaceAll("\\s+|\\t+|\\n+","");
        System.out.println(s);
        //loop stringa
        for (int i = 0; i < s.length(); i++) {
            char tmp = s.charAt(i);

            //inserisco parentesi aperte nella stack
            if (tmp == '(' || tmp == '[' || tmp == '{') {
                stack.offerLast(tmp);
                continue;
            }

            //se la stack è vuota vuol dire che la stringa ha chiuso una parentesi all'inizio
            if (stack.isEmpty())  {
                return false;
            }

            //rimuovo e verifico parentesi dalla stack
            char test;
            switch (tmp) {
                case ')' :
                    test = stack.pollLast();
                    if (test == '{' || test == '[') {
                        return false;
                    }
                    break;
                case ']' :
                    test = stack.pollLast();
                    if (test == '(' || test == '{') {
                        return false;
                    }
                    break;
                case '}' :
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

    // Driver code
    public static void main(String[] args) {
        String expr = " (( [( {\t (\t) [ ] } ) \n ] ) ) ";

        // Function call
        if (areBracketsBalanced(expr))
            System.out.println("Balanced ");
        else
            System.out.println("Not Balanced ");
    }
}

