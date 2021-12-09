class Solution {
    public boolean canReach(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        Queue<Integer> queue = new LinkedList<>();
        
        queue.offer(start);
        visited[start] = true;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int idx = queue.poll();
                int move = arr[idx];
                if(move == 0)
                    return true;
                if(idx + move < arr.length && !visited[idx+move]){
                    queue.offer(idx+move);
                    visited[idx+move]=true;
                }
                if(idx - move >= 0 && !visited[idx-move]){
                    queue.offer(idx-move);
                    visited[idx-move]=true;
                }
                
            }
        }
        return false;
    }
}
