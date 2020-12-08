/**
 *
 */
package it.unicam.cs.asdl2021.es9;

import java.util.*;

/**
 * Realizza un insieme tramite una tabella hash con indirizzamento primario (la
 * funzione di hash primario deve essere passata come parametro nel costruttore
 * e deve implementare l'interface PrimaryHashFunction) e liste di collisione.
 *
 * La tabella, poiché implementa l'interfaccia Set<E> non accetta elementi
 * duplicati (individuati tramite il metodo equals() che si assume sia
 * opportunamente ridefinito nella classe E) e non accetta elementi null.
 *
 * La tabella ha una dimensione iniziale di default (16) e un fattore di
 * caricamento di defaut (0.75). Quando il fattore di bilanciamento effettivo
 * eccede quello di default la tabella viene raddoppiata e viene fatto un
 * riposizionamento di tutti gli elementi.
 *
 * @author Template: Luca Tesei, Implementazione: collettiva
 *
 */
public class CollisionListResizableHashTable<E> implements Set<E> {

    /*
     * La capacità iniziale. E' una potenza di due e quindi la capacità sarà
     * sempre una potenza di due, in quanto ogni resize raddoppia la tabella.
     */
    private static final int INITIAL_CAPACITY = 16;

    /*
     * Fattore di bilanciamento di default. Tipico valore.
     */
    private static final double LOAD_FACTOR = 0.75;

    /*
     * Numero di elementi effettivamente presenti nella hash table in questo
     * momento. ATTENZIONE: questo valore è diverso dalla capacity, che è la
     * lunghezza attuale dell'array di Object che rappresenta la tabella.
     */
    private int size;

    /*
     * L'idea è che l'elemento in posizione i della tabella hash è un bucket che
     * contiene null oppure il puntatore al primo nodo di una lista concatenata
     * di elementi. Si può riprendere e adattare il proprio codice della
     * Esercitazione 6 che realizzava una lista concatenata di elementi
     * generici. La classe interna Node<E> è ripresa proprio da lì.
     *
     * ATTENZIONE: la tabella hash vera e propria può essere solo un generico
     * array di Object e non di Node<E> per una impossibilità del compilatore di
     * accettare di creare array a runtime con un tipo generics. Ciò infatti
     * comporterebbe dei problemi nel sistema di check dei tipi Java che, a
     * run-time, potrebbe eseguire degli assegnamenti in violazione del tipo
     * effettivo della variabile. Quindi usiamo un array di Object che
     * riempiremo sempre con null o con puntatori a oggetti di tipo Node<E>.
     *
     * Per inserire un elemento nella tabella possiamo usare il polimorfismo di
     * Object:
     *
     * this.table[i] = new Node<E>(item, next);
     *
     * ma quando dobbiamo prendere un elemento dalla tabella saremo costretti a
     * fare un cast esplicito:
     *
     * Node<E> myNode = (Node<E>) this.table[i];
     *
     * Ci sarà dato un warning di cast non controllato, ma possiamo eliminarlo
     * con un tag @SuppressWarning,
     */
    private Object[] table;

    /*
     * Funzion di hash primaria usata da questa hash table. Va inizializzata nel
     * costruttore all'atto di creazione dell'oggetto.
     */
    private final PrimaryHashFunction phf;

    /*
     * Contatore del numero di modifiche. Serve per rendere l'iterator
     * fail-fast.
     */
    private int modCount;

    // I due metodi seguenti sono di comodo per gestire la capacity e la soglia
    // oltre la quale bisogna fare il resize.

    /* Numero di elementi della tabella corrente */
    private int getCurrentCapacity() {
        return this.table.length;
    }

    /*
     * Valore corrente soglia oltre la quale si deve fare la resize,
     * getCurrentCapacity * LOAD_FACTOR
     */
    private int getCurrentThreshold() {
        return (int) (getCurrentCapacity() * LOAD_FACTOR);
    }

