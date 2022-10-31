package week13;

import java.io.*;
import java.util.*;

public class BJ13904_과제 {
	static class Report{
		int deadLine, score;
		public Report(int deadLine, int score) {
			super();
			this.deadLine = deadLine;
			this.score = score;
		}
	}
	static int N, totalScore;	// 과제 개수, 총 점수
	static boolean[] plan = new boolean[1001];	// 0: dummy
	static PriorityQueue<Report> pq = new PriorityQueue<Report>(
			(r1, r2) -> r1.score==r2.score? r2.deadLine-r1.deadLine : r2.score-r1.score);
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			pq.add(new Report(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		while(!pq.isEmpty()) {
			Report r = pq.poll();
			int idx = r.deadLine;
			if(plan[r.deadLine])  {
				idx = getEmptyIdx(r.deadLine-1);
				if(idx==-1) continue;
			}
			plan[idx] = true;
			totalScore += r.score;
		}
		System.out.println(totalScore);
	}
	static int getEmptyIdx(int n) {
		for (int i = n; i >= 1; i--) {
			if(!plan[i]) return i;
		}
		return -1;
	}
}
