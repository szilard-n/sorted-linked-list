package test;

import main.SortedLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortedLinkedListTest {

    private final SortedLinkedList<Integer> intList = new SortedLinkedList<>();
    private final SortedLinkedList<String> stringList = new SortedLinkedList<>(Comparator.<String>reverseOrder());

    @BeforeEach
    void clear() {
        intList.clear();
        stringList.clear();
    }

    @Test
    void testAdd() {
        intList.add(3);
        intList.add(2);
        intList.add(1);

        assertEquals(3, intList.size());
        assertEquals(1, intList.get(0));
        assertEquals(2, intList.get(1));
        assertEquals(3, intList.get(2));

        stringList.add("a");
        stringList.add("c");
        stringList.add("b");

        assertEquals(3, stringList.size());
        assertEquals("c", stringList.get(0));
        assertEquals("b", stringList.get(1));
        assertEquals("a", stringList.get(2));
    }

    @Test
    void testAdd_illegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> stringList.add(null));
    }

    @Test
    void testRemove() {
        intList.add(3);
        intList.add(2);
        intList.add(1);

        assertTrue(intList.remove(2));
        assertEquals(2, intList.size());
        assertEquals(1, intList.get(0));
        assertEquals(3, intList.get(1));
        assertFalse(intList.remove(2));

        stringList.add("a");
        stringList.add("c");
        stringList.add("b");

        assertTrue(stringList.remove("a"));
        assertEquals(2, stringList.size());
        assertEquals("c", stringList.get(0));
        assertEquals("b", stringList.get(1));
    }

    @Test
    void testRemove_illegalArgument() {
        stringList.add("a");
        assertThrows(IllegalArgumentException.class, () -> stringList.remove(null));
    }

    @Test
    void testGet() {
        intList.add(3);
        intList.add(2);
        intList.add(1);

        assertEquals(1, intList.get(0));
        assertEquals(2, intList.get(1));
        assertEquals(3, intList.get(2));

        stringList.add("a");
        stringList.add("c");
        stringList.add("b");

        assertEquals("c", stringList.get(0));
        assertEquals("b", stringList.get(1));
        assertEquals("a", stringList.get(2));
    }

    @Test
    void testGet_indexOutOfBound() {
        stringList.add("a");
        stringList.add("b");

        assertThrows(IndexOutOfBoundsException.class, () -> stringList.get(3));
    }

    @Test
    void testClear() {
        intList.add(3);
        intList.add(2);
        intList.add(1);

        intList.clear();

        assertEquals(0, intList.size());
    }

    @Test
    void testSize() {
        assertEquals(0, intList.size());

        intList.add(3);
        assertEquals(1, intList.size());

        intList.add(2);
        assertEquals(2, intList.size());

        intList.add(1);
        assertEquals(3, intList.size());

        intList.clear();
        assertEquals(0, intList.size());
    }

    @Test
    void testIsEmpty() {
        assertTrue(intList.isEmpty());

        intList.add(3);
        assertFalse(intList.isEmpty());

        intList.clear();
        assertTrue(intList.isEmpty());
    }

    @Test
    void testContains() {
        intList.add(3);
        intList.add(2);
        intList.add(1);

        assertTrue(intList.contains(1));
        assertTrue(intList.contains(2));
        assertTrue(intList.contains(3));
        assertFalse(intList.contains(4));
    }

    @Test
    void testIndexOf() {
        intList.add(3);
        intList.add(2);
        intList.add(1);

        assertEquals(0, intList.indexOf(1));
        assertEquals(1, intList.indexOf(2));
        assertEquals(2, intList.indexOf(3));
        assertEquals(-1, intList.indexOf(4));
    }
}
