import java.util.*;
class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int n = lock.length;
        int m = key.length;
        List<int[]> holes = new ArrayList<>();
        int[][] larger_lock = recreateLock(lock, n, m, holes);
        int[][] key_90 = rotate(key);
        int[][] key_180 = rotate(key_90);
        int[][] key_270 = rotate(key_180);
        for(int i = 0; i < m+n-1; i++){
            for(int j = 0; j < m+n-1; j++){
                if( fits(key,i,j, larger_lock, n,m, holes ) ||
                    fits(key_90,i,j, larger_lock, n,m, holes ) ||
                    fits(key_180,i,j, larger_lock, n,m, holes ) ||
                    fits(key_270,i,j, larger_lock, n,m, holes ) )
                   return true;
            }
        }
        return false;
    }
    
    private int[][] recreateLock(int[][] lock, int n, int m, List<int[]> holes){
        int[][] l = new int[(m-1)*2+n][(m-1)*2+n];
        for(int i=m-1; i<n+m-1; i++){
            for(int j=m-1; j<n+m-1; j++){
                l[i][j] = lock[i-(m-1)][j-(m-1)];
                if(l[i][j] == 0)
                    holes.add(new int[]{i,j});
            }
        }
        return l;
    }
    
    private boolean fits(int[][] key, int i_offset, int j_offset, int[][] lock, int n, int m, List<int[]> holes ){
        int[][] lock_key = new int[lock.length][lock.length];
        for(int i = 0; i < lock.length; i++){
            for(int j = 0; j < lock.length; j++){
                lock_key[i][j] = lock[i][j];
            }
        }
        for(int i = 0; i < key.length; i++){
            for(int j = 0; j < key.length; j++){
                if( i+i_offset >= m-1 && i+i_offset < n+m-1
                  && j+j_offset >= m-1 && j+j_offset < n+m-1 ){
                    if( lock[i+i_offset][j+j_offset] + key[i][j] != 1 )
                        return false;
                    else{ 
                        lock_key[i+i_offset][j+j_offset] = 1;
                    }
                }
            }
        }
        
        for( int[] hole : holes ){
            int i = hole[0];
            int j = hole[1];
            if( lock_key[i][j] != 1)
                return false;
        }
        return true;
    }
    
    private int[][] rotate(int[][] key){
        int[][] copy = new int[key.length][key[0].length];
        for(int i = 0; i < key.length; i++){
            for(int j = 0; j < key.length; j++){
                copy[i][j] = key[key.length-1-j][i];
            }
        }
        return copy;
    }
}
