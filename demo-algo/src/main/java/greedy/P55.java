package greedy;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class P55 {
    public boolean canJump(int[] nums) {
        if (nums.length == 0) return true;
        int end = nums.length - 1;
        int cover = 0;
        for (int i = 0; i <= cover; i++) {
            cover = Math.max(cover, i + nums[i]);
            if (cover >= end) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new P55().canJump(new int[]{2, 3, 1, 1, 4}));
    }
}
