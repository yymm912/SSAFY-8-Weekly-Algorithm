package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA8382_방향전환 {

	static int T, x1, y1, x2, y2, res;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken()); // 입력 끝
			
			res = Integer.MAX_VALUE;
			
			move(true);  // 가로
			move(false); // 세로
			
			System.out.println("#" + t + " " + res);
		}
	}

	static void move(boolean flag) {
		int tx = x1;
		int ty = y1;
		int cnt = 0;
		
		while(true) {
			if(tx == x2 && ty == y2) {
				res = Math.min(res, cnt);
				return;
			}
			
			if(flag) { // 가로
				
				if(ty > y2) ty--;
				else ty++;
				flag = false;
				
			} else {  // 세로
				
				if(tx > x2) tx--;
				else tx++;
				flag = true;
			}
			
			cnt++;
		}
	}
}
