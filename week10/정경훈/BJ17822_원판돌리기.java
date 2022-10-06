package baekjoon.원판돌리기_17822;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N, M, T, sum, cnt;
	static int[][] circle;
	static int[] startIdx;
	
	static Set<Node> set = new HashSet<>();
	
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1 ,1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		circle = new int[N+2][M];
		startIdx = new int[N+2]; // 0 dummy , N+1도 dummy
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				circle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			lotation(x, d, k);
		}
		
		sum = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				if(circle[i][j] == 0) continue;
				sum += circle[i][j];
			}
		}
		
		System.out.println(sum);
		
		
	}
	
	static void lotation(int x, int d, int k) {
		for (int i = 1; i <= N; i++) {
			// x의 배수인 원판을 회전
			if(i%x == 0) {
				// d  == 0 이면 시계
				if( d == 0 ) {
					// k만큼 회전
					startIdx[i] = ((startIdx[i]-k)+M)%M;
				}else { // d == 1이면 반시계로 k만큼 회전
					startIdx[i] = (startIdx[i]+k)%M;
					
				}
			}
		}
		
		
		// 인접하는 수가 있으면 지우기
		// 인접하는 수가 없었으면 평균구해서 높으면 -1 낮으면 +1
		
		set.clear();
		find();
		sum = 0;
		cnt = 0;
		if(set.size() == 0) {
			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < M; j++) {
					if(circle[i][j] == 0) continue;
					sum += circle[i][j];
					cnt++;
				}
			}
			
			double avg = (double)sum / (double)cnt;
			
			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < M; j++) {
					if(circle[i][j] == 0) continue;
					if(avg>circle[i][j]) circle[i][j]++;
					else if(avg<circle[i][j]) circle[i][j]--;
				}
			}
		}else {
			Iterator<Node> it = set.iterator();
			while (it.hasNext()) {
				Node n = it.next();
				circle[n.y][n.x] = 0; // 0 == 제거
			}
		}
		
	}
	
	static void find() {
		// 1,0 지점 부터 시작		
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				int cy = i;
				int cx = startIdx[i] + j;
				cx = (cx+M)%M;
				if(circle[cy][cx] == 0 ) continue;
				for (int d = 0; d < 4; d++) {
					int ny = i + dy[d];
					if(ny<=0 || ny>N) continue;
					int nx = j + dx[d];
					int rx = startIdx[ny] + nx;
					rx = (rx+M)%M;
					
					if(circle[ny][rx] == circle[cy][cx]) {
						set.add(new Node(ny, rx));
						set.add(new Node(cy, cx));
					}
				}
			}
		}	
	}
	
	static class Node{
		int y,x;
		Node(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
}
