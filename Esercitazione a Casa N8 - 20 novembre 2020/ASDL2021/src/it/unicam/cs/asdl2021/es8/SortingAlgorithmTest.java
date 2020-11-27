package it.unicam.cs.asdl2021.es8;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * @author Luca Tesei
 *
 */
class SortingAlgorithmTest {

    /**
     * Test method for
     * {@link it.unicam.cs.asdl2021.es8.SortingAlgorithm#sort(java.util.List)}.
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    final void testSort() {
        ArrayList<Integer> l = new ArrayList<>();
        l.add(15);
        l.add(90);
        l.add(87);
        l.add(33);
        l.add(15);
        l.add(55);
        l.add(1);
        l.add(7);
        l.add(44);
        l.add(55);
        ArrayList<Integer> ordered = new ArrayList<>();
        ordered.add(1);
        ordered.add(7);
        ordered.add(15);
        ordered.add(15);
        ordered.add(33);
        ordered.add(44);
        ordered.add(55);
        ordered.add(55);
        ordered.add(87);
        ordered.add(90);

        SortingAlgorithm<Integer> bubbleSort = new BubbleSort();
        SortingAlgorithmResult<Integer> result = bubbleSort.sort((ArrayList<Integer>) l.clone());
        assertEquals(ordered, result.getL());
        assertTrue(result.checkOrder());

        SortingAlgorithm<Integer> heapSort = new HeapSort();
        result = heapSort.sort((ArrayList<Integer>) l.clone());
        assertEquals(ordered, result.getL());
        assertTrue(result.checkOrder());

        SortingAlgorithm<Integer> insertionSort = new InsertionSort();
        result = insertionSort.sort((ArrayList<Integer>) l.clone());
        assertEquals(ordered, result.getL());
        assertTrue(result.checkOrder());

        SortingAlgorithm<Integer> quickSort = new QuickSort();
        result = quickSort.sort((ArrayList<Integer>) l.clone());
        assertEquals(ordered, result.getL());
        assertTrue(result.checkOrder());

        SortingAlgorithm<Integer> quickSortRandom = new QuickSortRandom();
        result = quickSortRandom.sort((ArrayList<Integer>) l.clone());
        assertEquals(ordered, result.getL());
        assertTrue(result.checkOrder());

        SortingAlgorithm<Integer> mergeSort = new MergeSort();
        result = mergeSort.sort((ArrayList<Integer>) l.clone());
        assertEquals(ordered, result.getL());
        assertTrue(result.checkOrder());

    }

}
