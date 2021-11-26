/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null)
            return null;
        int length = 0;
        ListNode cur = head;
        while(cur.next!=null){
            length++;
            cur=cur.next;
        }
        length++;
        cur.next = head;
        
        int rotate = k % length;
        ListNode dummy = new ListNode();
        dummy.next = head;
        for(int i = 0; i < length-rotate; i++ ){
            dummy = dummy.next;
        }
        cur = dummy.next;
        dummy.next = null;
        return cur;
    }
}
