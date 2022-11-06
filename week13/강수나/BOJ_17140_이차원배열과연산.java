package a22_10_26;

import java.util.*;
import java.io.*;

public class BOJ_17140_이차원배열과연산 {

	static int[] numCnt;
	static int[][] map;
	static List<Integer> list;
	static int r,c,k, ans, rLen, cLen;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken()) -1;
		c = Integer.parseInt(st.nextToken()) -1;
		k = Integer.parseInt(st.nextToken());
		rLen = 3;
		cLen = 3;
		map = new int[100][100];
		for (int i=0; i<3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} //입력 끝
		
		solution();
		System.out.println(ans);
	}
	
	static void solution() {
		for(int t=0; t<100; t++) {
			if (map[r][c] == k) {
				break;
			}
			//2. 배열 크기 확인 -> R연산할지 C연산할지 체크
			if (rLen >= cLen) doR();
			else doC();
			ans++;
		}
		if (map[r][c] != k) ans = -1;
	}
	
	static void doR() { //행마다 정렬
		for (int r=0; r<rLen; r++) {
			PriorityQueue<Node> pq = new PriorityQueue<> ();
			numCnt = new int[101];
			
			for (int c=0; c<cLen; c++) {
				numCnt[ map[r][c] ]++;
				map[r][c] = 0;
			}
			//1. pq에 넣어줌 -> 자동정렬
			for (int n=1; n<=100; n++) {
				if (numCnt[n] == 0) continue;
				pq.offer(new Node(n, numCnt[n]));
			}
			//2. map에 다시 넣어줌
			int len = 0;
			while(!pq.isEmpty()) {
				Node cur = pq.poll();
				map[r][len++] = cur.num;
				map[r][len++] = cur.cnt;
			}
			cLen= Math.max(cLen, len+1);
		}
	}
	
	static void doC() { //열마다 정렬
		for (int c=0; c<cLen; c++) {
			PriorityQueue<Node> pq = new PriorityQueue<> ();
			numCnt = new int[101];
			
			for (int r=0; r<rLen; r++) {
				numCnt[ map[r][c] ]++;
				map[r][c] = 0;
			}
			//1. pq에 넣어줌 -> 자동정렬
			for (int n=1; n<=100; n++) {
				if (numCnt[n] == 0) continue;
				pq.offer(new Node(n, numCnt[n]));
			}
			//2. map에 다시 넣어줌
			int len = 0;
			while(!pq.isEmpty()) {
				Node cur = pq.poll();
				map[len++][c] = cur.num;
				map[len++][c] = cur.cnt;
			}
			rLen= Math.max(rLen, len+1);
		}
	}
	
	static class Node implements Comparable<Node>{
		int num, cnt;
		Node(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Node o) {
			if (this.cnt==o.cnt) {
				return this.num-o.num;
			} else {
				return this.cnt-o.cnt;
			}
		}
	}
}
