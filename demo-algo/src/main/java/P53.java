/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class P53 {
    public int maxSubArray(int[] nums) {
        int ans = nums[0];
//        int[] dp = new int[nums.length];
//        dp[0] = nums[0];
//        for (int i = 1; i < nums.length; i++) {
//            if (dp[i - 1] < 0) {
//                dp[i] = nums[i];
//            } else {
//                dp[i] = dp[i-1] + nums[i];
//            }
//            ans = Math.max(ans, dp[i]);
//        }
        int tmp = 0;
        for (int i = 1; i < nums.length; i++) {
            tmp = Math.max(tmp, 0) + nums[i];
            ans = Math.max(ans, tmp);
        }
            return ans;
    }

    public static void main(String[] args) {
        System.out.println(new P53().maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
}
