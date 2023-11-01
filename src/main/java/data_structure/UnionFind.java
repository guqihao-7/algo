package data_structure;

/**
 * 非带权并查集, 解决图论中的连通性问题, 优化有
 *      1. 小树合并到大树, 减小平均高度
 *      2. 路径压缩
 *  由于路径压缩包含按秩合并, 因此本代码中没有按秩合并的优化
 */
public class UnionFind {
    int[] s;

    void init(int N) {
        s = new int[N];
        for (int i = 0; i < N; i++) s[i] = i;
    }

    /**
     * 查找元素所在的集, 即该集的根节点
     * @param x     待查找的元素
     * @return      该元素所在的集的根节点
     */
    int findSet(int x) {
        /*
        递归版本, 有可能爆栈:
            if (x != s[x])
                s[x] = findSet(s[x]); // 回溯的过程中做路径压缩
            return s[x];
        */

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

    /**
     * 如果 x 和 y 不在一个集合中
     * 则合并 x 和 y 所在的两个集合
     * 将 x 合并到 y 上, y 的根成为 x 的根
     * @param x     元素 x
     * @param y     元素 y
     */
    void mergeSet(int x, int y) {
        x = findSet(x);
        y = findSet(y);
        if (x != y)
            s[x] = s[y];
    }

    /**
     * @return 并查集中集合的数量, 即有几个非联通区
     */
    int count() {
        int ans = 0;
        for (int i = 0; i < s.length; i++)
            if (s[i] == i)
                ans++;
        return ans;
    }
}
