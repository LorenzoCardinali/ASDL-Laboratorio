package it.unicam.cs.asdl2021.mp1;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TernaryHeapMinPriorityQueueTest {

    private final static int MAX_PROBLEM_SIZE = 1000;
    private final static double DOUBLE_DECREASE = 0.0000000000000001d;

    TernaryHeapMinPriorityQueue ternMinPrHeap = new TernaryHeapMinPriorityQueue();

    @Test
    void size() {
        assertEquals(0, ternMinPrHeap.size());
        for (int i = 1; i <= MAX_PROBLEM_SIZE; i++) {
            ternMinPrHeap.insert(new Job("Job " + i, (i + 1) / 2d));
            assertEquals(i, ternMinPrHeap.size());
        }

        for (int i = MAX_PROBLEM_SIZE; i > 0; i--) {
            ternMinPrHeap.extractMinimum();
            assertEquals(i - 1, ternMinPrHeap.size());
        }

        for (int i = 1; i <= MAX_PROBLEM_SIZE; i++)
            ternMinPrHeap.insert(new Job("Job " + i, (i + 1) / 2d));

        ternMinPrHeap.clear();
        assertEquals(0, ternMinPrHeap.size());
    }

    @Test
    void insert() {
        assertThrows(NullPointerException.class, () -> new TernaryHeapMinPriorityQueue().insert(null));
        ternMinPrHeap.clear();
        Job element;
        for (int i = 1; i <= MAX_PROBLEM_SIZE; i++)
            ternMinPrHeap.insert(new Job("Job " + i, (1d / i)));

        ArrayList<PriorityQueueElement> jobslist = ternMinPrHeap.getTernaryHeap();

        for (int i = 0; i < jobslist.size(); i++)
            assertEquals(i, jobslist.get(i).getHandle());

        assertTrue(checkMinHeapProperty(jobslist));
    }

    @Test
    void minimum() {
        assertThrows(NoSuchElementException.class, () -> new TernaryHeapMinPriorityQueue().minimum());

        ArrayList<Job> toCompareList = new ArrayList<>();
        ternMinPrHeap.clear();
        for (int i = 1; i <= MAX_PROBLEM_SIZE; i++)
            toCompareList.add(new Job("Job " + i, (1d / i)));

        Collections.shuffle(toCompareList);
        Job minimum = Collections.min(toCompareList, new PriorityQueueElementComparator());

        for (Job job : toCompareList)
            ternMinPrHeap.insert(job);

        assertEquals(0, new PriorityQueueElementComparator().compare(minimum, ternMinPrHeap.minimum()));
        assertEquals(minimum.getHandle(), ternMinPrHeap.minimum().getHandle());

    }

    @Test
    void extractMinimum() {
        ternMinPrHeap.clear();
        assertThrows(NoSuchElementException.class, () -> new TernaryHeapMinPriorityQueue().minimum());

        ArrayList<Job> toCompareList = new ArrayList<>();
        ternMinPrHeap.clear();
        for (int i = 1; i <= MAX_PROBLEM_SIZE; i++)
            toCompareList.add(new Job("Job " + i, (1d / i)));

        Collections.shuffle(toCompareList);
        for (Job job : toCompareList)
            ternMinPrHeap.insert(job);

        Job minimum;
        PriorityQueueElementComparator comparator =
                new PriorityQueueElementComparator();

        while (ternMinPrHeap.size() > 0) {
            minimum = Collections.min(toCompareList, new PriorityQueueElementComparator());
            toCompareList.remove(minimum);
            assertEquals(0, comparator.compare(minimum, ternMinPrHeap.extractMinimum()));
        }

        assertEquals(0, ternMinPrHeap.size());
    }

    @Test
    void decreasePriority() {
        assertThrows(NullPointerException.class, () -> new TernaryHeapMinPriorityQueue().decreasePriority(null, 5.d));
        ternMinPrHeap.clear();
        ArrayList<PriorityQueueElement> jobsList = new ArrayList();
        for (int i = 1; i <= MAX_PROBLEM_SIZE; i++) {
            ternMinPrHeap.insert(new Job("Job " + i, (1d / i)));
        }

        //creating clones of the heap with all the elements having handle set to 0 (to test with extractMinimum)
        ternMinPrHeap.getTernaryHeap().stream().forEach((queueElement) -> {
            Job support = new Job(((Job) queueElement).getName(), (queueElement.getPriority()));//CLONES NOT COPIES!
            support.setHandle(0);
            jobsList.add(support);
        });
        //Sorting the clones' list
        Collections.sort(jobsList, new PriorityQueueElementComparator());

        //checking exceptions
        final TernaryHeapMinPriorityQueue testingExceptions = ternMinPrHeap;
        assertThrows(IllegalArgumentException.class, () -> testingExceptions.decreasePriority(jobsList.get(0), 2d));
        assertThrows(NoSuchElementException.class, () -> testingExceptions.decreasePriority(new Job("https://youtu.be/T6NcUlxtIuw", 0.8950850969d), 0.2d));

        for (PriorityQueueElement element : jobsList) {
            ArrayList<PriorityQueueElement> heap = ternMinPrHeap.getTernaryHeap();
            for (int i = 0; i < heap.size(); i++)
                ternMinPrHeap.decreasePriority(heap.get(i), heap.get(i).getPriority() - DOUBLE_DECREASE);

            assertTrue(checkMinHeapProperty(heap));

            /*
            The Job class does not redefine equals() so two distinct objects
            sharing the same attributes, should always be considered different.

            If this doesn't happen, the test will fail.
             */
            PriorityQueueElement support = ternMinPrHeap.extractMinimum();

            assertNotEquals(element, support);

            assertEquals(element.getHandle(), support.getHandle());
            assertTrue(Double.compare(element.getPriority(), support.getPriority()) > 0);
        }
    }

    @Test
    void clear() {
        for (int i = 1; i <= MAX_PROBLEM_SIZE; i++)
            ternMinPrHeap.insert(new Job("Job " + i, (1d / i)));
        assertEquals(MAX_PROBLEM_SIZE, ternMinPrHeap.size());

        ternMinPrHeap.clear();
        assertEquals(0, ternMinPrHeap.size());
        assertThrows(NoSuchElementException.class, () -> new TernaryHeapMinPriorityQueue().minimum());
        assertThrows(NoSuchElementException.class, () -> new TernaryHeapMinPriorityQueue().extractMinimum());
    }

    private static class PriorityQueueElementComparator implements Comparator<PriorityQueueElement> {
        @Override
        public int compare(PriorityQueueElement o1, PriorityQueueElement o2) {
            return Double.compare(o1.getPriority(), o2.getPriority());
        }
    }

    private boolean checkMinHeapProperty(ArrayList<PriorityQueueElement> list) {
        PriorityQueueElementComparator comparator = new PriorityQueueElementComparator();
        for (int i = list.size() - 1; i > 0; i--)
            if (comparator.compare(list.get(i), list.get(getParentIndex(i))) >= 0)
                continue;
            else return false;
        return true;
    }

    private int getParentIndex(int index) {
        return (index - 1) / 3;
    }

}