import java.util.*;
class Solution {
    public Set<String> set = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {
        dfs(0, banned_id, user_id, new TreeSet<>());
        return set.size();
    }
    
    private void dfs( int i, String[] banned_id, String[] user_id, Set<String> list ){
        if(i >= banned_id.length ){
            if(list.size() == banned_id.length)
                set.add(list2String(list));
            return;
        }
        String bannedId = banned_id[i];
        List<String> matches = matchBannedIdPatter( bannedId, user_id );
        for(String match: matches){
            if(list.contains(match))
                continue;
            list.add(match);
            dfs(i+1, banned_id, user_id, list);
            list.remove(match);
        }
    }
    
    private String list2String(Set<String> list){
        StringBuilder sb = new StringBuilder();
        for(String s: list){
            sb.append(s).append(",");
        }
        return sb.toString();
    }
    
    private List<String> matchBannedIdPatter(String ban, String[] user_id){
        int length = ban.length();
        List<String> list = new ArrayList<>();
        for(String userId: user_id){
            if(userId.length() != length)
                continue;
            else{
                boolean isMatch = true;
                for(int i = 0; i < length; i++){
                    if(userId.charAt(i) != ban.charAt(i) &&
                      ban.charAt(i) != '*'){
                        isMatch = false;
                        break;
                    }
                }
                if(isMatch) list.add(userId);
            }
        }
        return list;
    }
}
