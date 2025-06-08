package backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class P17 {
    public static final Map<Character, String> alpha = new HashMap<>(8);
    static {
        alpha.put('2', "abc");
        alpha.put('3', "def");
        alpha.put('4', "ghi");
        alpha.put('5', "jkl");
        alpha.put('6', "mno");
        alpha.put('7', "pqrs");
        alpha.put('8', "tuv");
        alpha.put('9', "wxyz");
    }

    List<String> ans = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        int n = digits.length();
        if (n == 0 || digits == null) {
            return ans;
        }
        backTracking(digits, 0);
        return ans;
    }

    private StringBuilder builder = new StringBuilder();
    private void backTracking(String digits, int index) {
        if (index >= digits.length()) {
            ans.add(builder.toString());
            return;
        }
        String arr = alpha.get(digits.charAt(index));
        for (int i = 0; i < arr.length(); i++) {
            builder.append(arr.charAt(i));
            backTracking(digits, index + 1);
            builder.deleteCharAt(builder.length() - 1);
        }
    }

    public static void main(String[] args) {
        new P17().letterCombinations("23");
    }
}
