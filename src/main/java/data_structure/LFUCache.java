package ds;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCache {
    // k 是键, v 是频率
    Map<Integer, Integer> k2Freq = new HashMap<>();
    // k 是键, v 是值
    Map<Integer, Integer> k2v = new HashMap<>();
    // 频率到 k 的映射, 主要是为了根据最小的频率找到 k
    Map<Integer, LinkedHashSet<Integer>> freq2ks = new HashMap<>();

    int minFreq;
    int cap;

    public LFUCache(int capacity) {
        this.cap = capacity;
    }

    public int get(int key) {
        Integer v = k2v.get(key);
        if (v == null) return -1;
        increaseFreq(key);
        return v;
    }

    void increaseFreq(int key) {
        int freq = k2Freq.get(key);
        k2Freq.put(key, freq + 1);
        freq2ks.get(freq).remove(key);
        freq2ks.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freq2ks.get(freq + 1).add(key);
        if (freq2ks.get(freq).isEmpty()) {
            freq2ks.remove(freq);
            if (freq == this.minFreq) {
                this.minFreq++;
            }
        }
    }

    void removeMinFreqKey() {
        LinkedHashSet<Integer> keyList = freq2ks.get(this.minFreq);
        int deletedKey = keyList.iterator().next();
        keyList.remove(deletedKey);
        if (keyList.isEmpty()) {
            freq2ks.remove(this.minFreq);
        }
        k2v.remove(deletedKey);
        k2Freq.remove(deletedKey);
    }

    public void put(int key, int value) {
        Integer v = k2v.get(key);
        if (v != null) {
            // k 已经存在
            k2v.put(key, value);
            increaseFreq(key);
        } else {
            // k 不存在
            if (k2v.size() == cap) {
                // 容量已满
                removeMinFreqKey();
            }
            // 容量未满
            k2v.put(key, value);
            k2Freq.put(key, 1);
            freq2ks.putIfAbsent(1, new LinkedHashSet<>());
            freq2ks.get(1).add(key);
            this.minFreq = 1;
        }
    }
}
