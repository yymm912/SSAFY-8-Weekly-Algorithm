import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA2382_미생물격리 {

	static int T, N, M, K;
	static Crew[][] map;
	static List<Crew> list;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static class Crew {
		int x, y, cnt, dir, tmp;
		Crew (int x, int y, int cnt, int dir) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());  // 행, 열
			M = Integer.parseInt(st.nextToken());  // 격리시간
			K = Integer.parseInt(st.nextToken());  // 미생물 군집 수
			
			list = new ArrayList<>();
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;
				
				list.add(new Crew(x, y, cnt, dir));
			} // 입력 끝
			
			while(M-- > 0) {
				// #1. 군집 이동
				move();
				
				// #2. 중복 제거
				check();
			}
			
			System.out.println("#" + t + " " + count());
		}
	}

	static void move() {
		int size = list.size();
		
		for (int i = size-1; i >= 0; i--) {
			Crew c = list.get(i);
			c.x += dx[c.dir];
			c.y += dy[c.dir];
			
			// 약품 위치로 이동했을 경우
			if(c.x == 0 || c.y == 0 || c.x == N-1 || c.y == N-1) {
				// 미생물 수 절반 감소
				c.cnt /= 2;
				
				// 방향 반대로 전환
				switch(c.dir) {
				case 0: c.dir = 1; break;
				case 1: c.dir = 0; break;
				case 2: c.dir = 3; break;
				case 3: c.dir = 2; break;
				}
			}
			
			// 미생물 수가 0이면 군집 제거
			if(c.cnt == 0)
				list.remove(i);
		}
	}
	
	static void check() {
		map = new Crew[N][N];
		
		int size = list.size();
		for (int i = size-1; i >= 0; i--) {
			Crew c = list.get(i);
			
			if(map[c.x][c.y] == null) { // 이동한 위치에 군집이 없으면 바로 등록
				map[c.x][c.y] = c;
			} else {  // 이동한 위치에 군집 있으면 군집수 비교
				if(map[c.x][c.y].cnt < c.cnt) {
					c.tmp += map[c.x][c.y].cnt + map[c.x][c.y].tmp; // 병합된 군집의 미생물수는 임시 저장
					list.remove(map[c.x][c.y]);
					map[c.x][c.y] = c;
				} else {
					map[c.x][c.y].tmp += c.cnt + c.tmp;
					list.remove(c);
				}
			}
		}
		
		// 병합된 군집의 미생물 수 누적
		size = list.size();
		for (int i = 0; i < size; i++) {
			Crew c = list.get(i);
			c.cnt += c.tmp;
			c.tmp = 0;
		}
	}
	
	static int count() {
		int cnt = 0;
		
		int size = list.size();
		for (int i = 0; i < size; i++) {
			cnt += list.get(i).cnt;
		}
		
		return cnt;
	}
	
}
