package BOJ.그래프탐색.소문난칠공주_1941;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

// 번호 : 1941
// 난이도 : 골드 3
// 제목 : 소문난 칠공주
// https://www.acmicpc.net/problem/1941
public class Main {
	static int ans; // 경우의 수
	static char[][] students; // 학생의 위치를 담을 2차원 배열
	static boolean[][] isMember; // 7명의 7공주를 뽑았을 때 뽑은 학생의 좌표를 담을 2차원배열 -> 나중에 연결됬는지 판단할때 사용
	static int[] member; // 7명의 7공주를 담을 좌표 (1차원 배열로 담음)
	static boolean[][] visit;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		students = new char[5][5]; // 입력 받는 학생들의 배치도
		isMember = new boolean[5][5]; // 조합을 만들 때의 방문 체크
		member = new int[7]; // 조합으로 뽑아낼 7명의 학생들
		visit = new boolean[5][5]; // bfs돌릴때의 방문 체크

		for(int i=0;i<5;i++) {
			String line = br.readLine();
			for(int j=0;j<5;j++) {
				students[i][j] = line.charAt(j);
			}
		}
		pro();
		System.out.println(ans);
	}

	static void pro() {
		comb(0,0); // 25C7을 뽑아내기
	}

	static void comb(int k, int start) {
		if(k == 7) {
			// 이다솜파 개수와 연결여부 검사
			if(getScnt() >= 4) {
				if(isConnected()) { // 이다솜파의 구성원이 더많고, 이제는 이 조합이 연결된 상태인지를 체크
					ans++; // 연결된 상태이면 ++
				}
			}
			return;
		}

		for(int i= start;i<25;i++) {
			member[k] = i;
			isMember[i/5][i%5] = true;
			comb(k+1,i+1);
			isMember[i/5][i%5] = false;
		}
	}

	static int getScnt() {
		int sCnt = 0;
		for(int idx : member) {
			int y = idx / 5;
			int x = idx % 5;
			if(students[y][x] == 'S') {
				sCnt++;
			}
		}
		return sCnt;
	}

	static boolean isConnected() {
		// 이다솜파의 수가 더 많기 때문에 이제 뽑힌 idx들이 인접해서 연결되있는지 판단하기만 하면 댐
		Queue<Integer> Q = new ArrayDeque<Integer>();
		visit = new boolean[5][5];
		int y = member[0]/5;
		int x = member[0]%5;
		Q.offer(y);
		Q.offer(x);
		visit[y][x] = true;
		int cnt = 1;
		while(!Q.isEmpty()) {
			int cy = Q.poll();
			int cx = Q.poll();

			for(int i=0;i<4;i++) {
				int ny = cy + dy[i];
				int nx = cx + dx[i];

				if(ny < 0 || nx < 0 || ny >= 5 || nx >= 5)continue;
				if(visit[ny][nx])continue;
				if(!isMember[ny][nx])continue;
				Q.offer(ny);
				Q.offer(nx);
				visit[ny][nx] = true;
				cnt++;
			}
		}
		return cnt == 7;
	}
}
