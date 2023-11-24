package practicals.lab8;

/**
 * An implementation of the {@link Entry} interface representing a key-value pair.
 *
 * @param <K> The type of the key.
 * @param <V> The type of the value.
 */
public class PQEntry<K, V> implements Entry<K, V> {

    private K k;
    private V v;

    /**
     * Constructs a new entry with the specified key and value.
     *
     * @param k The key of the entry.
     * @param v The value of the entry.
     */
    public PQEntry(K k, V v) {
        this.k = k;
        this.v = v;
    }

    public K getKey() {
        return k;
    }

    public void setKey(K k) {
        this.k = k;
    }

    public V getValue() {
        return v;
    }

    public void setValue(V v) {
        this.v = v;
    }
}
