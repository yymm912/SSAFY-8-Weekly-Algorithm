package _8주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ10775_공항 {
	static int G,P;
	static int [] parent;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		parent = new int[G+1];
		for(int i = 0;i<=G;i++) parent[i] = i;
		int answer = 0;
		for(int i = 0;i<P;i++) {
			int u = Integer.parseInt(br.readLine());
			int next = find(u);
			if(next == 0) break;
			answer++;
			merge(next - 1,u);
		}
		System.out.println(answer);

	}
	
	static int find(int u) {
		if(u == parent[u])
			return u;
		return parent[u] = find(parent[u]);
	}
	
	static void merge(int u,int v) {
		u = find(u); v = find(v);
		if(u > v) {
			int temp = u;
			u = v;
			v = temp;
		}
		if(u == v) return;
		parent[v] = u;
	}

}
