import java.io.*;
import java.util.*;

class Solution{
	static int T;
	static int N;
	static int[][] map;
	
	static class Stair{
		int x, y, len;

		public Stair(int x, int y, int len) {
			this.x = x;
			this.y = y;
			this.len = len;
		}
	}
	static class Person{
		int x, y, arriveTime, stair, moveTime;

		public Person(int x, int y, int arriveTime, int stair) {
			this.x = x;
			this.y = y;
			this.arriveTime = arriveTime;
			this.stair = stair;
		}
	}
	static Person[] people;
	static Stair[] stairs;
	static int peopleNum;
	static int answer;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			people = new Person[10];
			stairs = new Stair[2];
			peopleNum = 0;
			answer = 987654321;
			int si=0;
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						people[peopleNum++] = new Person(j, i, 0, 0);
					} else if(map[i][j] > 1) {
						stairs[si++] = new Stair(j, i, map[i][j]);
					}
				}
			}

			/**
			 * 완탐문제
			 * 1. 순열 -> 각 사람이 갈 계단 입구를 정함
			 * 2. 각 time마다 계단 입구 이동 & 내려가기 시작 
			 * 	- 각 사람들 입구까지 시간을 구함
			 * 	- timer ++;
			 * 	-   timer <= 입구까지 시간 -> 계단 이용자 3명 미만 -> 계단 이용 큐에 넣어줌 (이용자 번호, 계단 밑 도착 시간), visit 배열 체크
			 * 		timer <= 입구까지 시간 -> 계단 이용자 3명 -> 대기. 입구까지 시간++ (대기 시간을 합침)
			 *  - 계단 이용 배열에서 timer <= 계단 밑 도착 시간 -> 해당 사람을 빼줌
			 * 
			 * 3. 모두 내려가면(입구 카운트 모두 0), 최소 시간 갱신
			 * */
			dfs(0);
			System.out.println("#" + t + " " + answer);
		}
	}
	static void solve() {
		int[] down = new int[peopleNum]; // 계단 내려가는 & 완료한 사람 체크 
		int downCnt = 0;
				
		Queue<Person>[] stairDown = new ArrayDeque[2]; // 계단1,2 내려가는 사람
		for(int i=0; i<2; i++) stairDown[i] = new ArrayDeque<>();
		
		int[] stairCnt = new int[2];
		int timer = 0;
		
		for(int i=0; i<peopleNum; i++) {
			people[i].moveTime = people[i].arriveTime; // 이동시간 == 계단 도착시간으로 초기화
		}
		while(true) {
			for(int stair=0; stair<=1; stair++) {
				while(!stairDown[stair].isEmpty()) {
					// 시간 완료
					if(stairDown[stair].peek().moveTime <= timer) {
						stairDown[stair].remove();
						stairCnt[stair]--;
						downCnt++;
					} else {
						break;
					}
				}
			}
			
			for(int p=0; p<peopleNum; p++) { // 계단에 넣어주는 과정
				if(down[p] == 1) continue;
				// 시간이 다 된 사람
				if(people[p].moveTime <= timer) {
					int stair = people[p].stair;
					if(stairCnt[stair] < 3) {
						// 계단 q에 넣어줌
						people[p].moveTime += stairs[stair].len; // 내려가는 시간
						stairDown[stair].add(people[p]);
						down[p] = 1;
						stairCnt[stair]++;
					} else { // 대기
						// 이동 시간 + 대기시간 합 -> moveTime++ 해줌
						people[p].moveTime++;
					}
				} 
				else {
					continue;
				}				
			}
			timer++;
			if(downCnt == peopleNum) break;
		}
		answer = Math.min(answer, timer);
	}
	static void dfs(int peopleCnt) {
		if(peopleCnt == peopleNum) {
			solve();
			return;
		}
		for(int si=0; si<=1; si++) {
			people[peopleCnt].stair = si;
			people[peopleCnt].arriveTime = getDist(people[peopleCnt], stairs[si]);
			dfs(peopleCnt+1);
		}
	}
	static int getDist(Person p, Stair s) {
		return Math.abs(p.x-s.x) + Math.abs(p.y-s.y);
	}
}