package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * bfs
 * 시간: 2.9874ms => 2.7689ms(예제5) 별차이 없는디..?
 * 		(시간초과)
 * -----
 * 시간초과
 * 문제점:  1. 모든 지점의 bfs가 끝난 후에 인구이동
 * 		  2. 인구이동할 지점을 map에 표시 => 인구이동할 때 NxN 모두 탐색
 * 해결방법: 1. 각 지점의 bfs가 끝난 후에 인구이동
 * 		  2. 인구이동할 지점의 좌표만 ArrayList에 저장 => 인구이동할 때 ArrayList 크기만큼만 탐색
 * */
public class BOJ_16234인구이동3 {

	static boolean move_s; //move_s:전체 인구이동 유무 상태
	static Queue<Node> queue = new ArrayDeque<>();
	static int[][] map;
	static boolean[][] visited;
	static int N, min, max, day; //day:인구이동 발생 일수
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	static List<Node> union = new ArrayList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		min = Integer.parseInt(st.nextToken());
		max = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while (true) { 	//더이상 인구이동이 없을 때까지
			visited = new boolean[N][N]; 
			move_s = false; //한번이라도 인구이동이 일어나면 true
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) //각 지점마다 bfs (방문하지 않은 지점만)
						bfs(i, j);
				}
			}
			
			if (!move_s) break; //move_s: 한번이라도 인구이동이 일어났으면 true
			day++;
		}
		System.out.println(day);
	}
	
	static void bfs(int y, int x) {
		queue.offer(new Node(y, x));
		visited[y][x] = true;
		union.add(new Node(y, x)); //시작점 넣어줘야함. 인구이동 지점 아니면 move()에서 어차피 지워짐
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			for (int d = 0; d < 4; d++) {
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];
				if (ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx]) continue;
				
				//인접 나라와의 인구 차이가 min 이상 max 이하일 때
				if (Math.abs(map[ny][nx] - map[node.y][node.x]) >= min 
						&& Math.abs(map[ny][nx] - map[node.y][node.x]) <= max) {
					
					queue.offer(new Node(ny, nx));
					visited[ny][nx] = true; 		//방문처리
					union.add(new Node(ny, nx));	//인구이동할 좌표를 ArrayList에 추가
					move_s = true;					//한번이라도 이동이 일어났으면 true
				}
			}
		}
		move(); //인구이동
	}
	
	static void move() {
		int sum = 0;
		for (int i = 0; i < union.size(); i++) { // 좌표 저장한 ArrayList크기만큼 돈다
			Node n = union.get(i); // 좌표 하나씩 꺼내서 더해줌
			sum += map[n.y][n.x];
		}
		int cnt = union.size();
		for (int i = 0; i < union.size(); i++) {
			Node n = union.get(i);
			map[n.y][n.x] = sum / cnt; // 인구이동 완
		}
		union.removeAll(union); // 초기화 해줘야함!!! 다음 구역에서 재활용
	}
	
	static class Node {
		int y, x;
		public Node (int y, int x){
			this.y = y;
			this.x = x;
		}
	}
}
