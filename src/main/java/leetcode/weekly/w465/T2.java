package leetcode.weekly.w465;

import java.util.*;

public class T2 {
    public static void main(String[] args) {
        T2 solution = new T2();
        int[] ints = solution.minDifference(100, 2);
        System.out.println(Arrays.toString(ints));
        ints = solution.minDifference(360, 4);
        System.out.println(Arrays.toString(ints));
    }

    public int[] minDifference(int n, int k) {
        List<List<Integer>> results = new ArrayList<>();
        if (k == 2) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n / i; j++) {
                    long l = ((long) i) * ((long) j);
                    if (l == n) {
                        List<Integer> list = new ArrayList<>();
                        list.add(i);
                        list.add(j);
                        results.add(list);
                    }
                }
            }
        }
        else if (k == 3) {
            for (int a = 1; a <= n; a++) {
                for (int b = 1; b <= n / a; b++) {
                    for (int c = 1; c <= n / a / b; c++) {
                        long l = ((long) a) * ((long) b) * ((long) c);
                        if (l == n) {
                            List<Integer> list = new ArrayList<>();
                            list.add(a);
                            list.add(b);
                            list.add(c);
                            results.add(list);
                        }
                    }
                }
            }
        }
        else if (k == 4) {
            for (int a = 1; a <= n; a++) {
                for (int b = 1; b <= n / a; b++) {
                    for (int c = 1; c <= n / a / b; c++) {
                        for (int d = 1; d <= n / a / b / c; d++) {
                            long l = ((long) a) * ((long) b) * ((long) c) * ((long) d);
                            if (l == n) {
                                List<Integer> list = new ArrayList<>();
                                list.add(a);
                                list.add(b);
                                list.add(c);
                                list.add(d);
                                results.add(list);
                            }
                        }
                    }
                }
            }
        }
        else if (k == 5) {
            for (int a = 1; a <= n; a++) {
                for (int b = 1; b <= n / a; b++) {
                    for (int c = 1; c <= n / a / b; c++) {
                        for (int d = 1; d <= n / a / b / c; d++) {
                            for (int e = 1; e <= n / a / b / c / d; e++) {
                                long l = ((long) a) * ((long) b) * ((long) c) * ((long) d) * ((long) e);
                                if (l == n) {
                                    List<Integer> list = new ArrayList<>();
                                    list.add(a);
                                    list.add(b);
                                    list.add(c);
                                    list.add(d);
                                    list.add(e);
                                    results.add(list);
                                }
                            }
                        }
                    }
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (List<Integer> result : results) {
            int diff = diff(result);
            if (diff < min) {
                res = result;
                min = diff;
            }
        }

        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    private int diff(List<Integer> list) {
        Optional<Integer> max = list.stream().max(Integer::compare);
        Optional<Integer> min = list.stream().min(Integer::compare);
        return max.get() - min.get();
    }
}
