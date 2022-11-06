import java.util.*;

class Solution {
    static Queue<board> board = new ArrayDeque<>();
    static int[] answer = {-1};
    static int score = 0;

    public int[] solution(int n, int[] info) {
        board.offer(new board(0,new int[]{0,0,0,0,0,0,0,0,0,0,0}, 0));
        int ryon, apeach;
        while(!board.isEmpty()){
            board b = board.poll();

            if(b.cnt == n){
                ryon = apeach = 0;
                for(int i = 0; i < 11; i++){
                    if(info[i] > b.scoreBoard[i]) apeach += 10-i;
                    else if(info[i] < b.scoreBoard[i]) ryon += 10-i;

                }
                if(apeach < ryon && score <= ryon-apeach ){
                    answer = b.scoreBoard;
                    score = ryon-apeach;
                }
            }
            else if(b.target == 10){
                board nextBoard = new board(0, b.scoreBoard.clone(), n);
                nextBoard.scoreBoard[b.target] = n-b.cnt;
                board.offer(nextBoard);
            }
            else{
                board nextBoard;
                if(b.cnt + info[b.target] + 1 <= n){
                    nextBoard = new board(b.target+1, b.scoreBoard.clone(), b.cnt + info[b.target] + 1);
                    nextBoard.scoreBoard[b.target] = info[b.target]+1;
                    board.offer(nextBoard);
                }
                nextBoard = new board(b.target+1, b.scoreBoard.clone(), b.cnt);
                board.offer(nextBoard);
            }


        }

        return answer;
    }
    static class board{
        int target, cnt, scoreBoard[];
        board(int target, int[] scoreBoard, int cnt){
            this.target = target;
            this.scoreBoard = scoreBoard;
            this.cnt = cnt;
        }
    }
}

