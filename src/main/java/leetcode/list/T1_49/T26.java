package leetcode.list.T1_49;

public class T26 {
    public int removeDuplicates(int[] nums) {
        int i = 0, j = 0, len = nums.length;
        while (j < len) {
            if (nums[i] != nums[j])
                nums[++i] = nums[j];
            j++;
        }
        return ++i;
    }
}
