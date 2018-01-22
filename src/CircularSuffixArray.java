import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class CircularSuffixArray {
    
    private final int length;
    private final int[] index;
    
    public CircularSuffixArray(String s) {
        if (s != null) {
            int len = s.length();
            int[] ind = new int[len];
            for (int i = 0; i < len; ++i)
                ind[i] = i;
            StdRandom.shuffle(ind);
            sort(ind, 0, len - 1, 0, s, len);
            length = len;
            index = ind;
        } else throw new IllegalArgumentException();
    }
    
    private static void sort(int[] a, int lo, int hi, int d, String s, int n) {
        if (lo < hi) {
            int lt = lo, gt = hi;
            int v = s.charAt((a[lo] + d) % n);
            int i = lo + 1;
            while (i <= gt) {
                int t = s.charAt((a[i] + d) % n);
                if (t < v)
                    exch(a, lt++, i++);
                else if (t > v)
                    exch(a, i, gt--);
                else ++i;
            }
            sort(a, lo, lt - 1, d, s, n);
            if (v >= 0)
                sort (a, lt, gt, d + 1, s, n);
            sort(a, gt + 1, hi, d, s, n);
        }
    }
    
    private static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    public int length() {
        return length;
    }
    
    public int index(int i) {
        if (i >= 0 && i < length)
            return index[i];
        throw new IllegalArgumentException();
    }
    
    public static void main(String[] args) {
        String s = "ABRACADABRA!";
        StdOut.println("s: " + s);
        CircularSuffixArray csa = new CircularSuffixArray(s);
        int len = csa.length();
        StdOut.println("len: " + len);
        int[] index = new int[len];
        for (int i = 0; i < len; ++i)
            index[i] = csa.index(i);
        StdOut.println("index: " + Arrays.toString(index));
        profile();
    }
    
    private static void profile() {
    }

}
