import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class CircularSuffixArray {
    
    private static final int CUTOFF = 15;
    
    private final int[] index;
    
    public CircularSuffixArray(String s) {
        if (s != null) {
            int len = s.length();
            char[] buf = new char[len << 1];
            s.getChars(0, len, buf, 0);
            System.arraycopy(buf, 0, buf, len, len);
            int[] ind = new int[len];
            for (int i = 0; i < len; ++i)
                ind[i] = i;
            sort(ind, 0, len - 1, 0, buf, len);
            index = ind;
        } else throw new IllegalArgumentException();
    }
    
    private static void sort(int[] a, int lo, int hi, int d, char[] buf, int n) {
        if (lo + CUTOFF < hi) {
            exch(a, lo, StdRandom.uniform(lo, hi + 1));
            int lt = lo, gt = hi;
            int v = buf[a[lo] + d];
            int i = lo + 1;
            while (i <= gt) {
                int cmp = buf[a[i] + d] - v;
                if (cmp < 0)
                    exch(a, lt++, i++);
                else if (cmp > 0)
                    exch(a, i, gt--);
                else ++i;
            }
            sort(a, lo, lt - 1, d, buf, n);
            if (d + 1 < n)
                sort(a, lt, gt, d + 1, buf, n);
            sort(a, gt + 1, hi, d, buf, n);
        } else insertion(a, lo, hi, d, buf, n);
    }
    
    private static void insertion(int[] a, int lo, int hi, int d, char[] buf, int n) {
        for (int i = lo + 1; i <= hi; ++i)
            for (int j = i; j > lo && less(a[j], a[j - 1], d, buf, n); --j)
                exch(a, j, j - 1);
    }
    
    private static boolean less(int v, int w, int d, char[] buf, int n) {
        for (int i = d; i < n; ++i) {
            int cmp = buf[v + i] - buf[w + i];
            if (cmp != 0)
                return cmp < 0;
        }
        return false;
    }
    
    private static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    public int length() {
        return index.length;
    }
    
    public int index(int i) {
        if (i >= 0 && i < index.length)
            return index[i];
        throw new IllegalArgumentException();
    }
    
    public static void main(String[] args) {
        StdOut.print("s: ");
        String s = StdIn.readLine();
        // String s = "ABRACADABRA!";
        // StdOut.println("s: " + s);
        CircularSuffixArray csa = new CircularSuffixArray(s);
        int len = csa.length();
        StdOut.println("len: " + len);
        int[] index = new int[len];
        for (int i = 0; i < len; ++i)
            index[i] = csa.index(i);
        StdOut.println("index: " + Arrays.toString(index));
    }

}
