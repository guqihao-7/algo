package codetop;

/**
 * 模拟字符串相加
 */
public class T415 {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        // cur 保留进位
        int cur = 0, i = num1.length() - 1, j = num2.length() - 1;
        while (i >= 0 || j >= 0 || cur > 0) {
            if (i >= 0) cur += num1.charAt(i--) - '0';
            if (j >= 0) cur += num2.charAt(j--) - '0';
            sb.append(cur % 10);
            cur /= 10;
        }
        return sb.reverse().toString();
    }
}
