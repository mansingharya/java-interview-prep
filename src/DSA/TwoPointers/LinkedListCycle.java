package DSA.TwoPointers;


// 3. https://leetcode.com/problems/linked-list-cycle/description/

class LinkedListCycle {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            next = null;
        }
    }

    static public boolean hasCycle(ListNode head) {

        if (head == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }

        }

        return false;
    }

    static void main(String[] args) {

        // 3 --> 2 --> 0 --> -4

        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);

        System.out.println();
        System.out.println("Cycle Detected?? " + hasCycle(head));



        // 3 --> 2 --> 0 --> -4 [ Cycle Start --> 2 --> 0 --> -4 Cycle Continue...]
        head.next.next.next.next = head.next;
        System.out.println();
        System.out.println("Cycle Detected?? " + hasCycle(head));

    }
}
