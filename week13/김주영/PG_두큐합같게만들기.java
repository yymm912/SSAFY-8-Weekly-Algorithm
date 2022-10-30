import java.util.*;

// 그리디 하게 풀어볼까? 하다가 두 수가 정렬되어 있지 않기 때문에
// 모든 경우를 다 따져보아야지만 최소를 찾을 수 있다고 생각 --> dfs 완탐 --> stackoverflow

// queue의 FIFO를 따르고, 어차피 중간 값을 옮길 수 없음 
// 따라서 그리디로 현재 합이 큰 곳에서 빼고, 작은 곳에 더해주는 식

class Solution {
    
    static Queue<Integer> q1,q2;
    static long sum1, sum2;
    static int cnt;
    static int limit;
    
    public int solution(int[] queue1, int[] queue2) {
        
        q1=new ArrayDeque<>();
        for (int i=0; i<queue1.length; i++) {
            q1.add(queue1[i]);
            sum1+=queue1[i];
        }
        
        q2=new ArrayDeque<>();
        for (int i=0; i<queue2.length; i++) {
            q2.add(queue2[i]);
            sum2+=queue2[i];
        }
        
        if (sum1==sum2) return 0;           //이미 합이 같은 경우
        if ((sum1+sum2)%2!=0) return -1;    //홀수여서 나눌 수 없는 경우 
        
        limit=(queue1.length+queue2.length)*2;
        
        while (cnt<limit) {
            if (sum1==sum2) {
                return cnt;
            }
            
            if (sum1>sum2) {
                int n=q1.poll();
                sum1-=n;
                sum2+=n;
                q2.add(n);
            } else {
                int n=q2.poll();
                sum1+=n;
                sum2-=n;
                q1.add(n);
            }
            cnt++;
        }
        return -1;
    }

}