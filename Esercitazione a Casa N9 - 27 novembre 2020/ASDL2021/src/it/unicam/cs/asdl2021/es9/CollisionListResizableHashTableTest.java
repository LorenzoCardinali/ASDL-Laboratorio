package it.unicam.cs.asdl2021.es9;

import it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable;
import it.unicam.cs.asdl2021.es9.DivisionPrimaryHashFunction;
import it.unicam.cs.asdl2021.es9.MultiplicationPrimaryHashFunction;
import it.unicam.cs.asdl2021.es9.PrimaryHashFunction;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test JUnit per la classe CollisionListResizableHashTable
 * 
 * @author Daniele Marchei
 *
 */
class CollisionListResizableHashTableTest {

    private it.unicam.cs.asdl2021.es9.PrimaryHashFunction divisionHash = new DivisionPrimaryHashFunction();

    private PrimaryHashFunction multiplicationHash = new MultiplicationPrimaryHashFunction();

    @Test
    void divisionHash_testContains() {
        int val = 42;
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                divisionHash);
        table.add(val);
        assertTrue(table.contains(val));
    }

    @Test
    void multiplicationHash_testContains() {
        int val = 42;
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                multiplicationHash);
        table.add(val);
        assertTrue(table.contains(val));
    }

    @Test
    void divisionHash_testContainsNull() {
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                divisionHash);
        assertThrows(NullPointerException.class, () -> {
            table.contains(null);
        });
    }

    @Test
    void multiplicationHash_testContainsNull() {
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                multiplicationHash);
        assertThrows(NullPointerException.class, () -> {
            table.contains(null);
        });
    }

    @Test
    void divisionHash_testContainsEmpty() {
        int val = 42;
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                divisionHash);
        assertFalse(table.contains(val));
    }

    @Test
    void multiplicationHash_testContainsEmpty() {
        int val = 42;
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                multiplicationHash);
        assertFalse(table.contains(val));
    }

    @Test
    void divisionHash_testNotContains() {
        int val = 42;
        int val_not_in = 10;
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                divisionHash);
        table.add(val);
        assertFalse(table.contains(val_not_in));
    }

    @Test
    void multiplicationHash_testNotContains() {
        int val = 42;
        int val_not_in = 10;
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                multiplicationHash);
        table.add(val);
        assertFalse(table.contains(val_not_in));
    }

    @Test
    void divisionHash_testAdd() {
        int val = 42;
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                divisionHash);
        assertTrue(table.add(val));
    }

    @Test
    void multiplicationHash_testAdd() {
        int val = 42;
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                multiplicationHash);
        assertTrue(table.add(val));
    }

    @Test
    void divisionHash_testAddNull() {
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                divisionHash);
        assertThrows(NullPointerException.class, () -> {
            table.add(null);
        });
    }

    @Test
    void multiplicationHash_testAddNull() {
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                multiplicationHash);
        assertThrows(NullPointerException.class, () -> {
            table.add(null);
        });
    }

    @Test
    void divisionHash_testNotAdd() {
        int val = 42;
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                divisionHash);
        table.add(val);
        assertFalse(table.add(val));
    }

    @Test
    void multiplicationHash_testNotAdd() {
        int val = 42;
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                multiplicationHash);
        table.add(val);
        assertFalse(table.add(val));
    }

    @Test
    void divisionHash_testRemove() {
        int val = 42;
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                divisionHash);
        table.add(val);
        assertTrue(table.remove(val));
    }

    @Test
    void multiplicationHash_testRemove() {
        int val = 42;
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                multiplicationHash);
        table.add(val);
        assertTrue(table.remove(val));
    }

    @Test
    void divisionHash_testRemoveNull() {
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                divisionHash);
        assertThrows(NullPointerException.class, () -> {
            table.remove(null);
        });
    }

    @Test
    void multiplicationHash_testRemoveNull() {
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                multiplicationHash);
        assertThrows(NullPointerException.class, () -> {
            table.remove(null);
        });
    }

    @Test
    void divisionHash_testNotRemove() {
        int val = 42;
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                divisionHash);
        assertFalse(table.remove(val));
    }

    @Test
    void multiplicationHash_testNotRemove() {
        int val = 42;
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                multiplicationHash);
        assertFalse(table.remove(val));
    }

    @Test
    void divisionHash_testContainsAll() {
        Integer vals[] = { 12, 42, 5, 32, 777, 11 };
        List<Integer> vals_list = Arrays.asList(vals);
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                divisionHash);
        table.addAll(vals_list);
        assertTrue(table.containsAll(Arrays.asList(vals)));
    }

    @Test
    void multiplicationHash_testContainsAll() {
        Integer vals[] = { 12, 42, 5, 32, 777, 11 };
        List<Integer> vals_list = Arrays.asList(vals);
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(multiplicationHash);
        table.addAll(vals_list);
        table.printHash();
        assertTrue(table.containsAll(Arrays.asList(vals)));
    }

    @Test
    void divisionHash_testContainsAllWithNull() {
        Integer vals[] = { null, 12, 42, 5, 32, 777, 11 };
        List<Integer> vals_list = Arrays.asList(vals);
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                divisionHash);
        assertThrows(NullPointerException.class, () -> {
            table.containsAll(vals_list);
        });
    }

    @Test
    void multiplicationHash_testContainsAllWithNull() {
        Integer vals[] = { null, 12, 42, 5, 32, 777, 11 };
        List<Integer> vals_list = Arrays.asList(vals);
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                multiplicationHash);
        assertThrows(NullPointerException.class, () -> {
            table.containsAll(vals_list);
        });
    }

    @Test
    void divisionHash_testContainsNotAll() {
        Integer vals[] = { 12, 42, 5, 32, 777, 11 };
        List<Integer> vals_list = Arrays.asList(vals);
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                divisionHash);
        table.addAll(vals_list);
        Integer vals_not_in[] = { 12, 42, 5, 32, 777, 11, -51 };
        assertFalse(table.containsAll(Arrays.asList(vals_not_in)));
    }

    @Test
    void multiplicationHash_testContainsNotAll() {
        Integer vals[] = { 12, 42, 5, 32, 777, 11 };
        List<Integer> vals_list = Arrays.asList(vals);
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                multiplicationHash);
        table.addAll(vals_list);
        Integer vals_not_in[] = { 12, 42, 5, 32, 777, 11, -51 };
        assertFalse(table.containsAll(Arrays.asList(vals_not_in)));
    }

    @Test
    void divisionHash_testAddAll() {
        Integer vals[] = { 12, 42, 5, 32, 777, 11 };
        List<Integer> vals_list = Arrays.asList(vals);
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                divisionHash);
        assertTrue(table.addAll(vals_list));
    }

    @Test
    void multiplicationHash_testAddAll() {
        Integer vals[] = { 12, 42, 5, 32, 777, 11 };
        List<Integer> vals_list = Arrays.asList(vals);
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                multiplicationHash);
        assertTrue(table.addAll(vals_list));
    }

    @Test
    void divisionHash_testAddAllWithNull() {
        Integer vals[] = { 12, 42, 5, 32, null, 777, 11 };
        List<Integer> vals_list = Arrays.asList(vals);
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                divisionHash);
        assertThrows(NullPointerException.class, () -> {
            table.addAll(vals_list);
        });
    }

    @Test
    void multiplicationHash_testAddAllWithNull() {
        Integer vals[] = { 12, 42, 5, 32, null, 777, 11 };
        List<Integer> vals_list = Arrays.asList(vals);
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                multiplicationHash);
        assertThrows(NullPointerException.class, () -> {
            table.addAll(vals_list);
        });
    }

    @Test
    void divisionHash_testAddNotAll() {
        Integer vals[] = { 12, 42, 5, 32, 777, 11 };
        int val_already_in = 12;
        List<Integer> vals_list = Arrays.asList(vals);
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                divisionHash);
        table.add(val_already_in);
        assertTrue(table.addAll(vals_list));
    }

    @Test
    void multiplicationHash_testAddNotAll() {
        Integer vals[] = { 12, 42, 5, 32, 777, 11 };
        int val_already_in = 12;
        List<Integer> vals_list = Arrays.asList(vals);
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                multiplicationHash);
        table.add(val_already_in);
        assertTrue(table.addAll(vals_list));
    }

    @Test
    void divisionHash_testRemoveAll() {
        Integer vals[] = { 12, 42, 5, 32, 777, 11 };
        List<Integer> vals_list = Arrays.asList(vals);
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                divisionHash);
        table.addAll(vals_list);
        assertTrue(table.removeAll(vals_list));
    }

    @Test
    void multiplicationHash_testRemoveAll() {
        Integer vals[] = { 12, 42, 5, 32, 777, 11 };
        List<Integer> vals_list = Arrays.asList(vals);
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                multiplicationHash);
        table.addAll(vals_list);
        assertTrue(table.removeAll(vals_list));
    }

    @Test
    void divisionHash_testRemoveAllWithNull() {
        Integer vals[] = { 12, 42, 5, 32, 777, 11 };
        List<Integer> vals_list = Arrays.asList(vals);
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                divisionHash);
        table.addAll(vals_list);
        Integer vals_to_remove[] = { 12, 42, 5, 32, null, 777, 11 };
        assertThrows(NullPointerException.class, () -> {
            table.removeAll(Arrays.asList(vals_to_remove));
        });
    }

    @Test
    void multiplicationHash_testRemoveAllWithNull() {
        Integer vals[] = { 12, 42, 5, 32, 777, 11 };
        List<Integer> vals_list = Arrays.asList(vals);
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                multiplicationHash);
        table.addAll(vals_list);
        Integer vals_to_remove[] = { 12, 42, 5, 32, null, 777, 11 };
        assertThrows(NullPointerException.class, () -> {
            table.removeAll(Arrays.asList(vals_to_remove));
        });
    }

    @Test
    void divisionHash_testRemoveNotAll() {
        Integer vals[] = { 12, 42, 5, 32, 777, 11 };
        List<Integer> vals_list = Arrays.asList(vals);
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                divisionHash);
        table.addAll(vals_list);
        Integer vals_not_in[] = { 12, 42, 5, 32, 777, 11, -51 };
        assertTrue(table.removeAll(Arrays.asList(vals_not_in)));
    }

    @Test
    void multiplicationHash_testRemoveNotAll() {
        Integer vals[] = { 12, 42, 5, 32, 777, 11 };
        List<Integer> vals_list = Arrays.asList(vals);
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                multiplicationHash);
        table.addAll(vals_list);
        Integer vals_not_in[] = { 12, 42, 5, 32, 777, 11, -51 };
        assertTrue(table.removeAll(Arrays.asList(vals_not_in)));
    }

    /*
    @Test
    void divisionHash_testShouldResize() {
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                divisionHash);
        Integer vals[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        table.addAll(Arrays.asList(vals));
        int tableLengthBeforeAdd = table.getTable().length;
        table.add(13);
        int tableLengthAfterAdd = table.getTable().length;
        assertEquals(tableLengthBeforeAdd * 2, tableLengthAfterAdd);
    }

    @Test
    void divisionHash_testShouldNotResize() {
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                divisionHash);
        Integer vals[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
        table.addAll(Arrays.asList(vals));
        int tableLengthBeforeAdd = table.getTable().length;
        table.add(12);
        int tableLengthAfterAdd = table.getTable().length;
        assertEquals(tableLengthBeforeAdd, tableLengthAfterAdd);
    }

    @Test
    void multiplicationHash_testShouldResize() {
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                multiplicationHash);
        Integer vals[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        table.addAll(Arrays.asList(vals));
        int tableLengthBeforeAdd = table.getTable().length;
        table.add(13);
        int tableLengthAfterAdd = table.getTable().length;
        assertEquals(tableLengthBeforeAdd * 2, tableLengthAfterAdd);
    }

    @Test
    void multiplicationHash_testShouldNotResize() {
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                multiplicationHash);
        Integer vals[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
        table.addAll(Arrays.asList(vals));
        int tableLengthBeforeAdd = table.getTable().length;
        table.add(12);
        int tableLengthAfterAdd = table.getTable().length;
        assertEquals(tableLengthBeforeAdd, tableLengthAfterAdd);
    }
    */

    @Test
    void divisionHash_testIteratorShouldFailFast() {
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                divisionHash);
        Integer vals[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
        table.addAll(Arrays.asList(vals));
        Iterator<Integer> it = table.iterator();
        it.next();
        table.add(42);
        assertThrows(ConcurrentModificationException.class, () -> {
            it.next();
        });
    }

    @Test
    void multiplicationHash_testIteratorShouldFailFast() {
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                multiplicationHash);
        Integer vals[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
        table.addAll(Arrays.asList(vals));
        Iterator<Integer> it = table.iterator();
        it.next();
        table.add(42);
        assertThrows(ConcurrentModificationException.class, () -> {
            it.next();
        });
    }

    @Test
    void divisionHash_testIteratorShouldNotFailFast() {
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<>(
                divisionHash);
        Integer vals[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
        table.addAll(Arrays.asList(vals));
        Iterator<Integer> it = table.iterator();
        boolean hasThrown = false;
        while (it.hasNext() && !hasThrown) {
            try {
                it.next();
            } catch (Exception e) {
                hasThrown = true;
            }
        }

        assertFalse(hasThrown);
    }

    @Test
    void multiplicationHash_testIteratorShouldNotFailFast() {
        it.unicam.cs.asdl2021.es9.CollisionListResizableHashTable<Integer> table = new CollisionListResizableHashTable<>(
                multiplicationHash);
        Integer vals[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
        table.addAll(Arrays.asList(vals));
        Iterator<Integer> it = table.iterator();
        boolean hasThrown = false;
        while (it.hasNext() && !hasThrown) {
            try {
                it.next();
            } catch (Exception e) {
                hasThrown = true;
            }
        }

        assertFalse(hasThrown);
    }

}
