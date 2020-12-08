/**
 * 
 */
package it.unicam.cs.asdl2021.es9sol;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

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
 * @author Luca Tesei
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
    };

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
        if (o == null)
            throw new NullPointerException(
                    "Ricerca di elemento null in un set che non accetta null");
        // Determino la posizione in cui si dovrebbe trovare l'oggetto
        int pos = this.phf.hash(o.hashCode(), this.getCurrentCapacity());
        if (this.table[pos] == null)
            // l'oggetto non è presente poiché la lista di collisioni è null
            return false;
        // Cerco se l'oggetto è presente nella lista di collisioni
        @SuppressWarnings("unchecked")
        Node<E> list = (Node<E>) this.table[pos];
        do {
            // controllo l'elemento corrente attraverso il metodo equals
            // chiamato su o, cfr. API
            if (o.equals(list.item))
                return true;
            // altrimenti vado avanti nella lista di collisioni
            list = list.next;
        } while (list != null);
        // Non ho trovato l'elemento nella lista di collisioni, quindi non è
        // presente
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
         * this.phf.hash(o.hashCode(),this.getCurrentCapacity)
         * 
         * In questa posizione, se non vuota, si deve inserire l'elemento o
         * nella lista concatenata lì presente. Se vuota, si crea la lista
         * concatenata e si inserisce l'elemento, che sarà l'unico.
         * 
         */
        // ATTENZIONE, si inserisca prima il nuovo elemento e poi si controlli
        // se bisogna fare resize(), cioè se this.size >
        // this.getCurrentThreshold()
        if (e == null)
            throw new NullPointerException(
                    "Inserimento di elemento null in un set che non accetta null");
        // vado alla ricerca dell'elemento, nel caso non lo trovi lo inserisco,
        // altrimenti non faccio niente
        // Determino la posizione in cui si dovrebbe trovare l'oggetto
        int pos = this.phf.hash(e.hashCode(), this.getCurrentCapacity());
        // Provo a inserire l'elemento
        boolean inserted = insertElementInTable(this.table, pos, e);
        if (!inserted)
            return false;
        // Aggiorno size e modCount
        this.modCount++;
        this.size++;
        // Controllo resize
        if (this.size > this.getCurrentThreshold())
            resize();
        return true;
    }

    /*
     * Inserisce un elemento nella tabella hash con liste di collisioni nella
     * posizione indicata, se non è già presente
     */
    private boolean insertElementInTable(Object[] table, int pos, E e) {
        if (table[pos] == null) {
            // l'oggetto non è presente, lo inserisco
            table[pos] = new Node<E>(e, null);
            return true;
        }
        // vado alla ricerca dell'oggetto nella lista di collisioni
        @SuppressWarnings("unchecked")
        Node<E> list = (Node<E>) table[pos];
        do {
            // controllo l'elemento corrente attraverso il metodo equals
            // chiamato su e, cfr. API
            if (e.equals(list.item))
                // l'oggetto è già presente, quindi non lo inserisco
                return false;
            // altrimenti vado avanti nella lista di collisioni
            list = list.next;
        } while (list != null);
        // Non ho trovato l'elemento nella lista di collisioni, quindi non è
        // presente e lo inserisco in testa alla lista
        @SuppressWarnings("unchecked")
        Node<E> head = (Node<E>) table[pos];
        table[pos] = new Node<E>(e, head);
        return true;
    }

    /*
     * Raddoppia la tabella corrente e riposiziona tutti gli elementi. Da
     * chiamare quando this.size diventa maggiore di getCurrentThreshold()
     */
    private void resize() {
        // Creo la nuova tabella
        Object[] newTable = new Object[this.getCurrentCapacity() * 2];
        // Scorro tutti gli elementi attualmente presenti e li inserisco nella
        // nuova tabella
        Iterator<E> iter = this.iterator();
        while (iter.hasNext()) {
            E item = iter.next();
            // Determino la posizione in cui si dovrebbe trovare l'oggetto nella
            // nuova tabella
            int pos = this.phf.hash(item.hashCode(), newTable.length);
            // Inserisco l'elemento nella nuova tabella
            insertElementInTable(newTable, pos, item);
        }
        // aggiorno la tabella nell'oggetto
        this.table = newTable;
    }

    @Override
    public boolean remove(Object o) {
        /*
         * ATTENZIONE: usare l'hashCode dell'oggetto e la funzione di hash
         * primaria passata all'atto della creazione: il bucket in cui cercare
         * l'oggetto o è la posizione
         * this.phf.hash(o.hashCode(),this.getCurrentCapacity)
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
        if (o == null)
            throw new NullPointerException(
                    "Cancellazione di elemento null in un set che non accetta null");
        // vado alla ricerca dell'elemento, nel caso lo trovi lo cancello,
        // altrimenti non faccio niente
        // Determino la posizione in cui si dovrebbe trovare l'oggetto
        int pos = this.phf.hash(o.hashCode(), this.getCurrentCapacity());
        if (this.table[pos] == null) {
            // l'oggetto non è presente, ritorno
            return false;
        }
        // vado alla ricerca dell'oggetto nella lista di collisioni
        @SuppressWarnings("unchecked")
        Node<E> previous = null;
        Node<E> list = (Node<E>) this.table[pos];
        do {
            // controllo l'elemento corrente attraverso il metodo equals
            // chiamato su o, cfr. API
            if (o.equals(list.item)) {
                // l'oggetto è presente, quindi lo cancello dalla lista
                if (previous == null) {
                    // l'elemento da togliere è in testa
                    this.table[pos] = list.next;
                } else {
                    // l'elemento non è in testa
                    previous.next = list.next;
                }
                // aggiorno la size e il modCount e ritorno
                this.modCount++;
                this.size--;
                return true;
            }
            // altrimenti vado avanti nella lista di collisioni
            previous = list;
            list = list.next;
        } while (list != null);
        // Non ho trovato l'oggetto, ritorno
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // utilizzare un iteratore della collection e chiamare il metodo
        // contains
        if (c == null)
            throw new NullPointerException("ContainsAll di collection null");
        Iterator<?> iter = c.iterator();
        while (iter.hasNext()) {
            Object item = iter.next();
            if (item == null)
                throw new NullPointerException(
                        "ContainsAll di collection che contiene elementi null");
            if (!this.contains(item))
                return false;
        }
        // Tutti gli elementi sono risultati presenti e non nulli
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        // utilizzare un iteratore della collection e chiamare il metodo add
        if (c == null)
            throw new NullPointerException("AddAll di collection null");
        boolean changed = false;
        Iterator<?> iter = c.iterator();
        while (iter.hasNext()) {
            // utilizzo il polimorfismo da sottoclasse sul tipo E
            @SuppressWarnings("unchecked")
            E item = (E) iter.next();
            if (item == null)
                throw new NullPointerException(
                        "AddAll di collection che contiene elementi null");
            // E' necessario usare l'OR NON PIGRO per assicurarsi che venga
            // chiamato il metodo add
            changed = changed | this.add(item);
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Operazione non supportata");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // utilizzare un iteratore della collection e chiamare il metodo remove
        if (c == null)
            throw new NullPointerException("RemoveAll di collection null");
        boolean changed = false;
        Iterator<?> iter = c.iterator();
        while (iter.hasNext()) {
            // utilizzo il polimorfismo di Object
            Object item = iter.next();
            if (item == null)
                throw new NullPointerException(
                        "RemoveAll di collection che contiene elementi null");
            // E' necessario usare l'OR NON PIGRO per assicurarsi che venga
            // chiamato il metodo remove
            changed = changed | this.remove(item);
        }
        return changed;
    }

    @Override
    public void clear() {
        // Ritorno alla situazione iniziale
        this.table = new Object[INITIAL_CAPACITY];
        this.size = 0;
        this.modCount = 0;
    }

    /*
     * Classe per i nodi della lista concatenata. Lo specificatore è protected
     * solo per permettere i test JUnit.
     */
    protected static class Node<E> {
        protected E item;

        protected Node<E> next;

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
     * ConcurrentModificationException se a una chiamata di next() si "accorge" che la
     * tabella è stata cambiata rispetto a quando l'iteratore è stato creato.
     */
    private class Itr implements Iterator<E> {

        private int currentPos;

        private Node<E> lastNode;

        private final int numeroModificheAtteso;

        private Itr() {
            this.numeroModificheAtteso = modCount;
            this.currentPos = 0;
            this.lastNode = null;
        }

        @Override
        public boolean hasNext() {
            // ricerco la prima posizione esistente non null, se c'è. Potrebbe
            // essere la posizine corrente currentPos
            if (currentPos == table.length)
                return false;
            while (currentPos < table.length && table[currentPos] == null)
                currentPos++;
            if (currentPos == table.length)
                return false;
            // table[currentPos] != null
            if (lastNode == null)
                // non ho ancora fatto il next del primo elemento della lista di
                // collisioni corrente
                return true;
            else if (lastNode.next != null)
                // c'è ancora un elemento di cui fare il next nella lista delle
                // collisioni corrente
                return true;
            else {
                // ho già fatto il next di tutti gli elementi della lista delle
                // collisioni corrente, provo ad andare avanti e mi richiamo
                // ricorsivamente
                currentPos++;
                lastNode = null;
                return hasNext();
            }
        }

        @Override
        public E next() {
            if (modCount != numeroModificheAtteso)
                throw new ConcurrentModificationException("Next in iteratore su tabella modificata");
            if (!hasNext())
                throw new NoSuchElementException(
                        "Richiesta di next con hasNext falso");
            // la chiamata a hasNext() in ogni caso ha aggiornato currentPos e
            // lastNode in modo che, se il risultato è true, indichino il primo
            // elemento disponibile, in particolare si avrà che
            // tab[currentPos]!= null
            E toReturn = null;
            if (lastNode == null) {
                // prendo il primo elemento della lista di collisioni corrente
                @SuppressWarnings("unchecked")
                Node<E> node = (Node<E>) table[currentPos];
                toReturn = node.item;
                lastNode = node;
            } else {
                // sono nel mezzo di una lista di collisioni e lastNode.next non
                // è null (ciò è assicurato dall'hasNext())
                // restituisco il prossimo elemento e mando avanti lastNode
                toReturn = lastNode.next.item;
                lastNode = lastNode.next;
            }
            return toReturn;
        }
    }

    /*
     * Only for JUnit testing purposes.
     */
    protected Object[] getTable() {
        return this.table;
    }

    /*
     * Only for JUnit testing purposes.
     */
    protected PrimaryHashFunction getPhf() {
        return this.phf;
    }

}
