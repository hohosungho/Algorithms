class Solution {
    public int maxPower(String s) {
        if(s == null || s.isEmpty())
            return 0;
        int max = 1;
        int cons = 1;
        char prev = '0';
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == prev){
                cons++;
                max = Math.max(cons, max);
            } else {
                cons = 1;
            }
            prev = c;
        }
        return max;
    }
}
