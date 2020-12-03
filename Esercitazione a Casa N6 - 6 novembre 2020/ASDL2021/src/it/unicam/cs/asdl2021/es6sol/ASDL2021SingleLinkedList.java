package it.unicam.cs.asdl2021.es6sol;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Lista concatenata singola che non accetta valori null, ma permette elementi
 * duplicati.
 * 
 * @author Luca Tesei
 *
 * @param <E>
 *                il tipo degli elementi della lista
 */
public class ASDL2021SingleLinkedList<E> implements List<E> {

    private int size;

    private Node<E> head;

    private Node<E> tail;

    private int numeroModifiche;

    /**
     * Crea una lista vuota.
     */
    public ASDL2021SingleLinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
        this.numeroModifiche = 0;
    }

    /*
     * Classe per i nodi della lista concatenata
     */
    private static class Node<E> {
        private E item;

        private Node<E> next;

        /*
         * Crea un nodo "singolo" equivalente a una lista con un solo elemento.
         */
        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }

    }

    /*
     * Classe che realizza un iteratore per ASDL2021SingleLinkedList.
     * L'iteratore deve essere fail-safe, cioè deve lanciare una eccezione
     * IllegalStateException se a una chiamata di next() si "accorge" che la
     * lista è stata cambiata rispetto a quando l'iteratore è stato creato.
     */
    private class Itr implements Iterator<E> {

        private Node<E> lastReturned;

        private int numeroModificheAtteso;

        private Itr() {
            // All'inizio non è stato fatto nessun next
            this.lastReturned = null;
            this.numeroModificheAtteso = ASDL2021SingleLinkedList.this.numeroModifiche;
        }

        @Override
        public boolean hasNext() {
            if (this.lastReturned == null)
                // sono all'inizio dell'iterazione
                return ASDL2021SingleLinkedList.this.head != null;
            else
                // almeno un next è stato fatto
                return lastReturned.next != null;

        }

        @Override
        public E next() {
            // controllo concorrenza
            if (this.numeroModificheAtteso != ASDL2021SingleLinkedList.this.numeroModifiche) {
                throw new ConcurrentModificationException(
                        "Lista modificata durante l'iterazione");
            }
            // controllo hasNext()
            if (!hasNext())
                throw new NoSuchElementException(
                        "Richiesta di next quando hasNext è falso");
            // c'è sicuramente un elemento di cui fare next
            // aggiorno lastReturned e restituisco l'elemento next
            if (this.lastReturned == null) {
                // sono all’inizio e la lista non è vuota
                this.lastReturned = ASDL2021SingleLinkedList.this.head;
                return ASDL2021SingleLinkedList.this.head.item;
            } else {
                // non sono all’inizio, ma c’è ancora qualcuno
                lastReturned = lastReturned.next;
                return lastReturned.item;
            }

        }

    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null)
            throw new NullPointerException(
                    "Richiesto contains di elemento null");
        Node<E> n = this.head;
        // cerco un elemento uguale a o
        while (n != null)
            if (o.equals(n.item)) {
                return true;
            } else
                n = n.next;
        return false;

    }

    @Override
    public boolean add(E e) {
        if (e == null)
            throw new NullPointerException(
                    "Tentativo di aggiungere un elemento null");
        // creo il nodo per il nuovo elemento da mettere in coda
        Node<E> n = new Node<E>(e, null);
        if (this.size == 0) {
            // attualmente la lista è vuota
            this.head = n;
            this.tail = n;
        } else {
            // c'è almeno un elemento
            this.tail.next = n;
            this.tail = n;
        }
        this.size++;
        this.numeroModifiche++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null)
            throw new NullPointerException(
                    "Tentativo di rimuovere un oggetto null");
        int index = this.indexOf(o);
        if (index == -1)
            // oggetto non presente
            return false;
        // rimuovo la prima occorrenza dell'oggetto in posizione index
        this.remove(index);
        return true;
    }

    @Override
    public void clear() {
        // scollego tutta la catena
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.numeroModifiche++;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException(
                    "Tentativo di accesso a un indice non valido della lista");
        // so che l'indice è valido, quindi sotto non controllo il next == null
        Node<E> n = this.head;
        int count = 0;
        while (count < index) {
            count++;
            n = n.next;
        }
        // n punta al nodo di indice index
        return n.item;
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException(
                    "Tentativo di accesso a un indice non valido della lista");
        if (element == null)
            throw new NullPointerException(
                    "Tentativo di aggiornare una posizione della lista con null");
        // so che l'indice è valido, quindi sotto non controllo il next == null
        Node<E> n = this.head;
        int count = 0;
        while (count < index) {
            count++;
            n = n.next;
        }
        // n punta al nodo di indice index, aggiorno l'item
        E result = n.item;
        n.item = element;
        return result;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException(
                    "Tentativo di accesso a un indice non valido della lista");
        if (element == null)
            throw new NullPointerException(
                    "Tentativo di aggiungere un elemento");
        // so che l'indice è valido, quindi sotto non controllo il next == null
        Node<E> previous = null;
        Node<E> n = this.head;
        int count = 0;
        while (count < index) {
            count++;
            previous = n;
            n = n.next;
        }
        // n punta al nodo di indice index e previous punta al nodo precedente
        // nella lista
        // creo un nuovo nodo facendolo puntare all'elemento corrente in
        // posizione index
        Node<E> newNode = new Node<E>(element, n);
        if (previous == null)
            // sto inserendo in testa
            this.head = newNode;
        else
            // sto inserendo in una posizione che non è head, può essere anche
            // tail, ma non importa: il nodo tail rimane lo stesso nodo
            previous.next = newNode;
        // aggiorno size e modifiche
        this.size++;
        this.numeroModifiche++;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException(
                    "Tentativo di accesso a un indice non valido della lista");
        // so che l'indice è valido, quindi sotto non controllo il next == null
        Node<E> previous = null;
        Node<E> n = this.head;
        int count = 0;
        while (count < index) {
            count++;
            previous = n;
            n = n.next;
        }
        // n punta al nodo di indice index e previous punta al nodo precedente
        // nella lista
        if (previous == null) {
            // sto eleminando l'elemento in testa alla lista
            if (n.next == null) {
                // l'elemento in testa è anche in coda, la lista si svuota
                this.head = null;
                this.tail = null;
            } else {
                // l'elemento in testa ha almeno un elemento successivo
                this.head = n.next;
            }
        } else {
            // sto eliminando un elemento non in testa
            if (n.next == null) {
                // sto eliminando l'elemento in coda
                previous.next = null;
                this.tail = previous;
            } else {
                // sto eliminando un elemento centrale
                previous.next = n.next;
            }
        }
        // aggiorno la size e il numero modifiche
        this.size--;
        this.numeroModifiche++;
        return n.item;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null)
            throw new NullPointerException("Richiesto index di elemento null");
        Node<E> n = this.head;
        int index = -1;
        // cerco un elemento uguale a o
        while (n != null) {
            index++;
            // l'indice del nodo n è index
            if (o.equals(n.item))
                // prima occorrenza dell'elemento trovata
                return index;
            else
                n = n.next;
        }
        // elemento non trovato
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null)
            throw new NullPointerException("Richiesto index di elemento null");
        // parto dal nodo testa
        Node<E> n = this.head;
        int index = -1;
        int lastIndex = -1;
        // cerco un elemento uguale a o fino a quando non ho guardato tutti gli
        // elementi
        while (n != null) {
            index++;
            // l'indice del nodo n è index
            if (o.equals(n.item))
                // trovata una occorrenza dell'elemento
                lastIndex = index;
            // vado avanti
            n = n.next;
        }
        return lastIndex;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[this.size];
        int i = 0;
        // uso il foreach
        for (E el : this) {
            result[i] = el;
            i++;
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Operazione non supportata.");
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Operazione non supportata.");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Operazione non supportata.");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Operazione non supportata.");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Operazione non supportata.");
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Operazione non supportata.");
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Operazione non supportata.");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Operazione non supportata.");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Operazione non supportata.");
    }
}
