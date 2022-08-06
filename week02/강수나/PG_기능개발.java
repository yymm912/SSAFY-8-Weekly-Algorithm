package algo_study;

import java.util.Arrays;

//1. 배열
public class PG_기능개발2 {

	public static void main(String[] args) {
		Solution_ ans = new Solution_();
		int[] tmp1 = {95, 90, 99, 99, 80, 99};
		int[] tmp2 = {1, 1, 1, 1, 1, 1};
		ans.solution(tmp1, tmp2);
		//System.out.println(ans.solution(tmp1, tmp2).toString());
	}
}


class Solution_ {
    public int[] solution(int[] progresses, int[] speeds) {
        int len = progresses.length;
        int[] answer;
        int[] temp = new int[len];
        int[] days = new int[len];
        
        for (int i = 0; i < len; i++) {
        	days[i] = (100 - progresses[i]) / speeds[i];
        	if ((100 - progresses[i]) % speeds[i] != 0) days[i]++;
        }
        for (int i : days) {
        	System.out.print(i +" ");
        }
        
        int idx = 0;
        int front = days[0];
        for (int i = 1; i < len; i++) {
    		temp[idx]++;
        	if (days[i] > front) {
        		idx++;
        		front = days[i];
        	}
        	if (i == len-1) temp[idx]++;
        }
        
        int cnt = 0;
        int tmpLen = temp.length;
        for (int i = 0; i < tmpLen; i++) {
        	if (temp[i] != 0) cnt++;
        }
        
        answer = new int[cnt];
        for (int i = 0; i<cnt; i++) {
        	answer[i] = temp[i];
        }
        
        for (int i : answer) {
        	System.out.print(i +" ");
        }
        
        return answer;
    }
}


