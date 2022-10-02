package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ15685드래곤커브 {

	static int N;
	static int x, y, d, g;
	static int size = 101;
	static boolean map[][] = new boolean[size][size];
	static int dy[] = { 0, -1, 0, 1 }; // 우 상 좌 하 
	static int dx[] = { 1, 0, -1, 0 };
	static List<Dist> list = new ArrayList<>();
	static Stack<Dist> stack = new Stack<>();
	static int ans;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// X 축은 → 방향, y축은 ↓ 방향 
		
		// 1. 시작 점
		// 2. 시작 방향
		// 3. 세대 
		
	
		for (int t = 0; t < N; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			stack.clear();
			list.clear();
			x = Integer.parseInt(st.nextToken()); // 시작점 x
			y = Integer.parseInt(st.nextToken()); // 시작점  y
			d = Integer.parseInt(st.nextToken()); // 시작 방향
			g = Integer.parseInt(st.nextToken()); // 세대 
			
			// 3 3 0 1
			
			// 시작점 초기화 
			int py = y + dy[d];
			int px = x + dx[d];
			map[y][x] = true;
			map[py][px] = true;
			stack.push(new Dist(py, px, d));
			list.add(new Dist(py,px,d)); // 리스트에는 드래곤 커브 누적 
			
			// d = 0 ( 오른쪽 ) 일 떄는 d = 1 (위쪽)
			// d = 1 ( 위쪽 ) 일 때는 d = 2 ( 왼쪽 )
			// d = 2 ( 왼쪽) 일 때는 d = 3 ( 아래쪽 ) 
			// d = 3 ( 아래쪽) 일 떄는 d = 0 ( 오른쪽 )
			
			// 끝점 기준 -> 끝점 기준으로 잡고 돌림 
			for (int i = 0; i < g; i++) { // 세대 만큼 반복 
				
				// 리스트의 마지막이 끝점 
				int listSize = list.size();
				// 현재 스택 크기 만큼 반복 
				for (int j = 0; j < listSize; j++) {
					Dist e = stack.pop();
					d = (e.d+1)%4; // 마지막 위치에서 방향 + 1
					if(py <  0 || px < 0 || py >= size || px >= size )continue;
					py += dy[d];
					px += dx[d];
					
					list.add(new Dist(py, px, d));
					map[py][px] = true;
				}
				
				// 스택에 현재 리스트에 있는 값 모두 넣기 
				for (int j = 0; j < list.size(); j++) {
					stack.push(list.get(j));
				}
				
				
			}
			
			// 네 꼭짓점이 정사각형인 것
			
			
		} // testcase
		
		square();
		
		System.out.println(ans);
	}
	static void square() {
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				
				// 왼쪽 위에서부터 세기
				if(i+1 >= size || j+1 >= size ) continue;
				if( map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1] ) {
					ans++;
				}
				
			}
		}
		
	}
	
	static class Dist {
		int y, x, d;

		public Dist(int y, int x, int d) {
			super();
			this.y = y;
			this.x = x;
			this.d = d;
		}

	}

}
