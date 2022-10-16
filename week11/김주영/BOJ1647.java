package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1647 {

	static int N,M;
	static List<Edge> edge;
	static int[] parents;
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		st=new StringTokenizer (br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		edge=new ArrayList<>();
		
		parents=new int[N+1];
		
		for (int i=0; i<M; i++) {
			st=new StringTokenizer (br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			
			edge.add(new Edge (a,b,c));
		}
		
		Collections.sort(edge);
		makeSet();

		int sum=0;
		int max=0;
		
		for (int i=0; i<edge.size(); i++) {
			int u=edge.get(i).u;
			int v=edge.get(i).v;
			int dist=edge.get(i).dist;
			
			if (union (u,v)) {
				sum+=dist;
				max=dist;
			} 
		}
		System.out.println(sum-max);
	}
	
	static int find (int u) {
		if (u==parents[u]) return u;
		else return find(parents[u]);
	}
	
	static boolean union (int u, int v) {
		u=find(u);
		v=find(v);
		
		if (u==v) return false;
		else {
			if (u>v) parents[u]=v;
			else parents[v]=u;
		}
		
		return true;
	}
	
	static void makeSet () {
		for (int i=1; i<=N; i++)
			parents[i]=i;
	}
	
	static class Edge implements Comparable <Edge>{
		int u;
		int v;
		int dist;
		
		Edge (int u, int v, int dist) {
			this.u=u;
			this.v=v;
			this.dist=dist;
		}

		@Override
		public int compareTo(Edge e) {
			return this.dist-e.dist;
		}

		
	}
}
