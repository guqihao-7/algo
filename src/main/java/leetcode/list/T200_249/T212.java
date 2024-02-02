package leetcode.list.T200_249;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T212 {
    // 基本思路全部走一遍 dfs 构建前缀树，然后在前缀树搜
    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        int m = board.length, n = board[0].length;
        StringBuilder path = new StringBuilder();
        boolean[][] visit = new boolean[m][n];
        int[] cnt = new int[26];
        words = filter(words, board);
        for (String word : words) {
            cnt[word.charAt(0) - 'a']++;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cnt[board[i][j] - 'a'] == 0) continue;  // 没有这个剪枝也能过，但是慢一点
                dfs(trie, board, i, j, path, visit);
                for (boolean[] arr : visit) Arrays.fill(arr, false);
            }
        }

        // trie.print();

        List<String> ans = new ArrayList<>();
        for (String word : words) {
            if (trie.contains(word)) {
                ans.add(word);
            }
        }
        return ans;
    }

    public String[] filter(String[] words, char[][] board) {
        List<String> list = new ArrayList<>();
        int[] cnt = new int[26];
        for (char[] chars : board) {
            for (char aChar : chars) {
                cnt[aChar - 'a']++;
            }
        }

        for (String word : words) {
            // 下面两种情况可以剪枝
            int[] tmp = new int[26];
            boolean flag = false;
            for (int i = 0; i < word.length(); i++) {
                // 如果word里面含有boards中不存在的字符
                if (cnt[word.charAt(i) - 'a'] == 0) {
                    flag = true;
                    break;
                }
                // 或者某种字符的数量比boards中该字符数量更多
                tmp[word.charAt(i) - 'a']++;
            }

            for (int i = 0; i < 26; i++) {
                if (tmp[i] > cnt[i]) {
                    flag = true;
                    break;
                }
            }

            if (flag) continue;
            list.add(word);
        }

        return list.toArray(new String[0]);
    }

    public void dfs(Trie trie, char[][] board, int x, int y, StringBuilder path, boolean[][] visit) {
        int m = board.length, n = board[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n) return;
        // visit 只需保证路径上不重，不会死循环
        if (visit[x][y]) return;
        if (path.length() >= 10) return;    // word的长度不会超过 10，这里来剪枝

        path.append(board[x][y]);
        trie.insert(path.toString());
        visit[x][y] = true;

        dfs(trie, board, x + 1, y, path, visit);
        dfs(trie, board, x - 1, y, path, visit);
        dfs(trie, board, x, y + 1, path, visit);
        dfs(trie, board, x, y - 1, path, visit);

        visit[x][y] = false;
        path.deleteCharAt(path.length() - 1);
    }

    public static class Trie {
        TrieNode root = new TrieNode('/');

        public void insert(String word) {
            int len = word.length();
            TrieNode p = root;
            for (int i = 0; i < len; i++) {
                char c = word.charAt(i);
                if (p.children == null || p.children.length == 0) {
                    p.children = new TrieNode[26];
                }
                if (p.children[c - 'a'] == null) {
                    p.children[c - 'a'] = new TrieNode(c);
                }
                p = p.children[c - 'a'];
            }
            p.isEndingChar = true;
        }

        public boolean contains(String word) {
            if (word == null || word.isEmpty()) return false;
            TrieNode p = root;
            int len = word.length();
            for (int i = 0; i < len; i++) {
                char c = word.charAt(i);
                if (p == null ||
                        p.children == null ||
                        p.children.length == 0) {
                    return false;
                }
                p = p.children[c - 'a'];
            }
            return p != null && p.isEndingChar;
        }

        public void print() {
            StringBuilder sb = new StringBuilder();
            for (TrieNode child : root.children) {
                dfs(child, sb);
            }
        }

        private void dfs(TrieNode node, StringBuilder sb) {
            if (node == null) return;
            sb.append(node.data);
            if (node.isEndingChar) {
                System.out.println(sb);
            }
            for (TrieNode child : node.children) {
                dfs(child, sb);
            }
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static class TrieNode {
        public char data;
        public boolean isEndingChar;
        public TrieNode[] children;

        public TrieNode(char data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        T212 solution = new T212();
        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };

        String[] words = {
                "oath", "pea", "eat", "rain"
        };
        List<String> list = solution.findWords(board, words);
        System.out.println(list);
    }
}
