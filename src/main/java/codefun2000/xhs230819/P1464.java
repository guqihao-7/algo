package codefun2000.xhs230819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class P1464 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        String line = br.readLine();
        int n = Integer.parseInt(line);

        Map<String, Integer> map = new HashMap<>();
        Set<String> remembered = new HashSet<>();
        int c = 1, ans = 0;
        while (n-- > 0) {
            line = br.readLine();
            map.put(line, map.getOrDefault(line, 0) + 1);
            if (map.get(line) >= c && !remembered.contains(line)) {
                ans++; c++;
                remembered.add(line);
            }
        }
        pw.println(ans);
        pw.close();
        br.close();
    }
}
