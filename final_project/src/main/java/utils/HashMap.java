package utils;

import java.util.ArrayList;

public class HashMap<K, V> {
    protected ArrayList<Entry<K, V>>[] map = (ArrayList<Entry<K, V>>[]) new ArrayList[103];
    private int count = 0;
    private static final double LOAD_FACTOR = 0.75;
    private static final int EXPANSION_FACTOR = 3;

    /**
     * Constructs an empty HashMap.
     */
    public HashMap() {
    }

    /**
     * Checks if the map has capacity based on the load factor.
     *
     * @return true if the map has capacity, false otherwise
     */
    private boolean hasCapacity() {
        return count < map.length * LOAD_FACTOR;
    }

    /**
     * Returns the number of elements in the map.
     *
     * @return the number of elements in the map
     */
    public int size() {
        return count;
    }

    /**
     * Computes the hash code for the given key.
     *
     * @param key the key to hash
     * @return the hash code for the key
     * @throws IllegalArgumentException if the key is null
     */
    protected int hash(K key) {
        validateKey(key);
        return Math.abs(key.hashCode()) % map.length;
    }

    /**
     * Updates the map by expanding its capacity.
     */
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

    /**
     * Associates the specified value with the specified key in the map.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     * @throws IllegalArgumentException if the key is null
     */
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

    /**
     * Removes the mapping for the specified key from this map if present.
     *
     * @param key the key whose mapping is to be removed from the map
     * @return the values associated with the removed key, or null if the key was not found
     * @throws IllegalArgumentException if the key is null
     */
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

    /**
     * Returns true if this map contains a mapping for the specified key.
     *
     * @param key the key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key, false otherwise
     * @throws IllegalArgumentException if the key is null
     */
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

    /**
     * Returns the values to which the specified key is mapped, or null if this map contains no mapping for the key.
     *
     * @param key the key whose associated values are to be returned
     * @return the values to which the specified key is mapped, or null if this map contains no mapping for the key
     * @throws IllegalArgumentException if the key is null
     */
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

    /**
     * Returns a list of all keys in the map.
     *
     * @return a list of all keys in the map
     */
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

    /**
     * Returns a list of all values in the map.
     *
     * @return a list of all values in the map
     */
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

    /**
     * Displays the contents of the map.
     */
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

    /**
     * Represents an entry in the map.
     *
     * @param <K> the type of keys maintained by this map
     * @param <V> the type of mapped values
     */
    private static class Entry<K, V> {
        K key;
        ArrayList<V> values;

        /**
         * Constructs a new Entry with the specified key and value.
         *
         * @param key   the key of the entry
         * @param value the value of the entry
         */
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

    /**
     * Validates the specified key.
     *
     * @param key the key to validate
     * @throws IllegalArgumentException if the key is null
     */
    private void validateKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
    }
}