package codetop;

/**
 * 字符串相乘
 */
public class T43 {
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] res = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j, p2 = i + j + 1;
                int sum = mul + res[p2];
                res[p2] = sum % 10;
                res[p1] += sum / 10;
            }
        }
        int i = 0;
        while (i < res.length && res[i] == 0)
            i++;
        StringBuilder sb = new StringBuilder();
        for (; i < res.length; i++)
            sb.append(res[i]);
        String ans = sb.toString();
        return ans.length() == 0 ? "0" : ans;
    }
}
