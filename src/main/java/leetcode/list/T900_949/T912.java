package leetcode.list.T900_949;

import java.util.Arrays;

/**
 * 排序数组
 */
public class T912 {

    public static void main(String[] args) {
        T912 solution = new T912();
        int[] a = {5, 1, 1, 2, 0, 0};
        System.out.println(Arrays.toString(solution.sortArray(a)));
    }

    public int[] sortArray(int[] nums) {
        //heap = new int[nums.length + 1];
        //heapsort(nums);
        //return nums;

        //tmp = new int[nums.length];
        //mergesort(nums, 0, nums.length - 1);
        //return nums;

        qsort(nums, 0, nums.length - 1);
        return nums;
    }

    static int[] tmp;

    void mergesort(int[] a, int l, int r) {
        if (l >= r) return;
        int m = l + r >> 1;
        mergesort(a, l, m);
        mergesort(a, m + 1, r);

        int k = 0, i = l, j = m + 1;
        while (i <= m && j <= r) {
            if (a[i] <= a[j]) tmp[k++] = a[i++];
            else tmp[k++] = a[j++];
        }

        while (i <= m) tmp[k++] = a[i++];
        while (j <= r) tmp[k++] = a[j++];
        for (i = l, j = 0; i <= r; i++, j++)
            a[i] = tmp[j];
    }

    void qsort(int[] nums, int l, int r) {
        if (l >= r) return;
        int x = nums[l];
        int i = l - 1, j = r + 1;
        while (i < j) {
            // i 找第一个大于的, j 找第一个小于的
            do i++; while (nums[i] < x);
            do j--; while (nums[j] > x);
            if (i < j) swap(nums, i, j);
        }
        qsort(nums, l, j);
        qsort(nums, j + 1, r);
    }

    int[] heap;
    int len;

    void heapsort(int[] nums) {
        for (int i : nums)
            push(i);
        for (int i = 0; i < nums.length; i++)
            nums[i] = pop();
    }

    void push(int x) {
        heap[++len] = x;
        int i = len;
        while (i > 1 && heap[i] < heap[i / 2]) {
            swap(heap, i, i / 2);
            i /= 2;
        }
    }

    int pop() {
        int tmp = heap[1];
        swap(heap, 1, len--);
        int i = 1;
        while (i * 2 <= len) {
            int minIdx = 2 * i;
            if (minIdx + 1 <= len && heap[minIdx] > heap[minIdx + 1])
                minIdx += 1;
            if (heap[i] <= heap[minIdx]) break;
            swap(heap, i, minIdx);
            i = minIdx;
        }
        return tmp;
    }

    void bubblesort(int[] nums) {
        int n = nums.length;
        for (int i = 1; i <= n - 1; i++) {
            int cnt = 0;
            for (int j = 1; j <= n - i; j++) {
                if (nums[j] < nums[j - 1]) {
                    cnt++;
                    swap(nums, j, j - 1);
                }
            }
            if (cnt == 0) break;
        }
    }

    void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
