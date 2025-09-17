package leetcode.list.T2300_2349;

import java.util.*;

public class T2349 {
    public static void main(String[] args) {
        NumberContainers containers = new NumberContainers();
        containers.change(1, 10);
        System.out.println(containers.find(10));
        containers.change(1, 20);
        System.out.println(containers.find(10));
        System.out.println(containers.find(20));
        System.out.println(containers.find(30));
    }

    static class NumberContainers {
        private final Map<Integer, Integer> map;
        private final Map<Integer, TreeSet<Integer>> map2;

        public NumberContainers() {
            this.map = new HashMap<>();
            this.map2 = new HashMap<>();
        }

        public void change(int index, int number) {
            Integer prevValue = map.get(index);
            map.put(index, number);
            if (prevValue != null) {
                map2.get(prevValue).remove(index);
            }

            if (map2.containsKey(number))
                map2.get(number).add(index);
            else {
                TreeSet<Integer> set = new TreeSet<>();
                set.add(index);
                map2.put(number, set);
            }
        }

        public int find(int number) {
            TreeSet<Integer> set = map2.get(number);
            if (set == null || set.isEmpty()) return -1;
            return set.first();
        }
    }
}
