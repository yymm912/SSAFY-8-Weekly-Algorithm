package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ6497 {

	static int M,N, total;
	static List<Edge> edge;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		   BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		   StringTokenizer st=null;
		   
		   while (true) {
			   st=new StringTokenizer (br.readLine());
			   M=Integer.parseInt(st.nextToken());
			   N=Integer.parseInt(st.nextToken());
			   
			   if (N==0 && M==0) break;
			   
			   edge=new ArrayList<>();
			   
			   for (int i=0; i<N; i++) {
				   st=new StringTokenizer (br.readLine());
				   int x=Integer.parseInt(st.nextToken());
				   int y=Integer.parseInt(st.nextToken());
				   int z=Integer.parseInt(st.nextToken());
				   
				   total+=z;
				   edge.add(new Edge (x,y,z));
			   }
			   
			   Collections.sort(edge, (Edge e1, Edge e2) -> e1.z-e2.z);
			   makeSet ();
			   
			   int dist=0;
			   for (int i=0; i<edge.size(); i++) {
				   int x=edge.get(i).x;
				   int y=edge.get(i).y;
				   int z=edge.get(i).z;
				   
				   if (union (x,y)) dist+=z;
			   }
			   System.out.println(total-dist);
		   }
		   
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
	
	static int find (int u) {
		if (u==parents[u]) return u;
		else return find(parents[u]);
	}
	
	static void makeSet () {
		parents=new int[M+1];
		for (int i=1; i<=M; i++)
			parents[i]=i;
	}
	
	static class Edge {
		int x,y,z;
		
		Edge (int x, int y, int z) {
			this.x=x;
			this.y=y;
			this.z=z;
		}
	}

}
