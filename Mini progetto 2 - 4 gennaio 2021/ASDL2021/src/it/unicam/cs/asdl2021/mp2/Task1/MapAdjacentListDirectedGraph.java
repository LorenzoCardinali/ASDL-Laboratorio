/**
 *
 */
package it.unicam.cs.asdl2021.mp2.Task1;

import it.unicam.cs.asdl2021.mp2.Graph;
import it.unicam.cs.asdl2021.mp2.GraphEdge;
import it.unicam.cs.asdl2021.mp2.GraphNode;

import java.util.*;

/**
 * Implementazione della classe astratta {@code Graph<L>} che realizza un grafo
 * orientato. Non sono accettate etichette dei nodi null e non sono accettate
 * etichette duplicate nei nodi (che in quel caso sono lo stesso nodo).
 *
 * Per la rappresentazione viene usata una variante della rappresentazione con
 * liste di adiacenza. A differenza della rappresentazione standard si usano
 * strutture dati più efficienti per quanto riguarda la complessità in tempo
 * della ricerca se un nodo è presente (pseudocostante, con tabella hash) e se
 * un arco è presente (pseudocostante, con tabella hash). Lo spazio occupato per
 * la rappresentazione risultà tuttavia più grande di quello che servirebbe con
 * la rappresentazione standard.
 *
 * Le liste di adiacenza sono rappresentate con una mappa (implementata con
 * tabelle hash) che associa ad ogni nodo del grafo i nodi adiacenti. In questo
 * modo il dominio delle chiavi della mappa è l'insieme dei nodi, su cui è
 * possibile chiamare il metodo contains per testare la presenza o meno di un
 * nodo. Ad ogni chiave della mappa, cioè ad ogni nodo del grafo, non è
 * associata una lista concatenata dei nodi collegati, ma un set di oggetti
 * della classe GraphEdge<L> che rappresentano gli archi uscenti dal nodo: in
 * questo modo la rappresentazione riesce a contenere anche l'eventuale peso
 * dell'arco (memorizzato nell'oggetto della classe GraphEdge<L>). Per
 * controllare se un arco è presenta basta richiamare il metodo contains in
 * questo set. I test di presenza si basano sui metodi equals ridefiniti per
 * nodi e archi nelle classi GraphNode<L> e GraphEdge<L>.
 *
 * Questa classe non supporta le operazioni di rimozione di nodi e archi e le
 * operazioni indicizzate di ricerca di nodi e archi.
 *
 * @author Template: Luca Tesei
 * @Implementation: Lorenzo Cardinali - lorenz.cardinali@studenti.unicam.it
 *
 * @param <L> etichette dei nodi del grafo
 */
public class MapAdjacentListDirectedGraph<L> extends Graph<L> {

    /*
     * Le liste di adiacenza sono rappresentate con una mappa. Ogni nodo viene
     * associato con l'insieme degli archi uscenti. Nel caso in cui un nodo non
     * abbia archi uscenti è associato con un insieme vuoto. La variabile
     * istanza è protected solo per scopi di test JUnit.
     */
    protected final Map<GraphNode<L>, Set<GraphEdge<L>>> adjacentLists;

    /*
     * NOTA: per tutti i metodi che ritornano un set utilizzare la classe
     * HashSet<E> per creare l'insieme risultato. Questo garantisce un buon
     * funzionamento dei test JUnit che controllano l'uguaglianza tra insiemi
     */

    /**
     * Crea un grafo vuoto.
     */
    public MapAdjacentListDirectedGraph() {
        // Inizializza la mappa con la mappa vuota
        this.adjacentLists = new HashMap<GraphNode<L>, Set<GraphEdge<L>>>();
    }

    @Override
    public int nodeCount() {
        // Ritorno la grandezza della lista dei nodi
        return this.adjacentLists.size();
    }

    @Override
    public int edgeCount() {
        int count = 0;
        Set<GraphNode<L>> nodes = this.adjacentLists.keySet();

        for (GraphNode<L> tmp : nodes) {
            count += this.adjacentLists.get(tmp).size();
        }

        return count;
    }

    @Override
    public void clear() {
        this.adjacentLists.clear();
    }

    @Override
    public boolean isDirected() {
        // Questa classe implementa grafi orientati
        return true;
    }

    @Override
    public Set<GraphNode<L>> getNodes() {
        return this.adjacentLists.keySet();
    }

