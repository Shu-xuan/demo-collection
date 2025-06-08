package greedy;

/**
 * <p>
 * 描述: 买股票
 * </p>
 *
 * @Author huhongyuan
 */
public class P121 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int minPrice = prices[0];
        int g0 = 0;
        int g1 = 0;
        for (int i = 1; i < n; i++) {
            if (minPrice >= prices[i]) {
                minPrice = prices[i];
                g1 = g0;
                g0 = g1;
            } else {
                g1 = Math.max(prices[i] - minPrice, g0);
                g0 = g1;
            }
        }
        return g1;
    }

    public static void main(String[] args) {
        System.out.println(new P121().maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }
}
