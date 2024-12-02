package leetcode.list.T50_99;

public class T76 {
    // public String minWindow(String s, String t) {
    //
    // }

    public static void main(String[] args) {
        String original = "26 Oak St,--";

        StringBuilder sb = new StringBuilder();
        String res;

        for (int i = 0; i < original.length(); i++) {
            char c = original.charAt(i);
            if (c == '#' || c == '*')
                continue;
            if (c == '-' && i + 1 < original.length() && original.charAt(i + 1) == '-') {
                i += 1;
                continue;
            }
            sb.append(c);
        }

        while (sb.charAt(sb.length() - 1) == ',')
            sb.deleteCharAt(sb.length() - 1);

        res = sb.toString();
        System.out.println(res);
    }
}
