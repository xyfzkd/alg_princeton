import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;

public class FastCollinearPoints {
    public int n = 0;
    private LineSegment[] seg = new LineSegment[10];

    public static <T> T[] merge(T[] a, T[] b) {
        T[] merge = Arrays.copyOf(a, a.length + b.length);
        System.arraycopy(b, 0, merge, a.length, b.length);
        return merge;
    }

    public void fastCollinearPoints(Point[] points) {
        int point_num = points.length;
        int i = 1;
        int currCount = 1;
        WeightedQuickUnionUF pointuf = new WeightedQuickUnionUF(point_num);
        //Point[] points_copy = points.clone();


        for (Point point : points) {
            currCount = 1;

            Arrays.sort(points, point.slopeOrder());
            //for (Point p : points) System.out.println(p.toString());
            //if (pointuf.find(j) != j) continue;
            Point before_point = points[0]; //slope would be -infinity
            for (i = 1; i < point_num; i++) {

                if (point.slopeTo(points[i]) == point.slopeTo(before_point)) {
                    currCount++;

                } else if (currCount != 1) {
                    break; //here maybe wrong when identity points exist but collinear points also exist
                }
                before_point = points[i];
            }
            if (currCount >= 3) {


                Point[] slopeequalpoint = Arrays.copyOfRange(points, i - currCount, i);
                Point[] thispoint = new Point[1]; // add this point and sort because they are colinear
                thispoint[0] = point;
                slopeequalpoint = merge(slopeequalpoint, thispoint);
                Arrays.sort(slopeequalpoint);

                segments(slopeequalpoint[0], slopeequalpoint[currCount]);
                break; // when find the colinear points, this point finished
                // three points collinear ignored
            }
            // when this point has colinear points, set up doing smaller set searching
        }
        if (currCount >= 3) {
            Point[] a = Arrays.copyOfRange(points, 1, i - currCount);

            Point[] b = Arrays.copyOfRange(points, i, point_num);
            Point[] merge = merge(a, b);
            //System.out.println();
            //for (Point m : merge) System.out.println(m.toString());
            if (merge.length != 0) fastCollinearPoints(merge); //
        }

    }

    public FastCollinearPoints(Point[] points) { // do not recursively initial constructor for n intial at the same time
        fastCollinearPoints(points);
        resize(n);
    }

    // finds all line segments containing 4 or more points

    public int numberOfSegments() {
        return n;
    }        // the number of line segments

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
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        //for (Point p : points) System.out.println(p.toString());
        FastCollinearPoints fcp = new FastCollinearPoints(points);
        for (LineSegment seg : fcp.seg) System.out.println(seg.toString());
    }
}
