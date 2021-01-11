/**
 * 
 */
package it.unicam.cs.asdl2021.mp2.Task2;

import it.unicam.cs.asdl2021.mp2.Graph;
import it.unicam.cs.asdl2021.mp2.GraphNode;

import java.util.Set;
import java.util.Stack;

/**
 * Un oggetto di questa classe è un algoritmo che trova le componenti fortemente
 * connesse in un grafo orientato.
 * 
 * @author Luca Tesei
 *
 */
public class StronglyConnectedComponentsFinder2<L> {

    /**
     * Dato un grafo orientato determina l'insieme di tutte le componenti
     * fortemente connesse dello stesso.
     * 
     * @param g
     *              un grafo orientato
     * @return l'insieme di tutte le componenti fortemente connesse di g dove
     *         ogni componente fortemente connessa è rappresentata dall'insieme
     *         dei nodi che la compongono.
     * @throws IllegalArgumentException
     *                                      se il grafo passato non è orientato
     * @throws NullPointerException
     *                                      se il grafo passato è nullo
     */
	
	private Stack<GraphNode<L>> pila;
	private Graph<L> graph;
	
    public Set<Set<GraphNode<L>>> findStronglyConnectedComponents(Graph<L> g) {
    	/*
        
    	if (g == null)
			throw new NullPointerException("Grafo nullo.");

    	// Algoritmo di Kosaraju
    	
    	pila = new Stack<GraphNode<L>>();
    	graph = g;

		// uso un insieme per contenere tutti i sottoinsiemi di grafi connessi (nodi)
		Set<Set<GraphNode<L>>> insiemiConnessi = new HashSet<Set<GraphNode<V>>>();

		for (GraphNode<L> node : g.getNodes()) {
			node.setColor(GraphNode.COLOR_WHITE);
		}
		
		for (GraphNode<L> node : g.getNodes()) {
			if(node.getColor() == GraphNode.COLOR_WHITE) {
				riempiVertici(node);
			}
		}
		
		Graph<L> gr = grafoTrasposto();
		
		for (GraphNode<L> node : gr.getNodes()) {
			node.setColor(GraphNode.COLOR_WHITE);
		}
		
		while(!pila.isEmpty()) {
			GraphNode<L> node = pila.pop();
			
			if(node.getColor() == GraphNode.COLOR_WHITE) {
				Set<GraphNode<L>> insieme = new HashSet<GraphNode<V>>();
				dfs(gr, insieme, gr.getNode(node.getLabel()));
				insiemiConnessi.add(insieme);
			}
		}
		
		return insiemiConnessi;

    	 */
		return null;
    }

    // TODO aggiungere eventuali metodi privati necessari per l'implementazione

	/*

    private Graph<V, E> grafoTrasposto() {

    	Graph<V, E> gr = new AdjacentListDirectedGraph<V, E>();
    	
    	for (GraphNode<V> node : graph.getNodes()) {
			gr.addNode(node);
		}
    	
    	for(GraphEdge<V, E> arco : graph.getEdges()) {
    		gr.addEdge(new DefaultGraphEdge<V,E>(arco.getNode2(), arco.getNode1(), true));
    	}
    	
    	return gr;

    }
    
    private void riempiVertici(GraphNode<V> node) {
    	node.setColor(GraphNode.COLOR_BLACK);
    	
    	for(GraphNode<V> g : graph.getAdjacentNodes(node)) {
			if(g.getColor() == GraphNode.COLOR_WHITE)
				riempiVertici(g);
		}
    	
    	pila.push(node);
    }
    
    private void dfs(Graph<V,E> gr, Set<GraphNode<V>> insieme, GraphNode<V> node) {
    	node.setColor(GraphNode.COLOR_BLACK);
    	insieme.add(node);
    	
    	for(GraphNode<V> g : gr.getAdjacentNodes(node)) {
			if(g.getColor() == GraphNode.COLOR_WHITE)
				dfs(gr, insieme, g);
		}
    }

	 */
}
