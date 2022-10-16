package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class SWEA2383점심식사시간2 {

	static int T, N, M, S;
	static int answer;
	static int time;
	static Dist person[] = new Dist[11]; // 사람 위치 저장 
	static Dist stair[] = new Dist[2]; // 계단 위치 저장 
	static int stairLen[] = new int[2]; // 계단 길이 저장 
	static int stair1[] = new int[3]; // 1번 계단에서 대기하는 사람 
	static int stair2[] = new int[3]; // 2번 계단에서 대기하는 사람 
	static int p; // 사람 수 
	static int s; // 계단 수 
	static int tgt[];
	static boolean flag; 
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			answer = Integer.MAX_VALUE;
			s = 0;
			p = 0;
			person = new Dist[11]; // 사람 위치 저장 
			stair = new Dist[2]; // 계단 위치 저장 
			stairLen = new int[2]; // 계단 길이 저장 
			stair1 = new int[3]; // 1번 계단에서 대기하는 사람 
			stair2 = new int[3]; // 2번 계단에서 대기하는 사람 
			
			N = Integer.parseInt(br.readLine());
			
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int n = Integer.parseInt(st.nextToken());
					
					if( n == 1 ) { // 사람 위치 저장 
						person[p++] = new Dist(i, j);
					}
					if( n >= 2 ) { // 계단 위치 저장 
						stair[s] = new Dist(i, j); 
						stairLen[s] = n;
						s++;
					}
				}
			}
			
		
			tgt = new int[p]; // 사람 수 만큼 
			perm(0);
			
			System.out.println("#" + tc + " " + answer );
			
		} // testcase
		
	}
	
	static void simulation() {
		
		PriorityQueue<Integer> pq1 = new PriorityQueue<>(); // 첫 번째 계단으로 가는 사람들 시간 저장 
		PriorityQueue<Integer> pq2 = new PriorityQueue<>(); // 두 번째 계단으로 가는 사람들 시간 저장
		
		for (int i = 0; i < tgt.length; i++) {
			int d = getDistance(person[i], stair[tgt[i]]);
			if(tgt[i] == 0) {
				pq1.offer(d);
			}
			else {
				pq2.offer(d);
			}
		}
		
		p = tgt.length;
		int time = 0;
		// 1번 계단의 길이 
		int stair1_len = stairLen[0];
		// 2번 계단의 길이
		int stair2_len = stairLen[1];
		
		
		while(true) {
				
			// 종료 조건 -> 남은 사람 수 0명 && 모든 계단에 사람이 없을 경우 
			if( p == 0 ) {
				flag = false;
				
				// 모든 계단이 0일 경우만 종료 
				for (int i = 0; i < 3; i++) {
					if( stair1[i] != 0 ) {
						flag = true;
						break;
					}
				}
				for (int i = 0; i < 3; i++) {
					if( stair2[i] != 0 ) {
						flag = true;
						break;
					}
				}
				
				if(!flag) break;
			}
			
			
			for (int i = 0; i < 3; i++) { // 계단에는 세 명까지 가능 
				
				// 1번 계단 
				if(stair1[i] != 0) { // 계단에 사람이 있을 경우 
					stair1[i]--; // 그 사람이 가진 시간 줄여주기 
				}	
				if(stair1[i] == 0){ // 계단에 사람이 없을 경우 
					
					if(!pq1.isEmpty()) { // 큐에 사람이 있다면 
						if( pq1.peek()  <= time ) {  // 현재 시간보다 남은 시간이 작은 사람이 있을 경우
								p--; // 남은 사람 수 한 명 감소
								
								pq1.poll(); // pq1에 있던 사람은 계단 입구로 이동
								stair1[i] = stairLen[0]; // 계단 입구쪽에 사람 추가
							
						}
						
					}
				}
				
				
				// 2번 계단 ( 1번과 코드 동일 )
	 
				if(stair2[i] != 0) { // 계단에 사람이 있을 경우 
					stair2[i]--; // 그 사람이 가진 시간 줄여주기 
				}	
				if( stair2[i] == 0 ) { // 계단에 사람이 없을 경우 
				
					if(!pq2.isEmpty()) { // 큐에 사람이 있다면 
						if( pq2.peek()  <= time ) {  // 현재 시간보다 남은 시간이 작은 사람이 있을 경우
								p--; // 남은 사람 수 한 명 감소
								
								pq2.poll(); // pq1에 있던 사람은 계단 입구로 이동
								stair2[i] = stairLen[1]; // 계단 입구쪽에 사람 추가
							
						}
					}
				}
				 
				
				
				
			} // for i 
			
			
			
			time++;
		} // while
		
		answer = Math.min(answer, time);
		
	}
	
	static int getDistance(Dist person, Dist stair) {
		return Math.abs(person.y - stair.y) + Math.abs(person.x - stair.x);
	}
	
	// 중복 순열 
	static void perm(int tgtIdx) {
		if( tgtIdx == tgt.length ) {
			
			simulation();
			return;
		}
		
		for (int i = 0; i < 2; i++) {
			tgt[tgtIdx] = i;
			perm( tgtIdx + 1 );
		}
		
	}
	
	static class Dist {
		int y, x;

		public Dist(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}

}
/*
10
5
0 1 1 0 0
0 0 1 0 3
0 1 0 1 0
0 0 0 0 0
1 0 5 0 0
5
0 0 1 1 0
0 0 1 0 2
0 0 0 1 0
0 1 0 0 0
1 0 5 0 0
6
0 0 0 1 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 1 0 0 0 0
2 0 1 0 0 0
0 0 2 0 0 0
6
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
1 0 0 0 0 0
0 0 0 2 0 4
7
0 0 0 0 0 0 0
0 0 0 0 0 0 4
0 0 0 0 1 0 0
1 0 0 1 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
0 2 0 0 0 0 0
7
0 0 0 0 0 0 0
7 0 0 0 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
2 0 0 0 0 1 0
0 0 0 0 0 0 0
8
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 2
0 0 0 0 0 0 0 0
2 0 0 0 0 0 0 0
0 0 0 0 0 1 0 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 1 0
0 0 0 0 1 0 0 0
8
3 0 0 0 0 0 5 0
0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
1 0 1 1 0 0 0 0
0 0 0 0 0 0 1 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
9
0 0 0 1 0 0 0 0 0
0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 8
7 0 0 0 0 1 0 0 0
0 0 0 0 0 1 1 0 0
0 0 0 0 0 0 0 0 0
1 0 0 0 0 1 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
10
0 0 0 0 0 0 0 0 0 0
0 0 0 0 1 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
3 0 1 0 1 0 0 0 0 2
1 1 0 0 1 0 1 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
*/