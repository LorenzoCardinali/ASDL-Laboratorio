package it.unicam.cs.asdl2021.mp1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ASDL2021DequeTest {

    private final static int MAX_PROBLEM_SIZE = 9999;

    private ASDL2021Deque<String> mDequeue = new ASDL2021Deque<String>();


    @Test
    public void testDeque() {
        assertNotNull(mDequeue);
    }

    @Test
    public void testIsEmpty() {
        assertTrue(mDequeue.isEmpty());
    }

    @Test
    public void testIsEmptyAfterAddRemoveFirst() {
        mDequeue.addFirst("Something");
        boolean empty = mDequeue.isEmpty();
        assertFalse(empty);
        mDequeue.removeFirst();

        empty = mDequeue.isEmpty();
        assertTrue(empty, "Should be empty after adding then removing");

    }

    @Test
    public void testIsEmptyAfterAddRemoveLast() {
        mDequeue.addLast("Something");
        assertFalse(mDequeue.isEmpty());
        mDequeue.removeLast();
        assertTrue(mDequeue.isEmpty(), "Should be empty after adding then removing");

    }

    @Test
    public void testIsEmptyAfterAddFirstRemoveLast() {
        mDequeue.addFirst("Something");
        assertFalse(mDequeue.isEmpty());
        mDequeue.removeLast();
        assertTrue(mDequeue.isEmpty(), "Should be empty after adding then removing");
    }

    @Test
    public void testIsEmptyAfterAddLastRemoveFirst() {
        mDequeue.addLast("Something");
        assertFalse(mDequeue.isEmpty());
        mDequeue.removeFirst();
        assertTrue(mDequeue.isEmpty(), "Should be empty after adding then removing");
    }

    @Test
    public void testIsEmptyAfterMultipleAddRemove() {
        for (int i = 0; i < MAX_PROBLEM_SIZE; i++) {
            mDequeue.addFirst("Something");
            assertFalse(mDequeue.isEmpty(), "Should not be empty after " + i + " item added");
        }

        for (int i = 0; i < MAX_PROBLEM_SIZE; i++) {
            assertFalse(mDequeue.isEmpty(), "Should not be empty after " + i + " item removed");
            mDequeue.removeLast();
        }

        assertTrue(mDequeue.isEmpty(), "Should be empty after adding and removing "
                + MAX_PROBLEM_SIZE + " elements.");
    }

    @Test
    public void testMultipleFillAndEmpty() {
        for (int tries = 0; tries < 50; tries++) {
            for (int i = 0; i < MAX_PROBLEM_SIZE; i++) {
                mDequeue.addFirst(String.valueOf(i));
            }

            assertFalse(mDequeue.isEmpty());
            int i = 0;
            while (!mDequeue.isEmpty()) {
                assertEquals(String.valueOf(i), mDequeue.removeLast());
                i++;
            }

            assertTrue(mDequeue.isEmpty());

            for (int j = 0; j < MAX_PROBLEM_SIZE; j++) {
                mDequeue.addLast(String.valueOf(j));
            }

            assertFalse(mDequeue.isEmpty());

            i = 0;
            while (!mDequeue.isEmpty()) {
                assertEquals(String.valueOf(i), mDequeue.removeFirst());
                i++;
            }

            assertTrue(mDequeue.isEmpty());


            for (int j = 0; j < MAX_PROBLEM_SIZE; j++) {
                mDequeue.addLast(String.valueOf(j));
            }

            mDequeue.clear();

            assertTrue(mDequeue.isEmpty());
        }
    }

    @Test
    public void testToArray() {
        ArrayList<String> resultComparator = new ArrayList<>();
        for (int i = 0; i < MAX_PROBLEM_SIZE; i++) {
            mDequeue.add(String.valueOf(i));
            resultComparator.add(String.valueOf(i));
        }
        assertArrayEquals(resultComparator.toArray(), mDequeue.toArray());

    }

    @Test
    public void testSize() {
        assertEquals(0, mDequeue.size());
        for (int i = 0; i < MAX_PROBLEM_SIZE; i++) {
            mDequeue.addFirst("Something");
            assertEquals(i + 1, mDequeue.size());
        }

        for (int i = MAX_PROBLEM_SIZE; i > 0; i--) {
            assertEquals(i, mDequeue.size());
            mDequeue.removeLast();
        }

        assertEquals(0, mDequeue.size());
    }

    @Test
    public void testAddFirst() {
        String[] aBunchOfString = {
                "One",
                "Two",
                "Three",
                "Four"
        };

        for (String aString : aBunchOfString) {
            mDequeue.addFirst(aString);
        }

        for (int i = aBunchOfString.length - 1; i >= 0; i--) {
            assertEquals(aBunchOfString[i], mDequeue.removeFirst());
        }
    }

    @Test
    public void testAddLast() {
        String[] aBunchOfString = {
                "One",
                "Two",
                "Three",
                "Four"
        };

        for (String aString : aBunchOfString) {
            mDequeue.addLast(aString);
        }

        for (int i = aBunchOfString.length - 1; i >= 0; i--) {
            assertEquals(aBunchOfString[i], mDequeue.removeLast());
        }
    }

    @Test
    public void testAddNull() {
        try {
            mDequeue.addFirst(null);
            fail("Should have thrown a NullPointerException");
        } catch (NullPointerException npe) {
            // Continue
        } catch (Exception e) {
            fail("Wrong exception catched." + e);
        }

        try {
            mDequeue.addLast(null);
            fail("Should have thrown a NullPointerException");
        } catch (NullPointerException npe) {
            // Continue
        } catch (Exception e) {
            fail("Wrong exception catched." + e);
        }
    }

    @Test
    public void testRemoveFirst() {
        for (int i = 0; i < MAX_PROBLEM_SIZE; i++) {
            mDequeue.addFirst(String.valueOf(i));
            assertEquals(String.valueOf(i), mDequeue.removeFirst());
        }

        mDequeue = new ASDL2021Deque<String>();

        for (int i = 0; i < MAX_PROBLEM_SIZE; i++) {
            mDequeue.addLast(String.valueOf(i));
            assertEquals(String.valueOf(i), mDequeue.removeFirst());
        }

        mDequeue = new ASDL2021Deque<String>();

        for (int i = 0; i < MAX_PROBLEM_SIZE; i++) {
            mDequeue.addLast(String.valueOf(i));
        }

        for (int i = 0; i < MAX_PROBLEM_SIZE; i++) {
            assertEquals(String.valueOf(i), mDequeue.removeFirst());
        }

    }

    @Test
    public void testRemoveLast() {
        for (int i = 0; i < MAX_PROBLEM_SIZE; i++) {
            mDequeue.addFirst(String.valueOf(i));
            assertEquals(String.valueOf(i), mDequeue.removeLast());
        }

        mDequeue = new ASDL2021Deque<String>();

        for (int i = 0; i < MAX_PROBLEM_SIZE; i++) {
            mDequeue.addLast(String.valueOf(i));
            assertEquals(String.valueOf(i), mDequeue.removeLast());
        }

        mDequeue = new ASDL2021Deque<String>();

        for (int i = 0; i < MAX_PROBLEM_SIZE; i++) {
            mDequeue.addFirst(String.valueOf(i));
        }

        for (int i = 0; i < MAX_PROBLEM_SIZE; i++) {
            assertEquals(String.valueOf(i), mDequeue.removeLast());
        }
    }

    @Test
    public void testRemoveEmpty() {
        try {
            assertTrue(mDequeue.isEmpty());
            mDequeue.removeFirst();
            fail("Expected a NoSuchElementException");
        } catch (NoSuchElementException nsee) {
            // Continue
        } catch (Exception e) {
            fail("Unexpected exception : " + e);
        }

        try {
            assertTrue(mDequeue.isEmpty());
            mDequeue.removeLast();
            fail("Expected a NoSuchElementException");
        } catch (NoSuchElementException nsee) {
            // Continue
        } catch (Exception e) {
            fail("Unexpected exception : " + e);
        }

        try {
            assertTrue(mDequeue.isEmpty());

            for (int i = 0; i < MAX_PROBLEM_SIZE; i++) {
                mDequeue.addLast(String.valueOf(i));
            }
            for (int i = 0; i < MAX_PROBLEM_SIZE; i++) {
                mDequeue.removeLast();
            }
            mDequeue.removeLast();
            fail("Expected a NoSuchElementException");
        } catch (NoSuchElementException nsee) {
            // Continue
        } catch (Exception e) {
            fail("Unexpected exception : " + e);
        }
    }

    @Test
    public void testRemoveWithParameter() {
        mDequeue.clear();
        assertTrue(mDequeue.isEmpty());

        mDequeue.add("alula");
        //testing with only one element in the queue
        assertTrue(mDequeue.remove("alula"));

        for (int i = 0; i < MAX_PROBLEM_SIZE; i++) {
            mDequeue.addLast(String.valueOf(i));
        }

        //testing removing tail;
        assertTrue(mDequeue.remove(String.valueOf(MAX_PROBLEM_SIZE - 1)));

        //testing removing head;
        assertTrue(mDequeue.remove(String.valueOf(0)));

        //testing clearing the list with remove(Object o);
        for (int i = 1; i < MAX_PROBLEM_SIZE - 1; i++) {
            mDequeue.remove(String.valueOf(i));
        }
    }

    @Test
    public void testIterator() {

        Iterator<String> anIterator = mDequeue.iterator();
        assertFalse(anIterator.hasNext());

        for (int i = 0; i < MAX_PROBLEM_SIZE; i++) {
            mDequeue.addFirst(String.valueOf(i));
        }

        anIterator = mDequeue.iterator();

        assertTrue(anIterator.hasNext());

        int i = MAX_PROBLEM_SIZE - 1;
        for (String aString : mDequeue) {
            assertEquals(String.valueOf(i), aString);
            i--;
        }

        anIterator = mDequeue.iterator();

        assertTrue(anIterator.hasNext());

        int j = MAX_PROBLEM_SIZE - 1;
        while (anIterator.hasNext()) {
            assertEquals(String.valueOf(j), anIterator.next());
            j--;
        }
    }

    @Test
    public void testIteratorNoMoreItem() {
        Iterator<String> anIterator = mDequeue.iterator();
        while (anIterator.hasNext()) {
            anIterator.next();
        }
        try {
            anIterator.next();
            fail("Should have thrown a NoSuchElementException.");
        } catch (NoSuchElementException nsee) {
            // Continue
        } catch (Exception e) {
            fail("Should have thrown a NoSuchElementException, but received" +
                    " : " + e);
        }
    }

    @Test
    public void testIteratorRemoveNotSupported() {
        Iterator<String> anIterator = mDequeue.iterator();
        try {
            anIterator.remove();
            fail("Should have thrown an UnsupportedOperationException");
        } catch (UnsupportedOperationException uoe) {
            // Continue
        } catch (Exception e) {
            fail("Unexpected exception : " + e);
        }
    }

    @Test
    public void testMultipleIterator() {
        for (int i = 0; i < MAX_PROBLEM_SIZE / 1000; i++) {

            mDequeue = new ASDL2021Deque<String>();
            for (int j = 0; j < i; j++) {
                mDequeue.addLast(String.valueOf(j));
            }

            @SuppressWarnings("rawtypes")
            Iterator[] someIterators = {
                    mDequeue.iterator(),
                    mDequeue.iterator(),
                    mDequeue.iterator(),
                    mDequeue.iterator(),
                    mDequeue.iterator(),
                    mDequeue.iterator()
            };

            @SuppressWarnings("unchecked")
            Iterator<String>[] manyStringIterators =
                    (Iterator<String>[]) someIterators;

            for (int iterID = 0; iterID < manyStringIterators.length; iterID++) {
                int index = 0;
                while (manyStringIterators[iterID].hasNext()) {
                    assertEquals(String.valueOf(index), manyStringIterators[iterID].next(), "Iterator #" + iterID + " failed:\n");
                    index++;
                }
            }

        }
    }

    @Test
    public void testIteratorFailSafety() {
        for (int i = 0; i < MAX_PROBLEM_SIZE; i++) {
            mDequeue.addFirst(String.valueOf(i));
        }
        //remove
        final Iterator testingRemoveIterator = mDequeue.iterator();
        assertDoesNotThrow(() -> testingRemoveIterator.next());
        mDequeue.removeFirst();
        assertThrows(ConcurrentModificationException.class, () -> testingRemoveIterator.next());

        final Iterator testingSecondRemoveIterator = mDequeue.iterator();
        assertDoesNotThrow(() -> testingSecondRemoveIterator.next());
        mDequeue.remove("0");
        assertThrows(ConcurrentModificationException.class, () -> testingSecondRemoveIterator.next());

        //add
        final Iterator testingAddIterator = mDequeue.iterator();
        assertDoesNotThrow(() -> testingAddIterator.next());
        mDequeue.add("alula");
        assertThrows(ConcurrentModificationException.class, () -> testingAddIterator.next());

        //poll
        final Iterator testingpollIterator = mDequeue.iterator();
        assertDoesNotThrow(() -> testingpollIterator.next());
        mDequeue.poll();
        assertThrows(ConcurrentModificationException.class, () -> testingpollIterator.next());

        //offer
        final Iterator testingofferIterator = mDequeue.iterator();
        assertDoesNotThrow(() -> testingofferIterator.next());
        mDequeue.offer("caracalla");
        assertThrows(ConcurrentModificationException.class, () -> testingofferIterator.next());

        //push
        final Iterator testingpushIterator = mDequeue.iterator();
        assertDoesNotThrow(() -> testingpushIterator.next());
        mDequeue.offer("caracalla");
        assertThrows(ConcurrentModificationException.class, () -> testingpushIterator.next());

        //pop
        final Iterator testingpopIterator = mDequeue.iterator();
        assertDoesNotThrow(() -> testingpopIterator.next());
        mDequeue.pop();
        assertThrows(ConcurrentModificationException.class, () -> testingpopIterator.next());
    }

    @Test
    public void testQueueBehavior() {

        String[] aBunchOfString = {
                "One", "Two", "Three", "Four"
        };

        for (String aString : aBunchOfString) {
            mDequeue.addFirst(aString);
        }

        for (String aString : aBunchOfString) {
            assertEquals(aString, mDequeue.removeLast());
        }
    }

    @Test
    public void testStackBehavior() {

        String[] aBunchOfString = {
                "One", "Two", "Three", "Four"
        };

        for (String aString : aBunchOfString) {
            mDequeue.addFirst(aString);
        }

        for (int i = aBunchOfString.length - 1; i >= 0; i--) {
            assertEquals(aBunchOfString[i], mDequeue.removeFirst());
        }
    }
}