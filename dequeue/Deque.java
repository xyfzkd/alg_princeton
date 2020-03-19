import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int n;


    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> front;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        if (size() == 0) last = first;
        else {
            oldfirst.front = first;
            first.front = null;
        }
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            last.front = null;
            first = last;
        } else {
            oldlast.next = last;
            last.front = oldlast;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        Item item = first.item;

        Node<Item> p = first.next;
        first.next = null;
        first = p;
        p = null;  // faster than first.next.front = null?

        n--;
        if (isEmpty()) last = null;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        Item item = last.item;
        Node<Item> p = last;
        last = last.front;
        // delete memory
        p.front = null;
        p = null;

        if (n >= 3) {
            last.front.next = last;
            last.next = null;
        } else if (n == 2) last.next = null;
        else first = null;
        n--;
        //if (isEmpty()) first = null;   // to avoid loitering
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
//        while (!StdIn.isEmpty()) {
//            String item = StdIn.readString();
//            if (!item.equals("-"))
//                deque.addFirst(item);
//            else if (!deque.isEmpty())
//                StdOut.print(deque.removeLast() + " ");
//        }
//        StdOut.println("(" + deque.size() + " left on deque)");
        deque.addLast(1);
        deque.removeLast();
        deque.addLast(3);
        deque.addFirst(4);
        deque.removeFirst();
        deque.addFirst(6);
        deque.addLast(7);
        deque.removeLast();

        for (int s : deque) {
            System.out.println(s);
        }
        System.out.println(deque.isEmpty());
    }

}
