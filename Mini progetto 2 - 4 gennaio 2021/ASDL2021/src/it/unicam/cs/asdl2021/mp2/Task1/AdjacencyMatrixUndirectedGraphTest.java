package it.unicam.cs.asdl2021.mp2.Task1;

import it.unicam.cs.asdl2021.mp2.Graph;
import it.unicam.cs.asdl2021.mp2.GraphEdge;
import it.unicam.cs.asdl2021.mp2.GraphNode;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AdjacencyMatrixUndirectedGraphTest {

    @Test
    void nodeCount() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertEquals(0, g.nodeCount());
        g.addNode(new GraphNode<String>("s"));
        assertEquals(1, g.nodeCount());
        g.addNode(new GraphNode<String>("u"));
        assertEquals(2, g.nodeCount());
    }

    @Test
    void edgeCount() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertEquals(0, g.edgeCount());
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        assertEquals(0, g.edgeCount());
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        GraphEdge<String> ess = new GraphEdge<String>(ns, ns, false, 1);
        g.addEdge(ess);
        assertEquals(1, g.edgeCount());
        g.addEdge(ess);
        assertEquals(1, g.edgeCount());
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, false, 5.12);
        g.addEdge(esx);
        assertEquals(2, g.edgeCount());
    }

    @Test
    void clear() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertTrue(g.isEmpty());
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        assertFalse(g.isEmpty());
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false, 10.1);
        g.addEdge(esu);
        assertFalse(g.isEmpty());
        g.clear();
        assertTrue(g.isEmpty());
    }

    @Test
    void isDirected() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertFalse(g.isDirected());
    }

    @Test
    void getNodes() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        Set<GraphNode<String>> nodes = g.getNodes();
        assertTrue(nodes.isEmpty());
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        nodes = g.getNodes();
        Set<GraphNode<String>> testNodes = new HashSet<GraphNode<String>>();
        GraphNode<String> nsTest = new GraphNode<String>("s");
        GraphNode<String> nuTest = new GraphNode<String>("u");
        testNodes.add(nuTest);
        testNodes.add(nsTest);
        assertTrue(nodes.equals(testNodes));
        GraphNode<String> nuTestBis = new GraphNode<String>("u");
        g.addNode(nuTestBis);
        nodes = g.getNodes();
        assertTrue(nodes.equals(testNodes));
    }

    @Test
    void addNode() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(NullPointerException.class, () -> g.addNode(null));
        GraphNode<String> ns = new GraphNode<String>("s");
        GraphNode<String> nsTest = new GraphNode<String>("s");
        assertFalse(g.containsNode(ns));
        g.addNode(ns);
        assertTrue(g.containsNode(nsTest));
        GraphNode<String> nu = new GraphNode<String>("u");
        GraphNode<String> nuTest = new GraphNode<String>("u");
        g.addNode(nu);
        assertTrue(g.containsNode(nuTest));
    }

    @Test
    void containsNode() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(NullPointerException.class, () -> g.containsNode(null));
        GraphNode<String> ns = new GraphNode<String>("s");
        GraphNode<String> nsTest = new GraphNode<String>("s");
        assertFalse(g.containsNode(nsTest));
        g.addNode(ns);
        assertTrue(g.containsNode(nsTest));
    }

    @Test
    void getNodeOf() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(NullPointerException.class, () -> g.getNodeOf(null));
        GraphNode<String> ns = new GraphNode<String>("s");
        ns.setColor(1);
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        GraphNode<String> node = g.getNodeOf("s");
        assertEquals("s", node.getLabel());
        assertEquals(1, node.getColor());
        node = g.getNodeOf("u");
        assertEquals("u", node.getLabel());
        assertEquals(0, node.getColor());
    }

    @Test
    void getNodeIndexOf() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        assertThrows(NullPointerException.class, () -> g.getNodeIndexOf(null));
        assertThrows(IllegalArgumentException.class, () -> g.getNodeIndexOf("zzzz"));
        assertTrue(g.getNodeIndexOf(ns.getLabel()) == 0);
        assertTrue(g.getNodeIndexOf(nu.getLabel()) == 1);
    }

    @Test
    void getNodeAtIndex() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(IndexOutOfBoundsException.class, () -> g.getNodeAtIndex(1));
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        assertThrows(IndexOutOfBoundsException.class, () -> g.getNodeAtIndex(1000));
        assertThrows(IndexOutOfBoundsException.class, () -> g.getNodeAtIndex(-1));
        assertTrue(g.getNodeAtIndex(0).equals(ns));
        assertTrue(g.getNodeAtIndex(1).equals(nu));
    }

    @Test
    void getAdjacentNodesOf() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(NullPointerException.class, () -> g.getAdjacentNodesOf(null));
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        Set<GraphNode<String>> adjNodes = new HashSet<GraphNode<String>>();
        assertTrue(g.getAdjacentNodesOf(ns).equals(adjNodes));
        GraphNode<String> nsTest = new GraphNode<String>("s");
        GraphNode<String> nu = new GraphNode<String>("u");
        GraphNode<String> nuTest = new GraphNode<String>("u");
        assertThrows(IllegalArgumentException.class, () -> g.getAdjacentNodesOf(nu));
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false, 10.1);
        g.addEdge(esu);
        GraphNode<String> nx = new GraphNode<String>("x");
        GraphNode<String> nxTest = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, false, 5.12);
        g.addEdge(esx);
        adjNodes.add(nxTest);
        adjNodes.add(nuTest);

        g.printAll();

        assertTrue(g.getAdjacentNodesOf(nsTest).equals(adjNodes));


    }

    @Test
    void getEdges() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        Set<GraphEdge<String>> edgesTest = new HashSet<GraphEdge<String>>();
        assertTrue(g.getEdges().equals(edgesTest));
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false);
        g.addEdge(esu);
        edgesTest.add(esu);
        assertTrue(g.getEdges().equals(edgesTest));
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, false, 5.12);
        g.addEdge(esx);
        GraphEdge<String> eux = new GraphEdge<String>(nu, nx, false, 2.05);
        g.addEdge(eux);
        GraphEdge<String> exu = new GraphEdge<String>(nx, nu, false, 3.04);
        g.addEdge(exu);
        edgesTest.add(eux);
        edgesTest.add(esx);
        edgesTest.add(exu);
        assertTrue(g.getEdges().equals(edgesTest));
        GraphNode<String> ny = new GraphNode<String>("y");
        g.addNode(ny);
        GraphEdge<String> exx = new GraphEdge<String>(nx, nx, false, 2.0);
        g.addEdge(exx);
        GraphEdge<String> eys = new GraphEdge<String>(ny, ns, false, 7.03);
        g.addEdge(eys);
        edgesTest.add(eys);
        edgesTest.add(exx);
        assertTrue(g.getEdges().equals(edgesTest));
        g.clear();
        edgesTest.clear();
        assertTrue(g.getEdges().equals(edgesTest));
    }

    @Test
    void addEdge() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(NullPointerException.class, () -> g.addEdge(null));
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<String>("u");
        assertThrows(IllegalArgumentException.class, () -> g.addEdge(new GraphEdge<String>(ns, nu, false)));
        assertThrows(IllegalArgumentException.class, () -> g.addEdge(new GraphEdge<String>(nu, ns, false)));
        g.addNode(nu);
        assertThrows(IllegalArgumentException.class, () -> g.addEdge(new GraphEdge<String>(ns, nu, true)));
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false);
        GraphEdge<String> eus = new GraphEdge<String>(nu, ns, false);
        assertTrue(g.addEdge(esu));
        assertFalse(g.addEdge(esu));
        assertFalse(g.addEdge(eus));
        assertTrue(g.containsEdge(new GraphEdge<String>(ns, nu, false)));
    }

    @Test
    void containsEdge() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(NullPointerException.class, () -> g.containsEdge(null));
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<String>("u");
        assertThrows(IllegalArgumentException.class, () -> g.containsEdge(new GraphEdge<String>(ns, nu, false)));
        assertThrows(IllegalArgumentException.class, () -> g.containsEdge(new GraphEdge<String>(nu, ns, false)));
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false);
        assertFalse(g.containsEdge(new GraphEdge<String>(ns, nu, false)));
        g.addEdge(esu);
        assertTrue(g.containsEdge(new GraphEdge<String>(ns, nu, false)));
    }

    @Test
    void getEdgesOf() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        Set<GraphEdge<String>> edgesTest = new HashSet<GraphEdge<String>>();
        assertThrows(NullPointerException.class,() -> g.getEdgesOf(null));
        GraphNode<String> nu = new GraphNode<String>("u");
        assertThrows(IllegalArgumentException.class,() -> g.getEdgesOf(nu));
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false);
        g.addEdge(esu);
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, false, 5.12);
        g.addEdge(esx);
        GraphEdge<String> eux = new GraphEdge<String>(nu, nx, false, 2.05);
        g.addEdge(eux);

        //per grafo non orientato, nodo identico ad eux.
        GraphEdge<String> exu = new GraphEdge<String>(nx, nu, false, 3.04);
        //ritornerà falso
        g.addEdge(exu);

        GraphNode<String> ny = new GraphNode<String>("y");
        g.addNode(ny);
        GraphEdge<String> exy = new GraphEdge<String>(nx, ny, false, 2.0);
        g.addEdge(exy);
        GraphEdge<String> eys = new GraphEdge<String>(ny, ns, false, 7.03);
        g.addEdge(eys);
        GraphNode<String> nw = new GraphNode<String>("w");
        g.addNode(nw);
        GraphEdge<String> euw = new GraphEdge<String>(nu, nw, false, 7.07);
        g.addEdge(euw);

        //Controllo gli archi connessi al node[ x ]
        edgesTest.add(esx);
        edgesTest.add(exu);
        edgesTest.add(exy);
        assertEquals(edgesTest,g.getEdgesOf(nx));
        edgesTest.clear();

        //controllo gli archi connessi al node[ s ]
        edgesTest.add(esu);
        edgesTest.add(esx);
        edgesTest.add(eys);
        assertEquals(edgesTest, g.getEdgesOf(ns));
        edgesTest.clear();

        //controllo gli archi connessi al node[ u ]
        edgesTest.add(esu);
        edgesTest.add(euw);
        edgesTest.add(eux);
        assertEquals(edgesTest, g.getEdgesOf(nu));
        edgesTest.clear();
    }
}