package basic_algo.lesson2;

public class T42 {
    public int trap(int[] height) {
        return planA(height);
    }

    public int planA(int[] height) {
        int n = height.length;
        int[] preMax = new int[n];
        int[] sufMax = new int[n];
        preMax[0] = height[0];
        sufMax[n - 1] = height[n - 1];

        for (int i = 1; i < n; i++)
            preMax[i] = Math.max(height[i], preMax[i - 1]);

        for (int i = n - 2; i >= 0; i--)
            sufMax[i] = Math.max(sufMax[i + 1], height[i]);

        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += Math.min(preMax[i], sufMax[i]) - height[i];
        return sum;
    }

    public static void main(String[] args) {
        T42 solution = new T42();
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(solution.trap(height));
    }
}
