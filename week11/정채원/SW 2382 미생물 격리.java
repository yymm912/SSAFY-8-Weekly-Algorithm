import java.io.*;
import java.util.*;

class Solution{
	/**
	 * 1. 이동
	 * 2. 셀 도착 체크 -> 셀이면 반토막 이동방향 반대
	 * 3. 합침 체크 -> 합, 이동방향 
	 * */
	static int T;
	static int N, M, K;
	static int[][] micro; // [K+1][y, x, num, dir]
	static List<int[]>[][] map; // micro index 입력
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new List[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) map[i][j] = new ArrayList<>();
			}
			micro = new int[K+1][5];
			for(int i=1; i<=K; i++) {
				st = new StringTokenizer(br.readLine());
				micro[i][0] = Integer.parseInt(st.nextToken());
				micro[i][1] = Integer.parseInt(st.nextToken());
				micro[i][2] = Integer.parseInt(st.nextToken());
				micro[i][3] = Integer.parseInt(st.nextToken())-1;
				micro[i][4] = i;
				map[micro[i][0]][micro[i][1]].add(micro[i]);
			}
			
			while(M-->0) {
				// 이동 && 가장자리 도착
				for(int k=1; k<K+1; k++) {
					move(k);
				}
				// 합침
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(map[i][j].size() > 1) {
							combine(i, j);
						}							
					}
				}
			}
			int sum = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!map[i][j].isEmpty()) {
						int[] m = map[i][j].get(0);
						sum += m[2];
					}
				}
			}
			System.out.println("#" + t + " " + sum);
		}
	}
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static void move(int midx) {
		if(micro[midx][2] == 0) return;
		int y = micro[midx][0];
		int x = micro[midx][1];
		int dir = micro[midx][3];
		
		// 이동
		map[y][x].remove(micro[midx]);
		micro[midx][0] = (y = y + dy[dir]);
		micro[midx][1] = (x = x + dx[dir]);
		// 가장자리
		if(x == 0 || x == N-1 || y == 0 || y == N-1) {
			micro[midx][2] /= 2;
			micro[midx][3] = (dir = (dir & 1) == 0 ? dir+1 : dir-1);
			if(micro[midx][2] == 0) { // 소멸
				return; // map에 새 위치 추가하지 않고 종료
			}
		}
		map[y][x].add(micro[midx]);
	}
	static void combine(int y, int x) {
		int numSum = 0;
		int dir = 0;
		int maxNum = 0;
		int maxIdx = 0;
		for(int idx=0; idx<map[y][x].size(); idx++) {
			int[] m = map[y][x].get(idx);
			numSum += m[2];
			if(maxNum < m[2]) {
				maxIdx = m[4];
				maxNum = m[2];
				dir = m[3];
			}
			m[2] = 0;
		}
		
		micro[maxIdx][2] = numSum;
		micro[maxIdx][3] = dir;
		map[y][x].clear();
		map[y][x].add(micro[maxIdx]);
	}
}