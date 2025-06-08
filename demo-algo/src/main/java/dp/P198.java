package dp;

import java.util.Map;

/**
 * <p>
 * 描述: 打家劫舍
 * </p>
 *
 * @Author huhongyuan
 */
public class P198 {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }

        int[] g = new int[n];
        g[0] = nums[0];
        g[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            g[i] = Math.max(g[i - 2] + nums[i], g[i - 1]);
        }
        return g[n-1];
    }
    public int 滚动数组(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int pre = nums[0];
        int ans = Math.max(pre, nums[1]);

        for (int i = 2; i < n; i++) {
            int newV = Math.max(ans, pre + nums[i]);
            pre = ans;
            ans = newV;
        }

        return ans;
    }



    public static void main(String[] args) {
        System.out.println(new P198().滚动数组(new int[]{2, 1, 1, 2}));
    }
}
