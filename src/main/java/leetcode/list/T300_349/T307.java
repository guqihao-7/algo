package leetcode.list.T300_349;

import data_structure.StaticSegmentTree;

public class T307 {
    static class NumArray {
        private final StaticSegmentTree staticSegmentTree;

        public NumArray(int[] nums) {
            staticSegmentTree = new StaticSegmentTree(nums, Integer::sum);
        }

        public void update(int index, int val) {
            staticSegmentTree.updatePoint(index, val);
        }

        public int sumRange(int left, int right) {
            return staticSegmentTree.queryInterval(left, right);
        }
    }

    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{659, 463, 793, 740, 374, 330, 772, 681});
        System.out.println(numArray.sumRange(0, 2));
        numArray.update(1, 2);
        System.out.println(numArray.sumRange(0, 2));
    }
}
