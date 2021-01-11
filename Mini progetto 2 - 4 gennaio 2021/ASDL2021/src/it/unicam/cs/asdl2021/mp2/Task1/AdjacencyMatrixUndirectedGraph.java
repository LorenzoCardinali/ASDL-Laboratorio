package it.unicam.cs.asdl2021.mp2.Task1;

import it.unicam.cs.asdl2021.mp2.Graph;
import it.unicam.cs.asdl2021.mp2.GraphEdge;
import it.unicam.cs.asdl2021.mp2.GraphNode;

import java.util.*;

/**
 * Classe che implementa un grafo non orientato tramite matrice di adiacenza.
 * Non sono accettate etichette dei nodi null e non sono accettate etichette
 * duplicate nei nodi (che in quel caso sono lo stesso nodo).
 * <p>
 * I nodi sono indicizzati da 0 a nodeCount() - 1 seguendo l'ordine del loro
 * inserimento (0 è l'indice del primo nodo inserito, 1 del secondo e così via)
 * e quindi in ogni istante la matrice di adiacenza ha dimensione nodeCount() *
 * nodeCount(). La matrice, sempre quadrata, deve quindi aumentare di dimensione
 * ad ogni inserimento di un nodo. Per questo non è rappresentata tramite array
 * ma tramite ArrayList.
 * <p>
 * Gli oggetti GraphNode<L>, cioè i nodi, sono memorizzati in una mappa che
 * associa ad ogni nodo l'indice assegnato in fase di inserimento. Il dominio
 * della mappa rappresenta quindi l'insieme dei nodi.
 * <p>
 * Gli archi sono memorizzati nella matrice di adiacenza. A differenza della
 * rappresentazione standard con matrice di adiacenza, la posizione i,j della
 * matrice non contiene un flag di presenza, ma è null se i nodi i e j non sono
 * collegati da un arco e contiene un oggetto della classe GraphEdge<L> se lo
 * sono. Tale oggetto rappresenta l'arco. Un oggetto uguale (secondo equals) e
 * con lo stesso peso (se gli archi sono pesati) deve essere presente nella
 * posizione j, i della matrice.
 * <p>
 * Questa classe non supporta i metodi di cancellazione di nodi e archi, ma
 * supporta tutti i metodi che usano indici, utilizzando l'indice assegnato a
 * ogni nodo in fase di inserimento.
 *
 * @author Template: Luca Tesei
 * @Implementation: Lorenzo Cardinali - lorenz.cardinali@studenti.unicam.it
 */
public class AdjacencyMatrixUndirectedGraph<L> extends Graph<L> {
    /*
     * Le seguenti variabili istanza sono protected al solo scopo di agevolare
     * il JUnit testing
     */

    // Insieme dei nodi e associazione di ogni nodo con il proprio indice nella
    // matrice di adiacenza
    protected Map<GraphNode<L>, Integer> nodesIndex;

    // Matrice di adiacenza, gli elementi sono null o oggetti della classe
    // GraphEdge<L>. L'uso di ArrayList permette alla matrice di aumentare di
    // dimensione gradualmente ad ogni inserimento di un nuovo nodo.
    protected ArrayList<ArrayList<GraphEdge<L>>> matrix;

    //contatore nodi
    //private int nodeCount;

    //contatore archi
    private int edgeCount;
    private GraphEdge<L> edge;

    /*
     * NOTA: per tutti i metodi che ritornano un set utilizzare la classe
     * HashSet<E> per creare l'insieme risultato. Questo garantisce un buon
     * funzionamento dei test JUnit che controllano l'uguaglianza tra insiemi
     */

    /**
     * Crea un grafo vuoto.
     */
    public AdjacencyMatrixUndirectedGraph() {
        this.matrix = new ArrayList<ArrayList<GraphEdge<L>>>();
        this.nodesIndex = new HashMap<GraphNode<L>, Integer>();
        this.edgeCount = 0;
    }

    /**
     * Restituisce il numero di nodi in questo grafo.
     *
     * @return il numero di nodi in questo grafo.
     */
    @Override
    public int nodeCount() {
        return nodesIndex.size();
    }

