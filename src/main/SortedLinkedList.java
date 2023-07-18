package main;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents a sorted linked list of elements of type T that extends Comparable.
 * Elements in this list are ordered according to their natural ordering, or by a Comparator
 * provided at the list creation time.
 *
 * @param <T> The type of elements in this list.
 */
public class SortedLinkedList<T extends Comparable<T>> implements Iterable<T> {

    private Node<T> head;
    private int size;
    private final Comparator<? super T> comparator;

    /**
     * Inner class to encapsulate a node of the linked list.
     *
     * @param <T> The type of elements in the node.
     */
    private static class Node<T> {
        T data;
        Node<T> next;

        /**
         * Constructs a Node.
         *
         * @param data The data to be stored in the node.
         * @param next The next node in the list.
         */
        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    /**
     * Constructs a main.SortedLinkedList with the given comparator.
     *
     * @param comparator Comparator to determine the order of the list.
     */
    public SortedLinkedList(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    /**
     * Constructs a main.SortedLinkedList with natural ordering.
     */
    public SortedLinkedList() {
        this(null);
    }

    /**
     * Returns the size of the list.
     *
     * @return Number of elements in the list.
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks if the list contains a specific element.
     *
     * @param value object to be checked for containment in this list.
     * @return true if the list contains the specified element, false otherwise.
     */
    public boolean contains(T value) {
        return indexOf(value) >= 0;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list.
     *
     * @param value Element to search for.
     * @return The index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
     */
    public int indexOf(T value) {
        Node<T> current = head;
        for (int i = 0; i < size; ++i) {
            if (value.equals(current.data)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    /**
     * Adds a new element to the list.
     *
     * @param value The element to be added.
     */
    public void add(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Null value not allowed");
        }

        if (head == null || compare(head.data, value) > 0) {
            head = new Node<>(value, head);
        } else {
            Node<T> current = head;
            while (current.next != null && compare(current.next.data, value) < 0) {
                current = current.next;
            }
            current.next = new Node<>(value, current.next);
        }
        size++;
    }

    /**
     * Removes a specific element from the list.
     *
     * @param value Element to be removed.
     * @return true if the list contained the specified element, false otherwise.
     */
    public boolean remove(T value) {
        if (isEmpty()) {
            return false;
        }

        if (value == null) {
            throw new IllegalArgumentException("Null value not allowed");
        }

        // data to remove is in the head
        if (head.data.equals(value)) {
            head = head.next;
            return true;
        }

        // find data to be removed in the list
        Node<T> current = head;
        while (current.next != null && !current.next.data.equals(value)) {
            current = current.next;
        }

        // data wasn't found
        if (current.next == null) {
            return false;
        }

        // remove the node containing the data from the list
        current.next = current.next.next;
        size--;
        return true;
    }

    /**
     * Removes all elements from the list.
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index Index of the element to return.
     * @return The element at the specified position.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public T get(int index) {
        if (index > size - 1) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    /**
     * Returns a string representation of the list.
     */
    @Override
    public String toString() {
        StringBuilder stringList = new StringBuilder("[");
        Node<T> current = head;

        while (current != null) {
            stringList.append(current.data);

            if (current.next != null) {
                stringList.append(", ");
            }
            current = current.next;
        }

        stringList.append("]");
        return stringList.toString();
    }

    /**
     * Compares two elements using the comparator if it exists, otherwise uses their natural ordering.
     *
     * @param a The first element to compare.
     * @param b The second element to compare.
     * @return A negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
     */
    private int compare(T a, T b) {
        if (comparator != null) {
            return comparator.compare(a, b);
        } else {
            return a.compareTo(b);
        }
    }
}
