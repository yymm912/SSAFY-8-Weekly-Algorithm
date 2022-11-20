import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1194_달이차오른다가자 {
	static int N, M, startX, startY, ans;
	static boolean breakable;
	static char[][] map;
	static boolean[][][] visited; 
	// 방문배열에서 3차원 배열을 통해 열쇠를 가지고 있을 때 방문 여부를 체크해준다.
	/*
	 * A열쇠 겟 = 000001
	 * B열쇠 겟 = 000010
	 * C열쇠 겟 = 000100
	 * A, B 열쇠 겟 상태 = 000011
	 * A, C 열쇠 겟 상태 = 000101
	 * 총 2^6(64)까지 가능
	 * 해당 키(소문자 알파벳)를 획득하면 OR 비트연산을 통해 해당 자리에 체크해준다.
	 */
	static Queue<Node> q = new ArrayDeque<>();
	static int dy[] = {-1, 1, 0 ,0};
	static int dx[] = {0, 0, -1, 1};
	static class Node{
		
		int y, x, len, key;
		
		public Node(int y, int x, int len, int key) {
			this.y = y;
			this.x = x;
			this.len = len;
			this.key = key;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][64];
		ans = 0;
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j]=='0') {
					startX = j;
					startY = i;
				}
			}
		}
		
		bfs();
		
		System.out.println(breakable ? ans : -1);
	}

	private static void bfs() {
		
		q.add(new Node(startY, startX, 0, 0));
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			
			for (int d = 0; d < 4; d++) {
				int ny = cur.y + dy[d];
				int nx = cur.x + dx[d];
				int nKey = cur.key;
				
				if(ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx]=='#' || visited[ny][nx][cur.key]) continue;
				
				if(map[cur.y][cur.x] == '1') {
					ans = cur.len;
					breakable = true;
					return;
				}
				// 열쇠 획득
				if(map[ny][nx] >= 'a' && map[ny][nx] <= 'f') // 여섯 개의 키(a,b,c,d,e,f) 중 하나라면,
				{
					nKey |= (1 << (map[ny][nx] - 'a')); // OR 연산을 통해 알파벳에 해당하는 자리를 체크해준다. 
				}
				// 문 도달
				else if(map[ny][nx] >= 'A'  && map[ny][nx] <= 'F') 
				{
					if((cur.key & (1 << map[ny][nx] - 'A' )) == 0) continue;
				}
				// 일반 길

				visited[ny][nx][nKey] = true;
				q.add(new Node(ny, nx, cur.len+1, nKey));

			}
		}
		
		breakable = false;
		return;
	}

}
