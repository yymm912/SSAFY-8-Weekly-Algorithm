import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	static int N, L, R;
	static int[][] land;
	static boolean[][] visit;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		land = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				land[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visit = new boolean[N][N];
		
		/**
		 * 전체 land를 돌면서 BFS -> 연합 구함
		 * 		-> BFS를 하면서 visit에 표시 (전체 land돌 때, 이미 돈 곳은 제외 /)
		 * 		-> 연합은 따로 list에 저장, -> 인구수 계산 후 갱신
		 * 	-> 더이상 인구조정 안할 때 까지 반복
		 * */
		
		int day = 0;
		boolean isShared = true;
		while(isShared) {
			isShared = false;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					// 시작점 (i,j)로부터 연합 구하기 & 인구조정 (연합은 visit 표시하기)
					if(!visit[i][j] && bfs(j, i)) {
						isShared = true;
					}
				}
			}
			if(isShared) day++;
			// visit 초기화
			for(int i=0; i<N; i++) Arrays.fill(visit[i], false);
		}
		System.out.println(day);
		
	}
	static void print() {
		for(int i=0; i<N; i++) {
			System.out.println(Arrays.toString(land[i]));
		}
		System.out.println();
	}
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static boolean bfs(int x, int y) { // 인구 조정 있으면 true
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(new Node(x, y));
		visit[y][x] = true; // visit 체크 잘하기
		
		List<Node> union = new ArrayList<>(); // 연합 저장
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			union.add(node);
			
			for(int d=0; d<4; d++) {
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];
				if(nx < 0 || nx >= N || ny < 0 || ny >= N || visit[ny][nx]) continue;
				
				int sub = Math.abs(land[ny][nx] - land[node.y][node.x]);
				if(sub < L || sub > R) continue;
				queue.offer(new Node(nx, ny));
				visit[ny][nx] = true;
			}
		}
		if(union.size() == 1) return false;
		
		// 인구 계산
		int people = 0;
		for(Node u : union) {
			people += land[u.y][u.x];
		}
		people /= union.size();
		// 인구 조정
		for(Node u : union) {
			land[u.y][u.x] = people; 
		}
		
		return true;
	}
}
class Node{
	int x, y;
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "Node [x=" + x + ", y=" + y + "]";
	}
}