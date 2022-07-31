import java.util.Scanner;

public class GameDevelopment {

	// 북 동 남 서
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	static int N;
	static int M;
	static  boolean[][] visited;
	
	static int moving(int y, int x, int d) {
		int cnt = 1;
		visited[y][x] = true;
		for(int i=0;i<4;i++) {
			d = (d+3)%4;
			int ny = y + dy[d];
			int nx = x + dx[d];
			if(ny<0 || nx<0 || ny>=N || nx>=M) continue;
			if(!visited[ny][nx]) {
				visited[ny][nx] = true;
				cnt += moving(ny,nx,d);
			}
		}
		return cnt;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt(); // 세로
		M = sc.nextInt(); // 가로
		
		visited = new boolean[N][M];
		
		int A = sc.nextInt(); // y좌표
		int B = sc.nextInt(); // x좌표
		int d = sc.nextInt(); // 바라보는 방향
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				int input = sc.nextInt();
				if(input == 1) {
					visited[i][j] = true;
				}
			}
		}
		System.out.println(moving(A,B,d));
		
		
		sc.close();
	}

}
