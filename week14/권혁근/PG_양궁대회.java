import java.util.*;



class Solution {
    
    static void rec(int idx, int score, int n){
        if(idx==11){
            if(score>maxScore){
                maxScore=score;
                //System.out.println(Arrays.toString(lyon));
                //System.out.println(score+ ", "+n);
                for(int i=0; i<=10; i++){
                    answer[i]=lyon[i];
                }
                if(n!=0){ //남은화살 끝에추가
                    answer[10]+=n;
                }
            }else if(score==maxScore){ //동점인경우
                int[] answer2=new int[11];
                for(int i=0; i<=10; i++){
                    answer2[i]=lyon[i];
                }
                if(n!=0){ //남은화살 끝에추가
                    answer2[10]+=n;
                }
                for(int i=10; i>=0; i--){ //낮은점수 비교
                    if(answer2[i]==answer[i])
                        continue;
                    else if(answer2[i]>answer[i]){
                        answer=answer2;
                        break;
                    }else{
                        break;
                    }
                }
            }
            return;
        }
        
        
        if(apeach[idx]==0){ //어피치가 0개 맞춤
            if(n>0){ //라이온 남은화살 0개이상
                lyon[idx]=1;
                rec(idx+1, score+(10-idx), n-1); //라이온 1개맞춤
                lyon[idx]=0;

                rec(idx+1, score, n); //라이온 0개맞춤
            }else{ 
                rec(idx+1, score, n); //라이온 0개 맞춤
            }   
        }else{
            if(n>=apeach[idx]+1){ //남은화살 충분함
                lyon[idx]=apeach[idx]+1;
                rec(idx+1, score+(10-idx), n-apeach[idx]-1); //라이온1개 더맞춤
                lyon[idx]=0;

                rec(idx+1, score-(10-idx), n); //라이온 0개맞춤
            }else{ //화살 부족
                rec(idx+1, score-(10-idx), n); //라이온 0개맞춤
            }
        }
    }

    static int N;
    static int[] lyon;
    static int maxScore;
    static int[] apeach,answer;
    public int[] solution(int n, int[] info) {
        answer=new int[11];
        apeach=info;
        lyon=new int[11];
        maxScore=-1000;
        rec(0,0,n);
        if(maxScore<=0){ //지거나 동점
            answer=new int[]{-1};
        }
        return answer;
    }
}