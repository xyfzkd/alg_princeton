import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int n;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        first = null;
        last = null;
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        n++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("RandomizedQueue underflow");
        int rand = StdRandom.uniform(n--);
        if (rand == 0) {
            Item item = first.item;
            first = first.next;
            if (isEmpty()) last = null;
            return item;
        } else {
            Node<Item> p = first;
            for (int i = 0; i < rand - 1; i++)
                p = p.next;
            Item item = p.next.item;
            if (p.next.next != null) {
                p.next = p.next.next;
            } else {
                p.next = null;
                last = p;
            }
            return item;
        }

    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("RandomizedQueue underflow");
        int rand = StdRandom.uniform(n);
        Node<Item> p = first;
        for (int i = 0; i < rand; i++)
            p = p.next;
        return p.item;
    }

    // return an independent iterator over items in random order
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
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            q.enqueue(item);
        }
        q.dequeue();
        for (String s : q) {
            System.out.println(s);
        }

        q.dequeue();
        for (String s : q) {
            System.out.println(s);
        }

        q.dequeue();
        for (String s : q) {
            System.out.println(s);
        }


    }

}
