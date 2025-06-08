/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class P141 {
    private static class ListNode {
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

    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        while (true) {
            if (head == null || head.next == null) {
                return false;
            }
            head = head.next;
            fast = fast.next.next;
            if (head == fast) {
                return true;
            }
        }
    }

    public static void main(String[] args) {
        new P141().hasCycle(new ListNode(1, null));
    }
}
