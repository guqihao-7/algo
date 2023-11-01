package data_structure;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] argv) throws IOException {
        BPlusTree<Integer, Integer> tree = new BPlusTree<>();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for(int i = 0;i < n;i++) {
            int opt = in.nextInt();
            int x = in.nextInt();

            if (opt == 1) {
                tree.insert(x, x);
            } else if (opt == 2) {
                tree.pseudo_delete(x);
            } else if (opt == 3) {
                int rank = tree.rank(x);
                System.out.println(rank);
            } else if (opt == 4) {
                int val = tree.val_at(x);
                System.out.println(val);
            } else if (opt == 5) {
                int p = tree.prior(x);
                System.out.println(p);
            } else if (opt == 6) {
                int s = tree.successor(x);
                System.out.println(s);
            } else {
                throw new RuntimeException("error opt");
            }
        }


    }
}
