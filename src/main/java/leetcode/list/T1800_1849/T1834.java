package leetcode.list.T1800_1849;


import java.util.Arrays;
import java.util.PriorityQueue;

public class T1834 {
    public int[] getOrder(int[][] tasks) {
        PriorityQueue<Task> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.start < o2.start) {
                return -1;
            }
            else {
                int c = o1.end - o1.start;
                int d = o2.end - o2.start;
                if (c < d) {
                    return -1;
                }
                else if (c == d) {
                    if (o1.start - o2.start < 0) {
                        return -1;
                    }
                    else if (o1.start == o2.start) {
                        return 0;
                    }
                    else {
                        return 1;
                    }
                }
                else {
                    return 1;
                }
            }
        });
        for (int i = 0; i < tasks.length; i++) {
            pq.add(new Task(i, tasks[i][0], tasks[i][0] + tasks[i][1]));
        }
        int[] res = new int[tasks.length];
        int i = 0;
        while (!pq.isEmpty()) {
            Task task = pq.poll();
            System.out.println(task);
            res[i++] = task.i;
        }
        return res;
    }

    static class Task {
        int i;
        int start, end;

        public Task(int i, int start, int end) {
            this.start = start;
            this.end = end;
            this.i = i;
        }

        @Override
        public String toString() {
            return "Task{" +
                    "i=" + i +
                    ", start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public static void main(String[] args) {
        int[][] tasks = {{1, 2}, {2, 4}, {3, 2}, {4, 1}};
        T1834 solution = new T1834();
        System.out.println(Arrays.toString(solution.getOrder(tasks)));
    }
}
