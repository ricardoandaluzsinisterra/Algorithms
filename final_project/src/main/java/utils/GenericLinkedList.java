package utils;

import java.util.NoSuchElementException;

@SuppressWarnings("unused")
public class GenericLinkedList<T> {
    protected int count;
    protected Node<T> head;
    protected Node<T> tail;

    /**
     * Constructs an empty GenericLinkedList.
     */
    public GenericLinkedList() {
        this.head = null;
        this.tail = null;
        count = 0;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the number of elements in the list
     */
    public int size() {
        return count;
    }

    /**
     * Returns the element at the specified position in the list.
     *
     * @param pos the position of the element to return
     * @return the element at the specified position in the list
     * @throws IndexOutOfBoundsException if the position is out of range
     */
    public T get(int pos) {
        validatePosition(pos);

        Node<T> current = head;
        for (int i = 0; i < pos; i++) {
            current = current.next;
        }

        return current.data;
    }

    /**
     * Returns the index of the first occurrence of the specified element in the list,
     * or -1 if this list does not contain the element.
     *
     * @param value the element to search for
     * @return the index of the first occurrence of the specified element in the list, or -1 if this list does not contain the element
     */
    public int indexOf(T value) {
        Node<T> current = head;
        for (int i = 0; i < count; i++) {
            if (value.equals(current.data)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    /**
     * Appends the specified element to the end of the list.
     *
     * @param data the element to be appended to the list
     */
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        count++;
    }

    /**
     * Inserts the specified element at the specified position in the list.
     *
     * @param value the element to be inserted
     * @param pos   the position at which the element is to be inserted
     * @throws IndexOutOfBoundsException if the position is out of range
     */
    public void insert(T value, int pos) {
        if (pos < 0 || pos > count) {
            throw new IndexOutOfBoundsException("Illegal position supplied - position must be within bounds of list");
        }

        if (pos == count) {
            add(value);
        } else if (pos == 0) {
            addToStart(value);
        } else {
            Node<T> newNode = new Node<>(value);

            Node<T> current = head;
            Node<T> prev = null;
            for (int i = 0; i < pos; i++) {
                prev = current;
                current = current.next;
            }
            newNode.next = current;
            prev.next = newNode;

            count++;
        }
    }

    /**
     * Inserts the specified element at the beginning of the list.
     *
     * @param value the element to be inserted
     */
    public void addToStart(T value) {
        Node<T> newNode = new Node<>(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        count++;
    }

    /**
     * Removes the element at the specified position in the list.
     *
     * @param pos the position of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the position is out of range
     */
    public T remove(int pos) {
        validatePosition(pos);

        if (pos == 0) {
            return removeFirst();
        } else if (pos == count - 1) {
            return removeLast();
        } else {
            Node<T> current = head;
            Node<T> prev = null;

            for (int i = 0; i < pos; i++) {
                prev = current;
                current = current.next;
            }
            T deleted = current.data;
            prev.next = current.next;
            count--;
            return deleted;
        }
    }

    /**
     * Validates the specified position.
     *
     * @param pos the position to validate
     * @throws IndexOutOfBoundsException if the position is out of range
     */
    private void validatePosition(int pos) {
        if (pos < 0 || pos >= count) {
            throw new IndexOutOfBoundsException("Position must be within the boundaries of the list");
        }
    }

    /**
     * Removes and returns the first element from the list.
     *
     * @return the first element from the list
     * @throws NoSuchElementException if the list is empty
     */
    public T removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("Cannot delete from an empty list");
        }

        T deleted = head.data;

        head = head.next;
        if (head == null) {
            tail = null;
        }
        count--;

        return deleted;
    }

    /**
     * Removes and returns the last element from the list.
     *
     * @return the last element from the list
     * @throws NoSuchElementException if the list is empty
     */
    public T removeLast() {
        if (head == null) {
            throw new NoSuchElementException("Cannot delete from an empty list");
        }

        T deleted = null;
        if (head == tail) {
            deleted = head.data;
            head = null;
            tail = null;
        } else {
            Node<T> current = head;
            while (current.next != tail) {
                current = current.next;
            }
            current.next = null;
            deleted = tail.data;
            tail = current;
        }
        count--;

        return deleted;
    }

    /**
     * Represents a node in the linked list.
     *
     * @param <T> the type of the element stored in the node
     */
    protected static class Node<T> {
        T data;
        Node<T> next;

        /**
         * Constructs a new Node with the specified data.
         *
         * @param data the data to be stored in the node
         */
        public Node(T data) {
            this.data = data;
            next = null;
        }
    }
}