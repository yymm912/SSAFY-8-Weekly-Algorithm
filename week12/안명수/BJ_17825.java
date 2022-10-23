package algo.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_17825 {
	static int[] dice, map;
	static int[] unit = new int[4];
	static int ans;
	
	static int[] route = {
			0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38, // 0 ~ 19
			10,13,16,19, // 20 ~ 23
			20,22,24, // 24 ~ 26
			30,28,27,26, // 27 ~ 30
			25,30,35,40, // 31 ~ 34
			0
	};
	static boolean[] visit = new boolean[45];
	
	public static void main(String[] args) throws Exception{
		StringTokenizer stk = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());
		dice = new int[10];
		map = new int[41];
		for(int i = 0; i < 10; i++) {
			dice[i] = Integer.parseInt(stk.nextToken());
		}
		 
		m(0, 0);
		
		System.out.println(ans);
	}
	
	public static void m(int cnt, int sum) {

		ans = Math.max(sum, ans);
		if(cnt == 10) return;
		
	
		for(int i = 0; i < 4; i++) {
			int idx = unit[i];
			if(idx == 35) continue; 
			
			int dest = idx + dice[cnt];
			
			if(dest == 5) dest = 20;
			else if(dest == 10) dest = 24;
			else if(dest == 15) dest = 27;
			else if(idx <= 19 && dest > 19) {
				dest = 33 + dest - 19;
			}
			else if(20 <= idx && idx <= 23 && dest > 23) {
				dest = 30 + dest - 23;
			}else if(24 <= idx && idx <= 26 && dest > 26 ) {
				dest = 30 + dest - 26;
			}
			
			if(dest >= 35) dest = 35;
			if(dest < 35 && visit[dest]) continue;
			
			

			visit[idx] = false;
			if(dest != 35) {
				visit[dest] = true;
			}
			
			unit[i] = dest;
			m(cnt + 1, sum + route[dest]);
			unit[i] = idx;
			

			visit[idx] = true;
			if(dest != 35) {
				visit[dest] = false;
			}
		}
	}
}
