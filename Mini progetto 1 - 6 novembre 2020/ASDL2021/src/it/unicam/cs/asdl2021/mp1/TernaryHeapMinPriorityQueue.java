package it.unicam.cs.asdl2021.mp1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Class that provides an implementation of a "dynamic" min-priority queue based
 * on a ternary heap. "Dynamic" means that the priority of an element already
 * present in the queue may be decreased, so possibly this element may become
 * the new minumum element. The elements that can be inserted may be of any
 * class implementing the interface <code>PriorityQueueElement</code>. This
 * min-priority queue does not have capacity restrictions, i.e., it is always
 * possible to insert new elements and the number of elements is unbound.
 * Duplicated elements are permitted while <code>null</code> elements are not
 * permitted.
 *
 * @author Template: Luca Tesei, Implementation:
 * <p>
 * Lorenzo Cardinali - lorenz.cardinali@studenti.unicam.it
 */
public class TernaryHeapMinPriorityQueue {

    /*
     * ArrayList for representing the ternary heap. Use all positions, including
     * position 0 (the JUnit tests will assume so). You have to adapt
     * child/parent indexing formulas consequently.
     */
    private final ArrayList<PriorityQueueElement> heap;

    // TODO implement: possibly insert other private fields that may be needed for implementation

    /**
     * Create an empty queue.
     */
    public TernaryHeapMinPriorityQueue() {
        this.heap = new ArrayList<PriorityQueueElement>();
    }

    /**
     * Return the current size of this queue.
     *
     * @return the number of elements currently in this queue.
     */
    public int size() {
        return this.heap.size();
    }

    /**
     * Add an element to this min-priority queue. The current priority
     * associated with the element will be used to place it in the correct
     * position in the ternary heap. The handle of the element will also be set
     * accordingly.
     *
     * @param element the new element to add
     * @throws NullPointerException if the element passed is null
     */
    public void insert(PriorityQueueElement element) {
        //controllo elemento
        if (element == null) {
            throw new NullPointerException("Elemento inserito nullo.");
        }

        //inserisco l'indice dell'elemento
        element.setHandle(size());
        heap.add(element);

        //index dell'ultimo nodo
        int last = heap.size() - 1;

        //Sistemo min-heap con il nuovo elemento
        while (last > 0) {
            int lastParent = parentIndex(last);
            if (element.getPriority() < heap.get(lastParent).getPriority()) {
                swap(last, lastParent);
                last = lastParent;
            } else break;
        }
    }

