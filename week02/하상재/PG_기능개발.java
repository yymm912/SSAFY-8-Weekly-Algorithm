import java.util.Queue;
import java.util.LinkedList;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> q = new LinkedList<>();
        int idx=0;

        while(idx<progresses.length){
            int cnt=0;
            for(int i=idx; i<progresses.length; i++){
                if(progresses[i]<100) progresses[i]+=speeds[i];
            }

            if(progresses[idx]>=100){
                while(idx<progresses.length){
                    if(progresses[idx]>=100){
                        idx++;
                        cnt++;
                    }
                    else break;
                }
                q.add(cnt);
            }
        }

        int i=0;
        int[] answer = new int[q.size()];
        while(!q.isEmpty()) answer[i++] = q.poll();


        return answer;
    }
}