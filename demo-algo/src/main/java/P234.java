/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class P234 {
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

    public boolean isPalindrome(ListNode head) {
        ListNode middle = head;
        middle = domiddle(middle);

        ListNode reversed = middle;
        reversed = reverseList(reversed);

        while (head != null && reversed != null) {
            if (head.val != reversed.val) {
                return false;
            }
            head= head.next;
            reversed = reversed.next;
        }
        return true;
    }

    private ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode reversed = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return reversed;
    }

    private ListNode domiddle(ListNode head) {
        ListNode middle = head;
        while (head != null && head.next != null) {
            middle = middle.next;
            head = head.next.next;
        }
        return middle;
    }

    public static void main(String[] args) {
        ListNode tail = new ListNode(1, null);
        ListNode n2 = new ListNode(2, tail);
        ListNode n1 = new ListNode(2, n2);
        ListNode head = new ListNode(1, n1);

        System.out.println(new P234().isPalindrome(head));
    }
}
