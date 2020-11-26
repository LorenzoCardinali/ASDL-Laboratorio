package it.unicam.cs.asdl1920.sort;

import java.util.List;

/**
 * Implementazione dell'algoritmo Bubble Sort integrata nel framework di
 * valutazione numerica.
 *
 * @author Luca Tesei
 */
public class BubbleSort<E extends Comparable<E>>
        implements SortingAlgorithm<E> {

    public SortingAlgorithmResult<E> sort(List<E> l) {
        int countCompare = 0;
        E appoggio;
        int a;
        // Ciclo esterno che controlla il numero di passate
        for (int i = l.size() - 1; i > 0; i--)
            for (int j = 0; j < i; j++) {
                // Confronto l'elemento in posizione j con il successivo
                countCompare++;
                a = l.get(j).compareTo(l.get(j + 1));
                if (a > 0) {
                    // Scambio
                    appoggio = l.get(j);
                    l.set(j, l.get(j + 1));
                    l.set(j + 1, appoggio);
                }
            }
        return new SortingAlgorithmResult<>(l, countCompare);
    }

    public String getName() {
        return "BubbleSort";
    }
}