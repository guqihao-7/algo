package hdu;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P1213 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        String line, strings[];
        while (T-- > 0) {
            line = br.readLine();
            strings = line.split(" ");
            int N = Integer.parseInt(strings[0]),
                    M = Integer.parseInt(strings[1]);
            UnionFind unionFind = new UnionFind();
            unionFind.init(N);
            while (M-- > 0) {
                line = br.readLine();
                strings = line.split(" ");
                unionFind.mergeSet(Integer.parseInt(strings[0]) - 1,
                        Integer.parseInt(strings[1]) - 1);
            }
            System.out.println(unionFind.count());
            br.readLine();
        }
    }

    static class UnionFind {
        int[] s;

        void init(int N) {
            s = new int[N];
            for (int i = 0; i < N; i++) s[i] = i;
        }

        int findSet(int x) {
            int root = x;
            while (s[root] != root) root = s[root];
            int i = x, j;
            while (i != root) {
                j = s[i];
                s[i] = root;
                i = j;
            }
            return root;
        }

        void mergeSet(int x, int y) {
            x = findSet(x);
            y = findSet(y);
            if (x != y)
                s[x] = s[y];
        }

        int count() {
            int ans = 0;
            for (int i = 0; i < s.length; i++)
                if (s[i] == i)
                    ans++;
            return ans;
        }
    }
}
