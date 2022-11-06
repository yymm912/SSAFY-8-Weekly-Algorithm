import java.util.*;

class Solution {
    
    static int H,W;
    static int[][] memoi;
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        H=board.length;
        W=board[0].length;
        memoi = new int[H][W];
        for(int i=0; i<skill.length; i++){
            int op=skill[i][0];
            int sy=skill[i][1],sx=skill[i][2];
            int ey=skill[i][3],ex=skill[i][4];
            int n=skill[i][5];
            if(op==1){
                n=-n;
            }
            
            memoi[sy][sx]+=n;
            if(ex!=W-1)
                memoi[sy][ex+1]-=n;
            
            if(ey!=H-1){
                memoi[ey+1][sx]-=n;
                if(ex!=W-1)
                    memoi[ey+1][ex+1]+=n;   
            }
        }
        
        for(int y=0; y<H; y++){
            int plus=0;
            for(int x=0; x<W; x++){
                plus+=memoi[y][x];
                memoi[y][x]=plus;
            }
        }
        
        for(int x=0; x<W; x++){
            int plus=0;
            for(int y=0; y<H; y++){
                plus+=memoi[y][x];
                board[y][x]+=plus;
                if(board[y][x]>0) answer++;
            }
        }
        //for(int y=0; y<H; y++)
        //    System.out.println(Arrays.toString(board[y]));
        
        return answer;
    }
}