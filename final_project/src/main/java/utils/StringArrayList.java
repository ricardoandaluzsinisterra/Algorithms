package utils;

public class StringArrayList {
    private String[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Constructs an empty StringArrayList with the default capacity.
     */
    public StringArrayList() {
        data = new String[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Adds the specified element to the end of the list.
     *
     * @param element the element to be added to the list
     */
    public void add(String element) {
        if (size == data.length) {
            resize();
        }
        data[size++] = element;
    }

    /**
     * Returns the element at the specified position in the list.
     *
     * @param index the index of the element to return
     * @return the element at the specified position in the list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public String get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return data[index];
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the number of elements in the list
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if this list contains the specified element.
     *
     * @param element the element whose presence in this list is to be tested
     * @return true if this list contains the specified element, false otherwise
     */
    public boolean contains(String element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes all occurrences of the specified element from this list.
     *
     * @param element the element to be removed from this list
     */
    public void removeAll(String element) {
        int newSize = 0;
        for (int i = 0; i < size; i++) {
            if (!data[i].equals(element)) {
                data[newSize++] = data[i];
            }
        }
        size = newSize;
    }

    /**
     * Resizes the internal array to accommodate more elements.
     */
    private void resize() {
        String[] newData = new String[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}