/**
 *
 */
package it.unicam.cs.asdl2021.es13;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapAdjacentListDirectedGraph<L> extends Graph<L> {

    /*
     * Le liste di adiacenza sono rappresentate con una mappa. Ogni nodo viene
     * associato con l'insieme degli archi uscenti. Nel caso in cui un nodo non
     * abbia archi uscenti è associato con un insieme vuoto. La variabile
     * istanza è protected solo per scopi di test JUnit.
     */
    protected final Map<GraphNode<L>, Set<GraphEdge<L>>> adjacentLists;

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

        Set<GraphNode<L>> preNodes = new HashSet<GraphNode<L>>();

        for (GraphEdge<L> tmp : this.getEdges()) {
            if (tmp.getNode2().equals(node)) {
                preNodes.add(tmp.getNode1());
            }
        }

        return preNodes;
    }

    @Override
    public Set<GraphEdge<L>> getEdges() {
        Set<GraphEdge<L>> edges = new HashSet<GraphEdge<L>>();

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

        Set<GraphEdge<L>> IngEdges = new HashSet<GraphEdge<L>>();

        for (GraphEdge<L> tmp : this.getEdges()) {
            if (tmp.getNode2().equals(node)) IngEdges.add(tmp);
        }

        return IngEdges;
    }
}
