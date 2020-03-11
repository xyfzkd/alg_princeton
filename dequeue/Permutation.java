import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            q.enqueue(item);
        }
        String item;

        Deque<String> d = new Deque<String>();
        for (int i = Integer.parseInt(args[0]); i != 0; i--) {
            item = q.dequeue();
            d.addLast(item);
        }
        for (String s : d) {
            System.out.println(s);
        }
    }
}
