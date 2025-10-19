package leetcode.list.T800_849;

public class T812 {
    // https://leetcode.cn/problems/largest-triangle-area/solutions/3793198/liang-chong-fang-fa-mei-ju-tu-bao-xuan-z-1780/?envType=daily-question&envId=2025-09-27
    public double largestTriangleArea(int[][] points) {
        int n = points.length;
        int ans = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    int[] p1 = points[i], p2 = points[j], p3 = points[k];
                    int x1 = p2[0] - p1[0], y1 = p2[1] - p1[1];
                    int x2 = p3[0] - p1[0], y2 = p3[1] - p1[1];
                    ans = Math.max(ans, Math.abs(x1 * y2 - y1 * x2));
                }
            }
        }
        return ans / 2.0;
    }
}
