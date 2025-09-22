package leetcode.weekly.w468;

import cn.hutool.core.collection.CollUtil;

import java.util.*;

public class T3 {
    public static void main(String[] args) {
        T3 solution = new T3();
        System.out.println(solution.minSplitMerge(new int[]{3, 1, 2}, new int[]{1, 2, 3}));
        System.out.println(solution.minSplitMerge(new int[]{1, 1, 2, 3, 4, 5}, new int[]{5, 4, 3, 2, 1, 1}));
        System.out.println(solution.minSplitMerge(new int[]{37, 10}, new int[]{37, 10}));
        System.out.println(solution.minSplitMerge(new int[]{24, -98, 98}, new int[]{98, -98, 24}));
        System.out.println();
        System.out.println(solution.minOp(new int[]{3, 1, 2}, new int[]{1, 2, 3}));
        System.out.println(solution.minOp(new int[]{1, 1, 2, 3, 4, 5}, new int[]{5, 4, 3, 2, 1, 1}));
        System.out.println(solution.minOp(new int[]{37, 10}, new int[]{37, 10}));
        System.out.println(solution.minOp(new int[]{24, -98, 98}, new int[]{98, -98, 24}));
    }

    public int minSplitMerge(int[] nums1, int[] nums2) {
        int n = nums1.length, res = 1;
        List<Integer> list = new ArrayList<>(), list2 = new ArrayList<>();
        for (int i : nums1) list.add(i);
        for (int i : nums2) list2.add(i);

        int targetHash = list2.hashCode();
        int hashCode = list.hashCode();
        if (hashCode == targetHash) return 0;

        Set<Integer> visit = new HashSet<>();
        visit.add(hashCode);

        Deque<List<Integer>> q = new ArrayDeque<>();
        q.addLast(list);
        while (!q.isEmpty()) {
            int sz = q.size();
            while (sz -- > 0) {
                List<Integer> head = q.removeFirst();
                for (int i = 0; i < n; i++) {
                    for (int j = i; j < n; j++) {
                        List<Integer> remove = new ArrayList<>(head.subList(i, j + 1));
                        List<Integer> remain = new ArrayList<>(head.subList(0, i));
                        remain.addAll(new ArrayList<>(head.subList(j + 1, n)));

                        int remainSize = remain.size();
                        for (int k = 0; k <= remainSize; k++) {
                            List<Integer> copy = new ArrayList<>(remain);
                            copy.addAll(k, remove);
                            int copyHashCode = copy.hashCode();
                            if (copyHashCode == targetHash && copy.equals(list2))
                                return res;
                            if (!visit.contains(copyHashCode)) {
                                visit.add(copyHashCode);
                                q.addLast(copy);
                            }
                        }
                    }
                }
            }
            res++;
        }

        return res;
    }

    public int minOp(int[] nums1, int[] nums2) {
        List<Integer> targetList = new ArrayList<>();
        for (int x : nums2) targetList.add(x);
        String target = targetList.toString();

        Map<String, Integer> dist = new HashMap<>();
        Queue<List<Integer>> queue = new LinkedList<>();

        List<Integer> start = new ArrayList<>();
        for (int x : nums1) start.add(x);
        String startKey = start.toString();
        dist.put(startKey, 0);
        queue.add(start);
        if (startKey.equals(target)) return 0;
        int n = nums1.length;
        while (!queue.isEmpty()) {
            List<Integer> cur = queue.poll();
            String curKey = cur.toString();
            int d = dist.get(curKey);

            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    List<Integer> sub = new ArrayList<>(cur.subList(i, j + 1));
                    List<Integer> remain = new ArrayList<>();
                    remain.addAll(cur.subList(0, i));
                    remain.addAll(cur.subList(j + 1, n));

                    int m = remain.size();
                    for (int k = 0; k <= m; k++) {
                        List<Integer> next = new ArrayList<>();
                        next.addAll(remain.subList(0, k));
                        next.addAll(sub);
                        next.addAll(remain.subList(k, m));

                        String nextKey = next.toString();
                        if (nextKey.equals(target)) return d + 1;
                        if (!dist.containsKey(nextKey)) {
                            dist.put(nextKey, d + 1);
                            queue.add(next);
                        }
                    }
                }
            }
        }
        return -1;
    }
}
