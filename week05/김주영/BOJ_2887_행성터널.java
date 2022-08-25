package graph;

/*
 * 행성이 최대 10만개 주어지고, 좌표가 최대 10^9 이므로 
 * 모든 행성에 대해 가중치를 조사하면 메모리 초과
 * 
 * x,y,x 좌표값 차이 중 가장 작은 값들이 간선 가중치의 후보가 될 수 있음
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Point {
	int no, x,y,z;
	
	Point (int no, int x, int y, int z) {
		this.no=no;
		this.x=x;
		this.y=y;
		this.z=z;
	}
}

class Edge {
	int start;
	int end;
	int cost;
	
	Edge (int start, int end, int cost) {
		this.start=start;
		this.end=end;
		this.cost=cost;
	}
}

public class BOJ2887 {

	static int N;
	static int[] parents;
	static Point[] point;
	static List<Edge> edge=new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		
		N=Integer.parseInt(br.readLine());
		point=new Point[N];
		parents=new int[N];
		
		for (int i=0; i<N; i++) {
			st=new StringTokenizer (br.readLine());
			
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			int z=Integer.parseInt(st.nextToken());
			
			point[i]=new Point (i, x, y, z);
			parents[i]=i;
		}
		
		Arrays.sort(point, (p1,p2)-> p1.x-p2.x);
		for (int i=0; i<N-1; i++) {
			int cost=Math.abs(point[i].x-point[i+1].x);
			edge.add(new Edge (point[i].no, point[i+1].no, cost));
		}
		
		Arrays.sort(point, (p1,p2)-> p1.y-p2.y);
		for (int i=0; i<N-1; i++) {
			int cost=Math.abs(point[i].y-point[i+1].y);
			edge.add(new Edge (point[i].no, point[i+1].no, cost));
		}
		
		Arrays.sort(point, (p1,p2)-> p1.z-p2.z);
		for (int i=0; i<N-1; i++) {
			int cost=Math.abs(point[i].z-point[i+1].z);
			edge.add(new Edge (point[i].no, point[i+1].no, cost));
		}
		
		Collections.sort(edge, (Edge e1, Edge e2)-> e1.cost-e2.cost);
		
		int ans=0;
		for (int i=0, size=edge.size(); i<size; i++) {
			Edge e=edge.get(i);
			
			if (union (e.start, e.end)) {
				ans+=e.cost;
			}
		}
		

		
		System.out.println(ans);
		
	}
	
	static int find (int u) {
		if (parents[u]==u) return u;
		else return find(parents[u]);
	}
	
	
	static boolean union (int u, int v) {
		u=find(u);
		v=find(v);
		
		if (u!=v) {
			if (u>v) parents[u]=v;
			else parents[v]=u;
			
			return true;
		}
		else return false;
	}

}
