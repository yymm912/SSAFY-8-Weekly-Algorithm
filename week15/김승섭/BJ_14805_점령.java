package 백준;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_14805_점령 {
	static int N, K, S, ansX, ansY, map[][];
	static PriorityQueue<Node> vq = new PriorityQueue<>(new Comparator<Node>() {

		@Override
		public int compare(Node o1, Node o2) {
			if(o1.time < o2.time) return -1;
			else if(o1.time > o2.time) return 1;
			else {
				if(o1.virus < o2.virus) return -1;
				else return 1;
			}
		}

	});
	static class Node{
		int y,x,virus, time;

		public Node(int x, int y, int virus, int time) {
			this.y = y;
			this.x = x;
			this.virus = virus;
			this.time = time;
		}

//		@Override
//		public String toString() {
//			return "Node [y=" + y + ", x=" + x + ", virus=" + virus + ", time=" + time + "]";
//		}
		

	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		vq.clear();
		
		for (int x = 0; x < N; x++) {
			st = new StringTokenizer(br.readLine());
			for (int y = 0; y < N; y++) {
				map[x][y] = Integer.parseInt(st.nextToken());
				if(map[x][y] != 0) vq.add(new Node(x, y, map[x][y], 0));
			}
		}
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		ansX = (Integer.parseInt(st.nextToken())) - 1;
		ansY = (Integer.parseInt(st.nextToken())) - 1;
		
		
//		while(!vq.isEmpty()) { // pq 출력확인 코드
//			System.out.println(vq.poll().toString());
//		}
		
		bfs();
		
		System.out.println(map[ansX][ansY] == 0 ? 0:map[ansX][ansY]);
	}
	
	static int dy[] = {-1, 1, 0, 0};
	static int dx[] = {0, 0, -1, 1};
	
	private static void bfs() {
		
		while(!vq.isEmpty()) {
			Node cur = vq.poll();
			
			if(cur.time > S-1) return;
			
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if(ny < 0 || nx < 0 || ny > N-1 || nx > N-1 || map[nx][ny] != 0) continue;
				map[nx][ny] = cur.virus;
				vq.add(new Node(nx, ny, cur.virus, cur.time+1));
			}
			
		}
		
	}

}
