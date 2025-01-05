package utils;

import java.util.ArrayList;

public class HashMap<K, V> {
    protected ArrayList<Entry<K, V>>[] map = (ArrayList<Entry<K, V>>[]) new ArrayList[103];
    private int count = 0;
    private static final double LOAD_FACTOR = 0.75;
    private static final int EXPANSION_FACTOR = 3;

    public HashMap() {
    }

    private boolean hasCapacity() {
        return count < map.length * LOAD_FACTOR;
    }

    public int size() {
        return count;
    }

    protected int hash(K key) {
        validateKey(key);
        return Math.abs(key.hashCode()) % map.length;
    }

    private void updateMap() {
        ArrayList<Entry<K, V>>[] oldMap = map;
        map = (ArrayList<Entry<K, V>>[]) new ArrayList[oldMap.length * EXPANSION_FACTOR];
        count = 0;

        for (ArrayList<Entry<K, V>> bucket : oldMap) {
            if (bucket == null) {
                continue;
            }

            for (Entry<K, V> entry : bucket) {
                for (V value : entry.values) {
                    put(entry.key, value);
                }
            }
        }
    }

    public void put(K key, V value) {
        validateKey(key);
        if (!hasCapacity()) {
            updateMap();
        }
        int pos = hash(key);

        if (map[pos] == null) {
            map[pos] = new ArrayList<>();
        }

        for (Entry<K, V> entry : map[pos]) {
            if (entry.key.equals(key)) {
                entry.values.add(value);
                return;
            }
        }
        map[pos].add(new Entry<>(key, value));
        count++;
    }

    public ArrayList<V> remove(K key) {
        validateKey(key);
        int pos = hash(key);
        if (map[pos] == null) {
            return null;
        }

        for (int i = 0; i < map[pos].size(); i++) {
            Entry<K, V> entry = map[pos].get(i);
            if (entry.key.equals(key)) {
                map[pos].remove(i);
                count--;
                return entry.values;
            }
        }
        return null;
    }

    public boolean containsKey(K key) {
        validateKey(key);
        int pos = hash(key);
        if (map[pos] == null) {
            return false;
        }

        for (Entry<K, V> entry : map[pos]) {
            if (entry.key.equals(key)) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<V> get(K key) {
        validateKey(key);
        int pos = hash(key);
        if (map[pos] == null) {
            return null;
        }

        for (Entry<K, V> entry : map[pos]) {
            if (entry.key.equals(key)) {
                return entry.values;
            }
        }
        return null;
    }

    public ArrayList<K> getKeys() {
        ArrayList<K> keys = new ArrayList<>();
        for (ArrayList<Entry<K, V>> bucket : map) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    keys.add(entry.key);
                }
            }
        }
        return keys;
    }

    public ArrayList<V> getValues() {
        ArrayList<V> allValues = new ArrayList<>();
        for (ArrayList<Entry<K, V>> bucket : map) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    allValues.addAll(entry.values);
                }
            }
        }
        return allValues;
    }

    public void display() {
        for (int i = 0; i < map.length; i++) {
            if (map[i] == null) {
                System.out.println("Slot " + i + ") Null");
                continue;
            }

            System.out.println("Slot " + i + ")");
            for (Entry<K, V> entry : map[i]) {
                System.out.println("  " + entry);
            }
        }
    }

    private static class Entry<K, V> {
        K key;
        ArrayList<V> values;

        public Entry(K key, V value) {
            this.key = key;
            this.values = new ArrayList<>();
            this.values.add(value);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Entry<?, ?> entry = (Entry<?, ?>) o;
            return key.equals(entry.key);
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", values=" + values +
                    '}';
        }
    }

    private void validateKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
    }
}
