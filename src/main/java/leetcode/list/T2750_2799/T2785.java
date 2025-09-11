package leetcode.list.T2750_2799;

import java.util.*;

public class T2785 {
    public static void main(String[] args) {
        T2785 solution = new T2785();
        System.out.println((int) 'a');
        System.out.println((int) 'A');
        System.out.println(solution.sortVowels("lEetcOde"));
    }

    public String sortVowels(String s) {
        char[] chars = s.toCharArray();
        char[] res = new char[chars.length];
        Arrays.fill(res, '#');
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');

        List<Character> consonants = new LinkedList<>();

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (set.contains(c)) consonants.add(c);
            else res[i] = chars[i];
        }

        consonants.sort(Comparator.comparingInt(o -> o));

        for (int i = 0; i < res.length; i++) {
            if (res[i] == '#') {
                res[i] = consonants.get(0);
                consonants.remove(0);
            }
        }

        return new String(res);
    }
}
