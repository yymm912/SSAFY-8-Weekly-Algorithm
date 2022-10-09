package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_점심식사시간_2383 {
	
	static int T, N, ans;
	static int[][] map;
	
	static boolean[] select;
	
	static List<Node> students = new ArrayList<>();
	static Node[] stairs = new Node[2];
	
	static Deque<Student> q = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			
			ans = Integer.MAX_VALUE;
			
			Arrays.fill(stairs, null);
			students.clear();
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					// map[i][j] == 1(학생)이면 students list에 넣는다.
					if (map[i][j] == 1) students.add(new Node(i, j));
					
					// map[i][j] > 1(계단)이면 stairs list에 넣는다.
					if (map[i][j] > 1) {
						if (stairs[0] == null) stairs[0] = new Node(i, j);
						else stairs[1] = new Node(i, j);
					} 
				}
			}
			
			// 부분집합을 사용해 1번 계단으로 갈지 2번 계단으로 갈지 선택한다.
			select = new boolean[students.size()];
			
			subset(0);
			
			System.out.println("#" + test_case + " " + ans);
		}
	}
	
	static void subset(int srcIdx) {
		if (srcIdx == students.size()) {
			// complete code
			// select된 학생들은 1번 계단, 그렇지 않은 학생들은 2번 계단으로 내려간다.
			int stair1Time = goStair1();
			int stair2Time = goStair2();
			
			int maxTime = Math.max(stair1Time, stair2Time);
			ans = Math.min(ans, maxTime);
			
			return;
		}
		
		select[srcIdx] = true;
		subset(srcIdx + 1);
		
		select[srcIdx] = false;
		subset(srcIdx + 1);
	}
	
	static int goStair1() {
		// select된 학생들
		q.clear();
		int time = 0;
		
		for (int i = 0; i < students.size(); i++) {
			if (select[i]) {
				int x = students.get(i).x;
				int y = students.get(i).y;
				int dist = Math.abs(stairs[0].x - x) + Math.abs(stairs[0].y - y);
				
				q.add(new Student(x, y, dist, -1));
			}
		}
		
		int emptyStair = 3;
		int waiting = 0;
		while (!q.isEmpty()) {
			int currentSize = q.size();
			
			for (int i = 0; i < currentSize; i++) {
				Student st = q.poll();
				
				if (st.dist > 0) {
					// 계단에 아직 도착 안한 경우
					q.add(new Student(st.x, st.y, st.dist - 1, st.stairRemain));
				} else {
					// 계단에 도착한 경우
					if (st.stairRemain == -1) {
						// 계단에 막 도착한 경우
						if (emptyStair >= 1) {
							// 계단에 아직 자리가 있으면 계단에 오른다.
							emptyStair--;
							q.add(new Student(st.x, st.y, st.dist, map[stairs[0].x][stairs[0].y]));
						} else {
							// 계단에 자리가 없으면 기다린다.
							q.addLast(st);
							waiting++;
						}
					} else {
						// 계단 위에 있는 경우
						if (st.stairRemain >= 2) {
							// 아직 계단의 남은 길이가 있는 경우
							q.add(new Student(st.x, st.y, st.dist, st.stairRemain - 1));
						} else if (st.stairRemain == 1){
							// 계단이 끝났다면
							emptyStair++;
							if (waiting >= 1 && !q.isEmpty()) {
								Student next = q.poll();
								q.add(new Student(next.x, next.y, next.dist, map[stairs[0].x][stairs[0].y]));
								waiting--;
							}
						}
					}
				}
			}
			
			time++;
		}
		
		return time;
	}
	
	static int goStair2() {
		// select되지 않은 학생들
		q.clear();
		int time = 0;
		
		for (int i = 0; i < students.size(); i++) {
			if (!select[i]) {
				int x = students.get(i).x;
				int y = students.get(i).y;
				int dist = Math.abs(stairs[1].x - x) + Math.abs(stairs[1].y - y);
				
				q.add(new Student(x, y, dist, -1));
			}
		}
		
		int emptyStair = 3;
		int waiting = 0;
		while (!q.isEmpty()) {
			int currentSize = q.size();
			
			for (int i = 0; i < currentSize; i++) {
				Student st = q.poll();
				
				if (st.dist > 0) {
					// 계단에 아직 도착 안한 경우
					q.add(new Student(st.x, st.y, st.dist - 1, st.stairRemain));
				} else {
					// 계단에 도착한 경우
					if (st.stairRemain == -1) {
						// 계단에 막 도착한 경우
						if (emptyStair >= 1) {
							// 계단에 아직 자리가 있으면 계단에 오른다.
							emptyStair--;
							q.add(new Student(st.x, st.y, st.dist, map[stairs[1].x][stairs[1].y]));
						} else {
							// 계단에 자리가 없으면 기다린다.
							q.addLast(st);
						}
					} else {
						// 계단 위에 있는 경우
						if (st.stairRemain >= 2) {
							// 아직 계단의 남은 길이가 있는 경우
							q.add(new Student(st.x, st.y, st.dist, st.stairRemain - 1));
						} else if (st.stairRemain == 1){
							// 계단이 끝났다면
							emptyStair++;
							if (waiting >= 1 && !q.isEmpty()) {
								Student next = q.poll();
								q.add(new Student(next.x, next.y, next.dist, map[stairs[1].x][stairs[1].y]));
								waiting--;
							}
						}
					}
				}
			}
			
			time++;
		}
		
		return time;
	}
	
	static class Node {
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Student {
		int x;
		int y;
		int dist;
		int stairRemain;
		
		public Student(int x, int y, int dist, int stairRemain) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.stairRemain = stairRemain;
		}
	}
}
