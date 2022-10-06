package baekjoon.뱀_3190;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, K, L, X, sy, sx, d, ans;
	static char C;
	static boolean ok;
	static int[][] map;
	static Queue<Node> body = new ArrayDeque<>(); // 뱀의 경로
	
	// 우 - 하 - 좌 - 상 : 오른쪽으로 90도 회전시 ++
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		ans = 0;
		
		map = new int[N+1][N+1];
		
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			map[y][x] = 1;
		}
		
		L = Integer.parseInt(br.readLine());
		X = 0;
		// 뱀의 위치
		sy = 1; 
		sx = 1;
		body.offer(new Node(sy, sx));
		// 시작은 오른쪽으로 시작
		d = 0;
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int newX = Integer.parseInt(st.nextToken());
			C = st.nextToken().charAt(0);
			
			move(newX);
			
			if(ok) {
				System.out.println(ans);
				break;
			}
		}
		if(!ok) {
			move(0);
			System.out.println(ans);
		}
		
	}
	
	static void move(int x) {
		int dist;
		if(x == 0) {
			dist = N;
		}else {
			dist = x - X;// 실제 이동거리
			X = x;
		}
		
		for (int i = 0; i < dist; i++) {
			ans++;
			sy = sy + dy[d];
			sx = sx + dx[d];
			
			// 벽을 만나면 종료
			if(sy<=0 || sx<=0 || sy>N || sx>N) {
				ok = true;
				return;
			}
			
			// 자신의 몸을 만나면 종료
			for (int j = 0; j < body.size(); j++) {
				Node n = body.poll();
				if(n.y == sy && n.x == sx) {
					ok = true;
					return;
				}
				body.offer(n);
			}
			
			// 머리 이동
			body.offer(new Node(sy, sx));
			
			// 사과가 있는지 확인
			if(map[sy][sx] == 1) { // 사과가 있으면 사과 냠냠 후 몸길이가 늘어남
				map[sy][sx] = 0;
			}else { // 사과가 없으면 머리는 이동하고 몸은 줄어듬
				body.poll();
			}
		}
		
		// 이동이 끝난 후 방향 전환
		// 오른쪽
		if(C == 'D') {
			d++;
		}else { // 왼쪽
			d--;
		}
		d = (d+4)%4;
	}
	
	static class Node{
		int y,x;
		Node(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
}
