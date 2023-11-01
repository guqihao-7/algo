package data_structure;

/**
 * 大根堆
 * 数组下标从 1 开始, 父节点节点 i 的左孩子是 2i, 右孩子是 2i+1
 * 子节点的父亲是 i/2
 * 父节点 i,如果 2i>n, 则该节点没孩子; 2i+1>n, 则该节点没有右孩子
 */
public class MaxHeap {
	int[] heap;
    int len;

    public MaxHeap(int n) {
    	this.heap = new int[n + 1];		// 下标 1-n
    }

    // 上浮 ->进堆, 建堆时所有元素进堆
    public void push(int x) {
        heap[++len] = x;
        int i = len;
        while (i > 1 && heap[i] > heap[i / 2]) {
            swap(i, i / 2);
            i /= 2;
        }
    }

    public int popMin() {
        return heap[len--];
    }

    public int pop() {
		int tmp = heap[1];
        swap(1, len--);
        int i = 1;
		while (2 * i <= len) { // 至少有孩子
            int maxIdx = 2 * i;
            if (maxIdx + 1 <= len && heap[maxIdx] < heap[maxIdx + 1])
                maxIdx += 1;
			if (heap[i] > heap[maxIdx]) break;
            swap(i, maxIdx);
            i = maxIdx;
        }
        return tmp;
    }

	public void build(int[] a) {
        for (int i : a)
            this.push(i);
    }

    public void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
}
