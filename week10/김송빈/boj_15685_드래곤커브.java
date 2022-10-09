package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_15685_드래곤커브 {
	static int n;
	static Stack<Integer> stk1 = new Stack<>();// 전에 것
	static Stack<Integer> stk2 = new Stack<>();// 다음 것
	static int[] dy = { 0, -1, 0, 1 }, dx = { 1, 0, -1, 0 };
	static boolean[][] map = new boolean[101][101];
	static int y, x, d, g;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			stk1.clear();
			map[y][x] = true;
			stk1.push(d);

			simul();
			cal(y,x);
		}
		int ans=0;
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				if(map[i][j]&&map[i+1][j]&&map[i+1][j+1]&&map[i][j+1])ans++;
			}
		}
		System.out.println(ans);

	}

	static void simul() {
		for (int i = 0; i < g; i++) {
			stk2.addAll(stk1);
			
			while (!stk1.isEmpty()) {
				int d = stk1.pop();
				
				int nd = d + 1;
				if (nd == 4)nd = 0;
				stk2.push(nd);
			
			}
			stk1.addAll(stk2);
			//System.out.println(stk1.toString());
			stk2.clear();
		}
	}
	static void cal(int y,int x) {
		for(int a:stk1) {
			map[y+dy[a]][x+dx[a]]=true;
			y+=dy[a];
			x+=dx[a];
		}
	}
}
