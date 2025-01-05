package utils;

public class StringArrayList {
    private String[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public StringArrayList() {
        data = new String[DEFAULT_CAPACITY];
        size = 0;
    }

    public void add(String element) {
        if (size == data.length) {
            resize();
        }
        data[size++] = element;
    }

    public String get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return data[index];
    }

    public int size() {
        return size;
    }

    public boolean contains(String element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    public void removeAll(String element) {
        int newSize = 0;
        for (int i = 0; i < size; i++) {
            if (!data[i].equals(element)) {
                data[newSize++] = data[i];
            }
        }
        size = newSize;
    }

    private void resize() {
        String[] newData = new String[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}