import java.util.*;

class Solution {
    public class Node{
        public int val;
        public Node next;
        public Node prev;
        
        public Node(int v){
            this.val = v;
            this.next = null;
            this.prev = null;
        }
    }
    
    public String solution(int n, int k, String[] cmd) {
        StringBuilder sb = new StringBuilder();
        boolean[] erased = new boolean[n];
        Stack<Node> stack = new Stack<>();
        Node[] nodeMap = new Node[n];
        Node head = new Node(0);
        Node curr = head;
        nodeMap[0] = curr;
        for(int i = 1; i < n; i++){
            Node node = new Node(i);
            nodeMap[i] = node;
            curr.next = node;
            node.prev = curr;
            curr = curr.next;
        }
        curr = nodeMap[k];
        for(String command : cmd){
            String[] parse = command.split(" ");
            String operation = parse[0];
            if(operation.equals("D")){
                int move = Integer.parseInt(parse[1]);
                for(int i = 0; i < move; i++) curr = curr.next;
            } else if (operation.equals("U")){
                int move = Integer.parseInt(parse[1]);
                for(int i = 0; i < move; i++) curr = curr.prev;
            } else if (operation.equals("C")){
                erased[curr.val] = true;
                stack.push(curr);
                if(curr.next == null){
                    curr = curr.prev;
                    curr.next = null;
                } else if(curr.prev == null){
                    curr = curr.next;
                    curr.prev = null;
                } else {
                    Node prev = curr.prev;
                    Node next = curr.next;
                    prev.next = next;
                    next.prev = prev;
                    curr = curr.next;
                }
            } else {
                Node restored = stack.pop();
                erased[restored.val] = false;
                int prev = restored.val-1;
                int next = restored.val+1;
                
                while(prev > -1 && erased[prev]) prev--;
                while(next < n && erased[next]) next++;
                if(prev <= -1){
                    restored.prev = null;
                    restored.next = nodeMap[next];
                    nodeMap[next].prev = restored;
                } else if(next >= n){
                    restored.next = null;
                    restored.prev = nodeMap[prev];
                    nodeMap[prev].next = restored;
                } else {
                    restored.next = nodeMap[next];
                    restored.prev = nodeMap[prev];
                    nodeMap[next].prev = restored;
                    nodeMap[prev].next = restored;
                }
            }
        }
        for(int i = 0; i < n; i++){
            sb.append(erased[i]?"X":"O");
        }
        return sb.toString();
    }
}
