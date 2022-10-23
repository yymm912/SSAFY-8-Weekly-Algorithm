package week12.김정윤;

import java.io.*;
import java.util.*;

public class SWEA2382_미생물격리 {
	static int T, N, M, K, ans;
	static int[][] map;
	static List<Microbe> microbe;
	static PriorityQueue<Microbe> pq;
	
	// 상: 1, 하: 2, 좌: 3, 우: 4
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 맵의 크기
			M = Integer.parseInt(st.nextToken()); // 격리 시간
			K = Integer.parseInt(st.nextToken()); // 미생물 군집 개수
			map = new int[N][N];
			microbe = new ArrayList<>();
			// 군집 규모로 정렬
			pq = new PriorityQueue<>((o1, o2) -> (o2.num - o1.num));
			
			for (int i = 1; i <= K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				
				microbe.add(new Microbe(x, y, num, dir)); // 미생물 군집 설정
				map[x][y] = i; // 군집 번호
			}
			
			ans = 0;
			// 격리 시간만큼 이동
			for (int i = 0; i < M; i++) {
				move();
			}
			
			for (int i = 0; i < microbe.size(); i++) {
				ans += microbe.get(i).num;
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	static void move() {
		for (int i = 0; i < microbe.size(); i++) {
			Microbe c = microbe.get(i); // i번째 군집 현재 정보
			
			map[c.x][c.y] = 0; // 현재 있던 위치 공백으로 변환
			
			int nx = c.x + dx[c.dir];
			int ny = c.y + dy[c.dir];
			int num = c.num;
			int dir = c.dir;
			
			// 군집의 다음 위치 규모로 정렬
			pq.add(new Microbe(nx, ny, num, dir));
		}
		
		// 초기화
		microbe.clear();
		
		while (!pq.isEmpty()) {
			Microbe n = pq.poll(); // 다음 위치
			
			// 약품 구역에 도달할 경우 규모를 반으로 줄이고 방향을 반대로 전환
			if (n.x == 0 || n.y == 0 || n.x == N-1 || n.y == N-1) {
				n.num /= 2; // 현재 규모의 반
				if (n.num == 0) continue; // 규모가 0이 될 경우 지우기
				
				// 하 -> 상, 우 -> 좌
				if (n.dir == 2 || n.dir == 4) {
					n.dir -= 1;
				} else { // 상 -> 하, 좌 -> 우
					n.dir += 1;
				}
				
				microbe.add(n);
			} else {
				if (map[n.x][n.y] == 0) { // 다음 위치에 미생물 군집이 없는 경우
					microbe.add(n);
					map[n.x][n.y] = microbe.size();
				} else { // 다음 위치에 미생물 군집이 있는 경우
					// 군집 합치기
					microbe.get(map[n.x][n.y] - 1).num += n.num;
				}
			}
		}
	}
	static class Microbe {
		int x, y, num, dir;

		public Microbe(int x, int y, int num, int dir) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Microbe [x=" + x + ", y=" + y + ", num=" + num + ", dir=" + dir + "]";
		}
		
		
	}
}
