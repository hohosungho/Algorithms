class Solution {
    Set<List<Integer>> res = new HashSet<>();
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates, 0, target, new ArrayList<>());
        
        return new ArrayList<>(res);
    }
    
    private void dfs(int[] candidates, int j, int target, List<Integer> list){
        if( target == 0 ){
            res.add(new ArrayList<>(list));
            return;
        }
        
        if( target < 0 ){
            return;
        }
        
        for(int i = j; i < candidates.length; i++){
            //duplicate check
            if(i > j && candidates[i] == candidates[i-1]) continue;
            list.add(candidates[i]);
            dfs(candidates, i+1, target-candidates[i], list);
            list.remove((Integer) candidates[i]);  
        }
    }
}
