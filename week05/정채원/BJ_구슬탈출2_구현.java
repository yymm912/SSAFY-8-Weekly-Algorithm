import java.io.*;
import java.util.*;
public class Main{
	static int N, M;
	static char[][] map;
	static int hx, hy;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][];
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				if(map[i][j] == 'O') { hx = j; hy = i; }
			}
		}
		/**
		 * 실패조건 :
		 * - (기울이는 방향에) 구멍과 벽 사이에 파란구슬이 있으면 안된다.
		 * 
		 * 성공조건 :
		 * - (10번 이내로) 기울이는 방향의, 구멍과 벽 사이에 빨간 구슬이 있어야 한다.
		 * 		- 근데 파란구슬도 같이 있으면 안된다.
		 * 
		 * 전체 동작 : 
		 * 		- 한번 움직인 후, 현재 이동방향이 아닌 다른 세방향으로 모두 동작함 ( BFS 느낌)
		 * 		- 각 동작한 결과를 [][]으로 저장해야 함.
		 * 		=> 결과 보드, 이전 이동 방향이 담긴 자료구조 : State 
		 * */
		
		Queue<State> q = new ArrayDeque<>();
		q.add(new State(map, -1, -1));
				
		int T = 0;
		while(T++<10 && !q.isEmpty()) {
			int len = q.size();
			while(len-->0) {
				State cur = q.remove();
				
				for(int d=0; d<4; d++) {
					// 동서->동서, 북남->북남은 반복된 결과를 불러옴
					if(cur.prevd != -1 && cur.prevd < 2 && d < 2) continue;
					if(cur.prevd != -1 && cur.prevd >= 2 && d >=2) continue;
//					if(cur.prevd == d) continue;
					State nxt = move(cur, d); // 움직임 후 반환 // 안에서 성공, 실패 조건 따짐
					
					// 만약 움직인 후 map과, 현재 map의 상태가 같다면 -> 더이상 진행할 필요 없음.
					// 		배열을 다 비교하기엔 리스크가 크므로, move할 때 구슬이 이동했는지 확인해주기.
					if(nxt.state <= 0) continue; // 실패했거나, 이동 없었음
//					printmap(nxt, T);
					if(nxt.state == 1) { // 성공 => 이동 횟수 출력 후, 종료
						System.out.println(T);
						return;
					}
					
					q.add(nxt);
				}
			}
		}
		System.out.println(-1);
	}
	static void printmap(State state, int T) {
		System.out.println("--------------" + T + "-------------" + state.prevd );
		for(int i=0; i<N; i++) {
			System.out.println(Arrays.toString(state.map[i]));
		}
		System.out.println();
	}
	static void printmap(char[][] map) {
		for(int i=0; i<N; i++) {
			System.out.println("\t" + Arrays.toString(map[i]));
		}
		System.out.println();
	}
	static class State{
		char[][] map;
		int prevd;
		int state; // move함수에서, 이동 없음: -1, 실패:0, 성공: 1, 이동함: 2, 
		public State(char[][] map, int prevd, int state) {
			this.prevd = prevd;
			this.map = map;
			this.state = state;
		}
	}
	/**
	 * 서, 동 -> 각 row에 대해 검사
	 * 북, 남 -> 각 col에 대해 검사
	 * 
	 * 움직임 :
	 * 1. 기울임 방향이 있음 : 서, 동, 북, 남
	 * 2. 기울이는 각 row 또는 col에 구멍이 위치한다면
	 * 		-> 성공,실패 조건 따져봄
	 * 3. 구멍이 없다면 이동
	 * 		- 처음 빈 곳(구슬이 이동할 곳) = idx, 현재 탐색 위치 = cur
	 * 		- cur == 구슬이 있음 -> idx로 이동시킴 -> idx++
	 * 		- cur == 빈 곳 -> continue
	 * 		- cur == 벽 -> idx = cur+1
	 * 		- cur < M-1 or N-1 or 1일 동안 탐색
	 * 4. 10회 이동이 끝나면 종료
	 * */
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static State move(State cur, int d){
		char[][] curmap = cur.map;
		char[][] newmap = new char[N][];
		for(int i=0; i<N; i++) newmap[i] = cur.map[i].clone();
		
		int isMoved = -1;
		if(d == 0) { // 서
			for(int r=1; r<N-1; r++) {
				if(r == hy) { // 구슬이 있는 경우
					int state = checkHole(newmap, d); // 상태 : 실패:0, 성공: 1, 이동함: 2
					if(state == 0) return new State(new char[0][], -1, 0);
					if(state == 1) return new State(newmap, -1, 1);
				} // 바로 return하지 않는다면, 규칙대로 구슬을 이동시키면 된다는 것
				for(int c=1, idx=1; c<M-1; c++) {
					if(newmap[r][c] == '.') continue;
					if(newmap[r][c] == '#') {idx = c+1; continue;}
					if(newmap[r][c] == 'R' || newmap[r][c] == 'B') {
						if(idx++ == c) continue; // 구슬이 제자리라면 움직이지 않움
						newmap[r][idx-1] = newmap[r][c];
						newmap[r][c] = '.';
						isMoved = 2;
						continue;
					}
					if(idx > M-1) break;
				}
				
			}
		} 
		else if(d == 1) { // 동
			for(int r=1; r<N-1; r++) {
				if(r == hy) { // 구슬이 있는 경우
					int state = checkHole(newmap, d); // 상태 : 실패:0, 성공: 1, 이동함: 2
					if(state == 0) return new State(new char[0][], -1, 0);
					if(state == 1) return new State(newmap, -1, 1);
				} // 바로 return하지 않는다면, 규칙대로 구슬을 이동시키면 된다는 것
				for(int c=M-2, idx=M-2; c>0; c--) {
					if(newmap[r][c] == '.') continue;
					if(newmap[r][c] == '#') {idx = c-1; continue;}
					if(newmap[r][c] == 'R' || newmap[r][c] == 'B') {
						if(idx-- == c) continue; // 구슬이 제자리라면 움직이지 않움
						newmap[r][idx+1] = newmap[r][c];
						newmap[r][c] = '.';
						isMoved = 2;
						continue;
					}
					if(idx < 1) break;				
				}
			}
		} 
		else if(d == 2) { // 남
			for(int c=1; c<M-1; c++) {
				if(c == hx) { // 구슬이 있는 경우
					int state = checkHole(newmap, d); // 상태 : 실패:0, 성공: 1, 이동함: 2
					if(state == 0) return new State(new char[0][], -1, 0);
					if(state == 1) return new State(newmap, -1, 1);
				} // 바로 return하지 않는다면, 규칙대로 구슬을 이동시키면 된다는 것
				for(int r=N-2, idx=N-2; r>0; r--) {
					if(newmap[r][c] == '.') continue;
					if(newmap[r][c] == '#') {idx = r-1; continue;}
					if(newmap[r][c] == 'R' || newmap[r][c] == 'B') {
						if(r == idx--) continue; // 구슬이 제자리라면 움직이지 않움
						newmap[idx+1][c] = newmap[r][c];
						newmap[r][c] = '.';
						isMoved = 2;
						continue;
					}
					if(idx < 1) break;
				}				
			}
		} 
		else if(d == 3) { // 북 ^
			for(int c=1; c<M-1; c++) {
				if(c == hx) { // 구슬이 있는 경우
					int state = checkHole(newmap, d); // 상태 :, 실패:0, 성공: 1, 아무것도 아님: 2
					if(state == 0) return new State(new char[0][], -1, 0);
					if(state == 1) return new State(newmap, -1, 1);
				} // 바로 return하지 않는다면, 규칙대로 구슬을 이동시키면 된다는 것
				for(int r=1, idx=1; r<N-1; r++) {
					if(newmap[r][c] == '.') continue;
					if(newmap[r][c] == '#') {idx = r+1; continue;}
					if(newmap[r][c] == 'R' || curmap[r][c] == 'B') {
						if(idx++ == r) continue; // 구슬이 제자리라면 움직이지 않움
						newmap[idx-1][c] = newmap[r][c];
						newmap[r][c] = '.';
						isMoved = 2;
						continue;
					}
					if(idx > N-1) break;				
				}
			}
		}
		
		return new State(newmap, d, isMoved);		
	}
	static int checkHole(char[][] curmap, int dir) {
		// 구멍-벽 사이에 빨간 구슬있는지 찾음 (파란 구슬은 있으면 바로 실패 반환)
		boolean red = false;
		if(dir == 0) { // 서
			// <- 이동방향 : 구멍 -- 벽 사이에 구슬 있는지 판단
			int wall = hx; // 벽 위치 찾음
			while(curmap[hy][wall] != '#') wall++;
			for(int x=hx; x<wall; x++) {
				if(curmap[hy][x] == 'B') {return 0;}
				else if(curmap[hy][x] == 'R') red = true;
			}
		}
		else if(dir == 1) { // 동
			// -> 이동방향 : 벽 -- 구멍 사이에 구슬 있는지 판단
			int wall = hx; // 벽 위치 찾음
			while(curmap[hy][wall] != '#') wall--;
			for(int x=hx; x>wall; x--) {
				if(curmap[hy][x] == 'B') {return 0;}
				else if(curmap[hy][x] == 'R') red = true;
			}
		}
		else if(dir == 2) { // 남
			// v 이동방향 : 벽 -- 구멍 사이에 구슬 있는지 판단
			int wall = hy; // 벽 위치 찾음
			while(curmap[wall][hx] != '#') wall--;
			for(int y=hy; y>wall; y--) {
				if(curmap[y][hx] == 'B') {return 0;}
				else if(curmap[y][hx] == 'R') red = true;
			}
		}
		else if(dir == 3) { // 북
			// ^ 이동방향 : 구멍 -- 벽 사이에 구슬 있는지 판단
			int wall = hy; // 벽 위치 찾음
			while(curmap[wall][hx] != '#') wall++;
			for(int y=hy; y<wall; y++) {
				if(curmap[y][hx] == 'B') {return 0;}
				else if(curmap[y][hx] == 'R') red = true;
			}
		}
		
		return red ? 1 : 2;
	}
}