package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class P118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        int[] dp = new int[numRows];
        dp[0] = 1;
        tmp.add(1);
        ans.add(tmp);
        if (numRows == 1) return ans;
        tmp = new ArrayList<>();
        dp[1] = 1;
        tmp.add(1);
        tmp.add(1);
        ans.add(tmp);
        if (numRows == 2) return ans;

        for (int i = 2; i < numRows; i++) {
            tmp = new ArrayList<>();
            tmp.add(1);
            int[] temp = Arrays.copyOf(dp, dp.length);
            for (int j = 1; j <= i; j++) {
                dp[j] = temp[j-1] + temp[j];
                tmp.add(dp[j]);
            }
            ans.add(tmp);
        }
        return ans;
    }

    public static void main(String[] args) {
        new P118().generate(5);
    }
}
