package it.unicam.cs.asdl2021.es12;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Implementazione della classe astratta {@code Graph<L>} che realizza un grafo
 * orientato. Per la rappresentazione viene usata una variante della
 * rappresentazione a liste di adiacenza. A differenza della rappresentazione
 * standard si usano strutture dati più efficienti per quanto riguarda la
 * complessità in tempo della ricerca se un nodo è presente (pseudocostante, con
 * tabella hash) e se un arco è presente (pseudocostante, con tabella hash). Lo
 * spazio occupato per la rappresentazione risultà tuttavia più grande di quello
 * che servirebbe con la rappresentazione standard.
 * <p>
 * Le liste di adiacenza sono rappresentate con una mappa (implementata con
 * tabelle hash) che associa ad ogni nodo del grafo i nodi adiacenti. In questo
 * modo il dominio delle chiavi della mappa è il set dei nodi, su cui è
 * possibile chiamare il metodo contains per testare la presenza o meno di un
 * nodo. Ad ogni chiave della mappa, cioè ad ogni nodo del grafo, non è
 * associata una lista concatenata dei nodi collegati, ma un set di oggetti
 * della classe GraphEdge<L> che rappresentano gli archi uscenti dal nodo: in
 * questo modo la rappresentazione riesce a contenere anche l'eventuale peso
 * dell'arco (memorizzato nell'oggetto della classe GraphEdge<L>). Per
 * controllare se un arco è presenta basta richiamare il metodo contains in
 * questo set. I test di presenza si basano sui metodi equals ridefiniti per
 * nodi e archi nelle classi GraphNode<L> e GraphEdge<L>.
 * <p>
 * Questa classe non supporta le operazioni di rimozione di nodi e archi e le
 * operazioni indicizzate di ricerca di nodi e archi.
 *
 * @param <L> etichette dei nodi del grafo
 * @author Template: Luca Tesei, Implementazione: collettiva
 */
public class MapAdjacentListDirectedGraph<L> extends Graph<L> {

    /*
     * Le liste di adiacenza sono rappresentate con una mappa. Ogni nodo viene
     * associato con l'insieme degli archi uscenti. Nel caso in cui un nodo non
     * abbia archi uscenti è associato con un insieme vuoto.
     */
    private final Map<GraphNode<L>, Set<GraphEdge<L>>> adjacentLists;

    /**
     * Crea un grafo vuoto.
     */
    public MapAdjacentListDirectedGraph() {
        // Inizializza la mappa con la mappa vuota
        this.adjacentLists = new HashMap<GraphNode<L>, Set<GraphEdge<L>>>();
    }

    @Override
    public int nodeCount() {
        return this.adjacentLists.size();
    }

    @Override
    public int edgeCount() {
        int count = 0;
        for (GraphNode<L> tmp : getNodes()) {
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
        if (node == null) throw new NullPointerException("Tentativo di rimuovere un nodo null");
        throw new UnsupportedOperationException("Rimozione dei nodi non supportata");
    }

    @Override
    public boolean containsNode(GraphNode<L> node) {
        // Key nulla
        if (node == null) throw new NullPointerException("Nodo nullo");
        // Test contains
        if (this.adjacentLists.containsKey(node)) return true;
        else return false;
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
    public Set<GraphEdge<L>> getEdgesBetween(int index1, int index2) {
        throw new UnsupportedOperationException("Operazioni con indici non supportate");
    }

    @Override
    public Set<GraphNode<L>> getAdjacentNodesOf(GraphNode<L> node) {
        Set<GraphNode<L>> adjNodes = new HashSet<GraphNode<L>>();

        for (GraphEdge<L> tmp : this.getEdgesOf(node)) adjNodes.add(tmp.getNode2());

        return adjNodes;
    }

    @Override
    public Set<GraphNode<L>> getPredecessorNodesOf(GraphNode<L> node) {
        if(node == null) throw new NullPointerException("Nodo nullo");
        if(!this.containsNode(node)) throw new IllegalArgumentException("Nodo non presente");

        Set<GraphNode<L>> preNodes = new HashSet<>();

        for (GraphEdge<L> tmp: this.getEdges()) {
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
        if (!(this.containsNode(edge.getNode1()) && this.containsNode(edge.getNode2()))) throw new IllegalArgumentException("Nodi non presenti");

        return this.adjacentLists.get(edge.getNode1()).add(edge);
    }

    @Override
    public boolean removeEdge(GraphEdge<L> edge) {
        throw new UnsupportedOperationException("Rimozione degli archi non supportata");
    }

    @Override
    public boolean containsEdge(GraphEdge<L> edge) {
        if (edge == null) throw new NullPointerException("Arco nullo");
        if (!(this.containsNode(edge.getNode1()) && this.containsNode(edge.getNode2()))) throw new IllegalArgumentException("Nodi non presenti");

        return this.adjacentLists.get(edge.getNode1()).contains(edge);
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

    @Override
    public void Printall() {
        for (GraphNode<L> tmp : this.getNodes()) {
            System.out.println("label: " + tmp.getLabel().toString());
            for (GraphEdge<L> tmp2 : this.adjacentLists.get(tmp)) {
                System.out.println("\t     " + tmp2.getNode1().getLabel().toString() + " - " + tmp2.getNode2().getLabel().toString());
            }
        }
    }

}
