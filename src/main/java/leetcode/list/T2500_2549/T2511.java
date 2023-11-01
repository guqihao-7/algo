package leetcode.list.T2500_2549;

public class T2511 {
    public static void main(String[] args) {
        int[] forts = {1,0,0,-1,0,0,-1,0,0,1};
        T2511 solution = new T2511();
        System.out.println(solution.captureForts(forts));
    }

    public int captureForts(int[] forts) {
        int i = 0, j, n = forts.length;
        while (i < n && forts[i] == 0) i++;
        j = i;
        int max = 0;
        while (j < n) {
            if (forts[j] * forts[i] < 0) {
                max = Math.max(j - i - 1, max);
                i = j;
            } else if (forts[j] == forts[i]) {
                i = j;
            }
            j++;
        }
        return max;
    }
}
