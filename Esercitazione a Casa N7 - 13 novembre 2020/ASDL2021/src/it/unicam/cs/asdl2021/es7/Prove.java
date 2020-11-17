package it.unicam.cs.asdl2021.es7;

import java.io.IOException;
import java.util.*;


class Prove {

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
        */

        System.out.println(leftIndex(4));
        System.out.println(rightIndex(4));
        System.out.println(parentIndex(9));
        System.out.println(parentIndex(10));


    }


}