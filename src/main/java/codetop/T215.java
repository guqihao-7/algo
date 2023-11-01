package codetop;

import java.io.IOException;
import java.util.PriorityQueue;

public class T215 {
    public static void main(String[] args) throws IOException {
        int[] nums = {3,2,1,5,6,4};
        T215 solution = new T215();
        int k = 2;
        /*
        	1大		nums.length - 1
         */
        System.out.println(solution.qnthmin(nums, 0, nums.length - 1, nums.length - k));
    }

    public int findKthLargest(int[] nums, int k) {
        return qnthmin(nums, 0, nums.length - 1, nums.length - k);
    }

    int pq(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.add(num);
            if (pq.size() > k)
                pq.poll();
        }
        return pq.peek();
    }

    /**
     * 求第 k 小, k 从 0 开始
     */
    int qnthmin(int[] nums, int l, int r, int k) {
        if (l == r) return nums[l];
        int i = l - 1, j = r + 1, x = nums[l + r >> 1];
        while (i < j) {
            do i++; while (nums[i] < x);
            do j--; while (nums[j] > x);
            if (i < j) swap(nums, i, j);
        }
        if (k <= j) return qnthmin(nums, l, j, k);
        return qnthmin(nums, j + 1, r, k);
    }

    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /*int qsort3(int[] nums, int l, int r, int k) {
        if (l == r) return nums[l];
        int x = nums[l], n = nums.length;
        int lt = l, gt = r, idx = l;
        while (idx <= gt) {
            if (nums[idx] < x) swap(nums, lt++, idx++);
            else if (nums[idx] > x) swap(nums, idx, gt--);
            else idx++;
        }
        // 这个是从小到大排序的, 因此需要倒着算第几大
        int eqr = n - gt, eql = n - lt;        // 算的是等于区间的两端是第几大, 右端点是第 n-gt 大
        if (k >= eql && k <= eqr) return nums[lt];
        else if (k < eqr) return qsort3(nums, gt + 1, r, k);
        else return qsort3(nums, l, lt - 1, k);
    }*/
}
