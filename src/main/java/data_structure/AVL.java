package data_structure;

import codefun2000.bytedance230820.T2;

import java.util.*;

// 平衡二叉搜索树（AVL树）：允许重复值
public class AVL<T> {

    static class Node<T> {
        public T val;
        public int cnt;
        public int size;
        public Node left;
        public Node right;
        public int height = 1;

        public Node() {
            super();
        }

        public Node(T val) {
            super();
            this.val = val;
            this.cnt = 1;
            this.size = 1;
        }

        public Node(T val, int cnt, int size, Node left, Node right, int height) {
            super();
            this.val = val;
            this.cnt = cnt;
            this.size = size;
            this.left = left;
            this.right = right;
            this.height = height;
        }

        public Node(T val, int cnt, int size, int height) {
            super();
            this.val = val;
            this.cnt = cnt;
            this.size = size;
            this.height = height;
        }

        @Override
        public String toString() {
            return "Node [key=" + val + ", cnt=" + cnt + ", size=" + size
                    + ", height=" + height + "]";
        }

    }

    public Node root;
    private Comparator<T> comp;
    private final Deque<Node> stack = new ArrayDeque<>();
    public int size = 0;

    private int compare(T a, T b) {
        if (comp != null) {
            return comp.compare(a, b);
        } else if (a instanceof Comparable && b instanceof Comparable && a.getClass() == b.getClass()) {
            Comparable<T> comparableA = (Comparable<T>) a;
            return comparableA.compareTo(b);
        } else {
            throw new IllegalArgumentException("Comparator is null, and a and b are not Comparable or of different types.");
        }
    }

    public AVL(Comparator<T> comp) {
        super();
        this.comp = comp;
    }

    public AVL() {
        super();
    }

