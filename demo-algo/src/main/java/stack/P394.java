package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class P394 {
    static class Node {
        public Integer multi;
        public String str;

        public Node(Integer multi, String str) {
            this.multi = multi;
            this.str = str;
        }
    }

    public String decodeString(String s) {
        char[] chars = s.toCharArray();
        Deque<Node> stk = new ArrayDeque<>();
        int multi = 0;
        StringBuilder res = new StringBuilder();
        for (Character aChar : chars) {
            if (Character.isDigit(aChar)) {
                multi = multi * 10 + aChar - '0';
            } else if (aChar == '[') {
                stk.addFirst(new Node(multi, res.toString()));
                multi = 0;
                res = new StringBuilder();
            } else if (aChar == ']') {
                StringBuilder tmp = new StringBuilder();
                Node node = stk.removeFirst();
                int n = node.multi;
                for (int i = 0; i < n; i++) {
                    tmp.append(res);
                }
                res = new StringBuilder();
                res.append(node.str);
                res.append(tmp);
            } else {
                res.append(aChar);
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(new P394().decodeString("100[leetcode]"));
    }
}
