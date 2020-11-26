package it.unicam.cs.asdl1920.sort;

import java.util.List;

/**
 * Implementazione dell'algoritmo di Insertion Sort integrata nel framework di
 * valutazione numerica.
 *
 * @param <E> Una classe su cui sia definito un ordinamento naturale.
 * @author Luca Tesei
 */
public class InsertionSort<E extends Comparable<E>>
        implements SortingAlgorithm<E> {

    public SortingAlgorithmResult<E> sort(List<E> l) {
        int countCompare = 0;
        for (int i = 1; i < l.size(); i++) {
            E appoggio = l.get(i);
            int j = i - 1;
            while (j >= 0 && l.get(j).compareTo(appoggio) > 0) {
                // Devo spostarmi di uno verso sinistra
                // Copio il valore in posizione j nella posizione j + 1
                // Non perdo niente perché la posizione originale di i è
                // salvata in appoggio
                l.set(j + 1, l.get(j));
                j--;
            }
            // countCompare deve essere incrementato con il
            // numero di volte che è stato eseguito il while
            // + 1
            countCompare += i - j; // ((i-1) - j) + 1;
            // L'elemento salvato in appoggio va in posizione j + 1
            if (j != i - 1)
                l.set(j + 1, appoggio);
        }
        return new SortingAlgorithmResult<>(l, countCompare);
    }

    public String getName() {
        return "InsertionSort";
    }
}
