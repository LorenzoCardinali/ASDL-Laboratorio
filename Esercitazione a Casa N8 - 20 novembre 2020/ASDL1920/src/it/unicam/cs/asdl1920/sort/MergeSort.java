package it.unicam.cs.asdl1920.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione dell'algoritmo di Merge Sort integrata nel framework di
 * valutazione numerica.
 *
 * @author Luca Tesei
 *
 */
public class MergeSort<E extends Comparable<E>> implements SortingAlgorithm<E> {

    public SortingAlgorithmResult<E> sort(List<E> l) {
        // TOFIX Eliminare il caso della lista vuota
        // Chiamo la procedura effettiva ricorsiva
        return recSort(l, 0, l.size() - 1);
    }

    /*
     * Reale procedura ricorsiva del Merge Sort
     */
    private SortingAlgorithmResult<E> recSort(List<E> l, int start, int stop) {
        if (start == stop) {
            // C'è solo un elemento
            return new SortingAlgorithmResult<>(l, 0);
        }
        int nElements = stop - start + 1;
        int middle = start + (nElements / 2) - 1;
        // L'elemento middle, nel caso in cui il numero degli
        // elementi è dispari, è scelto in modo che l'elemento
        // in più vada nella parte destra
        SortingAlgorithmResult<E> left = this.recSort(l, start, middle);
        SortingAlgorithmResult<E> right = this.recSort(l, middle + 1, stop);
        SortingAlgorithmResult<E> merge = this.merge(l, start, middle, stop);
        return new SortingAlgorithmResult<>(l, left.getCountCompare()
                + right.getCountCompare() + merge.getCountCompare());

    }

    /*
     * Procedura che fa il merge delle sottoliste ordinate che vanno da start a
     * middle e da middle + 1 a stop.
     *
     * Crea delle copie delle sottoliste e scrive nella lista originale l
     */
    private SortingAlgorithmResult<E> merge(List<E> l, int start, int middle,
                                            int stop) {
        int countCompare = 0;
        // Creo due liste accessorie per salvare gli elementi
        // dei due sottoarray ordinati di cui fare il merge
        // Sono costretto a farlo perché il risultato lo scrivo
        // su l, quindi mi scriverei sopra
        List<E> leftCopy = new ArrayList<>();
        for (int i = start; i <= middle; i++)
            leftCopy.add(l.get(i));
        List<E> rightCopy = new ArrayList<>();
        for (int i = middle + 1; i <= stop; i++)
            rightCopy.add(l.get(i));
        int i, j, k;
        // i scorre su l da start a stop
        i = start;
        // j scorre su leftCopy da 0
        j = 0;
        // k scorre su rightCopu da 0
        k = 0;
        while (j < leftCopy.size() && k < rightCopy.size()) {
            // Esiste ancora un elemento in entrambe le sottoliste
            // Faccio un confronto
            countCompare++;
            if (leftCopy.get(j).compareTo(rightCopy.get(k)) < 0) {
                // Metto in posizione i l'elemento della leftCopy
                l.set(i, leftCopy.get(j));
                i++;
                j++;
            } else {
                // Metto in posizione i l'elemento della rightCopy
                l.set(i, rightCopy.get(k));
                i++;
                k++;
            }
        }
        // Una delle due sottoliste è stata completata
        if (j >= leftCopy.size())
            // Metto nella lista l tutti gli elementi che rimangono in rightCopy
            for (; k < rightCopy.size(); k++) {
                l.set(i, rightCopy.get(k));
                i++;
            }
        else
            // Metto nella lista l tutti gli elementi che rimangono in leftCopy
            for (; j < leftCopy.size(); j++) {
                l.set(i, leftCopy.get(j));
                i++;
            }
        // Ritorno il risultato
        return new SortingAlgorithmResult<>(l, countCompare);
    }

    public String getName() {
        return "MergeSort";
    }
}
