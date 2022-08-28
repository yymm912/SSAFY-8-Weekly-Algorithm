package _5주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ2887_행성터널 {
	static n [] X,Y,Z;
	static int N;
	static List<ed> edge;
	static int [] parent;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		X = new n[N];
		Y = new n[N];
		Z = new n[N];
		parent = new int[N];
		edge = new ArrayList<>();
		for(int i = 0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			X[i] = new n(Integer.parseInt(st.nextToken()),i);
			Y[i] = new n(Integer.parseInt(st.nextToken()),i);
			Z[i] = new n(Integer.parseInt(st.nextToken()),i);
			parent[i] = i;
		}
		Arrays.sort(X,(a,b)->a.c - b.c);
		Arrays.sort(Y,(a,b)->a.c - b.c);
		Arrays.sort(Z,(a,b)->a.c - b.c);
		
		for(int i = 0;i<N-1;i++) {
			edge.add(new ed(Math.abs(X[i+1].c-X[i].c),X[i+1].i,X[i].i));
			edge.add(new ed(Math.abs(Y[i+1].c-Y[i].c),Y[i+1].i,Y[i].i));
			edge.add(new ed(Math.abs(Z[i+1].c-Z[i].c),Z[i+1].i,Z[i].i));
		}
		Collections.sort(edge,(a,b) -> (a.d - b.d));
		long answer = 0;
		for(int i = 0;i<edge.size();i++) {
			int u = edge.get(i).s;
			int v = edge.get(i).e;
			if(find(u) != find(v)) {
				answer += edge.get(i).d;
				merge(u,v);
			}
		}
		System.out.println(answer);
	}
	static int find(int u) {
		if(u == parent[u])
			return u;
		return parent[u] = find(parent[u]);
	}
	static void merge(int u,int v) {
		u = find(u);v = find(v);
		if(u == v) return;
		parent[u] = v;
	}
	static class n{
		int c,i;
		n(int c,int i){
			this.c = c;
			this.i = i;
		}
	}
	static class ed {
		int d, s , e;
		ed(int d,int s,int e){
			this.d = d;
			this.s = s;
			this.e = e;
		}
	}

}
