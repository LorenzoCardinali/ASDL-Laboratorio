package it.unicam.cs.asdl2021.mp1;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TernaryHeapMinPriorityQueueTest {

    private final static int MAX_PROBLEM_SIZE = 9999;
    private final static double DOUBLE_DECREASE = 0.0000000000000001d;

    TernaryHeapMinPriorityQueue ternMinPrHeap = new TernaryHeapMinPriorityQueue();

    @Test
    void size() {
        assertEquals(0, ternMinPrHeap.size());
        for(int i = 1; i <= MAX_PROBLEM_SIZE; i++){
            ternMinPrHeap.insert(new Job("Job "+i,(i+1)/2d));
            assertEquals(i, ternMinPrHeap.size());
        }

        for(int i = MAX_PROBLEM_SIZE; i>0 ; i--){
            ternMinPrHeap.extractMinimum();
            assertEquals(i-1, ternMinPrHeap.size());
        }

        for(int i = 1; i <= MAX_PROBLEM_SIZE; i++)
            ternMinPrHeap.insert(new Job("Job "+i,(i+1)/2d));

        ternMinPrHeap.clear();
        assertEquals(0, ternMinPrHeap.size());
    }

    @Test
    void insert() {
        assertThrows(NullPointerException.class, ()->new TernaryHeapMinPriorityQueue().insert(null));
        ArrayList<Job> toCompareList = new ArrayList<>();
        ternMinPrHeap.clear();
        Job element;
        for(int i = 1; i <= MAX_PROBLEM_SIZE; i++) {
            element=new Job("Job "+i,  (1d / i));
            toCompareList.add(element);
            ternMinPrHeap.insert(element);
        }

        Collections.sort(toCompareList,new priorityQueueElementComparator());

        for(int i = 0; i < MAX_PROBLEM_SIZE; i++)
            assertEquals(toCompareList.get(i), ternMinPrHeap.extractMinimum());
    }

    @Test
    void minimum() {
        assertThrows(NoSuchElementException.class, ()->new TernaryHeapMinPriorityQueue().minimum());

        ArrayList<Job> toCompareList = new ArrayList<>();
        ternMinPrHeap.clear();
        for(int i = 1; i <= MAX_PROBLEM_SIZE; i++)
            toCompareList.add(new Job("Job "+i,(1d/i)));

        Collections.shuffle(toCompareList);
        Job minimum = Collections.min(toCompareList, new priorityQueueElementComparator());

        for (Job job: toCompareList)
            ternMinPrHeap.insert(job);

        assertEquals(minimum, ternMinPrHeap.minimum());
    }

    @Test
    void extractMinimum() {
        ternMinPrHeap.clear();
        assertThrows(NoSuchElementException.class, ()->new TernaryHeapMinPriorityQueue().minimum());

        ArrayList<Job> toCompareList = new ArrayList<>();
        ternMinPrHeap.clear();
        for(int i = 1; i <= MAX_PROBLEM_SIZE; i++)
            toCompareList.add(new Job("Job "+i,(1d/i)));

        Collections.shuffle(toCompareList);
        for (Job job: toCompareList)
            ternMinPrHeap.insert(job);

        Job minimum;
        priorityQueueElementComparator comparator =
                new priorityQueueElementComparator();

        while(ternMinPrHeap.size()>0) {
            minimum = Collections.min(toCompareList, new priorityQueueElementComparator());
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
            Job support = new Job("Job " + i, (1d / i));
            ternMinPrHeap.insert(support);
        }
        //creating a copy of the heap
        ternMinPrHeap.getTernaryHeap().stream().forEach((queueElement) -> jobsList.add(new Job(((Job) queueElement).getName(), ((Job) queueElement).getPriority())));

        //checking exceptions
        {
            final TernaryHeapMinPriorityQueue testingExceptions = ternMinPrHeap;
            assertThrows(IllegalArgumentException.class, () -> testingExceptions.decreasePriority(jobsList.get(0), 2d));
            assertThrows(NoSuchElementException.class, () -> testingExceptions.decreasePriority(new Job("https://youtu.be/T6NcUlxtIuw", 0.8950850969d), 0.2d));
        }

        //decreasing priority. No exception expected
        for (int i = 0; i < MAX_PROBLEM_SIZE; i++) {
            final Job element = (Job) jobsList.get(i);
            double newPriority = (jobsList.get(i).getPriority() - DOUBLE_DECREASE);
            ternMinPrHeap.decreasePriority(element, newPriority);
        }

        priorityQueueElementComparator comparator = new priorityQueueElementComparator();
        while (!jobsList.isEmpty())
        {
            Job minimumFromList = (Job)Collections.min(jobsList, comparator);
            jobsList.remove(minimumFromList);
            Job minimumFromHeap = (Job)ternMinPrHeap.extractMinimum();
            assertEquals(minimumFromList.getName(),minimumFromHeap.getName());
            assertTrue(comparator.compare(minimumFromList,minimumFromHeap)>0);
        }
    }

    @Test
    void clear() {
        for(int i = 1; i <= MAX_PROBLEM_SIZE; i++)
            ternMinPrHeap.insert(new Job("Job "+i,(1d/i)));
        assertEquals(MAX_PROBLEM_SIZE, ternMinPrHeap.size());

        ternMinPrHeap.clear();
        assertEquals(0, ternMinPrHeap.size());
        assertThrows(NoSuchElementException.class, ()->new TernaryHeapMinPriorityQueue().minimum());
        assertThrows(NoSuchElementException.class, ()->new TernaryHeapMinPriorityQueue().minimum());
    }

    private static class priorityQueueElementComparator implements Comparator<PriorityQueueElement> {
        @Override
        public int compare(PriorityQueueElement o1, PriorityQueueElement o2) {
            return Double.compare(o1.getPriority(),o2.getPriority());
        }
    }
}