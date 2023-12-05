package practicals.lab9;

import java.util.Iterator;

/**
 * An abstract implementation of the {@code Map} interface, providing default
 * implementations for some methods and support for key and value iteration.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {

    /**
     * Returns {@code true} if this map contains no key-value mappings.
     *
     * @return {@code true} if this map contains no key-value mappings
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns an iterable collection of the keys contained in this map.
     *
     * @return an iterable collection of the keys contained in this map
     */
    public Iterable<K> keySet() {
        return new Keylterable();
    }

    /**
     * Returns an iterable collection of the values contained in this map.
     *
     * @return an iterable collection of the values contained in this map
     */
    public Iterable<V> values() {
        return new Valuelterable();
    }

    /**
     * An entry in the map, consisting of a key-value pair.
     *
     * @param <K> the type of keys
     * @param <V> the type of values
     */
    protected static class MapEntry<K, V> implements Entry<K, V> {
        private K k;
        private V v;

        public MapEntry(K key, V value) {
            k = key;
            v = value;
        }

        public K getKey() {
            return k;
        }

        protected void setKey(K key) {
            this.k = key;
        }

        public V getValue() {
            return v;
        }

        protected V setValue(V value) {
            this.v = value;
            return v;
        }
    }

    private class Keylterator implements Iterator<K> {
        private final Iterator<Entry<K, V>> entries = entrySet().iterator();

        public boolean hasNext() {
            return entries.hasNext();
        }

        public K next() {
            return entries.next().getKey();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Keylterable implements Iterable<K> {
        public Iterator<K> iterator() {
            return new Keylterator();
        }
    }

    private class Valuelterator implements Iterator<V> {
        private final Iterator<Entry<K, V>> entries = entrySet().iterator();

        public boolean hasNext() {
            return entries.hasNext();
        }

        public V next() {
            return entries.next().getValue();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Valuelterable implements Iterable<V> {
        public Iterator<V> iterator() {
            return new Valuelterator();
        }
    }
}