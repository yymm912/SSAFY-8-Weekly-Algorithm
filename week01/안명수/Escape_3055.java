package algorithm.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Escape_3055 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		int Y, X;
		
		Y = Integer.parseInt(stk.nextToken());
		X = Integer.parseInt(stk.nextToken());
		
		
		// 고슴도치의 위치를 저장할 Queue
		Queue<Dochi> dochis = new LinkedList<>();
		
		// 물의 위치를 저장할 ArrayList
		List<Pos> water = new ArrayList<>();
		
		// 맵 정보 로드
		char[][] area = new char[Y][X];
		boolean[][] visited = new boolean[Y][X];
		for(int y = 0; y < Y; y++) {
			String str = br.readLine();
			for(int x = 0; x < X; x++) {
				area[y][x] = str.charAt(x);
				Pos p = new Pos(y,x);
				
				if(area[y][x] == 'S')	dochis.add(new Dochi(p, 0));
				else if(area[y][x] == '*') water.add(p);
			}
		}
		
		br.close();
		
		// 출력결과를 도달할 수 없는 -1로 세팅 ( -1이 반환되면 목표지점에 도달하는 고슴도치가 없는걸로 판정 )
		int result = -1;
		
		// 고슴도치와 물이 이동가능한 방향 ( 동남서북 )
		int[] dy = {0, 1, 0, -1};
		int[] dx = {1, 0, -1, 0};
		
		int tmp = 0;
		
		// 이동중인 고슴도치가 없을때까지 반복
		while(!dochis.isEmpty()) {
			// poll() 원소 반환 + 삭제
			Dochi now = dochis.poll();
			Pos pos = now.pos;
			
			// 이미 방문한 기록이 있으면 무시
			if(visited[pos.y][pos.x]) continue;
			
			visited[pos.y][pos.x] = true;
			
			//목적지에 도착했으면 탈출
			if(area[pos.y][pos.x] == 'D') {
				result = now.count;
				break;
			}
			
			// 물의 이동 
			// 이번 턴의 고슴도치 이동이 모두 끝났는지 체크
			if(now.count != tmp) {
				tmp = now.count;
				
				List<Pos> nWater = new ArrayList<Pos>();
				
				for(Pos w : water) {
					for(int i = 0; i < 4; i++) {
						int ny = w.y + dy[i];
						int nx = w.x + dx[i];
						if(ny < 0 || ny >= Y || nx < 0 || nx >= X) continue;
						if(area[ny][nx] == '*' || area[ny][nx] == 'X' || area[ny][nx] == 'D') continue;
						
						area[ny][nx] = '*';
						nWater.add(new Pos(ny,nx));
					}
				}
				
				water = null;
				water = nWater;
			}
			
			// 현재 고슴도치의 위치에 물이 차있으면 사망
			if(area[pos.y][pos.x] == '*') continue;
			
			// 고슴도치 방향 탐색
			for(int i = 0; i < 4; i++) {
				int ny = pos.y + dy[i];
				int nx = pos.x + dx[i];
				
				if(ny < 0 || ny >= Y || nx < 0 || nx >= X) continue;
				if(area[ny][nx] == 'X' || area[ny][nx] == '*' || visited[ny][nx]) continue;
				Pos npos = new Pos(ny,nx);
				dochis.add(new Dochi(npos, now.count + 1));
			}
			
			
			
		}
		
		
		if(result == -1) System.out.println("KAKTUS");
		else System.out.println(result);
	}
	
	// 고슴도치 정보 클래스
	public static class Dochi{
		Pos pos;
		int count;
		
		public Dochi(Pos pos, int count) {
			this.pos = pos;
			this.count = count;
		}
		

	}
	
	public static class Pos{
		int y;
		int x;
		
		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
