import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class P438 {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        int left = 0; // 最左字母的索引
        for (int i = 0; i < s.length(); i++) {
            if (builder.toString().length() < p.length()) {
                builder.append(s.charAt(i));
                if (i < p.length() - 1) continue;
            }
            if (contain(builder.toString(), p)) {
                left = i - p.length() + 1;
                ans.add(left);
            }
            builder.deleteCharAt(0);
        }
        return ans;
    }

    private boolean contain(String s, String p) {
        // 数量一致
        int[] slen = new int[26];
        int[] plen = new int[26];
        for (char c : s.toCharArray()) {
            slen[c-'a'] ++;
        }
        for (char c : p.toCharArray()) {
            plen[c-'a'] ++;
        }
        for (char c : p.toCharArray()) {
            if (plen[c - 'a'] != slen[c - 'a']) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new P438().findAnagrams("baa", "aa").forEach(System.out::println);
    }
}
