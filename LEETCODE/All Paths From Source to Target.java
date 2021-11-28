class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        boolean visited[] = new boolean[graph.length];
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> start = new ArrayList<>();
        start.add(0);
        dfs(graph, graph[0], graph.length-1, visited, res, start);
        return res;
    }
    
    //dfs
    private void dfs(int[][] graph, int[] paths, int n, boolean[] visited, List<List<Integer>> res, List<Integer> path){
        for(int next: paths){
            path.add(next);
            if( next == n ){
                List<Integer> list = new ArrayList<>();
                list.addAll(path);
                res.add(list);
            } else {
                visited[next] = true;
                dfs(graph, graph[next], n, visited, res, path);
                visited[next] = false;
            }
            path.remove((Integer)next);
        }
    }
// bfs method -> much slower because we have to keep track of all paths ( cause duplicates ) ( it's not suitable for this problem )
//         Map<Integer, List<List<Integer>>> map = new HashMap<>();
//         Queue<Integer> queue = new LinkedList<>();
        
//         queue.offer(0); //start;
//         List<Integer> l = new ArrayList<>();
//         l.add(0);
//         List<List<Integer>> o = new ArrayList<>();
//         o.add(l);
//         map.put(0, o);
        
//         while( !queue.isEmpty() ){
//             int size = queue.size();
//             for(int i=0; i < size; i++ ){
//                 int node = queue.poll();
                
//                 //all possible paths to reach to node
//                 List<List<Integer>> paths = map.get(node);                 
//                 int[] directions = graph[node];
//                 for( int direction : directions ){
//                     queue.offer(direction);
                    
//                     //paths up to new direction
//                     List<List<Integer>> updatedPath = updatePaths(paths, direction);
                    
//                     //create new paths for destination if not exists
//                     if(!map.containsKey(direction)){
//                         List<List<Integer>> temp2 = new ArrayList<>();
//                         map.put(direction, temp2);
//                     }
                    
//                     //add all new paths to current data
//                     List<List<Integer>> originalPaths = map.get(direction);
//                     for( List<Integer> path : updatedPath ){
//                         originalPaths.add(path);
//                     }
//                 }
//             }
//         }
        
//         return map.get(graph.length-1);
//     }
    
    private List<List<Integer>> updatePaths(List<List<Integer>> paths, int dest ){
        List<List<Integer>> res = new ArrayList<>();
        for(List<Integer> path : paths){
            List<Integer> copy = new ArrayList<>();
            for(int node : path){
                copy.add(node);
            }
            copy.add(dest);
            res.add(copy);
        }
        return res;
    }
}
