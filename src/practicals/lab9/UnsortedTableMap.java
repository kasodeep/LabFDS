package practicals.lab9;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An abstract implementation of the {@code Map} interface that uses an
 * unsorted array list as the underlying data structure for key-value mappings.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class UnsortedTableMap<K, V> extends AbstractMap<K, V> {

    /**
     * The underlying array list to store key-value mappings.
     */
    private final ArrayList<MapEntry<K, V>> table = new ArrayList<>();

    public UnsortedTableMap() {

    }

    /**
     * An abstract implementation of the {@code Map} interface that uses an
     * unsorted array list as the underlying data structure for key-value mappings.
     */
    private int findIndex(K key) {
        int n = table.size();
        for (int i = 0; i < n; i++) {
            if (table.get(i).getKey().equals(key))
                return i;
        }
        return -1;
    }

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map
     */
    public int size() {
        return table.size();
    }

    /**
     * Returns the value to which the specified key is mapped, or {@code null} if
     * this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or {@code null} if
     * this map contains no mapping for the key
     */
    public V get(K key) {
        int j = findIndex(key);
        if (j == -1)
            return null;
        return table.get(j).getValue();
    }

    /**
     * Associates the specified value with the specified key in this map. If the map
     * previously contained a mapping for the key, the old value is replaced by the
     * specified value.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     * @return the previous value associated with the key, or {@code null} if there
     * was no mapping for the key
     */
    public V put(K key, V value) {
        int j = findIndex(key);
        if (j == -1) {
            table.add(new MapEntry<>(key, value));
            return null;
        } else
            return table.get(j).setValue(value);
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     *
     * @param key the key whose mapping is to be removed from the map
     * @return the previous value associated with the key, or {@code null} if there
     * was no mapping for the key
     */
    public V remove(K key) {
        int j = findIndex(key);
        int n = size();
        if (j == -1)
            return null;
        V answer = table.get(j).getValue();

        if (j != n - 1)
            table.set(j, table.get(n - 1));
        table.remove(n - 1);

        return answer;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return new EntryIterable();
    }

    public class EntryIterator implements Iterator<Entry<K, V>> {
        private int j = 0;

        public boolean hasNext() {
            return (j < table.size());
        }

        public Entry<K, V> next() {
            if (j == table.size())
                throw new NoSuchElementException();
            return table.get(j++);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Iterable<Entry<K, V>> entrySet() {
            return new EntryIterable();
        }

    }

    private class EntryIterable implements Iterable<Entry<K, V>> {
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator();
        }
    }
}