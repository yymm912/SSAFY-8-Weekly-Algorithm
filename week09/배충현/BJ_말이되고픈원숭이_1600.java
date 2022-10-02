import java.util.*;
import java.io.*;

public class BJ_말이되고픈원숭이_1600 {
	
	static StringBuilder sb = new StringBuilder();
	static int K, W, H, ans = 40001;
	static int[][] input, dt = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}}, dth = {{-2, -1}, {-2, 1}, {2, -1}, {2, 1}, {-1, 2}, {-1, -2}, {1, 2}, {1, -2}};
	static int[][][] visited;
	static Deque<int[]> dq;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		K = Integer.parseInt(br.readLine());
		stk = new StringTokenizer(br.readLine());
		W = Integer.parseInt(stk.nextToken());
		H = Integer.parseInt(stk.nextToken());
		input = new int[H][W];
		visited = new int[H][W][K+1];
		dq = new ArrayDeque<>();
		
		for(int i=0; i<H; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j=0; j<W; j++) {
				input[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		solve();
		
		System.out.println(ans == 40001 ? -1 : ans);
		
	}
	
	static void solve() {
		dq.addLast(new int[] {0, 0, 0, K});
		
		while(!dq.isEmpty()) {
			int[] cur = dq.removeFirst();
			int ch = cur[3];
			
			if (cur[0] == H-1 && cur[1] == W-1) {
				ans = Math.min(ans, cur[2]);
			}
			
			// 상하좌우 보내기
			for(int d=0; d<4; d++) {
				int ny = cur[0] + dt[d][0];
				int nx = cur[1] + dt[d][1];
				int nt = cur[2] + 1;
				
				if (ny<0 || ny>=H || nx<0 || nx>=W) continue;
				if (input[ny][nx] == 1) continue;
				if (visited[ny][nx][ch] != 0 && visited[ny][nx][ch] <= nt) continue;
				
				dq.addLast(new int[] {ny, nx, nt, ch});
				visited[ny][nx][ch] = nt;
			}
			
			// ch가 남아있으면 말 보내기
			if (ch >= 1) {
				for(int d=0; d<8; d++) {
					int ny = cur[0] + dth[d][0];
					int nx = cur[1] + dth[d][1];
					int nt = cur[2] + 1;
					int nh = ch - 1;
					
					if (ny<0 || ny>=H || nx<0 || nx>=W) continue;
					if (input[ny][nx] == 1) continue;
					if (visited[ny][nx][nh] != 0 && visited[ny][nx][nh] <= nt) continue;
					
					dq.addLast(new int[] {ny, nx, nt, nh});
					visited[ny][nx][nh] = nt;
				}
			}
		}
	}
}
