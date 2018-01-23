import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    
    private static final int R = 256;
    private static final int[] SEQUENCE = new int[R];
    
    public static void encode() {
        int[] seq = SEQUENCE;
        for (int i = 0; i < R; ++i)
            seq[i] = i;
        while (!BinaryStdIn.isEmpty()) {
            int c = BinaryStdIn.readChar();
            int i = 0;
            while (seq[i] != c)
                ++i;
            System.arraycopy(seq, 0, seq, 1, i);
            seq[0] = c;
            BinaryStdOut.write((char) i);
        }
        BinaryStdOut.flush();
    }
    
    public static void decode() {
        int[] seq = SEQUENCE;
        for (int i = 0; i < R; ++i)
            seq[i] = i;
        while (!BinaryStdIn.isEmpty()) {
            int i = BinaryStdIn.readChar();
            int c = seq[i];
            BinaryStdOut.write((char) c);
            System.arraycopy(seq, 0, seq, 1, i);
            seq[0] = c;
        }
        BinaryStdOut.flush();
    }
    
    public static void main(String[] args) {
        if (args[0].equals("-"))
            encode();
        else decode();
    }

}