    /**
     * Returns the current minimum element of this min-priority queue without
     * extracting it. This operation does not affect the ternary heap.
     *
     * @return the current minimum element of this min-priority queue
     * @throws NoSuchElementException if this min-priority queue is empty
     */
    public PriorityQueueElement minimum() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Lista min-priority vuota");
        }
        return heap.get(0);
    }

    /**
     * Extract the current minimum element from this min-priority queue. The
     * ternary heap will be updated accordingly.
     *
     * @return the current minimum element
     * @throws NoSuchElementException if this min-priority queue is empty
     */
    public PriorityQueueElement extractMinimum() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Lista min-priority vuota");
        }

        //salvo il minimo (testa del heap)
        PriorityQueueElement tmp = minimum();

        //scambio il minimo con la fine della lista (ultima foglia del heap)
        swap(0, size() - 1);

        //rimuovo l'ultima foglia del heap
        heap.remove(heap.size() - 1);

        //sistemo l'heap
        heapify(0);

        //setto l'handle dell'elemento minimo a 0
        tmp.setHandle(0);

        //ritorno il minimo
        return tmp;
    }

    /**
     * Decrease the priority associated to an element of this min-priority
     * queue. The position of the element in the ternary heap must be changed
     * accordingly. The changed element may become the minimum element. The
     * handle of the element will also be changed accordingly.
     *
     * @param element     the element whose priority will be decreased, it
     *                    must currently be inside this min-priority queue
     * @param newPriority the new priority to assign to the element
     * @throws NoSuchElementException   if the element is not currently
     *                                  present in this min-priority queue
     * @throws IllegalArgumentException if the specified newPriority is not
     *                                  strictly less than the current
     *                                  priority of the element
     */
    public void decreasePriority(PriorityQueueElement element, double newPriority) {

        if (element == null) {
            throw new NullPointerException("Elemento nullo");
        }

        boolean trovato = false;
        int indexElement = 0;

        //cerco l'elemento nel heap e se c'è acquisisco l'indice
        while (indexElement < heap.size() && trovato != true) {
            if (this.heap.get(indexElement).getPriority() == element.getPriority()) {
                trovato = true;
                break;
            }
            indexElement++;
        }
        if (!trovato) {
            throw new NoSuchElementException("Elemento non presente");
        }

        //verifico la priorità
        if (!(newPriority < heap.get(indexElement).getPriority())) {
            throw new IllegalArgumentException("Nuova priorità non abbastanza bassa");
        }

        //setto la nuova priorità
        this.heap.get(indexElement).setPriority(newPriority);

        //index del nodo dell'elemento
        int last = parentIndex(indexElement);

        //Ricostruisco min-heap con il nuovo elemento
        for (int i = last; i >= 0; i--) {
            heapify(i);
        }
    }

    /**
     * Erase all the elements from this min-priority queue. After this operation
     * this min-priority queue is empty.
     */
    public void clear() {
        this.heap.clear();
    }

    /*
     * Ricostituisce uno heap a partire dal nodo in posizione i assumendo che i
     * suoi sottoalberi sinistro e destro (se esistono) siano heap.
     */
    private void heapify(int i) {
        if (size() < i || i < 0) {
            throw new IllegalArgumentException("Index non valido (heapify) " + i);
        }

        //Verifica se sono arrivato ad una foglia
        if (isLeaf(i)) return;

        //Trovo i 3 indici delle foglie appartenenti al ramo i
        int left = leftIndex(i), center = centerIndex(i), right = rightIndex(i);

        //caso in cui il ramo i ha solo 1 foglia
        if (left == size() - 1) {
            if (heap.get(left).getPriority() < heap.get(i).getPriority()) {
                swap(i, left);
            }
        } else //caso in cui il ramo i ha solo 2 foglie
            if (left == size() - 2) {
                if (heap.get(left).getPriority() < heap.get(i).getPriority()
                        || heap.get(center).getPriority() < heap.get(i).getPriority()) {

                    if (heap.get(left).getPriority() < heap.get(center).getPriority()) {
                        swap(i, left);
                    } else {
                        swap(i, center);
                    }
                }
            } else //caso in cui l'indice i è completo (3 foglie)
                if (heap.get(left).getPriority() < heap.get(i).getPriority()
                        || heap.get(center).getPriority() < heap.get(i).getPriority()
                        || heap.get(right).getPriority() < heap.get(i).getPriority()) {

                    //Cerco indice della foglia minore tra tutte
                    int min = left;
                    if (heap.get(center).getPriority() < heap.get(min).getPriority()) {
                        min = center;
                    }
                    if (heap.get(right).getPriority() < heap.get(min).getPriority()) {
                        min = right;
                    }

                    swap(i, min);
                    heapify(min);
                }
    }

    //Scambia 2 nodi
    private void swap(int fpos, int spos) {
        //Variabili di copia
        PriorityQueueElement tmp1, tmp2;

        //copio il primo elemento con Handle del secondo elemento
        tmp1 = this.heap.get(fpos);
        tmp1.setHandle(spos);

        //copio il secondo elemento con Handle del primo elemento
        tmp2 = this.heap.get(spos);
        tmp2.setHandle(fpos);

        //eseguo lo scambio
        this.heap.set(fpos, tmp2);
        this.heap.set(spos, tmp1);
    }

    //Return true se la posizione i è una foglia
    private boolean isLeaf(int i) {

        //i è una foglia solo se il suo figlio sinistro non esiste (quindi maggiore o uguale al size)
        return leftIndex(i) >= this.size();
    }

    //Restituisce la foglia sinistra del ramo i
    private int leftIndex(int i) {
        if (i > size() || i < 0) {
            throw new IllegalArgumentException("Index non valido (leftIndex) " + i);
        }

        return i * 3 + 1;
    }

    //Restituisce la foglia centrale del ramo i
    private int centerIndex(int i) {
        if (i > size() || i < 0) {
            throw new IllegalArgumentException("Index non valido (centerIndex) " + i);
        }

        return i * 3 + 2;
    }

    //Restituisce la foglia destra del ramo i
    private int rightIndex(int i) {
        if (i > size() || i < 0) {
            throw new IllegalArgumentException("Index non valido (rightIndex) " + i);
        }

        return i * 3 + 3;
    }

    //Restituisce il ramo di i
    private int parentIndex(int i) {
        if (size() < i || i < 0) {
            throw new IllegalArgumentException("Index non valido (parentIndex) " + i);
        }

        if (i == 0) {
            return 0;
        } else {
            return (i - 1) / 3;
        }
    }

    // TODO implement: possibly add private methods for implementation purposes

    /*
     * This method is only for JUnit testing purposes.
     */
    protected ArrayList<PriorityQueueElement> getTernaryHeap() {
        return this.heap;
    }

}