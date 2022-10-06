package baekjoon.낚시왕_17143;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;

public class Main {

	static int R, C , M, ans;
	static int[][] map, moveMap;
	static List<Shark> sharks = new ArrayList<>();
	
	static int[] dy = {0, -1, 1, 0, 0};
	static int[] dx = {0, 0, 0, 1, -1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = 0;
		map = new int[R+1][C+1];
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			map[r][c] = i;
			sharks.add(new Shark(i,r,c,s,d,z));
		}
		
		fishing();
		
		System.out.println(ans);

	}
	
	static void fishing() {
		
		for (int i = 1; i <= C; i++) {
			// 1. 낚시 왕이 오른쪽으로 이동했다.
			
			// 2. 열에 있는 상어중 땅(r = 0)과 가장 가까운 상어를 찾는다.
			for (int j = 1; j <= R; j++) {
				if(map[j][i] != 0) { // 상어를 찾았다면 사냥할 시간이다.
					Shark shark = sharks.get(map[j][i]-1);
					ans += shark.z;
					shark.hunt = true;
					map[j][i] = 0;
					break;
				}
			}
			
			// 3. 상어들이 이동한다.
			move();
		}
		
	}
	
	static void move() {
		moveMap = new int[R+1][C+1];
		for (int i = 0; i < sharks.size(); i++) {
			if(sharks.get(i).hunt) continue; // 이미 사냥당한 상어는 냅둔다.
			Shark shark = sharks.get(i);
			int y = shark.r;
			int x = shark.c;
			int d = shark.d;
			// 속력만큼 이동
			for (int s = 0; s < shark.s; s++) {
				y = y + dy[d];
				x = x + dx[d];
				if( y<=0 || x<=0 || y>R || x>C ) {
					if( d == 1 ) d = 2;
					else if( d == 2 ) d = 1;
					else if( d == 3 ) d = 4;
					else if( d == 4 ) d = 3;
					y = y + dy[d] * 2;
					x = x + dx[d] * 2;
				}
			}
			
			// 새로운 좌표 등록
			shark.r = y;
			shark.c = x;
			shark.d = d;
			
			// 해당 지역에 상어가 없으면 등록
			if( moveMap[y][x] == 0 ) moveMap[y][x] = shark.n;
			else { // 있는 경우
				Shark es = sharks.get(moveMap[y][x]-1);
				if(es.z < shark.z) { // 새로운 상어가 더 큰 경우
					es.hunt = true; // 기존 상어는 죽고
					moveMap[y][x] = shark.n; // 좌표를 새롭게 등록
				}else { // 기존의 상어가 더 큰 경우
					shark.hunt = true; // 계산된 상어는 사냥 당함
				}
			}
		}
		
		// map을 새로운 map으로 바꿔야함
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				map[i][j] = moveMap[i][j];
			}
			
		}
	}
	
	static class Shark{
		int n,r,c,s,d,z;
		boolean hunt = false;
		
		Shark(int n, int r, int c, int s, int d, int z){
			this.n = n;
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}

}
