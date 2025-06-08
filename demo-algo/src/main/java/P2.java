/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class P2 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    private ListNode doAdd(ListNode l1, ListNode l2, int toAdd) {
        if (l1 == null && l2 == null && toAdd == 0) {
            return null;
        }
        int sum = toAdd;
        if (l1 != null) {
            sum += l1.val;
            l1 = l1.next;
        }
        if (l2 != null) {
            sum += l2.val;
            l2 = l2.next;
        }
        toAdd = sum / 10;

        ListNode res = new ListNode(sum % 10);
        res.next = doAdd(l1, l2, toAdd);

        return res;
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return doAdd(l1, l2, 0);
    }

    public ListNode addTwoNumbersBYFOOl(ListNode l1, ListNode l2) {
        int nextAdd = 0;
        ListNode dummy = new ListNode();
        ListNode cur = dummy;

        while (l1 != null || l2 != null) {
            ListNode zeroNode = null;
            ListNode newNode = null;
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + nextAdd;
            nextAdd = 0;
            if (sum > 10) {
                nextAdd = 1;
                int rm = sum % 10;
                newNode = new ListNode();
                newNode.val = rm;
            } else if (sum == 10) {
                nextAdd = 1;
                zeroNode = new ListNode();
                zeroNode.val = 0;
            } else {
                int rm = sum % 10;
                newNode = new ListNode();
                newNode.val = rm;
            }
            cur.next = newNode == null ? zeroNode : newNode;
            cur = cur.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (nextAdd == 1) {
            ListNode node = new ListNode();
            node.val = 1;
            cur.next = node;
        }
        return dummy.next;
    }
    public static void main(String[] args) {
    // 创建 P2 实例
    P2 solution = new P2();

    // 构造 l1 = [9,9,9,9,9,9,9]
    ListNode l1 = solution.new ListNode(9);
    l1.next = solution.new ListNode(9);
    l1.next.next = solution.new ListNode(9);
    l1.next.next.next = solution.new ListNode(9);
    l1.next.next.next.next = solution.new ListNode(9);
    l1.next.next.next.next.next = solution.new ListNode(9);
    l1.next.next.next.next.next.next = solution.new ListNode(9);

    // 构造 l2 = [9,9,9,9]
    ListNode l2 = solution.new ListNode(9);
    l2.next = solution.new ListNode(9);
    l2.next.next = solution.new ListNode(9);
    l2.next.next.next = solution.new ListNode(9);

    // 调用 addTwoNumbers 方法
    ListNode result = solution.addTwoNumbers(l1, l2);

    // 打印结果
    while (result != null) {
        System.out.print(result.val + " ");
        result = result.next;
    }
}

}
