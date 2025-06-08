package dp;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class P70 {
    private Map<Integer, Integer> memo = new HashMap<>();
    public int climbStairs(int n) {
        int f0 = 1;
        int f1 = 1;
        for (int i = 0; i < n - 1; i++) {
            int newV = f0 + f1;
            f0 = f1;
            f1 = newV;
        }
        return f1;
    }


    public static void main(String[] args) {
        System.out.println(new P70().climbStairs(10));
    }
}
