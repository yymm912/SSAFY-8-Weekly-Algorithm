package baekjoon.아기상어2_17086;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, ans;
	static int[][] map;
	static int[][] safeMap;
	static List<Shark> list = new ArrayList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		safeMap = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				safeMap[i][j] = Integer.MAX_VALUE;
				if(n == 1) {
					safeMap[i][j] = 0;
					Shark s = new Shark(i,j);
					list.add(s);
				}
			}
		}
		
		
		start();
		findMax();
		
		System.out.println(ans);
		
	}
	
	static void start() {
		for (Shark shark : list) {
			int y = shark.y;
			int x = shark.x;
			int dist = 0;
			while(true) {
				boolean ok = false;
				dist++;
				// #1. 좌상단 -> 오른쪽으로 쭉 위쪽으로 뚫고 나간 경우만 예외 처리
				int ny = y-dist;
				int nx = x-dist;
				if(ny>=0) {
					for (int i = 0; i < dist*2; i++) {
						nx++;
						if(nx<M && nx>=0) {
							safeMap[ny][nx] = Math.min(safeMap[ny][nx], dist);
							ok = true;
						}
						
					}
				}
				// #2. 우상단 -> 아래로 쭈욱  왼쪽으로 뚫고 나간 경우만 예외 처리
				ny = y-dist;
				nx = x+dist;
				if(nx<M) {
					for (int i = 0; i < dist*2; i++) {
						ny++;
						if(ny<N && ny>=0) {
							safeMap[ny][nx] = Math.min(safeMap[ny][nx], dist);
							ok = true;
						}
						
					}
				}
				
				// #3. 우하단 -> 왼쪽으로 쭈우욱 아래로 뚫고 간 경우만 예외 처리
				ny = y+dist;
				nx = x+dist;
				if(ny<N) {
					for (int i = 0; i < dist*2; i++) {
						nx--;
						if(nx<M && nx>=0) {
							safeMap[ny][nx] = Math.min(safeMap[ny][nx], dist);
							ok = true;
						}
						
					}
				}
				
				// #4. 좌하단 -> 위쪽으로 쭈우우욱 왼쪽으로 뚫고 간 경우만 예외 처리
				ny = y+dist;
				nx = x-dist;
				if(nx>=0) {
					ok = true;
					for (int i = 0; i < dist*2; i++) {
						ny--;
						if(ny<N && ny>=0) {
							safeMap[ny][nx] = Math.min(safeMap[ny][nx], dist);
							ok = true;
						}
						
					}
				}
				if(!ok) break;
			}
		}
	}
	
	static void findMax() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				ans = Math.max(ans, safeMap[i][j]);
			}
		}
	}
	
	static class Shark{
		int y;
		int x;
		Shark(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
}
