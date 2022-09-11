package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 여행계획 {
	static int N, M;
	static int[] parents;
	static int[][] map;
	static int[] plan;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 여행지의 수
		M = Integer.parseInt(st.nextToken()); // 여행계획 수
		
		map = new int[N+1][N+1]; 
		
		parents = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) union(i, j); // 여행지 i와 j가 연결된 경우, union 취함
			}
		}
		
		plan = new int[M+1]; // 여행계획을 입력받는 배열
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			plan[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < M-1; i++) { // 여행계획대로 따르지않아 같은 부모노드를 취하지 않는다면 NO를 출력하고 종료.
			if(find(plan[i]) != find(plan[i+1])) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}
	
	static int find(int x) {
		if(parents[x] == x) return x;
		return parents[x] = find(parents[x]);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return;
		parents[y] = x;
	}

}
