package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class P94 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stk = new ArrayDeque();
        TreeNode cur = root;
        while (cur != null || stk.size() > 0) {
            while (cur != null) {
                stk.addFirst(cur);
                cur = cur.left;
            }
            if (stk.peekFirst() != null) {
                cur = stk.removeFirst();
                ans.add(cur.val);
                cur = cur.right;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode l0 = new TreeNode(3);
        TreeNode r0 = new TreeNode(2, l0, null);
        TreeNode root = new TreeNode(1, null, r0);
        for (Integer integer : new P94().inorderTraversal(root)) {
            System.out.println(integer);
        }
    }
}
