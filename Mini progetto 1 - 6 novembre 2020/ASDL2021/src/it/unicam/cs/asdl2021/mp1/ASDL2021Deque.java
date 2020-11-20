/**
 *
 */
package it.unicam.cs.asdl2021.mp1;

import java.util.*;

/**
 * Implementation of the Java SE Double-ended Queue (Deque) interface
 * (<code>java.util.Deque</code>) based on a double linked list. This deque does
 * not have capacity restrictions, i.e., it is always possible to insert new
 * elements and the number of elements is unbound. Duplicated elements are
 * permitted while <code>null</code> elements are not permitted. Being
 * <code>Deque</code> a sub-interface of
 * <code>Queue<code>, this class can be used also as an implementaion of a <code>Queue</code>
 * and of a <code>Stack</code>.
 *
 * The following operations are not supported:
 * <ul>
 * <li><code>public <T> T[] toArray(T[] a)</code></li>
 * <li><code>public boolean removeAll(Collection<?> c)</code></li>
 * <li><code>public boolean retainAll(Collection<?> c)</code></li>
 * <li><code>public boolean removeFirstOccurrence(Object o)</code></li>
 * <li><code>public boolean removeLastOccurrence(Object o)</code></li>
 * </ul>
 *
 * @author Template: Luca Tesei, Implementation:
 *
 *  Lorenzo Cardinali - lorenz.cardinali@studenti.unicam.it
 *
 */
public class ASDL2021Deque<E> implements Deque<E> {

    /*
     * Current number of elements in this deque
     */
    private int size;

    /*
     * Pointer to the first element of the double-linked list used to implement
     * this deque
     */
    private Node<E> first;

    /*
     * Pointer to the last element of the double-linked list used to implement
     * this deque
     */
    private Node<E> last;

    /*
     * Variabile controllo modifiche
     */
    private int numeroModifiche;

    /**
     * Constructs an empty deque.
     */
    public ASDL2021Deque() {
        //Inizializzo tutte le variabili
        this.size = 0;
        this.first = null;
        this.last = null;
        this.numeroModifiche = 0;
    }

    @Override
    public boolean isEmpty() {
        //Ritorna vero se il size è uguale a 0
        return this.size == 0;
    }

