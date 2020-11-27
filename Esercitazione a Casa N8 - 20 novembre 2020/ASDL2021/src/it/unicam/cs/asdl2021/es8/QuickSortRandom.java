/**
 *
 */
package it.unicam.cs.asdl2021.es8;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Implementazione del QuickSort con scelta della posizione del pivot scelta
 * randomicamente tra le disponibili. L'implementazione è in loco.
 *
 * @author Template: Luca Tesei, Implementazione: collettiva
 * @param <E> il tipo degli elementi della sequenza da ordinare.
 *
 */
public class QuickSortRandom<E extends Comparable<E>> implements SortingAlgorithm<E> {

    private static final Random randomGenerator = new Random();

    int cCompare = 0;

    @Override
    public SortingAlgorithmResult<E> sort(List<E> l) {
        cCompare = 0;
        quickSort(l, 0, l.size() - 1);
        return new SortingAlgorithmResult<>(l, cCompare);
    }

    private int partition(List<E> l, int head, int tail) {
        int i = head + 1;
        E tmp = l.get(head);
        for (int j = head + 1; j <= tail; j++) {
            cCompare++;
            if (l.get(j).compareTo(tmp) < 0) {
                swap(l, i, j);
                i += 1;
            }
        }
        swap(l, head, i - 1);
        return i - 1;
    }

    private void quickSort(List<E> l, int head, int tail) {
        if (head < tail) {
            int pivot = randomPartition(l, head, tail);
            quickSort(l, head, pivot - 1);
            quickSort(l, pivot + 1, tail);
        }
    }

    private void swap(List<E> l, int x, int y) {
        E tmp = l.get(x);
        l.set(x, l.get(y));
        l.set(y, tmp);
    }

    private int randomPartition(List<E> l, int head, int tail) {
        int random = randomGenerator.ints(head, tail).findFirst().getAsInt();
        swap(l, random, head);
        return partition(l, head, tail);
    }

    @Override
    public String getName() {
        return "QuickSortRandom";
    }

}
