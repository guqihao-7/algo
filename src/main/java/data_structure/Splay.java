package data_structure;

public class Splay {
    int[][] ch;
    int[] f, siz, val, cnt;
    int root, tot, len, x, N = 100005;

    public Splay() {
        init();
    }

    public Splay(int N) {
        this.N = N + 5;
        init();
    }

    void init() {
        root = 0;
        len = 1;
        ch = new int[N][2];
        f = new int[N];
        siz = new int[N];
        val = new int[N];
        cnt = new int[N];
    }

    void update(int a) {
        siz[a] = siz[ch[a][0]] + siz[ch[a][1]] + cnt[a];
    }

    void pushUp(int a) {
        while (a != 0) {
            update(a);
            a = f[a];
        }
    }

    void clear(int a) {
        f[a] = val[a] = siz[a] = cnt[a] = ch[a][0] = ch[a][1] = 0;
    }

    void connect(int a, int b, int son) {
        if (b != 0) f[b] = a;
        if (a != 0) ch[a][son] = b;
    }

    void rotate(int a) {
        int fa = f[a];
        int ffa = f[fa];
        int k = ch[fa][0] == a ? 0 : 1;
        int k2 = k ^ 1;
        connect(ffa, a, ch[ffa][0] == fa ? 0 : 1);
        connect(fa, ch[a][k2], k);
        connect(a, fa, k2);
        update(fa);
        update(a);
    }

    /**
     * 将a节点旋转至v(使得v为a的直接父亲),v为0则旋至根
     */
    void splay(int a, int v) {
        for (int fa; (fa = f[a]) != v; rotate(a))
            if (f[fa] != v) rotate(((ch[f[a]][0] == a) ^ (ch[f[fa]][0] == fa)) ? a : fa);
        if (v == 0) root = a;
    }

    /**
     * 插入一个值为x的数
     */
    void add(int x) {
        if (root == 0) {
            root = len;
            val[len] = x;
            siz[len] = 1;
            cnt[len] = 1;
            len++;
            return;
        }
        int now = root, fa = 0;
        while (true) {
            if (x == val[now]) {
                cnt[now]++;
                siz[now]++;
                pushUp(f[now]);
                splay(now, 0);
                break;
            }
            fa = now;
            now = ch[now][(x < val[now]) ? 0 : 1];
            if (now == 0) {
                val[len] = x;
                siz[len] = 1;
                cnt[len] = 1;
                connect(fa, len, (x < val[fa]) ? 0 : 1);
                pushUp(fa);
                len++;
                splay(len - 1, 0);
                break;
            }
        }
    }

    /**
     * 查找x的排名
     */
    int find(int x) {
        int now = root;
        int ans = 0;
        while (true) {
            if (x < val[now]) {
                now = ch[now][0];
                continue;
            }
            ans += siz[ch[now][0]];
            if (x == val[now]) {
                splay(now, 0);
                return ans + 1;
            }
            ans += cnt[now];
            now = ch[now][1];
        }
    }

    /**
     * 返回当前根节点前驱的下标
     */
    int prefixId() {
        int now = ch[root][0];
        while (ch[now][1] != 0) now = ch[now][1];
        return now;
    }

    /**
     * 返回x的前驱的值
     */
    int prefix(int x) {
        add(x);
        int res = val[prefixId()];
        del(x);
        return res;
    }

    /**
     * 返回当前根节点后继的下标
     */
    int suffixId() {
        int now = ch[root][1];
        while (ch[now][0] != 0) now = ch[now][0];
        return now;
    }

    /**
     * 返回x的后继的值
     */
    int suffix(int x) {
        add(x);
        int res = val[suffixId()];
        del(x);
        return res;
    }

    /**
     * 找到排名为k的值(第k小的值)
     */
    int kth(int k) {
        int now = root;
        while (true) {
            if (ch[now][0] != 0) {
                if (k <= siz[ch[now][0]]) {
                    now = ch[now][0];
                    continue;
                }
                k -= siz[ch[now][0]];
            }
            if (k <= cnt[now]) {
                splay(now, 0);
                return val[now];
            }
            k -= cnt[now];
            now = ch[now][1];
        }
    }

    /**
     * 删除一个值为x的数
     */
    void del(int x) {
        find(x);
        if (cnt[root] > 1) {
            cnt[root]--;
            update(root);
            return;
        }
        if (ch[root][0] == 0 && ch[root][1] == 0) {
            clear(root);
            root = 0;
            return;
        }
        if (ch[root][0] == 0) {
            int tmp = root;
            f[root = ch[root][1]] = 0;
            clear(tmp);
            return;
        }
        if (ch[root][1] == 0) {
            int tmp = root;
            f[root = ch[root][0]] = 0;
            clear(tmp);
            return;
        }
        int tmp = root;
        int l = prefixId();
        splay(l, 0);
        connect(l, ch[tmp][1], 1);
        clear(tmp);
    }
}
