package it.unicam.cs.asdl2021.es7;


import java.util.ArrayList;
import java.util.List;

class Prove {
    public static ArrayList<Integer> heap = new ArrayList<>();
    public static List<Integer> test = new ArrayList<>();

    private static int leftIndex(int i) {
        // TODO implementare
        //if (heap.size() - 1 < i || i < 0) {
        //    throw new IllegalArgumentException("Index non valido");
        //}

        return i * 2 + 1;
    }

    /*
     * Funzione di comodo per calcolare l'indice del figlio destro del nodo in
     * posizione i. Si noti che la posizione 0 è significativa e contiene sempre
     * la radice dello heap.
     */
    private static int rightIndex(int i) {
        // TODO implementare
        //if (heap.size() - 1 < i || i < 0) {
        //    throw new IllegalArgumentException("Index non valido");
        //}

        return i * 2 + 2;
    }

    /*
     * Funzione di comodo per calcolare l'indice del genitore del nodo in
     * posizione i. Si noti che la posizione 0 è significativa e contiene sempre
     * la radice dello heap.
     */
    private static int parentIndex(int i) {
        // TODO implementare
        //if (heap.size() - 1 < i || i < 0) {
        //    throw new IllegalArgumentException("Index non valido");
        //}

        return (i - 1) / 2;
    }

    private static void MaxHeap(List<Integer> list) {
        // TODO implementare
        if (list == null) {
            throw new NullPointerException("Lista nulla.");
        }

        heap.clear();

        for (Integer i : list) {
            heap.add(i);
        }

        System.out.println(heap.toString());
    }

    public static void main(String[] args) {

        /*
        ArrayList<Integer> test = new ArrayList();

        for (int i = 10; i >= 0; i--) {
            test.add(i);
        }

        BubbleSort list = new BubbleSort();

        System.out.println(test.toString());
        System.out.println(test.get(0));

        list.sort(test);

        System.out.println(test.toString());
        System.out.println(test.get(0));

        System.out.println(7 / 2);



        System.out.println(leftIndex(4));
        System.out.println(rightIndex(4));
        System.out.println(parentIndex(9));
        System.out.println(parentIndex(10));
        System.out.println(parentIndex(0));



        int[] Heap = new int[30];
        int size = 6, element;

        element = 4;


        Heap[++size] = element;
        int current = size-1;


        System.out.println(current);
        */

        test.add(1);
        test.add(3);
        test.add(5);
        test.add(4);
        test.add(6);
        test.add(13);
        test.add(10);
        test.add(9);
        test.add(8);
        test.add(15);
        test.add(17);

        System.out.println(heap.size());
        MaxHeap(test);
        System.out.println(heap.size());
        heap.remove(5);
        System.out.println(heap.toString());
        System.out.println(heap.size());
    }


}