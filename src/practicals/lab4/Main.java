package practicals.lab4;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        // Creating a list.
        LinkedPositionalList<Integer> list = new LinkedPositionalList<>();
        Position<Integer> p = list.addFirst(10);
        p = list.addBefore(p, 20);
        list.addFirst(30);
        list.addBefore(p, 40);

        // Iterating the list.
        Iterator<Position<Integer>> itr = list.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next().getElement());
        }
    }
}