    /**
     * Costruisce una Hash Table con capacità iniziale di default e fattore di
     * caricamento di default.
     */
    public CollisionListResizableHashTable(PrimaryHashFunction phf) {
        this.phf = phf;
        this.table = new Object[INITIAL_CAPACITY];
        this.size = 0;
        this.modCount = 0;
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
        /*
         * ATTENZIONE: usare l'hashCode dell'oggetto e la funzione di hash
         * primaria passata all'atto della creazione: il bucket in cui cercare
         * l'oggetto o è la posizione
         * this.phf.hash(o.hashCode(),this.getCurrentCapacity)
         *
         * In questa posizione, se non vuota, si deve cercare l'elemento o
         * utilizzando il metodo equals() su tutti gli elementi della lista
         * concatenata lì presente
         *
         */

        if (o == null) {
            throw new NullPointerException("Oggetto nullo.");
        }
        int index = this.phf.hash(o.hashCode(), this.getCurrentCapacity());

        Node<E> tmp = (Node<E>) this.table[index];

        if (tmp == null) {
            return false;
        } else {
            for (Node<E> n = tmp; n != null; n = n.next) {
                if (n.item.equals(o)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Operazione non supportata");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Operazione non supportata");
    }

    @Override
    public boolean add(E e) {
        /*
         * ATTENZIONE: usare l'hashCode dell'oggetto e la funzione di hash
         * primaria passata all'atto della creazione: il bucket in cui inserire
         * l'oggetto o è la posizione
         * this.phf.hash(e.hashCode(),this.getCurrentCapacity())
         *
         * In questa posizione, se non vuota, si deve inserire l'elemento o
         * nella lista concatenata lì presente. Se vuota, si crea la lista
         * concatenata e si inserisce l'elemento, che sarà l'unico.
         *
         */
        // ATTENZIONE, si inserisca prima il nuovo elemento e poi si controlli
        // se bisogna fare resize(), cioè se this.size >
        // this.getCurrentThreshold()

        if (e == null) {
            throw new NullPointerException("Elemento nullo.");
        }
        int index = this.phf.hash(e.hashCode(), this.getCurrentCapacity());

        if (table[index] == null) {
            this.table[index] = new Node<E>(e, null);
        } else {
            Node<E> tmp = (Node<E>) this.table[index];

            for (Node<E> n = tmp; n != null; n = n.next) {
                if (n.item.equals(e)) {
                    return false;
                }
                if (n.next == null) {
                    tmp = n;
                }
            }

            tmp.next = new Node<E>(e, null);
        }

        size++;
        modCount++;

        if (this.size() > getCurrentThreshold()) {
            resize();
        }

        return true;
    }

    /*
     * Raddoppia la tabella corrente e riposiziona tutti gli elementi.
     * Da chiamare quando this.size diventa maggiore di getCurrentThreshold()
     */
    private void resize() {
        Object[] tmp = table.clone();

        this.table = new Object[tmp.length * 2];
        this.size = 0;
        this.modCount = 0;

        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] != null) {
                Node<E> tmp2 = (Node<E>) this.table[i];
                for (Node<E> n = tmp2; n != null; n = n.next) {
                    add(n.item);
                }
            }
        }
    }

    @Override
    public boolean remove(Object o) {
        /*
         * ATTENZIONE: usare l'hashCode dell'oggetto e la funzione di hash
         * primaria passata all'atto della creazione: il bucket in cui cercare
         * l'oggetto o è la posizione
         * this.phf.hash(o.hashCode(),this.getCurrentCapacity())
         *
         * In questa posizione, se non vuota, si deve cercare l'elemento o
         * utilizzando il metodo equals() su tutti gli elementi della lista
         * concatenata lì presente. Se presente, l'elemento deve essere
         * eliminato dalla lista concatenata
         *
         */
        // ATTENZIONE: la rimozione, in questa implementazione, **non** comporta
        // mai una resize "al ribasso", cioè un dimezzamento della tabella se si
        // scende sotto il fattore di bilanciamento desiderato.

        if (o == null) {
            throw new NullPointerException("Oggetto nullo.");
        }
        if (!contains(o)) return true;

        int index = this.phf.hash(o.hashCode(), this.getCurrentCapacity());

        Node<E> tmp = (Node<E>) this.table[index];
        //this.table[index] = new Node<E>(e, null);

        if (tmp.equals(o)) {
            if (tmp.next == null) {
                this.table[index] = null;
            } else {
                this.table[index] = tmp.next;
            }
            size--;
            modCount++;
            return true;
        }

        for (Node<E> n = tmp; n != null; n = n.next) {
            if (n.next.equals(o)) {
                n.next = n.next.next;
                n.next.next = null;
                size--;
                modCount++;
                return true;
            }
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // utilizzare un iteratore della collection e chiamare il metodo contains

        boolean findAll = false;

        for (Iterator<?> tmp = c.iterator(); tmp.hasNext(); ) {
            findAll = contains(tmp.next());
        }

        return findAll;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        // utilizzare un iteratore della collection e chiamare il metodo add

        for (Iterator<? extends E> tmp = c.iterator(); tmp.hasNext(); ) {
            E item = (E) tmp.next();
            this.add(item);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Operazione non supportata");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // utilizzare un iteratore della collection e chiamare il metodo remove

        for (Iterator<?> tmp = c.iterator(); tmp.hasNext(); ) {
            remove(tmp.next());
        }
        return true;
    }

    @Override
    public void clear() {
        // Ritorno alla situazione iniziale
        this.table = new Object[INITIAL_CAPACITY];
        this.size = 0;
        this.modCount = 0;
    }

    public void printHash(){
        System.out.println("mod" + modCount);
        for(int index = 0; index < table.length; index++) {
            System.out.println("index " + index + ": ");
            for (Node<E> n = (Node<E>) this.table[index]; n != null; n = n.next) {
                System.out.print(n.item + " ");
            }
            System.out.println(" ");
        }
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
     * Classe che realizza un iteratore per questa hash table. L'ordine in cui
     * vengono restituiti gli oggetti presenti non è rilevante, ma ogni oggetto
     * presente deve essere restituito dall'iteratore una e una sola volta.
     * L'iteratore deve essere fail-fast, cioè deve lanciare una eccezione
     * IllegalStateException se a una chiamata di next() si "accorge" che la
     * tabella è stata cambiata rispetto a quando l'iteratore è stato creato.
     */
    private class Itr implements Iterator<E> {

        private Node<E> lastNode;

        private int lastIndex;

        private int tableSize;

        private int numeroModificheAtteso;

        private Itr() {
            this.lastNode = null;
            this.lastIndex = -1;
            this.tableSize = CollisionListResizableHashTable.this.size();
            this.numeroModificheAtteso = CollisionListResizableHashTable.this.modCount;
        }

        @Override
        public boolean hasNext() {
            if (this.lastNode == null && lastIndex == -1) {
                if (CollisionListResizableHashTable.this.isEmpty()) {
                    //lista vuota
                    return false;
                } else {
                    return true;
                }
            }

            if (this.lastNode.next == null) {
                if (lastIndex < tableSize) {
                    return true;
                }
                return false;
            } else {
                return true;
            }
        }

        @Override
        public E next() {
            if (this.numeroModificheAtteso != CollisionListResizableHashTable.this.modCount) {
                throw new ConcurrentModificationException("Errore, c'è stata una modifica inaspettata.");
            }

            if (!this.hasNext()) {
                throw new NoSuchElementException("Non esiste un altro elemento");
            }

            if (this.lastNode == null) {
                    Node<E> n = (Node<E>) CollisionListResizableHashTable.this.table[lastIndex + 1];
            }

            if (this.lastNode == null && lastIndex == -1) {
                // sono all’inizio e la lista non è vuota
                while((Node<E>) CollisionListResizableHashTable.this.table[lastIndex+1] == null) {
                    lastIndex++;
                }
                this.lastNode = (Node<E>) CollisionListResizableHashTable.this.table[++lastIndex];
            } else {
                // non sono all’inizio, ma c’è ancora qualcuno
                if(lastNode.next == null) {
                    while (lastNode == null || lastNode.next == null) {
                        lastNode = (Node<E>) CollisionListResizableHashTable.this.table[++lastIndex];
                    }
                } else {
                    lastNode = lastNode.next;
                }
            }
            return lastNode.item;
        }
    }
}
