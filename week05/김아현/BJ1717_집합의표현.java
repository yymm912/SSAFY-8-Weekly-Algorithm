package forStudy.week05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 서로소 집합
public class BJ1717_집합의표현 {

	static int N, M;
	static int[] parents;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		makeSet(); // 대표자만 존재하는 서로소 집합 생성
		
		// 주어지는 연산 수행
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(op == 0) { // 합집합
				union(a,b);
			} else if(op == 1) { // 같은 부모인지 확인
				sb.append(isSame(a, b) ? "YES" : "NO").append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	static void makeSet() {
		parents = new int[N+1];
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int n) {
		if(parents[n] == n) return n;
		return parents[n] = find(parents[n]);
	}
	
	static void union(int a, int b) {
		int rA = find(a);
		int rB = find(b);
		
		if(rA < rB) parents[rB] = rA;
		else if(rA > rB) parents[rA] = rB;
	}
	
	static boolean isSame(int a, int b) {
		return find(a) == find(b);
	}
	
}
