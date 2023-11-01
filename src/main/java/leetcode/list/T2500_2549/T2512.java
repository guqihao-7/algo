package leetcode.list.T2500_2549;

import java.util.*;

public class T2512 {
    public static void main(String[] args) {
        T2512 solution = new T2512();
        String[] positive_feedback = {"m", "eveszfubew"};
        String[] negative_feedback = {"iq", "etwuedg", "egpakyk", "da", "qkmhvgxg", "q", "zs", "ujmy", "mh"};
        String[] report = {"eveszfubew jebebqp iq eveszfubew eveszfubew iq daej eveszfubew q da", "ohfz zs ujmy egpakyk eveszfubew pffeq q qkmhvgxg kdgqq ipp", "cceierguau mh da eveszfubew m etwuedg ikeft egpakyk ltnibxljfi m", "km m iq rab inooo ujmy tlrdyu yqhn m xlkhebs", "q etwuedg m eveszfubew ixrfzwmb m jyltumdwt dacmewk odbllqdiq eveszfubew"};
        int[] student_id = {643903773, 468275834, 993893529, 509587004, 61125507};
        int k = 5;
        System.out.println(solution.topStudents(positive_feedback, negative_feedback, report, student_id, k));
    }

    public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback,
                                     String[] report, int[] student_id, int k) {
        Set<String> ps = new HashSet<>(Arrays.asList(positive_feedback));
        Set<String> ns = new HashSet<>(Arrays.asList(negative_feedback));
        int n = report.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            int sid = student_id[i];
            int t = 0;
            for (String s : report[i].split(" ")) {
                if (ps.contains(s)) t += 3;
                else if (ns.contains(s)) t -= 1;
            }
            arr[i] = new int[]{t, sid};
        }
        Arrays.sort(arr, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) ans.add(arr[i][1]);
        return ans;
    }
}