    /**
     * Restituisce il numero di archi in questo grafo.
     *
     * @return il numero di archi in questo grafo.
     */
    @Override
    public int edgeCount() {
        return this.edgeCount;
    }

    /**
     * Cancella tutti i nodi e gli archi di questo grafo portandolo ad essere un grafo vuoto.
     */
    @Override
    public void clear() {
        this.matrix = new ArrayList<ArrayList<GraphEdge<L>>>();
        this.nodesIndex = new HashMap<GraphNode<L>, Integer>();
        this.edgeCount = 0;
    }

    /**
     * Determina se questo grafo è orientato oppure no.
     *
     * @return true se questo grafo è orientato, false altrimenti.
     */
    @Override
    public boolean isDirected() {
        // Questa classe implementa un grafo non orientato
        return false;
    }

    /**
     * Restituisce l'insieme dei nodi di questo grafo.
     *
     * @return l'insieme dei nodi di questo grafo, possibilmente vuoto.
     */
    @Override
    public Set<GraphNode<L>> getNodes() {
        return nodesIndex.keySet();
    }

    /**
     * Aggiunge un nodo a questo grafo.
     *
     * @param node il nuovo nodo da aggiungere
     * @return true se il nodo è stato aggiunto, false altrimenti cioè se il nodo è già presente
     * @throws NullPointerException se il nodo passato è null
     */
    @Override
    public boolean addNode(GraphNode<L> node) {
        // TODO controllare
        if (node == null) throw new NullPointerException("Nodo nullo.");
        if (this.nodesIndex.containsKey(node.getLabel())) return false;

        // Aggiunta nodo nella lista hash
        this.nodesIndex.put(node, this.matrix.size());
        // Aggiunta lista vuota nella matrice
        this.matrix.add(new ArrayList<GraphEdge<L>>());
        // Sistemo matrice
        for (ArrayList<GraphEdge<L>> tmp : this.matrix) {
            for (int i = tmp.size(); i < this.nodeCount(); i++) {
                tmp.add(null);
            }
        }
        return true;
    }

    @Override
    public boolean removeNode(GraphNode<L> node) {
        throw new UnsupportedOperationException("Remove di nodi non supportata");
    }

    /**
     * Determina se c'è un certo nodo in questo grafo.
     *
     * @param node il nodo cercato
     * @return true se il nodo {@code node} esiste in questo grafo
     * @throws NullPointerException se il nodo passato è null
     */
    @Override
    public boolean containsNode(GraphNode<L> node) {
        // Key nulla
        if (node == null) throw new NullPointerException("Nodo nullo.");
        // Test contains
        return this.nodesIndex.containsKey(node);
    }

    /**
     * Restituisce il nodo di questo grafo avente l'etichetta passata.
     *
     * @param label l'etichetta del nodo da restituire
     * @return il nodo di questo grafo che ha l'etichetta uguale a {@code label},
     * null se tale nodo non esiste in questo grafo
     * @throws NullPointerException se l'etichetta è nulla
     */
    @Override
    public GraphNode<L> getNodeOf(L label) {
        if (label == null) throw new NullPointerException("Label nullo.");

        Set<GraphNode<L>> nodes = this.getNodes();

        for (GraphNode<L> tmp : nodes) if (tmp.getLabel().equals(label)) return tmp;

        return null;
    }

    /**
     * Restituisce un indice unico attualmente associato a un certo nodo
     * nell'intervallo <code>[0, this.nodeCount() - 1]</code>. Questa
     * funzionalità è tipicamente disponibile se il grafo è rappresentato con
     * una matrice di adiacenza. Tale indice può anche essere usato per
     * identificare i nodi in strutture dati esterne come array o liste che
     * contengono informazioni aggiuntive calcolate, ad esempio, da un algoritmo sul grafo.
     * <p>
     * Questa operazione è opzionale.
     *
     * @param label l'etichetta del nodo di cui restituire l'indice
     * @return un indice unico nell'intervallo <code>[0, this.nodeCount() - 1]</code>
     * attualmente associato al nodo con etichetta {@code label}.
     * L'indice può cambiare se il grafo viene modificato togliendo dei nodi
     * @throws NullPointerException          se l'etichetta passata è null
     * @throws IllegalArgumentException      se un nodo con l'etichetta {@code label}
     *                                       non esiste in questo grafo
     * @throws UnsupportedOperationException se questa operazione non è supportata
     *                                       dall'implementazione  di questo grafo
     */
    @Override
    public int getNodeIndexOf(L label) {
        if (label == null) throw new NullPointerException("Label nullo.");

        for (GraphNode<L> tmp : this.getNodes()) {
            if (tmp.getLabel().equals(label)) return this.nodesIndex.get(tmp);
        }

        throw new IllegalArgumentException("Label non presente.");
    }

