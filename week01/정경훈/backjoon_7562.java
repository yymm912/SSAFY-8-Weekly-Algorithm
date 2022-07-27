import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class backjoon_7562 {
    static int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int tc=0;tc<T;tc++) {
			int I = sc.nextInt();
			int sy = sc.nextInt();
			int sx = sc.nextInt();
			int ey = sc.nextInt();
			int ex = sc.nextInt();
			int cnt = 0;
			boolean[][] visited = new boolean[I][I];
			visited[sy][sx] = true;
			Queue<Knight> q = new LinkedList<>();
			q.add(new Knight(sx,sy,0));
			while(!(q.isEmpty())) {
				Knight k = q.poll();
				int x = k.getX();
				int y = k.getY();
				int move = k.getCnt();
				if(x == ex && y == ey) {
					cnt = move;
					break;
				}
				for(int i=0;i<8;i++) {
					int nx = x +  dx[i];
					int ny = y +  dy[i];
					if(ny<0 || nx<0 || ny>=I || nx>=I) continue;
					if(!visited[ny][nx]) {
						visited[ny][nx] = true;
						q.add(new Knight(nx,ny,move+1));
					}
				}
			}
			System.out.println(cnt);
		}
		
		sc.close();
	}

	static class Knight{
		private int x;
		private int y;
		private int cnt;
		
		public Knight(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public int getCnt() {
			return cnt;
		}

		public void setCnt(int cnt) {
			this.cnt = cnt;
		}
		
		
	}
}
