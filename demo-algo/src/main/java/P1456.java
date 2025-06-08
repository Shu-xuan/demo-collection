/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class P1456 {
    public int maxVowels(String s, int k) {
        int ans = 0;
        for (int i = 0; i < k; i++) {
            ans += yuanyin(s.charAt(i));
        }
        int max = ans;
        for (int i = k; i < s.length(); i++) {
            if (yuanyin(s.charAt(i - k)) == 1) {
                ans--;
            }
            ans += yuanyin(s.charAt(i));
            max = Math.max(ans, max);
            System.out.println(max);
        }
        return max;
    }

    private int yuanyin(char at) {
        String yuan = "aeiou";
        return yuan.contains(String.valueOf(at)) ? 1 : 0;
    }

    public static void main(String[] args) {
        System.out.println(new P1456().maxVowels("tryhard", 4));
    }
}
