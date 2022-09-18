package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ21608상어초등학교 {

	static int N;
	static int map[][];
	static int favorArray[][];
	static int emptyArray[][];
	static int student[];
	static int favorCnt;
	static int max;
	static int ans;
	static int favor[][];
	static int result[][];
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = {  0, 0,-1, 1 };
	static Dist order[];
	static List<Dist> list = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		result = new int[N][N]; // 결과값 계산 배열 
		student = new int[N*N]; // 배치할 학생 번호
		favor = new int[N*N][4]; // 좋아하는 학생 번호 
		order = new Dist[N*N]; // 학생들 저장 순서
		for (int t = 0; t < N*N; t++) {
			favorArray = new int[N][N];
			emptyArray = new int[N][N];
			Dist select = new Dist(0,0); // 선택된 칸 표시
			list.clear();
			max = 0;
			// 초기화 
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			student[t] = Integer.parseInt(st.nextToken()); // 배치할 학생의 번호 
			for (int f = 0; f < 4; f++) {
				favor[t][f] = Integer.parseInt(st.nextToken()); 
			}
			
			// 1. 좋아하는 학생 수 세기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] != 0) continue; // 비어있는 칸 중
					
					favorCnt = 0;
					for (int d = 0; d < 4; d++) {
						int py = i + dy[d];
						int px = j + dx[d];
						if(py < 0 || py >= N || px < 0 || px >= N ) continue;
						for (int f = 0; f < 4; f++) {
							if(map[py][px] == favor[t][f]) favorCnt++;
						}
						
					}
					favorArray[i][j] = favorCnt;
					
				}
			}
			
			// 좋아하는 학생 최댓값 찾기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					max = Math.max(max, favorArray[i][j]);
				}
			}
			
			// 좋아하는 학생 max 값 가지고 있는 칸을 리스트에 넣기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if( favorArray[i][j] == max && map[i][j] == 0 ) list.add(new Dist(i, j));
				}
			}
			
			// 2. 인접한 빈칸 수 세기 
			// 리스트가 1개보다 많으면 인접 칸 중 빈 칸이 많은 칸 찾아야 함
			int empty = 0;
			if(list.size() > 1) {
				for (Dist e : list) {
					for (int d = 0; d < 4; d++) {
						int py = e.y + dy[d];
						int px = e.x + dx[d];
						if(py < 0 || py >= N || px < 0 || px >= N  ) continue;
						if(map[py][px] == 0) emptyArray[e.y][e.x]++;
						
					}
				}
				int maxEmpty = 0;
				
				 // 최대 빈칸 수 찾기 
				for (Dist e : list) {
					maxEmpty = Math.max(maxEmpty, emptyArray[e.y][e.x] );
				}
				
				// 최대 빈칸 수의 개수 세기
				for (Dist e : list) { 
					if( maxEmpty == emptyArray[e.y][e.x] ) {
					// 3. 최대 빈칸 수가 여러개이면 최소 행 열 값 찾기
					//  행과 열 이 제일 작은 칸 ? -> 제일 앞에서부터 찾기
						select.y = e.y;
						select.x = e.x;
						break;
					}
				}
		
				
			} 
			else { // 리스트가 1개이하이면 맨 처음 값 ( 0 번째 : 행, 열 최솟값 )
				select.y = list.get(0).y;
				select.x = list.get(0).x;
			}
			
			// 최종 선택된 칸에 학생 배치
			map[select.y][select.x] = student[t];
			// 저장 순서 기억
			order[t] = select;
		
			
		}
		// 모든 배치가 끝난 후, 학생들의 만족도 출력
	
		
		// 현재 배치한 곳의 만족도 저장
		int idx = 0;
		for (Dist t : order) {
			for (int d = 0; d < 4; d++) {
				int py = t.y + dy[d];
				int px = t.x + dx[d];
				if(py < 0 || py >= N || px < 0 || px >= N ) continue;
				for (int f = 0; f < 4; f++) {
					if(favor[idx][f] == map[py][px]) result[t.y][t.x]++;
				}
			}
			idx++;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(result[i][j] == 1) ans += 1;
				else if(result[i][j] == 2) ans += 10;
				else if(result[i][j] == 3) ans += 100;
				else if(result[i][j] == 4) ans += 1000;
			}
		}
		
		System.out.println(ans);
		
	}
	static class Dist {
		int y,x;

		public Dist(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}

}
