package it.unicam.cs.asdl2021.mp1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class TernaryHeapMinPriorityQueueTest {

    @Test
    final void testSize() {
        TernaryHeapMinPriorityQueue h = new TernaryHeapMinPriorityQueue();
        assertTrue(h.size() == 0);
        Job j1 = new Job("Job1", 30);
        h.insert(j1);
        assertTrue(h.size() == 1);
        assertTrue(j1.getHandle() == 0);
        Job j2 = new Job("Job2", 25);
        h.insert(j2);
        assertTrue(h.size() == 2);
        assertTrue(j1.getHandle() == 1);
        assertTrue(j2.getHandle() == 0);
        Job ex1 = (Job) h.extractMinimum();
        assertTrue(ex1 == j2);
        assertTrue(h.size() == 1);
        assertTrue(j1.getHandle() == 0);
        Job ex2 = (Job) h.extractMinimum();
        assertTrue(ex2 == j1);
        assertTrue(h.size() == 0);
    }

    @Test
    final void testInsert() {
        TernaryHeapMinPriorityQueue h = new TernaryHeapMinPriorityQueue();
        assertThrows(NullPointerException.class, () -> h.insert(null));
        Job j30 = new Job("Job30", 30);
        h.insert(j30);
        Job j25 = new Job("Job25", 25);
        h.insert(j25);
        Job j41 = new Job("Job41", 41);
        h.insert(j41);
        ArrayList<PriorityQueueElement> a = h.getTernaryHeap();
        assertTrue(a.get(0) == j25);
        assertTrue(a.get(1) == j30);
        assertTrue(a.get(2) == j41);
        controlloHandles(a,a.size());
        Job j17 = new Job("Job17", 17);
        h.insert(j17);
        assertTrue(a.get(0) == j17);
        assertTrue(a.get(1) == j30);
        assertTrue(a.get(2) == j41);
        assertTrue(a.get(3) == j25);
        controlloHandles(a,a.size());
        Job j28 = new Job("Job28", 28);
        h.insert(j28);
        assertTrue(a.get(0) == j17);
        assertTrue(a.get(1) == j28);
        assertTrue(a.get(2) == j41);
        assertTrue(a.get(3) == j25);
        assertTrue(a.get(4) == j30);
        controlloHandles(a,a.size());
        Job j15 = new Job("Job15", 15);
        h.insert(j15);
        assertTrue(a.get(0) == j15);
        assertTrue(a.get(1) == j17);
        assertTrue(a.get(2) == j41);
        assertTrue(a.get(3) == j25);
        assertTrue(a.get(4) == j30);
        assertTrue(a.get(5) == j28);
        controlloHandles(a,a.size());
        Job j16 = new Job("Job16", 16);
        h.insert(j16);
        assertTrue(a.get(0) == j15);
        assertTrue(a.get(1) == j16);
        assertTrue(a.get(2) == j41);
        assertTrue(a.get(3) == j25);
        assertTrue(a.get(4) == j30);
        assertTrue(a.get(5) == j28);
        assertTrue(a.get(6) == j17);
        controlloHandles(a,a.size());
        Job j42 = new Job("Job42", 42);
        h.insert(j42);
        Job j43 = new Job("Job43", 43);
        h.insert(j43);
        Job j18 = new Job("Job18", 18);
        h.insert(j18);
        assertTrue(a.get(0) == j15);
        assertTrue(a.get(1) == j16);
        assertTrue(a.get(2) == j18);
        assertTrue(a.get(3) == j25);
        assertTrue(a.get(4) == j30);
        assertTrue(a.get(5) == j28);
        assertTrue(a.get(6) == j17);
        assertTrue(a.get(7) == j42);
        assertTrue(a.get(8) == j43);
        assertTrue(a.get(9) == j41);
        controlloHandles(a,a.size());
        Job j26 = new Job("Job26", 26);
        h.insert(j26);
        Job j27 = new Job("Job27", 27);
        h.insert(j27);
        Job j10 = new Job("Job10", 10);
        h.insert(j10);
        assertTrue(a.get(0) == j10);
        assertTrue(a.get(1) == j16);
        assertTrue(a.get(2) == j18);
        assertTrue(a.get(3) == j15);
        assertTrue(a.get(4) == j30);
        assertTrue(a.get(5) == j28);
        assertTrue(a.get(6) == j17);
        assertTrue(a.get(7) == j42);
        assertTrue(a.get(8) == j43);
        assertTrue(a.get(9) == j41);
        assertTrue(a.get(10) == j26);
        assertTrue(a.get(11) == j27);
        assertTrue(a.get(12) == j25);
        controlloHandles(a,a.size());
        Job j20 = new Job("Job20", 20);
        h.insert(j20);
        Job j21 = new Job("Job21", 21);
        h.insert(j21);
        assertTrue(a.get(0) == j10);
        assertTrue(a.get(1) == j16);
        assertTrue(a.get(2) == j18);
        assertTrue(a.get(3) == j15);
        assertTrue(a.get(4) == j20);
        assertTrue(a.get(5) == j28);
        assertTrue(a.get(6) == j17);
        assertTrue(a.get(7) == j42);
        assertTrue(a.get(8) == j43);
        assertTrue(a.get(9) == j41);
        assertTrue(a.get(10) == j26);
        assertTrue(a.get(11) == j27);
        assertTrue(a.get(12) == j25);
        assertTrue(a.get(13) == j30);
        assertTrue(a.get(14) == j21);
        // controllo handles
        controlloHandles(a,a.size());
        

    }

    @Test
    final void testMinimum() {
        TernaryHeapMinPriorityQueue h = new TernaryHeapMinPriorityQueue();
        assertThrows(NoSuchElementException.class, () -> h.minimum());
        Job j30 = new Job("Job30", 30);
        h.insert(j30);
        Job j25 = new Job("Job25", 25);
        h.insert(j25);
        Job j41 = new Job("Job41", 41);
        h.insert(j41);
        PriorityQueueElement min = h.minimum();
        assertTrue(min.getPriority() == 25);
        assertTrue(min == j25);
        assertTrue(min.getHandle() == 0);
    }

    @Test
    final void testExtractMinimum() {
        TernaryHeapMinPriorityQueue h = new TernaryHeapMinPriorityQueue();
        assertThrows(NoSuchElementException.class, () -> h.extractMinimum());
        Job j30 = new Job("Job30", 30);
        h.insert(j30);
        Job j25 = new Job("Job25", 25);
        h.insert(j25);
        Job j41 = new Job("Job41", 41);
        h.insert(j41);
        Job j17 = new Job("Job17", 17);
        h.insert(j17);
        Job j28 = new Job("Job28", 28);
        h.insert(j28);
        Job j15 = new Job("Job15", 15);
        h.insert(j15);
        Job j16 = new Job("Job16", 16);
        h.insert(j16);
        Job j42 = new Job("Job42", 42);
        h.insert(j42);
        Job j43 = new Job("Job43", 43);
        h.insert(j43);
        Job j18 = new Job("Job18", 18);
        h.insert(j18);
        Job j26 = new Job("Job26", 26);
        h.insert(j26);
        Job j27 = new Job("Job27", 27);
        h.insert(j27);
        Job j10 = new Job("Job10", 10);
        h.insert(j10);
        Job j20 = new Job("Job20", 20);
        h.insert(j20);
        Job j21 = new Job("Job21", 21);
        h.insert(j21);
        PriorityQueueElement min = h.extractMinimum();
        assertTrue(min == j10);
        ArrayList<PriorityQueueElement> a = h.getTernaryHeap();
        assertTrue(a.get(0) == j15);
        assertTrue(a.get(1) == j16);
        assertTrue(a.get(2) == j18);
        assertTrue(a.get(3) == j21);
        assertTrue(a.get(4) == j20);
        assertTrue(a.get(5) == j28);
        assertTrue(a.get(6) == j17);
        assertTrue(a.get(7) == j42);
        assertTrue(a.get(8) == j43);
        assertTrue(a.get(9) == j41);
        assertTrue(a.get(10) == j26);
        assertTrue(a.get(11) == j27);
        assertTrue(a.get(12) == j25);
        assertTrue(a.get(13) == j30);
        controlloHandles(a,h.size());
        min = h.extractMinimum();
        assertTrue(min == j15);
        assertTrue(a.get(0) == j16);
        assertTrue(a.get(1) == j17);
        assertTrue(a.get(2) == j18);
        assertTrue(a.get(3) == j21);
        assertTrue(a.get(4) == j20);
        assertTrue(a.get(5) == j28);
        assertTrue(a.get(6) == j30);
        assertTrue(a.get(7) == j42);
        assertTrue(a.get(8) == j43);
        assertTrue(a.get(9) == j41);
        assertTrue(a.get(10) == j26);
        assertTrue(a.get(11) == j27);
        assertTrue(a.get(12) == j25);
        controlloHandles(a,h.size());
        min = h.extractMinimum();
        assertTrue(min == j16);
        assertTrue(a.get(0) == j17);
        assertTrue(a.get(1) == j20);
        assertTrue(a.get(2) == j18);
        assertTrue(a.get(3) == j21);
        assertTrue(a.get(4) == j25);
        assertTrue(a.get(5) == j28);
        assertTrue(a.get(6) == j30);
        assertTrue(a.get(7) == j42);
        assertTrue(a.get(8) == j43);
        assertTrue(a.get(9) == j41);
        assertTrue(a.get(10) == j26);
        assertTrue(a.get(11) == j27);
        controlloHandles(a,h.size());
        min = h.extractMinimum();
        assertTrue(min == j17);
        assertTrue(a.get(0) == j18);
        assertTrue(a.get(1) == j20);
        assertTrue(a.get(2) == j27);
        assertTrue(a.get(3) == j21);
        assertTrue(a.get(4) == j25);
        assertTrue(a.get(5) == j28);
        assertTrue(a.get(6) == j30);
        assertTrue(a.get(7) == j42);
        assertTrue(a.get(8) == j43);
        assertTrue(a.get(9) == j41);
        assertTrue(a.get(10) == j26);
        controlloHandles(a,h.size());
        min = h.extractMinimum();
        assertTrue(min == j18);
        assertTrue(a.get(0) == j20);
        assertTrue(a.get(1) == j25);
        assertTrue(a.get(2) == j27);
        assertTrue(a.get(3) == j21);
        assertTrue(a.get(4) == j26);
        assertTrue(a.get(5) == j28);
        assertTrue(a.get(6) == j30);
        assertTrue(a.get(7) == j42);
        assertTrue(a.get(8) == j43);
        assertTrue(a.get(9) == j41);
        controlloHandles(a,h.size());
        min = h.extractMinimum();
        assertTrue(min == j20);
        assertTrue(a.get(0) == j21);
        assertTrue(a.get(1) == j25);
        assertTrue(a.get(2) == j27);
        assertTrue(a.get(3) == j41);
        assertTrue(a.get(4) == j26);
        assertTrue(a.get(5) == j28);
        assertTrue(a.get(6) == j30);
        assertTrue(a.get(7) == j42);
        assertTrue(a.get(8) == j43);
        controlloHandles(a,h.size());
        min = h.extractMinimum();
        assertTrue(min == j21);
        assertTrue(a.get(0) == j25);
        assertTrue(a.get(1) == j26);
        assertTrue(a.get(2) == j27);
        assertTrue(a.get(3) == j41);
        assertTrue(a.get(4) == j43);
        assertTrue(a.get(5) == j28);
        assertTrue(a.get(6) == j30);
        assertTrue(a.get(7) == j42);
        controlloHandles(a,h.size());
        min = h.extractMinimum();
        assertTrue(min == j25);
        assertTrue(a.get(0) == j26);
        assertTrue(a.get(1) == j28);
        assertTrue(a.get(2) == j27);
        assertTrue(a.get(3) == j41);
        assertTrue(a.get(4) == j43);
        assertTrue(a.get(5) == j42);
        assertTrue(a.get(6) == j30);
        controlloHandles(a,h.size());
        min = h.extractMinimum();
        assertTrue(min == j26);
        controlloHandles(a,h.size());
        min = h.extractMinimum();
        assertTrue(min == j27);
        controlloHandles(a,h.size());
        min = h.extractMinimum();
        assertTrue(min == j28);
        controlloHandles(a,h.size());
        min = h.extractMinimum();
        assertTrue(min == j30);
        controlloHandles(a,h.size());
        min = h.extractMinimum();
        assertTrue(min == j41);
        controlloHandles(a,h.size());
        min = h.extractMinimum();
        assertTrue(min == j42);
        controlloHandles(a,h.size());
        min = h.extractMinimum();
        assertTrue(min == j43);
        assertTrue(h.size() == 0);
    }

    @Test
    final void testDecreasePriority() {
        TernaryHeapMinPriorityQueue h = new TernaryHeapMinPriorityQueue();
        Job j30 = new Job("Job30", 30);
        h.insert(j30);
        Job j25 = new Job("Job25", 25);
        h.insert(j25);
        Job j41 = new Job("Job41", 41);
        h.insert(j41);
        Job j17 = new Job("Job17", 17);
        h.insert(j17);
        Job j28 = new Job("Job28", 28);
        h.insert(j28);
        Job j15 = new Job("Job15", 15);
        h.insert(j15);
        Job j16 = new Job("Job16", 16);
        h.insert(j16);
        Job j42 = new Job("Job42", 42);
        h.insert(j42);
        Job j43 = new Job("Job43", 43);
        h.insert(j43);
        Job j18 = new Job("Job18", 18);
        h.insert(j18);
        Job j26 = new Job("Job26", 26);
        h.insert(j26);
        Job j27 = new Job("Job27", 27);
        h.insert(j27);
        Job j10 = new Job("Job10", 10);
        h.insert(j10);
        Job j20 = new Job("Job20", 20);
        h.insert(j20);
        Job j21 = new Job("Job21", 21);
        assertThrows(NoSuchElementException.class, () -> h.decreasePriority(j21, 11));
        h.insert(j21);
        assertThrows(IllegalArgumentException.class, () -> h.decreasePriority(j21, 23));
        h.decreasePriority(j43, 19);
        assertTrue(j43.getPriority()== 19);
        assertTrue(j43.getHandle() == 8);
        h.decreasePriority(j30, 14);
        assertTrue(j30.getPriority()== 14);
        assertTrue(j30.getHandle() == 1);
        assertTrue(j16.getHandle() == 4);
        assertTrue(j20.getHandle() == 13);
        h.decreasePriority(j20, 5);
        assertTrue(j20.getPriority()== 5);
        ArrayList<PriorityQueueElement> a = h.getTernaryHeap();
        assertTrue(a.get(0).getPriority() == 5);
        assertTrue(a.get(1).getPriority() == 10);
        assertTrue(a.get(2).getPriority() == 18);
        assertTrue(a.get(3).getPriority() == 15);
        assertTrue(a.get(4).getPriority() == 14);
        assertTrue(a.get(5).getPriority() == 28);
        assertTrue(a.get(6).getPriority() == 17);
        assertTrue(a.get(7).getPriority() == 42);
        assertTrue(a.get(8).getPriority() == 19);
        assertTrue(a.get(9).getPriority() == 41);
        assertTrue(a.get(10).getPriority() == 26);
        assertTrue(a.get(11).getPriority() == 27);
        assertTrue(a.get(12).getPriority() == 25);
        assertTrue(a.get(13).getPriority() == 16);
        assertTrue(a.get(14).getPriority() == 21);
        controlloHandles(a,h.size());
        PriorityQueueElement min = h.extractMinimum();
        assertTrue(min.getPriority() == 5);
        assertTrue(a.get(0).getPriority() == 10);
        assertTrue(a.get(1).getPriority() == 14);
        assertTrue(a.get(2).getPriority() == 18);
        assertTrue(a.get(3).getPriority() == 15);
        assertTrue(a.get(4).getPriority() == 16);
        assertTrue(a.get(5).getPriority() == 28);
        assertTrue(a.get(6).getPriority() == 17);
        assertTrue(a.get(7).getPriority() == 42);
        assertTrue(a.get(8).getPriority() == 19);
        assertTrue(a.get(9).getPriority() == 41);
        assertTrue(a.get(10).getPriority() == 26);
        assertTrue(a.get(11).getPriority() == 27);
        assertTrue(a.get(12).getPriority() == 25);
        assertTrue(a.get(13).getPriority() == 21);
        controlloHandles(a,h.size());
        h.decreasePriority(j27, 13);
        assertTrue(j27.getPriority()== 13);
        min = h.extractMinimum();
        assertTrue(min.getPriority() == 10);
        assertTrue(a.get(0).getPriority() == 13);
        assertTrue(a.get(1).getPriority() == 14);
        assertTrue(a.get(2).getPriority() == 18);
        assertTrue(a.get(3).getPriority() == 15);
        assertTrue(a.get(4).getPriority() == 16);
        assertTrue(a.get(5).getPriority() == 28);
        assertTrue(a.get(6).getPriority() == 17);
        assertTrue(a.get(7).getPriority() == 42);
        assertTrue(a.get(8).getPriority() == 19);
        assertTrue(a.get(9).getPriority() == 41);
        assertTrue(a.get(10).getPriority() == 26);
        assertTrue(a.get(11).getPriority() == 21);
        assertTrue(a.get(12).getPriority() == 25);
        controlloHandles(a,h.size());
    }

    @Test
    final void testClear() {
        TernaryHeapMinPriorityQueue h = new TernaryHeapMinPriorityQueue();
        Job j30 = new Job("Job30", 30);
        h.insert(j30);
        Job j25 = new Job("Job25", 25);
        h.insert(j25);
        Job j41 = new Job("Job41", 41);
        h.insert(j41);
        Job j30bis = new Job("Job30bis", 30);
        h.insert(j30bis);
        Job j25bis = new Job("Job25bis", 25);
        h.insert(j25bis);
        assertTrue(h.size() == 5);
        h.clear();
        assertTrue(h.size() == 0);
        ArrayList<PriorityQueueElement> a = h.getTernaryHeap();
        assertTrue(a.size() == 0);
        
    }
    
    final void controlloHandles(ArrayList<PriorityQueueElement> a, int n) {
        for (int i = 0; i<n; i++)
            assertTrue(a.get(i).getHandle() == i);
    }

}
