package it.unicam.cs.asdl1920.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe per heap binario che contiene elementi non nulli possibilmente
 * ripetuti.
 *
 * @param <E> il tipo degli elementi dello heap, che devono avere un
 *            ordinamento naturale.
 * @author Luca Tesei
 */
public class MaxHeap<E extends Comparable<E>> {
    private final ArrayList<E> heap;

    private int cmpCount;

    /**
     * Costruisce uno heap vuoto.
     */
    public MaxHeap() {
        this.heap = new ArrayList<>();
        this.cmpCount = 0;
    }

    /**
     * Restituisce il numero di elementi nello heap.
     *
     * @return il numero di elementi nello heap
     */
    public int size() {
        return this.heap.size();
    }

    /**
     * Determina se lo heap è vuoto.
     *
     * @return true se lo heap è vuoto.
     */
    public boolean isEmpty() {
        return this.heap.isEmpty();
    }

    /**
     * Costruisce uno heap a partire da una lista di elementi.
     *
     * @param list lista di elementi
     */
    public MaxHeap(List<E> list) {
        this.heap = new ArrayList<>();
        this.cmpCount = 0;
        this.heap.addAll(list);
        // heapify dell'array a partire dalla metà
        // indice da cui partire: padre dell'ultima foglia
        int index = (this.heap.size() - 2) / 2;
        // eseguo heapify dal padre dell'ultima foglia indietro fino alla radice
        for (; index >= 0; index--)
            heapify(index);
    }

    /**
     * Restituisce il numero di confronti fatti durante le operazioni in questo
     * heap a partire dalla creazione dell'oggetto o dall'ultima chiamata
     * {@code resetCompareCount()}
     *
     * @return il numero di confronti fatti durante le operazioni
     */
    public int getCompareCount() {
        return this.cmpCount;
    }

    /**
     * Mette a zero il contatore di confronti fatti durante le operazioni.
     */
    public void resetCompareCount() {
        this.cmpCount = 0;
    }

    /**
     * Inserisce un elemento nello heap
     *
     * @param el l'elemento da inserire
     * @throws NullPointerException se l'elemento è null
     */
    public void insert(E el) {
        if (el == null)
            throw new NullPointerException(
                    "Tentativo di inserire elemento null");
        if (this.heap.isEmpty())
            // inserisco nella posizione 0
            this.heap.add(el);
        else {
            // inserisco nella prima posizione libera e riadatto
            this.heap.add(el);
            // ciclo di scambio col padre fino a quando la proprietà dello heap
            // non è soddisfatta
            int indice = this.heap.size() - 1;
            // Ciclo di scambi
            while (indice > 0) {
                int cmp = this.heap.get(parentIndex(indice))
                        .compareTo(this.heap.get(indice));
                this.cmpCount++;
                if (cmp >= 0)
                    // esco
                    break;
                // altrimenti scambio
                E appoggio = this.heap.get(indice);
                this.heap.set(indice, this.heap.get(parentIndex(indice)));
                this.heap.set(parentIndex(indice), appoggio);
                // vado avanti
                indice = parentIndex(indice);
            }
        }
    }

    /*
     * Funzione di comodo per calcolare l'indice del figlio sinistro del nodo in
     * posizione i
     */
    private int leftIndex(int i) {
        return 2 * i + 1;
    }

    /*
     * Funzione di comodo per calcolare l'indice del figlio destro del nodo in
     * posizione i
     */
    private int rightIndex(int i) {
        return 2 * i + 2;
    }

    /*
     * Funzione di comodo per calcolare l'indice del genitore del nodo in
     * posizione i
     */
    private int parentIndex(int i) {
        // sfruttiamo il fatto che la divisione intera ha sempre come risultato
        // la parte intera della divisione floating point
        return (i - 1) / 2;
    }

    /**
     * Ritorna l'elemento massimo senza toglierlo.
     *
     * @return l'elemento massimo dello heap oppure null se lo heap è vuoto
     */
    public E getMax() {
        if (heap.isEmpty())
            return null;
        else
            return heap.get(0);
    }

    /**
     * Estrae l'elemento massimo dallo heap. Dopo la chiamata tale elemento non
     * è più presente nello heap.
     *
     * @return l'elemento massimo di questo heap.
     */
    public E extractMax() {
        if (heap.isEmpty())
            return null;
        E risultato = this.heap.get(0);
        if (heap.size() == 1) {
            // caso particolare di heap contenente un solo elemento
            heap.clear();
            return risultato;
        }
        // lo heap contiene almeno due elementi
        // metto in testa l'ultimo elemento
        heap.set(0, heap.get(heap.size() - 1));
        // cancello l'ultimo elemento
        heap.remove(heap.size() - 1);
        heapify(0);
        return risultato;
    }

    /*
     * Ricostituisce uno heap a partire dal nodo in posizione i assumendo che i
     * suoi sottoalberi sinistro e destro (se esistono) siano heap.
     */
    private void heapify(int i) {
        int leftIndex = leftIndex(i);
        int rightIndex = rightIndex(i);
        int maxIndex;
        if (leftIndex < heap.size()
                && heap.get(leftIndex).compareTo(heap.get(i)) > 0)
            maxIndex = leftIndex;
        else
            maxIndex = i;
        if (leftIndex < heap.size())
            this.cmpCount++;
        if (rightIndex < heap.size()
                && heap.get(rightIndex).compareTo(heap.get(maxIndex)) > 0)
            maxIndex = rightIndex;
        if (rightIndex < heap.size())
            this.cmpCount++;
        // maxIndex corrisponde alla posizione del valore massimo tra i e i suoi
        // figli
        if (maxIndex != i) {
            // mi scambio col massimo
            E appoggio = heap.get(i);
            heap.set(i, heap.get(maxIndex));
            heap.set(maxIndex, appoggio);
            // richiamo heapify sul maxIndex
            heapify(maxIndex);
        }
    }
}
