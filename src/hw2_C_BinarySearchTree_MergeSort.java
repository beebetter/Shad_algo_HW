import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Created by Artem Ogarkov at 16:31 on 16.11.2015.
 * artem.ogarkov@gmail.com
 */
    /*
    I know that naive solution is not working so
    I am going to test idea with using Inversion count
    Maybe I it will not work, so I take code from:
    http://algs4.cs.princeton.edu/22mergesort/Inversions.java
    */
public class hw2_C_BinarySearchTree_MergeSort implements Runnable {

    final static int MAX_SIZE = 300000;

    private BufferedReader reader;
    private PrintWriter out;
    private long count;
    private int curSize;
    private int value[]= new int [MAX_SIZE];
    public static class Inversions {

        // do not instantiate
        private Inversions() {
        }

        // merge and count
        private static long merge(int[] a, int[] aux, int lo, int mid, int hi) {
            long inversions = 0;

            // copy to aux[]
            for (int k = lo; k <= hi; k++) {
                aux[k] = a[k];
            }

            // merge back to a[]
            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++) {
                if (i > mid) a[k] = aux[j++];
                else if (j > hi) a[k] = aux[i++];
                else if (aux[j] < aux[i]) {
                    a[k] = aux[j++];
                    inversions += (mid - i + 1);
                } else a[k] = aux[i++];
            }
            return inversions;
        }

        // return the number of inversions in the subarray b[lo..hi]
        // side effect b[lo..hi] is rearranged in ascending order
        private static long count(int[] a, int[] b, int[] aux, int lo, int hi) {
            long inversions = 0;
            if (hi <= lo) return 0;
            int mid = lo + (hi - lo) / 2;
            inversions += count(a, b, aux, lo, mid);
            inversions += count(a, b, aux, mid + 1, hi);
            inversions += merge(b, aux, lo, mid, hi);
            assert inversions == brute(a, lo, hi);
            return inversions;
        }


        /**
         * Returns the number of inversions in the integer array.
         * The argument array is not modified.
         *
         * @param a the array
         * @return the number of inversions in the array. An inversion is a pair of
         * indicies <tt>i</tt> and <tt>j</tt> such that <tt>i &lt; j</tt>
         * and <tt>a[i]</tt> &gt; <tt>a[j]</tt>.
         */
        public static long count(int[] a) {
            int[] b = new int[a.length];
            int[] aux = new int[a.length];
            for (int i = 0; i < a.length; i++)
                b[i] = a[i];
            long inversions = count(a, b, aux, 0, a.length - 1);
            return inversions;
        }


        // merge and count (Comparable version)
        private static long merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
            long inversions = 0;

            // copy to aux[]
            for (int k = lo; k <= hi; k++) {
                aux[k] = a[k];
            }

            // merge back to a[]
            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++) {
                if (i > mid) a[k] = aux[j++];
                else if (j > hi) a[k] = aux[i++];
                else if (less(aux[j], aux[i])) {
                    a[k] = aux[j++];
                    inversions += (mid - i + 1);
                } else a[k] = aux[i++];
            }
            return inversions;
        }

        // return the number of inversions in the subarray b[lo..hi]
        // side effect b[lo..hi] is rearranged in ascending order
        private static long count(Comparable[] a, Comparable[] b, Comparable[] aux, int lo, int hi) {
            long inversions = 0;
            if (hi <= lo) return 0;
            int mid = lo + (hi - lo) / 2;
            inversions += count(a, b, aux, lo, mid);
            inversions += count(a, b, aux, mid + 1, hi);
            inversions += merge(b, aux, lo, mid, hi);
            assert inversions == brute(a, lo, hi);
            return inversions;
        }


        /**
         * Returns the number of inversions in the comparable array.
         * The argument array is not modified.
         *
         * @param a the array
         * @return the number of inversions in the array. An inversion is a pair of
         * indicies <tt>i</tt> and <tt>j</tt> such that <tt>i &lt; j</tt>
         * and <tt>a[i].compareTo(a[j]) &gt; 0</tt>.
         */
        public static long count(Comparable[] a) {
            Comparable[] b = new Comparable[a.length];
            Comparable[] aux = new Comparable[a.length];
            for (int i = 0; i < a.length; i++)
                b[i] = a[i];
            long inversions = count(a, b, aux, 0, a.length - 1);
            return inversions;
        }


        // is v < w ?
        private static boolean less(Comparable v, Comparable w) {
            return (v.compareTo(w) < 0);
        }

        // count number of inversions in a[lo..hi] via brute force (for debugging only)
        private static long brute(Comparable[] a, int lo, int hi) {
            long inversions = 0;
            for (int i = lo; i <= hi; i++)
                for (int j = i + 1; j <= hi; j++)
                    if (less(a[j], a[i])) inversions++;
            return inversions;
        }

        // count number of inversions in a[lo..hi] via brute force (for debugging only)
        private static long brute(int[] a, int lo, int hi) {
            long inversions = 0;
            for (int i = lo; i <= hi; i++)
                for (int j = i + 1; j <= hi; j++)
                    if (a[j] < a[i]) inversions++;
            return inversions;
        }

        /**
         * Reads in a sequence of integers from standard input and prints
         * the number of inversions.
         */
    }
    public static void main(String[] args) {
        new hw2_C_BinarySearchTree_MergeSort().run();
    }

    @Override
    public void run() {
        try{
            reader = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(new OutputStreamWriter(System.out));
            String[] tmp = reader.readLine().trim().split(" ");
            int n = Integer.parseInt(tmp[0]);
            int x;
            tmp = reader.readLine().trim().split(" ");
            for (int i = 0; i < tmp.length; i++) {
                value[i] = Integer.parseInt(tmp[i]);
            }
            long counter = Inversions.count(value);
            if (counter < n*(n-1)/2) {
                counter = n*(n-1)/2;
            }
            out.println(counter);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            out.close();
        }
    }
}
