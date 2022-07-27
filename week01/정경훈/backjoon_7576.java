import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class backjoon_7576 {
    static int[] dy = {0, 0, 1, -1};
	static int[] dx = {-1, 1, 0, 0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int M = sc.nextInt();
		int N = sc.nextInt();
		
		int[][] map = new int[N][M];
		Queue<Tomato> start = new LinkedList<>(); // 시작할 좌표 입력
		int cnt = 0; // 바꿔야할 토마토 개수
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 0) cnt++;
				if(map[i][j] == 1) start.add(new Tomato(j,i,0));
			}
		}
		
		int check = 0; // 바뀌는 토마토 개수 체크
		int rst = 0;
		while(!(start.isEmpty())) {
			Tomato t = start.poll();
			int y = t.getY();
			int x = t.getX();
			int nday = t.getDay();
			rst = nday;
			for(int i=0;i<4;i++) {
				int ny = y+dy[i];
				int nx = x+dx[i];
				if(ny<0 || nx<0 || ny>=N || nx>=M) continue;
				if(map[ny][nx]==0) {
					map[ny][nx] = 1;
					check++;
					start.add(new Tomato(nx, ny, nday+1));
				}
			}
		}
		
		if(check != cnt) System.out.println(-1);
		else System.out.println(rst);
		
		sc.close();
	}
	
	static class Tomato{
		private int x;
		private int y;
		private int day;
		
		public Tomato(int x, int y, int day) {
			super();
			this.x = x;
			this.y = y;
			this.day = day;
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

		public int getDay() {
			return day;
		}

		public void setDay(int day) {
			this.day = day;
		}
		
		
	}
}
