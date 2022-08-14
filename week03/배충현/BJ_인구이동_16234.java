package ssafy.algorithm.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_인구이동_16234_2 {
	
	static int N, L, R, total;
	static int[][] input, dt = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][] visited;
	static List<LP> rl;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		L = Integer.parseInt(stk.nextToken());
		R = Integer.parseInt(stk.nextToken());
		
		input = new int[N][N];
		visited = new boolean[N][N];
		rl = new ArrayList<>();
		
		// 입력받기
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				input[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		// 순회하면서 인구 이동의 유무를 확인해야한다.
		// visited 체크하면서 L ~ R 범위 내의 값인지 체크하고 어딘가에 모은다? --> 평균값으로 바꿔주고 다시 박아넣는다? List<List<Land>>
		// 굳이 큐를 쓸 필요 없이 리스트에 계속 넣고, 인덱스로 확인하기
		total = 0;
		while (true) {
			rl.clear();
			visited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) check(i, j); 
				}
			}
			
			if (rl.size() == N * N) break;
			
			move();
			
			total++;
			
		}
		
		System.out.println(total);
		
	}
	
	static void move() {
		for (LP l : rl) {
			for (Land ld : l.list) {
				input[ld.y][ld.x] = l.avg;
			}
		}
	}
	
	static void check(int y, int x) {
		visited[y][x] = true;
		int sum = input[y][x];	// cnt 는 list 의 size
		List<Land> list = new ArrayList<>();
		list.add(new Land(y, x, input[y][x]));	// 어딘가에 저장 --> idx: 0
		int idx = 0;
		
		while (idx < list.size()) {
			Land cur = list.get(idx);
			int cy = cur.y;
			int cx = cur.x;
			int cp = cur.p;
			
			for (int d = 0; d < 4; d++) {
				int ny = cy + dt[d][0];
				int nx = cx + dt[d][1];
				
				if (ny<0 || nx<0 || ny>=N || nx>=N) continue;
				int np = input[ny][nx];
				if (visited[ny][nx] || Math.abs(np - cp) < L || Math.abs(np - cp) > R) continue;
				
				visited[ny][nx] = true;
				sum += np;
				list.add(new Land(ny, nx, np));
				
			}
			idx++;
		}
		
		rl.add(new LP(sum, list));
		
	}
	
	static class LP {
		int sum;
		int avg;
		List<Land> list;
		
		public LP(int s, List<Land> l) {
			this.sum = s;
			this.list = l;
			this.avg = s / l.size();
		}
	}
	
	static class Land {
		int y;
		int x;
		int p;
		
		public Land(int y, int x, int p) {
			this.y = y;
			this.x = x;
			this.p = p;
		}
	}
}
