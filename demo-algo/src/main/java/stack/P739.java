package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class P739 {
    Deque<Integer> stk = new ArrayDeque<>();

    public int[] dailyTemperatures(int[] temperatures) {
        int[] ans = new int[temperatures.length];
        stk.addFirst(0);
        for (int i = 0; i < temperatures.length; i++) {
            while (stk.size() > 0 && temperatures[i] > temperatures[stk.peekFirst()]) {
                ans[stk.peekFirst()] = i - stk.removeFirst();
            }
            stk.addFirst(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        for (int i : new P739().dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})) {
            System.out.println(i);
        }
    }
}
