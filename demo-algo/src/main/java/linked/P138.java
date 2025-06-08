package linked;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class P138 {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap();
        Node dummy = head;
        while (dummy != null) {
            Node node = new Node(dummy.val);
            map.put(dummy, node);
            dummy = dummy.next;
        }
        Node ans = map.get(head);
        while (head != null) {
            Node node = map.get(head);
            node.next = map.get(head.next);
            node.random = map.get(head.random);
            head = head.next;
        }
        return ans;
    }

    public static void main(String[] args) {
//        new P138().copyRandomList()
    }
}
