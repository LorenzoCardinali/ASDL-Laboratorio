package it.unicam.cs.asdl2021.mp2.Task4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ElMamunCaravanSolverTest {

    @Test
    void solve() {
        assertThrows(NullPointerException.class, () -> new ElMamunCaravanSolver(null));
        // create the two objective functions
        ObjectiveFunction max = new MaximumFunction();
        ObjectiveFunction min = new MinimumFunction();

        // create the expression
        Expression f = new Expression("1+2*3*4+5");

        // create the solver
        ElMamunCaravanSolver solver = new ElMamunCaravanSolver(f);

        /** solve using the max objective function **/
        solver.solve(max);

        // prints “81”
        System.out.println(solver.getOptimalSolution());
        assertTrue(solver.getOptimalSolution() == 81);

        // prints “((1+2)*(3*(4+5)))”
        System.out.println(solver.getOptimalParenthesization());
        assertTrue(solver.getOptimalParenthesization().equals("((1+2)*(3*(4+5)))"));

        /** solve using the min objective function **/
        solver.solve(min);

        // prints “30”
        System.out.println(solver.getOptimalSolution());
        assertTrue(solver.getOptimalSolution() == 30);

        // prints “(1+((2*(3*4))+5))”
        System.out.println(solver.getOptimalParenthesization());
        assertTrue(solver.getOptimalParenthesization().equals("(1+((2*(3*4))+5))"));

        // create the expression
        f = new Expression("1+3*5+3*4+6*4");
        solver = new ElMamunCaravanSolver(f);

        solver.solve(min);

        System.out.println(solver.getOptimalSolution());
        System.out.println(solver.getOptimalParenthesization());
        assertTrue(solver.getOptimalParenthesization().equals("(1+((3*5)+((3*4)+(6*4))))"));

        solver.solve(max);

        System.out.println(solver.getOptimalSolution());
        System.out.println(solver.getOptimalParenthesization());
        assertTrue(solver.getOptimalParenthesization().equals("((1+3)*((5+3)*((4+6)*4)))"));
    }
}