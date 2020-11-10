package it.unicam.cs.asdl2021.es6;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Lista concatenata singola che non accetta valori null, ma permette elementi
 * duplicati.
 * 
 * @author Template: Luca Tesei, Implementazione: collettiva
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
            // All'inizio il cursore è null
            this.lastReturned = null;
            this.numeroModificheAtteso = ASDL2021SingleLinkedList.this.numeroModifiche;
        }

        @Override
        public boolean hasNext() {
            // TODO implementare
            return false;
        }

        @Override
        public E next() {
            // TODO implementare
            return null;
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
        // TODO implementare
        return false;
    }

    @Override
    public boolean add(E e) {
        // TODO implementare
        return false;
    }

    @Override
    public boolean remove(Object o) {
        // TODO implementare
        return false;
    }

    @Override
    public void clear() {
        // TODO implementare
    }

    @Override
    public E get(int index) {
        // TODO implementare
        return null;
    }

    @Override
    public E set(int index, E element) {
        // TODO implementare
        return null;
    }

    @Override
    public void add(int index, E element) {
        // TODO implementare
    }

    @Override
    public E remove(int index) {
        // TODO implementare
        return null;
    }

    @Override
    public int indexOf(Object o) {
        // TODO implementare
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        // TODO implementare
        return -1;
    }
    
    @Override
    public Object[] toArray() {
        // TODO implementare
        return null;
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
