import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[] parent;
    private int[] size;
    private int count;
    private WeightedQuickUnionUF percolation;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 0) throw new IllegalArgumentException();
        WeightedQuickUnionUF percolation = new WeightedQuickUnionUF(n * n);

        Class clazz = percolation.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 设置允许访问私有变量
            field.setAccessible(true);
            try {
                // 打印父亲的属性信息
                System.out.println("通过反射获得的 " + field.get(father));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return true;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return true;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return 1;
    }


    // does the system percolate?
    public boolean percolates() {
        return true;
    }


    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(5);
        System.out.println();
    }
}
