package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ16234인구이동 {

	static int N, L, R;
	static int map[][];
	static boolean visit[][];
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0,  0,-1, 1 };
	static int ans;
	static int num;
	static int cnt;
	static List<Dist> list = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			 st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 완 
		
		// 국경선 공유하는 곳 차이가 L ~ R 이라면 방문 표시 
		// 2중 for문 다 돌아도 if문에 걸리지 않으면 종료
		while(true) {
			
			num = 0; // 연합의 개수 
			visit = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(visit[i][j]) continue;
					cnt = 0;
					dfs(i, j, num); // num은 연합 번호
					if(cnt <= 1) list.remove(list.size()-1);
					else num++;
				}
			}
			
			
			if(list.size() > 1) {
				chk();
				ans++;
			}
			else break;
			list.clear();	
		}
	
		System.out.println(ans);
		
	}
	static void chk() {
		
		for (int i = 0; i < num; i++) { // 연합 개수 만큼 반복 
			int unionSize = 0; // i 번째 연합국가 수 
			int sum = 0;
			for (Dist l : list) {
				if(l.num == i) {
					unionSize++;
					sum += map[l.y][l.x];
				}
			}
			
			// (연합의 인구 / 연합을 이루는 칸의 개수)
			int person = sum / unionSize;
			// 리스트 안의 map 요소들 모두 값 변경
			
			for (Dist l : list) {
				if(l.num == i) {
					map[l.y][l.x] = person;
				}
			}	
		}
		
	}
	static void dfs(int y, int x, int num) {
		
		visit[y][x] = true;
		list.add(new Dist(y,x, num));
		cnt++;
		
		for (int d = 0; d < 4; d++) {
			int py = y + dy[d];
			int px = x + dx[d];
			
			if(py < 0 || py >= N || px < 0 || px >= N || visit[py][px]) continue;
			int diff = Math.abs(map[py][px] - map[y][x]); // 두 나라의 차이 
			if( diff >= L && diff <= R ) { // L ~ R 일 때만 dfs 
				dfs(py, px, num);
			}
			
		}
		
	}
	
	static class Dist {
		int y, x, num;

		public Dist(int y, int x, int num) {
			super();
			this.y = y;
			this.x = x;
			this.num = num;
		}
	}
}
