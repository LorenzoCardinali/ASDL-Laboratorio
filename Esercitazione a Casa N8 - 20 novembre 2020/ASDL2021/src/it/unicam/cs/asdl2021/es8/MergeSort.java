package it.unicam.cs.asdl2021.es8;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione dell'algoritmo di Merge Sort integrata nel framework di
 * valutazione numerica. Non è richiesta l'implementazione in loco.
 *
 * @author Template: Luca Tesei, Implementazione: collettiva
 */
public class MergeSort<E extends Comparable<E>> implements SortingAlgorithm<E> {

    int cCompare = 0;

    public SortingAlgorithmResult<E> sort(List<E> l) {
        cCompare = 0;
        mergeSort(l, 0, l.size() - 1);
        return new SortingAlgorithmResult<>(l, cCompare);
    }

    void mergeSort(List<E> l, int head, int tail) {
        if (head < tail) {

            int mid = (head + tail) / 2;

            mergeSort(l, head, mid);
            mergeSort(l, mid + 1, tail);

            merge(l, head, mid, tail);
        }
    }


    void merge(List<E> l, int head, int mid, int tail) {
        int first1 = head, first2 = mid + 1;

        List<E> tmp = new ArrayList<>();


        for (int i = head; i <= tail; i++) {
            cCompare++;
            if (first1 > mid) {
                tmp.add(l.get(first2));
                first2++;
            } else if (first2 > tail) {
                tmp.add(l.get(first1));
                first1++;
            } else if (l.get(first1).compareTo(l.get(first2)) < 0) {
                tmp.add(l.get(first1));
                first1++;
            } else {
                tmp.add(l.get(first2));
                first2++;
            }
        }

        for (int z = 0; z < tmp.size(); z++) {
            l.set(head, tmp.get(z));
            head++;
        }
    }

    public String getName() {
        return "MergeSort";
    }
}
