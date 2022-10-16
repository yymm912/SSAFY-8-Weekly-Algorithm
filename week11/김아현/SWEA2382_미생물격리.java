package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 군집이 가지는 속성 : 위치(x, y), 미생물수(c), 방향(d), 임시 미생물 수 (tmp) -> Team 클래스 + List
// 가장자리 부분 약품 처리 : 0행 0열, N-1행 N-1열
// 한시간 마다 군집의 이동 : 상하좌우. 한칸씩 군집이 갖는 방향으로.
//		이동한 곳이 가장자리면 군집/2 처리 + 다른 군집과 충돌하면 큰 군집으로 수 병합

// 다른 군집과 충돌하는 것 처리 -> 미생물 수가 큰 군집의 임시 미생물 수 변수에,
// 		미생물 수가 작은 군집의 미생물 수 + 임시 미생물 수를 누적해 준 뒤 큰 군집을 배열에 넣어둔다.
//		마지막에 임시 미생물 수 값을 가지고 있는 군집이 있으면 원래의 미생물 수에 누적한다.

/*
1
7 2 9
1 1 7 1
2 1 7 1
5 1 5 4
3 2 8 4
4 3 14 1
3 4 7 3
1 5 8 2
3 5 100 1
5 5 1 1
=> 149
*/

public class SWEA2382_미생물격리 {

	static int T, N, K, M, res;
	static Team[][] map;
	static List<Team> list;
	
	static int[] dx = {-1, 1, 0, 0}; // 상하좌우
	static int[] dy = {0, 0, -1, 1};
	
	static class Team {
		int x, y, c, d, tmp;
		Team(int x, int y, int c, int d) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.d = d;
		}
		
		@Override
		public String toString() {
			return "Team [x=" + x + ", y=" + y + ", c=" + c + ", d=" + d + "]";
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new Team[N][N];
			list = new ArrayList<>();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken()) - 1;
				
				Team team = new Team(x, y, c, d);
				map[x][y] = team;
				list.add(team);
			}
			
			res = 0;
			
			move();
			
			int size = list.size();
			for (int i = 0; i < size; i++) {
				res += list.get(i).c;
			}
			
			System.out.println("#" + t + " " + res);
		}
	}

	static void move() {
		
		while(M-- > 0) {
			int size = list.size();
			for (int i = size-1; i >= 0; i--) {
				Team t = list.get(i);
				
				t.x += dx[t.d];
				t.y += dy[t.d];
				
				// 가장자리 약품에 닿으면 미생물 수 절반 줄어듬
				if(t.x == 0 || t.y == 0 || t.x == N-1 || t.y == N-1) {
					t.c /= 2;
					
					if(t.c == 0) { // 미생물수가 없으면 군집 제거 
						list.remove(i);
						continue;
					}
					
					switch(t.d) { // 방향 반대로 전환
					case 0 : t.d = 1; break;
					case 1 : t.d = 0; break;
					case 2 : t.d = 3; break;
					case 3 : t.d = 2; break;
					}
				}
			}
			
			clean(); // 겹쳐진 군집 정리
		}
	}
	
	static void clean() {
		// 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = null;
			}
		}
		
		// 리스트의 군집 정보를 배열에 업데이트
		int size = list.size();
		for (int i = size-1; i >= 0; i--) {
			Team t = list.get(i);
			
			if(map[t.x][t.y] == null) { // 칸이 비어있으면 바로 저장
				map[t.x][t.y] = t;
			} else { // 칸이 비어있지 않으면 미생물 수 비교 후 큰쪽의 임시 미생물 수에 값 누적
				if(map[t.x][t.y].c < t.c) {
					t.tmp += map[t.x][t.y].c + map[t.x][t.y].tmp;
					list.remove(map[t.x][t.y]);
					map[t.x][t.y] = t;
				} else {
					map[t.x][t.y].tmp += t.c;
					list.remove(i);
				}
			}
		}
		
		size = list.size();
		for (int i = 0; i < size; i++) {
			if(list.get(i).tmp != 0) {
				list.get(i).c += list.get(i).tmp;
				list.get(i).tmp = 0;
			}
		}
	}
}
