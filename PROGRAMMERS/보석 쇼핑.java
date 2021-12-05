import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        Map<String, Integer> map = new HashMap<>();
        for( String gem : gems ){
            map.put(gem,0);
        }
        
        int start = 0,gems_collected=0;
        int gems_size = map.size();
        int min_range = gems.length;
        int j = 0;
        while(j < gems.length){
            if(map.get(gems[j])==0){
                gems_collected++;
            }
            
            map.put(gems[j], map.get(gems[j])+1);
            
            if(gems_collected == gems_size){
                while(start<j && map.get(gems[start]) > 1){
                    map.put(gems[start],map.get(gems[start])-1);
                    start++;
                }
                while(start<j && map.get(gems[j]) > 1){
                    map.put(gems[j],map.get(gems[j])-1);
                    j--;
                }
                if(min_range>j-start){
                    min_range = j-start;
                    answer[0] = start+1;
                    answer[1] = j+1;
                }
                map.put(gems[start], map.get(gems[start])-1);
                start++;
                gems_collected--;
            }
            j++;
        }
        return answer;
    }
}