    @Override
    public boolean addNode(GraphNode<L> node) {
        // Key nulla
        if (node == null) throw new NullPointerException("Nodo nullo");
        // Key già presente
        if (this.adjacentLists.containsKey(node)) return false;
        // Aggiunta chiave
        this.adjacentLists.put(node, new HashSet<GraphEdge<L>>());
        return true;
    }

    @Override
    public boolean removeNode(GraphNode<L> node) {
        if (node == null)
            throw new NullPointerException("Tentativo di rimuovere un nodo null");
        throw new UnsupportedOperationException("Rimozione dei nodi non supportata");
    }

    @Override
    public boolean containsNode(GraphNode<L> node) {
        // Key nulla
        if (node == null) throw new NullPointerException("Nodo nullo");
        // Test contains
        return this.adjacentLists.containsKey(node);
    }

    @Override
    public GraphNode<L> getNodeOf(L label) {
        if (label == null) throw new NullPointerException("Label nullo");

        Set<GraphNode<L>> nodes = this.adjacentLists.keySet();

        for (GraphNode<L> tmp : nodes) if (tmp.getLabel().equals(label)) return tmp;

        return null;
    }

    @Override
    public int getNodeIndexOf(L label) {
        if (label == null)
            throw new NullPointerException("Tentativo di ricercare un nodo con etichetta null");
        throw new UnsupportedOperationException("Ricerca dei nodi con indice non supportata");
    }

    @Override
    public GraphNode<L> getNodeAtIndex(int i) {
        throw new UnsupportedOperationException("Ricerca dei nodi con indice non supportata");
    }

    @Override
    public Set<GraphNode<L>> getAdjacentNodesOf(GraphNode<L> node) {
        Set<GraphNode<L>> adjNodes = new HashSet<GraphNode<L>>();

        for (GraphEdge<L> tmp : this.getEdgesOf(node)) adjNodes.add(tmp.getNode2());

        return adjNodes;
    }

    @Override
    public Set<GraphNode<L>> getPredecessorNodesOf(GraphNode<L> node) {
        if (node == null) throw new NullPointerException("Nodo nullo");
        if (!this.containsNode(node)) throw new IllegalArgumentException("Nodo non presente");

        Set<GraphNode<L>> preNodes = new HashSet<>();

        for (GraphEdge<L> tmp : this.getEdges()) {
            if (tmp.getNode2().equals(node)) {
                preNodes.add(tmp.getNode1());
            }
        }

        return preNodes;
    }

    @Override
    public Set<GraphEdge<L>> getEdges() {
        Set<GraphEdge<L>> edges = new HashSet<>();

        for (GraphNode<L> tmp : this.getNodes()) edges.addAll(this.adjacentLists.get(tmp));

        return edges;
    }

    @Override
    public boolean addEdge(GraphEdge<L> edge) {
        if (edge == null) throw new NullPointerException("Edge nullo");
        if (edge.isDirected() == false) throw new IllegalArgumentException("Impossibile inserire Arco non orientarto");
        if (!(this.containsNode(edge.getNode1()) && this.containsNode(edge.getNode2())))
            throw new IllegalArgumentException("Nodi non presenti");

        return this.adjacentLists.get(edge.getNode1()).add(edge);
    }

    @Override
    public boolean removeEdge(GraphEdge<L> edge) {
        throw new UnsupportedOperationException("Rimozione degli archi non supportata");
    }

    @Override
    public boolean containsEdge(GraphEdge<L> edge) {
        if (edge == null) throw new NullPointerException("Arco nullo");
        if (!(this.containsNode(edge.getNode1()) && this.containsNode(edge.getNode2())))
            throw new IllegalArgumentException("Nodi non presenti");

        return this.getEdges().contains(edge);
    }

    @Override
    public Set<GraphEdge<L>> getEdgesOf(GraphNode<L> node) {
        if (node == null) throw new NullPointerException("Nodo nullo");
        if (!this.containsNode(node)) throw new IllegalArgumentException("Nodo non presente");

        return this.adjacentLists.get(node);
    }

    @Override
    public Set<GraphEdge<L>> getIngoingEdgesOf(GraphNode<L> node) {
        if (node == null) throw new NullPointerException("Nodo nullo");
        if (!this.containsNode(node)) throw new IllegalArgumentException("Nodo non presente");

        Set<GraphEdge<L>> IngEdges = new HashSet<>();

        for (GraphEdge<L> tmp : this.getEdges()) {
            if (tmp.getNode2().equals(node)) IngEdges.add(tmp);
        }

        return IngEdges;
    }

}
