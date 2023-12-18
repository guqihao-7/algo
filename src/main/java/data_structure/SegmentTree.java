package data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SegmentTree {
    // 下标从 1 开始, 1 下标是根节点
    int[] tree;
    int[] tag;    // lazy tag
    // 这里的 a 是下标从 1 开始的, 注意要移位
    int[] a;

    public SegmentTree(int[] a) {
        this.a = a;
        this.tree = new int[a.length << 2];
        this.tag = new int[a.length << 2];
        build(1, 1, a.length - 1);
    }

    int lson(int p) {
        return p << 1; // p*2
    }

    int rson(int p) {
        return p << 1 | 1; // p * 2 + 1
    }

    void pushUp(int p) {
        tree[p] = tree[lson(p)] + tree[rson(p)]; // 区间和
        // tree[p] = Math.min(tree[lson(p)], tree[rson(p)]);  // 区间最小值
    }

    // 节点 p 代表的区间是 [pl, pr] 左闭右闭
    void build(int p, int pl, int pr) {
        if (pl == pr) {
            tree[p] = a[pl];
            return;
        }

        int mid = (pl + pr) >> 1;
        build(lson(p), pl, mid);
        build(rson(p), mid + 1, pr);
        pushUp(p);        // 从下向上传递值
    }

    // [l, R] 是待查询的区间, [pl, pr] 是 p 表示的区间
    // 都是左闭右闭
    int query(int L, int R, int p, int pl, int pr) {
        if (L <= pl && R >= pr) return tree[p];
        pushDown(p, pl, pr);        // 不能覆盖, tag 传给子树
        int mid = pl + pr >> 1, res = 0;
        if (L <= mid) res += query(L, R, lson(p), pl, mid);
        if (R > mid) res += query(L, R, rson(p), mid + 1, pr);
        return res;
    }        // 调用方式 query(L, R, 1, 1, n);

    void addTag(int p, int pl, int pr, int addition) {
        tag[p] += addition;
        tree[p] += addition * (pr - pl + 1);
    }

    void pushDown(int p, int pl, int pr) {        // 不能覆盖时, 把tag传给子树
        if (tag[p] != 0) {
            int mid = pl + pr >> 1;
            addTag(lson(p), pl, mid, tag[p]);
            addTag(rson(p), mid + 1, pr, tag[p]);
            tag[p] = 0;
        }
    }

    void update(int L, int R, int p, int pl, int pr, int addition) {
        if (L <= pl && R >= pr) {
            addTag(p, pl, pr, addition);
            return;
        }

        pushDown(p, pl, pr);
        int mid = pl + pr >> 1;
        if (L <= mid) update(L, R, lson(p), pl, mid, addition);
        if (R > mid) update(L, R, rson(p), mid + 1, pr, addition);
        pushUp(p);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        line = bufferedReader.readLine();
        String[] strings = line.split(" ");
        int n = Integer.parseInt(strings[0]), m = Integer.parseInt(strings[1]);
        line = bufferedReader.readLine();
        strings = line.split(" ");
        int[] a = new int[strings.length + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(strings[i - 1]);
        }
        SegmentTree segmentTree = new SegmentTree(a);
        for (int i = 0; i < m; i++) {
            line = bufferedReader.readLine();
            strings = line.split(" ");
            int L = Integer.parseInt(strings[1]);
            int R = Integer.parseInt(strings[2]);
            if (strings.length == 3) {
                System.out.println(segmentTree.query(L, R, 1, 1, n));
            } else if (strings.length == 4) {
                segmentTree.update(L, R, 1, 1, n, Integer.parseInt(strings[3]));
            }
        }
    }
}
