import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Arrays;

public class BruteCollinearPoints {
    private int n = 0;
    private LineSegment[] seg = new LineSegment[10];


    public BruteCollinearPoints(Point[] points) {
        int len = points.length;
        for (int p = 0; p < len; p++) {
            for (int q = p + 1; q < len; q++) {
                for (int r = q + 1; r < len; r++) {
                    for (int s = r + 1; s < len; s++) {
                        Point[] fp = new Point[4];
                        fp[0] = points[p];
                        fp[1] = points[q];
                        fp[2] = points[r];
                        fp[3] = points[s];
                        Arrays.sort(fp);
                        if (fp[0].slopeTo(fp[1]) == fp[0].slopeTo(fp[2]) && fp[0].slopeTo(fp[1]) == fp[0].slopeTo(fp[3])) {
                            segments(fp[0], fp[3]);
                        }
                    }
                }
            }
        }
        resize(n);
    }// finds all line segments containing 4 points

    public int numberOfSegments() {
        return n;
    }       // the number of line segments

    private void resize(int capacity) {
        assert capacity >= n;
        LineSegment[] copy = new LineSegment[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = seg[i];
        }
        seg = copy;
    }

    public LineSegment[] segments(Point p, Point q) {
        if (n == seg.length) resize(2 * seg.length);
        seg[n++] = new LineSegment(p, q);
        return seg;
    }               // the line segments

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenRadius(0.02);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }

        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment se : collinear.seg) {
            System.out.println(se.toString());
            se.draw();
        }
        StdDraw.show();
    }
}
