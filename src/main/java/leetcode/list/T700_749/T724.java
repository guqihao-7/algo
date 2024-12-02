package leetcode.list.T700_749;

public class T724 {
    public int pivotIndex(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int l = 0, r = 0;
            for (int j = 0; j < i; j++) l += nums[j];
            for (int j = i + 1; j < len; j++) r += nums[j];
            if (l == r) return i;
        }
        return -1;
    }
}
