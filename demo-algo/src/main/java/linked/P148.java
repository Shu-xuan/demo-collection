package linked;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class P148 {
    static class ListNode {
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

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode l3 = new ListNode(3, null);
        ListNode l2 = new ListNode(1, l3);
        ListNode l1 = new ListNode(2, l2);
        ListNode head = new ListNode(4, l1);
        ListNode ans = new P148().sortList(head);
        while (ans != null) {
            System.out.println(ans.val);
            ans = ans.next;
        }
    }
}
