package leetcode.weekly.w468;

public class T1 {
    public static void main(String[] args) {
        T1 solution = new T1();
    }

    public int evenNumberBitwiseORs(int[] nums) {
        int res = 0;
        for (int num : nums) {
            if (num % 2 == 0)
                res |= num;
        }
        return res;
    }
}
