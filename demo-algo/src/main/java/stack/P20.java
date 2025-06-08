package stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class P20 {
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        char[] chars = s.toCharArray();
        Deque<Character> stk = new ArrayDeque();
        Map<Character, Character> mp = new HashMap<>();
        mp.put(')', '(');
        mp.put(']', '[');
        mp.put('}', '{');

        for (char c : chars) {
            if (!mp.containsKey(c)) {
                stk.addFirst(c);
            } else {
                if (stk.size() < 1) {
                    return false;
                }
                if (!mp.get(c).equals(stk.removeFirst())) {
                    return false;
                }
            }
        }
        if (stk.size() > 0) {
            return false;
        }
        return true;

    }

    public static void main(String[] args) {
        new P20().isValid("){");
    }
}
