package week012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 풀이시간: 2시간
 * */
// 점심식사시간
public class Aswea_2383_bnuri00 {
	static class Person implements Comparable<Person> {
		int n, y, x, time;

		public Person(int n, int y, int x) {
			super();
			this.n = n;
			this.y = y;
			this.x = x;
		}

		@Override
		public int compareTo(Person o) {
			return this.time - o.time;
		}
	}

	static int N, P, minTime = Integer.MAX_VALUE;

	// [0] y
	// [1] x
	// [2] 길이
	static int[] stair1 = new int[3];
	static int[] stair2 = new int[3];
	static ArrayList<Person> input = new ArrayList<>();

	static ArrayList<Person> goStair1 = new ArrayList<>();
	static ArrayList<Person> goStair2 = new ArrayList<>();

	static ArrayList<Person> stair1List = new ArrayList<>();
	static ArrayList<Person> stair2List = new ArrayList<>();

	static Queue<Person> q1 = new ArrayDeque<>(); // 계단1 대기
	static Queue<Person> q2 = new ArrayDeque<>(); // 계단2 대기

	// 부분집합
	static boolean[] tgt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			// init
			minTime = Integer.MAX_VALUE;
			input.clear();

			// input
			boolean stair1Exist = false;
			N = Integer.parseInt(br.readLine());
			P = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int n = Integer.parseInt(st.nextToken());

					if (n == 1)
						input.add(new Person(P++, i, j));
					else if (n != 0) { // 계단 입력받기
						if (stair1Exist) {
							stair2[0] = i;
							stair2[1] = j;
							stair2[2] = n;
						} else {
							stair1[0] = i;
							stair1[1] = j;
							stair1[2] = n;
							stair1Exist = true;
						}
					}
				}
			}
			// P = input.size();
			tgt = new boolean[P];
			subSet(0);
			System.out.println("#"+t+" "+minTime);
			//System.out.println(minTime);
		}

	}

	static void subSet(int tgtIdx) {
		if (tgtIdx == P) {
			// init
			init();

			//System.out.println(Arrays.toString(tgt));
			for (int i = 0; i < P; i++) {
				Person p = input.get(i);
				if (tgt[i]) {
					p.time = Math.abs(stair1[0] - p.y) + Math.abs(stair1[1] - p.x);
					goStair1.add(p);
					// pq1.add(p);
				} else {
					p.time = Math.abs(stair2[0] - p.y) + Math.abs(stair2[1] - p.x);
					goStair2.add(p);
					// pq2.add(p);
				}
			}
			timeGo();
			// move();
			return;
		}

		tgt[tgtIdx] = true;
		subSet(tgtIdx + 1);
		tgt[tgtIdx] = false;
		subSet(tgtIdx + 1);
	}

	private static void init() {
		goStair1.clear();
		goStair2.clear();

		stair1List.clear();
		stair2List.clear();

		q1.clear();
		q2.clear();

	}

	private static void timeGo() {
		int curTime = 0;

		while (true) {
			curTime++;
			
			// 종료조건
			if (curTime > minTime) break;	// 최소 시간보다 크면 그만둠
			if (complete()) {
				minTime = curTime;
				break;
			}

			

			// 사람들이 계단으로 가고있음
			int len1 = goStair1.size()-1;
			for (int i = len1; i >= 0; i--) {
				Person p = goStair1.get(i);
				p.time--;
				if (p.time <= 0) {
					q1.add(p);
					goStair1.remove(p);
				}
			}
			int len2 = goStair2.size()-1;
			for (int i = len2; i >= 0; i--) {
				Person p = goStair2.get(i);
				p.time--;
				if (p.time <= 0) {
					q2.add(p);
					goStair2.remove(p);
				}
			}

			// 계단 내려가기
			// 다내려가면(시간 0) 삭제
			if (!stair1List.isEmpty()) {
				int len = stair1List.size() - 1;
				for (int i = len; i >= 0; i--) {
					Person p = stair1List.get(i);
					p.time--;
					if (p.time == 0)
						stair1List.remove(i);
				}
			}
			if (!stair2List.isEmpty()) {
				int len = stair2List.size() - 1;
				for (int i = len; i >= 0; i--) {
					Person p = stair2List.get(i);
					p.time--;
					if (p.time == 0)
						stair2List.remove(i);
				}
			}

			// 계단에 3명까지만 들어갈 수 있음
			// 3 - 현재계단사람
			if (!q1.isEmpty()) {
				int repeat = Math.min(3 - stair1List.size(), q1.size());
				
				for (int i = 0; i < repeat; i++) {
					Person p = q1.poll();
					p.time = stair1[2];
					stair1List.add(p);
				}
			}
			if (!q2.isEmpty()) {
				int repeat = Math.min(3 - stair2List.size(), q2.size());
				for (int i = 0; i < repeat; i++) {
					Person p = q2.poll();
					p.time = stair2[2];
					stair2List.add(p);
				}
			}

		}

	}

	private static boolean complete() {
		if (goStair1.isEmpty() && goStair2.isEmpty() && stair1List.isEmpty() && stair2List.isEmpty())
			return true;
		return false;
	}

//	private static void move() {
//
//		completeTime++;
//
//		// 계단 내려가기
//		// 다내려가면(시간 0) 삭제
//		if (!stair1List.isEmpty()) {
//			int len = stair1.length - 1;
//			for (int i = len; i >= 0; i--) {
//				Person p = stair1List.get(i);
//				p.time--;
//				if (p.time == 0)
//					stair1List.remove(i);
//			}
//		}
//		if (!stair2List.isEmpty()) {
//			int len = stair1.length - 1;
//			for (int i = len; i >= 0; i--) {
//				Person p = stair2List.get(i);
//				p.time--;
//				if (p.time == 0)
//					stair2List.remove(i);
//			}
//		}
//
//		// 계단에 3명까지만 들어갈 수 있음
//		// 3 - 현재계단사람
//		while (!q1.isEmpty()) {
//			int repeat = Math.min(3 - stair1List.size(), q1.size());
//			for (int i = 0; i < repeat; i++) {
//				Person p = q1.poll();
//				p.time = stair1[2];
//			}
//		}
//		while (!q2.isEmpty()) {
//			int repeat = Math.min(3 - stair2List.size(), q2.size());
//			for (int i = 0; i < repeat; i++) {
//				Person p = q2.poll();
//				p.time = stair2[2];
//			}
//		}
//
//	}

}
