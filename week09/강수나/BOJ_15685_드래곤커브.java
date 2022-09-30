package a22_09_29;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15685_µå·¡°ïÄ¿ºê {

	static boolean[][] map = new boolean[101][101];
	static int K, ans;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		while (K-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			dragonCurve(x, y, d, g); //µå·¡°ï Ä¿ºê
		}
		
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1]) {
					ans++;
				}
			}
		}
		System.out.println(ans);
	}
	
	static void dragonCurve(int x, int y, int d, int g) {
		List<Integer> list = new ArrayList<> ();
		list.add(d);
		
		for (int t = 1; t <= g; t++) {
			for (int i = list.size() - 1; i >= 0; i--) {
				list.add((list.get(i)+1)%4);
			}
		}
		map[y][x] = true;
		for (Integer dir : list) {
			x = x+dx[dir];
			y = y+dy[dir];
			map[y][x] = true;
		}
	}
	
}
