import java.util.Arrays;
import java.util.Collections;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class P238 {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] suffix = new int[n];
        suffix[n-1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i+1] * nums[i+1];
        }

        int pre = 1;
        for (int i = 0; i < n - 1; i++) {
            suffix[i] = pre * suffix[i];
            pre *= nums[i];
        }
        return suffix;
    }

    public static void main(String[] args) {
        new P238().productExceptSelf(new int[]{1,2,3,4});
    }

}
