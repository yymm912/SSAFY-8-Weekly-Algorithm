import java.io.*;
import java.util.*;

public class Solution{
	static int T, N;
	/**
	 * 1. 0~4000 을 벗어나면 -> 영원히 벗어남
	 * 
	 * 충돌 시뮬
	 * 0.5초 고려 -> 좌표를 2배로 늘림.
	 * 
	 * 죽지 않은 원자들에 대해,
	 * 1. map에서 현재 위치를 0으로(초기화), 다음 위치를 nx, ny에 기록, map에 이동한 위치 cnt++
	 * 		좌표를 *2로 늘려서 -> 초기화와 동시에 이동 cnt를 표시해도 문제X (0.5초만에 다른 원자의 위치로 이동하는 경우가 없기 때문)
	 * 2. 모든 갱신 위치에 대해, map에 기록하기 (해당 위치에 자기 idx넣기, 다른 idx가 있으면 -k표시)
	 * 3. map에 방출될 에너지들 -> energy에 더하기, map=0만들기, 방출된 원자 삭제하기.
	 * 
	 * 실패 원인
	 * 1. 방출된 원자를 삭제하지 않고 플래그로 표시하니까 시간초과.
	 * */
	static class Atom{
		int x, y, d, k;
		public Atom(int x, int y, int d, int k) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.k = k;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
	
			// atoms, map을 전역변수로 돌리니까 시간초과뜸;;
			Atom[] atoms = new Atom[N+1];
			int[][] map = new int[4001][4001]; // 0 ~ 4000
			for(int i=1; i<=N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken())*2 + 2000;
				int y = Integer.parseInt(st.nextToken())*2 + 2000;
				atoms[i] = new Atom(x, y, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				map[y][x] = i;
			}
			
			int I = 0;
			int energy = 0;
			int len = N;
			final int MAX = 4001;
			while(I++ < MAX) {
				len = move(atoms, map, len);
				
				// map에 표시
				for(int i=1; i<=len; i++) {
					Atom a = atoms[i];
					
					if(map[a.y][a.x] > 1) { // 2개 이상 충돌한 경우, 방출될 에너지를 표시
						map[a.y][a.x] = -a.k;
					} else if(map[a.y][a.x] < 0){ // 다른 atom이 충돌표시를 함
						map[a.y][a.x] -= a.k;
					} 
				}
				// -cnt인 것들 -> energy에 더함 & die 표시
				for(int i=len; i>0; i--) {
					Atom a = atoms[i];
					
					if(map[a.y][a.x] <= 0) { // 충돌했으면 
						energy += -map[a.y][a.x];
						map[a.y][a.x] = 0; 
						
						// delete
						Atom tmp = atoms[len];
						atoms[len] = atoms[i];
						atoms[i] = tmp;
						len--;
					}
				}
			}
			System.out.println("#" + tc + " " + energy);
		}
	}
	
	static boolean oor(int x, int y) {
		final int MAX = 4001;
		return x < 0 || x >= MAX || y < 0 || y >= MAX;
	}
	static int move(Atom[] atoms, int[][] map, int len) {
		int[] dx = {0, 0, -1, 1};
		int[] dy = {1, -1, 0, 0};
		
		for(int i=len; i>0; i--){
			Atom a = atoms[i];
			int nx = a.x + dx[a.d];
			int ny = a.y + dy[a.d];
			map[a.y][a.x] =0;
			if(oor(nx, ny)) { // 범위 넘어섬				
				// delete
				Atom tmp = atoms[len];
				atoms[len] = atoms[i];
				atoms[i] = tmp;
				len--;
				continue;
			}
			map[ny][nx]++;
			// a.y a.x 갱신
			a.y = ny;
			a.x = nx;				
		}
		return len;
	}
}