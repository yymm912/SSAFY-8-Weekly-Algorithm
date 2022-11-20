import java.io.*;
import java.util.*;

public class SWEA2382_미생물격리 {
	static int T, N, M, K, ans;
	static int[][] map;
	static List<Microbe> microbe;
	static PriorityQueue<Microbe> mpq; // 군집 규모순으로 정렬
	
	// 상:1, 하:2, 좌:3, 우:4
	static int[] dr = {0, -1, 1, 0, 0};
	static int[] dc = {0, 0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 셀 크기
			M = Integer.parseInt(st.nextToken()); // 격리 시간
			K = Integer.parseInt(st.nextToken()); // 군집 개수
			map = new int[N][N];
			microbe = new ArrayList<>();
			mpq = new PriorityQueue<>((o1, o2) -> (o2.sum - o1.sum));
			
			// 미생물 군집 저장
			for (int i = 1; i <= K; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int sum = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				microbe.add(new Microbe(r, c, sum, dir));
				map[r][c] = i;
			}
			
			ans = 0;
			
			// 격리 시간만큼 움직임
			for (int i = 0; i < M; i++) {
				move();
			}
			
			// 남아있는 미생물 총 합 구하기
			for (int i = 0; i < microbe.size(); i++) {
				ans += microbe.get(i).sum;
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	static void move() {
		// 큐에 미생물 군집 저장
		for (int i = 0; i < microbe.size(); i++) {
			Microbe m = microbe.get(i);
			int cr = m.r;
			int cc = m.c;
			int sum = m.sum;
			int dir = m.dir;
			
			// 현재 지점 군집 초기화
			map[cr][cc] = 0;

			int nr = cr + dr[dir];
			int nc = cc + dc[dir];

			mpq.offer(new Microbe(nr, nc, sum, dir));
		}
		microbe.clear();
		
		while (!mpq.isEmpty()) {
			Microbe m = mpq.poll();

			// 약품에 닿을 경우
			if (m.r == 0 || m.c == 0 || m.r == N-1 || m.c == N-1) {
				m.sum /= 2;
				
				// 군집 규모가 0이 될 경우 없어짐
				if (m.sum == 0) continue;
				
				// 방향 전환
				if (m.dir == 2 || m.dir == 4) {
					m.dir -= 1;
				} else {
					m.dir += 1;
				}

				microbe.add(m);
			} 
			else { // 약품에 닿지 않을 경우
				if (map[m.r][m.c] == 0) { // 다음 위치에 미생물 군집이 없는 경우
					microbe.add(m);
					map[m.r][m.c] = microbe.size();
				} else { // 다음 위치에 미생물 군집이 있는 경우
					microbe.get(map[m.r][m.c] - 1).sum += m.sum;
				}
			}
		}
	}
	static class Microbe {
		int r, c, sum, dir;

		public Microbe(int r, int c, int sum, int dir) {
			this.r = r;
			this.c = c;
			this.sum = sum;
			this.dir = dir;
		}
		
	}
}
