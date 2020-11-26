package it.unicam.cs.asdl1920.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che implementa un algoritmo di ordinamento basato su heap.
 *
 * @author Luca Tesei
 */
public class HeapSort<E extends Comparable<E>> implements SortingAlgorithm<E> {

    @Override
    public SortingAlgorithmResult<E> sort(List<E> l) {
        if (l == null)
            throw new NullPointerException(
                    "Tentativo di ordinare una lista vuota");
        if (l.isEmpty())
            return new SortingAlgorithmResult<>(l, 0);
        // Creo uno heap e faccio heapifyzzare la lista
        MaxHeap<E> h = new MaxHeap<>(l);
        // Creo la lista risultato
        List<E> risultato = new ArrayList<>();
        while (h.size() > 1) {
            E max = h.extractMax();
            risultato.add(0, max);
        }
        // aggiungo l'ultimo
        risultato.add(0, h.getMax());
        return new SortingAlgorithmResult<>(risultato, h.getCompareCount());
    }

    @Override
    public String getName() {
        return "HeapSort";
    }

}
