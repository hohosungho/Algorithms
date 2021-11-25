class Solution {
    public int maxSubArray(int[] nums) {
        int[] arr = new int[nums.length];
        int max = 0;
        for(int i = 0; i < nums.length; i++ ){
            if(i==0){
                arr[i]=nums[i];
                max=arr[i];
            }
            else{
                arr[i]=Math.max(nums[i],nums[i]+arr[i-1]);
                max = Math.max(arr[i],max);
            }
        }
        return max;
    }
}
