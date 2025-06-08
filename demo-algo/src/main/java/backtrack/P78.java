package backtrack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class P78 {
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> sub = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        ans.add(Collections.emptyList());
        backTracking(nums, 0);
        return ans;
    }

    private void backTracking(int[] nums, int startIdx) {
        if (startIdx >= nums.length) {
            return;
        }
        for (int i = startIdx; i < nums.length; i++) {
            sub.add(nums[i]);
            ans.add(new ArrayList<>(sub));
            backTracking(nums, i + 1);
            sub.remove(sub.size() - 1);
        }
    }
}
