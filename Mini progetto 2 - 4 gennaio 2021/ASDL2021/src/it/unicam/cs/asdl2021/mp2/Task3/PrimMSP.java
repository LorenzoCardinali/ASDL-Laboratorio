package it.unicam.cs.asdl2021.mp2.Task3;

import it.unicam.cs.asdl2021.mp2.Graph;
import it.unicam.cs.asdl2021.mp2.GraphEdge;
import it.unicam.cs.asdl2021.mp2.GraphNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe singoletto che implementa l'algoritmo di Prim per trovare un Minimum
 * Spanning Tree di un grafo non orientato, pesato e con pesi non negativi.
 * <p>
 * L'algoritmo usa una coda di min priorità tra i nodi implementata dalla classe
 * TernaryHeapMinPriorityQueue. I nodi vengono visti come PriorityQueueElement
 * poiché la classe GraphNode<L> implementa questa interfaccia. Si noti che
 * nell'esecuzione dell'algoritmo è necessario utilizzare l'operazione di
 * decreasePriority.
 *
 * @param <L> etichette dei nodi del grafo
 * @author Template: Luca Tesei
 * @Implementation: Lorenzo Cardinali - lorenz.cardinali@studenti.unicam.it
 */
public class PrimMSP<L> {

    /*
     * Coda di priorità che va usata dall'algoritmo. La variabile istanza è
     * protected solo per scopi di testing JUnit.
     */
    protected TernaryHeapMinPriorityQueue queue;

    /**
     * Crea un nuovo algoritmo e inizializza la coda di priorità con una coda
     * vuota.
     */
    public PrimMSP() {
        this.queue = new TernaryHeapMinPriorityQueue();
    }

    /**
     * Utilizza l'algoritmo goloso di Prim per trovare un albero di copertura
     * minimo in un grafo non orientato e pesato, con pesi degli archi non negativi.
     * Dopo l'esecuzione del metodo nei nodi del grafo il campo previous deve
     * contenere un puntatore a un nodo in accordo all'albero di copertura
     * minimo calcolato, la cui radice è il nodo sorgente passato.
     *
     * @param g un grafo non orientato, pesato, con pesi non negativi
     * @param s l nodo del grafo g sorgente, cioè da cui parte il calcolo dell'albero
     *          di copertura minimo. Tale nodo sarà la radice dell'albero di copertura trovato
     * @throw NullPointerException se il grafo g o il nodo sorgente s sono nulli
     * @throw IllegalArgumentException se il nodo sorgente s non esiste in g
     * @throw IllegalArgumentException se il grafo g è orientato, non pesato o con pesi negativi
     */
    public void computeMSP(Graph<L> g, GraphNode<L> s) {
        /** Eccezioni **/

        if (g == null || s == null) throw new NullPointerException("Grafo o/e nodo sorgente nullo.");
        if (!g.containsNode(s)) throw new IllegalArgumentException("Nodo sorgente non esiste nel grafo.");
        if (g.isDirected()) throw new IllegalArgumentException("Grafo orientati non ammessi.");
        for (GraphEdge<L> tmp : g.getEdges())
            if (!tmp.hasWeight() || tmp.getWeight() <= 0)
                throw new IllegalArgumentException("Prensente un edge non pesato o con pesi negativi");

        /** Variabili **/

        // Albero MSP
        List<PriorityQueueElement> mspTree = new ArrayList<PriorityQueueElement>();

        // Costo albero
        double cost = 0;

        // Infinito
        Double inf = Double.MAX_VALUE;


        /** Codice **/

        // for each u ∈ G.V
        for (GraphNode<L> node : g.getNodes()) {
            // u.key = ∞
            node.setPriority(inf);
            // u.π = NIL
            node.setPrevious(null);
            // u non visitato
            node.setColor(GraphNode.COLOR_WHITE);
        }

        // r.key = 0
        g.getNodeOf(s.getLabel()).setPriority(0);

        // Q = G.V
        for (GraphNode<L> node : g.getNodes()) {
            this.queue.insert(node);
        }

        // while Q ≠ ∅
        while (this.queue.size() > 0) {

            // u = EXTRACT-MIN(Q)
            GraphNode<L> minNode = (GraphNode<L>) this.queue.extractMinimum();

            // u visitato
            minNode.setColor(GraphNode.COLOR_BLACK);

            // se la priorità è ∞ allora il nodo è isolato
            if(Double.compare(minNode.getPriority(), inf) == 0) continue;

            // for each v ∈ G.Adj[u]
            for (GraphNode<L> compareNode : g.getAdjacentNodesOf(minNode)) {

                // edge (u,v) ∈ v
                GraphEdge<L> nodesEdge = findEdge(g, minNode, compareNode);

                // if v ∈ Q and w(u,v) < v.key
                if (compareNode.getColor() == GraphNode.COLOR_WHITE && Double.compare(nodesEdge.getWeight(), compareNode.getPriority() ) < 0) {
                    // v.π = u
                    compareNode.setPrevious(minNode);
                    // v.key = w(u,v)
                    this.queue.decreasePriority(compareNode, nodesEdge.getWeight());
                }
            }
            // mspTree <- u
            //mspTree.add(minNode);

            // Cost += u.key
            //cost += minNode.getPriority();
        }

        /*
        for (PriorityQueueElement node : mspTree) {
            System.out.println("[" + ((GraphNode<L>) node).getPrevious() + " - " + node + "]");
        }
        System.out.println("");

         */

    }

    private GraphEdge<L> findEdge(Graph<L> g, GraphNode<L> u, GraphNode<L> v) {
        // Cerco tra i nodi di u il grafo v
        for (GraphEdge<L> tmp : g.getEdgesOf(u)) {
            if (u.equals(tmp.getNode1()) && v.equals(tmp.getNode2()) || u.equals(tmp.getNode2()) && v.equals(tmp.getNode1()) ) {
                return tmp;
            }
        }
        return null;
    }
}