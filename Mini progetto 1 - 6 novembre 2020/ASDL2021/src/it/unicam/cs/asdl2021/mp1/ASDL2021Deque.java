/**
 * 
 */
package it.unicam.cs.asdl2021.mp1;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
 * @author Template: Luca Tesei, Implementation: INSERIRE NOME E COGNOME DELLO
 *         STUDENTE - INSERIRE ANCHE L'EMAIL xxxx@studenti.unicam.it
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

    // TODO implement: possibly insert other private fields that may be needed
    // for implementation

    /**
     * Constructs an empty deque.
     */
    public ASDL2021Deque() {
        // TODO implement
    }

    @Override
    public boolean isEmpty() {
        // TODO implement
        return false;
    }

    @Override
    public Object[] toArray() {
        // TODO implement
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException(
                "This class does not implement this service.");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // TODO implement
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        // TODO implement
        return false;
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
        // TODO implement
    }

    @Override
    public void addFirst(E e) {
        // TODO implement
    }

    @Override
    public void addLast(E e) {
        // TODO implement
    }

    @Override
    public boolean offerFirst(E e) {
        // TODO implement
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        // TODO implement
        return false;
    }

    @Override
    public E removeFirst() {
        // TODO implement
        return null;
    }

    @Override
    public E removeLast() {
        // TODO implement
        return null;
    }

    @Override
    public E pollFirst() {
        // TODO implement
        return null;
    }

    @Override
    public E pollLast() {
        // TODO implement
        return null;
    }

    @Override
    public E getFirst() {
        // TODO implement
        return null;
    }

    @Override
    public E getLast() {
        // TODO implement
        return null;
    }

    @Override
    public E peekFirst() {
        // TODO implement
        return null;
    }

    @Override
    public E peekLast() {
        // TODO implement
        return null;
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
        // TODO implement
        return false;
    }

    @Override
    public boolean offer(E e) {
        // TODO implement
        return false;
    }

    @Override
    public E remove() {
        // TODO implement
        return null;
    }

    @Override
    public E poll() {
        // TODO implement
        return null;
    }

    @Override
    public E element() {
        // TODO implement
        return null;
    }

    @Override
    public E peek() {
        // TODO implement
        return null;
    }

    @Override
    public void push(E e) {
        // TODO implement
    }

    @Override
    public E pop() {
        // TODO implement
        return null;
    }

    @Override
    public boolean remove(Object o) {
        // TODO implement
        return false;
    }

    @Override
    public boolean contains(Object o) {
        // TODO implement
        return false;
    }

    @Override
    public int size() {
        // TODO implement
        return -1;
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
        // TODO implement: insert private fields needed for the implementation
        // and for making the iterator fail-safe

        Itr() {
            // TODO implement
        }

        public boolean hasNext() {
            // TODO implement
            return false;
        }

        public E next() {
            // TODO implement: REMEMBER that the iterator must be fail-safe: if
            // the Deque has been modified by a method of the main class the
            // first attempt to call next() must throw a
            // ConcurrentModificationException
            return null;
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
        // TODO implement: insert private fields needed for the implementation
        // and for making the iterator fail-safe

        DescItr() {
            // TODO implement
        }

        public boolean hasNext() {
            // TODO implement
            return false;
        }

        public E next() {
            // TODO implement: REMEMBER that the iterator must be fail-safe: if
            // the Deque has been modified by a method of the main class the
            // first attempt to call next() must throw a
            // ConcurrentModificationException
            return null;
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
