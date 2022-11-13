import java.awt.Point;
import java.io.*;
import java.util.*;

/*
 * boj 1941 소문난 7공주
 * 
 * 참고: boj 알고리즘 분류
 * 시간: 2~3시간..?
 * 
 * <풀이>
 * - 조합, bfs
 * 
 * <삽질목록>
 * - 조합 안쓰고 dfs로만 풀려다 실패
 * - bfs code 중 while(!q.isEmpty()) 에서 ! 빼먹음
 * 
 * */
public class boj1941_소문난칠공주 {
	static int result = 0;
	static char[][] input;
	static boolean[][] team;	// 팀 위치 저장 -> bfs
	static int[] tgt = new int[7]; // 조합에서 사용
	
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		input = new char[5][];
		for (int i = 0; i < 5; i++) {
			input[i] = br.readLine().toCharArray();
		}
		
		team = new boolean[5][5];	
		comb(0, 0);
		System.out.println(result);
	}

	/*
	 * 0 1 2 3 4 
	 * 5 6 7 8 9
	 * 10...
	 * 
	 * 0 ~ 24 중 7개 뽑는 조합
	 * */
	static void comb(int src, int tgtIdx) {
		if(tgtIdx==7) {
			int somTeamNum = checkSomTeam();
			if(somTeamNum >= 4) {
				handleTeamArr(true);
				if(checkAdj()) result++;
				handleTeamArr(false);	
			}
			return;
		}
		if(src > 24) return;
		
		tgt[tgtIdx] = src;
		comb(src+1, tgtIdx+1);
		comb(src+1, tgtIdx);
	}

	// tgt 인덱스를 이용,
	// team에 팀 지우기 / 만들기 수행
	// 팀 지우기  type == false
	// 팀 만들기  type == true
	private static void handleTeamArr(boolean type) {
		for (int tgtIdx = 0; tgtIdx < 7; tgtIdx++) {
			int tgtY = tgt[tgtIdx]/5;
			int tgtX = tgt[tgtIdx]%5;
			team[tgtY][tgtX] = type;
		}
	}

	private static int checkSomTeam() {
		int somTeamNum = 0;
		for (int tgtIdx = 0; tgtIdx < 7; tgtIdx++) {
			int tgtY = tgt[tgtIdx]/5;
			int tgtX = tgt[tgtIdx]%5;
			somTeamNum += input[tgtY][tgtX]=='S'? 1: 0;
		}
		return somTeamNum;	
	}
	
	// bfs
	private static boolean checkAdj() {
		Queue<Point> q = new ArrayDeque<>();
		int y = tgt[0]/5;
		int x = tgt[0]%5;
		q.add(new Point(x, y));
		team[y][x] = false;
		
		int cnt = 1;
		while(!q.isEmpty()) {
			Point p = q.poll();
			for (int d = 0; d < 4; d++) {
				int ny = dy[d]+p.y;
				int nx = dx[d]+p.x;
				
				if(ny < 0|| ny >= 5 || nx < 0 || nx >= 5 || !team[ny][nx]) continue;
				
				cnt++;
				if(cnt==7) return true;
				
				team[ny][nx] = false;
				q.add(new Point(nx, ny));
			}
		}
		return false;
	}
}
