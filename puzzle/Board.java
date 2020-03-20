import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;

public class Board {

    private int[][] board;
    private int[][] goal;
    private int blankx, blanky;
    private int n;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        int i, j;
        board = tiles;
        n = tiles.length;
        goal = new int[n][n];
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                goal[i][j] = i * n + j + 1;
                if (board[i][j] == 0) {
                    blankx = i;
                    blanky = j;
                }
            }
        }
        goal[n - 1][n - 1] = 0;
    }

    // string representation of this board
    public String toString() {
        String str = "\n";
        for (int[] a : board) {
            for (int b : a) {
                str = str + b + '\t';
            }
            str += '\n';
        }
        return n + str;

    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int dis = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                if (goal[i][j] != board[i][j]) dis++;
        }
        if (board[n - 1][n - 1] == 0) return dis; // board last item blank
        return dis - 1;
    }

    //
    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int dis = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                if (board[i][i] != 0)
                    dis += Math.abs(board[i][j] / 3 + board[i][j] % 3 - i - j - 1);
        }
        return dis;
    }


    // is this board the goal board?
    public boolean isGoal() {
        return equals(this.goal);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        return this.board.equals(y);
    }

//    private class BoardQueue extends Queue<Board> {
//        Queue<Board> queue = new Queue<Board>();
//    }

    private int[][] exch(int[][] a, char type) {
        if (type == 'w') { //upper move
            a[blankx][blanky] = a[blankx + 1][blanky];
            a[blankx + 1][blanky] = 0;
        }
        if (type == 'a') {
            a[blankx][blanky] = a[blankx][blanky + 1];
            a[blankx][blanky + 1] = 0;
        }
        if (type == 's') {
            a[blankx][blanky] = a[blankx - 1][blanky];
            a[blankx - 1][blanky] = 0;
        }
        if (type == 'd') {
            a[blankx][blanky] = a[blankx][blanky - 1];
            a[blankx][blanky - 1] = 0;
        }
        return a;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        int[][] n1 = board.clone(), n2 = n1.clone(), n3 = board.clone(), n4 = board.clone(); //wasd

        if (blankx == 0) {

            exch(n1, 'w');
            if (blanky == 0) {
                exch(n2, 'a');
            } else if (blanky == n - 1) {
                exch(n4, 'd');
            } else {
                exch(n2, 'a');
                exch(n4, 'd');
            }
        } else if (blankx == n - 1) {

            exch(n3, 's');
            if (blanky == 0) {
                exch(n2, 'a');
            } else if (blanky == n - 1) {
                exch(n4, 'd');
            } else {
                exch(n2, 'a');
                exch(n4, 'd');
            }
        } else {
            for (int i = 0; i < n1.length; i++)
                System.out.println(Arrays.toString(n1[i]));
            exch(n1, 'w');
            for (int i = 0; i < n1.length; i++)
                System.out.println(Arrays.toString(n1[i]));
            exch(n3, 's');
            for (int i = 0; i < n1.length; i++)
                System.out.println(Arrays.toString(n1[i]));
            if (blanky == 0) {
                exch(n2, 'a');
            } else if (blanky == n - 1) {
                exch(n4, 'd');
            } else {

                exch(n2, 'a');
                exch(n4, 'd');
            }
        }
        Queue<Board> neighb = new Queue<Board>();
        if (!equals(n1)) neighb.enqueue(new Board(n1));
        if (!equals(n2)) neighb.enqueue(new Board(n2));
        if (!equals(n3)) neighb.enqueue(new Board(n3));
        if (!equals(n4)) neighb.enqueue(new Board(n4));

        return neighb;
    }


//
//    // a board that is obtained by exchanging any pair of tiles
//    public Board twin()

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] magicSquare =
                {
                        {8, 1, 3},
                        {4, 0, 2},
                        {7, 6, 5}
                };
        Board board = new Board(magicSquare);
        System.out.println(board.toString());
//        System.out.println(board.hamming());
//        System.out.println(board.manhattan());
        board.neighbors();

    }

}
