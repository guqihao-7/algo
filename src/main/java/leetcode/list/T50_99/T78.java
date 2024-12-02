package leetcode.list.T50_99;

import java.util.ArrayList;
import java.util.List;

public class T78 {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        dfs(nums, 0, new ArrayList<>());
        return ans;
    }

    public void dfs(int[] nums, int s, List<Integer> path) {
        ans.add(new ArrayList<>(path));

        for (int i = s; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(nums, i + 1, path);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
    }
}
