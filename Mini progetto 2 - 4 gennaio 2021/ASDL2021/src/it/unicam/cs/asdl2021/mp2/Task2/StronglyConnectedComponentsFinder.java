package it.unicam.cs.asdl2021.mp2.Task2;

import it.unicam.cs.asdl2021.mp2.Graph;
import it.unicam.cs.asdl2021.mp2.GraphNode;
import it.unicam.cs.asdl2021.mp2.GraphEdge;
import it.unicam.cs.asdl2021.mp2.Task1.MapAdjacentListDirectedGraph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Un oggetto di questa classe singoletto è un attore che trova le componenti
 * fortemente connesse in un grafo orientato che viene passato come parametro.
 *
 * @author Template: Luca Tesei
 * @Implementation: Lorenzo Cardinali - lorenz.cardinali@studenti.unicam.it
 *
 */
public class StronglyConnectedComponentsFinder<L> {

    /*
     * NOTA: per tutti i metodi che ritornano un set utilizzare la classe
     * HashSet<E> per creare l'insieme risultato. Questo garantisce un buon
     * funzionamento dei test JUnit che controllano l'uguaglianza tra insiemi
     */

    /**
     * Dato un grafo orientato determina l'insieme di tutte le componenti
     * fortemente connesse dello stesso.
     *
     * @param g un grafo orientato
     *
     * @return l'insieme di tutte le componenti fortemente connesse di g dove
     *         ogni componente fortemente connessa è rappresentata dall'insieme
     *         dei nodi che la compongono.
     *
     * @throws IllegalArgumentException se il grafo passato non è orientato
     * @throws NullPointerException se il grafo passato è nullo
     */

    /********** Algoritmo di Kosaraju **********/

    public Set<Set<GraphNode<L>>> findStronglyConnectedComponents(Graph<L> g) {

        /** Controlli **/

        if (g == null) throw new NullPointerException("Grafo nullo.");
        if (!g.isDirected()) throw new IllegalArgumentException("Grafo non diretto.");

        /** Variabili **/

        // Stack nodi
        Stack<GraphNode<L>> nodeStack = new Stack<GraphNode<L>>();

        // Grafo opposto
        Graph<L> reverseGraph = new MapAdjacentListDirectedGraph<L>();

        // Lista componenti fortemente connessi
        Set<Set<GraphNode<L>>> componentiFC = new HashSet<Set<GraphNode<L>>>();

        /** Fine variabili **/

        // Colorazione nodes (bianco) e caricamento nodi in reverseGraph
        for (GraphNode<L> node : g.getNodes()) {
            node.setColor(GraphNode.COLOR_WHITE);
            reverseGraph.addNode(node);
        }

        /** DFS su g e riempimento stack **/
        for (GraphNode<L> tmp : g.getNodes()) {
            if (tmp.getColor() == GraphNode.COLOR_WHITE) allDFS(g, tmp, nodeStack);
        }

        /** Gira tutti i nodi **/
        // Reverse edges: graph -> reverseGraph
        for (GraphEdge<L> edge : g.getEdges())
            reverseGraph.addEdge(new GraphEdge<L>(edge.getNode2(), edge.getNode1(), true));

        // Colorazione nodes (bianco): reverseGraph
        for (GraphNode<L> node : reverseGraph.getNodes())
            node.setColor(GraphNode.COLOR_WHITE);

        /** Eseguo DFS sul reverseGraph per ogni nodo nella stack **/
        while (!nodeStack.isEmpty()) {
            // Rimuovo nodo dalla stack
            GraphNode<L> node = nodeStack.pop();

            if (node.getColor() == GraphNode.COLOR_WHITE) {
                // Set per componenti fortemente connessi parziali
                Set<GraphNode<L>> tmpHolder = new HashSet<GraphNode<L>>();
                // DFS su reverseGraph
                stackDFS(reverseGraph, reverseGraph.getNodeOf(node.getLabel()), tmpHolder);
                // Aggiungo componenti fortemente connessi parziali nel Set totale
                componentiFC.add(tmpHolder);
            }
        }

        return componentiFC;
    }

    private void allDFS(Graph<L> graph, GraphNode<L> node, Stack<GraphNode<L>> stack) {
        // Segno di aver visitato il nodo
        node.setColor(GraphNode.COLOR_BLACK);

        // Ricorsione per visita dei nodi adiacenti (se presenti)
        for (GraphNode<L> tmp : graph.getAdjacentNodesOf(node))
            if (tmp.getColor() == GraphNode.COLOR_WHITE) allDFS(graph, tmp, stack);

        // Aggiungo nodo in cui mi sono "bloccato"
        // (l'ultimo nodo senza adiacenze che ho visitato)
        stack.push(node);
    }

    private void stackDFS(Graph<L> stack, GraphNode<L> node, Set<GraphNode<L>> holder) {
        // Segno di aver visitato il nodo
        node.setColor(GraphNode.COLOR_BLACK);
        // Aggiungo nodo al Set parziale
        holder.add(node);

        // Ricorsione per visita dei nodi adiacenti (se presenti)
        for (GraphNode<L> tmp : stack.getAdjacentNodesOf(node))
            if (tmp.getColor() == GraphNode.COLOR_WHITE) stackDFS(stack, tmp, holder);
    }
}
