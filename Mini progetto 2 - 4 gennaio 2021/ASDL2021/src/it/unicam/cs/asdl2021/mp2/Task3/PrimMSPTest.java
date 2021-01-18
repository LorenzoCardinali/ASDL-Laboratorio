package it.unicam.cs.asdl2021.mp2.Task3;

import it.unicam.cs.asdl2021.mp2.Graph;
import it.unicam.cs.asdl2021.mp2.GraphEdge;
import it.unicam.cs.asdl2021.mp2.GraphNode;
import it.unicam.cs.asdl2021.mp2.Task1.AdjacencyMatrixUndirectedGraph;
import it.unicam.cs.asdl2021.mp2.Task1.MapAdjacentListDirectedGraph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimMSPTest {

    @Test
    void computeMSP() {
        Graph<String> graph = new AdjacencyMatrixUndirectedGraph<String>();

        GraphNode<String> a = new GraphNode<String>("a");
        GraphNode<String> b = new GraphNode<String>("b");
        GraphNode<String> c = new GraphNode<String>("c");
        GraphNode<String> d = new GraphNode<String>("d");
        GraphNode<String> e = new GraphNode<String>("e");
        GraphNode<String> f = new GraphNode<String>("f");
        GraphNode<String> g = new GraphNode<String>("g");
        GraphNode<String> h = new GraphNode<String>("h");
        GraphNode<String> i = new GraphNode<String>("i");

        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(d);
        graph.addNode(e);
        graph.addNode(f);
        graph.addNode(g);
        graph.addNode(h);
        graph.addNode(i);

        GraphEdge<String> ab = new GraphEdge<String>(a, b, false, 4);
        GraphEdge<String> ah = new GraphEdge<String>(a, h, false, 8.5);
        GraphEdge<String> bc = new GraphEdge<String>(b, c, false, 8);
        GraphEdge<String> bh = new GraphEdge<String>(b, h, false, 11);
        GraphEdge<String> cd = new GraphEdge<String>(c, d, false, 7);
        GraphEdge<String> cf = new GraphEdge<String>(c, f, false, 4);
        GraphEdge<String> ci = new GraphEdge<String>(c, i, false, 2);
        GraphEdge<String> de = new GraphEdge<String>(d, e, false, 9);
        GraphEdge<String> df = new GraphEdge<String>(d, f, false, 14);
        GraphEdge<String> ef = new GraphEdge<String>(e, f, false, 10);
        GraphEdge<String> fg = new GraphEdge<String>(f, g, false, 2);
        GraphEdge<String> gi = new GraphEdge<String>(g, i, false, 6);
        GraphEdge<String> gh = new GraphEdge<String>(g, h, false, 1);
        GraphEdge<String> hi = new GraphEdge<String>(h, i, false, 7);

        graph.addEdge(ab);
        graph.addEdge(ah);
        graph.addEdge(bc);
        graph.addEdge(bh);
        graph.addEdge(cd);
        graph.addEdge(cf);
        graph.addEdge(ci);
        graph.addEdge(de);
        graph.addEdge(df);
        graph.addEdge(ef);
        graph.addEdge(fg);
        graph.addEdge(gi);
        graph.addEdge(gh);
        graph.addEdge(hi);

        graph.printAll();

        PrimMSP<String> test = new PrimMSP<>();

        // porta
        test.computeMSP(graph, a);
        // porta
        test.computeMSP(graph, b);
        // non porta
        test.computeMSP(graph, c);
        // non porta
        test.computeMSP(graph, d);
        // porta
        test.computeMSP(graph, e);
        // porta
        test.computeMSP(graph, f);
        // non porta
        test.computeMSP(graph, g);
        // non porta
        test.computeMSP(graph, h);
        // non porta
        test.computeMSP(graph, i);
    }

    @Test
    void MSPComputerExceptions(){
        PrimMSP MSTcomputer = new PrimMSP();

        Graph<String> g = new AdjacencyMatrixUndirectedGraph();
        GraphNode<String> nodeA = new GraphNode("A");
        GraphNode<String> nodeB = new GraphNode("B");
        GraphNode<String> nodeC = new GraphNode("C");
        GraphEdge<String> edgeAB = new GraphEdge(nodeA, nodeB, false, 8);
        GraphEdge<String> edgeBC = new GraphEdge(nodeB, nodeC, false);

        g.addNode(nodeA); g.addNode(nodeB); g.addEdge(edgeAB);

        assertThrows(NullPointerException.class,()-> MSTcomputer.computeMSP(null, null));
        assertThrows(NullPointerException.class,()-> MSTcomputer.computeMSP(g, null));
        assertThrows(NullPointerException.class,()-> MSTcomputer.computeMSP(null, nodeA));

        assertThrows(IllegalArgumentException.class, ()-> MSTcomputer.computeMSP(new MapAdjacentListDirectedGraph(), nodeA));
        assertThrows(IllegalArgumentException.class, ()-> MSTcomputer.computeMSP(g, nodeC));
        g.addNode(nodeC);
        g.addEdge(edgeBC);
        assertThrows(IllegalArgumentException.class, ()-> MSTcomputer.computeMSP(g, nodeC));
    }

    @Test
    void computeMSP1() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph();
        PrimMSP MSTcomputer = new PrimMSP();

        GraphNode<String> nodeA = new GraphNode("A");
        GraphNode<String> nodeB = new GraphNode("B");
        GraphNode<String> nodeC = new GraphNode("C");
        GraphNode<String> nodeD = new GraphNode("D");
        GraphNode<String> nodeE = new GraphNode("E");
        GraphNode<String> nodeF = new GraphNode("F");
        GraphNode<String> nodeG = new GraphNode("G");
        GraphNode<String> nodeH = new GraphNode("H");

        GraphEdge<String> edgeAB = new GraphEdge(nodeA, nodeB, false, 8);
        GraphEdge<String> edgeAD = new GraphEdge(nodeA, nodeD, false, 9);
        GraphEdge<String> edgeBD = new GraphEdge(nodeB, nodeD, false, 7);
        GraphEdge<String> edgeBF = new GraphEdge(nodeB, nodeF, false, 5);
        GraphEdge<String> edgeCG = new GraphEdge(nodeC, nodeG, false, 3);
        GraphEdge<String> edgeGE = new GraphEdge(nodeG, nodeE, false, 3);
        GraphEdge<String> edgeGH = new GraphEdge(nodeG, nodeH, false, 5);

        g.addNode(nodeA); g.addNode(nodeB); g.addNode(nodeC);
        g.addNode(nodeD); g.addNode(nodeE); g.addNode(nodeF);
        g.addNode(nodeG); g.addNode(nodeH);

        g.addEdge(edgeAB); g.addEdge(edgeAD); g.addEdge(edgeBD);
        g.addEdge(edgeBF); g.addEdge(edgeCG); g.addEdge(edgeGE); g.addEdge(edgeGH);

        //Checking for nodeA as source;
        MSTcomputer.computeMSP(g, nodeA);

        assertNull(nodeA.getPrevious());
        assertEquals(0, nodeA.getFloatingPointDistance());

        assertEquals(nodeA, nodeB.getPrevious());
        assertEquals(edgeAB.getWeight(), nodeB.getFloatingPointDistance());

        assertEquals(nodeB, nodeD.getPrevious());
        assertEquals(edgeBD.getWeight(),nodeD.getFloatingPointDistance());

        assertEquals(nodeB, nodeF.getPrevious());
        assertEquals(edgeBF.getWeight(), nodeF.getFloatingPointDistance());

        assertNull(nodeC.getPrevious());
        assertEquals(Double.MAX_VALUE, nodeC.getFloatingPointDistance());

        assertNull(nodeE.getPrevious());
        assertEquals(Double.MAX_VALUE, nodeE.getFloatingPointDistance());

        assertNull(nodeG.getPrevious());
        assertEquals(Double.MAX_VALUE, nodeG.getFloatingPointDistance());

        assertNull(nodeH.getPrevious());
        assertEquals(Double.MAX_VALUE, nodeH.getFloatingPointDistance());

        //Checking for nodeB as source;
        MSTcomputer.computeMSP(g, nodeB);

        assertNull(nodeB.getPrevious());
        assertEquals(0, nodeB.getFloatingPointDistance());

        assertEquals(nodeB, nodeA.getPrevious());
        assertEquals(edgeAB.getWeight(), nodeA.getFloatingPointDistance());

        assertEquals(nodeB, nodeD.getPrevious());
        assertEquals(edgeBD.getWeight(),nodeD.getFloatingPointDistance());

        assertEquals(nodeB, nodeF.getPrevious());
        assertEquals(edgeBF.getWeight(), nodeF.getFloatingPointDistance());

        assertNull(nodeC.getPrevious());
        assertEquals(Double.MAX_VALUE, nodeC.getFloatingPointDistance());

        assertNull(nodeE.getPrevious());
        assertEquals(Double.MAX_VALUE, nodeE.getFloatingPointDistance());

        assertNull(nodeG.getPrevious());
        assertEquals(Double.MAX_VALUE, nodeG.getFloatingPointDistance());

        assertNull(nodeH.getPrevious());
        assertEquals(Double.MAX_VALUE, nodeH.getFloatingPointDistance());

        //Checking for nodeC as source;
        MSTcomputer.computeMSP(g, nodeC);

        assertNull(nodeC.getPrevious());
        assertEquals(0, nodeC.getFloatingPointDistance());

        assertEquals(nodeC, nodeG.getPrevious());
        assertEquals(edgeCG.getWeight(), nodeG.getFloatingPointDistance());

        assertEquals(nodeG, nodeH.getPrevious());
        assertEquals(edgeGH.getWeight(), nodeH.getFloatingPointDistance());

        assertEquals(nodeG, nodeE.getPrevious());
        assertEquals(edgeGE.getWeight(), nodeE.getFloatingPointDistance());

        assertNull(nodeA.getPrevious());
        assertEquals(Double.MAX_VALUE, nodeA.getFloatingPointDistance());

        assertNull(nodeB.getPrevious());
        assertEquals(Double.MAX_VALUE, nodeB.getFloatingPointDistance());

        assertNull(nodeD.getPrevious());
        assertEquals(Double.MAX_VALUE, nodeD.getFloatingPointDistance());

        assertNull(nodeF.getPrevious());
        assertEquals(Double.MAX_VALUE, nodeF.getFloatingPointDistance());

        //Checking for nodeE as source;
        MSTcomputer.computeMSP(g, nodeE);

        assertNull(nodeE.getPrevious());
        assertEquals(0, nodeE.getFloatingPointDistance());

        assertEquals(nodeE, nodeG.getPrevious());
        assertEquals(edgeGE.getWeight(), nodeG.getFloatingPointDistance());

        assertEquals(nodeG, nodeC.getPrevious());
        assertEquals(edgeCG.getWeight(), nodeC.getFloatingPointDistance());

        assertEquals(nodeG, nodeH.getPrevious());
        assertEquals(edgeGH.getWeight(), nodeH.getFloatingPointDistance());

        assertNull(nodeA.getPrevious());
        assertEquals(Double.MAX_VALUE, nodeA.getFloatingPointDistance());

        assertNull(nodeB.getPrevious());
        assertEquals(Double.MAX_VALUE, nodeB.getFloatingPointDistance());

        assertNull(nodeD.getPrevious());
        assertEquals(Double.MAX_VALUE, nodeD.getFloatingPointDistance());

        assertNull(nodeF.getPrevious());
        assertEquals(Double.MAX_VALUE, nodeF.getFloatingPointDistance());
    }

    @Test
    void computeMSP2(){
        Graph<String> g = new AdjacencyMatrixUndirectedGraph();
        PrimMSP MSTcomputer = new PrimMSP();

        GraphNode<String> nodeA = new GraphNode("A");
        GraphNode<String> nodeB = new GraphNode("B");
        GraphNode<String> nodeC = new GraphNode("C");
        GraphNode<String> nodeD = new GraphNode("D");
        GraphNode<String> nodeE = new GraphNode("E");
        GraphNode<String> nodeF = new GraphNode("F");
        GraphNode<String> nodeG = new GraphNode("G");
        GraphNode<String> nodeH = new GraphNode("H");

        GraphEdge<String> edgeAB = new GraphEdge(nodeA, nodeB, false, 8);
        GraphEdge<String> edgeAC = new GraphEdge(nodeA, nodeC, false, 7);
        GraphEdge<String> edgeBC = new GraphEdge(nodeB, nodeC, false, 6);
        GraphEdge<String> edgeBG = new GraphEdge(nodeB, nodeG, false, 2);
        GraphEdge<String> edgeCG = new GraphEdge(nodeC, nodeG, false, 1);
        GraphEdge<String> edgeBF = new GraphEdge(nodeB, nodeF, false, 9);
        GraphEdge<String> edgeCF = new GraphEdge(nodeC, nodeF, false, 5);
        GraphEdge<String> edgeFD = new GraphEdge(nodeF, nodeD, false, 6);
        GraphEdge<String> edgeAE = new GraphEdge(nodeA, nodeE, false, 5);
        GraphEdge<String> edgeEH = new GraphEdge(nodeE, nodeH, false, 4);

        g.addNode(nodeA); g.addNode(nodeB); g.addNode(nodeC);
        g.addNode(nodeD); g.addNode(nodeE); g.addNode(nodeF);
        g.addNode(nodeG); g.addNode(nodeH);

        g.addEdge(edgeAB); g.addEdge(edgeAC); g.addEdge(edgeBC); g.addEdge(edgeBG);
        g.addEdge(edgeCG); g.addEdge(edgeBF); g.addEdge(edgeCF); g.addEdge(edgeFD);
        g.addEdge(edgeAE); g.addEdge(edgeEH);

        //Checking for nodeA as source;
        MSTcomputer.computeMSP(g, nodeA);

        assertNull(nodeA.getPrevious());
        assertEquals(0, nodeA.getFloatingPointDistance());

        assertEquals(nodeA, nodeC.getPrevious());
        assertEquals(edgeAC.getWeight(), nodeC.getFloatingPointDistance());

        assertEquals(nodeA, nodeE.getPrevious());
        assertEquals(edgeAE.getWeight(), nodeE.getFloatingPointDistance());

        assertEquals(nodeC, nodeG.getPrevious());
        assertEquals(edgeCG.getWeight(), nodeG.getFloatingPointDistance());

        assertEquals(nodeC, nodeF.getPrevious());
        assertEquals(edgeCF.getWeight(), nodeF.getFloatingPointDistance());

        assertEquals(nodeE, nodeH.getPrevious());
        assertEquals(edgeEH.getWeight(), nodeH.getFloatingPointDistance());

        assertEquals(nodeG, nodeB.getPrevious());
        assertEquals(edgeBG.getWeight(), nodeB.getFloatingPointDistance());

        assertEquals(nodeF, nodeD.getPrevious());
        assertEquals(edgeFD.getWeight(), nodeD.getFloatingPointDistance());

        //Checking for nodeG as source;
        MSTcomputer.computeMSP(g, nodeG);

        assertNull(nodeG.getPrevious());
        assertEquals(0, nodeG.getFloatingPointDistance());

        assertEquals(nodeG, nodeC.getPrevious());
        assertEquals(edgeCG.getWeight(), nodeC.getFloatingPointDistance());

        assertEquals(nodeG, nodeB.getPrevious());
        assertEquals(edgeBG.getWeight(), nodeB.getFloatingPointDistance());

        assertEquals(nodeC, nodeF.getPrevious());
        assertEquals(edgeCF.getWeight(), nodeF.getFloatingPointDistance());

        assertEquals(nodeC, nodeA.getPrevious());
        assertEquals(edgeAC.getWeight(), nodeA.getFloatingPointDistance());

        assertEquals(nodeF, nodeD.getPrevious());
        assertEquals(edgeFD.getWeight(), nodeD.getFloatingPointDistance());

        assertEquals(nodeA, nodeE.getPrevious());
        assertEquals(edgeAE.getWeight(), nodeE.getFloatingPointDistance());

        assertEquals(nodeE, nodeH.getPrevious());
        assertEquals(edgeEH.getWeight(), nodeH.getFloatingPointDistance());

        //Checking for nodeH as source;
        MSTcomputer.computeMSP(g, nodeH);

        assertNull(nodeH.getPrevious());
        assertEquals(0, nodeH.getFloatingPointDistance());

        assertEquals(nodeH, nodeE.getPrevious());
        assertEquals(edgeEH.getWeight(), nodeE.getFloatingPointDistance());

        assertEquals(nodeH, nodeE.getPrevious());
        assertEquals(edgeEH.getWeight(), nodeE.getFloatingPointDistance());

        assertEquals(nodeE, nodeA.getPrevious());
        assertEquals(edgeAE.getWeight(), nodeA.getFloatingPointDistance());

        assertEquals(nodeA, nodeC.getPrevious());
        assertEquals(edgeAC.getWeight(), nodeC.getFloatingPointDistance());

        assertEquals(nodeG, nodeB.getPrevious());
        assertEquals(edgeBG.getWeight(), nodeB.getFloatingPointDistance());

        assertEquals(nodeC, nodeF.getPrevious());
        assertEquals(edgeCF.getWeight(), nodeF.getFloatingPointDistance());

        assertEquals(nodeC, nodeG.getPrevious());
        assertEquals(edgeCG.getWeight(), nodeG.getFloatingPointDistance());

        assertEquals(nodeF, nodeD.getPrevious());
        assertEquals(edgeFD.getWeight(), nodeD.getFloatingPointDistance());
    }
}