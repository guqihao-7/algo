package leetcode.list.T2000_2049;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 *  not done!
 */
public class T2049 {
    public int countHighestScoreNodes(int[] parents) {
        int n = parents.length;
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < parents.length; i++) {
            int parent = parents[i];
            if (i == 0) continue;
            graph[i].add(parent);
            graph[parent].add(i);
        }
        int[] cnt = new int[n];
        for (int i = 0; i < n; i++) {

            List<Integer>[] tmp = copy(graph);
            for (List<Integer> list : tmp) {
                removeItemFromList(list, i);
            }

            for (List<Integer> integers : tmp) {
                if (!integers.isEmpty()) {
                    cnt[i]++;
                }
            }
        }
        int max = 0;
        for (int j : cnt) {
            max = Math.max(max, j);
        }
        int ans = 0;
        for (int j : cnt) {
            if (j == max) ans++;
        }
        return ans;
    }

    public void removeItemFromList(List<Integer> list, Integer item) {
        for (int k = list.size() - 1; k >= 0; k--) {
            if (Objects.equals(list.get(k), item)) {
                list.remove(item);
            }
        }
    }

    public List<Integer>[] copy(List<Integer>[] src) {
        List<Integer>[] copy = new ArrayList[src.length];
        for (int i = 0; i < src.length; i++) {
            copy[i] = new ArrayList<>();
            for (int j = 0; j < src[i].size(); j++) {
                copy[i].add(src[i].get(j));
            }
        }
        return copy;
    }

    public static void main(String[] args) {
        T2049 solution = new T2049();
        int[] parents = {-1,2,0,2,0};
        System.out.println(solution.countHighestScoreNodes(parents));
    }
}
