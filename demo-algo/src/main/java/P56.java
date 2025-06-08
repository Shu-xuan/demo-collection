import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class P56 {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        List<int[]> ans = new ArrayList<>();
        for (int[] interval : intervals) {
            int left = ans.size();
            if (left > 0 && ans.get(left-1)[1] >= interval[0]) {
                ans.get(left - 1)[1] = interval[1];
            } else {
                ans.add(interval);
            }
        }

        return ans.toArray(new int[intervals.length][2]);
    }

    public static void main(String[] args) {
        new P56().merge(new int[][]{{1,3},{2,6},{8,10},{15,18}});
    }
}
