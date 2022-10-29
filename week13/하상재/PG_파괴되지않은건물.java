package study;

import java.util.HashMap;
class Solution {
    static int N, M, answer;
    
    public int solution(int[][] board, int[][] skill) {
        answer = 0;
        N = board.length;
        M = board[0].length;
        
        int[][] board2 = new int[N+1][M+1];
        
        for(int i=0; i<skill.length; i++){
            int val = skill[i][5];
            if(skill[i][0]==1) val *= -1;
            
            board2[skill[i][1]][skill[i][2]] += val;
            board2[skill[i][3]+1][skill[i][2]] += (val*-1);
            board2[skill[i][1]][skill[i][4]+1] += (val*-1);
            board2[skill[i][3]+1][skill[i][4]+1] += val;
            
        }
        
        setting(board2);
        
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                 if(board2[i][j] + board[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }
    
    static void setting(int[][] board2){
        
        for(int i = 1; i<N; i++){
            for(int j = 0; j<M; j++){
                 board2[i][j] += board2[i-1][j];
            }
        }
        
        for(int i = 1; i<N; i++){
            for(int j = 0; j<M; j++){
                 board2[j][i] += board2[j][i-1];
            }
        }
    }
}
