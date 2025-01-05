package utils;

import java.util.ArrayList;

public class HashMap<K, V> {
    protected java.util.ArrayList<Entry<K,V>>[] map = new java.util.ArrayList[103];
    private int count = 0;
    private static final double LOAD_FACTOR = 0.75;
    private static final int EXPANSION_FACTOR = 3;

    public HashMap(){
        
    }

    private boolean hasCapacity(){
        return count < map.length*LOAD_FACTOR;
    }

    public int size(){
        return count;
    }

    protected int hash(K key){
        validateKey(key);
        return Math.abs(key.hashCode()) % map.length;
    }

    private void updateMap() {
        java.util.ArrayList<Entry<K, V>>[] oldMap = map;
        map = new java.util.ArrayList[oldMap.length * EXPANSION_FACTOR];
        count = 0;
    
        for (int i = 0; i < oldMap.length; i++) {
            if (oldMap[i] == null) {
                continue;
            }
    
            for (int j = 0; j < oldMap[i].size(); j++) {
                Entry<K, V> currentEntry = oldMap[i].get(j);
                K key = currentEntry.key;
                for (V value : currentEntry.values) {
                    put(key, value);
                }
            }
        }
    }

    public V put (K key, V value){
        validateKey(key);
        if (!hasCapacity()){
            updateMap();
        }
        int pos = hash(key);
        if (map[pos] == null){
            map[pos] = new ArrayList<>();
        }

        Entry<K,V> newEntry = new Entry<>(key, value);
        int index = map[pos].indexOf(newEntry);
        if (index != -1) {
            Entry<K, V> toBeUpdated = map[pos].get(index);
            toBeUpdated.values.add(value);
            return value;
        }

        map[pos].add(newEntry);
        count++;
        return null;
    }

    public ArrayList<V> remove (K key){
        validateKey(key);
        int pos = hash(key);
        if (map[pos] == null) {
            return null;
        }

        int index = map[pos].indexOf(new Entry<K, V>(key, null));
        if (index == -1) {
            return null;
        }

        Entry<K, V> removedEntry = map[pos].remove(index);
        count--;
        return removedEntry.values;
    }

    public boolean containsKey(K key){
        validateKey(key);
        int pos = hash(key);
        return map[pos] != null;
    }

    public ArrayList<V> get(K key){
        validateKey(key);
        int pos = hash(key);
        if (map[pos] == null){
            return null;
        }
        int index = map[pos].indexOf(new Entry<K,V>(key, null));
        if (index == -1){
            return null;
        }
        Entry<K,V> match = map[pos].get(index);
        return match.values;
    }

    public ArrayList<K> getKeys(){
        ArrayList<K> keys = new ArrayList<>();
        
    }

    public void display(){
        for (int i = 0; i < map.length; i++) {
            if(map[i] == null) {
                System.out.println("Slot " + i + ") Null");
                continue;
            }

            System.out.println("Slot " + i + ")");
            for (int j = 0; j < map[i].size(); j++) {
                Entry<K, V> currentEntry = map[i].get(j);
                System.out.println("Entry " + j + " -> " + currentEntry);
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

    private void validateKey(K key){
        if (key == null){
            throw new IllegalArgumentException("Key cannot be null");
        }
    }
}
