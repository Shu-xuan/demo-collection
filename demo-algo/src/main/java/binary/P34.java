package binary;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class P34 {
    public int[] searchRange(int[] nums, int target) {
        int firstIdx = -1, lastIdx = -1;
        if (nums.length == 0 || nums[0] > target) return new int[]{firstIdx, lastIdx};
        firstIdx = lowerBound(nums, target);
        if (nums[firstIdx] != target || firstIdx == nums.length) return new int[]{-1,-1};
        lastIdx = lowerBound(nums, target + 1) - 1;

        return new int[]{firstIdx, lastIdx};
    }

    public int lowerBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        for (int num : new P34().searchRange(new int[]{1}, 1)) {
            System.out.println(num);
        }
    }
}
