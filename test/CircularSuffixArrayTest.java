import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class CircularSuffixArrayTest {
    
    private static final int SECONDS = 10;
    
    public static void main(String[] args) {
        In in = new In("in/aesop.txt");
        String s = in.readAll();
        int count = 0;
        long start = System.nanoTime();
        while (System.nanoTime() - start < SECONDS * (long) 1e9) {
            new CircularSuffixArray(s);
            ++count;
        }
        StdOut.println(count + " objects created in " + SECONDS + "s");
    }

}
