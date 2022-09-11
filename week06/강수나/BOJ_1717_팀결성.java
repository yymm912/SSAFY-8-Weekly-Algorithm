

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1717_팀결성 {

	static int n, m, op, a, b;
	static int[] parent;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		parent = new int[n+1];
		
		//1. 자기자신이 곧 대장
		makeSet();
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			op = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			if (op == 0) union(a, b); //합집합
			else if (op == 1) {
				if (find(a) == find(b)) System.out.println("yes");
				else System.out.println("no");//대장 찾기
			}
		}
	}
	
	static void makeSet() {
		for (int i = 1; i <= n; i++) {
			parent[i] = i; //부모 저장
		}
	}
	
	static void union(int n1, int n2) {
		
		int p1 = find(n1);
		int p2 = find(n2);
		/*
		if (p1 < p2) parent[p2] = p1; //작은 녀석이 위에옴?
		else parent[p1] = p2;
		*/
		parent[p1] = p2;
	}
	
	static int find(int n1) {
		if (parent[n1] == n1) return n1;
		return parent[n1] = find(parent[n1]); //계속 갱신해주면 덜돈다
	}
}
