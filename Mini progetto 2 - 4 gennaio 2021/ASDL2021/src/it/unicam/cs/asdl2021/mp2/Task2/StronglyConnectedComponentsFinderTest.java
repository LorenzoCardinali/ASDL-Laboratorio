package it.unicam.cs.asdl2021.mp2.Task2;

import it.unicam.cs.asdl2021.mp2.Graph;
import it.unicam.cs.asdl2021.mp2.GraphEdge;
import it.unicam.cs.asdl2021.mp2.GraphNode;
import it.unicam.cs.asdl2021.mp2.Task1.AdjacencyMatrixUndirectedGraph;
import it.unicam.cs.asdl2021.mp2.Task1.MapAdjacentListDirectedGraph;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StronglyConnectedComponentsFinderTest {

    @Test
    final void testFindStronglyConnectedComponents1() {
        Graph<String> g = new MapAdjacentListDirectedGraph<String>();
        GraphNode<String> n1 = new GraphNode<String>("1");
        g.addNode(n1);
        GraphNode<String> n2 = new GraphNode<String>("2");
        g.addNode(n2);
        GraphNode<String> n3 = new GraphNode<String>("3");
        g.addNode(n3);
        GraphNode<String> n4 = new GraphNode<String>("4");
        g.addNode(n4);
        GraphNode<String> n5 = new GraphNode<String>("5");
        g.addNode(n5);
        GraphNode<String> n6 = new GraphNode<String>("6");
        g.addNode(n6);
        GraphEdge<String> e12 = new GraphEdge<String>(n1, n2, true);
        g.addEdge(e12);
        GraphEdge<String> e22 = new GraphEdge<String>(n2, n2, true);
        g.addEdge(e22);
        GraphEdge<String> e24 = new GraphEdge<String>(n2, n4, true);
        g.addEdge(e24);
        GraphEdge<String> e41 = new GraphEdge<String>(n4, n1, true);
        g.addEdge(e41);
        GraphEdge<String> e25 = new GraphEdge<String>(n2, n5, true);
        g.addEdge(e25);
        GraphEdge<String> e45 = new GraphEdge<String>(n4, n5, true);
        g.addEdge(e45);
        GraphEdge<String> e54 = new GraphEdge<String>(n5, n4, true);
        g.addEdge(e54);
        GraphEdge<String> e63 = new GraphEdge<String>(n6, n3, true);
        g.addEdge(e63);
        Set<GraphNode<String>> component1 = new HashSet<GraphNode<String>>();
        component1.add(n1);
        component1.add(n2);
        component1.add(n4);
        component1.add(n5);
        Set<GraphNode<String>> component2 = new HashSet<GraphNode<String>>();
        component2.add(n3);
        Set<GraphNode<String>> component3 = new HashSet<GraphNode<String>>();
        component3.add(n6);
        Set<Set<GraphNode<String>>> stronglyConnectedComponents = new HashSet<Set<GraphNode<String>>>();
        stronglyConnectedComponents.add(component1);
        stronglyConnectedComponents.add(component2);
        stronglyConnectedComponents.add(component3);
        StronglyConnectedComponentsFinder<String> f = new StronglyConnectedComponentsFinder<String>();

        g.printAll();

        Set<Set<GraphNode<String>>> test = f.findStronglyConnectedComponents(g);

        /*
        for (Set<GraphNode<String>> tmp : test) {
            System.out.print("{");
            for (GraphNode<String> tmp2 : tmp) {
                System.out.print(" " + tmp2 + " ");
            }
            System.out.print("}\n");
        }

         */

        assertTrue(test.equals(stronglyConnectedComponents));
    }


    @Test
    void findStronglyConnectedComponents() {
        assertThrows(NullPointerException.class,
                () -> new StronglyConnectedComponentsFinder().findStronglyConnectedComponents(null));

        assertThrows(IllegalArgumentException.class,
                () -> new StronglyConnectedComponentsFinder().findStronglyConnectedComponents(new AdjacencyMatrixUndirectedGraph<String>()));

        Graph<String> g = new MapAdjacentListDirectedGraph();

        GraphNode<String> nodeA = new GraphNode("A");
        GraphNode<String> nodeB = new GraphNode("B");
        GraphNode<String> nodeC = new GraphNode("C");
        GraphNode<String> nodeD = new GraphNode("D");
        GraphNode<String> nodeE = new GraphNode("E");
        GraphNode<String> nodeF = new GraphNode("F");
        GraphNode<String> nodeG = new GraphNode("G");
        GraphNode<String> nodeH = new GraphNode("H");
        GraphNode<String> nodeI = new GraphNode("I");

        GraphEdge<String> edgeAB = new GraphEdge(nodeA, nodeB, true);
        GraphEdge<String> edgeBC = new GraphEdge(nodeB, nodeC, true);
        GraphEdge<String> edgeCD = new GraphEdge(nodeC, nodeD, true);
        GraphEdge<String> edgeDA = new GraphEdge(nodeD, nodeA, true);
        GraphEdge<String> edgeCE = new GraphEdge(nodeC, nodeE, true);
        GraphEdge<String> edgeEF = new GraphEdge(nodeE, nodeF, true);
        GraphEdge<String> edgeFG = new GraphEdge(nodeF, nodeG, true);
        GraphEdge<String> edgeGE = new GraphEdge(nodeG, nodeE, true);
        GraphEdge<String> edgeHG = new GraphEdge(nodeH, nodeG, true);
        GraphEdge<String> edgeHI = new GraphEdge(nodeH, nodeI, true);

        g.addNode(nodeA);
        g.addNode(nodeB);
        g.addNode(nodeC);
        g.addNode(nodeD);
        g.addNode(nodeE);
        g.addNode(nodeF);
        g.addNode(nodeG);
        g.addNode(nodeH);
        g.addNode(nodeI);

        g.addEdge(edgeAB);
        g.addEdge(edgeBC);
        g.addEdge(edgeCD);
        g.addEdge(edgeDA);
        g.addEdge(edgeCE);
        g.addEdge(edgeEF);
        g.addEdge(edgeFG);
        g.addEdge(edgeGE);
        g.addEdge(edgeHG);
        g.addEdge(edgeHI);

        Set<Set<GraphNode<String>>> resultTest = new HashSet();
        Set<GraphNode<String>> resultTestPart1 = new HashSet();
        Set<GraphNode<String>> resultTestPart2 = new HashSet();
        Set<GraphNode<String>> resultTestPart3 = new HashSet();
        Set<GraphNode<String>> resultTestPart4 = new HashSet();

        resultTestPart1.add(nodeH);
        resultTestPart2.add(nodeI);
        resultTestPart3.add(nodeA);
        resultTestPart3.add(nodeD);
        resultTestPart3.add(nodeC);
        resultTestPart3.add(nodeB);
        resultTestPart4.add(nodeE);
        resultTestPart4.add(nodeG);
        resultTestPart4.add(nodeF);

        resultTest.add(resultTestPart1);
        resultTest.add(resultTestPart2);
        resultTest.add(resultTestPart3);
        resultTest.add(resultTestPart4);

        StronglyConnectedComponentsFinder<String> finder = new StronglyConnectedComponentsFinder();
        assertEquals(resultTest, finder.findStronglyConnectedComponents(g));
    }

    @Test
    final void testFindStronglyConnectedComponents2() {
        Graph<String> g = new MapAdjacentListDirectedGraph<String>();
        GraphNode<String> n1 = new GraphNode<String>("1");
        g.addNode(n1);
        GraphNode<String> n2 = new GraphNode<String>("2");
        g.addNode(n2);
        GraphNode<String> n3 = new GraphNode<String>("3");
        g.addNode(n3);
        GraphNode<String> n4 = new GraphNode<String>("4");
        g.addNode(n4);
        GraphNode<String> n5 = new GraphNode<String>("5");
        g.addNode(n5);
        GraphNode<String> n6 = new GraphNode<String>("6");
        g.addNode(n6);
        GraphEdge<String> e12 = new GraphEdge<String>(n1, n2, true);
        g.addEdge(e12);
        GraphEdge<String> e22 = new GraphEdge<String>(n2, n2, true);
        g.addEdge(e22);
        GraphEdge<String> e24 = new GraphEdge<String>(n2, n4, true);
        g.addEdge(e24);
        GraphEdge<String> e41 = new GraphEdge<String>(n4, n1, true);
        g.addEdge(e41);
        GraphEdge<String> e25 = new GraphEdge<String>(n2, n5, true);
        g.addEdge(e25);
        GraphEdge<String> e45 = new GraphEdge<String>(n4, n5, true);
        g.addEdge(e45);
        GraphEdge<String> e54 = new GraphEdge<String>(n5, n4, true);
        g.addEdge(e54);
        GraphEdge<String> e63 = new GraphEdge<String>(n6, n3, true);
        g.addEdge(e63);
        GraphEdge<String> e26 = new GraphEdge<String>(n2, n6, true);
        g.addEdge(e26);
        Set<GraphNode<String>> component1 = new HashSet<GraphNode<String>>();
        component1.add(n1);
        component1.add(n2);
        component1.add(n4);
        component1.add(n5);
        Set<GraphNode<String>> component2 = new HashSet<GraphNode<String>>();
        component2.add(n3);
        Set<GraphNode<String>> component3 = new HashSet<GraphNode<String>>();
        component3.add(n6);
        Set<Set<GraphNode<String>>> stronglyConnectedComponents = new HashSet<Set<GraphNode<String>>>();
        stronglyConnectedComponents.add(component1);
        stronglyConnectedComponents.add(component2);
        stronglyConnectedComponents.add(component3);
        StronglyConnectedComponentsFinder<String> f = new StronglyConnectedComponentsFinder<String>();
        assertTrue(f.findStronglyConnectedComponents(g).equals(stronglyConnectedComponents));
    }

    @Test
    final void testFindStronglyConnectedComponents3() {
        Graph<String> g = new MapAdjacentListDirectedGraph<String>();
        GraphNode<String> n1 = new GraphNode<String>("1");
        g.addNode(n1);
        GraphNode<String> n2 = new GraphNode<String>("2");
        g.addNode(n2);
        GraphNode<String> n3 = new GraphNode<String>("3");
        g.addNode(n3);
        GraphNode<String> n4 = new GraphNode<String>("4");
        g.addNode(n4);
        GraphNode<String> n5 = new GraphNode<String>("5");
        g.addNode(n5);
        GraphNode<String> n6 = new GraphNode<String>("6");
        g.addNode(n6);
        GraphEdge<String> e12 = new GraphEdge<String>(n1,
                n2, true);
        g.addEdge(e12);
        GraphEdge<String> e22 = new GraphEdge<String>(n2,
                n2, true);
        g.addEdge(e22);
        GraphEdge<String> e24 = new GraphEdge<String>(n2,
                n4, true);
        g.addEdge(e24);
        GraphEdge<String> e41 = new GraphEdge<String>(n4,
                n1, true);
        g.addEdge(e41);
        GraphEdge<String> e25 = new GraphEdge<String>(n2,
                n5, true);
        g.addEdge(e25);
        GraphEdge<String> e45 = new GraphEdge<String>(n4,
                n5, true);
        g.addEdge(e45);
        GraphEdge<String> e54 = new GraphEdge<String>(n5,
                n4, true);
        g.addEdge(e54);
        GraphEdge<String> e63 = new GraphEdge<String>(n6,
                n3, true);
        g.addEdge(e63);
        GraphEdge<String> e36 = new GraphEdge<String>(n3,
                n6, true);
        g.addEdge(e36);
        Set<GraphNode<String>> component1 = new HashSet<GraphNode<String>>();
        component1.add(n1);
        component1.add(n2);
        component1.add(n4);
        component1.add(n5);
        Set<GraphNode<String>> component2 = new HashSet<GraphNode<String>>();
        component2.add(n3);
        component2.add(n6);
        Set<Set<GraphNode<String>>> stronglyConnectedComponents = new HashSet<Set<GraphNode<String>>>();
        stronglyConnectedComponents.add(component1);
        stronglyConnectedComponents.add(component2);
        StronglyConnectedComponentsFinder<String> f = new StronglyConnectedComponentsFinder<String>();
        //System.out.println(f.findStronglyConnectedComponents(g));
        g.printAll();
        assertTrue(f.findStronglyConnectedComponents(g).equals(stronglyConnectedComponents));
    }

    @Test
    final void testFindStronglyConnectedComponents4() {
        Graph<String> g = new MapAdjacentListDirectedGraph<String>();
        GraphNode<String> n1 = new GraphNode<String>("1");
        g.addNode(n1);
        GraphNode<String> n2 = new GraphNode<String>("2");
        g.addNode(n2);
        GraphNode<String> n3 = new GraphNode<String>("3");
        g.addNode(n3);
        GraphNode<String> n4 = new GraphNode<String>("4");
        g.addNode(n4);
        GraphNode<String> n5 = new GraphNode<String>("5");
        g.addNode(n5);
        GraphNode<String> n6 = new GraphNode<String>("6");
        g.addNode(n6);
        GraphEdge<String> e12 = new GraphEdge<String>(n1,
                n2, true);
        g.addEdge(e12);
        GraphEdge<String> e22 = new GraphEdge<String>(n2,
                n2, true);
        g.addEdge(e22);
        GraphEdge<String> e24 = new GraphEdge<String>(n2,
                n4, true);
        g.addEdge(e24);
        GraphEdge<String> e41 = new GraphEdge<String>(n4,
                n1, true);
        g.addEdge(e41);
        GraphEdge<String> e25 = new GraphEdge<String>(n2,
                n5, true);
        g.addEdge(e25);
        GraphEdge<String> e45 = new GraphEdge<String>(n4,
                n5, true);
        g.addEdge(e45);
        GraphEdge<String> e54 = new GraphEdge<String>(n5,
                n4, true);
        g.addEdge(e54);
        GraphEdge<String> e63 = new GraphEdge<String>(n6,
                n3, true);
        g.addEdge(e63);
        GraphEdge<String> e36 = new GraphEdge<String>(n3,
                n6, true);
        g.addEdge(e36);
        GraphEdge<String> e26 = new GraphEdge<String>(n2,
                n6, true);
        g.addEdge(e26);
        Set<GraphNode<String>> component1 = new HashSet<GraphNode<String>>();
        component1.add(n1);
        component1.add(n2);
        component1.add(n4);
        component1.add(n5);
        Set<GraphNode<String>> component2 = new HashSet<GraphNode<String>>();
        component2.add(n3);
        component2.add(n6);
        Set<Set<GraphNode<String>>> stronglyConnectedComponents = new HashSet<Set<GraphNode<String>>>();
        stronglyConnectedComponents.add(component1);
        stronglyConnectedComponents.add(component2);
        StronglyConnectedComponentsFinder<String> f = new StronglyConnectedComponentsFinder<String>();
        //System.out.println(f.findStronglyConnectedComponents(g));
        g.printAll();
        assertTrue(f.findStronglyConnectedComponents(g).equals(stronglyConnectedComponents));
    }

    @Test
    final void testFindStronglyConnectedComponents5() {
        Graph<String> g = new MapAdjacentListDirectedGraph<String>();
        GraphNode<String> na = new GraphNode<String>("a");
        g.addNode(na);
        GraphNode<String> nb = new GraphNode<String>("b");
        g.addNode(nb);
        GraphNode<String> nc = new GraphNode<String>("c");
        g.addNode(nc);
        GraphNode<String> nd = new GraphNode<String>("d");
        g.addNode(nd);
        GraphNode<String> ne = new GraphNode<String>("e");
        g.addNode(ne);
        GraphNode<String> nf = new GraphNode<String>("f");
        g.addNode(nf);
        GraphNode<String> ng = new GraphNode<String>("g");
        g.addNode(ng);
        GraphNode<String> nh = new GraphNode<String>("h");
        g.addNode(nh);

        GraphEdge<String> eab = new GraphEdge<String>(na,
                nb, true);
        g.addEdge(eab);
        GraphEdge<String> ebc = new GraphEdge<String>(nb,
                nc, true);
        g.addEdge(ebc);
        GraphEdge<String> ebe = new GraphEdge<String>(nb,
                ne, true);
        g.addEdge(ebe);
        GraphEdge<String> ebf = new GraphEdge<String>(nb,
                nf, true);
        g.addEdge(ebf);
        GraphEdge<String> ecd = new GraphEdge<String>(nc,
                nd, true);
        g.addEdge(ecd);
        GraphEdge<String> ecg = new GraphEdge<String>(nc,
                ng, true);
        g.addEdge(ecg);
        GraphEdge<String> edc = new GraphEdge<String>(nd,
                nc, true);
        g.addEdge(edc);
        GraphEdge<String> edh = new GraphEdge<String>(nd,
                nh, true);
        g.addEdge(edh);
        GraphEdge<String> eea = new GraphEdge<String>(ne,
                na, true);
        g.addEdge(eea);
        GraphEdge<String> eef = new GraphEdge<String>(ne,
                nf, true);
        g.addEdge(eef);
        GraphEdge<String> efg = new GraphEdge<String>(nf,
                ng, true);
        g.addEdge(efg);
        GraphEdge<String> egf = new GraphEdge<String>(ng,
                nf, true);
        g.addEdge(egf);
        GraphEdge<String> egh = new GraphEdge<String>(ng,
                nh, true);
        g.addEdge(egh);
        GraphEdge<String> ehh = new GraphEdge<String>(nh,
                nh, true);
        g.addEdge(ehh);

        Set<GraphNode<String>> component1 = new HashSet<GraphNode<String>>();
        component1.add(na);
        component1.add(nb);
        component1.add(ne);
        Set<GraphNode<String>> component2 = new HashSet<GraphNode<String>>();
        component2.add(nc);
        component2.add(nd);
        Set<GraphNode<String>> component3 = new HashSet<GraphNode<String>>();
        component3.add(nf);
        component3.add(ng);
        Set<GraphNode<String>> component4 = new HashSet<GraphNode<String>>();
        component4.add(nh);
        Set<Set<GraphNode<String>>> stronglyConnectedComponents = new HashSet<Set<GraphNode<String>>>();
        stronglyConnectedComponents.add(component1);
        stronglyConnectedComponents.add(component2);
        stronglyConnectedComponents.add(component3);
        stronglyConnectedComponents.add(component4);
        StronglyConnectedComponentsFinder<String> f = new StronglyConnectedComponentsFinder<String>();
        Set<Set<GraphNode<String>>> test = f.findStronglyConnectedComponents(g);
        /*
        for (Set<GraphNode<String>> tmp : test) {
            for (GraphNode<String> tmp2 : tmp) {
                System.out.print("\t[" + tmp + ",");
            }
            System.out.print("]\n");
        }

         */
        g.printAll();
        assertTrue(test.equals(stronglyConnectedComponents));
    }

}
