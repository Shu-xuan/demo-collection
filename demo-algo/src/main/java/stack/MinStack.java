package stack;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
class MinStack {
    Integer min;

    static class Node {
        private Integer value;
        private Integer minBefore;

        public void setMinBefore(Integer minBefore) {
            this.minBefore = minBefore;
        }

        public Node(Integer value, Integer minBefore) {
            this.value = value;
            this.minBefore = minBefore;
        }
    }

    List<Node> stack;


    public MinStack() {
        stack = new ArrayList<>();
    }

    public void push(int val) {
        if (stack.size() == 0) {
            min = val;
            stack.add(new Node(val, val));
            return;
        }
        Node newNode = new Node(val, min);
        // 拿到栈顶
        Node top = stack.get(stack.size() - 1);
        if (top.value < min) {
            newNode.setMinBefore(top.value);
        }
        // 更新最小值
        min = min > newNode.value ? newNode.value : min;
        stack.add(newNode);

    }

    public void pop() {
        // 移除栈顶元素
        Node removed = stack.remove(stack.size() - 1);
        if (removed.value != null && removed.value.equals(min)) {
            min = getTop() == null ? null : Math.min(getTop().value, getTop().minBefore);
        }
    }

    public int top() {
        return getTop() == null ? null : getTop().value;
    }

    private Node getTop() {
        return stack.size() > 0 ? stack.get(stack.size() - 1) : null;
    }

    public Integer getMin() {
        return min;
    }

    public static void main(String[] args) {
//        test01();
//        test02();
    }

    private static void test01() {
        MinStack minStack = new MinStack();

        minStack.push(395);
        System.out.println(minStack.getMin()); // getMin
        System.out.println(minStack.top());    // top
        System.out.println(minStack.getMin()); // getMin
        minStack.push(276);
        minStack.push(29);
        System.out.println(minStack.getMin()); // getMin
        minStack.push(-482);
        System.out.println(minStack.getMin()); // getMin
        minStack.pop();
        minStack.push(-108);
        minStack.push(-251);
        System.out.println(minStack.getMin()); // getMin
        minStack.push(-439);
        System.out.println(minStack.top());    // top
        minStack.push(370);
        minStack.pop();
        minStack.pop();
        System.out.println(minStack.getMin()); // getMin
        minStack.pop();
        System.out.println(minStack.getMin()); // getMin
        System.out.println(minStack.getMin()); // getMin
        minStack.pop();
        System.out.println(minStack.getMin()); // getMin
    }

    private static void test02() {
        MinStack minStack = new MinStack();

        minStack.push(2147483646);
        minStack.push(2147483646);
        minStack.push(2147483647);
        System.out.println(minStack.top()); // 返回 2147483647
        minStack.pop();
        System.out.println(minStack.getMin()); // 返回 2147483646
        minStack.pop();
        System.out.println(minStack.getMin()); // 返回 2147483646
        minStack.pop();
        minStack.push(-2147483648);
        System.out.println(minStack.top()); // 返回 -2147483648
        System.out.println(minStack.getMin()); // 返回 -2147483648
        minStack.pop();
        System.out.println(minStack.getMin()); // 返回 null 或抛异常（栈空）
    }

}