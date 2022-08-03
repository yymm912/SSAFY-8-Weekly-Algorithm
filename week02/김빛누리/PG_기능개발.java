package week02;
import java.util.Stack;
import java.util.Arrays;

public class PG_기능개발{
    public int[] solution(int[] progresses, int[] speeds) {
		int[] answer = {};
		int[] tmp = new int[progresses.length];
        int ansNum=0;
        int max = 0;
        
        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0; i<progresses.length;i++) {
        	int b = (int)Math.ceil((100-progresses[i])/(double)speeds[i]);
        	
        	if(stack.empty())
        		max=b;
        	else if(max<b) {
        		tmp[ansNum++]=stack.size();
        		stack.clear();
                max=b;
        	}
        		
        	stack.push(b);
        }
        
        tmp[ansNum++]=stack.size();
        stack.clear();
        
        answer = Arrays.copyOf(tmp, ansNum);
        
        return answer;
    }
}
