package it.unicam.cs.asdl2021.es6;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.*;

/**
 * Lista concatenata singola che non accetta valori null, ma permette elementi
 * duplicati.
 *
 * @param <E> il tipo degli elementi della lista
 * @author Template: Luca Tesei, Implementazione: collettiva
 */
public class ASDL2021SingleLinkedList<E> implements List<E> {

    //todo
    LinkedList<E> list = new LinkedList<E>();

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
     * L'iteratore deve essere Fail-Fast, cioè deve lanciare una eccezione
     * IllegalStateException se a una chiamata di next() si "accorge" che la
     * lista è stata cambiata rispetto a quando l'iteratore è stato creato.
     */
    private class Itr implements Iterator<E> {

        private Node<E> lastReturned;

        private int numeroModificheAtteso;

        private Itr() {
            // All'inizio il cursore è null
            this.lastReturned = null;
            this.numeroModificheAtteso = ASDL2021SingleLinkedList.this.numeroModifiche;
        }

        @Override
        public boolean hasNext() {
            //stato iniziale
            if (this.lastReturned == null) {
                if (ASDL2021SingleLinkedList.this.isEmpty()) {
                    //lista vuota
                    return false;
                } else {
                    return true;
                }
            }

            if (this.lastReturned.next == null) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public E next() {
            if (this.numeroModificheAtteso != ASDL2021SingleLinkedList.this.numeroModifiche) {
                throw new IllegalArgumentException("Errore, c'è stata una modifica inaspettata.");
            }

            if (!this.hasNext()) {
                throw new NoSuchElementException("Non esiste un altro elemento");
            }

            Node<E> n = this.lastReturned.next;
            // aggiorno il cursore
            this.lastReturned = n;
            return n.item;
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
        if (o == null) {
            throw new NullPointerException("Ricerca nulla.");
        }

        boolean trovato = false;
        Node<E> temp = this.head;

        while (temp != null && !trovato) {
            trovato = o.equals(temp.item);
            temp = temp.next;
        }

        return trovato;
    }

    @Override
    public boolean add(E e) {
        if (e == null) {
            throw new NullPointerException("Elemento inserito nullo.");
        }

        Node n = new Node(e, null);

        if (this.size == 0) {
            head = n;
        } else {
            tail.next = n;
        }
        tail = n;
        this.size++;
        this.numeroModifiche++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        // TODO controllare

        if (o == null) {
            throw new NullPointerException("Oggetto inserito nullo.");
        }

        if (!contains(o) || size == 0) {
            return false;
        }

        boolean rimosso = false;

        if (head.item.equals(o)) {
            head = head.next;
            size--;
            this.numeroModifiche++;
            return true;
        }

        for (Node<E> n = head; n != null || !rimosso; n = n.next) {
            if (n.next.equals(o)) {
                if (n.next.next != null) {
                    n.next = n.next.next;
                    n.next.next = null;
                } else {
                    n.next = tail;
                    tail = n;
                }
                size--;
                this.numeroModifiche++;
                rimosso = true;
            }
        }

        return rimosso;
    }

    @Override
    public void clear() {
        // TODO controllare

        for (Node<E> x = head; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x = next;
        }

        head = tail = null;
        size = 0;
        this.numeroModifiche++;
    }

    @Override
    public E get(int index) {
        // TODO controllare

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index oversized");
        }

        int i = 0;
        Node<E> temp = head;
        Node<E> nodo = null;

        while (nodo == null) {
            if (index == i) {
                nodo = temp;
            }
            temp = temp.next;
            i++;
        }

        return nodo.item;
    }

    @Override
    public E set(int index, E element) {
        // TODO controllare

        if (element == null) {
            throw new NullPointerException("Tentativo di inserire un elemento nullo");
        }
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index oversized");
        }

        Node<E> n = head;
        for (int i = 0; i != index; i++) {
            if (i == index) {
                n.item = element;
                this.numeroModifiche++;
            }
            n = n.next;
        }

        return null;
    }

    @Override
    public void add(int index, E element) {
        // TODO controllare

        if (element == null) {
            throw new NullPointerException("Tentativo di inserire un elemento nullo");
        }
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index oversized");
        }

        if (this.size == index) {
            this.add(element);
            return;
        }

        //aggiunta nuova testa (index == 0)
        if (index == 0) {
            Node<E> testa = new Node(element, this.head);
            this.head = testa;
            this.size++;
            this.numeroModifiche++;
            return;
        }

        Node<E> n = this.head;

        for (int i = 0; i != size; i++) {
            if (i == index - 1) {
                Node<E> elemento = new Node(element, n.next);
                n.next = elemento;
                this.size++;
                this.numeroModifiche++;
                return;
            }

            n = n.next;
        }
    }

    @Override
    public E remove(int index) {
        // TODO controllare

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index oversized");
        }

        E element = null;
        Node<E> n = this.head;

        //caso in cui c'è solo un elemento
        if (this.size - 1 == index  && index == 0) {
            element = head.item;
            this.clear();
        } else //caso in cui il remove rimuove il primo elemento (index == 0)
            if (index == 0) {
                element = head.item;
                head.item = head.next.item;
                head.next = head.next.next;
            } else { //resto dei casi
                for (int i = 0; i != size; i++) {
                    if (i == index - 1) {
                        element = n.next.item;
                        n.next.item = null;
                        n.next = n.next.next;
                        n.next.next = null;
                        if (index == size-1) { //se l'elemento da rimuovere è anche il tail (index == size-1)
                            tail.item = n.item;
                            tail.next = n.next;
                        }
                    }

                    n = n.next;
                }
            }

        this.numeroModifiche++;
        return element;
    }

    @Override
    public int indexOf(Object o) {
        // TODO controllare

        if (o == null) {
            throw new NullPointerException("Oggetto inserito nullo.");
        }

        if (!contains(o)) {
            return -1;
        }

        int posizione = -1;
        Node<E> n = this.head;

        for (int i = 0; i != size && posizione != -1; i++) {
            if (o.equals(n.item)) {
                posizione = i;
            }
            n = n.next;
        }

        return posizione;
    }

    @Override
    public int lastIndexOf(Object o) {
        // TODO controllare

        if (o == null) {
            throw new NullPointerException("Oggetto inserito nullo.");
        }

        if (!contains(o)) {
            return -1;
        }

        int posizione = -1;
        Node<E> n = this.head;

        for (int i = 0; i != size; i++) {
            if (o.equals(n.item)) {
                posizione = i;
            }
            n = n.next;
        }

        return posizione;
    }

    @Override
    public Object[] toArray() {
        Object[] list = new Object[size];
        int i = 0;
        for (Node<E> x = head; x != null; x = x.next) {
            list[i++] = x.item;
        }
        return list;
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
