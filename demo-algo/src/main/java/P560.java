import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class P560 {
    public int subarraySum(int[] nums, int k) {
        int ans = 0;
        int n = nums.length;
        int[] prefix = new int[n + 1];
        Map<Integer, Integer> cnt = new HashMap<>(n + 1);
        prefix[0] = 0;
//        for (int i = 0; i < nums.length; i++) {
//            prefix[i + 1] = prefix[i] + nums[i];
//        }
//        for (int num : prefix) {
//            ans += cnt.getOrDefault(num - k, 0);
//            cnt.merge(num, 1, Integer::sum);
//        }

        for (int i = 0; i < prefix.length; i++) {
            if (i == 0) {
                prefix[0] = 0;
            } else {
                prefix[i] = prefix[i - 1] + nums[i - 1];
            }
            ans += cnt.getOrDefault(prefix[i] - k, 0);
            cnt.merge(prefix[i], 1, Integer::sum);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new P560().subarraySum(new int[]{1, 1, 1}, 2));
    }
}
