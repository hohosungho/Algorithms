class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int i = 0;
        int j = 0;
        List<int[]> list = new ArrayList<>();
        
        while(i<firstList.length && j <secondList.length){
            int[] p1 = firstList[i];
            int[] p2 = secondList[j];
            int start = Math.max(p1[0],p2[0]);
            int end = Math.min(p1[1],p2[1]);
            
            if(start <= end){
                list.add(new int[]{start,end});
            }
            
            if(p1[1]==end) i++;
            if(p2[1]==end) j++;
            
        }
        
        return list.toArray(new int[0][]);
    }
}
