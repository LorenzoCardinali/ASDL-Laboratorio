package it.unicam.cs.asdl1920.sort;

import java.util.List;

/**
 * Implementazione del QuickSort con scelta della posizione del pivot fissa.
 *
 * @param <E> il tipo degli elementi della sequenza da ordinare.
 * @author Luca Tesei
 */
public class QuickSort<E extends Comparable<E>> implements SortingAlgorithm<E> {

    @Override
    public SortingAlgorithmResult<E> sort(List<E> l) {
        int numeroConfronti = quickSort(l, 0, l.size() - 1);
        return new SortingAlgorithmResult<>(l, numeroConfronti);
    }

    private int quickSort(List<E> a, int p, int r) {
        if (p < r) {
            // la porzione di array(list) contiene almeno due elementi, quindi
            // devo partizionare
            PartitionResult pr = partition(a, p, r);
            int numeroConfronti1 = quickSort(a, p, pr.q - 1);
            int numeroConfronti2 = quickSort(a, pr.q + 1, r);
            return numeroConfronti1 + numeroConfronti2 + pr.numeroConfronti;
        } else // caso in cui la porzione di array(list) contiene 1 elemento
            // (p=r) o è vuota (p>r). Non faccio niente, quindi 0 confronti.
            return 0;
    }

    private PartitionResult partition(List<E> a, int p, int r) {
        // Inizializziamo il pivot
        E x = a.get(r);
        // Inizializziamo il numero di confronti
        int numeroConfronti = 0;
        // Inizializziamo l'indice i
        int i = p - 1;
        for (int j = p; j <= r - 1; j++) {
            int cmp = a.get(j).compareTo(x);
            numeroConfronti++;
            if (cmp <= 0) {
                // l'elemento in posizione j è minore o uguale del pivot
                i = i + 1;
                // scambio l'elemento in posizione i con l'elemento in posizione
                // j
                E appoggio = a.get(i);
                a.set(i, a.get(j));
                a.set(j, appoggio);
            }
        }
        // Scambio l'elemento pivot (in posizione r) con l'elemento in posizione
        // i+1 che è il primo degli elementi maggiori del pivot
        E appoggio = a.get(r);
        a.set(r, a.get(i + 1));
        a.set(i + 1, appoggio);
        // Creiamo l'oggetto risultato
        return new PartitionResult(i + 1, numeroConfronti);
    }

    @Override
    public String getName() {
        return "QuickSort";
    }

    /*
     * Classe di comodo per contenere i due risultati del metodo partition.
     */
    private static class PartitionResult {
        private final int q;

        private final int numeroConfronti;

        /**
         * @param q               Posizione Pivot(?)
         * @param numeroConfronti Confronti
         */
        public PartitionResult(int q, int numeroConfronti) {
            this.q = q;
            this.numeroConfronti = numeroConfronti;
        }

    }

}
