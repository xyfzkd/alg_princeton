import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            q.enqueue(item);
        }
        String item;


        for (int i = Integer.parseInt(args[0]); i != 0; i--) {
            item = q.dequeue();
            System.out.println(item);
        }
    }
}
