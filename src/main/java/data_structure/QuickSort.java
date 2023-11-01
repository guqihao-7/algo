package ds;

public class QuickSort {
    public static void main(String[] args) {
        int[] nums = {5, 1, 1, 2, 0, 0};
        new QuickSort().qsort2(nums, 0, nums.length - 1);
    }

    // 三路快排
    void qsort3(int[] a, int l, int r) {
        if (l >= r) return;
        int x = a[l];
        // lt gt 左开右开, lt 左边的是, gt 右边的是, 不包含 lt 和 gt 位置的
        // 这里其实是荷兰国旗问题
        int lt = l, gt = r, idx = l;
        while (idx <= gt) {	// gt 是开, 所以 gt 位置的数字也要被处理
            if (a[idx] < x) swap(a, lt++, idx++);
            else if (a[idx] > x) swap(a, idx, gt--);
            else idx++;
        }
        qsort3(a, l, lt - 1);	// 这里是闭区间, 所以要减一和加一
        qsort3(a, gt + 1, r);
    }

    // 二路快排
    // 所有 大于等于x的在右边, 小于等于 x 的在左边
    // x 不一定在中间

    // 这样记: j 找到小于等于 x, 是应该放在左边的, i 找到大于等于 x 的, 这时应该放在右边的
    void qsort2(int[] nums, int l, int r) {
        if (l >= r) return;
        int x = nums[l];
        int i = l - 1, j = r + 1;
        while (i < j) {
            do i++; while (nums[i] < x);
            do j--; while (nums[j] > x);
            if (i < j) swap(nums, i, j);
        }
        qsort2(nums, l, j);
        qsort2(nums, j + 1, r);
    }

    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
