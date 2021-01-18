package it.unicam.cs.asdl2021.mp2.Task4;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that solves an instance of the the El Mamun's Caravan problem using
 * dynamic programming.
 * <p>
 * Template: Daniele Marchei and Luca Tesei
 *
 * @Implementation: Lorenzo Cardinali - lorenz.cardinali@studenti.unicam.it
 */
public class ElMamunCaravanSolver {

    // the expression to analyse
    private final Expression expression;

    // table to collect the optimal solution for each sub-problem,
    // protected just for Junit Testing purposes
    protected Integer[][] table;

    // table to record the chosen optimal solution among the optimal solution of
    // the sub-problems, protected just for JUnit Testing purposes
    protected Integer[][] tracebackTable;

    // flag indicating that the problem has been solved at least once
    private boolean solved;

    /**
     * Create a solver for a specific expression.
     *
     * @param expression The expression to work on
     * @throws NullPointerException if the expression is null
     */
    public ElMamunCaravanSolver(Expression expression) {
        if (expression == null) throw new NullPointerException("Creazione di solver con expression null.");
        this.expression = expression;
        int size = this.expression.size();

        this.table = new Integer[size][size];
        this.tracebackTable = new Integer[size][size];
        this.solved = false;
    }

    /**
     * Returns the expression that this solver analyse.
     *
     * @return the expression of this solver
     */
    public Expression getExpression() {
        return this.expression;
    }

    /**
     * Solve the problem on the expression of this solver by using a given
     * objective function.
     *
     * @param function The objective function to be used when deciding which
     *                 candidate to choose
     * @throws NullPointerException if the objective function is null
     */
    public void solve(ObjectiveFunction function) {
        if (function == null) throw new NullPointerException("Funzione nulla.");

        // Variabile di supporto (contiene il size dell'espressione)
        int size = this.expression.size();

        // Riempio la matrice con i valori numerici dell'espressione
        for (int i = 0; i < size; i += 2) {
            this.table[i][i] = (Integer) this.expression.get(i).getValue();
        }

        // For che genstisce l'avanzamento del livello in cui l'algoritmo sta lavorando
        for (int level = 0; level < size - 2; level += 2) {
            // For che genstisce l'avanzamento di i
            for (int i = 0; i < size - level - 2; i += 2) {
                // Avanzamento di j dipendente da i e level
                int j = i + level + 2;

                // Lista di supporto per immagazzinare i valori calcolati
                List<Integer> tempList = new ArrayList<>();

                // For che genstisce l'avanzamento di k
                for (int k = 0; i + k + 2 <= j; k += 2) {

                    // Valori da calcolare
                    Integer a = this.table[i][i + k], b = this.table[i + k + 2][j];

                    // Eseguo il calcolo basandomi sul valore di e[i + k + 1]
                    // (moltiplicazione o addizione)
                    if (this.expression.get(i + k + 1).getValue().equals("+")) {
                        tempList.add(a + b);
                    } else if (this.expression.get(i + k + 1).getValue().equals("*")) {
                        tempList.add(a * b);
                    }
                }
                // Ricavo il "best"
                this.table[i][j] = function.getBest(tempList);

                // Ricavo il k equivalente all'indice del "best"
                this.tracebackTable[i][j] = function.getBestIndex(tempList) * 2;
            }
        }

        // Algoritmo risolto
        this.solved = true;
    }

    /**
     * Returns the current optimal value for the expression of this solver. The
     * value corresponds to the one obtained after the last solving (which used
     * a particular objective function).
     *
     * @return the current optimal value
     * @throws IllegalStateException if the problem has never been solved
     */
    public int getOptimalSolution() {
        if (!this.isSolved()) throw new IllegalArgumentException("Problema mai stato risolto.");

        // Ritorno la soluzione migliore (table[0][size -1])
        return this.table[0][this.expression.size() - 1];
    }

    /**
     * Returns an optimal parenthesization corresponding to an optimal solution
     * of the expression of this solver. The parenthesization corresponds to the
     * optimal value obtained after the last solving (which used a particular
     * objective function).
     * <p>
     * If the expression is just a digit then the parenthesization is the
     * expression itself. If the expression is not just a digit then the
     * parethesization is of the form "(<parenthesization>)". Examples: "1",
     * "(1+2)", "(1*(2+(3*4)))"
     *
     * @return the current optimal parenthesization for the expression of this
     * solver
     * @throws IllegalStateException if the problem has never been solved
     */
    public String getOptimalParenthesization() {
        if (!this.isSolved()) throw new IllegalArgumentException("Problema mai stato risolto.");

        // Richiamo il metodo di ricorsione per ricostruire la parentesizzazione
        // (traceback tra [0] e [size-1])
        return traceBack(0, this.expression.size() - 1);
    }

    /**
     * Determines if the problem has been solved at least once.
     *
     * @return true if the problem has been solved at least once, false
     * otherwise.
     */
    public boolean isSolved() {
        return this.solved;
    }

    @Override
    public String toString() {
        return "ElMamunCaravanSolver for " + expression;
    }

    private String traceBack(int i, int j) {

        // Stampo e[i]
        if (i == j) return this.expression.get(i).toString();

        // Stringa del segno di moltiplicazione o addizione
        String op = this.expression.get(i + this.tracebackTable[i][j] + 1).toString();

        // Ricorsione del metodo di traceBack
        /** "( x e[i + k + 1] y )"
         * x -> traceback( e[i] , e[i + k] )
         * e[i + k + 1] -> segno di moltiplicazione o addizione
         * y -> traceback( e[i + k + 2] , e[j] )
         */
        return "(" + traceBack(i, i + this.tracebackTable[i][j]) + op + traceBack(i + this.tracebackTable[i][j] + 2, j) + ")";
    }
}
