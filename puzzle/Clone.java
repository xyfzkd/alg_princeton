public class Clone {
    int[] a = {2, 3, 4};
    int[] b = a.clone();
    int[] c = a.clone();

    public int[] exch(int[] a) {
        a[0] = 3;
        return a;
    }

    public static void main(String[] args) {
        Clone my = new Clone();
        my.exch(my.b);
        System.out.println(my.a[0]);
        System.out.println(my.b[0]);
        System.out.println(my.c[0]);
    }
}
