import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
	static int N, M;
	static int[][] map;
	// 북, 동, 남, 서 (시계방향)
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static int dir;
	static int cx, cy;
	public static void init() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		cx = Integer.parseInt(st.nextToken());
		cy = Integer.parseInt(st.nextToken());
		dir = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
	}
	public static void print() { // 디버깅용
		for(int i=0; i<N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}
	public static void main(String[] args) throws Exception{
		init();
		// 이미 간 곳은 -1로 표시
		map[cy][cx] = -1;
		int stepCnt = 1;
		// 1. 왼쪽부터 4방향 검사
		// 2. 갈 수 있으면 전진
		// 3. 갈 수 없으면, 갈 수 있을 때 까지 회전
		// 4. (for 밖)모두 못 가면 -> 뒤 칸 바다인지 확인 -> 바다이면 stop
		while(true) {
			dir = (dir+3)%4; // 반시계 방향으로 회전
			boolean go = false; // 전진했는지 여부 체크
			for(int d=0; d<4; d++) {
				int nx = cx + dx[dir];
				int ny = cy + dy[dir];
				dir = (dir+3) % 4;
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if(map[ny][nx] == 1) continue; // 바다인 경우
				if(map[ny][nx] == -1) continue; // 이미 간 곳
				cx = nx;
				cy = ny;
				map[cy][cx] = -1;
				stepCnt ++;
				go = true;
				break;
			}
			if(!go) {
				int nx = cx + dx[(dir+2)%4];
				int ny = cy + dy[(dir+2)%4];
				if(map[ny][nx] == 1) break;
				cx = nx;
				cy = ny;
			}
//			print(); // 디버깅 용, map 출력
		}
		System.out.println(stepCnt);
		
	}
}