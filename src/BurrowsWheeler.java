import java.util.Arrays;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
    
    private static final int R = 256;
    private static final int CAP = 16;
    
    public static void transform() {
        char[] s = new char[CAP];
        int len = 0;
        while (!BinaryStdIn.isEmpty()) {
            if (len == s.length)
                s = Arrays.copyOf(s, s.length << 1);
            s[len++] = BinaryStdIn.readChar();
        }
        CircularSuffixArray csa;
        csa = new CircularSuffixArray(String.valueOf(s, 0, len));
        int first = 0;
        while (csa.index(first) != 0)
            ++first;
        BinaryStdOut.write(first);
        char last = s[len - 1];
        System.arraycopy(s, 0, s, 1, len - 1);
        s[0] = last;
        for (int i = 0; i < len; ++i)
            BinaryStdOut.write(s[csa.index(i)]);
        BinaryStdOut.flush();
    }
    
    public static void inverseTransform() {
        int first = BinaryStdIn.readInt();
        char[] t = new char[CAP];
        int len = 0;
        while (!BinaryStdIn.isEmpty()) {
            if (len == t.length)
                t = Arrays.copyOf(t, t.length << 1);
            t[len++] = BinaryStdIn.readChar();
        }
        char[] aux = new char[len];
        int[] next = new int[len];
        int[] count = new int[R + 1];
        for (int i = 0; i < len; ++i)
            ++count[t[i] + 1];
        for (int r = 0; r < R; ++r)
            count[r + 1] += count[r];
        for (int i = 0; i < len; ++i) {
            int j = count[t[i]]++;
            aux[j] = t[i];
            next[j] = i;
        }
        for (int i = 0; i < len; ++i) {
            BinaryStdOut.write(aux[first]);
            first = next[first];
        }
        BinaryStdOut.flush();
    }
    
    public static void main(String[] args) {
        if (args[0].equals("-"))
            transform();
        else inverseTransform();
    }

}
