package week012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 원자소멸 시뮬레이션
public class Cswea_5648_bnuri00 {
	static class Atom{
		int x, y, d, k;
		boolean alive = true;
		public Atom(int x, int y, int d, int k) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
			this.k = k;
		}
		@Override
		public String toString() {
			return "Atom [x=" + x + ", y=" + y + ", d=" + d + ", k=" + k + ", alive=" + alive + "]";
		}
		
	}
	static int N, eSum;
	static ArrayList<Atom> atomList = new ArrayList<>();
	static ArrayList<Atom> removeList = new ArrayList<>();
	
	static boolean[][] map = new boolean[4001][4001];
	
	// 상 하 좌 우
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			// init
			eSum = 0;
			atomList.clear();
			removeList.clear();
			resetMap();
			
			N = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				// 원점 왼쪽 아래로 세팅
				int x = 2*Integer.parseInt(st.nextToken())+2000;
				int y = 2*Integer.parseInt(st.nextToken())+2000;
				int d = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				atomList.add(new Atom(x, y, d, k));
			}
			
			simul();
			
			System.out.println("#"+t+" "+eSum);
		}
		
	}

	private static void resetMap() {
		for (int i = 0; i < 4001; i++) {
			for (int j = 0; j < 4001; j++) {
				map[i][j] = false;
			}
		}
		
	}

	static void simul() {
		while(true) {
			for (int i = 0; i < atomList.size(); i++) {
				Atom atom = atomList.get(i);
				
				// 이미 죽은애
				if(!atom.alive) continue;
				
				map[atom.y][atom.x] = false;
				
				atom.y += dy[atom.d];
				atom.x += dx[atom.d];
				
				// 범위 벗어남
				if(atom.y < 0 || atom.x < 0|| atom.y > 4000 || atom.x > 4000) {
					atom.alive = false;
				}
				else if(map[atom.y][atom.x]) { // 부딪힘(다른 원자가 이미 있음)
					// 이동 완료한 원자랑 부딪혔으면
					// (자기보다 이전 원자)
					// 얘도 죽음
					if(bomb(atom.x,atom.y, i)) atom.alive = false;
				}
				else map[atom.y][atom.x] = true;
			}
			
			
			// removeList 이용해서 없어져야 할 원자 없애고
			// 맵에서도 지우기
			removeAtom();
			
			if(atomList.size() <= 1) break;
		}
	}

	private static void removeAtom() {
		int len = atomList.size() -1;
		for (int i = len; i >=0; i--) {
			Atom atom = atomList.get(i);
			
			// 살아있으면 아무것도 안함
			if(atom.alive) continue;
			
			atomList.remove(i);
			
			// 범위 밖 (멀리 간 친구)
			if(atom.y < 0 || atom.x < 0|| atom.y > 4000 || atom.x > 4000) continue;
			else {	// 부딪힌 친구
				eSum += atom.k;
				map[atom.y][atom.x] = false;
			}
			
		}
	}
	private static boolean bomb(int x, int y, int idx) {
		for (int i = idx-1; i >= 0; i--) {
			Atom atom = atomList.get(i);
			if(atom.x==x&&atom.y==y) {
				atom.alive = false;
				return true;
			}
		}
		return false;
	}

}