    public int size() {
        return root == null ? 0 : root.size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void put(T key) {
        Node entry = getEntry(key);
        if (entry != null) {
            getEntryForInsert(key);
            entry.cnt++;
            return;
        }
        if (root == null) {
            root = new Node(key);
            stack.push(root);
        } else {
            Node p = root;
            while (p != null) {
                stack.push(p);
                int compareResult = compare(key, (T) p.val);
                if (compareResult == 0) {
                    p.cnt++;
                    break;
                } else if (compareResult < 0) {
                    if (p.left == null) {
                        p.left = new Node(key);
                        stack.push(p.left);
                        break;
                    } else {
                        p = p.left;
                    }
                } else {
                    if (p.right == null) {
                        p.right = new Node(key);
                        stack.push(p.right);
                        break;
                    } else {
                        p = p.right;
                    }
                }
            }
        }
        fixAfterInsertion(key);
    }

    public Node getEntry(T key) {
        Node p = root;
        while (p != null) {
            int compareResult = compare(key, (T) p.val);
            if (compareResult == 0) {
                return p;
            } else if (compareResult < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return null;
    }

    public void getEntryForRemove(T key) {
        Node p = root;
        while (p != null) {
            p.size--;
            int compareResult = compare(key, (T) p.val);
            if (compareResult == 0) {
                return;
            } else if (compareResult < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
    }

    public void getEntryForInsert(T key) {
        Node p = root;
        while (p != null) {
            p.size++;
            int compareResult = compare(key, (T) p.val);
            if (compareResult == 0) {
                return;
            } else if (compareResult < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
    }

    public boolean containsKey(T key) {
        Node p = getEntry(key);
        return p != null;
    }

    public Node getFirstEntry(Node p) {
        if (p == null) {
            return null;
        }
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    public Node getLastEntry(Node p) {
        if (p == null) {
            return null;
        }
        while (p.right != null) {
            p = p.right;
        }
        return p;
    }

    private Node deleteEntry(Node p, T key) {
        if (p == null) {
            return null;
        } else {
            int compareResult = compare(key, (T) p.val);
            if (compareResult == 0) {
                if (p.left == null && p.right == null) {
                    p = null;
                } else if (p.left != null && p.right == null) {
                    p = p.left;
                } else if (p.left == null && p.right != null) {
                    p = p.right;
                } else {
                    if ((root.size & 1) == 0) {
                        Node rightMin = getFirstEntry(p.right);
                        p.val = rightMin.val;
                        p.cnt = rightMin.cnt;//cnt的设置别忘了
                        Node newRight = deleteEntry(p.right, (T) p.val);
                        p.right = newRight;
                        updateSize(p);
                    } else {
                        Node leftMax = getLastEntry(p.left);
                        p.val = leftMax.val;
                        p.cnt = leftMax.cnt;
                        Node newLeft = deleteEntry(p.left, (T) p.val);
                        p.left = newLeft;
                        updateSize(p);
                    }
                }
            } else if (compareResult < 0) {
                Node newLeft = deleteEntry(p.left, key);
                p.left = newLeft;
                updateSize(p);
            } else {
                Node newRight = deleteEntry(p.right, key);
                p.right = newRight;
                updateSize(p);
            }
            p = fixAfterDeletion(p);
            updateSize(p);
            return p;
        }
    }

    public int getHeight(Node p) {
        return p == null ? 0 : p.height;
    }

    private void updateSize(Node p) {
        if (p == null) {
            return;
        }
        p.size = p.cnt;
        if (p.left != null) {
            p.size += p.left.size;
        }
        if (p.right != null) {
            p.size += p.right.size;
        }
    }

    private Node rotateRight(Node p) {
        Node left = p.left;
        p.left = left.right;
        left.right = p;
        p.height = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
        updateSize(p);
        left.height = Math.max(getHeight(left.left), p.height) + 1;
        updateSize(left);
        return left;
    }

    private Node rotateLeft(Node p) {
        Node right = p.right;
        p.right = right.left;
        right.left = p;
        p.height = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
        updateSize(p);
        right.height = Math.max(p.height, getHeight(right.right)) + 1;
        updateSize(right);
        return right;
    }

    private Node firstLeftThenRight(Node p) {
        p.left = rotateLeft(p.left);
        p = rotateRight(p);
        return p;
    }

    private Node firstRightThenLeft(Node p) {
        p.right = rotateRight(p.right);
        p = rotateLeft(p);
        return p;
    }

    private void fixAfterInsertion(T key) {
        Node p = root;
        while (!stack.isEmpty()) {
            p = stack.pop();
            int newHeight = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
            if (p.height > 1 && newHeight == p.height) {
                updateSize(p);
                //不能直接break，需要更新上面的父节点
                continue;
            }
            p.height = newHeight;
            int d = getHeight(p.left) - getHeight(p.right);
            if (Math.abs(d) <= 1) {
                //不能直接break，需要更新上面的父节点
                updateSize(p);
                continue;
            } else {
                if (d == 2) {
                    if (compare(key, (T) p.left.val) < 0) {
                        p = rotateRight(p);
                    } else {
                        p = firstLeftThenRight(p);
                    }
                } else {
                    if (compare(key, (T) p.right.val) > 0) {
                        p = rotateLeft(p);
                    } else {
                        p = firstRightThenLeft(p);
                    }
                }
                if (!stack.isEmpty()) {
                    if (compare(key, (T) stack.peek().val) < 0) {
                        stack.peek().left = p;
                    } else {
                        stack.peek().right = p;
                    }
                }
            }
        }
        root = p;
    }

    public boolean remove(T key) {
        Node entry = getEntry(key);
        if (entry == null) {
            return false;
        }
        if (entry.cnt > 1) {
            getEntryForRemove(key);
            entry.cnt--;
        } else {
            root = deleteEntry(root, key);
        }
        return true;
    }

    public Node fixAfterDeletion(Node p) {
        if (p == null) {
            return null;
        } else {
            p.height = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
            int d = getHeight(p.left) - getHeight(p.right);
            if (d == 2) {
                if (getHeight(p.left.left) - getHeight(p.left.right) >= 0) {
                    p = rotateRight(p);
                } else {
                    p = firstLeftThenRight(p);
                }
            } else if (d == -2) {
                if (getHeight(p.right.right) - getHeight(p.right.left) >= 0) {
                    p = rotateLeft(p);
                } else {
                    p = firstRightThenLeft(p);
                }
            }
            return p;
        }
    }

    public Node nextNode(Node root, Node p) {
        if (p == null) {
            return null;
        }
        if (getLastEntry(root) == p) {
            return null;
        }
        if (p.right != null) {
            return getFirstEntry(p.right);
        }
        Node parent = root;
        Node temp = root;
        while (parent != null) {
            if (parent == p) {
                break;
            } else if (compare((T) p.val, (T) parent.val) < 0) {
                temp = parent;
                parent = parent.left;
            } else {
                parent = parent.right;
            }
        }
        return temp;
    }

    public Node preNode(Node root, Node p) {
        if (p == null) {
            return null;
        }
        if (getFirstEntry(root) == p) {
            return null;
        }
        if (p.left != null) {
            return getLastEntry(p.left);
        }
        Node parent = root;
        Node temp = root;
        while (parent != null) {
            if (parent == p) {
                break;
            } else if (compare((T) p.val, (T) parent.val) > 0) {
                temp = parent;
                parent = parent.right;
            } else {
                parent = parent.left;
            }
        }
        return temp;
    }

    //<=k的最大
    public Node lowerEntry(T k) {
        Node p = root;
        Deque<Node> st = new ArrayDeque<>();
        while (p != null) {
            st.push(p);
            int compResult = compare(k, (T) p.val);
            if (compResult == 0) {
                return p;
            } else if (compResult < 0) {
                if (p.left == null) {
                    break;
                } else {
                    p = p.left;
                }
            } else {
                if (p.right == null) {
                    return p;
                } else {
                    p = p.right;
                }
            }
        }
        if (st.size() == 1) {
            return st.pop();
        } else {
            p = st.pop();
            Node parent = st.pop();
            Node ch = p;
            while (parent != null && ch == parent.left) {
                ch = parent;
                parent = st.isEmpty() ? null : st.pop();
            }
            return parent;
        }
    }

    //<k的最大
    public Node lessEntry(T k) {
        Node p = root;
        Deque<Node> st = new ArrayDeque<>();
        while (p != null) {
            st.push(p);
            int compResult = compare(k, (T) p.val);
            if (compResult == 0) {
                if (p.left != null) {
                    return getLastEntry(p.left);
                } else {
                    break;
                }
            } else if (compResult < 0) {
                if (p.left == null) {
                    break;
                } else {
                    p = p.left;
                }
            } else {
                if (p.right == null) {
                    return p;
                } else {
                    p = p.right;
                }
            }
        }
        if (st.size() == 1) {
            return st.pop();
        } else {
            p = st.pop();
            Node parent = st.pop();
            Node ch = p;
            while (parent != null && ch == parent.left) {
                ch = parent;
                parent = st.isEmpty() ? null : st.pop();
            }
            return parent;
        }
    }

    // >= k 的最小
    public Node higherEntry(T k) {
        Node p = root;
        Deque<Node> st = new ArrayDeque<>();
        while (p != null) {
            st.push(p);
            int compResult = compare(k, (T) p.val);
            if (compResult == 0) {
                return p;
            } else if (compResult < 0) {
                if (p.left == null) {
                    return p;
                } else {
                    p = p.left;
                }
            } else {
                if (p.right == null) {
                    break;
                } else {
                    p = p.right;
                }
            }
        }
        if (st.size() == 1) {
            return st.pop();
        } else {
            p = st.pop();
            Node parent = st.pop();
            Node ch = p;
            while (parent != null && ch == parent.right) {
                ch = parent;
                parent = st.isEmpty() ? null : st.pop();
            }
            return parent;
        }
    }

    // > k 的最小
    public Node greaterEntry(T k) {
        Node p = root;
        Deque<Node> st = new ArrayDeque<>();
        while (p != null) {
            st.push(p);
            int compResult = compare(k, (T) p.val);
            if (compResult == 0) {
                if (p.right != null) {
                    return getFirstEntry(p.right);
                } else {
                    break;
                }
            } else if (compResult < 0) {
                if (p.left == null) {
                    return p;
                } else {
                    p = p.left;
                }
            } else {
                if (p.right == null) {
                    break;
                } else {
                    p = p.right;
                }
            }
        }
        if (st.size() == 1) {
            return st.pop();
        } else {
            p = st.pop();
            Node parent = st.pop();
            Node ch = p;
            while (parent != null && ch == parent.right) {
                ch = parent;
                parent = st.isEmpty() ? null : st.pop();
            }
            return parent;
        }
    }

    public int getLeftSize(Node p) {
        if (p == null || p.left == null) {
            return 0;
        } else {
            return p.left.size;
        }
    }
    
    public void inOrder(Node p) {
        if (p != null) {
            size++;
            inOrder(p.left);
            inOrder(p.right);
        }
    }

    public int findRank(T key) {
        int ans = 0;
        Node p = root;
        while (p != null) {
            int compareResult = compare(key, (T) p.val);
            if (compareResult == 0) {
                break;
            } else if (compareResult < 0) {
                p = p.left;
            } else {
                ans += p.cnt + getLeftSize(p);
                p = p.right;
            }
        }
        ans += getLeftSize(p);
        return ans + 1;
    }

    public Node findkth(int k) {
        Node u = root;
        while (u != null) {
            if (k <= getLeftSize(u))
                u = u.left;
            else if (k <= u.cnt + getLeftSize(u))
                return u;
            else {
                k -= (getLeftSize(u) + u.cnt);
                u = u.right;
            }
        }
        return null;
    }
}