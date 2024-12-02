package luogu;

import data_structure.DynamicSegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P3372 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] s = line.split(" ");
        int n = Integer.parseInt(s[0]), m = Integer.parseInt(s[1]);
        line = br.readLine();
        int[] nums = new int[n];
        s = line.split(" ");
        for (int i = 0; i < n; i++)
            nums[i] = Integer.parseInt(s[i]);
        DynamicSegmentTree segmentTree = new DynamicSegmentTree(Integer::sum);
        for (int i = 0; i < nums.length; i++) {
            segmentTree.updatePoint(i, nums[i]);
        }
        for (int i = 0; i < m; i++) {
            line = br.readLine();
            s = line.split(" ");
            int[] tmp = new int[s.length];
            for (int j = 0; j < s.length; j++) tmp[j] = Integer.parseInt(s[j]);
            if (tmp[0] == 1) {
                segmentTree.addInterval(tmp[1] - 1, tmp[2] - 1, tmp[3]);
            }
            else if (tmp[0] == 2) {
                System.out.println(segmentTree.queryInterval(tmp[1] - 1, tmp[2] - 1));
            }
        }
    }
}
