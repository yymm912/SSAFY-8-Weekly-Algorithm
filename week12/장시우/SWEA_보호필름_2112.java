package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_보호필름_2112 {
	
	static int[][] map, temp;
	static int D, W, K, T, ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1 ; t <= T ; ++t) {
			st = new StringTokenizer(br.readLine());
			
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[D][W];
			temp = new int[D][W];
			ans = Integer.MAX_VALUE;
			
			for(int r = 0 ; r < D ; ++r) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0 ; c < W ; ++c) {
					int type = Integer.parseInt(st.nextToken());
					map[r][c] = temp[r][c] = type;
				}
			}
			
			if(isPass()) {
				ans = 0;
			} else {
				injection(0, 0);
			}
			
			System.out.println("#" + t + " " + ans);
		}
	}

	private static void injection(int cnt, int layer) {
		if(cnt >= ans) return;
		
		if(layer == D) {
			if(isPass()) {
				ans = ans > cnt ? cnt : ans;
			}
			
			return;
		}
		
		// 주입하지 않음
		injection(cnt, layer + 1);
		
		// A 주입
		for(int c = 0 ; c < W ; ++c) temp[layer][c] = 0;
		injection(cnt + 1, layer + 1);

		// B 주입
		for(int c = 0 ; c < W ; ++c) temp[layer][c] = 1;
		injection(cnt + 1, layer + 1);
		
		// 되돌리기 
		for(int c = 0 ; c < W ; ++c) temp[layer][c] = map[layer][c];
	}

	private static boolean isPass() {
		for(int c = 0 ; c < W ; ++c) {
			int cnt = 1;
			int type = temp[0][c];
			boolean flag = false;
			
			for(int r = 1 ; r < D ; ++r) {
				if(type == temp[r][c]) {
					cnt++;
				} else {
					type = temp[r][c];
					cnt = 1;
				}
				
				if(cnt == K) {
					flag = true;
					break;
				}
			}
			
			if(!flag) return false;
		}
		
		return true;
	}
}