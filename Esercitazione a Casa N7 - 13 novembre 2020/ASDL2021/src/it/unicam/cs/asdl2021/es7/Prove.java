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
        if (heap.size() - 1 < i || i < 0) {
            throw new IllegalArgumentException("Index non valido");
        }

        return i * 2 + 2;
    }

    /*
     * Funzione di comodo per calcolare l'indice del genitore del nodo in
     * posizione i. Si noti che la posizione 0 è significativa e contiene sempre
     * la radice dello heap.
     */
    private static int parentIndex(int i) {
        // TODO implementare
        if (heap.size() - 1 < i || i < 0) {
            throw new IllegalArgumentException("Index non valido");
        }

        return (i - 1) / 2;
    }

    private static void MaxHeap(List<Integer> list) {

        heap.clear();

        for (Integer tmp : list) {
            heap.add(tmp);
        }

        //index dell'ultimo nodo
        int last = parentIndex(heap.size() - 1);

        for (int i = last; i >= 0; i--) {
            heapify(i);
        }
    }

    private static void heapify(int i) {
        // TODO implementare
        if (heap.size() < i || i < 0) {
            throw new IllegalArgumentException("Index non valido");
        }

        if (isLeaf(i)) return;

        int left = leftIndex(i), right = rightIndex(i);

        if (left == heap.size() - 1) {
            if (heap.get(i).compareTo(heap.get(left)) < 0) {
                swap(i, left);
                heapify(left);
            }
        } else if (heap.get(i).compareTo(heap.get(left)) < 0 || heap.get(i).compareTo(heap.get(right)) < 0) {
            if (heap.get(left).compareTo(heap.get(right)) > 0) {
                swap(i, left);
                heapify(left);
            } else {
                swap(i, right);
                heapify(right);
            }
        }
    }

    /*
     * Scambia 2 nodi
     */
    private static void swap(int fpos, int spos) {
        int tmp;
        tmp = heap.get(fpos);
        heap.set(fpos, heap.get(spos));
        heap.set(spos, tmp);
    }

    /*
     * return true se la posizione i è una foglia
     */
    private static boolean isLeaf(int i) {
        if (i >= (heap.size() / 2) && i <= heap.size()) {
            return true;
        }
        return false;
    }

    public static int extractMax() {
        int tmp = heap.get(0);

        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);

        heapify(0);

        return tmp;
    }

    public static void insert(Integer el) {
        if (el == null) {
            throw new NullPointerException("Elemento nullo");
        }

        heap.add(el);

        //index dell'ultimo nodo
        int last = parentIndex(heap.size() - 1);

        for (int i = last; i >= 0; i--) {
            heapify(i);
        }
    }

    public static List sort(List<Integer> list) {
        MaxHeap(list);

        List<Integer> riordino = new ArrayList<>();

        while(heap.size() > 0) {
            riordino.add(extractMax());
        }

        return riordino;
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

        System.out.println("test list: " + test.toString());
        test = sort(test);
        System.out.println("heap list: " + heap.toString());
        System.out.println("test list: " + test.toString());

        /*
        System.out.println("inserisco 25");
        insert(25);
        System.out.println("heap list: " + heap.toString());

        System.out.println("#######");

        System.out.println("inserisco 25");
        insert(25);
        System.out.println("heap list: " + heap.toString());

        System.out.println("#######");

        System.out.println("rimuovo max");
        System.out.println("Max: " + extractMax());
        System.out.println("heap list: " + heap.toString());

        System.out.println("#######");

        System.out.println("rimuovo max");
        System.out.println("Max: " + extractMax());
        System.out.println("heap list: " + heap.toString());




        System.out.println(parentIndex(10));
        System.out.println(rightIndex(parentIndex(10)));
        System.out.println(leftIndex(parentIndex(10)));

        test.add(17);
        test.add(15);
        test.add(13);
        test.add(9);
        test.add(6);
        test.add(5);
        test.add(10);
        test.add(4);
        test.add(8);
        test.add(3);
        test.add(1);

        System.out.println(heap.size());
        MaxHeap(test);
        System.out.println(heap.size());
        System.out.println("##############");
        //heap.remove(5);
        //System.out.println(heap.toString());
        //System.out.println(heap.size());

        //heapify(0);
        //System.out.println(heap.toString());

        System.out.println(extractMax());
        System.out.println(heap.toString());
        System.out.println(heap.size());



        int Heap[] = {1, 3, 5, 4, 6, 13, 10, 9, 8, 15, 17};
        int size = Heap.length;

        for (int tmp: Heap) {
            System.out.print(tmp + " ");
        }
        System.out.println(" ");

        buildHeap(Heap,size);

        for (int tmp: Heap) {
            System.out.print(tmp + " ");
        }
        System.out.println(" ");

         */

    }


}