package leetcode.list.T450_499;

public class T466 {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        // 循环节--用s2去匹配s1，判断多少个s1可以得到多少个完整的s2
        int[] dp = new int[n1 + 1];  // dp数组表示前n个s1可以获得多少个s2
        int i = 0, len = s2.length();
        int ans = 0;  // 匹配的s2的个数
        int cnt = 1;  // 当前第几个s1
        while(cnt <= n1) {
            for(int j = 0; j < s1.length(); j++) {
                if(s1.charAt(j) == s2.charAt(i)) {
                    i = (i + 1) % len;
                    if(i == 0) ans++;
                }
            }
            dp[cnt] = ans;
            // 获得一个循环节，前cnt个s1正好可以获得ans个s2，不用继续暴力判断，直接得出答案
            if(i == 0) break;
            cnt++;
        }
        // 每cnt个s1获得ans个s2，剩余(n1%cnt)个s1，再利用dp数组可以得到这些s1可以获得多少个s2
        return (n1 / cnt * ans + dp[n1 % cnt]) / n2;
    }

    /*
        循环节 3种解题
使用n1和n2将s1和s2都展开，本题的目的是找到s1中能匹配多少个s2。因为s1和s2都是循环拼接而成的字符串，所以一定能找到规律--比如说2个s1匹配3个s2，或者1个s1匹配2个s2等等

这里的思路就可以分成两种：找一个循环节当中多少个s1匹配完整的多少个s2，或者找多少个s2匹配完整的多少个s1。循环节就是上面我说的规律

可以暴力解题，即遍历s1，找它里面有多少个s2，但是会超时。可以使用数组存储匹配的比率，然后提前结束循环。或者使用哈希存储匹配的s2循环节字符对应的多少个s1和s2

注意：循环节中每一个 s1 可以匹配 0 个 s2。在使用s1匹配s2时，鸽笼原理指的是每过一个s1，对应匹配到的s2的index只有|s2|种可能：0-|s2-1|，所以经过|s2|+1个s1，这个s1结束时匹配到的index必然和前面某个s1结束时匹配到的index相同。进一步，只要index“相同”就能找到循环节

暴力优化

class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        // 循环节--用s2去匹配s1，判断多少个s1可以得到多少个完整的s2
        int[] dp = new int[n1 + 1];  // dp数组表示前n个s1可以获得多少个s2
        int i = 0, len = s2.length();
        int ans = 0;  // 匹配的s2的个数
        int cnt = 1;  // 当前第几个s1
        while(cnt <= n1) {
            for(int j = 0; j < s1.length(); j++) {
                if(s1.charAt(j) == s2.charAt(i)) {
                    i = (i + 1) % len;
                    if(i == 0) ans++;
                }
            }
            dp[cnt] = ans;
            // 获得一个循环节，前cnt个s1正好可以获得ans个s2，不用继续暴力判断，直接得出答案
            if(i == 0) break;
            cnt++;
        }
        // 每cnt个s1获得ans个s2，剩余(n1%cnt)个s1，再利用dp数组可以得到这些s1可以获得多少个s2
        return (n1 / cnt * ans + dp[n1 % cnt]) / n2;
    }
}
转换思路

class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int len = s2.length();
        int[] dp = new int[len];  // dp[i]表示从s2的i字符开始匹配s1，可以匹配多少个字符
        for(int i = 0; i < len; i++){
            int p = i;
            for(int j = 0; j < s1.length(); j++){
                if(s1.charAt(j) == s2.charAt(p)){
                    p = (p + 1) % len;
                    dp[i]++;
                }
            }
        }
        // 统计从第1个s1开始，一直到第n1个s1，可以一共匹配s2中的多少个字符
        int sum = 0, idx = 0;
        for(int i = 0; i < n1; i++){
            sum += dp[idx];
            idx = (idx + dp[idx]) % len;
        }
        return sum / len / n2;
    }
}
哈希快速找循环节

class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        // 使用s1去匹配s2，能更快地得到循环节
        if (n1 == 0)
            return 0;
        int s1cnt = 0, index = 0, s2cnt = 0;
        // recall 是我们用来找循环节的变量，它是一个哈希映射
        // 我们如何找循环节？假设我们遍历了 s1cnt 个 s1，此时匹配到了第 s2cnt 个 s2 中的第 index 个字符
        // 如果我们之前遍历了 s1cnt' 个 s1 时，匹配到的是第 s2cnt' 个 s2 中同样的第 index 个字符，那么就有循环节了
        // 我们用 (s1cnt', s2cnt', index) 和 (s1cnt, s2cnt, index) 表示两次包含相同 index 的匹配结果
        // 那么哈希映射中的键就是 index，值就是 (s1cnt', s2cnt') 这个二元组
        // 循环节就是；
        //    - 前 s1cnt' 个 s1 包含了 s2cnt' 个 s2
        //    - 以后的每 (s1cnt - s1cnt') 个 s1 包含了 (s2cnt - s2cnt') 个 s2
        // 那么还会剩下 (n1 - s1cnt') % (s1cnt - s1cnt') 个 s1, 我们对这些与 s2 进行暴力匹配
        // 注意 s2 要从第 index 个字符开始匹配
        Map<Integer, int[]> recall = new HashMap<Integer, int[]>();
        int[] preLoop = new int[2];
        int[] inLoop = new int[2];
        while (true) {
            // 我们多遍历一个 s1，看看能不能找到循环节
            ++s1cnt;
            for (int i = 0; i < s1.length(); ++i) {
                char ch = s1.charAt(i);
                if (ch == s2.charAt(index)) {
                    index += 1;
                    if (index == s2.length()) {
                        ++s2cnt;
                        index = 0;
                    }
                }
            }
            // 还没有找到循环节，所有的 s1 就用完了
            if (s1cnt == n1) {
                return s2cnt / n2;
            }
            // 出现了之前的 index，表示找到了循环节
            if (recall.containsKey(index)) {
                int[] value = recall.get(index);
                int s1cntPrime = value[0];
                int s2cntPrime = value[1];
                // 前 s1cnt' 个 s1 包含了 s2cnt' 个 s2
                preLoop = new int[]{s1cntPrime, s2cntPrime};
                // 以后的每 (s1cnt - s1cnt') 个 s1 包含了 (s2cnt - s2cnt') 个 s2
                inLoop = new int[]{s1cnt - s1cntPrime, s2cnt - s2cntPrime};
                break;
            } else {
                recall.put(index, new int[]{s1cnt, s2cnt});
            }
        }
        // ans 存储的是 S1 包含的 s2 的数量，考虑的之前的 preLoop 和 inLoop
        int ans = preLoop[1] + (n1 - preLoop[0]) / inLoop[0] * inLoop[1];
        // S1 的末尾还剩下一些 s1，我们暴力进行匹配
        int rest = (n1 - preLoop[0]) % inLoop[0];
        for (int i = 0; i < rest; ++i) {
            for (int j = 0; j < s1.length(); ++j) {
                char ch = s1.charAt(j);
                if (ch == s2.charAt(index)) {
                    ++index;
                    if (index == s2.length()) {
                        ++ans;
                        index = 0;
                    }
                }
            }
        }
        // S1 包含 ans 个 s2，那么就包含 ans / n2 个 S2
        return ans / n2;
    }
}
     */
}
