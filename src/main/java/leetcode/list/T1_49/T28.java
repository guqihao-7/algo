package leetcode.list.T1_49;

public class T28 {
    public int strStr(String haystack, String needle) {
        // return bruteForce(haystack, needle);
        return kmp(haystack, needle);
    }

    int bruteForce(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        for (int i = 0; i <= n - m; i++) {
            int a = i, b = 0;
            while (b < m && haystack.charAt(a) == needle.charAt(b)) {
                a++;
                b++;
            }
            if (b == m) return i;
        }
        return -1;
    }

    public int kmp(String haystack, String needle) {
        int j = 0;
        for (int i = 0; i < haystack.length(); ) {
            while (j <= needle.length()) {
                if (j == needle.length())
                    return i;
                if (haystack.charAt(i) == needle.charAt(j)) {
                    i++;
                    j++;
                }
                else {
                    j = needle.length() - j - 1;
                    break;
                }
            }
        }
        return -1;
    }

    // txt.charAt(i) 与 pat.charAt(j) 比较后, 与 txt.charAt(i + 1) 比较的 pat 的下标
    public int next(String txt, int i, String pat, int j) {
        if (txt.charAt(i) == pat.charAt(j)) return j + 1;
        else return pat.length() - j;
    }

    public static void main(String[] args) {
        T28 solution = new T28();
        System.out.println(solution.strStr("sadbutsad", "sad"));
        System.out.println(solution.strStr("abcaof", "abce"));
    }
}
