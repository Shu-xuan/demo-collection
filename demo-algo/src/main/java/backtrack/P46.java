package backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class P46 {

    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        backTracking(nums, new int[nums.length]);
        return ans;
    }

    private void backTracking(int[] nums, int[] used) {
        if (path.size() == nums.length) {
            List<Integer> tmp = new ArrayList<>(path);
            ans.add(tmp);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == 1) continue;
            path.add(nums[i]);
            used[i] = 1;
            backTracking(nums, used);
            used[i] = 0;
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        new P46().permute(new int[]{1,2,3});
    }
}
