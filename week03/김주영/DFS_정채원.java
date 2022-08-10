import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Test2 {
	/**
	 * < 문제 > 
	 * 10x10 크기의 미로가 있습니다.
	 * 시작점 (0,0)에서 출구(9,9)까지 탈출하는 최단 시간을 찾으세요.
	 * < 조건 >
	 * - 한 번 이동할 때, 상하좌우로 이동할 수 있습니다.
	 * - 한 칸 이동할 때마다 1씩 시간이 증가합니다.
	 * - 벽으로 막힌 곳은 이동할 수 없습니다.
	 * - 갈 수 있는 길은 0, 벽으로 막힌 곳은 1입니다.
	 * */
    static int[][] map = { 
            {0,  1,  0,  0,  0,  0,  1,  1,  1,  1},
            {0,  1,  0,  1,  0,  0,  0,  0,  0,  1},
            {0,  0,  0,  0,  1,  1,  0,  1,  0,  1},
            {0,  1,  1,  1,  1,  0,  0,  1,  0,  0},
            {0,  0,  0,  0,  1,  0,  1,  0,  1,  0},
            {0,  1,  1,  0,  1,  0,  1,  0,  0,  0},
            {0,  1,  0,  0,  0,  0,  1,  0,  1,  0},
            {0,  0,  0,  1,  1,  0,  0,  1,  0,  0},
            {0,  1,  0,  1,  0,  1,  0,  0,  0,  1},
            {1,  0,  0,  1,  0,  1,  0,  1,  0,  0},
    };
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    
    static int[][] dist=new int[10][10];
    
    public static void main(String[] args) {
        
    	for (int i=0; i<10; i++)
    		Arrays.fill(dist[i], -1);
    	
    	dist[0][0]=0; // 시작점 체크
    	System.out.println("최단 시간: "+bfs (0,0));
    	
    	//print();
    	
    }
    
    static int bfs (int sy, int sx) {
    	Queue <Node> queue=new ArrayDeque<>();
    	queue.add(new Node (sy, sx,0));
    	
    	while (!queue.isEmpty()) {
    		Node cur=queue.poll();
    		
    		if (cur.y==9 && cur.x==9)
    			return cur.cnt;
  
    		for (int d=0; d<4; d++) {
    			int ny=cur.y+dy[d];
    			int nx=cur.x+dx[d];
    			
    			if (ny<0 || nx<0 || ny>=10|| nx>=10 || dist[ny][nx]!=-1) continue;
    			
    			if (map[ny][nx]==0) {
	    			dist[ny][nx]=dist[cur.y][cur.x]+1;
	    			queue.add(new Node (ny, nx, cur.cnt+1));
    			}
    		}
    	}
    	return -1;
    	
    }
    
    static class Node{
        int y, x, cnt;
        public Node(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
        @Override
        public String toString() {
            return "Node [y=" + y + ", x=" + x + "]";
        }
    }
    
    static void print () {
    	for (int i=0; i<10; i++) {
    		for (int j=0; j<10; j++) {
    			System.out.printf("%3d", dist[i][j]);
    		}
    		System.out.println();
    	}
    }
}