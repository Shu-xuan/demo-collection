package skill;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class P169 {
    public int majorityElement(int[] nums) {
        int ans = nums[0];
        int cnt = 1;
        for (int i = 1; i < nums.length; i++) {
            if (cnt > 0) {
                if (ans == nums[i]) {
                    cnt++;
                } else {
                    cnt--;
                }
            } else {
                ans = nums[i];
                cnt = 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new P169().majorityElement(new int[]{6, 5, 5}));
    }
}
