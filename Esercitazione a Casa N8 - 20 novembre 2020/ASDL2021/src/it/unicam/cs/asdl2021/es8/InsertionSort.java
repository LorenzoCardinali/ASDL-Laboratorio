package it.unicam.cs.asdl2021.es8;

import java.util.List;

/**
 * Implementazione dell'algoritmo di Insertion Sort integrata nel framework di
 * valutazione numerica. L'implementazione è in loco.
 *
 * @param <E> Una classe su cui sia definito un ordinamento naturale.
 * @author Template: Luca Tesei, Implementazione: Collettiva
 */
public class InsertionSort<E extends Comparable<E>> implements SortingAlgorithm<E> {

    public SortingAlgorithmResult<E> sort(List<E> l) {
        int cCompare = 0;

        for (int i = 1; i < l.size(); i++) {
            E tmp = l.get(i);
            int j = i-1;
            cCompare++;
            while (j > -1 && l.get(j).compareTo(tmp) > 0) {
                cCompare++;
                l.set(j+1,l.get(j));
                j--;
            }
            l.set(j+1,tmp);
        }
        return new SortingAlgorithmResult<>(l, cCompare);
    }

    public String getName() {
        return "InsertionSort";
    }
}
