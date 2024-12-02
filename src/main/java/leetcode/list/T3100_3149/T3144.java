package leetcode.list.T3100_3149;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class T3144 {
    public int minimumSubstringsInPartition(String s) {
        return dfs(s, s.length() - 1);
    }

    Map<Integer, Integer> memo = new HashMap<>();

    public int dfs(String s, int idx) {
        if (idx < 0) return 0;
        if (idx == 0) return 1;

        int key = (s + " " + idx).intern().hashCode();
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= idx; i++) {
            String a = s.substring(i, idx + 1);
            if (!balance(a)) continue;
            ans = Math.min(ans, dfs(s, i - 1) + 1);
        }

        memo.put(key, ans);

        return ans;
    }

    private boolean balance(String a) {
        return a.chars()
                .boxed()
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .values()
                .stream()
                .distinct()
                .count() == 1;
    }

    public static void main(String[] args) {
        T3144 solution = new T3144();
        int a = solution.minimumSubstringsInPartition("ddpohkuytutetjkellldddgihnnzzerjjschnnnrqfjonqqbkkkssmggqxyaaaqzrvvvkkvvuuvagkuonewwwwjxdkdxzwtoikqpmyyhixxiiiqnigctxxxeoofcsssssiikljmaaaaamsgkrxxxxxtffdrddddkghhgbabayxsskvqqxawimipnyxpqbcvvxsvwllbbuuqmmmbbbgefddbdzzuuiatecwnghmnmrrpnqqqjjhjswhzttsqdvwxxxdeccccccffhptbbvuyhnmziibxxxxtvvaqddcyvpppzbcddlmyriakkmaaacdeitxpoijpphgfeohhbbbbttllzuuqhquizddbbnrcccqkmlprddesrsrhivvwvjnnnzdxsttgaajjjgggvvvzeeyllyzfffsssrrssddvlkevfhtedcjwwqqmnbddoxxxxxaafoggglbzyydncccbaffhghdfrodqlljkjjkzwwxxonmoqjjjjaxyssjkwmyxhaaccpqqqqqqqgggsxwioodddklmfgfzyoibbbhhhyyyrooeepkkhhttjddccuukourrrggnssnhhk");
        System.out.println(a);
    }
}
