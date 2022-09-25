package week07;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 풀이시간: 1시간 반
 * 참고: X
 * 
 * 14700KB	136ms
 * 
 * <풀이>
 * - bfs
 * - 먹을 수 있는 물고기 같은 길이 다 리스트에 넣기
 * - 정렬
 * - 맨 앞에거 먹고 아기상어 위치 업데이트
 * 
 * */
public class BJ16236_아기상어 {
	static int N, bbShark = 2, eatFish, time;	// 맵 크기, 아기상어 크기, 먹은 물고기수, 시간
	static int[][] map;
	
	static Point sharkLoc;
	static ArrayList<Point> fish = new ArrayList<>();
	
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int input = Integer.parseInt(st.nextToken());
				if(input == 9) {
					sharkLoc = new Point(j, i);
				}
				else {
					map[i][j] = input;
				} 
			}
		}
		
		while(true) {
			
			int t = bfs();
			if(t == 0) break;
			
			time += t;
			
			Collections.sort(fish, (p1, p2) -> p1.y==p2.y? p1.x-p2.x: p1.y-p2.y);
			eatFish++;
			sharkLoc.setLocation(fish.get(0).x, fish.get(0).y);
			map[sharkLoc.y][sharkLoc.x] = 0;
			
			if(eatFish == bbShark) {
				eatFish = 0;
				bbShark++;
			}
		}
		
		System.out.println(time);

	}
	
	// 돌아다니며 먹을 수 있는 물고기 탐색
	// 시간 리턴
	static int bfs() {
		fish.clear();
		
		boolean[][] visit = new boolean[N][N];
		Queue<Point> q = new ArrayDeque<>();
		q.add(sharkLoc);
		
		int t = 0;
		boolean canEat = false;
		while(!q.isEmpty()) {
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				Point p = q.poll();
				
				for (int d = 0; d < 4; d++) {
					int ny = p.y + dy[d];
					int nx = p.x + dx[d];
					
					if(ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx] || map[ny][nx] > bbShark) continue;
					
					if(map[ny][nx]!=0 && map[ny][nx] < bbShark) {
						fish.add(new Point(nx, ny));
						canEat = true;
						continue;
					}
					
					q.add(new Point(nx, ny));
					visit[ny][nx] = true;
				}
				
			}	
			t++;
			
			if(canEat) return t;
		}
		
		return 0;	
	}
}