    /**
     * Restituisce il nodo attualmente associato a un certo indice
     * nell'intervallo <code>[0, this.nodeCount() - 1]</code>. Questa
     * funzionalità è tipicamente disponibile se il grafo è rappresentato con
     * una matrice di adiacenza.
     * <p>
     * Questa operazione è opzionale.
     *
     * @param i l'indice del nodo.
     * @return il nodo correntemente associato all'indice i
     * @throws IndexOutOfBoundsException     se l'indice passato non
     *                                       corrisponde a nessun nodo o è
     *                                       fuori dai limiti
     *                                       dell'intervallo
     *                                       <code>[0, this.nodeCount() - 1]</code>
     * @throws UnsupportedOperationException se questa operazione non è
     *                                       supportata dall'implementazione
     *                                       di questo grafo
     */
    @Override
    public GraphNode<L> getNodeAtIndex(int i) {
        if (i >= 0 || i <= this.nodeCount() - 1) {
            for (GraphNode<L> tmp : getNodes()) {
                if (this.nodesIndex.get(tmp) == i) return tmp;
            }
        }
        throw new IndexOutOfBoundsException("Indice non corretto.");
    }

    /**
     * Restituisce l'insieme di tutti i nodi adiacenti a un certo nodo. Se il
     * grafo è orientato, i nodi restituiti sono solo quelli collegati da un un
     * arco uscente dal nodo passato.
     *
     * @param node il nodo di cui cercare i nodi adiacenti
     * @return l'insieme di tutti i nodi adiacenti al nodo specificato
     * @throws IllegalArgumentException se il nodo passato non esiste
     * @throws NullPointerException     se il nodo passato è nullo
     */
    @Override
    public Set<GraphNode<L>> getAdjacentNodesOf(GraphNode<L> node) {
        if (node == null) throw new NullPointerException("Nodo nullo");
        if (!this.containsNode(node)) throw new IllegalArgumentException("Nodo non presente");

        Set<GraphNode<L>> adjNodes = new HashSet<>();

        for (GraphEdge<L> tmp : this.matrix.get(this.getNodeIndexOf(node.getLabel()))) {
            if (tmp != null) adjNodes.add(tmp.getNode2());
        }

        return adjNodes;
    }

    @Override
    public Set<GraphNode<L>> getPredecessorNodesOf(GraphNode<L> node) {
        throw new UnsupportedOperationException("Operazione non supportata in un grafo non orientato");
    }

    /**
     * Restituisce l'insieme di tutti gli archi in questo grafo.
     *
     * @return un insieme, possibilmente vuoto, contenente tutti gli archi di questo grafo
     */
    @Override
    public Set<GraphEdge<L>> getEdges() {
        if (this.edgeCount == 0) return new HashSet<>();

        Set<GraphEdge<L>> edges = new HashSet<>();

        for (int i = 0; i < this.matrix.size(); i++) {
            for (int j = i; j < this.matrix.size(); j++) {
                if(this.matrix.get(i).get(j) != null) edges.add(this.matrix.get(i).get(j));
            }
        }

        return edges;
    }

    /*
    edges.addAll(this.matrix.get(i));

    for(GraphEdge<L> tmp : this.matrix.get(i)) {
        if (tmp != null) edges.add(tmp);
    }
     */

