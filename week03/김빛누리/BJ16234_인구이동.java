package week03;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 메모리: 298096	KB
 * 시간: 636 ms
 * */
public class BJ16234_인구이동 {
	static int N, L, R, day;	
	static int[][] map;
	static boolean[][] visit;
	static List<Point> union = new ArrayList<>();	// 연합 -> 나라(bfs에서 꺼내진 Point객체) 저장할 큐
	
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	private static boolean isUnionExist;	// 연합 있는지 체크
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		L = Integer.parseInt(input[1]);
		R = Integer.parseInt(input[2]);
		
		map = new int[N][N];
		visit = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			// 초기화
			isUnionExist = false;
			visit = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(visit[i][j]) continue;
					
					bfs(i, j);	// y, x 순서
				}
			}
			// 연합 없으면 빠져나옴, 연합 있으면 날짜 +1
			if(!isUnionExist) break;	
			day++;	// 하루 지남
		}
		
		System.out.println(day);
	}
	static void bfs(int y, int x) {
		Queue<Point> q = new ArrayDeque<>();	// bfs에 이용할 큐
		union.clear();
		
		// 시작점 큐에 넣기
		q.add(new Point(x,y));
		visit[y][x] = true;
		int sum = map[y][x];
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			union.add(p);	// q에서 꺼내진 나라 연합에 추가
			
			x = p.x;
			y = p.y;
			
			for(int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				// 배열 범위 밖 or 이미 방문한 곳 
				if (ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx])
					continue;
				
				// 인구차이 조건 통과 못하는 경우
				int gap = Math.abs(map[ny][nx] - map[y][x]);
				if(gap < L || gap > R) continue;
				
				// 큐에 넣기
				q.add(new Point(nx, ny));
				visit[ny][nx] = true;
				sum+=map[ny][nx];
				
			}
		}
		// 연합 있음 (2개 이상 나라가 들어가있을 경우)
		if(union.size() != 1) {
			isUnionExist = true;
			
			int unionSize = union.size();
			int population = sum/unionSize;
			
			for(int i = 0; i < unionSize; i++) {
				Point p = union.get(i);
				map[p.y][p.x] = population;
				
			}
		}
		
	}
}