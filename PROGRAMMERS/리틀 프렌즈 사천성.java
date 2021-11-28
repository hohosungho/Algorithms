import java.util.*;

class Solution {
    public static final String IMPOSSIBLE = "IMPOSSIBLE";

    public String solution(int m, int n, String[] board) {
        StringBuilder res = new StringBuilder();
        char[][] cBoard = new char[m][n];
        PriorityQueue<Character> pq = new PriorityQueue<>();
        Map<Character, int[][]> map = new HashMap<>();
        int total_tiles = recreateBoard(board, m, n, cBoard, map);

        Set<Character> tiles = getErasableTiles(cBoard, m, n);
        for( char tile : tiles ){
            pq.offer(tile);
        }

        while(!pq.isEmpty()){
            char tile = pq.poll();

            int[][] indices = map.get(tile);
            cBoard[indices[0][0]][indices[0][1]] = '.';
            cBoard[indices[1][0]][indices[1][1]] = '.';
            total_tiles -= 2;
            res.append(tile);
            tiles = getErasableTiles(cBoard, m, n);
            for( char _tile : tiles ){
                if(!pq.contains(_tile)){
                    pq.offer(_tile);
                }
            }
        }

        if(total_tiles!=0)
            return IMPOSSIBLE;

        return res.toString();
    }

    private Set<Character> getErasableTiles(char[][] cBoard,int m, int n){
        Set<Character> tiles = new HashSet<>();
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                char block = cBoard[i][j];
                if(block == '.' || block == '*')
                    continue;
                if(erasable(i,j+1,m,n,cBoard,0,block,'R'))
                    tiles.add(block);
                else if(erasable(i+1,j,m,n,cBoard,0,block,'D'))
                    tiles.add(block);
                else if(erasable(i-1,j,m,n,cBoard,0,block,'U'))
                    tiles.add(block);
                else if(erasable(i,j-1,m,n,cBoard,0,block,'L'))
                    tiles.add(block);
            }
        }
        return tiles;
    }

    private boolean erasable(int i, int j, int m, int n, char[][] board,
                      int turn_count, char block, char direction){
        if(i<0 || i>=m || j<0 || j>=n || board[i][j] == '*'){
            return false;
        }
        int curr = board[i][j];
        if(turn_count == 0){
            if( curr == '.' && direction == 'R' ){
                return erasable(i,j+1,m,n,board,0,block,direction) ||
                    erasable(i+1,j,m,n,board,1,block,'D') ||
                    erasable(i-1,j,m,n,board,1,block,'U');
            } else if( curr == '.' && direction == 'D' ){
                return erasable(i+1,j,m,n,board,0,block,direction) ||
                    erasable(i,j+1,m,n,board,1,block,'R') ||
                    erasable(i,j-1,m,n,board,1,block,'L');
            } else if( curr == '.' && direction == 'U' ){
                return erasable(i-1,j,m,n,board,0,block,direction) ||
                    erasable(i,j+1,m,n,board,1,block,'R') ||
                    erasable(i,j-1,m,n,board,1,block,'L');
            } else if( curr == '.' && direction == 'L' ){
                return erasable(i,j-1,m,n,board,0,block,direction) ||
                    erasable(i-1,j,m,n,board,1,block,'U') ||
                    erasable(i+1,j,m,n,board,1,block,'D');
            } else if( curr == block ){
                return true;
            } else {
                return false;
            }
        } else {
            if( curr == '.' && direction == 'R' ){
                return erasable(i,j+1,m,n,board,turn_count,block,direction);
            } else if( curr == '.' && direction == 'D' ){
                return erasable(i+1,j,m,n,board,turn_count,block,direction);
            } else if( curr == '.' && direction == 'L' ){
                return erasable(i,j-1,m,n,board,turn_count,block,direction);
            } else if( curr == '.' && direction == 'U' ){
                return erasable(i-1,j,m,n,board,turn_count,block,direction);
            } else if( curr == block ){
                return true;
            } else {
                return false;
            }
        }
    }

    private int recreateBoard(String[] board, int m, int n, char[][] b, Map<Character, int[][]> map){
        int count = 0;
        for(int i=0; i<m; i++){
            char[] row = board[i].toCharArray();
            int j = 0;
            for(char c : row){
                b[i][j] = c;
                if(c!='.'&&c!='*'){
                    count++;
                    if(!map.containsKey(c)){
                        map.put(c, new int[2][2]);
                        map.get(c)[0][0] = i;
                        map.get(c)[0][1] = j;
                    } else {
                        map.get(c)[1][0] = i;
                        map.get(c)[1][1] = j;
                    }
                }
                j++;
            }
        }
        return count;
    }
}
