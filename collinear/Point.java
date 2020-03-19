import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class Point implements Comparable<Point> {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }                        // constructs the point (x, y)

    public void draw() {
        StdDraw.point(this.x, this.y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }                   // draws the line segment from this point to that point

    //
    public String toString() {
        return "(" + x + ", " + y + ")";
    }                          // string representation

    //
    public int compareTo(Point that) {
        if (this.y < that.y) return -1;
        else if (this.y == that.y && this.x < that.x) return -1;
        else return 1;
    }     // compare two points by y-coordinates, breaking ties by x-coordinates

    public double slopeTo(Point that) {
        if (this.y == that.y) {
            if (this.x != that.x) return +0.0;
            else return Double.NEGATIVE_INFINITY;
        } else {
            if (this.x == that.x) return Double.POSITIVE_INFINITY;
            else return (this.y - that.y) / (double) (this.x - that.x);
        }
    }       // the slope between this point and that point

    //
    public Comparator<Point> slopeOrder() {
        return new SlopeOrder();
    }

    private class SlopeOrder implements Comparator<Point> {
        public int compare(Point p, Point q) {
            double p_slope = slopeTo(p);
            double q_slope = slopeTo(q);
//            if (Double.isInfinite(p_slope) || Double.isInfinite(q_slope))
//                throw new IllegalArgumentException("The argument point must not be vertical to nor be the invoking point");
//            if (p_slope == 0 || q_slope == 0)
//                throw new IllegalArgumentException("The argument point must not be horizontal to the invoking point");

            if (p_slope < q_slope) return -1;
            if (p_slope == q_slope) return 0;
            return 1;
        }
    }


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
        StdDraw.show();

        Arrays.sort(points);
        for (Point point : points) {
            StdOut.println(point.toString());
        }
        StdOut.println((Double.NEGATIVE_INFINITY) > 0);


        Arrays.sort(points, points[0].slopeOrder());
        for (Point point : points) {
            StdOut.println(point.toString());
        }
        // print and draw the line segments
//        FastCollinearPoints collinear = new FastCollinearPoints(points);
//        for (LineSegment segment : collinear.segments()) {
//            StdOut.println(segment);
//            segment.draw();
//        }
//        StdDraw.show();
    }
}
