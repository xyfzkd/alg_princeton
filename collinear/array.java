import java.util.Arrays;

public class array {
    private int[] a;

    public void array(int[] a, int[] b) {
        int[] merge = new int[a.length + b.length];

    }

    public static <T> T[] merge(T[] a, T[] b) {
        T[] merge = Arrays.copyOf(a, a.length + b.length);
        System.arraycopy(b, 0, merge, a.length, b.length);
        return merge;
    }

    public static void main(String[] args) {
        int[] a = {1, 2};
        int[] b = Arrays.copyOfRange(a, 1, 1);
        System.out.println(b.length == 0);
    }
}
