package it.unicam.cs.asdl2021.es7;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Classe che implementa uno heap binario che può contenere elementi non nulli
 * possibilmente ripetuti.
 *
 * @param <E> il tipo degli elementi dello heap, che devono avere un ordinamento naturale.
 * @author Template: Luca Tesei, Implementation: collettiva
 */
public class MaxHeap<E extends Comparable<E>> {

    /*
     * L'array che serve come base per lo heap
     */
    private ArrayList<E> heap;

    /**
     * Costruisce uno heap vuoto.
     */
    public MaxHeap() {
        this.heap = new ArrayList<E>();
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
        if (list.isEmpty()) {
            throw new NullPointerException("Lista vuota");
        }

        //inizializzo heap
        this.heap = new ArrayList<E>();

        for (E tmp : list) {
            heap.add(tmp);
        }

        //index dell'ultimo nodo
        int last = parentIndex(heap.size() - 1);

        for (int i = last; i >= 0; i--) {
            heapify(i);
        }
    }

    /**
     * Inserisce un elemento nello heap
     *
     * @param el l'elemento da inserire
     * @throws NullPointerException se l'elemento è null
     */
    public void insert(E el) {
        if (el == null) {
            throw new NullPointerException("Elemento nullo");
        }

        heap.add(el);

        //index dell'ultimo nodo
        int last = parentIndex(heap.size() - 1);

        for (int i = last; i >= 0; i--) {
            heapify(i);
        }
    }

    /*
     * Funzione di comodo per calcolare l'indice del figlio sinistro del nodo in
     * posizione i. Si noti che la posizione 0 è significativa e contiene sempre
     * la radice dello heap.
     */
    private int leftIndex(int i) {
        if (heap.size() - 1 < i || i < 0) {
            throw new IllegalArgumentException("Index non valido");
        }

        return i * 2 + 1;
    }

    /*
     * Funzione di comodo per calcolare l'indice del figlio destro del nodo in
     * posizione i. Si noti che la posizione 0 è significativa e contiene sempre
     * la radice dello heap.
     */
    private int rightIndex(int i) {
        if (heap.size() - 1 < i || i < 0) {
            throw new IllegalArgumentException("Index non valido");
        }

        return i * 2 + 2;
    }

    /*
     * Funzione di comodo per calcolare l'indice del genitore del nodo in
     * posizione i. Si noti che la posizione 0 è significativa e contiene sempre
     * la radice dello heap.
     */
    private int parentIndex(int i) {
        if (heap.size() - 1 < i || i < 0) {
            throw new IllegalArgumentException("Index non valido");
        }

        return (i - 1) / 2;
    }

    /**
     * Ritorna l'elemento massimo senza toglierlo.
     *
     * @return l'elemento massimo dello heap oppure null se lo heap è vuoto
     */
    public E getMax() {
        if (heap.isEmpty()) {
            return null;
        } else {
            return heap.get(0);
        }
    }

    /**
     * Estrae l'elemento massimo dallo heap.
     * Dopo la chiamata tale elemento non è più presente nello heap.
     *
     * @return l'elemento massimo di questo heap.
     */
    public E extractMax() {
        if(heap.isEmpty()) {
            return null;
        }

        E tmp = heap.get(0);

        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);

        heapify(0);

        return tmp;
    }

    /*
     * Ricostituisce uno heap a partire dal nodo in posizione i assumendo che i
     * suoi sottoalberi sinistro e destro (se esistono) siano heap.
     */
    private void heapify(int i) {
        if (heap.size() < i || i < 0) {
            throw new IllegalArgumentException("Index non valido");
        }

        if (isLeaf(i)) return;

        int left = leftIndex(i), right = rightIndex(i);

        if (left == heap.size() - 1) {
            if (heap.get(i).compareTo(heap.get(left)) < 0) {
                swap(i, left);
                return;
            }
        } else if (heap.get(i).compareTo(heap.get(left)) < 0 || heap.get(i).compareTo(heap.get(right)) < 0) {
            if (heap.get(left).compareTo(heap.get(right)) > 0) {
                swap(i, left);
                heapify(left);
            } else {
                swap(i, right);
                heapify(right);
            }
        }
    }

    /*
     * Scambia 2 nodi
     */
    private void swap(int fpos, int spos) {
        E tmp;
        tmp = heap.get(fpos);
        heap.set(fpos, heap.get(spos));
        heap.set(spos, tmp);
    }

    /*
     * return true se la posizione i è una foglia
     */
    private boolean isLeaf(int i) {
        if (i >= (heap.size() / 2) && i <= heap.size()) {
            return true;
        }
        return false;
    }

    /**
     * Only for JUnit testing purposes.
     *
     * @return the arraylist representing this max heap
     */
    protected ArrayList<E> getHeap() {
        return this.heap;
    }
}
