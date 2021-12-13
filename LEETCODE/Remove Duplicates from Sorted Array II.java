class Solution {
    public int removeDuplicates(int[] nums) {
        int curr = nums[0];
        int k = 0;
        for(int i = 0; i < nums.length; i++){
            int cons = 0;
            while(i < nums.length && curr == nums[i]){
                i++;
                cons++;
            }
            if(cons >= 2)
                for(int j = 0; j < 2; j++) nums[k++] = curr;
            else
                nums[k++] = curr;
            
            if(i == nums.length)
                break;
            
            curr = nums[i];
            i--;
        }
        return k;
    }
}
