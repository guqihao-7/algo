package leetcode.list.T750_799;

public class T765 {
    public static void main(String[] args) {
        T765 solution = new T765();
        int[] a = {0, 2, 1, 3};
        a = new int[]{3, 2, 0, 1};
        System.out.println(solution.minSwapsCouples(a));
    }

    public int minSwapsCouples(int[] row) {
        int n = row.length;
        int ans = 0;
        for (int i = 0; i < n; i += 2) {
            int cur = row[i];
            int need = (cur & 1) == 0 ? cur + 1 : cur - 1;
            if (row[i + 1] != need) {
                find(row, need, i + 1);
                ans++;
            }
        }
        return ans;
    }

    void find(int[] nums, int need, int begin) {
        for (int i = begin; i < nums.length; i++) {
            if (nums[i] == need) {
                swap(nums, begin, i);
                return;
            }
        }
    }

    void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
