package practicals.lab8;

import java.util.Comparator;

/**
 * A default implementation of the {@link Comparator} interface that compares
 * elements of a generic type using their natural ordering, assuming they implement
 * the {@link Comparable} interface.
 *
 * @param <E> The type of elements to be compared.
 */
public class DefaultComparator<E> implements Comparator<E> {

    /**
     * Compares two elements for order. Returns a negative integer, zero,
     * or a positive integer as the first element is less than, equal to,
     * or greater than the second.
     *
     * @param a the first element to be compared.
     * @param b the second element to be compared.
     * @return a negative integer, zero, or a positive integer as the first
     * element is less than, equal to, or greater than the second.
     * @throws ClassCastException if the elements being compared do not implement the {@link Comparable} interface.
     */
    @SuppressWarnings("unchecked")
    public int compare(E a, E b) throws ClassCastException {
        return ((Comparable<E>) a).compareTo(b);
    }
}

