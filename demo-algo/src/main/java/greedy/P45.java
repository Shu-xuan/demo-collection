package greedy;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class P45 {
    public int jump(int[] nums) {
        int ans = 0;
        if (nums.length == 1) return ans;
        int curPath = 0;
        int nextPath = 0;
        for (int i = 0; i < nums.length; i++) {
            nextPath = Math.max(nextPath, i + nums[i]);
            if (i == curPath) {
                ++ans;
                curPath = nextPath;
                if (curPath >= nums.length - 1) break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new P45().jump(new int[]{2, 3, 1, 1, 4}));
    }
}