    /**
     * Aggiunge un arco a questo grafo.
     *
     * @param edge l'arco da inserire
     * @return true se l'arco è stato inserito, false se un arco esattamente uguale già esiste
     * @throws NullPointerException     se l'arco passato è nullo
     * @throws IllegalArgumentException se almeno uno dei due nodi specificati nell'arco non esiste
     * @throws IllegalArgumentException se l'arco è orientato e questo grafo non è orientato o viceversa
     */
    @Override
    public boolean addEdge(GraphEdge<L> edge) {
        // TODO controllare
        if (edge == null) throw new NullPointerException("Edge nullo");

        if (!containsNode(edge.getNode1()) || !containsNode(edge.getNode2()))
            throw new IllegalArgumentException("Uno o entrambi i nodi non sono presenti.");

        if (edge.isDirected()) throw new IllegalArgumentException("Edge orientato non accettato.");

        if (this.containsEdge(edge) || this.containsEdge(new GraphEdge(edge.getNode2(), edge.getNode1(), false))) return false;

        int indexLabel1 = this.getNodeIndexOf(edge.getNode1().getLabel());
        int indexLabel2 = this.getNodeIndexOf(edge.getNode2().getLabel());

        this.matrix.get(indexLabel1).set(indexLabel2, edge);
        if (indexLabel1 != indexLabel2) {
            this.matrix.get(indexLabel2).set(indexLabel1, new GraphEdge(edge.getNode2(), edge.getNode1(), false, edge.getWeight()));
        }
        edgeCount++;
        return true;
    }

    @Override
    public boolean removeEdge(GraphEdge<L> edge) {
        throw new UnsupportedOperationException("Operazione di remove non supportata in questa classe");
    }

    /**
     * Cerca se c'è un certo arco in questo grafo.
     *
     * @param edge l'arco da cercare
     * @return true se in questo grafo c'è l'arco specificato, false altrimenti
     * @throws NullPointerException     se l'arco passato è nullo
     * @throws IllegalArgumentException se almeno uno dei due nodi specificati nell'arco non esiste
     */
    @Override
    public boolean containsEdge(GraphEdge<L> edge) {
        if (edge == null) throw new NullPointerException("Edge nullo");

        if (!this.containsNode(edge.getNode1()) || !this.containsNode(edge.getNode2()))
            throw new IllegalArgumentException("Nodi non presenti");

        if (this.edgeCount == 0) return false;

        for (int i = 0; i < this.nodeCount(); i++) {
            for(GraphEdge<L> tmp : this.matrix.get(i)) {
                if(tmp!=null && tmp.equals(edge)) return true;
            }
        }
        return false;
    }

    /**
     * Restituisce l'insieme di tutti gli archi connessi a un certo nodo in un grafo.
     * Nel caso di grafo orientato vengono restituiti solo gli archi uscenti.
     *
     * @param node il nodo di cui sono richiesti gli archi connessi
     * @return un insieme contenente tutti gli archi connessi al nodo specificato
     * in questo grafo (solo gli archi uscenti in caso di grafo orientato)
     * @throws IllegalArgumentException se il nodo passato non esiste
     * @throws NullPointerException     se il nodo passato è nullo
     */
    @Override
    public Set<GraphEdge<L>> getEdgesOf(GraphNode<L> node) {
        if (node == null) throw new NullPointerException("Nodo nullo");
        if (!this.containsNode(node)) throw new IllegalArgumentException("Nodo non presente");

        Set<GraphEdge<L>> edgesOf = new HashSet<>();

        for(GraphEdge<L> tmp : this.matrix.get(this.getNodeIndexOf(node.getLabel()))){
            if (tmp != null) edgesOf.add(tmp);
        }

        return edgesOf;
    }

    @Override
    public Set<GraphEdge<L>> getIngoingEdgesOf(GraphNode<L> node) {
        throw new UnsupportedOperationException("Operazione non supportata in un grafo non orientato");
    }

    @Override
    public void printAll() {
        System.out.println("##################################\n");
        System.out.println("\t" + nodesIndex + "\n");

        for (int i = 0; i < this.nodeCount(); i++) {
            System.out.print(i + " -> ");
            for (Object tmp : this.matrix.get(i)) {
                if (tmp instanceof GraphEdge)
                    System.out.print("\t[" + ((GraphEdge<?>) tmp).getNode1().getLabel() + "-" + ((GraphEdge<?>) tmp).getNode2().getLabel() + " w:"+((GraphEdge<?>) tmp).getWeight() + "] ");
                else
                    System.out.print("\t[" + tmp + "] ");
            }
            System.out.println("");
        }

        System.out.println("\n##################################");
    }
}
