package leetcode.list.T200_249;

import java.util.HashMap;
import java.util.Map;

public class T211 {
    static
    class WordDictionary {
        TrieNode root = new TrieNode('#', false);

        public WordDictionary() {}

        public void addWord(String word) {
            int n = word.length();
            TrieNode cur = root;
            for (int i = 0; i < n; i++) {
                char c = word.charAt(i);
                if (cur.children.get(c) == null)
                    cur.children.put(c, new TrieNode(c, false));
                cur = cur.children.get(c);
            }
            cur.end = true;
        }

        public boolean search(String word) {
            TrieNode p = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (c != '.') {
                    if (p.children.get(c) == null)
                        return false;
                    p = p.children.get(c);
                } else {
                    TrieNode t = p;
                    for (Character k : t.children.keySet()) {
                        p = p.children.get(k);
                    }
                }
            }
            return p.end;
        }

        static class TrieNode {
            Map<Character, TrieNode> children = new HashMap<>();
            char data;
            boolean end;

            public TrieNode(char data, boolean end) {
                this.data = data;
                this.end = end;
            }
        }
    }
}
