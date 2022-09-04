

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SW_1767_프로세서연결하기 {

	static int[]dy = {0,-1,0,1,0};
	static int[]dx = {-1,0,1,0,0};
	static int N, all_cnt, max_cnt, min_len, idx, tmp;
	static List<Core> cores;
	static int[][] map, map_origin;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			//초기화
			cores = new ArrayList<> ();
			max_cnt = Integer.MIN_VALUE;
			min_len = Integer.MAX_VALUE;
			all_cnt = 0;
			idx = 0;
			
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			map_origin = new int[N][N];
			map_origin = map;
			
			for (int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < N; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
					if (map[y][x] == 1) { //코어를 만났을 때
						if (x == 0 || y == 0 || x == N-1 || y == N-1) { //가장자리에 있으면
							map[y][x] = 2; //연결된 걸로 하겠다
							cores.add(new Core(y, x, true));
						} else cores.add(new Core(y, x, false));
					}
				}
			}
			all_cnt = cores.size(); //총 코어 개수
			//입력 끝
			
			//탐색
			dfs(idx, 0, 0);
			System.out.println("#"+t+" "+min_len);
			
		}
	}
	
	static void dfs(int idx, int cnt, int len) {
		//기저조건
		if (idx == all_cnt) { //코어 전부 다 봤을 때
			
			if (cnt > max_cnt) {
				max_cnt = cnt;
				min_len = len;
			}
			if (cnt == max_cnt) {
				if (len < min_len) {
					max_cnt = cnt;
					min_len = len;
				}
			}
			//맵 원상복구
			map = map_origin;
			return;
		}
		Core core = cores.get(idx);
		if (map[core.y][core.x] == 2) { //이미 연결된 애들이면 (가장자리녀석들)
			dfs(idx+1, cnt, len);
		}
		
		else {
			for (int d = 0; d < 5; d++) {
				if (!check(core.y, core.x, d)) continue; //겹치는 부분 체크

				core.s = true;
				if (d != 4) {
					dfs(idx+1, cnt+1, len + tmp);
				}
				else {
					dfs(idx+1, cnt, len);
				}
				erase(core.y, core.x, d);
			}
			core.s = false;
		}
	}
	
	static void erase(int y, int x, int d) {
		switch(d) { //최소정렬하고 색까지 칠했다. 그 줄 색칠했다가 아니면 초기화를 해줘야 한다.
		case 0:
			//for 왼쪽으로 체크
			for (int i = x-1; i >= 0; i--) { 
				if (map[y][i] == 3) map[y][i] = 0;
			}
			break;
		case 1:
			//for 위로 체크
			for (int i = y-1; i >= 0; i--) {
				if (map[i][x] == 3) map[i][x] = 0;
			}
			break;
		case 2:
			//for 오른쪽으로 체크
			for (int i = x+1; i < N; i++) {
				if (map[y][i] == 3) map[y][i] = 0;
			}
			break;
		case 3:
			//for 아래로 체크
			for (int i = y+1; i < N; i++) {
				if (map[i][x] == 3) map[i][x] = 0;
			}
			break;
		case 4:
			
			break;
		}
	}
	
	static boolean check(int y, int x, int d) { 
		tmp = 0;
		switch(d) { //최소정렬하고 색까지 칠했다. 그 줄 색칠했다가 아니면 초기화를 해줘야 한다.
		case 0:
			//for 왼쪽으로 체크
			for (int i = x-1; i >= 0; i--) {
				if (map[y][i] != 0) return false;
			}
			for (int i = x-1; i >= 0; i--) { 
				map[y][i] = 3;
				tmp++;
			}
			break;
		case 1:
			//for 위로 체크
			for (int i = y-1; i >= 0; i--) {
				if (map[i][x] != 0) return false;
			}
			for (int i = y-1; i >= 0; i--) {
				map[i][x] = 3;
				tmp++;
			}
			break;
		case 2:
			//for 오른쪽으로 체크
			for (int i = x+1; i < N; i++) {
				if (map[y][i] != 0) return false;
			}
			for (int i = x+1; i < N; i++) {
				map[y][i] = 3;
				tmp++;
			}
			break;
		case 3:
			//for 아래로 체크
			for (int i = y+1; i < N; i++) {
				if (map[i][x] != 0) return false;
			}
			for (int i = y+1; i < N; i++) {
				map[i][x] = 3;
				tmp++;
			}
			break;
		case 4:
			return true;
		}
		return true;
	}
	
	static class Core {
		int y, x;
		boolean s; //s:연결상태
		public Core(int y, int x, boolean s) {
			this.y = y;
			this.x = x;
			this.s = s;
		}
	}
	
	static class Dir {
		int len, d;
		public Dir(int len, int d) {
			this.len = len;
			this.d = d;
		}
	}
}