    @Override
    public Object[] toArray() {
        //Inizializzo contatore e array da inviare
        Object[] array = new Object[this.size];
        int i = 0;

        //Inserisco ogni singolo elemento della Deque
        for (Node<E> tmp = first; tmp != null; tmp = tmp.next) {
            array[i] = tmp.item;
            i++;
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("This class does not implement this service.");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        //Controllo della collection
        if (c.contains(null) || c.isEmpty()) {
            throw new NullPointerException("Collection con valori nulli o vuota.");
        }

        boolean trovato = false;

        //Ciclo ogni oggetto della lista e lo invio al metodo "contains"
        for (Object tmp : c) {
            trovato = this.contains(tmp);
        }

        return trovato;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        //Controllo della collection
        if (c.contains(null) || c.isEmpty()) {
            throw new NullPointerException("Collection con valori nulli o vuota.");
        }

        //Ciclo e inserisco ogni elemento della collection con il metodo "add"
        for (E tmp : c) {
            this.offerLast(tmp);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException(
                "This class does not implement this service.");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException(
                "This class does not implement this service.");
    }

    @Override
    public void clear() {
        //Elimino ogni collegamento
        Node<E> tmp = first;
        Node<E> next;
        while (tmp != null) {
            next = tmp.next;
            tmp.item = null;
            tmp.next = null;
            tmp.prev = null;
            tmp = next;
        }
        first = last = null;
        size = 0;
        numeroModifiche++;
    }

    @Override
    public void addFirst(E e) {
        if (e == null) {
            throw new NullPointerException("Elemento nullo.");
        }
        //Sono equivalenti ma non ritorna un boolean
        offerFirst(e);
    }

    @Override
    public void addLast(E e) {
        if (e == null) {
            throw new NullPointerException("Elemento nullo.");
        }
        //Sono equivalenti ma non ritorna un boolean
        offerLast(e);
    }

    @Override
    public boolean offerFirst(E e) {
        //copio il primo nodo e creo il nuovo nodo
        Node<E> n = first;
        Node<E> addNode = new Node<>(null, e, n);
        first = addNode;

        //aggiunta nodo se non c'è un nodo successivo (first==last)
        if (n == null) {
            last = addNode;
        } else { //aggiunta nodo se è presente un successivo (first!=last)
            n.prev = addNode;
        }

        //aumento size e modifiche
        size++;
        numeroModifiche++;

        return true;
    }

    @Override
    public boolean offerLast(E e) {
        //copio l'ultimo nodo e creo il nuovo nodo
        Node<E> n = last;
        Node<E> newNode = new Node<>(n, e, null);
        last = newNode;

        //aggiunta nodo se non c'è un nodo precendente (last==first)
        if (n == null) {
            first = newNode;
        } else { //aggiunta nodo se è presente un successivo (last!=first)
            n.next = newNode;
        }

        //aumento size e modifiche
        size++;
        numeroModifiche++;

        return false;
    }

    @Override
    public E removeFirst() {
        //controllo che la deque non sia vuota
        if (this.isEmpty()) {
            throw new NoSuchElementException("Deque vuota");
        }

        //Sono equivalenti
        return pollFirst();
    }

    @Override
    public E removeLast() {
        //controllo che la deque non sia vuota
        if (this.isEmpty()) {
            throw new NoSuchElementException("Deque vuota");
        }

        //Sono equivalenti
        return pollLast();
    }

    @Override
    public E pollFirst() {
        //controllo che first non sia nullo
        if (this.first == null) {
            return null;
        }

        //copio l'ultimo nodo e creo il nuovo nodo
        E element = this.first.item;
        Node<E> next = this.first.next;
        this.first.item = null;
        this.first.next = null;
        this.first = next;

        //rimozione nodo se non c'è un nodo precendente (first==last)
        if (next == null) {
            last = null;
        } else { //rimozione nodo se è presente un successivo (first!=last)
            next.prev = null;
        }

        //diminuisco size e aumento modifiche
        size--;
        numeroModifiche++;

        //ritorno l'elemento rimosso
        return element;
    }

    @Override
    public E pollLast() {
        //controllo che first non sia nullo
        if (this.last == null) {
            return null;
        }

        //copio il primo nodo e creo il nuovo nodo
        E element = this.last.item;
        Node<E> prev = this.last.prev;
        this.last.item = null;
        this.last.prev = null;
        this.last = prev;

        //rimozione nodo se non c'è un nodo precendente (last==first)
        if (prev == null) {
            first = null;
        } else { //rimozione nodo se è presente un successivo (last!=first)
            prev.next = null;
        }

        //diminuisco size e aumento modifiche
        size--;
        numeroModifiche++;

        //ritorno l'elemento rimosso
        return element;
    }

    @Override
    public E getFirst() {
        //controllo che la deque non sia vuota
        if (this.isEmpty()) {
            throw new NoSuchElementException("Deque vuota");
        }

        return this.first.item;
    }

    @Override
    public E getLast() {
        //controllo che la deque non sia vuota
        if (this.isEmpty()) {
            throw new NoSuchElementException("Deque vuota");
        }

        return this.last.item;
    }

    @Override
    public E peekFirst() {
        if (this.isEmpty()) return null;
        else return this.first.item;
    }

    @Override
    public E peekLast() {
        if (this.isEmpty()) return null;
        else return this.last.item;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        throw new UnsupportedOperationException(
                "This class does not implement this service.");
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        throw new UnsupportedOperationException(
                "This class does not implement this service.");
    }

    @Override
    public boolean add(E e) {
        //Sono equivalenti
        return offerLast(e);
    }

    @Override
    public boolean offer(E e) {
        //Sono equivalenti
        return offerLast(e);
    }

    @Override
    public E remove() {
        //Sono equivalenti
        return removeFirst();
    }

    @Override
    public E poll() {
        //Sono equivalenti
        return pollFirst();
    }

    @Override
    public E element() {
        //Sono equivalenti
        return getFirst();
    }

    @Override
    public E peek() {
        //Sono equivalenti
        return peekFirst();
    }

    @Override
    public void push(E e) {
        //Sono equivalenti ma non ritorna un boolean
        offerFirst(e);
    }

    @Override
    public E pop() {
        //Sono equivalenti
        return removeFirst();
    }

    @Override
    public boolean remove(Object o) {
        //Controllo dell'oggetto
        if (o == null) {
            throw new NullPointerException("Oggetto nullo.");
        }

        //Controllo che l'oggetto sia presente
        if (!this.contains(o)) {
            return false;
        }

        Node<E> tmp = first;
        boolean rimosso = false;

        //ricerca elemento da eliminare
        while (!rimosso) {
            if (tmp.item.equals(o)) {

                if (tmp.prev == null) { //elemento nella posizione "first"
                    pollFirst();
                } else if (tmp.next == null) { //elemento in posizione "last"
                    pollLast();
                } else { //elemento in mezzo alla lista
                    tmp.prev.next = tmp.next;
                    tmp.next.prev = tmp.prev;
                    tmp.prev = null;
                    tmp.next = null;
                    this.size--;
                    this.numeroModifiche++;
                }

                rimosso = true;
            }
            tmp = tmp.next;
        }

        return true;
    }

    @Override
    public boolean contains(Object o) {

        //Controllo dell'oggetto
        if (o == null) {
            throw new NullPointerException("Oggetto nullo.");
        }

        boolean trovato = false;
        Node<E> tmp = this.first;

        //Ricerca oggetto
        while (tmp != null && !trovato) {
            trovato = o.equals(tmp.item);
            tmp = tmp.next;
        }

        return trovato;
    }

    @Override
    public int size() {
        return this.size;
    }

    /*
     * Class for representing the nodes of the double-linked list used to
     * implement this deque. The class and its members/methods are protected
     * instead of private only for JUnit testing purposes.
     */
    protected static class Node<E> {
        protected E item;

        protected Node<E> next;

        protected Node<E> prev;

        protected Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    /*
     * Class for implementing an iterator for this deque. The iterator is
     * fail-safe: it detects if during the iteration a modification to the
     * original deque was done and, if so, it launches a
     * <code>ConcurrentModificationException</code> as soon as a call to the
     * method <code>next()</code> is done.
     */
    private class Itr implements Iterator<E> {

        private Node<E> lastReturned;

        private int modifiche;

        Itr() {
            //Inizializzazione Iteratore
            this.lastReturned = null;
            this.modifiche = ASDL2021Deque.this.numeroModifiche;
        }

        public boolean hasNext() {
            //stato iniziale
            if (this.lastReturned == null) {
                if (ASDL2021Deque.this.first != null) {
                    return true;
                } else {
                    return false;
                }
            } else { //l'iteratore si è mosso almeno una volta
                if (this.lastReturned.next != null) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        public E next() {
            //compare numero modifiche per confermare il "fail-safe"
            if (this.modifiche != ASDL2021Deque.this.numeroModifiche) {
                throw new ConcurrentModificationException("Errore, c'è stata una modifica inaspettata.");
            }

            //controllo che ci sia un next
            if (!this.hasNext()) {
                throw new NoSuchElementException("Non esiste un altro elemento");
            }

            //stato iniziale
            if (lastReturned == null) {
                lastReturned = ASDL2021Deque.this.first;
            } else { //l'iteratore si è mosso almeno una volta
                lastReturned = lastReturned.next;
            }

            //ritorno next item
            return lastReturned.item;
        }
    }

    @Override
    public Iterator<E> descendingIterator() {
        return new DescItr();
    }

    /*
     * Class for implementing a descendign iterator for this deque. The iterator
     * is fail-safe: it detects if during the iteration a modification to the
     * original deque was done and, if so, it launches a
     * <code>ConcurrentModificationException</code> as soon as a call to the
     * method <code>next()</code> is done.
     */
    private class DescItr implements Iterator<E> {

        private Node<E> lastReturned;

        private int modifiche;

        DescItr() {
            this.lastReturned = null;
            this.modifiche = ASDL2021Deque.this.numeroModifiche;
        }

        public boolean hasNext() {
            //stato iniziale
            if (this.lastReturned == null) {
                if (ASDL2021Deque.this.last != null) {
                    return true;
                } else {
                    return false;
                }
            } else { //l'iteratore si è mosso almeno una volta
                if (this.lastReturned.prev != null) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        public E next() {
            //compare numero modifiche per confermare il "fail-safe"
            if (this.modifiche != ASDL2021Deque.this.numeroModifiche) {
                throw new ConcurrentModificationException("Errore, c'è stata una modifica inaspettata.");
            }

            //controllo che ci sia un prev
            if (!this.hasNext()) {
                throw new NoSuchElementException("Non esiste un altro elemento");
            }

            //stato iniziale
            if (lastReturned == null) {
                lastReturned = ASDL2021Deque.this.last;
            } else { //l'iteratore si è mosso almeno una volta
                lastReturned = lastReturned.prev;
            }

            //ritorno prev item
            return lastReturned.item;
        }

    }

    // TODO implement: possibly add private methods for implementation purposes

    /*
     * This method is only for JUnit testing purposes.
     */
    protected Node<E> getFirstNode() {
        return this.first;
    }

    /*
     * This method is only for JUnit testing purposes.
     */
    protected Node<E> getLastNode() {
        return this.last;
    }

}
