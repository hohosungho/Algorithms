/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int findTilt(TreeNode root) {
        if(root == null)
            return 0;
        
        int left_diff = findTilt(root.left);
        int right_diff = findTilt(root.right);
        int diff = 0;
        int sum = 0;
                
        if(root.left != null && root.right != null){
            sum = root.right.val + root.left.val + root.val;
            diff = Math.abs(root.left.val-root.right.val);
        } else if(root.left != null){
            diff = Math.abs(root.left.val);
            sum = root.left.val + root.val;
        } else if(root.right != null){
            diff = Math.abs(root.right.val);
            sum = root.right.val + root.val;
        } else {
            sum = root.val;
        }
        
        root.val = sum;
        
        return left_diff + right_diff + diff;
    }
}
