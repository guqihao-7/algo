package ds;

public class BinarySearch {
    // 找 x 或 x 的后继
    int bsearch_succ(int[] a, int x) {
        // 左闭右开
        int n = a.length, l = 0, r = n;
        while (l < r) {
            // mid 是左中位数
            int mid = (l + r) >> 1;
            if (a[mid] >= x) r = mid;
            else l = mid + 1;
        }
        // 代码执行完之后 l==r
        return l;
    }

    int bsearch_pre(int[] a, int x) {
        int n = a.length, l = 0, r = n;
        while (l < r) {
            // 右中位数
            int mid = (l + r + 1) >> 1;
        	if (a[mid] > x) r = mid - 1;
            else l = mid;
        }
        // l == r
        return l;
    }

    /*
         返回第一个大于等于 target 的下标, 如果所有数都小于 target, 返回数组长度
         大于 target --> 大于等于 (target+1)
         小于 target --> 大于等于 target 的第一个数的下标 - 1
         小于等于 target --> 大于等于 (target+1) 的第一个数的下标 - 1
     */
    static int lower_bound(int[] nums, int target) {
        int n = nums.length, l = 0, r = n - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] < target) l = m + 1;
            else r = m - 1; // 等于的时候, r = m - 1, 因为找下界, 所以 r 要往右边缩
        }
        return l;
    }
}
