package algo_study._8월2주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * bfs
 * 시작지점이 여러군데
 * => 입력받을 때 큐에 먼저 넣어주고 시작
 * */
public class BOJ_7569토마토 {

	static int[] dz = {1, -1, 0, 0, 0, 0}; //위-아래-우-하-좌-상
	static int[] dy = {0, 0, 0, -1, 0, 1};
	static int[] dx = {0, 0, 1, 0, -1, 0};
	static int lenX, lenY, lenZ, ans, size;
	static int[][][] map;
	static boolean isRipened = true;
	static Queue<Node> queue = new ArrayDeque<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		lenX = Integer.parseInt(st.nextToken());
		lenY = Integer.parseInt(st.nextToken());
		lenZ = Integer.parseInt(st.nextToken());
		map = new int[lenZ][lenY][lenX];
		for (int z = 0; z < lenZ; z++) {
			for (int y = 0; y < lenY; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < lenX; x++) {
					map[z][y][x] = Integer.parseInt(st.nextToken());
					//큐에 익은토마토(시작지점) 넣고 시작 => 시작지점이 여러개
					if (map[z][y][x] == 1) queue.add(new Node(z, y, x)); 
					if (map[z][y][x] == 0) isRipened = false; //모두 익은상태면 0출력하고 끝
				}
			}
		}
		
		if (isRipened) { //모두 익었으면 0 출력하고 끝
			System.out.println(0);
			return;
		}
		
		bfs();
		check(); //토마토 상태 체크
		
		System.out.println(ans);
	}
	
	static void bfs() {
		//시작좌표 이미 큐에 들어가있음, map 값이 0인 녀석만 보기 때문에 visited 필요없음
		int cnt = queue.size();		//처음의 큐 크기(시작지점 개수) 저장
		while (!queue.isEmpty()) {
			if (cnt == 0) {			//cnt가 0이면 시작지점 다 뺐다 => 하루가 지났다
				cnt = queue.size();	//시작지점 다시 set
				ans++;	//하루 지남
			}
			Node node = queue.poll();
			cnt--;	//큐에서 하나씩 뺄 때마다 1씩 줄여줌
			for (int d = 0; d < 6; d++) {
				int nz = node.z + dz[d];
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];
				if (nz < 0 || nz >= lenZ || nx < 0 || nx >= lenX || ny < 0 || ny >= lenY 
						|| map[nz][ny][nx] != 0) continue;	//범위체크
				queue.add(new Node(nz, ny, nx)); //큐에 넣어줌
				map[nz][ny][nx] = 1; //토마토 익음
			}
		}
	}
	
	static void check() {
		for (int z = 0; z < lenZ; z++) {
			for (int y = 0; y < lenY; y++) {
				for (int x = 0; x < lenX; x++) {
					if (map[z][y][x] == 0) ans = -1; //안익은게 하나라도 남았다 -> 바로 -1
				}
			}
		}
	}
	
	static class Node {
		int x, y, z;
		public Node(int z, int y, int x) {
			this.z = z;
			this.y = y;
			this.x = x;
		}
	}
}
