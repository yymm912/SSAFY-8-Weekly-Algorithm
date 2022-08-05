package stack.queue;

import java.util.*;

public class 기능개발 {
    public int[] solution(int[] progresses, int[] speeds) {
        
        Queue<Integer> q = new LinkedList<>();
        List<Integer> answerList=new ArrayList<>();
        
        int N=progresses.length;
           
        for (int i=0; i<N; i++) {
            
           double remain=(100-progresses[i])/(double)speeds[i];
           int date=(int)Math.ceil(remain);
            
            
          //  int date=(100-progresses[i])/speeds[i];
          //  if (progresses[i]%2==0) date++;
            
            if (!q.isEmpty() && q.peek()<date) {
                answerList.add(q.size());
                q.clear();
            }
             q.offer(date);
        }
        
        answerList.add(q.size());
        
        int[] answer=new int[answerList.size()];
        for (int i=0; i<answer.length; i++)
            answer[i]=answerList.get(i);
        
        
        return answer;
    }
}
